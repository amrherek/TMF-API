package com.orange.apibss.cucumber.productOrdering;


import org.springframework.beans.factory.annotation.Autowired;

import com.orange.apibss.cucumber.StepDefs;
import com.orange.apibss.cucumber.config.ProductOrderingProperties;
import com.orange.apibss.productOrdering.model.ProductOrder;
import com.orange.apibss.productOrdering.model.PublicKey;

import cucumber.api.java.en.When;

/**
 * Step defs for product ordering API
 * @author Thibault Duperron
 */
public class RelatedPublicKeyStepDefs  extends StepDefs {
    @Autowired
    private ProductOrderingSharedData productOrderingSharedData;
    @Autowired
    private ProductOrderingProperties productOrderingProperties;

    @When("^Use a related public key with (valid|invalid) msisdn$")
    public void use_a_related_public_key(final String type) {
        ProductOrder productOrder = productOrderingSharedData.getTestProductOrder();
        PublicKey publicKey = new PublicKey();

        switch (type) {
        case "valid":
            publicKey.setId(productOrderingProperties.getPublicKey().getValidMsisdn());
            break;
        case "invalid":
            publicKey.setId(productOrderingProperties.getPublicKey().getInvalidMsisdn());
            break;
        default:
            throw new IllegalArgumentException("Can't use key " + type);
        }

        publicKey.setName("customerMSISDN");
        productOrder.addRelatedPublicKeyItem(publicKey);
    }

}
