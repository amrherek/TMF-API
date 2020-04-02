package com.orange.apibss.common.exceptions;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Parent class for all Exceptions. Can build a custom error object containing
 * the functional code of the error case
 * 
 * @author xbbs3851
 *
 */
public abstract class ApiException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5649222075410429602L;

	/**
	 * Exception functional code
	 */
	private Integer code;

	private String genericMessage;

    protected ArrayList<Object> arguments = null;

    protected String suffix = "";

	public ApiException() {
		super();
	}

	public ApiException(String message, Throwable cause) {
		super(message, cause);
        arguments = new ArrayList<Object>(Arrays.asList(message));
	}

	public ApiException(String message) {
		super(message);
        arguments = new ArrayList<Object>(Arrays.asList(message));
	}

	public ApiException(Throwable cause) {
		super(cause);
        arguments = new ArrayList<Object>(Arrays.asList(cause.getMessage()));
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getGenericMessage() {
		return genericMessage;
	}

	public void setGenericMessage(String genericMessage) {
		this.genericMessage = genericMessage;
	}

    public Object[] getArguments() {
        return arguments.toArray();
    }

    public String getSuffix() {
        return suffix;
    }

}
