package com.orange.mea.apisi.bucketbalance.exception;

import java.util.ArrayList;
import java.util.Arrays;

import com.orange.apibss.common.exceptions.badrequest.BadRequestException;

public class BadMsisdnException extends BadRequestException {

	private static final Integer CONSTANT_CODE = BucketBalanceExceptionCode.FC_CONTRACT_NOT_FOUND;
    private static final String MESSAGE = "Msisdn is invalid";

	private static final long serialVersionUID = -6442307516706390897L;

	public BadMsisdnException(String msisdn) {
        super("No contract found with msisdn [" + msisdn + "]");
		this.setCode(CONSTANT_CODE);
		this.setGenericMessage(MESSAGE);
        arguments = new ArrayList<Object>(Arrays.asList(msisdn));
	}


}
