package com.orange.apibss.common.exceptions.badrequest;

import java.util.ArrayList;
import java.util.Arrays;

import com.orange.apibss.common.exceptions.ApiExceptionCode;

/**
 * Empty parameter exception (functional code : API_ERR_FC006)
 *
 * @author xbbs3851
 *
 */
public class TooManyParameterException extends BadRequestException {

    private static final Integer CONSTANT_CODE = ApiExceptionCode.FC_TOO_MANY_PARAMETERS;
    private static final String MESSAGE = "Too many parameters";

    /**
     *
     */
    private static final long serialVersionUID = 6430667700153058326L;

    public TooManyParameterException(final String parameter, final int maxOccurrence) {
        super("Parameter: " + parameter + " must be at most " + maxOccurrence + " times");
        this.setCode(CONSTANT_CODE);
        this.setGenericMessage(MESSAGE);
        arguments = new ArrayList<Object>(Arrays.asList(parameter, maxOccurrence));
    }
}
