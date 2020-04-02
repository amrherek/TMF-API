package com.orange.apibss.cucumber.productInventory;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.orange.apibss.cucumber.Arguments;
import com.orange.apibss.cucumber.StepDefs;
import com.orange.apibss.cucumber.config.ApibssProperties;
import com.orange.apibss.cucumber.config.ProductInventoryProperties;
import com.orange.apibss.cucumber.config.productInventory.Msisdn;
import com.orange.apibss.productInventory.model.Product;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Find products step defs for OTN
 * 
 * @author Stéphanie Gerbaud
 */
public class FindProductOTNStepDefs extends StepDefs{
    @Autowired
    private ApibssProperties apibssProperties;
    @Autowired
    private ProductInventorySharedData productInventorySharedData;

    @When("^Use a MSISDN (with|without) faf")
    public void use_msisdn(final String type) {
        final Arguments arg = Arguments.MSISDN;
        Msisdn properties = apibssProperties.getProductInventory().getMsisdn();
        switch (type) {
        case "with":
            sharedData.getArguments().put(arg, properties.getWithFaf());
            break;
        case "without":
            sharedData.getArguments().put(arg, properties.getWithoutFaf());
            break;
        default:
            throw new IllegalArgumentException("Can't use key " + type + " on \"Use a MSISDN (.*) faf\" step");
        }
    }

    @When("^Use a MSISDN (with|without) transfer data")
    public void use_msisdn_transferData(final String type) {
        final Arguments arg = Arguments.MSISDN;
        Msisdn properties = apibssProperties.getProductInventory().getMsisdn();
        switch (type) {
        case "with":
            sharedData.getArguments().put(arg, properties.getWithTransferData());
            break;
        case "without":
            sharedData.getArguments().put(arg, properties.getWithoutTransferData());
            break;
        default:
            throw new IllegalArgumentException(
                    "Can't use key " + type + " on \"Use a MSISDN (.*) transfer data\" step");
        }
    }

    @When("^Use a MSISDN (with|without) transfer credit")
    public void use_msisdn_transferCredit(final String type) {
        final Arguments arg = Arguments.MSISDN;
        Msisdn properties = apibssProperties.getProductInventory().getMsisdn();
        switch (type) {
        case "with":
            sharedData.getArguments().put(arg, properties.getWithTransferCredit());
            break;
        case "without":
            sharedData.getArguments().put(arg, properties.getWithoutTransferCredit());
            break;
        default:
            throw new IllegalArgumentException(
                    "Can't use key " + type + " on \"Use a MSISDN (.*) transfer credit\" step");
        }
    }

    @When("^Filter product with faf")
    public void filter_with_faf() throws Throwable {
        final Arguments arg = Arguments.productSpecificationId;
        sharedData.getArguments().put(arg, "faf");
    }

    @Then("^Get product with faf$")
    public void get_product_with_faf() throws Throwable {
        ProductInventoryProperties properties = apibssProperties.getProductInventory();
        checkNoException();
        assertThat(productInventorySharedData.getProducts()).isNotNull();
        List<Product> result = properties.getProductFaf();
        assertThat(productInventorySharedData.getProducts()).containsOnly(result.toArray(new Product[result.size()]));
    }

    @Then("^Get product with transfer data$")
    public void get_product_with_transferData() throws Throwable {
        ProductInventoryProperties properties = apibssProperties.getProductInventory();
        checkNoException();
        assertThat(productInventorySharedData.getProducts()).isNotNull();
        List<Product> result = properties.getProductTransferData();
        assertThat(productInventorySharedData.getProducts()).containsOnly(result.toArray(new Product[result.size()]));
    }

    @Then("^Get product with transfer credit$")
    public void get_product_with_transferCredit() throws Throwable {
        ProductInventoryProperties properties = apibssProperties.getProductInventory();
        checkNoException();
        assertThat(productInventorySharedData.getProducts()).isNotNull();
        List<Product> result = properties.getProductTransferCredit();
        assertThat(productInventorySharedData.getProducts()).containsOnly(result.toArray(new Product[result.size()]));
    }

    @Then("^Get product with data bundle$")
    public void get_product_with_dataBundle() throws Throwable {
        ProductInventoryProperties properties = apibssProperties.getProductInventory();
        checkNoException();
        assertThat(productInventorySharedData.getProducts()).isNotNull();
        List<Product> result = properties.getProductDataBundle();
        assertThat(productInventorySharedData.getProducts()).containsOnly(result.toArray(new Product[result.size()]));
    }

}
