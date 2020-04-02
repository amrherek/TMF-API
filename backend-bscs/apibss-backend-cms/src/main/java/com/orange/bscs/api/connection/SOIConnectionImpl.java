/*
 * BSCS-DAO
 * soiConnection.java
 * Aug. 2010 - G. Defosse
 * Capgemini TMD
 */

package com.orange.bscs.api.connection;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.orange.bscs.api.exceptions.ErrorDictionary;
import com.orange.bscs.api.exceptions.factory.FaultFactory;
import com.orange.bscs.api.exceptions.fault.AuthenticationExceptionFault;
import com.orange.bscs.api.model.AbstractSVLObjectFactory;
import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.model.SVLObjectWrapper;
import com.orange.bscs.api.model.exception.SOIException;
import com.orange.bscs.api.utils.config.Constants;

public class SOIConnectionImpl implements SOIConnection {

    private static final Logger LOG = LoggerFactory.getLogger(SOIConnectionImpl.class);

    private static final String STATUS_ERR = "ERROR";
    private static final String STATUS_OK = "OK";


    /**
     * BSCS CMS invalidate some transaction after some SESSION.CHANGE.
     * So in the goal to limit the risk of that possibility we define a max
     * session variable that give us the control of the invalidation.
     * Once this max reached the soiConnection must be destroy/unavailable
     * to used.
     */
    public static final int MAX_SESSION_CHANGE = 200;

    /**
     * Used to trace the cycle of life of this transaction
     */
    private String logConnectionTrace = UUID.randomUUID().toString().replaceAll("-", "");
    /**
     * Used to trace the cycle of life of this transaction
     */
    private String connectionId = UUID.randomUUID().toString().replaceAll("-", "");

    private String authUsername;
    private String authPassword;
    private String componentName;
    private String componentVersion;

    private String transactionId;
    private String applicationName;
    private String bscsUserId;
    private String bscsBusinessUnit;

    private int nbSessionChange = 0;

    /**
     * For each using of this SOIConnection, it's necessary to indicate the
     * start running. This value must be initialized on each using of the
     * connection.
     */
    private long startRunning;

    private IConnectionBackend backend;


    /**
     * Don't execut twice COMMIT/ROLLBACK by framework (10ms each call)
     */
    private boolean isLastCommandCommitOrRollback;


    /**
     * No-args constructor used and must be used only for unit test run.
     */
    protected SOIConnectionImpl() {
        // Declare for UnitTest
    }


    /**
	 * Créé une connection
	 *
	 * @param backend
	 */
	public SOIConnectionImpl(
            IConnectionBackend backend) {
        this.backend = backend;

        isLastCommandCommitOrRollback = true;

        LOG.info("[C:{}, T:null] new SOIConnection()", connectionId);
    }


    /**
     * @param commandName
     * @param input
     * @param outputClass
     * @return
     * @throws SOIException backend exception
     */
    @Override
	public <T extends BSCSModel> T execute(String commandName, BSCSModel input, Class<T> outputClass) {
        LOG.debug("Calling BSCS with command: {} and input: {}", commandName, input);
        SVLObjectWrapper output = execute(commandName, null == input ? null : input.getSVLObject());
        LOG.debug("Returned Results from BSCS output: {}", output);
        return BSCSModel.newInstance(outputClass, output);
    }

    /**
     * @param commandName
     * @param input
     * @return
     * @throws SOIException backend exception
     */
    @Override
	public BSCSModel execute(String commandName, BSCSModel input) {
        LOG.debug("Calling BSCS with command: {} and input: {}", commandName, input);
        SVLObjectWrapper svlInput = null == input ? backend.createSVLObject() : input.getSVLObject();
        SVLObjectWrapper output = execute(commandName, svlInput);
        LOG.debug("Returned Results from BSCS output: {}", output);
        return BSCSModel.newInstance(BSCSModel.class, output);
    }


    /**
     * Execute a SOI command.
     *
     * @param command
     * @param input
     * @return
     * @throws SOIException backend exception
     */
    private SVLObjectWrapper execute(String command, SVLObjectWrapper input) {
        // May need a commit or rollback at the end of all process.
        isLastCommandCommitOrRollback = false;

        return backend.execute(command, input);
    }

    /**
     * Commit the transaction.
     *
     * @throws SOIException backend exception
     * @throw APIException logging exception
     */
    @Override
	public void commit() {

        // Don't check if connexion is alive (gain : 7 ms on each EBS call)
        // # if(! isAlive()){  return; } #

        StringBuilder sbTrace = null;

        boolean commited = false;
        try {
            if (LOG.isDebugEnabled()) {
                sbTrace = new StringBuilder();
                sbTrace.append("[C:").append(connectionId).append(", T:").append(transactionId)
                        .append("] COMMIT () : ");
            }
            backend.commit();
            commited = true;
            isLastCommandCommitOrRollback = true;

            if (null != sbTrace) {
                sbTrace.append("OK");
                LOG.info(sbTrace.toString());
            }
        } finally {
            if (!commited) {
                if (null != sbTrace) {
                    sbTrace.append(" ERROR");
                    LOG.info(sbTrace.toString());
                }
            }
        }
    }

    /**
     * Roll back the transaction.
     */
    @Override
	public void rollback() {
        if (!isAlive()) {
            return;
        }

        StringBuilder sbTrace = null;
        try {
            if (LOG.isDebugEnabled()) {
                sbTrace = new StringBuilder();
                sbTrace.append("[C:").append(connectionId).append(", T:").append(transactionId)
                        .append("] ROLLBACK () : ");
            }

            backend.rollback();
            isLastCommandCommitOrRollback = true;

            if (null != sbTrace) {
                sbTrace.append("OK");
                LOG.info(sbTrace.toString());
            }
        } catch (Exception e) {
            LOG.trace("Catching exception in Rollback : {}", e);
            if (null != sbTrace) {
                sbTrace.append(" ERROR");
                LOG.warn(sbTrace.toString());
            }
        }
    }


    /**
     * Preventative rollback used when the SOI connection is retrieve from the
     * pool on activation.
     * <p>
     * <p>package-protected visibility, should only be called by ISOIConnectionProvider</p>
     *
     * @throws SOIException raised if the rollback failed.
     */
	public void preventativeRollback() {
        LOG.trace("preventativeRollback [SOIConnection trace : {}] : enter", logConnectionTrace);
        if (!isLastCommandCommitOrRollback) {
            backend.rollback();
        }
        LOG.trace("preventativeRollback [SOIConnection trace : {}] : exit", logConnectionTrace);
    }

    /**
     * Close the transaction.
     * <p>
     * <p>package-protected visibility, should only be called by IConnectionProvider</p>
     */
	public void close() {
        synchronized (this) {
            LOG.info("[C:{}, T:{}] CLOSE()", connectionId, transactionId);
            backend.close();
            connectionId = null;

            // no need Commit or Rollback (but isAlive=false)
            isLastCommandCommitOrRollback = true;
        }
    }


    /**
     * Re-initialization of the root accessor.
     * <p>
     * <p>package-protected visibility, sould only be called by IConnectionProvider</p>
     *
     * @throws SOIException
     */
	public void reopen() {
        String status = STATUS_ERR;
        try {
            backend.reopen();
            status = STATUS_OK;
            // no need Commit or Rollback (but isAlive=true)
            isLastCommandCommitOrRollback = true;
        } finally {
            LOG.debug("[C:{}, T:{}] reopen={}", new Object[]{connectionId, transactionId, status});
        }
    }

    /**
     */
    public void sessionHaveChanged() {
        nbSessionChange++;
    }

    /**
     * Permits to determine whether the max number of sessions is reached.
     *
     * @return true if the max session is reached, false otherwise.
     */
    public boolean isMaxSessionChangeHaveBeenReached() {
        return getNbSessionChange() >= MAX_SESSION_CHANGE;
    }

    /**
     * This method indicate if the connection to the remote service accessor
     * is alive.
     *
     * @return true if this SOI connection is alive, otherwise false.
     */
    public boolean isAlive() {
        return backend.isAlive();
    }


    /**
     * Call when new connection is created, so need to initialise session.
     * <p>
     * <p>package-protected visibility, should only be called by SOITransactionsManager</p>
     *
     * @param bscsBusinessUnit
     * @param bscsLogin        throw APIException (AuthenticationExceptionFault.class)
     */
    public void doChangeSession(String bscsBusinessUnit, String bscsLogin) {
        try {
            if (null != bscsBusinessUnit && 0 < bscsBusinessUnit.length() && null != bscsLogin && 0 < bscsLogin.length()) {
                LOG.debug("[Transaction ID : {}] perform a SESSION.CHANGE CMS command with BU_ID = {} and USER = {}", new Object[]{transactionId, bscsBusinessUnit, bscsLogin});

                List<BSCSModel> sessionValues = new ArrayList<BSCSModel>();


                // set the USER
                BSCSModel sessionValue = new BSCSModel();
                sessionValue.setStringValue(Constants.P_KEY, "USER");
                sessionValue.setStringValue(Constants.P_VALUE, bscsLogin);
                sessionValues.add(sessionValue);

                // set the BU_ID
                sessionValue = new BSCSModel();

                sessionValue.setStringValue(Constants.P_KEY, "BU_ID");
                sessionValue.setStringValue(Constants.P_VALUE, bscsBusinessUnit);
                sessionValues.add(sessionValue);

                sessionValue = new BSCSModel();
                sessionValue.setStringValue(Constants.P_KEY, "PROCESS");
                sessionValue.setStringValue(Constants.P_VALUE, "CX");


                sessionValues.add(sessionValue);
                // execute command
                BSCSModel sessionChangeInput = new BSCSModel();
                sessionChangeInput.setListOfBSCSModelValue("values", sessionValues);

                execute(Constants.CMD_SESSION_CHANGE, sessionChangeInput.getSVLObject());
                sessionHaveChanged();
            }
        } catch (SOIException e) {
            LOG.trace("Catching SOIException in SessionChange : {}", e);
            String errorCode = AbstractSVLObjectFactory.getErrorCode(e);
            throw FaultFactory.getInstance().newAPIException(AuthenticationExceptionFault.class, ErrorDictionary.RC_0001, errorCode + " " + e.getMessage(), e);
        }

    }


    /* ===================================================================
     * 					ACCESSORS 
     * ===================================================================
     */

    public String getAuthUsername() {
        return authUsername;
    }

    public String getAuthPassword() {
        return authPassword;
    }

    public String getComponentName() {
        return componentName;
    }

    public String getComponentVersion() {
        return componentVersion;
    }

    @Override
	public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getBscsUserId() {
        return bscsUserId;
    }

    public void setBscsUserId(String userId) {
        bscsUserId = userId;
    }

    @Override
	public String getBusinessUnit() {
        return bscsBusinessUnit;
    }

    public void setBscsBusinessUnit(String businessUnit) {
        bscsBusinessUnit = businessUnit;
    }

    /**
     * @return the nbSessionChange
     */
    public int getNbSessionChange() {
        return nbSessionChange;
    }

    /**
     * Used to trace the cycle of life of this connection.
     *
     * @return the logConnectionTrace
     */
    public String getLogConnectionTrace() {
        return logConnectionTrace;
    }

    /**
     * @return return the start running of this soi connection. This value must
     * be initialized on each using of the connection.
     */
    public long getStartRunning() {
        return startRunning;
    }

    /**
     * Initialization of the start running of this connection. This value must
     * be initialized on each using of the connection.
     * <p>
     * Called by @see SOIConnectionFactory when connection is reactivated from pool.
     *
     * @param startRunning
     */
    public void setStartRunning(long startRunning) {
        this.startRunning = startRunning;
    }

    @Override
	public String getConnectionId() {
        return connectionId;
    }


    @Override
	public IConnectionBackend getBackend() {
        return backend;
    }


    /**
     * Search in IBackend source hierarchy the first backend implementing this class.
     * Used by Unit Tests to retrieve, for example, IConnectionBackendFactoryFile instance.
     *
     * @param clazz Type of IConnectionBackend to find.
     * @return first child backend implementing this class
     */
    @Override
	@SuppressWarnings("unchecked")
    public <T extends IConnectionBackend> T getBackend(Class<T> clazz) {
        if (null == backend) {
            return null;
        }
        T res = null;
        IConnectionBackend currentBackend = backend;
        IConnectionBackend src;
        while (null != currentBackend && null == res) {
            if (currentBackend.getClass().equals(clazz)) {
                res = (T) currentBackend;
            } else {
                src = currentBackend.getSourceBackend();
                if (currentBackend.equals(src)) {
                    // Should not, should return null if no source Backend
                    currentBackend = null;
                } else {
                    currentBackend = src;
                }
            }
        }
        return res;
    }

    public void setBackend(IConnectionBackend backend) {
        this.backend = backend;
    }

}
