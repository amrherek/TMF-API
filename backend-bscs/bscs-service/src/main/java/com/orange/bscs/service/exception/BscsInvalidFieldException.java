package com.orange.bscs.service.exception;

/**
 * BSCS exception when a field value is invalid
 * 
 * @author jwck2987
 *
 */
public class BscsInvalidFieldException extends Exception {


    /**
     * 
     */
    private static final long serialVersionUID = -8252732531448883101L;
    private String value;
    private BscsFieldExceptionEnum name;

    public BscsInvalidFieldException(BscsFieldExceptionEnum name, String value, String message) {
        super(message);
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public BscsFieldExceptionEnum getName() {
        return name;
    }

}
