package com.orange.bscs.api.command;

import com.orange.bscs.api.model.BSCSModel;

/**
 * Base Class describing a command with a Verb, input parameters and result.
 * 
 * This class only store datas, it doesn't have method to execute statement.
 * 
 * @author IT&Labs
 * 
 * @param <TINPUT>
 *            Parameters as BSCSModel or sub-class
 * @param <TOUTPUT>
 *            Result BSCSModel type (may be not null when command is executed)
 */
public abstract class BaseCommand<TINPUT extends BSCSModel, TOUTPUT extends BSCSModel> implements ICommand<TINPUT, TOUTPUT> {

    private TINPUT input;
    private TOUTPUT output;

    /**
     * Verb / Command Name. eg : CONTRACT.READ
     */
    public abstract String getCommand();

    /**
     * Parameters passed to CORBA server
     */
    public TINPUT getInput() {
        return input;
    }

    /**
     * set parameters to send to CORBA server
     */
    public void setInput(TINPUT input) {
        this.input = input;
    }

    /**
     * should be the response
     */
    public TOUTPUT getOutput() {
        return output;
    }

    /**
     * Store response in this objet
     * 
     * @param out
     *            response from CORBA serveur
     */
    public void setOutput(TOUTPUT out) {
        this.output = out;
    }

}
