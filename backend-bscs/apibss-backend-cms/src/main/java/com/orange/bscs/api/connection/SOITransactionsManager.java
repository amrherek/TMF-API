/*
 * Filename : SOITransactionsManager.java						8 juil. 2011
 * Author : Michaël LADOWICHX
 */
package com.orange.bscs.api.connection;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import com.orange.bscs.api.exceptions.APIException;
import com.orange.bscs.api.exceptions.ErrorDictionary;
import com.orange.bscs.api.exceptions.factory.FaultFactory;
import com.orange.bscs.api.exceptions.fault.AuthenticationExceptionFault;
import com.orange.bscs.api.exceptions.fault.InvalidTransactionIDExceptionFault;
import com.orange.bscs.api.exceptions.fault.PoolExceptionFault;

/**
 * Manager of transactions. This class manages a pool of connections.<br />
 * Each connection permits to send a command to the CIL of BSCS. If it is the
 * statefull mode that is used, a data structure permits to treat this case
 * <br />
 * This class implements {@link Observer} to have the control on the
 * {@link SOIConnectionPool} when some process on the pool occurred
 * independently of actions managed by this class.<br />
 * For example, the invalidate object process destroy an
 * {@link SOIConnectionImpl} from the pool whether the object is not validate on
 * activation, so it's necessary to cancel the reservation of the connection to
 * a transaction ID if exists.
 *
 * @author Michaël LADOWICHX
 */
public class SOITransactionsManager implements Observer, ApplicationListener<ContextClosedEvent> {

    /**
     * Static variable used to log execution informations of this class
     */
    private static final Logger LOG = LoggerFactory.getLogger(SOITransactionsManager.class);

    /**
     * Multiplier to use to manipulate secondes instead of milliseconds
     */
    private static final int COEF_MULTIPLIER_SECONDS = 1000;

    /**
     * Multiplier to use to manipulate minutes instead of milliseconds
     */
    private static final int COEF_MULTIPLIER_MINUTES = 60000;

    /**
     * The connection pool. Each connection permits to send a command to the CIL
     * of BSCS.<br />
     * This pool is initialized by a spring injection
     */
    private ISOIConnectionProvider provider;

    /**
     * In case of the stateful mode, we booked the connection in a hashtable
     * indexed by the transaction ID. A Scheduler thread will loop this
     * hashtable in the goal to evict connections which are locked since a long
     * time.
     */
    private Map<String, SOIConnectionImpl> reservedTxQueue;

    private Timer schedulerReservationControl;

    /**
     * Default timeout for a reservation of a connection (in sec)
     */
    private static final long DEFAULT_TIMEOUT = 600;

    /**
     * Default delay between each reservationchecker (in sec)
     */
    private static final long DEFAULT_DELAY = 180;

    /**
     * min delay between each reservationchecker (in sec)
     */
    private static final long MIN_DELAY = 60;

    /**
     * In case of the stateful mode : timeout for a reservation of a connection
     * (in seconds) V2
     */
    // private long statefullConnectionTimeout = DEFAULT_TIMEOUT

    /**
     * Constructor with defaults values for timeouts and delays :
     * statefullConnectionTimeout : 600 sec. delay to launch reservation cleaner
     * scheduler : 180 sec period (minimun time between execution if
     * delay+running<period then wait) : 60sec.
     *
     * @param provider Connection pool or Connection factory
     */
    public SOITransactionsManager(ISOIConnectionProvider provider) {
        this(provider, DEFAULT_TIMEOUT, DEFAULT_DELAY, MIN_DELAY);
    }

    /**
     * Constructor to use to initialize the SOIConnectionPool.
     *
     * @param provider                   the SOIConnection pool which has to be managed by this current
     *                                   manager.
     * @param statefullConnectionTimeout in case of the stateful mode : timeout for a reservation of a
     *                                   connection (in seconds)
     * @param delay                      the delay before the reservations control scheduler is to be
     *                                   executed (in seconds)
     * @param period                     the time between successive reservations control executions
     *                                   (in seconds)
     */
    public SOITransactionsManager(ISOIConnectionProvider provider, long statefullConnectionTimeout, long delay,
                                  long period) {
        this();
        this.provider = provider;
        this.provider.addObserver(this);

        // V2, Change for a @Schedule method because of somme
        // ClassDefFoundException

        // launching the timer below permits to control the reservation time on
        // SOIConnection reserved by some transaction ID in stateful mode

        schedulerReservationControl = new Timer();
        schedulerReservationControl.schedule(
                new ReservationsControlTask(statefullConnectionTimeout * COEF_MULTIPLIER_SECONDS),
                delay * COEF_MULTIPLIER_SECONDS, period * COEF_MULTIPLIER_SECONDS);
    }

    /**
     * No-arg constructor private. It's mandatory to use the constructor which
     * has a SOIConnectionPool in parameter.
     */
    private SOITransactionsManager() {
        reservedTxQueue = new HashMap<String, SOIConnectionImpl>();
    }

    /**
     * Test if the given parameter is null or empty.
     *
     * @param requestTransactionId the transaction ID to test whether it's null or empty.
     * @return true if the given parameter is null or empty. This mean it's a
     * new transaction. Otherwise return false and this mean it's not a
     * new transaction.
     */
    public static boolean isNewTransaction(String requestTransactionId) {
        return null == requestTransactionId || requestTransactionId.isEmpty();
    }

    /**
     * This method return a SOIConnection. The SOIConnection returned is either
     * a new blank SOIConnection (this is the case of stateless mode or the
     * first request of stateful mode) or a SOIConnection previously booked by a
     * request launch in stateful mode.
     *
     * @param requestTransactionId the transaction ID given by the request. Can be empty or null
     *                             which mean that you need a new blank SOIConnection, or can
     *                             contains a transaction ID which mean you need to retrieve an
     *                             existing SOIConnection previously booked.
     * @param applicationName      the name of the application which launch the request.
     * @param bscsUserId           the bscs user ID necessary to execute a command via the SOI
     *                             connection.
     * @param bscsBusinessUnit     the bscs user ID associated with the bscs user ID.
     * @return either a new blank SOIConnection (this is the case of stateless
     * mode or the first request of stateful mode) or a SOIConnection
     * previously booked by a request launch in stateful mode.
     * @throws Exception if the configured
     *                   {@link org.apache.commons.pool.PoolableObjectFactory} throws
     *                   an exception destroying soiConnection.
     */
    public final SOIConnectionImpl getSOIConnection(String requestTransactionId, String applicationName,
			String bscsUserId, String bscsBusinessUnit) {
        LOG.trace("getSOIConnection: enter");

        String transactionId;
        boolean newTransaction = isNewTransaction(requestTransactionId);
        // new transaction?
        if (newTransaction) {
            // generate a unique transaction identifier
            transactionId = UUID.randomUUID().toString().replaceAll("-", "");
            LOG.debug("generation of a new transaction id: {}", transactionId);
        } else {
            if (!isReserved(requestTransactionId)) {
                // there is no transaction associated with the given
                // transaction ID (the ID is wrong, the transaction has
                // been deleted after the timeout, etc.)

                LOG.error("transaction {} does not exist!", requestTransactionId);
                throw FaultFactory.getInstance().newAPIException(InvalidTransactionIDExceptionFault.class,
                        ErrorDictionary.API_1300, null);
            }
            LOG.debug("transaction {} is available in the pool", requestTransactionId);
            transactionId = requestTransactionId;
        }

		/* create/get the connection by means of super.borrowObject() */
        SOIConnectionImpl soiConnection = null;

        LOG.debug("getting/creating the SOIConnection whose ID is {}", transactionId);
        soiConnection = borrowSOIConnection(transactionId, newTransaction);

        if (newTransaction) {
            soiConnection.setTransactionId(transactionId);
            soiConnection.setApplicationName(applicationName);
            soiConnection.setBscsUserId(bscsUserId);
            soiConnection.setBscsBusinessUnit(bscsBusinessUnit);


			soiConnection.getBackend().doChangeSession(this, soiConnection, bscsBusinessUnit, bscsUserId);
        } else {
            if (null != bscsBusinessUnit && !bscsBusinessUnit.equals(soiConnection.getBusinessUnit())) {
                // cannot change the Business Unit during one transaction!
                LOG.error("cannot change Business Unit while the transaction is open!");
                throw FaultFactory.getInstance().newAPIException(AuthenticationExceptionFault.class,
                        ErrorDictionary.API_1009, null);
            }
			if (null != bscsUserId && !bscsUserId.equals(soiConnection.getBscsUserId())) {
                // cannot change the BSCS login during one transaction!
                LOG.error("cannot change BSCS login while the transaction is open!");
                throw FaultFactory.getInstance().newAPIException(AuthenticationExceptionFault.class,
                        ErrorDictionary.API_1010, null);
            }
        }
        LOG.trace("getSOIConnection: exit");
        return soiConnection;
    }

    /**
     * This method permits to determine whether the transaction ID given in
     * parameter has already a SOI connection reserved or not.<br />
     * A SOI connection is reserved for a transaction ID in stateful mode.
     *
     * @param transactionId the transaction ID to test.
     * @return true if the transaction ID has a SOI connection reserved.
     */
    private boolean isReserved(String transactionId) {
        return reservedTxQueue.containsKey(transactionId);
    }

    /**
     * This method borrows a SOI connection. The connection returned is either a
     * new connection or a connection previously reserved during the execution
     * of the previous request in case of stateful mode.
     *
     * @param transactionId the ID of the transaction associated with the SOI connection
     *                      to borrow.
     * @return either a new connection or a connection previously reserved by a
     * previous request (stateful mode).
     * @throws Exception                                        NoSuchElementException if an instance cannot be returned
     * @throws com.orange.bscs.api.exceptions.APIException if the SOIConnection normally associated with the
     *                                                          transactionId given in parameter was not found.
     */
    private SOIConnectionImpl borrowSOIConnection(String transactionId, boolean isNewTransaction) {
        SOIConnectionImpl soiConnectionBorrowed = null;
        try {
            // 1. if it's a new transaction ID, we have to get a new blank SOI
            // connection from the pool
            if (isNewTransaction) {
                // 1.a.this a new request, we can take a new connection
                soiConnectionBorrowed = provider.retreiveConnection();
                LOG.info("A new blank SOIConnection has been borrowed");
            } else {

                soiConnectionBorrowed = reservedTxQueue.remove(transactionId);
                if (null != soiConnectionBorrowed) {
                    LOG.info("The SOIConnection associated with the following " + "trasanction id {} has been borrowed",
                            transactionId);
                } else {
                    // 1.b.b. throw an exception because we haven't found the
                    // SOIConnection normally associated with the
                    // transaction ID
                    LOG.error("The SOIConnection normally associated with the transaction ID {} was not found !",
                            transactionId);
                    throw FaultFactory.getInstance().newAPIException(PoolExceptionFault.class, ErrorDictionary.API_1200,
                            null);
                }

            }
            // Reset Connection StartTime
            soiConnectionBorrowed.setStartRunning(new Date().getTime());

        } catch (NoSuchElementException e) {
            // the pool is exhausted!

            LOG.error("transaction pool is exhausted, no more transaction can be created at this time!");
            throw FaultFactory.getInstance().newAPIException(PoolExceptionFault.class, ErrorDictionary.API_1201, e);
        } catch (SecurityException e) {
            // Error during initialization of the transaction

            LOG.error("error during transaction initialisation (e.g. login to BSCS failed, ...)", e);
            throw FaultFactory.getInstance().newAPIException(AuthenticationExceptionFault.class,
                    ErrorDictionary.API_1008, e.getMessage(), e);
        } catch (APIException e) {
            // Pool Exception (1200 for example ?)
            // don't encapsulate error
            throw e;
        } catch (Exception e) {
            // unexpected pool error

            LOG.error("Unexpected pool exception! : {}", e);
            throw FaultFactory.getInstance().newAPIException(PoolExceptionFault.class, ErrorDictionary.API_1202, e);
        }
        return soiConnectionBorrowed;
    }

    /**
     * This method booked the soiConnection given in parameter for a next used
     * by another process which have the same transaction ID as the process
     * which booked this connection.<br />
     * This method must be used in the stateful mode.
     *
     * @param soiConnection the soiConnection to book for a next used in stateful mode.
     */
    public final void reservationSOIConnection(SOIConnectionImpl soiConnection) {
        // maintain the SOIConnection for the next web Service equipped with
        // an existing transaction ID presents in the hashtable.
        // Statefull mode
        reservedTxQueue.put(soiConnection.getTransactionId(), soiConnection);
    }

    /**
     * Remove a SOI connection from the pool.
     *
     * @param soiConnection the SOI connection to remove
     * @throws Exception if the configured
     *                   {@link org.apache.commons.pool.PoolableObjectFactory} throws
     *                   an exception destroying soiConnection.
     */
    public final void deleteSOIConnection(SOIConnection soiConnection) {
        if (null == soiConnection) {
            return;
        }
        try {
            reservedTxQueue.remove(soiConnection.getTransactionId());
            if (soiConnection instanceof SOIConnectionImpl) {
                provider.destroyConnection((SOIConnectionImpl) soiConnection);
            }

        } catch (Exception e) {
            LOG.error("Error when calling deleteSOIConnection but don't know what to do", e);
        }
    }

    /**
     * Release the SOI connection given in parameter. The consequence of this
     * method is the return of the SOI connection in the pool, and remove it
     * from the hashtable if presents in it. After this method, the
     * SOIConnection given in parameter is blank ready to serve a new Web
     * Service.
     *
     * @param soiConn the transaction to release.
     */
    public final void releaseSOIConnection(SOIConnection soiConn) {
        // the connection will no longer be used. Case of stateless mode or the
        // last request on stateful mode.
        // return the connection in the pool
        try {
            reservedTxQueue.remove(soiConn.getTransactionId());
            if (soiConn instanceof SOIConnectionImpl) {
                provider.releaseConnection((SOIConnectionImpl) soiConn);
            }
        } catch (Exception e) {
            // no details about this exception in the API commons tools, so I
            // caught it here.
            LOG.error("The following exception {} has been raised when we were trying to return the "
                    + "SOIConnection in the pool !", e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void update(Observable o, Object arg) {
        if (arg instanceof DataNotificationWrapper) {
            DataNotificationWrapper dataWrapper = (DataNotificationWrapper) arg;
            switch (dataWrapper.getType()) {
                case INVALIDATE_OBJECT:
                    String transactionID = dataWrapper.getSOIConnection().getTransactionId();
                    reservedTxQueue.remove(transactionID);
                    LOG.trace("The connection which manages the following transaction ID : {}, is not available",
                            transactionID);
                    break;
                default:
                    // do nothing
                    break;
            }
        }
    }

    /**
     * @return the ConnectionProvider
     */
    public final ISOIConnectionProvider getConnectionProvider() {
        return provider;
    }

    /**
     * @param manager Pool or Factory of SOIConnection
     */
    public final void setConnectionProvider(ISOIConnectionProvider manager) {
        provider = manager;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {

        LOG.info("CLOSING APPLICATION CONTEXTE");

        // Dispose timer (necessary to dispose Spring context)
        if (null != schedulerReservationControl) {
            schedulerReservationControl.cancel();
        }

        LOG.debug("Releasing Connexions...");
        new ReservationsControlTask().run();
        reservedTxQueue.clear();
        LOG.debug("Released Connexions...");

        provider.removeObserver(this);
        provider = null;
    }

    /**
	 * Tasks which permits to control the execution time of each connection, and
	 * release connections which their time execution is too long.
	 *
	 * @author Michaël LADOWICHX
	 */
    private final class ReservationsControlTask extends TimerTask {

        /**
         * Default timeout for a reservation of a connection (in milliseconds)
         */

        private static final long DEFAULT_TIMEOUT = 5L * COEF_MULTIPLIER_MINUTES;

        /**
         * In case of the stateful mode : timeout for a reservation of a
         * connection (in milliseconds)
         */

        private long statefullConnectionTimeout = DEFAULT_TIMEOUT;

        /**
         * No-args constructor. The default timeout for a reservation of a
         * connection is DEFAULT_TIMEOUT.
         */

        private ReservationsControlTask() {
            // Class should declare an default constructor, comment in empty
            // method
        }

        /**
         * Constructor to use to initialize the timeout of the connection
         * reservation.
         *
         * @param statefullConnectionTimeout in case of the stateful mode : timeout for a reservation
         *                                   of a connection (in milliseconds)
         */
        private ReservationsControlTask(long statefullConnectionTimeout) {
            this();
            this.statefullConnectionTimeout = statefullConnectionTimeout;
        }

        /**
         * {@inheritDoc}
         */

        @Override
        public void run() {
            long executionTime;
            long currentTime = new Date().getTime();
            long startTime;
            boolean isExpired;
            List<SOIConnectionImpl> transToRemove = new ArrayList<SOIConnectionImpl>();
            synchronized (reservedTxQueue) {
                for (SOIConnectionImpl soiConnection : reservedTxQueue.values()) {
                    startTime = soiConnection.getStartRunning();
                    executionTime = currentTime - startTime;
                    isExpired = executionTime >= statefullConnectionTimeout;
                    LOG.trace(
                            "   calculate expiration of Connection {} :  now {} - Start {} = Exec {},  isExpired = (Exec >= Limit={})= {}",
                            soiConnection.getConnectionId(), currentTime, startTime, executionTime,
                            statefullConnectionTimeout, isExpired);
                    if (isExpired) {
                        transToRemove.add(soiConnection);
                    }
                }

                for (SOIConnectionImpl soiConnection : transToRemove) {
                    try {
                        reservedTxQueue.remove(soiConnection.getTransactionId());
                        soiConnection.rollback();
                        provider.releaseConnection(soiConnection);

                    } catch (Exception e) {
                        // no details about this exception in the
                        // API commons tools, so I caught it here.
                        LOG.error("The following exception {} has been raised when we were trying to "
                                + "return the SOIConnection in the pool !", e.getMessage());
                    }
                }
            }
        }
    }
    /**
     * @return la liste des transactions Statefull maintenue.
     */
    public Set<String> getReservedTransactionIds(){
        return reservedTxQueue.keySet();
    }

}
