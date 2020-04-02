package com.orange.mea.apisi.eligibility.obw.backend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.orange.apibss.eligibility.model.EligibleOption;
import com.orange.apibss.eligibility.model.ProductSpecification;
import com.orange.mea.apisi.eligibility.constants.EligibilityConstants;
import com.orange.mea.apisi.obw.webservice.TPricePlanDto2;
import com.orange.mea.apisi.obw.webservice.TQueryAllPricePlanResponse;

@Component
public class EligibilityZteInTransformerOBW {

    /**
     * 
     * @param response
     * @return
     */
    public List<EligibleOption> transform(TQueryAllPricePlanResponse response) {
        List<EligibleOption> result = new ArrayList<>();
        if (response != null && response.getPricePlanDtoList() != null
                && response.getPricePlanDtoList().getPricePlanDto() != null) {
            // TODO: filter results to only keep bundle of data/voice/SMS
            for (TPricePlanDto2 pricePlan : response.getPricePlanDtoList().getPricePlanDto()) {
                EligibleOption option = new EligibleOption();
                option.setProductOfferingId(pricePlan.getPricePlanIndex());
                option.setProductOfferingName(pricePlan.getPricePlanName());

                // TODO: fill productSpecId, depends on pricePlan.getPricePlanType() ?
                ProductSpecification productSpecification = new ProductSpecification();
                productSpecification.setId(EligibilityConstants.DATA_BUNDLE);
                option.setProductSpecification(productSpecification);

                result.add(option);
            }
        }
        return result;
    }

}
