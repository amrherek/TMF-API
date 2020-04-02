package com.orange.mea.apisi.bucketbalance.exception;

import java.util.ArrayList;
import java.util.Arrays;

import com.orange.apibss.common.exceptions.badrequest.BadRequestException;

public class BadCreditAmountException extends BadRequestException {

    private static final Integer CONSTANT_CODE = BucketBalanceExceptionCode.FC_BAD_CREDIT_AMOUNT;
    private static final String MESSAGE = "Bad credit value";

	private static final long serialVersionUID = -6442307516706390897L;

    public BadCreditAmountException(String amount, String msisdn) {
        super("Invalid credit amount [" + amount + "] for msisdn [" + msisdn + "]");
		this.setCode(CONSTANT_CODE);
		this.setGenericMessage(MESSAGE);
        arguments = new ArrayList<Object>(Arrays.asList(amount, msisdn));
	}


}
