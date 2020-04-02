package com.orange.apibss.cucumber.eligibility;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
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
import com.orange.apibss.cucumber.SharedData;
import com.orange.apibss.cucumber.config.ApibssProperties;
import com.orange.apibss.cucumber.config.EligibilityProperties;
import com.orange.apibss.cucumber.tools.Tools;
import com.orange.apibss.eligibility.model.EligibleContract;
import com.orange.apibss.eligibility.model.EligibleOption;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * @author Thibault Duperron
 */
public class EligibilityStepDefs {

    @Autowired
    private ApibssProperties apibssProperties;
    @Autowired
    private EligibilityProperties eligibilityProperties;
    @Autowired
    private EligibilitySharedData eligibilitySharedData;
    @Autowired
    private SharedData sharedData;
    @Autowired
    private RestTemplate restTemplate;

    @When("^Use a MSISDN (valid|unknown|invalid) for EL$")
    public void use_msisdn(final String type) {
        final Arguments arg = Arguments.MSISDN;
        switch (type) {
            case "valid":
                sharedData.getArguments().put(arg, eligibilityProperties.getMsisdnValid());
                break;
            case "unknown":
                sharedData.getArguments().put(arg, eligibilityProperties.getMsisdnUnknown());
                break;
            case "invalid":
                sharedData.getArguments().put(arg, eligibilityProperties.getMsisdnInvalid());
                break;
            default:
                throw new IllegalArgumentException("Can't use key " + type + " on \"Use a MSISDN (.*) for EL\" step");
        }
    }

    @When("^Use a MSISDN valid without prefix for EL")
    public void use_msisdn_without_prefix() {
        final Arguments arg = Arguments.MSISDN;
        String msisdn = eligibilityProperties.getMsisdnValid();
        String localMsisdn = msisdn.substring(3);
        sharedData.getArguments().put(arg, localMsisdn);
    }

    @When("^Use a product offering id for EL$")
    public void use_product_offering_id() {
        sharedData.getArguments().put(Arguments.productOfferingId, eligibilityProperties.getProductOfferingId());
    }

    @When("^Use (.*) product specification id for EL$")
    public void use_product_spec_id(String productSpecId) {
        sharedData.getArguments().put(Arguments.productSpecificationId, productSpecId);
    }

    @When("^Request for eligible options$")
    public void request_eligible_options() {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apibssProperties.getEligibilityUrl() + "/eligibleOptions");
            for (Arguments key : sharedData.getArguments().keySet()) {
                String value = sharedData.getArguments().get(key);
                switch (key) {
                    case productOfferingId:
                        builder.queryParam(key.name(), value);
                        break;
                    case MSISDN:
                        builder.queryParam("publicKey", value);
                        break;
                case productSpecificationId:
                    builder.queryParam("productSpecification.id", value);
                    break;
                    default:
                        throw new IllegalArgumentException("Can't use key " + key + " on \"Refer to the main offer\" step");
                }
            }
            HttpHeaders headers = new HttpHeaders();
            Tools.addHeaders(apibssProperties.getHeaders(), headers);
            ResponseEntity<EligibleOption[]> response = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET,
                    new HttpEntity<>(headers), EligibleOption[].class);
            eligibilitySharedData.setEligibleOptions(Arrays.asList(response.getBody()));
        } catch (HttpStatusCodeException e) {
            sharedData.setException(e);
        }
    }

    @When("^Request for eligible contract$")
    public void request_eligible_contract() {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder
                    .fromHttpUrl(apibssProperties.getEligibilityUrl() + "/eligibleContract");
            for (Arguments key : sharedData.getArguments().keySet()) {
                String value = sharedData.getArguments().get(key);
                switch (key) {
                case productOfferingId:
                    builder.queryParam(key.name(), value);
                    break;
                case MSISDN:
                    builder.queryParam("publicKey", value);
                    break;
                default:
                    throw new IllegalArgumentException("Can't use key " + key + " on \"Refer to the main offer\" step");
                }
            }
            HttpHeaders headers = new HttpHeaders();
            Tools.addHeaders(apibssProperties.getHeaders(), headers);
            ResponseEntity<EligibleContract> response = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET,
                    new HttpEntity<>(headers), EligibleContract.class);
            eligibilitySharedData.setEligibleContract(response.getBody());
        } catch (HttpStatusCodeException e) {
            sharedData.setException(e);
        }
    }

    @Then("^Response contain (all|filtered|no|transfer data|emergency voice|data bundle|emergency credit) eligible option$")
    public void response_contain_eligible_option(final String type) {
        assertThat(sharedData.getException()).isNull();
        assertThat(eligibilitySharedData.getEligibleOptions()).isNotNull();
        List<EligibleOption> result;
        switch (type) {
            case "all":
                result = eligibilityProperties.getEligibleOptions();
                break;
            case "filtered":
                result = eligibilityProperties.getEligibleOptionsFiltered();
                    break;
            case "no":
                result = new ArrayList<>();
                break;
            case "transfer data":
                result = eligibilityProperties.getTransferDataEligibleOptions();
                break;
            case "emergency voice":
                result = eligibilityProperties.getEmergencyVoiceEligibleOptions();
                break;
            case "data bundle":
                result = eligibilityProperties.getDataBundleEligibleOptions();
                break;
            case "emergency credit":
                result = eligibilityProperties.getEmergencyCreditEligibleOptions();
                break;
            default:
                throw new IllegalArgumentException("Can't use key " + type + " on \"Response contain (.*) eligible option\" step");
        }

        assertThat(eligibilitySharedData.getEligibleOptions())
                .containsOnly(result.toArray(new EligibleOption[result.size()]));
    }

    @Then("^Response contain eligible contract$")
    public void response_contain_eligible_contract() {
        assertThat(sharedData.getException()).isNull();
        assertThat(eligibilitySharedData.getEligibleContract()).isNotNull();
        EligibleContract result = eligibilityProperties.getEligibleContract();
        if (result != null) {
            assertThat(eligibilitySharedData.getEligibleContract()).isEqualTo(result);
        } else {
            assertThat(eligibilitySharedData.getEligibleContract().getProductOffering()).isNull();
        }
    }
}
