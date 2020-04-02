package com.orange.bscs.service.exception;

/**
 * BSCS exception when peding requests exist on contract
 * 
 * @author jwck2987
 *
 */
public class BscsPendingException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -2413744227807090345L;


    public BscsPendingException(String message) {
        super(message);
    }

}
