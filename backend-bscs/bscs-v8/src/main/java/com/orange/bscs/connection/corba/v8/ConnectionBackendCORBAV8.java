package com.orange.bscs.connection.corba.v8;

import java.util.Properties;

import org.omg.CORBA.ORB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.orange.bscs.api.connection.IConnectionBackend;
import com.orange.bscs.api.connection.SOIConnection;
import com.orange.bscs.api.connection.SOITransactionsManager;
import com.orange.bscs.api.exceptions.APIException;
import com.orange.bscs.api.model.AbstractSVLObjectFactory;
import com.orange.bscs.api.model.ParameterListWrapper;
import com.orange.bscs.api.model.SVLObjectListWrapper;
import com.orange.bscs.api.model.SVLObjectWrapper;
import com.orange.bscs.api.model.exception.CMSErrorInfo;
import com.orange.bscs.api.model.exception.CMSException;
import com.orange.bscs.api.model.exception.SOIException;
import com.orange.bscs.model.wrapper.ParameterListWrapperV8;
import com.orange.bscs.model.wrapper.SVLObjectWrapperV8;
import com.semagroup.targys.framework.common.corba.NvElementI;
import com.semagroup.targys.framework.common.corba.NvElementListIHolder;
import com.semagroup.targys.framework.common.util.CORBAAdapter;
import com.semagroup.targys.servicelayer.client.RemoteParameterListLoader;
import com.semagroup.targys.servicelayer.common.NVExchangeFormatFactory;
import com.semagroup.targys.servicelayer.common.ParameterList;
import com.semagroup.targys.servicelayer.common.ParameterListException;
import com.semagroup.targys.servicelayer.common.ServiceLayerIntrospector;
import com.semagroup.targys.servicelayer.corba.ErrorInfoI;
import com.semagroup.targys.servicelayer.corba.ErrorInfoListIHolder;
import com.semagroup.targys.servicelayer.corba.LoginFailedExceptionI;
import com.semagroup.targys.servicelayer.corba.ServiceFactoryI;
import com.semagroup.targys.servicelayer.corba.ServiceLayerExceptionI;
import com.semagroup.targys.servicelayer.corba.ServiceObjectI;
import com.semagroup.targys.servicelayer.corba.ServiceRootI;
import com.semagroup.targys.servicelayer.corba.ServiceRootIHelper;

/**
 * Group all CORBA commands in one class.
 *
 * @author IT&Labs
 */
public class ConnectionBackendCORBAV8 implements IConnectionBackend {

    private static final Logger LOG = LoggerFactory.getLogger(ConnectionBackendCORBAV8.class);

    private String server;
    private String authUsername;
    private String authPassword;

    private String serviceobjectversion;

	private int timezoneOffset;

    /**
     * The property representing the ORB Visibroker agent IP address.
     */
	public final static String ORB_AGENT_ADDR_PROPERTY = "ORBagentAddr";

    /**
     * The property representing the ORB Visibroker agent port.
     */
	public final static String ORB_AGENT_PORT_PROPERTY = "ORBagentPort";

    /**
     * The property representing the CMS ServiceObjectI version.
     */
	public final static String SERVICE_OBJECT_VERSION_PROPERTY = "cms.serviceobjectversion";

    /**
     * The CMS server name prefix.
     */
    private final static String CMS_SERVER_NAME_PREFIX = "SL_";
    /**
     * Missing property message
     * 
     * TODO create a specific exception
     */
    private final static String MISSING_PROPERTY_MESSAGE = "Missing \"%s\" property";

    /**
     * The CMS Corba ServiceFactoryI object.
     */
    private ServiceFactoryI _serviceFactory = null;

    /**
     * The CMS Corba ServiceObjectI object.
     */
    private ServiceObjectI _serviceObject = null;

    /**
     * The ORB used to communicate with CMS Corba server.
     */
    protected ORB orb = null;

    private static Properties properties;

	public ConnectionBackendCORBAV8(int timezoneOffset) {
		this.timezoneOffset = timezoneOffset;
	}

    /**
     * Initialise CORBA and create a NamingContext.
     *
     * @param properties
     *            initialization properties
     * @return the {@link ORB}
     * @throws SOIException
     *             A RuntimeException throw if initialisation fail
     */
    public ORB initialiseOnceCorba(Properties properties) {
        LOG.info("=> initialiseOnceCorba()");
        ServiceLayerIntrospector.setParameterListLoader(RemoteParameterListLoader.instance());

        if (properties.getProperty(ORB_AGENT_ADDR_PROPERTY) == null) {
            String msg = String.format(MISSING_PROPERTY_MESSAGE, ORB_AGENT_ADDR_PROPERTY);
            LOG.error(msg);
            throw new SOIException(msg);
        }
        if (properties.getProperty(ORB_AGENT_PORT_PROPERTY) == null) {
            String msg = String.format(MISSING_PROPERTY_MESSAGE, ORB_AGENT_PORT_PROPERTY);
            LOG.error(msg);
            throw new SOIException(msg);
        }
        if (properties.getProperty(SERVICE_OBJECT_VERSION_PROPERTY) == null) {
            String msg = String.format(MISSING_PROPERTY_MESSAGE, SERVICE_OBJECT_VERSION_PROPERTY);
            LOG.error(msg);
            throw new SOIException(msg);
        }

        // Addition of mandatory Visibroker properties.
        properties.put("org.omg.CORBA.ORBClass", "com.inprise.vbroker.orb.ORB");
        properties.put("org.omg.CORBA.ORBSingletonClass", "com.inprise.vbroker.orb.ORB");

        ConnectionBackendCORBAV8.properties = properties;
        // initializes the ORB for future CORBA communication.
        initORB();

        // _convertor = new ConvertorV8(_orb);
        LOG.info("<= initialiseOnceCorba()");
        return orb;
    }

    /**
     * This method initializes the ORB for future CORBA communication.
     */
    protected void initORB() {
        Properties systProperties = System.getProperties();
        systProperties.putAll(properties);
        orb = ORB.init((String[]) null, systProperties);
    }

    /**
     * Open a new connexion
     * 
     * @param orb
     *            form {{@link #initialiseOnceCorba(Properties)}
     * @param server
     * @param defaultUsername
     * @param defaultPassword
     * @param serviceobjectversion
     */
    public void open(ORB orb, String server, String defaultUsername, String defaultPassword,
            String serviceobjectversion) {
        this.orb = orb;
        this.server = server;
        this.authUsername = defaultUsername;
        this.authPassword = defaultPassword;
        this.serviceobjectversion = serviceobjectversion;
        try {
            privateOpenV8();
        } catch (CMSException e) {
			throw new SOIException(e);
        }
    }

    /**
     * Re-initialization of the root accessor. Obtains new serviceAccessor.
     *
     * @throws SOIException
     *             when open fails
     */
    @Override
    public void reopen() {
        LOG.info("Reopen...");
        try {
            close();

            privateOpenV8();
        } catch (Exception e) {
            throw new SOIException("Creating or Loging to Root/Service Accessor", e);
        }
    }

    private void privateOpenV8() throws CMSException {
        LOG.debug("=> connect()");

        if (_serviceObject != null) {
            String msg = "This CMS client is already connected";
            LOG.error(msg);
            throw new CMSException(msg);
        }

        try {
            String cmsServerName = CMS_SERVER_NAME_PREFIX + server;
            LOG.info(cmsServerName);
            LOG.info(orb.get_default_context().context_name());
            ServiceRootI serviceRoot = ServiceRootIHelper.bind(orb, cmsServerName);

            NvElementI[] nvList = new NvElementI[0];
            _serviceFactory = serviceRoot.loginI(authUsername, authPassword, null, nvList);
            _serviceObject = _serviceFactory.getServiceI(properties.getProperty(SERVICE_OBJECT_VERSION_PROPERTY));

            RemoteParameterListLoader.instance().setServiceFactory(_serviceFactory);
            NVExchangeFormatFactory.instance().setORB(orb);
        } catch (LoginFailedExceptionI exception) {
            String msg = "CMS connection error";
            LOG.error(msg, exception);
            throw new CMSException(msg, exception);
        } catch (ServiceLayerExceptionI exception) {
            String msg = "CMS connection error" + exception.message;
            LOG.error(msg, exception);
            throw new CMSException(msg, exception);
        }
        // For Corba exceptions.
        catch (Exception exception) {
            String message = exception.getMessage();
            String msg = "CMS connection error" + message;
            // exception.printStackTrace();
            LOG.error(msg, exception);
            throw new CMSException(msg, exception);
        }

        LOG.info("Connection ok");
        LOG.debug("<= connect()");
    }

    /**
     * Call serviceAccessor.dispose()
     */
    @Override
    public void close() {
        LOG.debug("=> disconnect()");

        if (_serviceObject != null) {
            try {
                _serviceObject.disposeI();
            }
            // For Corba exceptions.
            catch (Exception exception) {
                // Nothing to do here.
                LOG.warn("Error disposing ServiceObject => ignored", exception);
            }

            _serviceObject = null;
        }

        if (_serviceFactory != null) {
            try {
                _serviceFactory.disposeI();
            }
            // For Corba exceptions.
            catch (Exception exception) {
                // Nothing to do here.
                LOG.warn("Error disposing ServiceFactory => ignored", exception);
            }

            _serviceFactory = null;
        }

        LOG.info("Disconnection ok");
        LOG.debug("<= disconnect()");
    }

    /**
     * Method to override for UnitTest without Corba
     *
     * @return corba adapter
     */
    protected CORBAAdapter getCORBAAdapter() {
        return CORBAAdapter.instance();
    }

    @Override
    public SVLObjectWrapper createSVLObject() {
        return AbstractSVLObjectFactory.createSVLObject();
    }

    @Override
    public SVLObjectListWrapper createSVLObjectList() {
        return AbstractSVLObjectFactory.createSVLObjectList();
    }

    /**
     * Implementation of ServiceLayerIntrospector
     * <p>
     * No trace
     */
    @Override
    public String[] getCommands(String componentName, String componentVersion) {
        try {
            return ServiceLayerIntrospector.getCommands(componentName);
        } catch (Exception e) {
            throw new SOIException("Delegating getCommands", e);
        }
    }

    /**
     * Implementation of ServiceLayerIntrospector
     * <p>
     * No trace
     */
    @Override
    public ParameterListWrapper getInputList(String command, String componentName, String componentVersion)
            throws APIException {
        try {
            return new ParameterListWrapperV8(ServiceLayerIntrospector.getInputList(command, componentName));
        } catch (ParameterListException e) {
            throw new APIException(e);
        }
    }

    /**
     * Implementation of ServiceLayerIntrospector
     * <p>
     * No Trace
     */
    @Override
    public ParameterListWrapper getOutputList(String command, String componentName, String componentVersion)
            throws APIException {
        try {
            return new ParameterListWrapperV8(ServiceLayerIntrospector.getOutputList(command, componentName));
        } catch (ParameterListException e) {
            throw new APIException(e);
        }
    }

    /**
     * call serviceAccessor.execute(...).
     * <p>
     * throws SOIException
     */
    @Override
    public SVLObjectWrapper execute(String command, SVLObjectWrapper input) {
        LOG.debug("=> executeCommand() : " + command);
        if (_serviceObject == null) {
            String msg = "CMS command execution error : " + command + ", CMS client not connected";
            LOG.error(msg);
        }

        SVLObjectWrapper outputParameters = null;

        try {
            // Convert the input parameters into a HashMap.
            if (input == null) {
                input = createSVLObject();
            }
            LOG.debug("Input parameters : " + input.toString());
            // Execute the CMS command.
            NvElementI[] inputNvElements = NVExchangeFormatFactory.instance().toNvList(
                    ((SVLObjectWrapperV8) input).getSVLObject(),
                    (ParameterList) getInputList(command, serviceobjectversion, null).getParameterList());
            NvElementListIHolder ouputParamsHolder = new NvElementListIHolder();
            ErrorInfoListIHolder errorsHolder = new ErrorInfoListIHolder();
            boolean isExecutionOk = _serviceObject.executeI(command, inputNvElements, ouputParamsHolder, errorsHolder);

            // If execution is nok.
            if (!isExecutionOk) {
                CMSErrorInfo[] cmsErrors = convertErrorInfoIArray(errorsHolder.value);
                String cmsErrorsStr = "";

                for (CMSErrorInfo errorInfo : cmsErrors) {
                    cmsErrorsStr += errorInfo.toString() + "\n";
                }

                String msg = "CMS command execution error : " + command;
                LOG.error(msg + "\n" + cmsErrorsStr);
                throw new CMSException(msg, cmsErrors);
            }

            if (ouputParamsHolder.value != null) {
                // Convert the execution result into a HashMap.
                outputParameters = new SVLObjectWrapperV8(
						NVExchangeFormatFactory.instance().fromNvList(ouputParamsHolder.value), timezoneOffset);
            }
        } catch (CMSException exception) {
            LOG.error(exception.getMessage());
			throw new SOIException(exception);
        }
        // For UnknownCommandExceptionI, ServiceLayerExceptionI exceptions, and
        // others Corba
        // exceptions.
        catch (Exception exception) {
            String msg = "CMS command execution error : " + command;
            LOG.error(msg, exception);
            throw new SOIException(msg, exception);
        }

        LOG.info("Command execution ok : command = " + command);
        LOG.debug("Output parameters = " + (outputParameters == null ? "null" : outputParameters.toString()));
        LOG.debug("<= executeCommand()");
        return outputParameters;
    }

    /**
     * This methods converts an array of ErrorInfo into an array of
     * CMSErrorInfo.
     *
     * @param errors
     *            The array of ErrorInfo instances to convert.
     * @return The array of CMSErrorInfo obtained.
     */
    private CMSErrorInfo[] convertErrorInfoIArray(ErrorInfoI[] errors) {
        CMSErrorInfo[] cmsErrors = null;

        if (errors != null) {
            cmsErrors = new CMSErrorInfo[errors.length];

            for (int nError = 0; nError < errors.length; nError++) {
                CMSErrorInfo cmsError = convertErrorInfoI(errors[nError]);
                cmsErrors[nError] = cmsError;
            }
        }

        return cmsErrors;
    }

    /**
     * This method converts an ErrorInfo into a CMSErrorInfo.
     *
     * @param error
     *            The ErrorInfo instance to convert.
     * @return The CMSErrorInfo obtained.
     */
    private CMSErrorInfo convertErrorInfoI(ErrorInfoI error) {
        CMSErrorInfo cmsError = null;

        if (error != null) {
            String[] errorArgs = error.errorArguments;
            String errorCode = "CMS." + error.errorCode;
            String additionalInfo = error.debugInfo;

            cmsError = new CMSErrorInfo(errorCode, errorArgs, additionalInfo);
        }

        return cmsError;
    }

    /**
     * Call serviceAccessor.commit()
     *
     * @throws SOIException
     *             when fails
     */
    @Override
    public void commit() {
        LOG.debug("=> commit()");

        if (_serviceObject == null) {
            String msg = "CMS commit error : CMS client not connected";
            LOG.error(msg);
        }

        execute("COMMIT", null);

        LOG.info("Commit ok");
        LOG.debug("<= commit()");
    }

    /**
     * Call serviceAccessor.rollback()
     *
     * @throws SOIException
     *             when fails
     */
    @Override
    public void rollback() {
        LOG.debug("=> rollback()");

        if (_serviceObject == null) {
            String msg = "CMS rollback error : CMS client not connected.";
            LOG.error(msg);
        }

        execute("ABORT", null);

        LOG.info("Rollback ok");
        LOG.debug("<= rollback()");
    }

    /**
     * This method indicate if the connection to the remote service accessor is
     * alive.
     * <p>
     * (Ask corbaAdapter if serviceAccessor is valid.
     *
     * @return true if this SOI connection is alive, otherwise false.
     */
    @Override
    public boolean isAlive() {
        LOG.trace("checking isAlive()");
        return !_serviceObject._non_existent();
    }

    /**
     * Return NULL, no source backend possible
     */
    @Override
    public IConnectionBackend getSourceBackend() {
        return null;
    }

    @Override
    public void doChangeSession(SOITransactionsManager soiTransactionsManager, SOIConnection soiConnection,
            String bscsBusinessUnit, String bscsLogin) {
        LOG.info("we don't trigger any change session on cms v8 version");
    }
}
