package com.orange.apibss.common.exceptions.technical;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.ApiExceptionCode;

public class TechnicalException extends ApiException {

	private static final Integer CONSTANT_CODE = ApiExceptionCode.TC_GENERIC;
	private static final String MESSAGE = "Internal error";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1308503354927630683L;

	public TechnicalException(String message, Throwable cause) {
		super(message, cause);
		this.setCode(CONSTANT_CODE);
		this.setGenericMessage(MESSAGE);
	}

	public TechnicalException(String message) {
		super(message);
		this.setCode(CONSTANT_CODE);
		this.setGenericMessage(MESSAGE);
	}

}
