package com.orange.bscs.api.connection.backend;

import com.orange.bscs.api.command.BaseCommand;
import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.model.SVLObjectWrapper;

/**
 * Extends BaseCommand and allow to specify CommandName in contructor.
 * <p>
 * used by ConnectionBackendFactoryRecordCmdNames and ConnectionBackendFactoryMock
 */
public class BackendCommand extends BaseCommand<BSCSModel, BSCSModel> {

    private static final String CRLF = System.getProperty("line.separator");

    private String command;

    public BackendCommand(String command) {
        this.command = command;
    }

    public BackendCommand(String command, SVLObjectWrapper out) {
        this.command = command;
        setOutput(newBSCSModelOrNull(out));
    }

    public BackendCommand(String commandName, BSCSModel output) {
        command = commandName;
        setOutput(output);
    }

    public BackendCommand(String command, SVLObjectWrapper in, SVLObjectWrapper out) {
        this.command = command;
        setInput(newBSCSModelOrNull(in));
        setOutput(newBSCSModelOrNull(out));
    }

    /**
     * @param out
     * @return
     */
    private BSCSModel newBSCSModelOrNull(SVLObjectWrapper out) {
        return null == out ? null : new BSCSModel(out);
    }


    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(command).append('@').append(this.hashCode());

        BSCSModel input = getInput();
        BSCSModel output = getOutput();
        if (null == input || input.getSVLObject().isEmpty()) {
            sb.append("()");
        } else {
            sb.append(" {").append(CRLF).append(input.getSVLObject().toString()).append('}');
        }
        if (null == output || output.getSVLObject().isEmpty()) {
            sb.append(" => {}");
        } else {
            sb.append(" => {").append(CRLF).append(output.getSVLObject().toString()).append('}');
        }
        return sb.toString();
    }


    public SVLObjectWrapper getSVLOutput() {
        BSCSModel output = getOutput();
        SVLObjectWrapper result = null;
        if (null != output) {
            result = output.getSVLObject();
        }
        return result;
    }

}
