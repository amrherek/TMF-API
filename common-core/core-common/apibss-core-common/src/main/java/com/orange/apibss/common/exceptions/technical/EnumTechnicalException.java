package com.orange.apibss.common.exceptions.technical;

import com.orange.apibss.common.exceptions.ApiExceptionCode;

public class EnumTechnicalException extends TechnicalException {

	private static final Integer CONSTANT_CODE = ApiExceptionCode.TC_ENUM;
	private static final String MESSAGE = "Internal error on enum";

	/**
	 * 
	 */
	private static final long serialVersionUID = -2851454549112255900L;

	public EnumTechnicalException(String message) {
		super(message);
		this.setCode(CONSTANT_CODE);
		this.setGenericMessage(MESSAGE);
	}
}
