package com.orange.mea.apisi.eligibility.backend;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.eligibility.model.EligibleContract;

public interface FindEligibleContractsBackend {

    /**
     * Get offers for migration
     * 
     * @param msisdn
     * @return
     * @throws ApiException
     */
    EligibleContract getAvailableOfferForMigration(String msisdn) throws ApiException;

}
