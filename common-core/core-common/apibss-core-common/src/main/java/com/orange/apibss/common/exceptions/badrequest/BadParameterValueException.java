package com.orange.apibss.common.exceptions.badrequest;

import java.util.ArrayList;
import java.util.Arrays;

import com.orange.apibss.common.exceptions.ApiExceptionCode;

/**
 * Bad parameter format exception (functional code : API_ERR_FC009)
 * 
 * @author xbbs3851
 *
 */
public class BadParameterValueException extends BadRequestException {

	private static final Integer CONSTANT_CODE = ApiExceptionCode.FC_BAD_VALUE_PARAMETER;
	private static final String MESSAGE = "Bad parameter value";

	private String name;
	/**
	 * 
	 */
	private static final long serialVersionUID = 8175569445085221967L;

    public BadParameterValueException(final String name, final String value) {
		super("Bad value for parameter " + name + ": [" + value + "]");
		this.name = name;
		this.setCode(CONSTANT_CODE);
		this.setGenericMessage(MESSAGE);
        arguments = new ArrayList<Object>(Arrays.asList(name, value));
        suffix = ".withValue";
	}

	public BadParameterValueException(String name, String value, Throwable cause) {
		super("Bad value for parameter " + name + ": [" + value + "], ", cause);
		this.name = name;
		this.setCode(CONSTANT_CODE);
		this.setGenericMessage(MESSAGE);
        arguments = new ArrayList<Object>(Arrays.asList(name, value));
        suffix = ".withValue";
	}

	public BadParameterValueException(String message) {
		super(message);
		this.setCode(CONSTANT_CODE);
		this.setGenericMessage(MESSAGE);
	}

	public BadParameterValueException(String name, String value, String expectedValue) {
		super("Bad value for parameter " + name + ": [" + value + "], should be [" + expectedValue + "]");
		this.name = name;
		this.setCode(CONSTANT_CODE);
		this.setGenericMessage(MESSAGE);
        arguments = new ArrayList<Object>(Arrays.asList(name, value, expectedValue));
        suffix = ".withValueAndExpected";
	}

	public String getParameterName() {
		return name;
	}
}
