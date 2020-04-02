package com.orange.apibss.common.exceptions.technical;

import com.orange.apibss.common.exceptions.ApiExceptionCode;

/**
 * Exception when a function is not implemented
 * 
 * @author Thibault Duperron
 *
 */
public class NotImplementedException extends TechnicalException {

    /**
     * Serial ID
     */
    private static final long serialVersionUID = -4130185490886256907L;

    private static final Integer CONSTANT_CODE = ApiExceptionCode.TC_NOT_IMPLEMENTED;
    private static final String MESSAGE = "Not implemented";

    /**
     * Build a not implemented exception base on {@link TechnicalException}
     *
     * @param function
     *            function name
     */
    public NotImplementedException(final String function) {
        super("Function " + function + " not implemented");
        this.setCode(CONSTANT_CODE);
        this.setGenericMessage(MESSAGE);
    }

}
