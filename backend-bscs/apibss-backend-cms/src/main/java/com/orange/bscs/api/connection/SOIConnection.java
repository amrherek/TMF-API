package com.orange.bscs.api.connection;

import com.orange.bscs.api.model.BSCSModel;

/**
 * Public interface of SOIConnections.
 * 
 * Developer should have enough methods her to build services.
 * 
 * @author IT&Labs
 * 
 */
public interface SOIConnection {

    /**
     * Execute a Command CMS
     * 
     * @param commandName
     * @param input
     * @param outputClass
     * @return
     * 
     * @throws com.orange.bscs.api.model.exception.SOIException
     *             backend exception
     */
    <T extends BSCSModel> T execute(String commandName, BSCSModel input, Class<T> outputClass);

    /**
     * Execute a Command CMS
     * 
     * @param commandName
     * @param input
     * @param outputClass
     * @return
     * 
     * @throws com.orange.bscs.api.model.exception.SOIException
     *             backend exception
     */
    BSCSModel execute(String commandName, BSCSModel input);

    /**
     * Commit the transaction.
     * 
     * @throws com.orange.bscs.api.model.exception.SOIException
     *             backend exception
     * @throw com.orange.bscs.api.model.exception.APIException logging exception
     */
    void commit();

    /**
     * Roll back the transaction.
     */
    void rollback();

    /**
     * @return a unique identifier of this Connection
     */
    String getConnectionId();

    /**
     * @return an identifier of the Transaction between multiples WebServices
     *         calls in Statefull mode.
     */
    String getTransactionId();

    /**
     * @return the current BSCS Business Unit selected when opening/reactivating
     *         the connection
     */
    String getBusinessUnit();

    /**
     * Return direct IConnectionBackend of this SOIConnection.
     * <p>
     * Usage : Unit tests
     * </p>
     * 
     * @return first IConnectionBackend in Backend chain of this connection.
     * 
     */
    IConnectionBackend getBackend();

    /**
     * Search in IBackend source hierarchy the first backend implementing this
     * class. Used by Unit Tests to retrieve, for example,
     * IConnectionBackendFactoryFile instance.
     * 
     * @param clazz
     *            Type of IConnectionBackend to find.
     * @return first child backend implementing this class
     */
    <T extends IConnectionBackend> T getBackend(Class<T> clazz);
}
