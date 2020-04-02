package com.orange.mea.apisi.security.exception;

import com.orange.apibss.common.exceptions.badrequest.BadRequestException;

public class ApiSecurityException extends BadRequestException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1308503354927630683L;

    public ApiSecurityException(Integer code, String shortMessage, String message, Throwable cause) {
		super(message, cause);
        this.setCode(code);
        this.setGenericMessage(shortMessage);
	}

}
