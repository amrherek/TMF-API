package com.orange.apibss.common.exceptions.technical;

import java.util.ArrayList;
import java.util.Arrays;

import com.orange.apibss.common.exceptions.ApiExceptionCode;

/**
 * Exception when try to update a contract before batch update the previous
 * request
 * 
 * @author Thibault Duperron
 *
 */
public class PendingRequestException extends TechnicalException {

	private static final Integer CONSTANT_CODE = ApiExceptionCode.TC_PENDING_REQUEST;
	private static final String MESSAGE = "Pending request";

    /**
     * Serial ID
     */
    private static final long serialVersionUID = 5639796749400160992L;

	public PendingRequestException(String coId) {
		super("Contract [" + coId + "] could not be updated because he has pending request(s)");
		this.setCode(CONSTANT_CODE);
		this.setGenericMessage(MESSAGE);
        arguments = new ArrayList<Object>(Arrays.asList(coId));
    }
}
