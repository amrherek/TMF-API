package com.orange.bscs.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

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
import com.orange.bscs.model.BscsPaymentMethod;
import com.orange.bscs.model.BscsReferencedTransactionSearch;
import com.orange.bscs.model.accounting.EnumBookingActionCode;
import com.orange.bscs.model.accounting.EnumBookingFeeType;
import com.orange.bscs.model.criteria.BscsBusinessTransactionSearchCriteria;
import com.orange.bscs.model.criteria.BscsFinancialDocumentSearchCriteria;
import com.orange.bscs.model.criteria.BscsFinancialTransactionSearchCriteria;
import com.orange.bscs.model.factory.BscsObjectFactory;
import com.orange.bscs.service.exception.BscsFieldExceptionEnum;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BscsTestConfig.class })
@ActiveProfiles("corba")
@Category(CorbaBscsTest.class)
public class BscsAccountingServiceAdapterV8Test {

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
            criteria.setCustomerId("10");
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
            adapter.getBusinessTransaction(10L);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("10");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.BUSINESS_TRANSACTION_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getBusinessTransaction_ok() throws BscsInvalidIdException {
        BscsBusinessTransaction businessTransaction = adapter.getBusinessTransaction(14407579L);
        assertThat(businessTransaction).isNotNull();
    }

    @Test
    public void findBusinessTransactions_unknownCustomerId() throws BscsInvalidIdException {
        BscsBusinessTransactionSearchCriteria criteria = bscsObjectFactory
                .createBscsBusinessTransactionSearchCriteria();
        criteria.setCustomerId("10");
        List<BscsBusinessTransactionSearch> findBusinessTransactions = adapter.findBusinessTransactions(criteria);
        assertThat(findBusinessTransactions).isEmpty();
    }

    @Test
    public void findBusinessTransactions_startDateInTheFuture() throws BscsInvalidIdException {
        BscsBusinessTransactionSearchCriteria criteria = bscsObjectFactory
                .createBscsBusinessTransactionSearchCriteria();
        criteria.setCustomerId("2949565");
        criteria.setStartDate(new GregorianCalendar(2030, 0, 1).getTime());
        List<BscsBusinessTransactionSearch> findBusinessTransactions = adapter.findBusinessTransactions(criteria);
        assertThat(findBusinessTransactions).isEmpty();
    }

    @Test
    public void findBusinessTransactions_ok() throws BscsInvalidIdException {
        BscsBusinessTransactionSearchCriteria criteria = bscsObjectFactory
                .createBscsBusinessTransactionSearchCriteria();
        criteria.setCustomerId("2949565");
        List<BscsBusinessTransactionSearch> findBusinessTransactions = adapter.findBusinessTransactions(criteria);
        assertThat(findBusinessTransactions).isNotEmpty();
    }

    @Test(expected = SOIException.class)
    public void writeBookingRequest_unknownCsId() throws BscsInvalidIdException, BscsInvalidFieldException {
        // exception is not well managed (NullPointerException)
        BscsBookingRequest bookingRequest = bscsObjectFactory.createBscsBookingRequest();
        bookingRequest.setFeeType(EnumBookingFeeType.NON_RECURRING);
        bookingRequest.setFeeClass(3);
        bookingRequest.setActionCode(EnumBookingActionCode.INSERT);
        bookingRequest.setCustomerId("10");
        adapter.writeBookingRequest(bookingRequest, true);
    }

    @Test
    public void writeBookingRequest_unknownContractId() throws BscsInvalidFieldException {
        BscsBookingRequest bookingRequest = bscsObjectFactory.createBscsBookingRequest();
        bookingRequest.setContractId("10");
        try {
            adapter.writeBookingRequest(bookingRequest, true);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("10");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.CONTRACT_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeBookingRequest_unknownServiceCode() throws BscsInvalidIdException, BscsInvalidFieldException {
        try {
            BscsBookingRequest bookingRequest = bscsObjectFactory.createBscsBookingRequest();
            bookingRequest.setFeeType(EnumBookingFeeType.NON_RECURRING);
            bookingRequest.setFeeClass(3);
            bookingRequest.setActionCode(EnumBookingActionCode.INSERT);
            bookingRequest.setCustomerId("1001");
            bookingRequest.setServicePackageCode("28");
            bookingRequest.setRatePlanCode("4");
            bookingRequest.setRatePlanVersionCode(1l);
            bookingRequest.setServiceCode("999999999");
            adapter.writeBookingRequest(bookingRequest, true);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("999999999");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeBookingRequest_unknownServicePackageCode()
            throws BscsInvalidIdException, BscsInvalidFieldException {
        try {
            BscsBookingRequest bookingRequest = bscsObjectFactory.createBscsBookingRequest();
            bookingRequest.setFeeType(EnumBookingFeeType.NON_RECURRING);
            bookingRequest.setFeeClass(3);
            bookingRequest.setActionCode(EnumBookingActionCode.INSERT);
            bookingRequest.setCustomerId("1001");
            bookingRequest.setContractId("1001");
            bookingRequest.setServiceCode("249");
            bookingRequest.setServicePackageCode("999999999");
            bookingRequest.setRatePlanCode("4");
            bookingRequest.setRatePlanVersionCode(1l);
            adapter.writeBookingRequest(bookingRequest, true);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("249");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_ID);
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
            bookingRequest.setCustomerId("1001");
            bookingRequest.setContractId("1001");
            bookingRequest.setServiceCode("19");
            bookingRequest.setServicePackageCode("28");
            bookingRequest.setRatePlanCode("4");
            bookingRequest.setRatePlanVersionCode(1l);
            adapter.writeBookingRequest(bookingRequest, true);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("19");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_ID);
            return;
        }
        fail("No exception");
    }


    @Test(expected = SOIException.class)
    public void writeBookingRequest_NoUserLimit() throws BscsInvalidIdException, BscsInvalidFieldException {
        BscsBookingRequest bookingRequest = bscsObjectFactory.createBscsBookingRequest();
        bookingRequest.setFeeType(EnumBookingFeeType.NON_RECURRING);
        bookingRequest.setFeeClass(3);
        bookingRequest.setActionCode(EnumBookingActionCode.INSERT);
        bookingRequest.setCustomerId("1001");
        bookingRequest.setContractId("1001");
        bookingRequest.setServiceCode("249");
        bookingRequest.setServicePackageCode("28");
        bookingRequest.setRatePlanCode("4");
        bookingRequest.setRatePlanVersionCode(1l);
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
    public void getCreditsByDebit_ok() {
        List<BscsReferencedTransactionSearch> creditsByDebit = adapter.getCreditsByDebit(15149452L);
        assertThat(creditsByDebit).isNotEmpty();
    }

    @Test
    public void getCreditsByDebit_unknownId() {
        List<BscsReferencedTransactionSearch> creditsByDebit = adapter.getCreditsByDebit(0L);
        assertThat(creditsByDebit).isEmpty();
    }

}
