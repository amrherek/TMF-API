package com.orange.mea.apisi.party.exceptions;

import com.orange.apibss.common.exceptions.technical.EnumTechnicalException;

/**
 * Party dictionary exception (functional code : API_ERR_TC010). When an marital
 * status does not match local IndividualMaritalStatus
 * 
 * @author xbbs3851
 *
 */
public class MaritalStatusNotKnownException extends EnumTechnicalException {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6442307516706390897L;


	public MaritalStatusNotKnownException(String message) {
		super(message);
	}


}
