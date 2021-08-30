package com.orange.mea.apisi.party.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.badrequest.BadParameterFormatException;
import com.orange.apibss.common.exceptions.badrequest.MissingParameterException;
import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.apibss.party.model.Individual;
import com.orange.apibss.party.model.obw.Organization;
import com.orange.apibss.party.model.obw.RelatedParty;
import com.orange.bscs.model.businesspartner.BSCSCustomer;
import com.orange.mea.apisi.party.backend.CreateIndividualBackend;
import com.orange.mea.apisi.party.backend.CreateOrganizationBackend;
import com.orange.mea.apisi.party.backend.FindIndividualsByCriteriaBackend;
import com.orange.mea.apisi.party.backend.FindIndividualsByIdentificationBackend;
import com.orange.mea.apisi.party.backend.GetIndividualBackend;
import com.orange.mea.apisi.party.backend.UpdateIndividualBackend;
import com.orange.mea.apisi.party.beans.IndividualFilteringCriteria;

/**
 * Service for Organization requests management
 *
 * @author LFI
 *
 */
@Service
public class OrganizationService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    
    @Autowired
    private CreateOrganizationBackend createOrganizationBackend;

    
    public Organization postOrganization(Organization organization)
    	    throws ApiException
    	  {
    	    if (organization == null) {
    	      throw new TechnicalException("organization is null");
    	    }
    	    this.logger.debug("creating new organization [{}]", organization.getId());
    	    try
    	    {
    	      BSCSCustomer customer = this.createOrganizationBackend.createOrganization(organization);
    	      
    	      RelatedParty newRelatedParty = new RelatedParty();
    	      if( null != organization.getRelatedParty() && organization.getRelatedParty().size()>0 ){
    	    	  newRelatedParty = (RelatedParty)organization.getRelatedParty().get(0);
       	      }
    	     
    	      newRelatedParty.setId(customer.getCustomerIDPub());
    	      List<RelatedParty> listRelatedParty = new ArrayList<RelatedParty>();
    	      listRelatedParty.add(newRelatedParty);
    	      organization.setRelatedParty(listRelatedParty);
    	      organization.setId(customer.getCustomerIDPub()); 
    	    }catch(ApiException e){
        		throw e;
        	}
    	    return organization;
    	  }
    
    
    public Organization patchOrganization(Organization organization)
    	    throws ApiException
    	  {
    	    if (organization == null) {
    	      throw new TechnicalException("organization is null");
    	    }
    	    this.logger.debug("PATCH existing organization [{}]", organization.getId());
    	    Organization organizationUp = null;
    	    try
    	    {
    	       organizationUp = this.createOrganizationBackend.patchOrganization(organization);

	  	    }catch(ApiException e){
	    		throw e;
	    	}
    	    return organizationUp;
    	  }

}
