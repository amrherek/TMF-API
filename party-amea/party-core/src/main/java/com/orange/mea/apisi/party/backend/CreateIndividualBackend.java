package com.orange.mea.apisi.party.backend;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.party.model.obw.Individual;
import com.orange.bscs.api.model.exception.CMSException;
import com.orange.bscs.model.businesspartner.BSCSCustomer;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;

public abstract interface CreateIndividualBackend
{
  public abstract BSCSCustomer createIndividual(Individual paramIndividual)
    throws ApiException, BscsInvalidIdException, BscsInvalidFieldException, CMSException;
  
  public abstract Individual patchIndividual(Individual paramIndividual)
		    throws ApiException;
}
