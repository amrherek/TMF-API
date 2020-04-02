package com.orange.mea.apisi.eligibility.backend;

import java.util.List;

import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.apibss.eligibility.model.EligibleOption;

public interface FindEligibleOptionsBackend {

    /**
     * Find all eligible options: UC102
     * 
     * @param msisdn
     * @param productSpecId
     * @return
     * @throws TechnicalException
     */
    List<EligibleOption> findEligibleOptions(final String msisdn, final String productSpecId) throws TechnicalException;

}
