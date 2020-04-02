package com.orange.apibss.cucumber.productCatalog;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.orange.apibss.cucumber.Arguments;
import com.orange.apibss.cucumber.StepDefs;
import com.orange.apibss.cucumber.config.ApibssProperties;
import com.orange.apibss.cucumber.config.Id;
import com.orange.apibss.cucumber.config.ProductCatalogProperties;
import com.orange.apibss.cucumber.tools.Tools;
import com.orange.apibss.productCatalog.model.ProductOffering;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class FindProductCatalogStepDefs extends StepDefs {

    @Autowired
    private ApibssProperties apibssProperties;

    @Autowired
    private ProductCatalogSharedData productCatalogSharedData;

    @Autowired
    private ProductCatalogProperties productCatalogProperties;

    @Autowired
    private RestTemplate restTemplate;

    @When("^Use a rate plan (valid|invalid) for productCatalog")
    public void use_ratePlan(final String type) {
        final Arguments arg = Arguments.offerProductOfferingId;
        Id properties = productCatalogProperties.getOfferProductOfferingId();
        switch (type) {
        case "valid":
            sharedData.getArguments().put(arg, properties.getValid());
            break;
        case "invalid":
            sharedData.getArguments().put(arg, properties.getInvalid());
            break;
        default:
            throw new IllegalArgumentException(
                    "Can't use key " + type + " on \"Use a rate plan (.*) for productCatalog\" step");
        }
    }

    @When("^Use a productOffering id (valid|invalid|with bad format) for productCatalog")
    public void use_productOffering(final String type) {
        final Arguments arg = Arguments.productOfferingId;
        Id properties = productCatalogProperties.getProductOffering();
        switch (type) {
        case "valid":
            sharedData.getArguments().put(arg, properties.getValid());
            break;
        case "invalid":
            sharedData.getArguments().put(arg, properties.getInvalid());
            break;
        case "with bad format":
            sharedData.getArguments().put(arg, properties.getBadFormat());
            break;
        default:
            throw new IllegalArgumentException(
                    "Can't use key " + type + " on \"Use a productOffering id (.*) for productCatalog\" step");
        }
    }

    @When("^Use a (migration|compatibility|configuration) type")
    public void use_type(final String type) {
        final Arguments arg = Arguments.operationType;
        switch (type) {
        case "migration":
            sharedData.getArguments().put(arg, "migrationFrom");
            break;
        case "compatibility":
            sharedData.getArguments().put(arg, "compatibilityWith");
            break;
        case "configuration":
            sharedData.getArguments().put(arg, "parameterConfiguration");
            break;
        default:
            throw new IllegalArgumentException("Can't use key " + type + " on \"Use a (.*) type\" step");
        }
    }

    @When("^Use a (.*) productSpecification.id")
    public void use_productSpecId(final String productSpecId) {
        final Arguments arg = Arguments.productSpecificationId;
        sharedData.getArguments().put(arg, productSpecId);
    }

    @When("^Refer to productOfferings$")
    public void refer_to_productOfferings() throws Throwable {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder
                    .fromHttpUrl(apibssProperties.getProductCatalogUrl() + "/productOffering");
            for (Arguments key : sharedData.getArguments().keySet()) {
                String value = sharedData.getArguments().get(key);
                switch (key) {
                case offerProductOfferingId:
                    builder.queryParam("offerProductOfferingId", value);
                    break;
                case operationType:
                    builder.queryParam("operationType", value);
                    break;
                case productSpecificationId:
                    builder.queryParam("productSpecification.id", value);
                    break;
                case ratingType:
                    builder.queryParam("ratingType", value);
                    break;
                default:
                    throw new IllegalArgumentException(
                            "Can't use key " + key + " on \"Refer to productOfferings\" step");
                }
            }
            System.out.println(builder.build().toUri());
            HttpHeaders headers = new HttpHeaders();
            Tools.addHeaders(apibssProperties.getHeaders(), headers);
            ResponseEntity<ProductOffering[]> response = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET,
                    new HttpEntity<>(headers),
                    ProductOffering[].class);
            productCatalogSharedData.setProductOfferings(Arrays.asList(response.getBody()));
        } catch (HttpStatusCodeException e) {
            sharedData.setException(e);
        }
    }

    @When("^Refer to the productOffering$")
    public void refer_to_the_productOffering() throws Throwable {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder
                    .fromHttpUrl(apibssProperties.getProductCatalogUrl() + "/productOffering");
            for (Arguments key : sharedData.getArguments().keySet()) {
                String value = sharedData.getArguments().get(key);
                switch (key) {
                case productOfferingId:
                    builder.pathSegment(value);
                    break;
                case operationType:
                    builder.queryParam("operationType", value);
                    break;
                default:
                    throw new IllegalArgumentException(
                            "Can't use key " + key + " on \"Refer to the productOffering\" step");
                }
            }
            System.out.println(builder.build().toUri());
            HttpHeaders headers = new HttpHeaders();
            Tools.addHeaders(apibssProperties.getHeaders(), headers);
            ResponseEntity<ProductOffering> response = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET,
                    new HttpEntity<>(headers), ProductOffering.class);
            productCatalogSharedData.setProductOffering(response.getBody());
        } catch (HttpStatusCodeException e) {
            sharedData.setException(e);
        }
    }

    @Then("^Get productOfferings for (migration|compatibility|faf configuration)$")
    public void get_productOfferings(final String type) throws Throwable {
        checkNoException();
        assertThat(productCatalogSharedData.getProductOfferings()).isNotNull();
        List<ProductOffering> result = null;
        switch (type) {
        case "migration":
            result = productCatalogProperties.getProductOfferingsForMigration();
            break;
        case "compatibility":
            result = productCatalogProperties.getProductOfferingsForCompatibility();
            break;
        case "faf configuration":
            result = productCatalogProperties.getProductOfferingsForFafConfiguration();
            break;
        default:
            throw new IllegalArgumentException("Can't use key " + type + " on \"Get productOffering for (.*)\" step");
        }

        if (result == null) {
            // empty result
            assertThat(productCatalogSharedData.getProductOfferings()).isEmpty();
        } else {
            assertThat(productCatalogSharedData.getProductOfferings())
                .containsOnly(result.toArray(new ProductOffering[result.size()]));
        }
    }

    @Then("^Get (valid) productOffering$")
    public void get_productOffering(String type) throws Throwable {
        checkNoException();
        assertThat(productCatalogSharedData.getProductOffering()).isNotNull();
        final ProductOffering result;
        switch (type) {
        case "valid":
            result = productCatalogProperties.getProductOfferingForConfiguration();
            break;
        default:
            throw new IllegalArgumentException("Can't use " + type + "for get (.*) productOffering");
        }
        assertThat(productCatalogSharedData.getProductOffering()).isEqualTo(result);
    }

    @Then("^Get empty productOffering result$")
    public void get_empty_productOffering_result() throws Throwable {
        checkNoException();
        assertThat(productCatalogSharedData.getProductOfferings()).isNotNull();
        assertThat(productCatalogSharedData.getProductOfferings()).isEmpty();
    }

}
