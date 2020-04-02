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
import com.orange.mea.apisi.obw.webservice.TQueryAllPricePlanResponse;
import com.orange.mea.apisi.obw.webservice.ZteWebService;

@Component
public class EligibilityZteInBackendOBW implements FindEligibleOptionsBackend {

    @Value("${queryAllPricePlan.productCode}")
    private String productCode;

    @Autowired
    private ZteWebService webService;

    @Autowired
    private EligibilityZteInTransformerOBW transformer;

    @Override
    public List<EligibleOption> findEligibleOptions(String msisdn, String productSpecId) throws TechnicalException {
        if (productSpecId == null || EligibilityConstants.DATA_BUNDLE.equals(productSpecId)) {
            TQueryAllPricePlanResponse pricePlans = webService.queryAllPricePlan(productCode);
            return transformer.transform(pricePlans);
        }
        return new ArrayList<>();
    }

}
