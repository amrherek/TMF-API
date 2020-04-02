package com.orange.bscs.api.exceptions;

/**
 * A runtime exception that encapsulates (in this.getCause()) an Exception Fault
 */
public class APIException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String faultCode;
	private String faultString;

	/**
	 * The default constructor
	 */
	public APIException() {
		super();
	}

	/**
	 * The default constructor with message and the root cause.
	 * 
	 * @param message
	 * @param cause
	 */
	public APIException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * The default constructor the root cause.
	 * 
	 * @param cause
	 */
	public APIException(Throwable cause) {
		super(cause);
	}

	/**
	 * Common constructor, construct getMessage as faultCode +" " + faultLabel
	 * but each parameter may be obtains with accessors
	 * 
	 * @param apiExceptionFault
	 *            initial exception
	 * 
	 * @param faultCode
	 *            a Code (Should be present in "errors" bundle)
	 * 
	 * @param faultLabel
	 *            a message description
	 */
	public APIException(Exception apiExceptionFault, String faultCode,
			String faultLabel) {
		super((null == faultCode ? "" : faultCode + " ") + faultLabel,
				apiExceptionFault);
		this.faultCode = faultCode;
		this.faultString = faultLabel;
	}

	/**
	 * @return initiale fault
	 */
	public Exception getFault() {
		return (Exception) this.getCause();
	}

	/**
	 * @return Error code
	 */
	public String getFaultCode() {
		return faultCode;
	}

	/**
	 * @return error label (as specify in constructor)
	 */
	public String getFaultLabel() {
		return faultString;
	}
}
