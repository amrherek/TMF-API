package com.orange.bscs.api.connection.backend;

import java.util.LinkedList;
import java.util.Queue;

import com.lhs.ccb.common.soi.ParameterListException;
import com.orange.bscs.api.connection.IConnectionBackend;
import com.orange.bscs.api.connection.SOIConnection;
import com.orange.bscs.api.connection.SOITransactionsManager;
import com.orange.bscs.api.exceptions.APIException;
import com.orange.bscs.api.model.AbstractSVLObjectFactory;
import com.orange.bscs.api.model.ParameterListWrapper;
import com.orange.bscs.api.model.SVLObjectListWrapper;
import com.orange.bscs.api.model.SVLObjectWrapper;
import com.orange.bscs.api.model.exception.SOIException;

public class ConnectionBackendMockv1 implements IConnectionBackend {


    private static final String ERR_NOT_IMPLEMENTED = "NOT IMPLEMENTED {}";

    private Queue<BackendCommand> mockQueue = new LinkedList<BackendCommand>();

    /**
     * factory share response to the (only instance of Mock backend)
     */
    private ConnectionBackendFactoryMockv1 myParent;

    private boolean isActive;


    /**
     * Only used by factory
     *
     * @param factoryMock
     */
    protected ConnectionBackendMockv1(ConnectionBackendFactoryMockv1 factoryMock) {
        myParent = factoryMock;
        isActive = true;
    }


    // -----------------------------------------------------
    // 				Gestion des commandes
    // -----------------------------------------------------
    // Visibility : protected because delegate by factory

    protected void addCommand(BackendCommand cmd) {
        mockQueue.add(cmd);
    }

    protected void clearCommands() {
        mockQueue.clear();
    }


    // -----------------------------------------------------
    // 				IConnectionBackend
    // -----------------------------------------------------
    // Visibility : protected because delegate by factory

    @Override
    public void close() {
        isActive = false;
    }

    @Override
    public void reopen() {
        isActive = true;
    }

    @Override
    public boolean isAlive() {
        return isActive;
    }

    @Override
    public void commit() {
        // Nothing to do
    }

    @Override
    public void rollback() {
        // Nothing to do
    }

    @Override
    public SVLObjectWrapper execute(String command, SVLObjectWrapper input) {
        if (!isActive) {
            throw new SOIException("MOCK!!! CONNECTION CLOSED !!! MOCK");
        }
        SVLObjectWrapper result = null;

        if (myParent.getCommandsToIgnore().contains(command)) {
            return createSVLObject();
        }

        if (!mockQueue.isEmpty()) {
            BackendCommand res = mockQueue.poll();

            if (!res.getCommand().equalsIgnoreCase(command)) {
                throw new SOIException(String.format("Actual command is : %s  but %s is expected", command, res.getCommand()));
            }

            result = res.getSVLOutput();
        }
        return result;
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
     */
    @Override
    public String[] getCommands(String componentName, String componentVersion) {
        throw new SOIException("NOT IMPLEMENTED getCommands");
    }

    /**
     * Implementation of ServiceLayerIntrospector
     */
    @Override
    public ParameterListWrapper getInputList(String command, String componentName, String componentVersion)
            throws APIException {
        throw new APIException(new ParameterListException(ERR_NOT_IMPLEMENTED, new Object[]{"getInputList"}));
    }

    /**
     * Implementation of ServiceLayerIntrospector
     */
    @Override
    public ParameterListWrapper getOutputList(String command, String componentName, String componentVersion)
            throws APIException {
        throw new APIException(new ParameterListException(ERR_NOT_IMPLEMENTED, new Object[]{"getOutputList"}));
    }


    /**
     * Return NULL
     */
    @Override
    public IConnectionBackend getSourceBackend() {
        return null;
    }

    @Override
    public void doChangeSession(SOITransactionsManager soiTransactionsManager, SOIConnection soiConnection, String bscsBusinessUnit, String bscsLogin) {
        // nothing to do
    }
}
