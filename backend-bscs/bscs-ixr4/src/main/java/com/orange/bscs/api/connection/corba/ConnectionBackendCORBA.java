package com.orange.bscs.api.connection.corba;

import java.util.Locale;
import java.util.Properties;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lhs.ccb.common.soi.AccessorFactory;
import com.lhs.ccb.common.soi.ExchangeFormatFactory;
import com.lhs.ccb.common.soi.ParameterListException;
import com.lhs.ccb.common.soi.RootAccessor;
import com.lhs.ccb.common.soi.SVLObject;
import com.lhs.ccb.common.soi.ServiceAccessor;
import com.lhs.ccb.common.soi.ServiceLayerIntrospector;
import com.lhs.ccb.common.soiimpl.CORBAAdapter;
import com.lhs.ccb.common.soiimpl.ClientCallBackImpl;
import com.lhs.ccb.common.soiimpl.RemoteParameterListLoader;
import com.lhs.ccb.func.ect.ComponentException;
import com.lhs.ccb.func.ect.SystemException;
import com.lhs.ccb.soi.ClientCallbackI;
import com.orange.bscs.api.connection.IConnectionBackend;
import com.orange.bscs.api.connection.SOIConnection;
import com.orange.bscs.api.connection.SOIConnectionImpl;
import com.orange.bscs.api.connection.SOITransactionsManager;
import com.orange.bscs.api.exceptions.APIException;
import com.orange.bscs.api.model.AbstractSVLObjectFactory;
import com.orange.bscs.api.model.ParameterListWrapper;
import com.orange.bscs.api.model.SVLObjectListWrapper;
import com.orange.bscs.api.model.SVLObjectWrapper;
import com.orange.bscs.api.model.exception.CMSErrorInfo;
import com.orange.bscs.api.model.exception.CMSException;
import com.orange.bscs.api.model.exception.SOIException;
import com.orange.bscs.model.wrapper.ParameterListWrapperIXR4;
import com.orange.bscs.model.wrapper.SVLObjectWrapperIXR4;


/**
 * Group all CORBA commands in one class.
 *
 * @author IT&Labs
 */
public class ConnectionBackendCORBA implements IConnectionBackend {

    private static final Logger LOG = LoggerFactory.getLogger(ConnectionBackendCORBA.class);

    private static final String NAME_SERVICE = "NameService";

    private RootAccessor rootAccessor;
    private AccessorFactory accessorFactory;
    private ServiceAccessor serviceAccessor;


    private NamingContextExt namingCtx;
    private String server;
    private String authUsername;
    private String authPassword;
    private String componentName;
    private String componentVersion;


    private int nbRootAccessor;
    private int nbAccessorFactory;
    private int nbServiceAccessor;

    public ConnectionBackendCORBA() {
        // Class should declare an default constructor, comment in empty method
    }


    /**
     * Initialise CORBA and create a NamingContext.
     *
     * @param corbaNameService
     * @return
     * @throws SOIException
     *             A RuntimeException throw if initialisation fail
     */
    public NamingContextExt initialiseOnceCorba(String corbaNameService) {

        NamingContextExt namingContext = null;

        ServiceLayerIntrospector.setParameterListLoader(new RemoteParameterListLoader());

        Properties properties = new Properties();
        properties.put("ORBInitRef." + NAME_SERVICE, corbaNameService);
        properties.put("org.omg.CORBA.ORBInitRef", NAME_SERVICE + "=" + corbaNameService);

        ORB orb = null;
        orb = CORBAAdapter.instance().initORB(new String[]{}, properties);
        try {
            namingContext = NamingContextExtHelper.narrow(orb.resolve_initial_references(NAME_SERVICE));
        } catch (Exception e) {
            // May be org.omg.CORBA.SystemException, InvalidName, IllegalArgumentException, ... 
            throw new SOIException(String.format("Failed to retrieve naming service '%s'. ", corbaNameService), e);
        }

        return namingContext;
    }

    /**
     * Create root and serviceAccessor.
     *
     * @param namingContext           cf initialiseOnceCorba() for nameContext initialisation
     * @param server
     * @param defaultUsername
     * @param defaultPassword
     * @param defaultComponentName    CIL
     * @param defaultComponentVersion 2
     * @throws SOIException
     */
    public void open(
            NamingContextExt namingContext,
            String server,
            String defaultUsername,
            String defaultPassword,
            String defaultComponentName,
            String defaultComponentVersion) {
        namingCtx = namingContext;
        this.server = server;
        authUsername = defaultUsername;
        authPassword = defaultPassword;
        componentName = defaultComponentName;
        componentVersion = defaultComponentVersion;

        privateOpen();
    }


    /**
     * Re-initialization of the root accessor.
     * Obtains new serviceAccessor.
     *
     * @throws SOIException
     */
    @Override
	public void reopen() {
        LOG.info("Reopen...");
        try {
            close();

            privateOpen();
        } catch (Exception e) {
            throw new SOIException("Creating or Loging to Root/Service Accessor", e);
        }
    }


    private void privateOpen() {
        LOG.info("Opening (namingContext={},server={}," +
                        "defaultUsername={},defaultPassword={}," +
                        "defaultComponentName={},defaultComponentVersion={})",
                new Object[]{namingCtx.toString(), server, authUsername, authPassword, componentName, componentVersion});

        try {
            rootAccessor = createRootAccessor();
            ClientCallbackI clientCallback = getNewClientCallback();
            accessorFactory = rootAccessor.loginWithCallBack(authUsername, authPassword, null, clientCallback);
            nbAccessorFactory++;
            serviceAccessor = accessorFactory.getServiceAccessor(componentName, componentVersion);
            nbServiceAccessor++;
            LOG.info("Opened, nbRoot={}, nbAccessor: {}, nbService: {}", nbRootAccessor, nbAccessorFactory, nbServiceAccessor);
        } catch (Exception e) {
            throw new SOIException("Creating or Loging to Root/Service Accessor", e);
        }
    }


    /**
     * Call serviceAccessor.dispose()
     */
    @Override
    public void close() {
        if (null != serviceAccessor && getCORBAAdapter().isObjectAlive(serviceAccessor.getCorbaObject())) {
            LOG.debug("serviceAccessor.dispose()");
            serviceAccessor.dispose();
            nbServiceAccessor--;
        }
        if (null != accessorFactory && getCORBAAdapter().isObjectAlive(accessorFactory.getCorbaObject())) {
            LOG.debug("accessorFactory.dispose()");
            accessorFactory.dispose();
            nbAccessorFactory--;
        }
    }


    /**
     * Method to override for UnitTest without Corba
     *
     * @return
     */
    protected CORBAAdapter getCORBAAdapter() {
        return CORBAAdapter.instance();
    }


    /**
     * Method to override for UnitTest without Corba
     *
     * @return
     */
    protected ClientCallbackI getNewClientCallback() {
        return new ClientCallBackImpl().getCorbaObject();
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
            return ServiceLayerIntrospector.getCommands(componentName, componentVersion);
        } catch (ComponentException e) {
            throw new SOIException("Delegating getCommands", createCMSException(e));
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
            return new ParameterListWrapperIXR4(ServiceLayerIntrospector.getInputList(command, componentName, componentVersion));
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
            return new ParameterListWrapperIXR4(ServiceLayerIntrospector.getOutputList(command, componentName, componentVersion));
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
        Locale locale = Locale.getDefault();
        SVLObjectWrapper output = null;
        try {
            output = new SVLObjectWrapperIXR4(serviceAccessor.execute(locale, command, getSVLObject(input)));
        } catch (ComponentException e) {
			throw new SOIException(createCMSException(e));
        } catch (SystemException e) {
            throw new SOIException(e.getErrorCode(), e);
        }
        return output;
    }

    private SVLObject getSVLObject(SVLObjectWrapper input) {
        return null == input ? ExchangeFormatFactory.instance().createSVLObject() : ((SVLObjectWrapperIXR4)input).getSVLObject();
    }

    /**
     * Call serviceAccessor.commit()
     *
     * @throws SOIException
     */
    @Override
	public void commit() {
        try {
            serviceAccessor.commit();
        } catch (ComponentException e) {
			throw new SOIException(createCMSException(e));
        } catch (SystemException e) {
            throw new SOIException(e.getErrorCode(), e);
        } catch (Exception e) {
            throw new SOIException("delegating commit", e);
        }
    }

    private CMSException createCMSException(ComponentException e) {
    	CMSErrorInfo[] errors = new CMSErrorInfo[1];
		CMSErrorInfo error = new CMSErrorInfo(e.getErrorCode(), e.getArguments(), null);
    	errors[0] = error;
		return new CMSException(e.getMessage(), e, errors);
	}



	/**
     * Call serviceAccessor.rollback()
     *
     * @throws SOIException
     */
    @Override
	public void rollback() {
        try {
            serviceAccessor.rollback();
        } catch (ComponentException e) {
			throw new SOIException(createCMSException(e));
        } catch (SystemException e) {
            throw new SOIException(e.getErrorCode(), e);
        } catch (Exception e) {
            throw new SOIException("delegating rollback", e);
        }
    }

    /**
     * This method indicate if the connection to the remote service accessor
     * is alive.
     * <p>
     * (Ask corbaAdapter if serviceAccessor is valid.
     *
     * @return true if this SOI connection is alive, otherwise false.
     */
    @Override
	public boolean isAlive() {
        LOG.trace("checking isAlive()");
        return getCORBAAdapter().isObjectAlive(serviceAccessor.getCorbaObject());
    }


    /**
     * Creating a new RootAccessor.
     *
     * @return a new root accessor.
     * @throws ComponentException
     * @throws Exception          if raised, it's either a failed when retrieve the
     *                            server or to create the root accessor.
     */
    private RootAccessor createRootAccessor() throws ComponentException {
        RootAccessor root = null;
        org.omg.CORBA.Object corbaObject = null;

        corbaObject = getCORBAAdapter().findCorbaObject(namingCtx, server);

        LOG.info("found server {}", corbaObject);

        root = ExchangeFormatFactory.instance().createRootAccessor(corbaObject);

        LOG.info("created rootAccessor[{}] from server : {}", ++nbRootAccessor, server);
        return root;
    }


    /**
     * Return NULL, no source backend possible
     */
    @Override
    public IConnectionBackend getSourceBackend() {
        return null;
    }

    @Override
    public void doChangeSession(SOITransactionsManager soiTransactionsManager, SOIConnection soiConnection, String bscsBusinessUnit, String bscsLogin) {
        boolean bChangedSession = false;
        try {
                    /* perform a SESSION.CHANGE SOI command */
            ((SOIConnectionImpl)soiConnection).doChangeSession(bscsBusinessUnit, bscsLogin);
            bChangedSession = true;

        } finally {
            if (!bChangedSession) {
                // an error occurred during session change => clear the
                // transaction
                LOG.error(String.format("[Transaction ID : %s] could not perform the Session Change command => "
                        + "delete transaction", soiConnection.getTransactionId()));
                soiTransactionsManager.deleteSOIConnection(soiConnection);
            }
        }

    }
}
