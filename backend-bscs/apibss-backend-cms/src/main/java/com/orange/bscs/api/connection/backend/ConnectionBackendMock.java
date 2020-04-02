package com.orange.bscs.api.connection.backend;

import java.util.LinkedList;
import java.util.Queue;

import com.orange.bscs.api.connection.IConnectionBackend;
import com.orange.bscs.api.exceptions.APIException;
import com.orange.bscs.api.model.AbstractSVLObjectFactory;
import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.model.ParameterListWrapper;
import com.orange.bscs.api.model.SVLObjectWrapper;
import com.orange.bscs.api.model.exception.SOIException;

public class ConnectionBackendMock extends ConnectionBackendBase {


    private static final String ERR_NOT_IMPLEMENTED = "NOT IMPLEMENTED {}";

    private Queue<BackendCommand> mockQueue = new LinkedList<BackendCommand>();

    /**
     * factory share response to the (only instance of Mock backend)
     */
    private ConnectionBackendFactoryMock myParent;

    private boolean hasNoSource;

    private boolean isActive;


    /**
     * Only used by factory
     *
     * @param factoryMock
     */
    protected ConnectionBackendMock(ConnectionBackendFactoryMock factoryMock) {
        super(null);
        myParent = factoryMock;
        hasNoSource = true;
        isActive = true;
    }


    // -----------------------------------------------------
    // 				Gestion des commandes
    // -----------------------------------------------------
    // Visibility : protected because delegate by factory

    public ConnectionBackendMock(ConnectionBackendFactoryMock factory,
                                 IConnectionBackend srcBackend) {
        super(srcBackend);
        hasNoSource = null == srcBackend;
        myParent = factory;
    }


    public void addCommand(BackendCommand cmd) {
        mockQueue.add(cmd);
    }

    public void clearCommands() {
        mockQueue.clear();
    }

    public void addCommand(String command, SVLObjectWrapper output) {
        addCommand(new BackendCommand(command, output));
    }

    public void addCommand(String command, BSCSModel output) {
        addCommand(new BackendCommand(command, output.getSVLObject()));
    }


    // -----------------------------------------------------
    // 				IConnectionBackend
    // -----------------------------------------------------
    // Visibility : protected because delegate by factory

    @Override
    public void close() {
        super.close();
        isActive = false;
    }

    @Override
    public void reopen() {
        super.reopen();
        isActive = true;
    }

    @Override
    public boolean isAlive() {
        return hasNoSource ? isActive : super.isAlive();
    }


    @Override
    public SVLObjectWrapper execute(String command, SVLObjectWrapper input) {
        if (!isAlive()) {
            throw new SOIException("MOCK!!! CONNECTION CLOSED !!! MOCK");
        }
        SVLObjectWrapper result;

        if (myParent.getCommandsToIgnore().contains(command)) {
            if (hasNoSource) {
                result = createSVLObject();
            } else {
                result = super.execute(command, input);
            }

            return result;
        }

        if (hasNoSource) {
            if (0 != mockQueue.size()) {
                BackendCommand res = mockQueue.poll();
                if (!res.getCommand().equalsIgnoreCase(command)) {
                    throw new SOIException(String.format("Actual command is : %s  but %s is expected", command, res.getCommand()));
                }
                result = res.getSVLOutput();
            } else {
                result = null;
            }
        } else {
            BackendCommand cmd = mockQueue.peek();
            if (null != cmd && cmd.getCommand().equalsIgnoreCase(command)) {
                // found, remove from queue
                cmd = mockQueue.poll();
                result = cmd.getSVLOutput();
            } else {
                // not in queue at the expected place
                result = super.execute(command, input);
            }
        }
        return result;
    }


    /**
     * Implementation of ServiceLayerIntrospector
     */
    @Override
    public String[] getCommands(String componentName, String componentVersion) {
        if (hasNoSource) {
            throw new SOIException("NOT IMPLEMENTED getCommands");
        } else {
            return super.getCommands(componentName, componentVersion);
        }
    }

    /**
     * Implementation of ServiceLayerIntrospector
     */
    @Override
    public ParameterListWrapper getInputList(String command, String componentName, String componentVersion)
            throws APIException {
        if (hasNoSource) {
            throw new APIException(AbstractSVLObjectFactory.getParameterListException(ERR_NOT_IMPLEMENTED, new Object[]{"getInputList"}));
        } else {
            return super.getInputList(command, componentName, componentVersion);
        }
    }

    /**
     * Implementation of ServiceLayerIntrospector
     */
    @Override
    public ParameterListWrapper getOutputList(String command, String componentName, String componentVersion)
            throws APIException {
        if (hasNoSource) {
            throw new APIException(AbstractSVLObjectFactory.getParameterListException(ERR_NOT_IMPLEMENTED, new Object[]{"getOutputList"}));
        } else {
            return super.getOutputList(command, componentName, componentVersion);
        }
    }


}
