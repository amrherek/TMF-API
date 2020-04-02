package com.orange.bscs.api.exceptions;


/**
 * Bscs connection initialization exception
 * 
 * @author xbbs3851
 *
 */
public class BscsConnectionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7833624856041291001L;

	public BscsConnectionException() {
		super();
	}

	public BscsConnectionException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BscsConnectionException(String message, Throwable cause) {
		super(message, cause);
	}

	public BscsConnectionException(String message) {
		super(message);
	}

	public BscsConnectionException(Throwable cause) {
		super(cause);
	}


}
