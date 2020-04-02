package com.orange.mea.apisi.productordering.exception;

import java.util.ArrayList;
import java.util.Arrays;

import com.orange.apibss.common.exceptions.badrequest.BadRequestException;

public class NoFafException extends BadRequestException {

    private static final Integer CONSTANT_CODE = ProductOrderingApiExceptionCode.FC_NO_FAF;
    private static final String MESSAGE = "Faf not configured";

	/**
	 * 
	 */
	private static final long serialVersionUID = -7298785965830190121L;

    public NoFafException(String msisdn, String message) {
        super("Faf not configured for contract with msisdn [" + msisdn + "]: " + message);
		this.setCode(CONSTANT_CODE);
		this.setGenericMessage(MESSAGE);
        arguments = new ArrayList<Object>(Arrays.asList(msisdn, message));
	}

}
