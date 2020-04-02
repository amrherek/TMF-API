package com.orange.apibss.common.exceptions.badrequest;

import com.orange.apibss.common.exceptions.ApiExceptionCode;

public class NotFoundException extends BadRequestException {

	private static final Integer CONSTANT_CODE = ApiExceptionCode.FC_NOT_FOUND;
	private static final String MESSAGE = "Not found";

	/**
	 * 
	 */
	private static final long serialVersionUID = -7614544692964595752L;

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
		this.setCode(CONSTANT_CODE);
		this.setGenericMessage(MESSAGE);
	}

	public NotFoundException(String message) {
		super(message);
		this.setCode(CONSTANT_CODE);
		this.setGenericMessage(MESSAGE);
	}

}
