package com.orange.bscs.api.command;

import com.orange.bscs.api.model.BSCSModel;

/**
 * Common interface for all commands
 * 
 * @author IT&Labs
 * 
 * @param <TINPUT>
 *            any classes extending BSCSModel and holding input parameters
 * @param <TOUTPUT>
 *            any classes extending BSCSModel and holding response of CMS
 *            Command.
 */
public interface ICommand<TINPUT extends BSCSModel, TOUTPUT extends BSCSModel> {

    /**
     * @return CommandCMS / Verb
     */
    String getCommand();

    /**
     * @return should be parameters associated/send with the CommandsCMS
     */
    TINPUT getInput();

    /**
     * @param input
     *            the parameters to send
     */
    void setInput(TINPUT input);

    /**
     * @return the result of the CMS command.
     */
    TOUTPUT getOutput();
}
