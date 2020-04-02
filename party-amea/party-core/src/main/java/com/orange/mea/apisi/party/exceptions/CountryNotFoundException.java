package com.orange.mea.apisi.party.exceptions;

import com.orange.apibss.common.exceptions.technical.EnumTechnicalException;

/**
 * Party dictionary exception (functional code : API_ERR_TC007). When a country
 * is expected to be in bscs but is not found in pre-loaded constants (see
 * ConstantsService)
 * 
 * @author xbbs3851
 *
 */
public class CountryNotFoundException extends EnumTechnicalException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6442307516706390897L;

	public CountryNotFoundException(String message) {
		super(message);
	}

}
