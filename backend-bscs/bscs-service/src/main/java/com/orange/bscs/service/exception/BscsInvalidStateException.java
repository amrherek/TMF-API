package com.orange.bscs.service.exception;

/**
 * BSCS exception when the state of an element is not valid for an operation
 * 
 * @author jwck2987
 *
 */
public class BscsInvalidStateException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -4404103302288059127L;

    private BscsFieldExceptionEnum field;

    public BscsInvalidStateException(BscsFieldExceptionEnum field, String message) {
        super(message);
        this.field = field;
    }

    public BscsFieldExceptionEnum getField() {
        return field;
    }

}
