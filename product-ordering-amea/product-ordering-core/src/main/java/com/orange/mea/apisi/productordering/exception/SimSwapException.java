package com.orange.mea.apisi.productordering.exception;

import com.orange.apibss.common.exceptions.badrequest.BadRequestException;

public class SimSwapException extends BadRequestException {

	private static final Integer CONSTANT_CODE = ProductOrderingApiExceptionCode.FC_SIM_SWAP;
	private static final String MESSAGE = "Sim swap impossible";

	/**
	 * 
	 */
	private static final long serialVersionUID = -7298785965830190121L;

	public SimSwapException(String message) {
		super(message);
		this.setCode(CONSTANT_CODE);
		this.setGenericMessage(MESSAGE);
	}

}
