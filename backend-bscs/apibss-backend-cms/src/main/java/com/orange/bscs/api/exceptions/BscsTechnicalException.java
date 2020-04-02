package com.orange.bscs.api.exceptions;

import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.bscs.api.model.exception.SOIException;

public class BscsTechnicalException extends TechnicalException {

	private static final Integer CONSTANT_CODE = 5100;
	private static final String MESSAGE = "BSCS internal error";

	/**
	 * 
	 */
	private static final long serialVersionUID = 2914910025089338473L;

	public BscsTechnicalException(SOIException e) {
		super("BSCS error: code=" + e.getCode() + ", message=" + e.getMessage(), e);
		this.setCode(CONSTANT_CODE);
		this.setGenericMessage(MESSAGE);
	}

	public BscsTechnicalException(Exception e) {
		super("BSCS error: " + e.getMessage(), e);
		this.setCode(CONSTANT_CODE);
		this.setGenericMessage(MESSAGE);
	}

}
