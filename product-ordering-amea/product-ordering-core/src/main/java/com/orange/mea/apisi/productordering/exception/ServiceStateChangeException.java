package com.orange.mea.apisi.productordering.exception;

import java.util.ArrayList;
import java.util.Arrays;

import com.orange.apibss.common.exceptions.badrequest.BadRequestException;

public class ServiceStateChangeException extends BadRequestException {

	private static final Integer CONSTANT_CODE = ProductOrderingApiExceptionCode.FC_SERVICE_STATE_CHANGE;
	private static final String MESSAGE = "Service state change impossible";

	/**
	 * 
	 */
	private static final long serialVersionUID = -7298785965830190121L;

	public ServiceStateChangeException(String serviceId, String coId, String message) {
		super(message + " for service with id [" + serviceId + "] in contract with id [" + coId + "]");
		this.setCode(CONSTANT_CODE);
		this.setGenericMessage(MESSAGE);
        arguments = new ArrayList<Object>(Arrays.asList(message, serviceId, coId));
	}

}
