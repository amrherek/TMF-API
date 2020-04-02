package com.orange.mea.apisi.security.exception;

import com.orange.apibss.common.exceptions.technical.TechnicalException;

public class ApiTechnicalSecurityException extends TechnicalException {

    /**
     * 
     */
    private static final long serialVersionUID = -7953460871392187199L;

    private static final Integer CONSTANT_CODE = ApiSecurityExceptionCode.TC_SECURITY;
    private static final String MESSAGE = "Authentication internal error";

    public ApiTechnicalSecurityException(Exception e) {
        super(e.getMessage(), e);
        this.setCode(CONSTANT_CODE);
        this.setGenericMessage(MESSAGE);
    }

}
