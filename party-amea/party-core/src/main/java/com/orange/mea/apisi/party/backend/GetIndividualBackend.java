package com.orange.mea.apisi.party.backend;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.party.model.Individual;

public interface GetIndividualBackend {

    /**
     * UC26: Get individual by id
     * 
     * @param id
     * @return
     * @throws ApiException
     */
    Individual getIndividual(String id) throws ApiException;

}
