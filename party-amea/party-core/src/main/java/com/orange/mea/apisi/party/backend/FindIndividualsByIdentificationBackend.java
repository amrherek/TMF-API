package com.orange.mea.apisi.party.backend;

import java.util.List;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.party.model.Individual;

public interface FindIndividualsByIdentificationBackend {

    /**
     * UC91: find individuals based on identification documents
     * 
     * @param identificationType
     * @param identificationId
     * @return
     * @throws ApiException
     */
    List<Individual> findIndividualsByIdentification(Long identificationType, String identificationId)
            throws ApiException;

}
