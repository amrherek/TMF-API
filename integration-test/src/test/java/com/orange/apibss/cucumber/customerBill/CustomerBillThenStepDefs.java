package com.orange.apibss.cucumber.customerBill;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

import com.orange.apibss.common.exceptions.ApiError;
import com.orange.apibss.cucumber.StepDefs;
import com.orange.apibss.cucumber.config.ApibssProperties;
import com.orange.apibss.cucumber.config.customerBill.CustomerBillProperties;
import com.orange.apibss.customerbill.model.AppliedPayment;
import com.orange.apibss.customerbill.model.CustomerBill;

import cucumber.api.java.en.Then;

/**
 * Date : 02/02/2017.
 *
 * @author Denis Borscia (deyb6792)
 */
public class CustomerBillThenStepDefs extends StepDefs {
    @Autowired
    private CustomerBillSharedData customerBillSharedData;
    @Autowired
    private ApibssProperties apibssProperties;

    private static final ApiError badError = new ApiError();
    private static final ApiError missingError = new ApiError();
    private static final ApiError badFormat = new ApiError();
    private static final ApiError badCombination = new ApiError();

    static {
        badError.setCode(4003);
        badError.setMessage("Bad parameter value");
        missingError.setCode(4001);
        missingError.setMessage("Missing parameter");
        badFormat.setCode(4002);
        badFormat.setMessage("Bad parameter format");
        badCombination.setCode(4005);
        badCombination.setMessage("Bad parameter combination");
    }


    @Then("^the number of bills should be (\\d*)$")
    public void check_the_number_of_bills(int nbOfBills) {
        assertThat(customerBillSharedData.getBills()).hasSize(nbOfBills);
    }

    @Then("^Get bills")
    public void get_bills() throws IOException {
        CustomerBillProperties properties = apibssProperties.getCustomerBill();
        checkNoException();
        checkUrl(customerBillSharedData.getBills());
        assertThat(customerBillSharedData.getBills()).isNotNull();
        List<CustomerBill> result = properties.getBills();
        checkAppliedPayment(result, customerBillSharedData.getBills());
        assertThat(customerBillSharedData.getBills()).containsOnly(result.toArray(new CustomerBill[result.size()]));
    }

    /**
     * Check that appliedPayment are the same, independantly of the order
     * 
     * @param result
     * @param bills
     */
    private void checkAppliedPayment(List<CustomerBill> bills, List<CustomerBill> result) {
        Iterator<CustomerBill> resIt = result.iterator();
        Iterator<CustomerBill> refIt = bills.iterator();
        // result can be bigger than bills
        while (resIt.hasNext()) {
            CustomerBill currentRes = resIt.next();
            CustomerBill currentRef = refIt.next();
            if (currentRef.getAppliedPayment() != null) {
                assertThat(currentRef.getAppliedPayment()).containsOnly(currentRes.getAppliedPayment()
                        .toArray(new AppliedPayment[currentRes.getAppliedPayment().size()]));
            }
            // store ref list in result
            currentRes.setAppliedPayment(currentRef.getAppliedPayment());
        }
    }

    @Then("^Get ordered bills")
    public void get_ordered_bills() throws IOException {
        CustomerBillProperties properties = apibssProperties.getCustomerBill();
        checkNoException();
        assertThat(customerBillSharedData.getBills()).isNotNull();
        List<CustomerBill> result = properties.getBills();
        checkUrl(customerBillSharedData.getBills());
        checkAppliedPayment(result, customerBillSharedData.getBills());
        assertThat(customerBillSharedData.getBills()).containsOnly(result.toArray(new CustomerBill[result.size()]));
        DateTime billDate = null;
        for (CustomerBill bill : customerBillSharedData.getBills()) {
            if (billDate != null) {
                assertThat(billDate.isAfter(bill.getBillDate())).isTrue();
            }
            billDate = bill.getBillDate();
        }
    }

    @Then("^Get the bill")
    public void get_bill() throws IOException {
        CustomerBillProperties properties = apibssProperties.getCustomerBill();
        checkNoException();
        assertThat(customerBillSharedData.getBills()).isNotNull();
        assertThat(customerBillSharedData.getBills()).hasSize(1);
        List<CustomerBill> result = properties.getBills();
        checkUrl(customerBillSharedData.getBills());
        checkAppliedPayment(result, customerBillSharedData.getBills());
        assertThat(customerBillSharedData.getBills()).containsOnly(result.get(0));
    }

    private void checkUrl(List<CustomerBill> bills) {
        for (CustomerBill cb : bills) {
            if (cb.getBillDocument() != null && !cb.getBillDocument().isEmpty()) {
                assertThat(cb.getBillDocument().get(0).getUrl()).isNotNull();
                assertThat(cb.getBillDocument().get(0).getUrl())
                        .endsWith("api/customerBill/v1/customerBill/" + cb.getId() + "/file");
                // remove URL
                cb.getBillDocument().get(0).setUrl(null);
            }
        }
    }

    @Then("^Get the image")
    public void get_image() throws IOException {
        CustomerBillProperties properties = apibssProperties.getCustomerBill();
        checkNoException();
        assertThat(customerBillSharedData.getHeaders()).isNotNull();
        assertThat(customerBillSharedData.getHeaders().getContentType().toString())
                .isEqualTo(properties.getBillImageFormat());
        assertThat(customerBillSharedData.getHeaders().getContentLength()).isEqualTo(properties.getBillImageSize());
    }

    @Then("^the response should be a bad request$")
    public void should_be_a_bad_request() throws Throwable {
        HttpStatusCodeException exception = sharedData.getException();
        assertThat(exception).isNotNull();
        assertThat(exception.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Then("^the response should be a not found response$")
    public void should_be_a_not_found_response() throws Throwable {
        HttpStatusCodeException exception = sharedData.getException();
        assertThat(exception).isNotNull();
        assertThat(exception.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Then("^the error is a (bad|missing) (combination|parameter|format) error for (.*)$")
    public void should_be_a_missing_parameter_error(String errorPart1, String errorPart2, String parameter) throws IOException {
        ApiError error = mappers.parseError(sharedData.getException());
        ApiError expected = null;
        final String errorType = errorPart1 + '-' + errorPart2;
        switch (errorType) {
            case "bad-parameter":
                expected = badError;
                break;
            case "missing-parameter":
                expected = missingError;
                break;
            case "bad-format":
                expected = badFormat;
                break;
            case "bad-combination":
                expected = badCombination;
                break;
        }
        assertThat(error).isNotNull();
        assertThat(error.getCode()).isEqualTo(expected.getCode());
        assertThat(error.getMessage()).isEqualTo(expected.getMessage());
        assertThat(error.getDescription()).contains(parameter);

    }

    @Then("^the error should be a no bill found error$")
    public void should_be_a_no_bill_found_error() throws IOException {
        ApiError error = mappers.parseError(sharedData.getException());
        assertThat(error).isNotNull();
        assertThat(error.getCode()).isEqualTo(4040);
        assertThat(error.getDescription()).contains("There is no bill");
        assertThat(error.getDescription()).contains("0");
        assertThat(error.getMessage()).contains("No data found");

    }

    @Then("^the response should be a not implemented error$")
    public void should_be_not_implemented() throws IOException {
        HttpStatusCodeException exception = sharedData.getException();
        assertThat(exception).isNotNull();
        assertThat(exception.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        ApiError error = mappers.parseError(sharedData.getException());
        assertThat(error).isNotNull();
        assertThat(error.getCode()).isEqualTo(5000);
        assertThat(error.getMessage()).contains("Internal error");
        assertThat(error.getDescription()).contains("Not implemented");

    }
}
