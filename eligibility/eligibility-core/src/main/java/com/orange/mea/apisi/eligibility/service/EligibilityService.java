package com.orange.mea.apisi.eligibility.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.eligibility.model.EligibleContract;
import com.orange.apibss.eligibility.model.EligibleOption;
import com.orange.mea.apisi.eligibility.backend.FindEligibleContractsBackend;
import com.orange.mea.apisi.eligibility.backend.FindEligibleOptionsBackend;

/**
 * Eligibility service
 *
 * @author Thibault Duperron
 *
 */
@Service
public class EligibilityService {

    @Autowired
    private List<FindEligibleOptionsBackend> findEligibleOptionsBackends;

    @Autowired
    private FindEligibleContractsBackend findEligibleContractsBackend;

    /**
     *
     * @param msisdn
     * @param productOfferingId
     * @param productSpecId
     * @return
     * @throws ApiException
     */
    public List<EligibleOption> findEligibleOptions(final String msisdn, final String productOfferingId,
            final String productSpecId) throws ApiException {
        List<EligibleOption> result = new ArrayList<>();
        for (FindEligibleOptionsBackend findEligibleOptionsBackend : findEligibleOptionsBackends) {
            result.addAll(findEligibleOptionsBackend.findEligibleOptions(msisdn, productSpecId));
        }
        return filterByProductOfferingId(result, productOfferingId);
    }

    /**
     * Filter eligible options by product offering id
     * @param eligibleOptions
     * @param productOfferingId
     * @return
     */
    private List<EligibleOption> filterByProductOfferingId(final List<EligibleOption> eligibleOptions, final String productOfferingId){
        final List<EligibleOption> result = new ArrayList<>();
        if(StringUtils.isNotBlank(productOfferingId)){
            for (final EligibleOption eligibleOption : eligibleOptions) {
                if(StringUtils.equals(productOfferingId, eligibleOption.getProductOfferingId())){
                    result.add(eligibleOption);
                }
            }
        } else {
            return eligibleOptions;
        }
        return result;
    }

    /**
     * Find eligible offers for migration
     * 
     * @param msisdn
     * @return
     * @throws ApiException
     */
    public EligibleContract findEligibleContract(String msisdn) throws ApiException {
        return findEligibleContractsBackend.getAvailableOfferForMigration(msisdn);
    }
}
