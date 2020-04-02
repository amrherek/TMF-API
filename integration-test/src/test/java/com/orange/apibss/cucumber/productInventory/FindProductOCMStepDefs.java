package com.orange.apibss.cucumber.productInventory;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.orange.apibss.cucumber.Arguments;
import com.orange.apibss.cucumber.StepDefs;
import com.orange.apibss.cucumber.config.ApibssProperties;
import com.orange.apibss.cucumber.config.productInventory.Msisdn;
import com.orange.apibss.productInventory.model.Product;
import com.orange.apibss.productInventory.model.RelatedParty;
import com.orange.apibss.productInventory.model.Role;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Find products step defs for OCM
 * @author Thibault Duperron
 */
public class FindProductOCMStepDefs extends StepDefs{
    @Autowired
    private ApibssProperties apibssProperties;
    @Autowired
    private ProductInventorySharedData productInventorySharedData;


    @When("^Use a MSISDN with tutor")
    public void use_msisdn() {
        final Arguments arg = Arguments.MSISDN;
        Msisdn properties = apibssProperties.getProductInventory().getMsisdn();
        sharedData.getArguments().put(arg, properties.getWithTutor());
    }

    @Then("^Get product with tutor")
    public void get_proudct_with_tutor(){
        assertThat(sharedData.getException()).isNull();
        List<Product> call = productInventorySharedData.getProducts();
        assertThat(call).isNotNull();
        Product product = call.get(0);
        RelatedParty relatedParty = new RelatedParty();
        relatedParty.setRole(Role.TUTOR);
        assertThat(product.getRelatedParty()).contains(relatedParty);
    }
}
