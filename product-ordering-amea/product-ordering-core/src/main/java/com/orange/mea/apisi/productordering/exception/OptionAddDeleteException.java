package com.orange.mea.apisi.productordering.exception;

import java.util.ArrayList;
import java.util.Arrays;

import com.orange.apibss.common.exceptions.badrequest.BadRequestException;

public class OptionAddDeleteException extends BadRequestException {

    private static final Integer CONSTANT_CODE = ProductOrderingApiExceptionCode.FC_OPTION_ADD;
    private static final String MESSAGE = "Option add / delete impossible";

	/**
	 * 
	 */
	private static final long serialVersionUID = -7298785965830190121L;

    public OptionAddDeleteException(String serviceId, String spId, String coId, String message) {
        super(message + " for service with id [" + serviceId + "] and service package id [" + spId
                + "] in contract with id [" + coId + "]");
		this.setCode(CONSTANT_CODE);
		this.setGenericMessage(MESSAGE);
        arguments = new ArrayList<Object>(Arrays.asList(message, serviceId, spId, coId));
	}

    public OptionAddDeleteException(String msisdn) {
        super("Option add / delete impossible: contract with msisdn [" + msisdn + "] is not active");
        this.setCode(CONSTANT_CODE);
        this.setGenericMessage(MESSAGE);
        arguments = new ArrayList<Object>(Arrays.asList(msisdn));
        suffix = ".inactive";
    }

}
