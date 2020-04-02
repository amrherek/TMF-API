package com.orange.apibss.cucumber.productOrdering;

import org.springframework.beans.factory.annotation.Autowired;

import com.orange.apibss.cucumber.StepDefs;
import com.orange.apibss.cucumber.config.ProductOrderingProperties;
import com.orange.apibss.cucumber.config.productOrdering.ProductOrderCommonProperties;

/**
 * Created by czfd3494 on 13/02/2017.
 */
public class ProductOrderingBaseStepDefs extends StepDefs {

    @Autowired
    private ProductOrderingProperties productOrderingProperties;


    /**
     * Find properties from usecase
     * @param usecase
     * @return
     */
    protected ProductOrderCommonProperties getProperties(final String usecase){
        switch (usecase){
            case "friend and family":
                return productOrderingProperties.getFriendAndFamily();
            case "um13":
                return productOrderingProperties.getUm13();
            case "um7":
                return productOrderingProperties.getUm7();
            case "emergency credit":
                return productOrderingProperties.getEmergencyCredit();
            case "data bundle":
                return productOrderingProperties.getDataBundle();
            default:
                throw new IllegalArgumentException("Can't use key " + usecase);
        }
    }
}
