package com.orange.mea.apisi.eligibility.obw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.utils.InternationalMsisdnTool;
import com.orange.apibss.eligibility.model.EligibleOption;
import com.orange.mea.apisi.eligibility.service.EligibilityService;

@Service
@Primary
public class EligibilityServiceOBW extends EligibilityService {

    @Autowired
    private InternationalMsisdnTool internationalMsisdnTool;

    @Override
    public List<EligibleOption> findEligibleOptions(final String msisdn, final String productOfferingId,
            final String productSpecId) throws ApiException {
        // add international prefix
        String internationalMsisdn = internationalMsisdnTool.addInternationalPrefix(msisdn);
        return super.findEligibleOptions(internationalMsisdn, productOfferingId, productSpecId);
    }

}
