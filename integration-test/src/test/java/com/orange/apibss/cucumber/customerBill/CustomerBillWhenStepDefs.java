package com.orange.apibss.cucumber.customerBill;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.orange.apibss.cucumber.StepDefs;
import com.orange.apibss.cucumber.config.ApibssProperties;
import com.orange.apibss.cucumber.config.customerBill.User;
import com.orange.apibss.customerbill.model.CustomerBill;

import cucumber.api.java.en.When;

/**
 * Date : 02/02/2017.
 *
 * @author Denis Borscia (deyb6792)
 */
public class CustomerBillWhenStepDefs extends StepDefs {

    @Autowired
    private ApibssProperties apibssProperties;
    @Autowired
    private CustomerBillSharedData customerBillSharedData;
    @Autowired
    private RestTemplate restTemplate;

    @When("^listing the customer bills$")
    public void listing_the_customer_bills() {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apibssProperties.getCustomerBillUrl() + "customerBill");
            if (null != customerBillSharedData.getMsisdn()) {
                builder.queryParam("publicKey", customerBillSharedData.getMsisdn());
            }
            if (null != customerBillSharedData.getPartyId()) {
                builder.queryParam("relatedParty.id", customerBillSharedData.getPartyId());
            }
            if (customerBillSharedData.getLimit() != null) {
                builder.queryParam("limit", customerBillSharedData.getLimit());
            }
            if (customerBillSharedData.getStartDate() != null) {
                builder.queryParam("billDate.gte", customerBillSharedData.getStartDate());
            }
            if (customerBillSharedData.getEndDate() != null) {
                builder.queryParam("billDate.lte", customerBillSharedData.getEndDate());
            }
            if (customerBillSharedData.getUser() != null) {
                List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
                interceptors.add(new UserInterceptor(customerBillSharedData.getUser()));
                restTemplate.setInterceptors(interceptors);
            }
            if (customerBillSharedData.getWithHierarchy() != null) {
                builder.queryParam("withHierarchy", customerBillSharedData.getWithHierarchy());
            }

            System.out.println(builder.build().toUri());
            ResponseEntity<List<CustomerBill>> response = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET,
                    null, new ParameterizedTypeReference<List<CustomerBill>>() {
                    });
            customerBillSharedData.setBills(response.getBody());
        } catch (HttpStatusCodeException e) {
            sharedData.setException(e);
        }
    }

    @When("^getting bill information$")
    public void getting_a_customer_bill() {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apibssProperties.getCustomerBillUrl() + "customerBill/" + customerBillSharedData.getCustomerBillId());

            if (customerBillSharedData.getUser() != null) {
                List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
                interceptors.add(new UserInterceptor(customerBillSharedData.getUser()));
                restTemplate.setInterceptors(interceptors);
            }

            System.out.println(builder.build().toUri());
            ResponseEntity<CustomerBill> response = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET,
                    null, CustomerBill.class);
            customerBillSharedData.setBill(response.getBody());
        } catch (HttpStatusCodeException e) {
            sharedData.setException(e);
        }
    }

    @When("^download bill image$")
    public void download_bill_image() {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apibssProperties.getCustomerBillUrl()
                    + "customerBill/" + customerBillSharedData.getCustomerBillId() + "/file");

            if (customerBillSharedData.getUser() != null) {
                List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
                interceptors.add(new UserInterceptor(customerBillSharedData.getUser()));
                restTemplate.setInterceptors(interceptors);
            }
            restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());

            System.out.println(builder.build().toUri());
            ResponseEntity<byte[]> response = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, null,
                    byte[].class);
            customerBillSharedData.setHeaders(response.getHeaders());
        } catch (HttpStatusCodeException e) {
            sharedData.setException(e);
        }
    }
}


class UserInterceptor implements ClientHttpRequestInterceptor {

    private final User user;

    public UserInterceptor(User user) {
        this.user = user;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        HttpRequest wrapper = new HttpRequestWrapper(httpRequest);
        wrapper.getHeaders().set("login", user.getLogin());
        wrapper.getHeaders().set("password", user.getPassword());
        wrapper.getHeaders().set("platForm", user.getPlatForm());
        return clientHttpRequestExecution.execute(wrapper, bytes);
    }
}