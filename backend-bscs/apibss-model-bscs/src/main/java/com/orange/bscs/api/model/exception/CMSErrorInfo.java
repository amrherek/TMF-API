/*
 * $$RCSfile: CMSErrorInfo.java,v $$
 * Copyright (c) 2006 Alcatel
 * All rights reserved.
 *
 * @author Jean-Baptiste Binet
 * @version $$Revision: 1.1 $$
 * $$Date: 2007/10/12 09:55:27 $$
 * Last modified by $$Author: binet $$
 * $$Name:  $$
 * $$Source: /home/BSCS_CVS/standard/bscs/ccl/src/com/alcatel/ccl/client/CMSErrorInfo.java,v $$
 */

package com.orange.bscs.api.model.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is used to map the data contained in an ErrorInfo instance (LHS object). It is used to
 * describe a CMS error.
 */
public class CMSErrorInfo implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = -6255533836330830557L;

    /** The error code. */
	private String _errorCode = null;

	/** The error arguments which add informations to error code. */
    private ArrayList<Object> _errorArgs = null;

	/** The error additional information. */
	private String _additionalInfo = null;

	/**
	 * This method constructs a new instance of this class.
	 * 
	 * @param errorCode
	 *            The error code.
	 * @param errorArgs
	 *            The error arguments.
	 * @param additionalInfo
	 *            The additional information.
	 */
	public CMSErrorInfo(String errorCode, Object[] errorArgs, String additionalInfo)
	{
		_errorCode = errorCode;
        _errorArgs = new ArrayList<>(Arrays.asList(errorArgs));
		_additionalInfo = additionalInfo;
	}

	/**
	 * This method returns the additional information.
	 * 
	 * @return The additional information.
	 */
	public String getAdditionalInfo()
	{
		return _additionalInfo;
	}

	/**
	 * This method returns the error arguments.
	 * 
	 * @return An array of Object representing the error arguments.
	 */
	public Object[] getErrorArgs()
	{
        return _errorArgs.toArray();
	}

	/**
	 * This method returns the error code.
	 * 
	 * @return The error code.
	 */
	public String getErrorCode()
	{
		return _errorCode;
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

		dump += "ErrorInfo =>";
		dump += "\n\terrorCode = " + _errorCode;
		dump += "\n\tadditionalInfo = " + _additionalInfo;

		if (_errorArgs == null)
		{
			dump += "\n\terrorArgs = <empty>";
		}
		else
		{
            for (int i = 0; i < _errorArgs.size(); i++)
			{
                dump += "\n\terrorArgs[" + i + "] = " + _errorArgs.get(i);
			}
		}

		dump += "\nErrorInfo <=";

		return dump;
	}
}