package com.orange.mea.apisi.productordering.exception;

import java.util.ArrayList;
import java.util.Arrays;

import com.orange.apibss.common.exceptions.technical.TechnicalException;

public class PartyTechnicalException extends TechnicalException {

	private static final Integer CONSTANT_CODE = ProductOrderingApiExceptionCode.TC_PARTY;
	private static final String MESSAGE = "API call internal error";

	/**
	 * 
	 */
	private static final long serialVersionUID = -6986588873153137756L;

	public PartyTechnicalException(String apiName, String message) {
		super("API " + apiName + " error: " + message);
		this.setCode(CONSTANT_CODE);
		this.setGenericMessage(MESSAGE);
        arguments = new ArrayList<Object>(Arrays.asList(apiName, message));
	}

	public PartyTechnicalException(String apiName, String message, Throwable t) {
		super("API " + apiName + " error: " + message, t);
		this.setCode(CONSTANT_CODE);
		this.setGenericMessage(MESSAGE);
        arguments = new ArrayList<Object>(Arrays.asList(apiName, message));
	}

}
