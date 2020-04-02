package com.orange.mea.apisi.party.backend.bscs.transformer;

import org.springframework.stereotype.Service;

import com.orange.apibss.party.model.MaritalStatus;
import com.orange.bscs.model.businesspartner.EnumMaritalStatus;

/**
 * 
 * @author xbbs3851
 *
 */
@Service
public class IndividualMaritalStatusToBscsMaritalStatusTransformer {


    public EnumMaritalStatus doTransform(MaritalStatus maritalStatus) {
		switch (maritalStatus) {
		case DIVORCED:
			return EnumMaritalStatus.DIVORCED;
		case MARRIED:
			return EnumMaritalStatus.MARRIED;
		case SINGLE:
			return EnumMaritalStatus.SINGLE;
		case WIDOWED:
			return EnumMaritalStatus.WIDOWED;
		default:
			return null;
		}
	}

}
