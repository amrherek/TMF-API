package com.orange.bscs.api.connection;


/**
 * Factory creating a IConnectionBackend.
 * 
 * Many implementation of this factory.
 * 
 * @author IT&Labs
 */
public interface IConnectionBackendFactory {

    /**
     * Factory method.
     * 
     * @throws com.orange.bscs.api.model.exception.SOIException
     */
    IConnectionBackend newInstance();


    /**
     * @param srcFactory a factory used to create sourceBackend for backend created by it.
     * 
     * Nota mock & corba factory ignore this call without error.
     */
    void setSourceBackendFactory(IConnectionBackendFactory srcFactory);


    /**
     * Used to check configuration or to realize some tasks.
     * 
     * This method should not be called directly by spring-framework but only by
     * SOITransactionManagers.
     * 
     * Called only once per factory if the factory is in the chain used to initialize a SOIConnection.
     * 
     */
    void postInitialisation();

}
