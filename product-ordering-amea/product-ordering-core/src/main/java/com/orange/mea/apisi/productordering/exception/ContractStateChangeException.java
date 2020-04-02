package com.orange.mea.apisi.productordering.exception;

import java.util.ArrayList;
import java.util.Arrays;

import com.orange.apibss.common.exceptions.badrequest.BadRequestException;

public class ContractStateChangeException extends BadRequestException {

	private static final Integer CONSTANT_CODE = ProductOrderingApiExceptionCode.FC_CONTRACT_STATE_CHANGE;
	private static final String MESSAGE = "Contract state change impossible";

	/**
	 * 
	 */
	private static final long serialVersionUID = -7298785965830190121L;

	public ContractStateChangeException(String coId, String message) {
		super("Impossible to change state for contract with id [" + coId + "]: " + message);
		this.setCode(CONSTANT_CODE);
		this.setGenericMessage(MESSAGE);
        arguments = new ArrayList<Object>(Arrays.asList(coId, message));
	}

}
