package com.orange.mea.apisi.productordering.exception;

import java.util.ArrayList;
import java.util.Arrays;

import com.orange.apibss.common.exceptions.badrequest.BadRequestException;

public class PartyUpdateException extends BadRequestException {

	private static final Integer CONSTANT_CODE = ProductOrderingApiExceptionCode.FC_CONTRACT_PARTY_UPDATE;
	private static final String MESSAGE = "Update party impossible";

	/**
	 * 
	 */
	private static final long serialVersionUID = -6986588873153137756L;

	public PartyUpdateException(String message) {
		super("API Party error: " + message);
		this.setCode(CONSTANT_CODE);
		this.setGenericMessage(MESSAGE);
        arguments = new ArrayList<Object>(Arrays.asList(message));
	}

}
