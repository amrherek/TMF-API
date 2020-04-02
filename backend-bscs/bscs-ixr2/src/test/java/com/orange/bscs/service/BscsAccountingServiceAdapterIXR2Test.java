package com.orange.bscs.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.orange.bscs.api.connection.service.BscsConnectionService;
import com.orange.bscs.api.model.exception.SOIException;
import com.orange.bscs.model.BscsBookingRequest;
import com.orange.bscs.model.BscsBusinessTransaction;
import com.orange.bscs.model.BscsBusinessTransactionSearch;
import com.orange.bscs.model.BscsDebitsByCredit;
import com.orange.bscs.model.BscsPayment;
import com.orange.bscs.model.BscsPaymentMethod;
import com.orange.bscs.model.BscsReferencedTransactionSearch;
import com.orange.bscs.model.accounting.EnumBookingActionCode;
import com.orange.bscs.model.accounting.EnumBookingFeeType;
import com.orange.bscs.model.criteria.BscsBusinessTransactionSearchCriteria;
import com.orange.bscs.model.criteria.BscsFinancialDocumentSearchCriteria;
import com.orange.bscs.model.criteria.BscsFinancialTransactionSearchCriteria;
import com.orange.bscs.model.criteria.BscsReferencedTransactionSearchCriteria;
import com.orange.bscs.model.factory.BscsObjectFactory;
import com.orange.bscs.service.exception.BscsFieldExceptionEnum;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BscsTestConfig.class })
@ActiveProfiles("corba")
@Category(CorbaBscsTest.class)
public class BscsAccountingServiceAdapterIXR2Test {

    @Autowired
    private BscsAccountingServiceAdapter adapter;

    @Autowired
    private BscsConnectionService bscsConnectionService;

    @Autowired
    protected BscsObjectFactory bscsObjectFactory;

    @Before
    public void openConnection() throws Exception {
        bscsConnectionService.openConnection();
    }

    @After
    public void closeConnection() {
        bscsConnectionService.closeConnection();
    }

    @Test
    public void getPaymentMethods_ok() {
        List<BscsPaymentMethod> paymentMethods = adapter.getPaymentMethods();
        assertThat(paymentMethods).isNotEmpty();
    }


    @Test
    public void getFinancialDocument_notAvailable() throws BscsInvalidIdException {
        try{
            adapter.getFinancialDocument(99999999L);
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).containsIgnoringCase("Not available");
            return;
        }
        fail("No exception");
    }

    @Test
    public void findFinancialDocuments_notAvailable() {
        try {
            BscsFinancialDocumentSearchCriteria criteria = bscsObjectFactory
                    .createBscsFinancialDocumentSearchCriteria();
            criteria.setCustomerId("UNKNOWN");
            adapter.findFinancialDocuments(criteria);
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).containsIgnoringCase("Not available");
            return;
        }
        fail("No exception");
    }

    @Test
    public void getBusinessTransaction_unknownId() {
        try {
            adapter.getBusinessTransaction(99999999L);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("99999999");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.BUSINESS_TRANSACTION_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getBusinessTransaction_ok() throws BscsInvalidIdException {
        BscsBusinessTransaction businessTransaction = adapter.getBusinessTransaction(12L);
        assertThat(businessTransaction).isNotNull();
    }

    @Test
    public void findBusinessTransactions_unknownCustomerId() {
        BscsBusinessTransactionSearchCriteria criteria = bscsObjectFactory
                .createBscsBusinessTransactionSearchCriteria();
        criteria.setCustomerId("UNKNOWN");
        List<BscsBusinessTransactionSearch> findBusinessTransactions = adapter.findBusinessTransactions(criteria);
        assertThat(findBusinessTransactions).isEmpty();
    }

    @Test
    public void findBusinessTransactions_startDateInTheFuture() throws BscsInvalidIdException {
        BscsBusinessTransactionSearchCriteria criteria = bscsObjectFactory
                .createBscsBusinessTransactionSearchCriteria();
        criteria.setStartDate(new GregorianCalendar(2030, 0, 1).getTime());
        List<BscsBusinessTransactionSearch> findBusinessTransactions = adapter.findBusinessTransactions(criteria);
        assertThat(findBusinessTransactions).isEmpty();
    }

    @Test
    public void findBusinessTransactions_ok() throws BscsInvalidIdException {
        BscsBusinessTransactionSearchCriteria criteria = bscsObjectFactory
                .createBscsBusinessTransactionSearchCriteria();
        criteria.setCustomerId("CUST0000000180");
        List<BscsBusinessTransactionSearch> findBusinessTransactions = adapter.findBusinessTransactions(criteria);
        assertThat(findBusinessTransactions).isNotEmpty();
    }

    @Test
    public void writeBookingRequest_unknownCsId() throws BscsInvalidFieldException {
        BscsBookingRequest bookingRequest = bscsObjectFactory.createBscsBookingRequest();
        bookingRequest.setCustomerId("UNKNOWN");
        try {
            adapter.writeBookingRequest(bookingRequest, true);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("UNKNOWN");
            // we do not know which is the bad field
            assertThat(e.getName()).isNull();
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeBookingRequest_unknownContractId() throws BscsInvalidFieldException {
        BscsBookingRequest bookingRequest = bscsObjectFactory.createBscsBookingRequest();
        bookingRequest.setContractId("UNKNOWN");
        try {
            adapter.writeBookingRequest(bookingRequest, true);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("UNKNOWN");
            // we do not know which is the bad field
            assertThat(e.getName()).isNull();
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeBookingRequest_unknownServiceCode() throws BscsInvalidFieldException {
        BscsBookingRequest bookingRequest = bscsObjectFactory.createBscsBookingRequest();
        bookingRequest.setServiceCode("UNKNOWN");
        try {
            adapter.writeBookingRequest(bookingRequest, true);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("UNKNOWN");
            // we do not know which is the bad field
            assertThat(e.getName()).isNull();
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeBookingRequest_unknownServicePackageCode() throws BscsInvalidFieldException {
        BscsBookingRequest bookingRequest = bscsObjectFactory.createBscsBookingRequest();
        bookingRequest.setServicePackageCode("UNKNOWN");
        try {
            adapter.writeBookingRequest(bookingRequest, true);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("UNKNOWN");
            // we do not know which is the bad field
            assertThat(e.getName()).isNull();
            return;
        }
        fail("No exception");
    }

    @Test(expected = SOIException.class)
    public void writeBookingRequest_missingParameters() throws BscsInvalidIdException, BscsInvalidFieldException {
        BscsBookingRequest bookingRequest = bscsObjectFactory.createBscsBookingRequest();
        bookingRequest.setFeeType(EnumBookingFeeType.NON_RECURRING);
        bookingRequest.setFeeClass(3);
        bookingRequest.setActionCode(EnumBookingActionCode.INSERT);
        adapter.writeBookingRequest(bookingRequest, true);
    }

    @Test
    public void writeBookingRequest_serviceNotInRP() throws BscsInvalidIdException, BscsInvalidFieldException {
        try {
            BscsBookingRequest bookingRequest = bscsObjectFactory.createBscsBookingRequest();
            bookingRequest.setFeeType(EnumBookingFeeType.NON_RECURRING);
            bookingRequest.setFeeClass(3);
            bookingRequest.setActionCode(EnumBookingActionCode.INSERT);
            bookingRequest.setCustomerId("CUST0000000183");
            bookingRequest.setContractId("CONTR0000000217");
            bookingRequest.setRatePlanCode("ISDS");
            bookingRequest.setServiceCode("MVS"); // service code pub
            bookingRequest.setServicePackageCode("OP001");
            adapter.writeBookingRequest(bookingRequest, true);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("226"); // service code
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeBookingRequest_ok() throws BscsInvalidIdException, BscsInvalidFieldException {
        BscsBookingRequest bookingRequest = bscsObjectFactory.createBscsBookingRequest();
        bookingRequest.setFeeType(EnumBookingFeeType.NON_RECURRING);
        bookingRequest.setFeeClass(3);
        bookingRequest.setActionCode(EnumBookingActionCode.INSERT);
        bookingRequest.setCustomerId("CUST0000000183");
        bookingRequest.setContractId("CONTR0000000217");
        bookingRequest.setServiceCode("ADMIN");
        bookingRequest.setServicePackageCode("OS");
        // RP code is mandatory in IXR2 (and not in IXR4)
        bookingRequest.setRatePlanCode("ISDS");
        adapter.writeBookingRequest(bookingRequest, true);
    }

    @Test
    public void getFinancialTransaction_notAvailable() throws BscsInvalidIdException {
        try {
            adapter.getFinancialTransaction(7245L);
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).containsIgnoringCase("Not available");
            return;
        }
        fail("No exception");
    }

    @Test
    public void findFinancialTransactions_notAvailable() throws BscsInvalidIdException {
        try {
            BscsFinancialTransactionSearchCriteria criteria = bscsObjectFactory
                    .createBscsFinancialTransactionSearchCriteria();
            criteria.setCustomerNumericId(47L);
            adapter.findFinancialTransactions(criteria);
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).containsIgnoringCase("Not available");
            return;
        }
        fail("No exception");
    }

    @Test
    public void writePayment_ok() throws BscsInvalidIdException, BscsInvalidFieldException {
        BscsPayment payment = bscsObjectFactory.createBscsPayment();
        payment.setTransactionCode("CE2IN");
        payment.setReceiptDate(new Date());
        payment.setReferenceDate(new Date());

        adapter.writePayment(payment, false);
    }

    @Test
    public void writePayment_unknownCsId() throws BscsInvalidIdException, BscsInvalidFieldException {
        BscsPayment payment = bscsObjectFactory.createBscsPayment();
        payment.setTransactionCode("CE2IN");
        payment.setReceiptDate(new Date());
        payment.setReferenceDate(new Date());
        payment.setCustomerId("UNKNOWN");
        try {
            adapter.writePayment(payment, false);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("UNKNOWN");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.CUSTOMER_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void findReferencedTransactions_ok() throws BscsInvalidIdException, BscsInvalidFieldException {
        BscsReferencedTransactionSearchCriteria criteria = bscsObjectFactory
                .createBscsReferencedTransactionSearchCriteria();
        criteria.setCustomerId("CUST0000000428");
        List<BscsReferencedTransactionSearch> transactions = adapter.findReferencedTransactions(criteria);
        assertThat(transactions).isNotEmpty();
    }

    @Test
    public void findReferencedTransactions_unknownCustomerId()
            throws BscsInvalidIdException, BscsInvalidFieldException {
        BscsReferencedTransactionSearchCriteria criteria = bscsObjectFactory
                .createBscsReferencedTransactionSearchCriteria();
        criteria.setCustomerId("UNKNOWN");
        List<BscsReferencedTransactionSearch> transactions = adapter.findReferencedTransactions(criteria);
        assertThat(transactions).isEmpty();
    }

    @Test
    public void getDebitsByCredit_ok() throws BscsInvalidIdException, BscsInvalidFieldException {
        List<BscsDebitsByCredit> debitsByCredit = adapter.getDebitsByCredit(12L);
        assertThat(debitsByCredit).isNotEmpty();
    }

    @Test
    public void getDebitsByCredit_unknownId() throws BscsInvalidIdException, BscsInvalidFieldException {
        List<BscsDebitsByCredit> debitsByCredit = adapter.getDebitsByCredit(12345678L);
        assertThat(debitsByCredit).isEmpty();
    }

}
