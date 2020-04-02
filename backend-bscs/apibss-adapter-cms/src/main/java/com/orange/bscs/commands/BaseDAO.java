package com.orange.bscs.commands;

import static com.orange.bscs.api.utils.config.Constants.P_CO_ID_PUB;

import com.orange.bscs.api.connection.ConnectionHolder;
import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.model.exception.SOIException;
import com.orange.bscs.model.contract.BSCSContract;

/**
 * Utility with commands to execute CMS
 *
 * @author IT&Labs
 * @version $Id: $
 */
public abstract class BaseDAO {
    
    public static final String ERR_RC2000 = "%s: The mandatory parameter %s is missing";
    
    public static final String QUOTE = "'";

    /**
     * Execute a CMS Command.
     *
     * @param command  CMS Command Name without parameters.
     * @return a BSCSModel (which can be empty)
     */
    protected BSCSModel execute(String command) {
        return ConnectionHolder.getCurrentConnection().execute(command, null);
    }

    /**
     * Execute a CMS Command with parameters
     *
     * @param command  CMS Command Name without parameters.
     * @return a BSCSModel (which can be empty)
     * @param input a {@link com.orange.bscs.api.model.BSCSModel} object.
     */
    protected BSCSModel execute(String command, BSCSModel input) {
        return ConnectionHolder.getCurrentConnection().execute(command, input);
    }

    /**
     * Execute a CMS Command with parameters
     *
     * @param command  CMS Command Name without parameters.
     * @return a value extending BSCSModel
     * @param input a {@link com.orange.bscs.api.model.BSCSModel} object.
     * @param clazz a {@link java.lang.Class} object.
     * @param <T> a T object.
     */
    protected <T extends BSCSModel> T execute(String command, BSCSModel input, Class<T> clazz) {
        return ConnectionHolder.getCurrentConnection().execute(command, input, clazz);
    }

    /**
     * Execute a CMS Command without parameter but with return type
     *
     * @param command  CMS Command Name without parameters.
     * @return a value extending BSCSModel
     * @param clazz a {@link java.lang.Class} object.
     * @param <T> a T object.
     */
    protected <T extends BSCSModel> T execute(String command, Class<T> clazz) {
        return ConnectionHolder.getCurrentConnection().execute(command, null, clazz);
    }
    
    
    protected BSCSContract initContractModel(Long coId, String coIdPub, String cmd) {
        BSCSContract contract = new BSCSContract();
        if (null != coIdPub && 0 < coIdPub.length()) {
            contract.setContractIdPub(coIdPub);
        } else if(null!=coId){
            contract.setContractId(coId);
        } else {
            throw new SOIException(String.format(ERR_RC2000, cmd, P_CO_ID_PUB));
        }
        return contract;
    }    
}
