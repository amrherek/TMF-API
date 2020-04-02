package com.orange.mea.apisi.party.backend.bscs.transformer;

import org.springframework.stereotype.Service;

import com.orange.apibss.party.model.MaritalStatus;
import com.orange.bscs.model.businesspartner.EnumMaritalStatus;

/**
 * 
 * 
 * @author xbbs3851
 *
 */
@Service
public class BscsMaritalStatusToIndividualMaritalStatusTransformer {


    public MaritalStatus doTransform(EnumMaritalStatus maritalStatus) {
		switch (maritalStatus) {
		case DIVORCED:
            return MaritalStatus.DIVORCED;
		case MARRIED:
            return MaritalStatus.MARRIED;
		case SINGLE:
            return MaritalStatus.SINGLE;
		case WIDOWED:
            return MaritalStatus.WIDOWED;
		default:
			return null;
		}
	}

}
