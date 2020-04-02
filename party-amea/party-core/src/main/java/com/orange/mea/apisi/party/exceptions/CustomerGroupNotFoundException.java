package com.orange.mea.apisi.party.exceptions;

import com.orange.apibss.common.exceptions.technical.EnumTechnicalException;

/**
 * Party dictionary exception (functional code : *** ). When a customerGroup
 * is expected to be in bscs but is not found in pre-loaded constants (see
 * ConstantsService)
 *
 */
public class CustomerGroupNotFoundException extends EnumTechnicalException {

	/**
	 *
	 */
	private static final long serialVersionUID = 3071535607439391067L;

	public CustomerGroupNotFoundException(String message) {
		super(message);
	}
}
