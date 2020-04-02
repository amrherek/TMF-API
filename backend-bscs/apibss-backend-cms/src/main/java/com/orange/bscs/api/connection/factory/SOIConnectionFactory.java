/*
 * BSCS-DAO
 * PoolableSOIConnectionFactory.java
 * Aug. 2010 - G. Defosse
 * Capgemini TMD
 */

package com.orange.bscs.api.connection.factory;

import java.util.Date;
import java.util.Observer;

import javax.annotation.PostConstruct;

import org.apache.commons.pool.PoolableObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.orange.bscs.api.connection.IConnectionBackend;
import com.orange.bscs.api.connection.IConnectionBackendFactory;
import com.orange.bscs.api.connection.ISOIConnectionProvider;
import com.orange.bscs.api.connection.SOIConnectionImpl;
import com.orange.bscs.api.model.exception.SOIException;

@Service
@Qualifier("soiConnectionFactory")
public class SOIConnectionFactory implements PoolableObjectFactory, ISOIConnectionProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(SOIConnectionFactory.class);

	@Autowired
    private IConnectionBackendFactory backendFactory;

	/**
	 * Ask BackendFactory used in chain if they are all well initialised.
	 * 
	 * <p>
	 * This bean is instantiate and all injections are done, so BackendFactory
	 * chain is in place.<br/>
	 * We can launch check or post-initialization in factories
	 * </p>
	 * <p>
	 * 
	 */
	@PostConstruct
	public void postInitialisation() {

		Assert.notNull(backendFactory);

		backendFactory.postInitialisation();
	}

    /**
     * Default constructor
     */
    public SOIConnectionFactory(){
    }


    /**
     * Constructor with required factory
     * 
     * @param backendFactory
     *            factory to create backend when creating connection
     */
    public SOIConnectionFactory(IConnectionBackendFactory backendFactory) {
        this.backendFactory=backendFactory;
    }


    /* ======================================================================
     *  			PoolableObjectFactory Implementations
     *  =====================================================================
     */
    /**
     * {@inheritDoc}
     * @throws SecurityException, UnknownComponentException, Exception 
     */
    @Override
    public Object makeObject() throws Exception {
        LOGGER.trace("makeObject : enter");
        SOIConnectionImpl soiConnection=null;

        try {
            IConnectionBackend backend = backendFactory.newInstance();

            // create a new SOI connection
            soiConnection = new SOIConnectionImpl(backend);
        }finally {
            if (null!=soiConnection){
                LOGGER.trace("makeObject : exit");
            }
        }
        return soiConnection;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void activateObject(Object obj) {
        SOIConnectionImpl soiConnection = (SOIConnectionImpl) obj;

        LOGGER.trace("Transaction TRACE {} is activate", soiConnection.getLogConnectionTrace());
        String tId = soiConnection.getTransactionId();
        LOGGER.trace("activateObject [Transaction ID : {}] : enter", tId);

        // preventative rollback
        try {
            LOGGER.trace("preventive rollback on [Transaction ID : {}]", tId);
            soiConnection.preventativeRollback();
            soiConnection.setStartRunning(new Date().getTime());
        } catch (SOIException e) {
            soiConnection.close();
            try {
                soiConnection.reopen();
            } catch (SOIException exception) {
                LOGGER.error("On the preventative rollback, a {} occurred with the following message : {}" ,
                        exception.getClass().getName(),exception.getMessage());
            }
            LOGGER.trace("-> preventive rollback on [Transaction ID : {}] failed !", tId);
        }

        LOGGER.trace("activateObject [Transaction ID : {}] : exit",tId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroyObject(Object obj) {
        SOIConnectionImpl soiConnection = (SOIConnectionImpl) obj;
        LOGGER.trace("Transaction TRACE  {} is destroy",soiConnection.getLogConnectionTrace());
        String tId = soiConnection.getTransactionId();
        LOGGER.trace("destroyObject [Transaction ID : {}] : enter",tId);
        LOGGER.info("Transaction {} is being destroyed!", tId);
        try {
            // close transaction when deleting
            soiConnection.close();
        } catch (Exception e) {
            // swallow
            LOGGER.error("[Transaction ID : {}] error during transaction destruction", tId,e);
        }
        LOGGER.info(" -> transaction {} destroyed.",tId);
        LOGGER.trace("destroyObject [Transaction ID : {}] : exit", tId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void passivateObject(Object obj) {
        SOIConnectionImpl soiConnection = (SOIConnectionImpl) obj;
        LOGGER.trace("Transaction TRACE {} is passivate",soiConnection.getLogConnectionTrace());
        String tId = soiConnection.getTransactionId();
        LOGGER.trace("passivateObject [Transaction ID : {}] : enter", tId);

        if (soiConnection.isMaxSessionChangeHaveBeenReached()) {
            destroyObject(soiConnection);
            LOGGER.info(" -> transaction {} has been destroyed because it have changed {} times of session", soiConnection.getTransactionId(),soiConnection.getNbSessionChange());
        }
        LOGGER.trace("passivateObject [Transaction ID : {}] : exit", tId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validateObject(Object obj) {
        SOIConnectionImpl soiConnection = (SOIConnectionImpl) obj;
        LOGGER.trace("Transaction TRACE {} is validate", soiConnection.getLogConnectionTrace());
        String tId = soiConnection.getTransactionId();
        LOGGER.trace("validateObject [Transaction ID : {}] : enter", tId);
        boolean validate = soiConnection.isAlive();
        LOGGER.trace("validateObject [Transaction ID : {}] : exit", tId);
        return validate;
    }


    /* =====================================================================
     * 					ISOIConnectionProvider implementation
     * =====================================================================
     */

    @Override
    public SOIConnectionImpl retreiveConnection() throws Exception{
        return(SOIConnectionImpl) makeObject(); 
    }

    @Override
    public void releaseConnection(SOIConnectionImpl connection) {
        connection.close();
    }

    @Override
    public void destroyConnection(SOIConnectionImpl connection) {
        destroyObject(connection);
    }


    @Override
    public void removeObserver(Observer obs) {
        // No connection in pool, not implement
    }


    @Override
    public void addObserver(Observer obs) {
        // No connection in pool, not implement
    }


    /* ====================================================================
     * 					ACCESSORS
     * ====================================================================
     */

    public void setConnectionBackendFactory(IConnectionBackendFactory factory){
        backendFactory=factory;
    }

}
