package com.orange.apibss.cucumber.payment;

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
import com.orange.apibss.cucumber.config.PaymentProperties;
import com.orange.apibss.cucumber.tools.Tools;
import com.orange.apibss.payment.model.Payment;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Step defs for payment API
 *
 * @author Stéphanie Gerbaud
 */
public class PaymentStepDefs extends StepDefs {

    @Autowired
    private ApibssProperties apibssProperties;
    @Autowired
    private PaymentProperties paymentProperties;
    @Autowired
    private PaymentSharedData paymentSharedData;
    @Autowired
    private RestTemplate restTemplate;

    @When("^Use a payment id (valid|invalid|bad format)")
    public void use_payment_id(final String type) {
        final Arguments arg = Arguments.paymentId;
        Id properties = paymentProperties.getPaymentId();
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
            throw new IllegalArgumentException("Can't use key " + type + " on \"Use a payment id (.*)\" step");
        }
    }

    @When("^Use a payment correlatorId (valid|invalid|bad format)")
    public void use_payment_correlatorId(final String type) {
        final Arguments arg = Arguments.paymentCorrelatorId;
        Id properties = paymentProperties.getPaymentId();
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
                    "Can't use key " + type + " on \"Use a payment correlatorId (.*)\" step");
        }
    }

    @When("^Use a party id (valid|invalid|bad format) for payment")
    public void use_party_id(final String type) {
        final Arguments arg = Arguments.partyId;
        Id properties = paymentProperties.getPartyId();
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
                    "Can't use key " + type + " on \"Use a party id (.*) for payment\" step");
        }
    }

    @When("^Use a distant payment start date")
    public void use_start_date() {
        final Arguments arg = Arguments.startDate;
        sharedData.getArguments().put(arg, "2000-01-01");
    }

    @When("^Refer to the payment$")
    public void refer_to_the_payment() throws Throwable {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder
                    .fromHttpUrl(apibssProperties.getPaymentUrl() + "/payment");
            for (Arguments key : sharedData.getArguments().keySet()) {
                String value = sharedData.getArguments().get(key);
                switch (key) {
                case paymentId:
                    builder.pathSegment(value);
                    break;
                default:
                    throw new IllegalArgumentException("Can't use key " + key + " on \"Refer to the payment\" step");
                }
            }
            System.out.println(builder.build().toUri());
            HttpHeaders headers = new HttpHeaders();
            Tools.addHeaders(apibssProperties.getHeaders(), headers);
            ResponseEntity<Payment> response = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET,
                    new HttpEntity<>(headers), Payment.class);
            paymentSharedData.setPayment(response.getBody());
        } catch (HttpStatusCodeException e) {
            sharedData.setException(e);
        }
    }

    @When("^Refer to payments$")
    public void refer_to_payments() throws Throwable {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder
                    .fromHttpUrl(apibssProperties.getPaymentUrl() + "/payment");
            for (Arguments key : sharedData.getArguments().keySet()) {
                String value = sharedData.getArguments().get(key);
                switch (key) {
                case partyId:
                    builder.queryParam("payer.id", value);
                    break;
                case startDate:
                    builder.queryParam("paymentDate.gte", value);
                    break;
                case paymentCorrelatorId:
                    builder.queryParam("correlatorId", value);
                    break;
                default:
                    throw new IllegalArgumentException(
                            "Can't use key " + key + " on \"Refer to the payments\" step");
                }
            }
            System.out.println(builder.build().toUri());
            HttpHeaders headers = new HttpHeaders();
            Tools.addHeaders(apibssProperties.getHeaders(), headers);
            ResponseEntity<Payment[]> response = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET,
                    new HttpEntity<>(headers), Payment[].class);
            paymentSharedData.setPayments(Arrays.asList(response.getBody()));
        } catch (HttpStatusCodeException e) {
            sharedData.setException(e);
        }
    }

    @Then("^Get (valid) payment$")
    public void get_payment(String type) throws Throwable {
        checkNoException();
        assertThat(paymentSharedData.getPayment()).isNotNull();
        final Payment result;
        switch (type) {
        case "valid":
            // first valid payment
            result = paymentProperties.getPayments().get(0);
            break;
        default:
            throw new IllegalArgumentException("Can't use " + type + "for get (.*) payment");
        }
        assertThat(paymentSharedData.getPayment()).isEqualTo(result);
    }

    @Then("^Get (valid|correlatorId) payments$")
    public void get_payments(String type) throws Throwable {
        checkNoException();
        assertThat(paymentSharedData.getPayments()).isNotNull();
        final List<Payment> result;
        switch (type) {
        case "valid":
            result = paymentProperties.getPayments();
            break;
        case "correlatorId":
            // first payment
            result = Arrays.asList(paymentProperties.getPayments().get(0));
            break;
        default:
            throw new IllegalArgumentException("Can't use " + type + "for get (.*) payments");
        }
        assertThat(paymentSharedData.getPayments()).containsOnly(result.toArray(new Payment[result.size()]));
    }

    @Then("^Get empty payment result$")
    public void get_empty_result() throws Throwable {
        checkNoException();
        assertThat(paymentSharedData.getPayments()).isNotNull();
        assertThat(paymentSharedData.getPayments()).isEmpty();
    }

}