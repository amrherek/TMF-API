/*
 * $$RCSfile: CMSException.java,v $$
 * Copyright (c) 2006 Alcatel
 * All rights reserved.
 *
 * @author Jean-Baptiste Binet
 * @version $$Revision: 1.3 $$
 * $$Date: 2011/06/16 09:20:05 $$
 * Last modified by $$Author: belbeze $$
 * $$Name:  $$
 * $$Source: /home/BSCS_CVS/standard/bscs/ccl/src/com/alcatel/ccl/client/CMSException.java,v $$
 */

package com.orange.bscs.api.model.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class represents a CMS exception. It is used to avoid Corba exceptions and CMS internal
 * exceptions to be propagated out of the CCL library.
 */
public class CMSException extends Exception
{
	/** Serial version identifier. */
	private static final long serialVersionUID = 7770448177664358780L;

	/**
	 * The cause of this exception. With Java 1.4, the Exception class have a cause attribute that
	 * can be used to have the history of all exceptions raised. But in Java 1.3, this mechanism
	 * does not exist. So the following attribute tries to replace it.
	 */
	private Throwable _cause = null;

	/** The detailed CMS internal errors. */
    private ArrayList<CMSErrorInfo> _errors = null;

	/**
	 * This method constructs a new instance of this class.
	 * 
	 * @param message
	 *            The message associated.
	 */
	public CMSException(String message)
	{
		super(message);
	}

	/**
	 * This method constructs a new instance of this class.
	 * 
	 * @param message
	 *            The message associated.
	 * @param errors
	 *            An array of CMSErrorInfo, each one describing a particular CMS internal error.
	 */
	public CMSException(String message, CMSErrorInfo[] errors)
	{
		super(message);
        _errors = new ArrayList<>(Arrays.asList(errors));
	}

	/**
	 * This method constructs a new instance of this class.
	 * 
	 * @param message
	 *            The message associated.
	 * @param cause
	 *            The cause of this exception.
	 */
	public CMSException(String message, Throwable cause)
	{
		super(message);
		_cause = cause;
	}

	/**
	 * This method constructs a new instance of this class.
	 * 
	 * @param message
	 *            The message associated.
	 * @param cause
	 *            The cause of this exception.
	 * @param errors
	 *            An array of CMSErrorInfo, each one describing a particular CMS internal error.
	 */
	public CMSException(String message, Throwable cause, CMSErrorInfo[] errors)
	{
		super(message);
		_cause = cause;
        _errors = new ArrayList<>(Arrays.asList(errors));
	}

	/**
	 * This method returns the list of detailed CMS internal errors
	 * 
	 * @return An array of CMSErrorInfo, each one describing a particular CMS internal error.
	 */
	public CMSErrorInfo[] getDetailedCMSInternalErrors() {
        return _errors.toArray(new CMSErrorInfo[_errors.size()]);
	}

	/**
	 * This method returns a string representation of this class.
	 * 
	 * @return A string representation of this class.
	 */
	@Override
    public String toString()
	{
		String dump = new String();

		dump += "Message = " + getMessage();

		if (_errors != null)
		{
            for (int i = 0; i < _errors.size(); i++)
			{
                dump += "\n" + _errors.get(i).toString();
			}
		}

		if (_cause != null)
		{
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			_cause.printStackTrace(pw);
			dump += "\ncaused by : " + sw.toString();
		}

		return dump;
	}
}