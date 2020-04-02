package com.orange.bscs.api.connection.backend;

import java.util.ArrayList;
import java.util.List;

import com.lhs.ccb.common.soi.ExchangeFormatFactory;
import com.lhs.ccb.common.soiimpl.DefaultSVLObjectFactory;
import com.lhs.ccb.func.ect.SystemException;
import com.orange.bscs.api.connection.IConnectionBackendFactory;
import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.model.SVLObjectWrapper;

public class ConnectionBackendFactoryMockv1 implements IConnectionBackendFactory{

    private List<String> commandsToIgnore;

    private ConnectionBackendMockv1 instance;

    static {
        try {
            ExchangeFormatFactory.instance();
        } catch (SystemException se) {
            ExchangeFormatFactory.init(new DefaultSVLObjectFactory());
        }
    }


    public ConnectionBackendFactoryMockv1() {
        commandsToIgnore = new ArrayList<String>();
        commandsToIgnore.add("SESSION.CHANGE");

        instance = new ConnectionBackendMockv1(this);
    }

    @Override
    public synchronized ConnectionBackendMockv1 newInstance() {
        instance.reopen();
        return instance;
    }

    // Gestion des commandes
    public void addCommand(String commandName, SVLObjectWrapper output) {
        instance.addCommand(new BackendCommand(commandName, output));
    }

    public void addCommand(String commandName, BSCSModel model) {
        instance.addCommand(new BackendCommand(commandName, model));
    }

    public void clearCommands() {
        instance.clearCommands();
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
        // Nothing to check
    }


    /**
     *  only shared with ConnectionBackendMock
     *  
     * @return
     */
    protected List<String> getCommandsToIgnore() {
        return commandsToIgnore;
    }

}
