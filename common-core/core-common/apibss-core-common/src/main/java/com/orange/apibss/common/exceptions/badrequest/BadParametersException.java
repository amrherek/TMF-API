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
public class BadParametersException extends BadRequestException {

    private static final Integer CONSTANT_CODE = ApiExceptionCode.FC_BAD_PARAMETERS;
    private static final String MESSAGE = "Bad parameter combination";

    /**
     *
     */
    private static final long serialVersionUID = 6430667700153058326L;

    public BadParametersException(final String message) {
        super(message);
        this.setCode(CONSTANT_CODE);
        this.setGenericMessage(MESSAGE);
        arguments = new ArrayList<Object>(Arrays.asList(message));
    }
}
