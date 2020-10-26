package com.orange.mea.apisi.party.backend;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.party.model.obw.Individual;

public abstract interface CreateIndividualBackend
{
  public abstract String createIndividual(Individual paramIndividual)
    throws ApiException;
}
