package com.orange.apibss.cucumber.productInventory;

import org.springframework.beans.factory.annotation.Autowired;

import com.orange.apibss.cucumber.Arguments;
import com.orange.apibss.cucumber.StepDefs;
import com.orange.apibss.cucumber.config.ApibssProperties;
import com.orange.apibss.cucumber.config.productInventory.Msisdn;

import cucumber.api.java.en.When;

/**
 * Find products step defs for OBW
 */
public class FindProductOBWStepDefs extends StepDefs{
    @Autowired
    private ApibssProperties apibssProperties;


    @When("^Use a MSISDN with contracts without prefix for PI")
    public void use_msisdn() {
        final Arguments arg = Arguments.MSISDN;
        Msisdn properties = apibssProperties.getProductInventory().getMsisdn();
        String msisdn = properties.getWithContracts();
        String localMsisdn = msisdn.substring(3);
        sharedData.getArguments().put(arg, localMsisdn);
    }

    @When("^Use a MSISDN (with|without) data bundle")
    public void use_msisdn_dataBundle(final String type) {
        final Arguments arg = Arguments.MSISDN;
        Msisdn properties = apibssProperties.getProductInventory().getMsisdn();
        switch (type) {
        case "with":
            sharedData.getArguments().put(arg, properties.getWithDataBundle());
            break;
        case "without":
            sharedData.getArguments().put(arg, properties.getWithoutDataBundle());
            break;
        default:
            throw new IllegalArgumentException("Can't use key " + type + " on \"Use a MSISDN (.*) data bundle\" step");
        }
    }
}
