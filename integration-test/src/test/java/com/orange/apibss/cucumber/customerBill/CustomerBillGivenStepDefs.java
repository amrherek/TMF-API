package com.orange.apibss.cucumber.customerBill;

import org.springframework.beans.factory.annotation.Autowired;

import com.orange.apibss.cucumber.StepDefs;
import com.orange.apibss.cucumber.config.ApibssProperties;

import cucumber.api.java.en.Given;

/**
 * Date : 02/02/2017.
 *
 * @author Denis Borscia (deyb6792)
 */
public class CustomerBillGivenStepDefs extends StepDefs {

    @Autowired
    private CustomerBillSharedData customerBillSharedData;

    @Autowired
    private ApibssProperties apibssProperties;

    @Given("^a[n]? (valid|empty|invalid|bad format) msisdn")
    public void get_a_valid_msisdn(String msisdn) {
        switch (msisdn) {
            case "valid":
            customerBillSharedData.setMsisdn(apibssProperties.getCustomerBill().getMsisdn().getValid());
                break;
        case "invalid":
            customerBillSharedData.setMsisdn(apibssProperties.getCustomerBill().getMsisdn().getInvalid());
            break;
        case "bad format":
            customerBillSharedData.setMsisdn(apibssProperties.getCustomerBill().getMsisdn().getBadFormat());
            break;
            case "empty":
                customerBillSharedData.setMsisdn("");
        }
    }

    @Given("^a (valid|invalid|bad format|large account) party id")
    public void get_a_party_id(String type) {
        switch (type) {
        case "valid":
            customerBillSharedData.setPartyId(apibssProperties.getCustomerBill().getPartyId().getValid());
            break;
        case "invalid":
            customerBillSharedData.setPartyId(apibssProperties.getCustomerBill().getPartyId().getInvalid());
            break;
        case "bad format":
            customerBillSharedData.setPartyId(apibssProperties.getCustomerBill().getPartyId().getBadFormat());
            break;
        case "large account":
            customerBillSharedData.setPartyId(apibssProperties.getCustomerBill().getPartyId().getLargeAccount());
            break;
        }
    }

    @Given("^a (valid|invalid|distant) start date")
    public void get_a_start_date(String type) {
        switch (type) {
        case "valid":
            customerBillSharedData.setStartDate(apibssProperties.getCustomerBill().getDate());
            break;
        case "invalid":
            customerBillSharedData.setStartDate("01/01/2012");
            break;
        case "distant":
            customerBillSharedData.setStartDate("2000-01-01");
            break;
        }
    }

    @Given("^a (valid|invalid) end date")
    public void get_a_end_date(String type) {
        switch (type) {
        case "valid":
            customerBillSharedData.setEndDate(apibssProperties.getCustomerBill().getDate());
            break;
        case "invalid":
            customerBillSharedData.setEndDate("01/01/2012");
            break;
        }
    }

    @Given("^a msisdn without prefix")
    public void get_a_msisdn_without_prefix() {
        customerBillSharedData.setMsisdn(apibssProperties.getCustomerBill().getMsisdn().getValid().substring(3));
    }

    @Given("^an authorized user$")
    public void an_authorized_user() {
        customerBillSharedData.setUser(apibssProperties.getCustomerBill().getUser());
    }

    @Given("^a maximum of (.*) results$")
    public void a_number_max_of_result(String limit) {
        customerBillSharedData.setLimit(limit);
    }

    @Given("a[n]? (valid|invalid|bad format) customer bill id")
    public void set_a_customer_bill_id(String type) {
        switch (type) {
        case "valid":
            customerBillSharedData.setCustomerBillId(apibssProperties.getCustomerBill().getId().getValid());
            break;
        case "invalid":
            customerBillSharedData.setCustomerBillId(apibssProperties.getCustomerBill().getId().getInvalid());
            break;
        case "bad format":
            customerBillSharedData.setCustomerBillId(apibssProperties.getCustomerBill().getId().getBadFormat());
            break;
        }
    }

    @Given("search with hierarchy")
    public void search_with_hierarchy() {
        customerBillSharedData.setWithHierarchy(true);
    }

}
