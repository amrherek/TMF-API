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
import com.orange.bscs.config.BscsTestConfig;
import com.orange.bscs.model.BscsBookingRequest;
import com.orange.bscs.model.BscsBusinessTransaction;
import com.orange.bscs.model.BscsBusinessTransactionSearch;
import com.orange.bscs.model.BscsFinancialDocument;
import com.orange.bscs.model.BscsFinancialDocumentSearch;
import com.orange.bscs.model.BscsFinancialTransaction;
import com.orange.bscs.model.BscsFinancialTransactionDetail;
import com.orange.bscs.model.BscsFinancialTransactionSearch;
import com.orange.bscs.model.BscsPaymentMethod;
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
public class BscsAccountingServiceAdapterV9Test {

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
    public void getFinancialDocument_unknownId() {
        try {
            adapter.getFinancialDocument(99999999L);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("99999999");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.FINANCIAL_DOCUMENT_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getFinancialDocument_ok() throws BscsInvalidIdException {
        BscsFinancialDocument financialDocument = adapter.getFinancialDocument(12L);
        assertThat(financialDocument).isNotNull();
    }

    @Test
    public void findFinancialDocuments_unknownCustomerId() {
        BscsFinancialDocumentSearchCriteria criteria = bscsObjectFactory.createBscsFinancialDocumentSearchCriteria();
        criteria.setCustomerId("UNKNOWN");
        List<BscsFinancialDocumentSearch> findFinancialDocuments = adapter.findFinancialDocuments(criteria);
        assertThat(findFinancialDocuments).isEmpty();
    }

    @Test
    public void findFinancialDocuments_startDateInTheFuture() {
        BscsFinancialDocumentSearchCriteria criteria = bscsObjectFactory.createBscsFinancialDocumentSearchCriteria();
        criteria.setStartDate(new GregorianCalendar(2030, 0, 1).getTime());
        List<BscsFinancialDocumentSearch> findFinancialDocuments = adapter.findFinancialDocuments(criteria);
        assertThat(findFinancialDocuments).isEmpty();
    }

    @Test
    public void findFinancialDocuments_ok() {
        BscsFinancialDocumentSearchCriteria criteria = bscsObjectFactory.createBscsFinancialDocumentSearchCriteria();
        criteria.setCustomerId("CUST0000000047");
        List<BscsFinancialDocumentSearch> findFinancialDocuments = adapter.findFinancialDocuments(criteria);
        assertThat(findFinancialDocuments).isNotEmpty();
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
    public void findBusinessTransactions_ok() {
        BscsBusinessTransactionSearchCriteria criteria = bscsObjectFactory
                .createBscsBusinessTransactionSearchCriteria();
        criteria.setCustomerId("CUST0000001804");
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
            // we do not know which is the bad field
            assertThat(e.getName()).isNull();
            assertThat(e.getId()).isEqualTo("UNKNOWN");
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
            // we do not know which is the bad field
            assertThat(e.getName()).isNull();
            assertThat(e.getId()).isEqualTo("UNKNOWN");
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
            // we do not know which is the bad field
            assertThat(e.getName()).isNull();
            assertThat(e.getId()).isEqualTo("UNKNOWN");
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
            bookingRequest.setCustomerId("CUST0002194271");
            bookingRequest.setContractId("CONTR0000000566");
            bookingRequest.setServiceCode("Yzone"); // service code pub
            adapter.writeBookingRequest(bookingRequest, true);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("128"); // service code
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.SERVICE_ID);
            return;
        }
        fail("No exception");
    }

    @Test(expected = SOIException.class)
    public void writeBookingRequest_noAmount() throws BscsInvalidIdException, BscsInvalidFieldException {
        BscsBookingRequest bookingRequest = bscsObjectFactory.createBscsBookingRequest();
        bookingRequest.setFeeType(EnumBookingFeeType.NON_RECURRING);
        bookingRequest.setFeeClass(3);
        bookingRequest.setActionCode(EnumBookingActionCode.INSERT);
        bookingRequest.setCustomerId("CUST0002194271");
        bookingRequest.setContractId("CONTR0000000566");
        bookingRequest.setServiceCode("BAGP");
        bookingRequest.setServicePackageCode("BB50G");
        adapter.writeBookingRequest(bookingRequest, true);
    }

    @Test
    public void writeBookingRequest_ok() throws BscsInvalidIdException, BscsInvalidFieldException {
        BscsBookingRequest bookingRequest = bscsObjectFactory.createBscsBookingRequest();
        bookingRequest.setFeeType(EnumBookingFeeType.NON_RECURRING);
        bookingRequest.setFeeClass(3);
        bookingRequest.setActionCode(EnumBookingActionCode.INSERT);
        bookingRequest.setCustomerId("CUST0002194271");
        bookingRequest.setContractId("CONTR0000000566");
        bookingRequest.setServiceCode("Credi");
        bookingRequest.setServicePackageCode("OPTSP");
        adapter.writeBookingRequest(bookingRequest, true);
    }

    @Test
    public void getFinancialTransaction_unknownId() {
        try {
            adapter.getFinancialTransaction(99999999L);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("99999999");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.FINANCIAL_TRANSACTION_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void getFinancialTransaction_ok() throws BscsInvalidIdException {
        BscsFinancialTransaction financialTransaction = adapter.getFinancialTransaction(7245L);
        assertThat(financialTransaction).isNotNull();
    }

    @Test
    public void findFinancialTransactions_unknownCustomerId() {
        BscsFinancialTransactionSearchCriteria criteria = bscsObjectFactory
                .createBscsFinancialTransactionSearchCriteria();
        criteria.setCustomerNumericId(0L);
        List<BscsFinancialTransactionSearch> transactions = adapter.findFinancialTransactions(criteria);
        assertThat(transactions).isEmpty();
    }

    @Test
    public void findFinancialTransactions_startDateInTheFuture() {
        BscsFinancialTransactionSearchCriteria criteria = bscsObjectFactory
                .createBscsFinancialTransactionSearchCriteria();
        criteria.setStartDate(new GregorianCalendar(2030, 0, 1).getTime());
        List<BscsFinancialTransactionSearch> transactions = adapter.findFinancialTransactions(criteria);
        assertThat(transactions).isEmpty();
    }

    @Test
    public void findFinancialTransactions_ok() {
        BscsFinancialTransactionSearchCriteria criteria = bscsObjectFactory
                .createBscsFinancialTransactionSearchCriteria();
        criteria.setCustomerNumericId(47L);
        List<BscsFinancialTransactionSearch> transactions = adapter.findFinancialTransactions(criteria);
        assertThat(transactions).isNotEmpty();
    }

    @Test
    public void writeFinancialTransaction_unknownCsId() throws BscsInvalidFieldException, BscsInvalidIdException {
        BscsFinancialTransaction transaction = bscsObjectFactory.createBscsFinancialTransaction();
        transaction.setUseCaseCode("CE2DD");
        transaction.setCustomerId("UNKNOWN");
        transaction.setRef("Ref");
        BscsFinancialTransactionDetail detail = bscsObjectFactory.createBscsFinancialTransactionDetail();
        detail.setDocumentId(134382L);
        detail.setCredit(false);
        detail.setAmount(1.0);
        transaction.addDetail(detail);
        detail = bscsObjectFactory.createBscsFinancialTransactionDetail();
        try {
            adapter.writeFinancialTransaction(transaction, false);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("UNKNOWN");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.CUSTOMER_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeFinancialTransaction_unknownUseCaseCode()
            throws BscsInvalidFieldException, BscsInvalidIdException {
        BscsFinancialTransaction transaction = bscsObjectFactory.createBscsFinancialTransaction();
        transaction.setUseCaseCode("UNKNOWN");
        transaction.setCustomerId("CUST0000000147");
        transaction.setRef("Ref");
        BscsFinancialTransactionDetail detail = bscsObjectFactory.createBscsFinancialTransactionDetail();
        detail.setDocumentId(134382L);
        detail.setCredit(false);
        detail.setAmount(1.0);
        transaction.addDetail(detail);
        detail = bscsObjectFactory.createBscsFinancialTransactionDetail();
        try {
            adapter.writeFinancialTransaction(transaction, false);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("UNKNOWN");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.USE_CASE_CODE);
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeFinancialTransaction_unknownDocumentId() throws BscsInvalidFieldException, BscsInvalidIdException {
        BscsFinancialTransaction transaction = bscsObjectFactory.createBscsFinancialTransaction();
        transaction.setUseCaseCode("CE2DD");
        transaction.setCustomerId("CUST0000000147");
        transaction.setRef("Ref");
        BscsFinancialTransactionDetail detail = bscsObjectFactory.createBscsFinancialTransactionDetail();
        detail.setDocumentId(0L);
        detail.setCredit(false);
        detail.setAmount(1.0);
        transaction.addDetail(detail);
        detail = bscsObjectFactory.createBscsFinancialTransactionDetail();
        try {
            adapter.writeFinancialTransaction(transaction, false);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("0");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.FINANCIAL_DOCUMENT_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeFinancialTransaction_invalidAmount() throws BscsInvalidFieldException, BscsInvalidIdException {
        BscsFinancialTransaction transaction = bscsObjectFactory.createBscsFinancialTransaction();
        transaction.setUseCaseCode("CE2DD");
        transaction.setCustomerId("CUST0000000147");
        transaction.setRef("Ref");
        BscsFinancialTransactionDetail detail = bscsObjectFactory.createBscsFinancialTransactionDetail();
        detail.setDocumentId(134382L);
        detail.setCredit(false);
        detail.setAmount(-10.0);
        transaction.addDetail(detail);
        detail = bscsObjectFactory.createBscsFinancialTransactionDetail();
        try {
            adapter.writeFinancialTransaction(transaction, false);
        } catch (BscsInvalidFieldException e) {
            // value not returned
            assertThat(e.getValue()).isNull();
            assertThat(e.getName()).isNull();
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeFinancialTransaction_customerNotAssociatedToBill()
            throws BscsInvalidFieldException, BscsInvalidIdException {
        BscsFinancialTransaction transaction = bscsObjectFactory.createBscsFinancialTransaction();
        transaction.setUseCaseCode("CE2DD");
        transaction.setCustomerId("CUST0000000148");
        transaction.setRef("Ref");
        BscsFinancialTransactionDetail detail = bscsObjectFactory.createBscsFinancialTransactionDetail();
        detail.setDocumentId(134382L);
        detail.setCredit(false);
        detail.setAmount(-10.0);
        transaction.addDetail(detail);
        detail = bscsObjectFactory.createBscsFinancialTransactionDetail();
        try {
            adapter.writeFinancialTransaction(transaction, false);
        } catch (BscsInvalidFieldException e) {
            // value not returned
            assertThat(e.getValue()).isNull();
            assertThat(e.getName()).isNull();
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeFinancialTransaction_badCurrency() throws BscsInvalidFieldException, BscsInvalidIdException {
        BscsFinancialTransaction transaction = bscsObjectFactory.createBscsFinancialTransaction();
        transaction.setUseCaseCode("CE2DD");
        transaction.setCustomerId("CUST0000000147");
        transaction.setRef("Ref");
        BscsFinancialTransactionDetail detail = bscsObjectFactory.createBscsFinancialTransactionDetail();
        detail.setDocumentId(134382L);
        detail.setCredit(false);
        detail.setAmount(1.0, "AAA");
        transaction.addDetail(detail);
        try {
            adapter.writeFinancialTransaction(transaction, false);
        } catch (BscsInvalidFieldException e) {
            assertThat(e.getValue()).isEqualTo("AAA");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.CURRENCY);
            return;
        }
        fail("No exception");
    }

    /**
     * Total amount not coherent with bill amount => generate a credit
     * 
     * @throws BscsInvalidIdException
     * @throws BscsInvalidFieldException
     */
    @Test
    public void writeFinancialTransaction_totalAmountNotCoherent()
            throws BscsInvalidIdException, BscsInvalidFieldException {
        BscsFinancialTransaction transaction = bscsObjectFactory.createBscsFinancialTransaction();
        transaction.setUseCaseCode("CE2DD");
        transaction.setCustomerId("CUST0000000147");
        transaction.setRef("Ref");
        transaction.setAmount(2.0);
        BscsFinancialTransactionDetail detail = bscsObjectFactory.createBscsFinancialTransactionDetail();
        detail.setDocumentId(134382L);
        // no impact for this attribute, but mandatory
        detail.setCredit(false);
        detail.setAmount(1.0);
        transaction.addDetail(detail);
        adapter.writeFinancialTransaction(transaction, false);
    }

    @Test
    public void writeFinancialTransaction_ok() throws BscsInvalidIdException, BscsInvalidFieldException {
        BscsFinancialTransaction transaction = bscsObjectFactory.createBscsFinancialTransaction();
        transaction.setUseCaseCode("CE2DD");
        transaction.setCustomerId("CUST0000000147");
        transaction.setRef("Ref");
        BscsFinancialTransactionDetail detail = bscsObjectFactory.createBscsFinancialTransactionDetail();
        detail.setDocumentId(134382L);
        // no impact for this attribute, but mandatory
        detail.setCredit(false);
        detail.setAmount(1.0);
        transaction.addDetail(detail);
        adapter.writeFinancialTransaction(transaction, false);
    }

}
