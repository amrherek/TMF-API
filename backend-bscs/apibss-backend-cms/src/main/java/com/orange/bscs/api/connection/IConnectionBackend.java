package com.orange.bscs.api.connection;

import com.orange.bscs.api.exceptions.APIException;
import com.orange.bscs.api.model.ParameterListWrapper;
import com.orange.bscs.api.model.SVLObjectListWrapper;
import com.orange.bscs.api.model.SVLObjectWrapper;

/**
 * Source of SOIConnection
 * 
 * @author IT&Labs
 * 
 */
public interface IConnectionBackend {

    /**
     * Close connection.
     */
    void close();

    /**
     * Ensure connection will be active. Backend should Close and Open channel
     * if necessary.
     * 
     * @throws com.orange.bscs.api.model.exception.SOIException
     */
    void reopen();

    /**
     * 
     * @return status of Channel. Should be alive when connection is create
     */
    boolean isAlive();

    /**
     * Commit changes
     * 
     * @throws com.orange.bscs.api.model.exception.SOIException
     */
    void commit();

    /**
     * Ignore changes.
     * 
     * @throws com.orange.bscs.api.model.exception.SOIException
     */
    void rollback();

    /**
     * Execute a CMS Command
     * 
     * @throws com.orange.bscs.api.model.exception.SOIException
     */
    SVLObjectWrapper execute(String command, SVLObjectWrapper input);

    /**
     * Create a SVLObject
     * 
     * @return new and Empty SVLObject
     */
    SVLObjectWrapper createSVLObject();

    /**
     * Create a SVLObject List
     * 
     * @return new and Empty list.
     */
    SVLObjectListWrapper createSVLObjectList();

    /**
     * @return the source backend OR null.
     */
    IConnectionBackend getSourceBackend();

    /**
     * Implement ServiceLayerIntrospector
     * 
     * @param componentName
     * @param componentVersion
     * @return list of commands served by this component
     * 
     * @Throws SOIException
     */
    String[] getCommands(String componentName, String componentVersion);

    /**
     * implements ServiceLayerIntrospector
     * 
     * @param command
     * @param defaultComponentName
     * @param defaultComponentVersion
     * @return Description of input
     * @throws APIException
     */
    ParameterListWrapper getInputList(String command, String defaultComponentName, String defaultComponentVersion)
            throws APIException;

    /**
     * implements ServiceLayerIntrospector
     * 
     * @param command
     * @param componentName
     * @param componentVersion
     * @return Description of output
     * @throws APIException
     */
    ParameterListWrapper getOutputList(String command, String componentName, String componentVersion) throws APIException;

    /**
     * Reinitialise la session corba (doit être fait entre deux utilisation)
     * 
     * @param soiTransactionsManager
     * @param soiConnection
     * @param bscsBusinessUnit
     * @param bscsLogin
     */
    void doChangeSession(SOITransactionsManager soiTransactionsManager, SOIConnection soiConnection, String bscsBusinessUnit, String bscsLogin);
}
