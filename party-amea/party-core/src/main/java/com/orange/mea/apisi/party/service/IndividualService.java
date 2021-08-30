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
import com.orange.apibss.party.model.obw.RelatedParty;
import com.orange.bscs.api.model.exception.CMSException;
import com.orange.bscs.model.businesspartner.BSCSCustomer;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;
import com.orange.mea.apisi.party.backend.CreateIndividualBackend;
import com.orange.mea.apisi.party.backend.FindIndividualsByCriteriaBackend;
import com.orange.mea.apisi.party.backend.FindIndividualsByIdentificationBackend;
import com.orange.mea.apisi.party.backend.GetIndividualBackend;
import com.orange.mea.apisi.party.backend.UpdateIndividualBackend;
import com.orange.mea.apisi.party.beans.IndividualFilteringCriteria;

/**
 * Service for Individual requests management
 *
 * @author xbbs3851
 *
 */
@Service
public class IndividualService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String INDIVIDUAL_ID = "individual.id";

    @Autowired
    private FindIndividualsByCriteriaBackend findIndividualsByCriteriaBackend;

    @Autowired
    private FindIndividualsByIdentificationBackend findIndividualsByIdentificationBackend;

    @Autowired
    private GetIndividualBackend getIndividualBackend;

    @Autowired
    private UpdateIndividualBackend updateIndividualBackend;
    
    @Autowired
    private CreateIndividualBackend createIndividualBackend;

    /**
     * Returns an individual using its id (get)
     *
     * @param id
     * @return
     * @throws ApiException
     */
    public Individual getIndividual(final String id) throws ApiException {
        logger.debug("Getting individual [{}]", id);
        // validation
        if (StringUtils.isEmpty(id)) {
            throw new MissingParameterException(INDIVIDUAL_ID);
        }
        try {
            return getIndividualBackend.getIndividual(id);
        } catch (final NumberFormatException e) {
            throw new BadParameterFormatException(INDIVIDUAL_ID, id, "a numeric value", e);
        }
    }

    /**
     * Returns a list of individuals matching the given filters (find)
     * 
     * @param criteria
     * @return
     * @throws ApiException
     */
    public List<Individual> findIndividualsByCriteria(final IndividualFilteringCriteria criteria)
            throws ApiException {
        logger.debug("Finding individuals with [{}]", criteria.toString());
        return findIndividualsByCriteriaBackend.findIndividualsByCriteria(criteria);

    }

    /**
     * Updates an existing individual
     * 
     * @param individual
     * @return
     * @throws ApiException
     */
    public Individual updateIndividual(final Individual individual) throws ApiException {

        if (individual == null) {
            throw new TechnicalException("individual is null");
        }

        logger.debug("Updating individual [{}]", individual.getId());
        try {
            updateIndividualBackend.updateIndividual(individual);
        } catch (NumberFormatException e) {
            throw new BadParameterFormatException(INDIVIDUAL_ID, individual.getId(), "a numeric value", e);
        }

        return getIndividual(individual.getId());
    }

    /**
     * Returns a list of individuals with the corresponding identification
     * document informations (find)
     * 
     * @param identificationType
     * @param identificationId
     * @return
     * @throws ApiException
     */
    public List<Individual> findIndividualsByIdentification(Long identificationType, String identificationId)
            throws ApiException {
        logger.debug("Finding individuals by identification with [{},{}]", identificationType, identificationId);
        return findIndividualsByIdentificationBackend.findIndividualsByIdentification(identificationType,
                identificationId);
    }
    
    public com.orange.apibss.party.model.obw.Individual postIndividual(com.orange.apibss.party.model.obw.Individual individual)
    	    throws ApiException, BscsInvalidIdException, BscsInvalidFieldException, CMSException
    	  {
    	   
    	try{
    		if (individual == null) {
    	      throw new TechnicalException("individual is null");
    	    }
    	    this.logger.debug("creating new individual [{}]", individual.getId());
    	      RelatedParty newRelatedParty = new RelatedParty();
    	      BSCSCustomer customer = this.createIndividualBackend.createIndividual(individual);
    	      if( null != individual.getRelatedParty() && individual.getRelatedParty().size()>0 ){
    	       newRelatedParty = (RelatedParty)individual.getRelatedParty().get(0);
    	      }
    	      newRelatedParty.setId(customer.getCustomerIDPub());
    	      List<RelatedParty> listRelatedParty = new ArrayList<RelatedParty>();
    	      listRelatedParty.add(newRelatedParty);
    	      individual.setRelatedParty(listRelatedParty);
    	      individual.setId(customer.getCustomerIDPub());

    	    return individual;
    	}catch(ApiException e){
    		throw e;
    	}
     }
    
    
    public com.orange.apibss.party.model.obw.Individual patchIndividual(com.orange.apibss.party.model.obw.Individual individual)
    	    throws ApiException
    	  {
    	    if (individual == null) {
    	      throw new TechnicalException("individual is null");
    	    }
    	    this.logger.debug("patch existing individual [{}]", individual.getId());
    	    com.orange.apibss.party.model.obw.Individual individualUp = null;
    	    try
    	    {
    	    	individualUp = this.createIndividualBackend.patchIndividual(individual);

    	    }catch(ApiException e){
        		throw e;
        	}
    	    return individualUp;
    	  }

}
