package com.orange.bscs.api.connection.backend;


import com.lhs.ccb.common.soi.SVLObject;

public class ParsedCommand {

    private String command;
    private SVLObject input;
    private SVLObject output;

    public String toString() {
        return command + "(" + input + ") => {" + output + "}";
    }


    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public SVLObject getInput() {
        return input;
    }

    public void setInput(SVLObject input) {
        this.input = input;
    }

    public SVLObject getOutput() {
        return output;
    }

    public void setOutput(SVLObject output) {
        this.output = output;
    }

}
