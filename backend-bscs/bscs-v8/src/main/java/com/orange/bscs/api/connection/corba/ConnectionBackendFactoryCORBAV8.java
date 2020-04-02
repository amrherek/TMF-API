package com.orange.bscs.api.connection.corba;

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.omg.CORBA.ORB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.orange.bscs.api.connection.IConnectionBackend;
import com.orange.bscs.api.connection.IConnectionBackendFactory;
import com.orange.bscs.api.model.AbstractSVLObjectFactory;
import com.orange.bscs.connection.corba.v8.ConnectionBackendCORBAV8;
import com.orange.bscs.model.factory.SVLObjectFactoryV8;

/**
 * V8 Corba backend factory
 * 
 * @author MFRH5781
 */
@Component
@Qualifier("corbaBackendFactory")
public class ConnectionBackendFactoryCORBAV8 implements IConnectionBackendFactory {

    private static final Logger LOG = LoggerFactory.getLogger(ConnectionBackendFactoryCORBAV8.class);

	@Value("${bscs.server}")
    private String server;

	@Value("${bscs.defaultUsername}")
    private String defaultUsername;

	@Value("${bscs.defaultPassword}")
    private String defaultPassword;

    @Value("${bscs.v8.serviceobjectversion}")
	private String serviceobjectversion;

    @Value("${bscs.v8.ORBagentAddr}")
    private String ORBagentAddr;

    @Value("${bscs.v8.ORBagentPort}")
    private String ORBagentPort;

	@Value("${bscs.v8.timezone.offset:0}")
	private int timezoneOffset;

	@Value("${bscs.v8.connectionMaxIdle:600}")
	private String connectionMaxIdle;

	@Value("${bscs.v8.corba.debug:false}")
	private boolean corbaDebug;

    private ORB orb = null;

    @PostConstruct
    public void postConstruct() {
        LOG.trace("initialize cms v8 : enter");
		AbstractSVLObjectFactory.init(new SVLObjectFactoryV8(timezoneOffset));
        LOG.trace("initialize : exit");
    }

    /**
     * Create new Backend and lazy initialise CORBA
     * <p>
     * throws SOIException
     */
    @Override
    public IConnectionBackend newInstance() {
        LOG.trace("Initiating new backend");
        ConnectionBackendCORBAV8 backend;
		backend = new ConnectionBackendCORBAV8(timezoneOffset);

        LOG.info("ConnectionBackendCORBA in version 8");

		// init orb once
		if (null == orb) {
			Properties properties = new Properties();

			properties.put(ConnectionBackendCORBAV8.SERVICE_OBJECT_VERSION_PROPERTY, serviceobjectversion);
			properties.put(ConnectionBackendCORBAV8.ORB_AGENT_ADDR_PROPERTY, ORBagentAddr);
			properties.put(ConnectionBackendCORBAV8.ORB_AGENT_PORT_PROPERTY, ORBagentPort);

			if (corbaDebug) {
				properties.put("vbroker.orb.logLevel", "debug");
			}
			// Specifies the time in secs that the client uses to determine if a
			// cached connection should be closed or not.
			properties.put("vbroker.ce.iiop.ccm.connectionMaxIdle", connectionMaxIdle);
            orb = backend.initialiseOnceCorba(properties);
        }

        backend.open(orb, server, defaultUsername, defaultPassword, serviceobjectversion);


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
        assert null != serviceobjectversion : "serviceobjectversion property must be define";
        assert null != ORBagentAddr : "ORBagentAddr property must be define";
        assert null != ORBagentPort : "ORBagentPort property must be define";
    }


}
