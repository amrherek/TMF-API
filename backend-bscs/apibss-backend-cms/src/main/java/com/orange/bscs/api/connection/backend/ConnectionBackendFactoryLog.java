package com.orange.bscs.api.connection.backend;

import java.util.ArrayList;
import java.util.List;

import com.orange.bscs.api.connection.IConnectionBackend;
import com.orange.bscs.api.connection.IConnectionBackendFactory;

/**
 * LOG output of CMS execution.
 * 	INFO : log only Command names
 *  DEBUG : Log Command and input parameters
 *  TRACE : Log Command, inputs and outputs.
 * 
 * LoggerName is :"com.orange.bscs.api.connection.CMS"
 * 
 * @author IT&Labs
 */
public class ConnectionBackendFactoryLog extends ConnectionBackendFactoryBase<ConnectionBackendLog> {

    // Factory 
    private static List<String> commandsToSkip = new ArrayList<String>();
    private static List<String> commandsToSkipInput = new ArrayList<String>();
    private static List<String> commandsToSkipOutput = new ArrayList<String>();

    static {
        commandsToSkip.add("SESSION.CHANGE");

        commandsToSkipOutput.add("SERVICES.READ");
        commandsToSkipOutput.add("SUB_MARKETS.READ");
        commandsToSkipOutput.add("SERVICE_PACKAGES.READ");
        commandsToSkipOutput.add("SERVICE_PARAMETERS.READ");
    }


    public ConnectionBackendFactoryLog() { 
        // Empty constructor
    }

    public ConnectionBackendFactoryLog(IConnectionBackendFactory srcFactory) {
        super(srcFactory);
    }




    @Override
    public ConnectionBackendLog newInstance() {
        long start=System.currentTimeMillis();
        IConnectionBackend source = getSourceBackendFactory().newInstance();
        ConnectionBackendLog result=new ConnectionBackendLog(source);
        result.logExecuteSuccess("NEW", null, null, start);
        return result;
    }


    /**
     * @return List of commands we wish to hide Input parameters (Empty list by default)
     */
    public static List<String> getCommandsToSkipInput() {
        return commandsToSkipInput;
    }
    /**
     * @param commandsToSkipInput list of commands we don't want to display input parameters
     *  even when LogLevel="DEBUG" or TRACE
     */
    public static void setCommandsToSkipInput(List<String> commandsToSkipInput) {
        ConnectionBackendFactoryLog.commandsToSkipInput = commandsToSkipInput;
    }

    /**
     * @return List of commands we don't want to log results (long list by example) even when
     * LogLevel = "TRACE"
     * 
     */
    public static List<String> getCommandsToSkipOutput() {
        return commandsToSkipOutput;
    }

    /**
     * @param commandsToSkipOutput List of commands we don't want to log results (long list by example) even when
     * LogLevel = "TRACE"
     */
    public static void setCommandsToSkipOutput(List<String> commandsToSkipOutput) {
        ConnectionBackendFactoryLog.commandsToSkipOutput = commandsToSkipOutput;
    }

    /**
     * @return List of commands to Hide (no trace of execution) : SESSION.CHANGE by default
     */
    public static List<String> getCommandsToSkip() {
        return commandsToSkip;
    }

    /**
     * @param commandsToSkip List of commands to Hide (no trace of execution)
     */
    public static void setCommandsToSkip(List<String> commandsToSkip) {
        ConnectionBackendFactoryLog.commandsToSkip = commandsToSkip;
    }

}
