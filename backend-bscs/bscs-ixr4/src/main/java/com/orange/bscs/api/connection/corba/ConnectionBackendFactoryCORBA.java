package com.orange.bscs.api.connection.corba;

import javax.annotation.PostConstruct;

import org.omg.CosNaming.NamingContextExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lhs.ccb.common.soi.ExchangeFormatFactory;
import com.lhs.ccb.common.soiimpl.DefaultSVLObjectFactory;
import com.lhs.ccb.func.ect.SystemException;
import com.orange.bscs.api.connection.IConnectionBackend;
import com.orange.bscs.api.connection.IConnectionBackendFactory;
import com.orange.bscs.api.model.AbstractSVLObjectFactory;
import com.orange.bscs.model.factory.SVLObjectFactoryIXR4;

/**
 * IXR4 Corba backend factory
 * 
 * @author MFRH5781 Note : modified by xbbs3851 on november 21st, 2016. Adding
 *         Spring IoC features
 */
@Component
@Qualifier("corbaBackendFactory")
public class ConnectionBackendFactoryCORBA implements IConnectionBackendFactory {

    private static final Logger LOG = LoggerFactory.getLogger(ConnectionBackendFactoryCORBA.class);

    private NamingContextExt namingContext;

	@Value("${bscs.v9.corbaNameService}")
    private String corbaNameService;

	@Value("${bscs.server}")
    private String server;

	@Value("${bscs.defaultUsername}")
    private String defaultUsername;

	@Value("${bscs.defaultPassword}")
    private String defaultPassword;

    @Value("${bscs.v9.defaultComponentName}")
    private String defaultComponentName;

    @Value("${bscs.v9.defaultComponentVersion}")
    private String defaultComponentVersion;

    /**
     * Default constructor for injection
     */
    public ConnectionBackendFactoryCORBA() {

    }

    /**
     * builds R3/R4 cms connection
     *
     * @param corbaNameService        ex corbaloc:iiop:server:port/NameService
     * @param server                  ex com/lhs/public/soi/fedfactory1
     * @param defaultUsername         ex DEMO
     * @param defaultPassword         ex DEMO
     * @param defaultComponentName    ex CIL
     * @param defaultComponentVersion ex 2
     */
	@Deprecated
    public ConnectionBackendFactoryCORBA(String corbaNameService, String server, String defaultUsername,
                                         String defaultPassword, String defaultComponentName, String defaultComponentVersion) {

        LOG.trace("initialize : enter");

        this.corbaNameService = corbaNameService;
        this.server = server;
        this.defaultUsername = defaultUsername;
        this.defaultPassword = defaultPassword;
        this.defaultComponentName = defaultComponentName;
        this.defaultComponentVersion = defaultComponentVersion;

		// Lazy initialisation, using first backend created
		// Oldcode : initialiseOnceCorba(corbaNameService)

		init();

		LOG.trace("initialize : exit");
	}

	/**
	 * Initialize
	 */
	@PostConstruct
	public void init() {
		LOG.info("Initializing IXR4 ConnectionBackendFactoryCORBA");

        try {
            ExchangeFormatFactory.instance();
        } catch (SystemException se) {
            ExchangeFormatFactory.init(new DefaultSVLObjectFactory());
        }

        AbstractSVLObjectFactory.init(new SVLObjectFactoryIXR4());
		LOG.info("Initializing IXR4 ConnectionBackendFactoryCORBA : DONE");
    }


    /**
     * Create new Backend and lazy initialise CORBA
     * <p>
     * throws SOIException
     */
    @Override
    public IConnectionBackend newInstance() {
        LOG.trace("Initiating new backend");

		ConnectionBackendCORBA backend = new ConnectionBackendCORBA();
        if (null == namingContext) {
			namingContext = backend.initialiseOnceCorba(corbaNameService);
        }

		backend.open(namingContext, server, defaultUsername, defaultPassword,
                defaultComponentName, defaultComponentVersion);

        LOG.trace("backend initiated and opened");
        return backend;
    }

    /**
     * Don't accept source factory, do nothing and don't throw exception.
     */
    @Override
    public void setSourceBackendFactory(IConnectionBackendFactory srcFactory) {
        // don't accepte source factory
    }

    @Override
    public void postInitialisation() {

        assert null != server : "server property must be define";
        assert null != defaultUsername && 0 < defaultUsername.length()
                && !defaultUsername.startsWith("${") : "bscs.defaultUsername not correctly initialized";
        assert null != defaultPassword && 0 < defaultPassword.length()
                && !defaultPassword.startsWith("${") : "bscs.defaultPassword not correctly initialized";
        assert null != corbaNameService && corbaNameService.startsWith(
                "corbaloc:iiop:") : "ORBInitRef.NameService property must be define and start with 'corbaloc:iiop:'";
        assert null != server && server
                .startsWith("com/lhs/") : "bscs.defaultServer property must be define and start with 'com/lhs/";
        assert null != defaultComponentName && 0 < defaultComponentName.length()
                && !defaultComponentName.startsWith("${") : "bscs.defaultComponentName not correctly initialized";
        assert null != defaultComponentVersion && 0 < defaultComponentVersion.length()
                && !defaultComponentVersion.startsWith("${") : "bscs.defaultComponentVersion not correctly initialized";
    }

}
