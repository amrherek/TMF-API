package com.orange.bscs.api.connection.backend;

import java.util.ArrayList;
import java.util.List;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.model.SVLObjectWrapper;

/**
 * This backend can be used as the last backend in the chain,
 * serving commands in queue or doing nothing if queue is empty.
 * <p>
 * v2: this backend can be used in the middle of the chain, serving commands
 * if it contains such command in queue and delegate execution to source backend if queue
 * doesn't contains such command.
 * <p>
 * Usefull when we want to differentiate executions (CONTRACT.NEW) for example.
 *
 * @author IT&Labs
 */
public class ConnectionBackendFactoryMock extends ConnectionBackendFactoryBase<ConnectionBackendMock> {

    private List<String> commandsToIgnore;

    private ConnectionBackendMock instance;

    public ConnectionBackendFactoryMock() {
        commandsToIgnore = new ArrayList<String>();
        commandsToIgnore.add("SESSION.CHANGE");
    }


    /**
     * only shared with ConnectionBackendMock
     *
     * @return
     */
    protected List<String> getCommandsToIgnore() {
        return commandsToIgnore;
    }


    @Override
    public ConnectionBackendMock newInstance() {
        if (null != instance) {
            instance.reopen();
            return instance;
        }

        ConnectionBackendMock backend;
        if (null == getSourceBackendFactory()) {
            backend = new ConnectionBackendMock(this);
        } else {
            backend = new ConnectionBackendMock(this, getSourceBackendFactory().newInstance());
        }
        return backend;
    }


    // Gestion des commandes
    public void addCommand(String commandName, SVLObjectWrapper output) {
        if (null == instance) {
            instance = newInstance();
        }
        instance.addCommand(new BackendCommand(commandName, output));
    }

    public void addCommand(String commandName, BSCSModel output) {
        if (null == instance) {
            instance = newInstance();
        }
        instance.addCommand(new BackendCommand(commandName, output));
    }

    public void clearCommands() {
        if (null == instance) {
            instance = newInstance();
        }
        instance.clearCommands();
    }

}
