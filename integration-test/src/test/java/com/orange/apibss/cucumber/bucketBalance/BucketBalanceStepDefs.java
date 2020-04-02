package com.orange.apibss.cucumber.bucketBalance;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.orange.apibss.bucketBalance.model.Characteristic;
import com.orange.apibss.bucketBalance.model.CreditBucketBalanceByTransfer;
import com.orange.apibss.bucketBalance.model.CreditBucketBalanceByValue;
import com.orange.apibss.bucketBalance.model.CreditBucketBalanceByVoucher;
import com.orange.apibss.bucketBalance.model.CreditBucketBalanceTransaction;
import com.orange.apibss.bucketBalance.model.PublicKey;
import com.orange.apibss.bucketBalance.model.UsageReport;
import com.orange.apibss.cucumber.Arguments;
import com.orange.apibss.cucumber.StepDefs;
import com.orange.apibss.cucumber.config.ApibssProperties;
import com.orange.apibss.cucumber.config.BucketBalanceProperties;
import com.orange.apibss.cucumber.config.Msisdn;
import com.orange.apibss.cucumber.tools.Tools;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BucketBalanceStepDefs extends StepDefs {

    @Autowired
    private ApibssProperties apibssProperties;

    @Autowired
    private BucketBalanceSharedData bucketBalanceSharedData;

    @Autowired
    private BucketBalanceProperties bucketBalanceProperties;

    @Autowired
    private RestTemplate restTemplate;

    @Given("^Use a Credit Bucket Balance By Value$")
    public void use_a_creditBucketBalanceByValue_body() {
        CreditBucketBalanceByValue credit = new CreditBucketBalanceByValue();
        bucketBalanceSharedData.setTestCreditBucketBalanceByValue(credit);
    }

    @Given("^Use a Credit Bucket Balance By Voucher$")
    public void use_a_creditBucketBalanceByVoucher_body() {
        CreditBucketBalanceByVoucher credit = new CreditBucketBalanceByVoucher();
        bucketBalanceSharedData.setTestCreditBucketBalanceByVoucher(credit);
    }

    @Given("^Use a Credit Bucket Balance By Transfer$")
    public void use_a_creditBucketBalanceByTransfer_body() {
        CreditBucketBalanceByTransfer credit = new CreditBucketBalanceByTransfer();
        bucketBalanceSharedData.setTestCreditBucketBalanceByTransfer(credit);
    }

    @When("^Add a (valid|invalid) Public Key to Credit Bucket Balance By (Value|Voucher|Transfer)")
    public void add_publicKey(final String publicKeyType, final String creditType) {
        PublicKey pk = new PublicKey();
        pk.setName("MSISDN");
        Msisdn properties = bucketBalanceProperties.getMsisdn();
        switch (publicKeyType) {
        case "valid":
            pk.setId(properties.getPrepaid());
            break;
        case "invalid":
            pk.setId(properties.getInvalid());
            break;
        default:
            throw new IllegalArgumentException(
                    "Can't use key " + publicKeyType + " on \"Add a (.*)  Public Key to Credit Bucket Balance\" step");
        }

        switch (creditType) {
        case "Value":
            CreditBucketBalanceByValue value = bucketBalanceSharedData.getTestCreditBucketBalanceByValue();
            value.setPublicKey(pk);
            break;
        case "Voucher":
            CreditBucketBalanceByVoucher voucher = bucketBalanceSharedData.getTestCreditBucketBalanceByVoucher();
            voucher.setPublicKey(pk);
            break;
        case "Transfer":
            CreditBucketBalanceByTransfer transfer = bucketBalanceSharedData.getTestCreditBucketBalanceByTransfer();
            transfer.setOriginPublicKey(pk);
            transfer.setTargetPublicKey(pk);
            break;
        default:
            throw new IllegalArgumentException(
                    "Can't use key " + creditType + " on \"Add a Public Key to Credit Bucket Balance By (.*)\" step");
        }
    }

    @When("^Add a valid id to Credit Bucket Balance By (Value|Voucher|Transfer)")
    public void add_id(final String creditType) {
        switch (creditType) {
        case "Value":
            CreditBucketBalanceByValue value = bucketBalanceSharedData.getTestCreditBucketBalanceByValue();
            value.setId("1234");
            break;
        case "Voucher":
            CreditBucketBalanceByVoucher voucher = bucketBalanceSharedData.getTestCreditBucketBalanceByVoucher();
            voucher.setId("1234");
            break;
        default:
            throw new IllegalArgumentException(
                    "Can't use key " + creditType + " on \"Add a valid id to Credit Bucket Balance By (.*)\" step");
        }
    }

    @When("^Add (float|integer|problematic) value to Credit Bucket Balance By (Value|Transfer)")
    public void add_value(String type, final String creditType) {
        Float value = null;
        switch (type) {
        case "float":
            value = 20.2f;
            break;
        case "integer":
            value = 20.0f;
            break;
        case "problematic":
            // 4.095*1000 => 4094.9999999999995
            value = 4.095f;
            break;
        default:
            throw new IllegalArgumentException(
                    "Can't use key " + type + " on \"Add a (.*) value to Credit Bucket Balance By (.*)\" step");
        }

        switch (creditType) {
        case "Value":
            CreditBucketBalanceByValue credit = bucketBalanceSharedData.getTestCreditBucketBalanceByValue();
            credit.setValue(value);
            break;
        case "Transfer":
            CreditBucketBalanceByTransfer transfer = bucketBalanceSharedData.getTestCreditBucketBalanceByTransfer();
            transfer.setValue(value);
            break;
        default:
            throw new IllegalArgumentException(
                    "Can't use key " + type + " on \"Add a (.*) value to Credit Bucket Balance By (.*)\" step");
        }
    }

    @When("^Add (.*) unit to Credit Bucket Balance By Value")
    public void add_unit(String currency) {
        CreditBucketBalanceByValue credit = bucketBalanceSharedData.getTestCreditBucketBalanceByValue();
        credit.setUnit(currency);
    }

    @When("^Add valid characteristics to Credit Bucket Balance By Value")
    public void add_characteristics() {
        CreditBucketBalanceByValue credit = bucketBalanceSharedData.getTestCreditBucketBalanceByValue();
        Characteristic charac = new Characteristic();
        charac.setName("externalData1");
        charac.setValue("comment1");
        credit.addCharacteristicItem(charac);
        charac = new Characteristic();
        charac.setName("externalData2");
        charac.setValue("comment2");
        credit.addCharacteristicItem(charac);
        charac = new Characteristic();
        charac.setName("profileId");
        charac.setValue("0");
        credit.addCharacteristicItem(charac);
        charac = new Characteristic();
        charac.setName("taxCode");
        charac.setValue("0");
        credit.addCharacteristicItem(charac);
    }

    @When("^Add number to Credit Bucket Balance By Voucher")
    public void add_number() {
        CreditBucketBalanceByVoucher credit = bucketBalanceSharedData.getTestCreditBucketBalanceByVoucher();
        credit.setNumber("1345A");
    }

    @When("^Remove msisdn from Credit Bucket Balance By Voucher")
    public void remove_msisdn_from_creditBucketBalanceByVoucher() {
        CreditBucketBalanceByVoucher credit = bucketBalanceSharedData.getTestCreditBucketBalanceByVoucher();
        credit.getPublicKey().setId(null);
    }

    @When("^Use a MSISDN (prepaid|postpaid|invalid) for bucket balance")
    public void use_msisdn(final String type) {
        final Arguments arg = Arguments.MSISDN;
        Msisdn properties = bucketBalanceProperties.getMsisdn();
        switch (type) {
        case "prepaid":
            sharedData.getArguments().put(arg, properties.getPrepaid());
            break;
        case "postpaid":
            sharedData.getArguments().put(arg, properties.getPostpaid());
            break;
        case "invalid":
            sharedData.getArguments().put(arg, properties.getInvalid());
            break;
        default:
            throw new IllegalArgumentException(
                    "Can't use key " + type + " on \"Use a MSISDN (.*) for bucket balance\" step");
        }
    }

    @When("^Use a distant start date for bucket balance")
    public void use_start_date() {
        final Arguments arg = Arguments.startDate;
        sharedData.getArguments().put(arg, "2000-01-01");
    }

    @When("^Use a (prepaid|postpaid) rating type")
    public void use_type(final String type) {
        final Arguments arg = Arguments.ratingType;
        sharedData.getArguments().put(arg, type);
    }
    
    @When("^Refer to usage report$")
    public void refer_to_usageReport() throws Throwable {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder
                    .fromHttpUrl(apibssProperties.getBucketBalanceUrl() + "/usageReport");
            for (Arguments key : sharedData.getArguments().keySet()) {
                String value = sharedData.getArguments().get(key);
                switch (key) {
                case MSISDN:
                    builder.queryParam("publicKey.id", value);
                    break;
                case ratingType:
                    builder.queryParam("ratingType", value);
                    break;
                default:
                    throw new IllegalArgumentException(
                            "Can't use key " + key + " on \"Refer to usageReport\" step");
                }
            }
            System.out.println(builder.build().toUri());
            HttpHeaders headers = new HttpHeaders();
            Tools.addHeaders(apibssProperties.getHeaders(), headers);
            ResponseEntity<UsageReport[]> response = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET,
                    new HttpEntity<>(headers), UsageReport[].class);
            bucketBalanceSharedData.setUsageReports(Arrays.asList(response.getBody()));
        } catch (HttpStatusCodeException e) {
            sharedData.setException(e);
        }
    }
    
    @When("^Refer to Credit Bucket Balance Transactions$")
    public void refer_to_creditBucketBalanceTransactions() throws Throwable {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder
                    .fromHttpUrl(apibssProperties.getBucketBalanceUrl() + "/creditBucketBalanceTransactions");
            for (Arguments key : sharedData.getArguments().keySet()) {
                String value = sharedData.getArguments().get(key);
                switch (key) {
                case MSISDN:
                    builder.queryParam("publicKey.id", value);
                    break;
                case startDate:
                    builder.queryParam("date.gte", value);
                    break;
                case ratingType:
                    builder.queryParam("ratingType", value);
                    break;
                default:
                    throw new IllegalArgumentException(
                            "Can't use key " + key + " on \"Refer to Credit Bucket Balance Transactions\" step");
                }
            }
            HttpHeaders headers = new HttpHeaders();
            Tools.addHeaders(apibssProperties.getHeaders(), headers);
            System.out.println(builder.build().toUri());
            ResponseEntity<CreditBucketBalanceTransaction[]> response = restTemplate
                    .exchange(builder.build().toUri(), HttpMethod.GET, new HttpEntity<>(headers),
                            CreditBucketBalanceTransaction[].class);
            bucketBalanceSharedData.setCreditBucketBalanceTransaction(Arrays.asList(response.getBody()));
        } catch (HttpStatusCodeException e) {
            sharedData.setException(e);
        }
    }

    @When("^Send Credit Bucket Balance By Value request")
    public void send_creditBucketBalanceByValue_request() throws Throwable {
        try {
            CreditBucketBalanceByValue testCredit = bucketBalanceSharedData.getTestCreditBucketBalanceByValue();
            UriComponentsBuilder builder = UriComponentsBuilder
                    .fromHttpUrl(apibssProperties.getBucketBalanceUrl() + "/creditBucketBalanceByValue");
            HttpHeaders headers = new HttpHeaders();
            Tools.addHeaders(apibssProperties.getHeaders(), headers);
            headers.setContentType(MediaType.APPLICATION_JSON);
            ResponseEntity<CreditBucketBalanceByValue> response = restTemplate.exchange(builder.build().toUri(),
                    HttpMethod.POST, new HttpEntity<>(testCredit, headers), CreditBucketBalanceByValue.class);
            bucketBalanceSharedData.setCreditBucketBalanceByValue(response.getBody());
        } catch (HttpStatusCodeException e) {
            sharedData.setException(e);
        }
    }

    @When("^Send Credit Bucket Balance By Transfer request")
    public void send_creditBucketBalanceByTransfer_request() throws Throwable {
        try {
            CreditBucketBalanceByTransfer testCredit = bucketBalanceSharedData.getTestCreditBucketBalanceByTransfer();
            UriComponentsBuilder builder = UriComponentsBuilder
                    .fromHttpUrl(apibssProperties.getBucketBalanceUrl() + "/creditBucketBalanceByTransfer");
            HttpHeaders headers = new HttpHeaders();
            Tools.addHeaders(apibssProperties.getHeaders(), headers);
            headers.setContentType(MediaType.APPLICATION_JSON);
            ResponseEntity<CreditBucketBalanceByTransfer> response = restTemplate.exchange(builder.build().toUri(),
                    HttpMethod.POST, new HttpEntity<>(testCredit, headers), CreditBucketBalanceByTransfer.class);
            bucketBalanceSharedData.setCreditBucketBalanceByTransfer(response.getBody());
        } catch (HttpStatusCodeException e) {
            sharedData.setException(e);
        }
    }

    @When("^Send Credit Bucket Balance By Voucher request")
    public void send_creditBucketBalanceByVoucher_request() throws Throwable {
        try {
            CreditBucketBalanceByVoucher testCredit = bucketBalanceSharedData.getTestCreditBucketBalanceByVoucher();
            UriComponentsBuilder builder = UriComponentsBuilder
                    .fromHttpUrl(apibssProperties.getBucketBalanceUrl() + "/creditBucketBalanceByVoucher");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            Tools.addHeaders(apibssProperties.getHeaders(), headers);
            ResponseEntity<CreditBucketBalanceByVoucher> response = restTemplate.exchange(builder.build().toUri(),
                    HttpMethod.POST, new HttpEntity<>(testCredit, headers), CreditBucketBalanceByVoucher.class);
            bucketBalanceSharedData.setCreditBucketBalanceByVoucher(response.getBody());
        } catch (HttpStatusCodeException e) {
            sharedData.setException(e);
        }
    }

    @Then("^Get (prepaid|postpaid) usage reports$")
    public void get_usageReports(final String type) throws Throwable {
        checkNoException();
        assertThat(bucketBalanceSharedData.getUsageReports()).isNotNull();
        List<UsageReport> result = null;
        switch (type) {
        case "prepaid":
            result = bucketBalanceProperties.getPrepaidUsageReports();
            break;
        case "postpaid":
            result = bucketBalanceProperties.getPostpaidUsageReports();
            break;
        default:
            throw new IllegalArgumentException("Can't use key " + type + " on \"Get (.*) usage reports\" step");
        }
    
        // do not check date
        validateAndRemoveDate();
        assertThat(bucketBalanceSharedData.getUsageReports())
                .containsOnly(result.toArray(new UsageReport[result.size()]));
    }

    @Then("^Get Credit Bucket Balance Transactions$")
    public void get_creditBucketBalanceTransactions() throws Throwable {
        checkNoException();
        assertThat(bucketBalanceSharedData.getCreditBucketBalanceTransaction()).isNotNull();
        List<CreditBucketBalanceTransaction> result = bucketBalanceProperties.getCreditBucketBalanceTransactions();
        if (result == null) {
            // empty result
            assertThat(bucketBalanceSharedData.getCreditBucketBalanceTransaction()).isEmpty();
        } else {
            assertThat(bucketBalanceSharedData.getCreditBucketBalanceTransaction())
                    .containsOnly(result.toArray(new CreditBucketBalanceTransaction[result.size()]));
        }
    }

    @Then("^Api return the Credit Bucket Balance By Value$")
    public void api_return_the_creditBucketBalanceByValue() {
        assertThat(sharedData.getException()).isNull();
        CreditBucketBalanceByValue credit = bucketBalanceSharedData.getCreditBucketBalanceByValue();
        assertThat(credit).isNotNull();
        assertThat(credit).isEqualTo(bucketBalanceSharedData.getTestCreditBucketBalanceByValue());
    }

    @Then("^Api return the Credit Bucket Balance By Voucher$")
    public void api_return_the_creditBucketBalanceByVoucher() {
        assertThat(sharedData.getException()).isNull();
        CreditBucketBalanceByVoucher credit = bucketBalanceSharedData.getCreditBucketBalanceByVoucher();
        assertThat(credit).isNotNull();
        assertThat(credit).isEqualTo(bucketBalanceSharedData.getTestCreditBucketBalanceByVoucher());
    }

    private void validateAndRemoveDate() {
        for (UsageReport ur : bucketBalanceSharedData.getUsageReports()) {
            assertThat(ur.getDate()).isNotNull();
            ur.setDate(null);
        }
    }

    @Then("^Get empty usage reports result$")
    public void get_empty_usage_report_result() throws Throwable {
        checkNoException();
        assertThat(bucketBalanceSharedData.getUsageReports()).isNotNull();
        assertThat(bucketBalanceSharedData.getUsageReports()).isEmpty();
    }

    @Then("^Get empty credit bucket balance transactions result$")
    public void get_empty_credit_bucket_balance_result() throws Throwable {
        checkNoException();
        assertThat(bucketBalanceSharedData.getCreditBucketBalanceTransaction()).isNotNull();
        assertThat(bucketBalanceSharedData.getCreditBucketBalanceTransaction()).isEmpty();
    }

    @Then("^Invalid credit error$")
    public void invalid_credit_error() throws Throwable {
        checkError(HttpStatus.BAD_REQUEST, 4101, "Bad credit value", "");
    }

}
