package com.orange.mea.apisi.party.backend;

import java.util.List;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.party.model.Individual;
import com.orange.mea.apisi.party.beans.IndividualFilteringCriteria;

public interface FindIndividualsByCriteriaBackend {

    /**
     * UC91: find individual by criteria
     * 
     * @param criteria
     * @return
     * @throws ApiException
     */
    List<Individual> findIndividualsByCriteria(IndividualFilteringCriteria criteria)
            throws ApiException;

}
