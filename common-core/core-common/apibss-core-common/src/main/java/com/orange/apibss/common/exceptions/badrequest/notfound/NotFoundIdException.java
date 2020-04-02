package com.orange.apibss.common.exceptions.badrequest.notfound;

import java.util.ArrayList;
import java.util.Arrays;

import com.orange.apibss.common.exceptions.badrequest.NotFoundException;

public class NotFoundIdException extends NotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1009416528943048299L;

	public NotFoundIdException(String name, String value) {
		super("Unknown id: " + value + " for " + name);
		idName = name;
		idValue = value;
        arguments = new ArrayList<Object>(Arrays.asList(name, value));
        suffix = ".withId";
	}

	private String idValue;
	private String idName;

	public String getIdValue() {
		return idValue;
	}

	public void setIdValue(String idValue) {
		this.idValue = idValue;
	}

	public String getIdName() {
		return idName;
	}

	public void setIdName(String idName) {
		this.idName = idName;
	}

}
