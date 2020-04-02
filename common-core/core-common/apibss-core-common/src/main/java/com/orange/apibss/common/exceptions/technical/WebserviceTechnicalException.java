package com.orange.apibss.common.exceptions.technical;

import java.util.ArrayList;
import java.util.Arrays;

import com.orange.apibss.common.exceptions.ApiExceptionCode;

public class WebserviceTechnicalException extends TechnicalException {

	private static final Integer CONSTANT_CODE = ApiExceptionCode.TC_WEBSERVICE;
	private static final String MESSAGE = "Webservice call internal error";

	/**
	 * 
	 */
	private static final long serialVersionUID = -6986588873153137756L;

	public WebserviceTechnicalException(String wsName, String method, String message) {
		super("Webservice " + wsName + ", method " + method + " error: " + message);
		this.setCode(CONSTANT_CODE);
		this.setGenericMessage(MESSAGE);
        arguments = new ArrayList<Object>(Arrays.asList(wsName, method, message));
	}

    public WebserviceTechnicalException(String wsName, String method, String code, String message) {
        super("Webservice " + wsName + ", method " + method + ", action failed with errorCode: " + code + ", message: "
                + message);
        this.setCode(CONSTANT_CODE);
        this.setGenericMessage(MESSAGE);
        suffix = ".withErrorCode";
        arguments = new ArrayList<Object>(Arrays.asList(wsName, method, code, message));
    }

	public WebserviceTechnicalException(String wsName, String method, String message, Throwable t) {
		super("Webservice " + wsName + ", method " + method + " error: " + message, t);
		this.setCode(CONSTANT_CODE);
		this.setGenericMessage(MESSAGE);
        arguments = new ArrayList<Object>(Arrays.asList(wsName, method, message));
	}

}
