package com.orange.mea.apisi.party.backend;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.party.model.Individual;

public interface UpdateIndividualBackend {

    /**
     * UM27: partially update individual
     * 
     * @param individual
     * @throws ApiException
     */
    void updateIndividual(Individual individual) throws ApiException;

}
