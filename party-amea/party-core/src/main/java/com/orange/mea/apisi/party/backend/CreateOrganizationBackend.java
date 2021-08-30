package com.orange.mea.apisi.party.backend;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.party.model.obw.Organization;
import com.orange.bscs.model.businesspartner.BSCSCustomer;

public abstract interface CreateOrganizationBackend
{
  public abstract BSCSCustomer createOrganization(Organization organization)
    throws ApiException;
  
  public abstract Organization patchOrganization(Organization organization)
		    throws ApiException;
}
