package com.orange.apibss.common.exceptions.badrequest;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.ApiExceptionCode;

/**
 * Bad parameter format exception (functional code : BAD_REQ)
 * 
 * @author xbbs3851
 *
 */
public class BadRequestException extends ApiException {

	private static final Integer CONSTANT_CODE = ApiExceptionCode.FC_GENERIC;
	private static final String MESSAGE = "Bad request";

	/**
	 * 
	 */
	private static final long serialVersionUID = 8175569445085221967L;

	public BadRequestException(String message, Throwable cause) {
		super(message, cause);
		this.setCode(CONSTANT_CODE);
		this.setGenericMessage(MESSAGE);
	}

	public BadRequestException(String message) {
		super(message);
		this.setCode(CONSTANT_CODE);
		this.setGenericMessage(MESSAGE);
	}


}
