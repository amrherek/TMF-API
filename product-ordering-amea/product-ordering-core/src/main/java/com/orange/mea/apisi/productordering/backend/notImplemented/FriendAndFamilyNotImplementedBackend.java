package com.orange.mea.apisi.productordering.backend.notImplemented;

import org.springframework.stereotype.Component;

import com.orange.apibss.common.exceptions.badrequest.BadRequestException;
import com.orange.apibss.common.exceptions.technical.NotImplementedException;
import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.mea.apisi.productordering.backend.faf.AddFaf;
import com.orange.mea.apisi.productordering.backend.faf.DeleteFaf;
import com.orange.mea.apisi.productordering.backend.faf.UpdateFaf;

@Component
public class FriendAndFamilyNotImplementedBackend implements AddFaf, UpdateFaf, DeleteFaf {

	@Override
	public void deleteFaf(String msisdn, String friendAndFamilyNumber) throws BadRequestException, TechnicalException {
        throw new NotImplementedException("deleteFaf");
	}

	@Override
	public void updateFaf(String msisdn, String currentNumber, String newNumber)
			throws BadRequestException, TechnicalException {
        throw new NotImplementedException("updateFaf");
	}

	@Override
	public void addFaf(String msisdn, String friendAndFamilyNumber) throws BadRequestException, TechnicalException {
        throw new NotImplementedException("addFaf");
	}


}
