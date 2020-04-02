package com.orange.apibss.cucumber.billingAccount;

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

import com.orange.apibss.billingAccount.model.BillingAccount;
import com.orange.apibss.cucumber.Arguments;
import com.orange.apibss.cucumber.StepDefs;
import com.orange.apibss.cucumber.config.ApibssProperties;
import com.orange.apibss.cucumber.config.BillingAccountProperties;
import com.orange.apibss.cucumber.config.Msisdn;
import com.orange.apibss.cucumber.config.billingAccount.BillingAccountId;
import com.orange.apibss.cucumber.config.billingAccount.PartyId;
import com.orange.apibss.cucumber.tools.Tools;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Step defs for find / get billing account API
 *
 * @author Stéphanie Gerbaud
 */
public class FindBillingAccountStepDefs extends StepDefs {

    @Autowired
    private ApibssProperties apibssProperties;
    @Autowired
    private BillingAccountProperties billingAccountProperties;
    @Autowired
    private BillingAccountSharedData billingAccountSharedData;
    @Autowired
    private RestTemplate restTemplate;

    @When("^Use a billing account id (valid|invalid|bad format)")
    public void use_billing_account_id(final String type) {
        final Arguments arg = Arguments.billingAccountId;
        BillingAccountId properties = billingAccountProperties.getBillingAccountId();
        switch (type) {
        case "valid":
            sharedData.getArguments().put(arg, properties.getValid());
            break;
        case "invalid":
            sharedData.getArguments().put(arg, properties.getInvalid());
            break;
        case "bad format":
            sharedData.getArguments().put(arg, properties.getBadFormat());
            break;
        default:
            throw new IllegalArgumentException("Can't use key " + type + " on \"Use a billing account id (.*)\" step");
        }
    }

    @When("^Use a MSISDN (valid|invalid|bad format) for billing account")
    public void use_msisdn(final String type) {
        final Arguments arg = Arguments.MSISDN;
        Msisdn properties = billingAccountProperties.getMsisdn();
        switch (type) {
        case "valid":
            sharedData.getArguments().put(arg, properties.getValid());
            break;
        case "invalid":
            sharedData.getArguments().put(arg, properties.getInvalid());
            break;
        case "bad format":
            sharedData.getArguments().put(arg, properties.getBadFormat());
            break;
        default:
            throw new IllegalArgumentException(
                    "Can't use key " + type + " on \"Use a MSISDN (.*) for billing account\" step");
        }
    }

    @When("^Use a party id (valid|invalid|with several BAs|bad format) for billing account")
    public void use_party_id(final String type) {
        final Arguments arg = Arguments.partyId;
        PartyId properties = billingAccountProperties.getPartyId();
        switch (type) {
        case "valid":
            sharedData.getArguments().put(arg, properties.getValid());
            break;
        case "invalid":
            sharedData.getArguments().put(arg, properties.getInvalid());
            break;
        case "with several BAs":
            sharedData.getArguments().put(arg, properties.getWithSeveralBillingAccounts());
            break;
        case "bad format":
            sharedData.getArguments().put(arg, properties.getBadFormat());
            break;
        default:
            throw new IllegalArgumentException(
                    "Can't use key " + type + " on \"Use a party id (.*) for billing account\" step");
        }
    }

    @When("^Refer to the billing account$")
    public void refer_to_the_billing_account() throws Throwable {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder
                    .fromHttpUrl(apibssProperties.getBillingAccountUrl() + "/billingAccount");
            for (Arguments key : sharedData.getArguments().keySet()) {
                String value = sharedData.getArguments().get(key);
                switch (key) {
                case billingAccountId:
                    builder.pathSegment(value);
                    break;
                default:
                    throw new IllegalArgumentException(
                            "Can't use key " + key + " on \"Refer to the billing account\" step");
                }
            }
            System.out.println(builder.build().toUri());
            HttpHeaders headers = new HttpHeaders();
            Tools.addHeaders(apibssProperties.getHeaders(), headers);
            ResponseEntity<BillingAccount> response = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET,
                    new HttpEntity<>(headers), BillingAccount.class);
            billingAccountSharedData.setBillingAccount(response.getBody());
        } catch (HttpStatusCodeException e) {
            sharedData.setException(e);
        }
    }

    @When("^Refer to billing accounts$")
    public void refer_to_billing_accounts() throws Throwable {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder
                    .fromHttpUrl(apibssProperties.getBillingAccountUrl() + "/billingAccount");
            for (Arguments key : sharedData.getArguments().keySet()) {
                String value = sharedData.getArguments().get(key);
                switch (key) {
                case MSISDN:
                    builder.queryParam("publicKey", value);
                    break;
                case partyId:
                    builder.queryParam("party.id", value);
                    break;
                default:
                    throw new IllegalArgumentException(
                            "Can't use key " + key + " on \"Refer to the billing account\" step");
                }
            }
            System.out.println(builder.build().toUri());
            HttpHeaders headers = new HttpHeaders();
            Tools.addHeaders(apibssProperties.getHeaders(), headers);
            ResponseEntity<BillingAccount[]> response = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET,
                    new HttpEntity<>(headers), BillingAccount[].class);
            billingAccountSharedData.setBillingAccounts(Arrays.asList(response.getBody()));
        } catch (HttpStatusCodeException e) {
            sharedData.setException(e);
        }
    }

    @Then("^Get (valid) billing account$")
    public void get_billing_account(String type) throws Throwable {
        BillingAccountProperties properties = billingAccountProperties;
        checkNoException();
        assertThat(billingAccountSharedData.getBillingAccount()).isNotNull();
        final BillingAccount result;
        switch (type) {
        case "valid":
            // first valid BA
            result = properties.getValid().get(0);
            break;
        default:
            throw new IllegalArgumentException("Can't use " + type + "for get (.*) billing account");
        }
        assertThat(billingAccountSharedData.getBillingAccount()).isEqualTo(result);
    }

    @Then("^Get (valid|several) billing accounts$")
    public void get_billing_accounts(String type) throws Throwable {
        BillingAccountProperties properties = billingAccountProperties;
        checkNoException();
        assertThat(billingAccountSharedData.getBillingAccounts()).isNotNull();
        final List<BillingAccount> result;
        switch (type) {
        case "valid":
            // main offer is the first product
            result = properties.getValid();
            break;
        case "several":
            // main offer is the first product
            result = properties.getSeveral();
            break;
        default:
            throw new IllegalArgumentException("Can't use " + type + "for get (.*) billing account");
        }
        assertThat(billingAccountSharedData.getBillingAccounts())
                .containsOnly(result.toArray(new BillingAccount[result.size()]));
    }

    @Then("^Get empty BA result$")
    public void get_empty_result() throws Throwable {
        checkNoException();
        assertThat(billingAccountSharedData.getBillingAccounts()).isNotNull();
        assertThat(billingAccountSharedData.getBillingAccounts()).isEmpty();
    }

}