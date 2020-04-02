package com.orange.mea.apisi.eligibility.obw.backend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.apibss.eligibility.model.EligibleOption;
import com.orange.mea.apisi.eligibility.backend.FindEligibleOptionsBackend;
import com.orange.mea.apisi.eligibility.constants.EligibilityConstants;
import com.orange.mea.apisi.obw.webservice.EmergencyWebService;
import com.orange.mea.apisi.obw.webservice.emergency.TDebitAuthResponse;

@Component
public class EligibilityEmergencyWSBackendOBW implements FindEligibleOptionsBackend {

    @Value("${emergencyCredit.activate}")
    private boolean activated;

    @Autowired
    private EmergencyWebService emergencyWebService;

    @Autowired
    private EligibilityEmergencyWSTransformerOBW transformer;

    @Override
    public List<EligibleOption> findEligibleOptions(String msisdn, String productSpecId) throws TechnicalException {
        List<EligibleOption> res = new ArrayList<>();
        if (!activated) {
            return res;
        }
        if (productSpecId == null || EligibilityConstants.EMERGENCY_CREDIT.equals(productSpecId)) {
            TDebitAuthResponse response = emergencyWebService.debitAuth(msisdn);
            EligibleOption emergencyCredit = transformer.transform(response);
            if (emergencyCredit != null) {
                res.add(emergencyCredit);
            }
        }
        return res;
    }

}
