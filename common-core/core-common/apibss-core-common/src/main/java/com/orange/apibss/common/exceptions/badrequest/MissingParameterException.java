package com.orange.apibss.common.exceptions.badrequest;

import java.util.ArrayList;
import java.util.Arrays;

import com.orange.apibss.common.exceptions.ApiExceptionCode;

/**
 * Bad parameter format exception
 *
 * @author xbbs3851
 *
 */
public class MissingParameterException extends BadRequestException {

    private static final Integer CONSTANT_CODE = ApiExceptionCode.FC_EMPTY_PARAMETER;
    private static final String MESSAGE = "Missing parameter";

    /**
     *
     */
    private static final long serialVersionUID = 8175569445085221967L;

    public MissingParameterException(final String name) {
        super("Parameter: " + name + " is missing, null or empty");
        this.setCode(CONSTANT_CODE);
        this.setGenericMessage(MESSAGE);
        arguments = new ArrayList<Object>(Arrays.asList(name));
    }

}
