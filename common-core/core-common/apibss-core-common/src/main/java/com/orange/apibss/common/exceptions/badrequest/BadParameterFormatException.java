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
public class BadParameterFormatException extends BadRequestException {

	private static final Integer CONSTANT_CODE = ApiExceptionCode.FC_BAD_FORMAT_PARAMETER;
	private static final String MESSAGE = "Bad parameter format";

	/**
	 * 
	 */
	private static final long serialVersionUID = 8175569445085221967L;

	public BadParameterFormatException(String name, String value, String format) {
        super("Bad format for parameter " + name + ": [" + value + "], must be " + format);
		this.setCode(CONSTANT_CODE);
		this.setGenericMessage(MESSAGE);
        arguments = new ArrayList<Object>(Arrays.asList(name, value, format));
        suffix = ".withFormat";
	}

	public BadParameterFormatException(String name, String value, String format, Throwable cause) {
        super("Bad format for parameter " + name + ": [" + value + "], must be " + format, cause);
		this.setCode(CONSTANT_CODE);
		this.setGenericMessage(MESSAGE);
        arguments = new ArrayList<Object>(Arrays.asList(name, value, format));
        suffix = ".withFormat";
	}

	public BadParameterFormatException(String message) {
		super(message);
		this.setCode(CONSTANT_CODE);
		this.setGenericMessage(MESSAGE);
	}

}
