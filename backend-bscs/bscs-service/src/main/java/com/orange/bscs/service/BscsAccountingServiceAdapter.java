package com.orange.bscs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.lang.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.orange.bscs.api.connection.ConnectionHolder;
import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.model.exception.SOIException;
import com.orange.bscs.cms.servicelayeradapter.AccountingServiceAdapterI;
import com.orange.bscs.model.BscsBookingRequest;
import com.orange.bscs.model.BscsBusinessTransaction;
import com.orange.bscs.model.BscsBusinessTransactionSearch;
import com.orange.bscs.model.BscsDebitsByCredit;
import com.orange.bscs.model.BscsFinancialDocument;
import com.orange.bscs.model.BscsFinancialDocumentDetail;
import com.orange.bscs.model.BscsFinancialDocumentSearch;
import com.orange.bscs.model.BscsFinancialTransaction;
import com.orange.bscs.model.BscsFinancialTransactionDetail;
import com.orange.bscs.model.BscsFinancialTransactionSearch;
import com.orange.bscs.model.BscsPayment;
import com.orange.bscs.model.BscsPaymentMethod;
import com.orange.bscs.model.BscsReferencedTransactionSearch;
import com.orange.bscs.model.accounting.BSCSPaymentMethod;
import com.orange.bscs.model.billing.BSCSDocuments;
import com.orange.bscs.model.billing.BusinessTransaction;
import com.orange.bscs.model.criteria.BscsBusinessTransactionSearchCriteria;
import com.orange.bscs.model.criteria.BscsFinancialDocumentSearchCriteria;
import com.orange.bscs.model.criteria.BscsFinancialTransactionSearchCriteria;
import com.orange.bscs.model.criteria.BscsReferencedTransactionSearchCriteria;
import com.orange.bscs.model.factory.BscsObjectFactory;
import com.orange.bscs.service.exception.BscsFieldExceptionEnum;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;

public abstract class BscsAccountingServiceAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected AccountingServiceAdapterI accountingServiceAdapter;

    @Autowired
    protected BscsObjectFactory bscsObjectFactory;

    /**
     * execute PAYMENT_METHODS.READ
     * 
     * @return
     */
    public List<BscsPaymentMethod> getPaymentMethods() {
        List<BSCSPaymentMethod> bscsPaymentMethods = accountingServiceAdapter.paymentMethodsRead();
        List<BscsPaymentMethod> res = new ArrayList<>();
        for (BSCSPaymentMethod paymentMethod : bscsPaymentMethods) {
            res.add(bscsObjectFactory.createBscsPaymentMethod(paymentMethod));
        }
        return res;
    }

    /**
     * execute FINANCIAL_DOCUMENT.READ
     * 
     * @param documentId
     * @return
     * @throws BscsInvalidIdException
     */
    public BscsFinancialDocument getFinancialDocument(Long documentId) throws BscsInvalidIdException {
        try {
            BSCSDocuments financialTransaction = accountingServiceAdapter.financialDocumentRead(documentId);
            return bscsObjectFactory.createBscsFinancialDocument(financialTransaction);
        } catch (SOIException ex) {
            logger.debug("BSCS FINANCIAL_DOCUMENT.READ error with code: " + ex.getCode());
            if (ex.getCode() != null) {
                if (ex.getCode().contains("FinancialDocumentNotFound")) {
                    // not found
                    throw new BscsInvalidIdException(BscsFieldExceptionEnum.FINANCIAL_DOCUMENT_ID,
                            ObjectUtils.toString(documentId, null), ex.getMessage());
                }
            }
            throw ex;
        }
    }

    /**
     * execute FINANCIAL_DOCUMENT.SEARCH
     * 
     * @param criteria
     * @return
     */
    public List<BscsFinancialDocumentSearch> findFinancialDocuments(BscsFinancialDocumentSearchCriteria criteria) {
        List<BSCSDocuments> financialDocuments = accountingServiceAdapter
                .financialDocumentSearch(criteria.getBscsModel());
        List<BscsFinancialDocumentSearch> res = new ArrayList<>();
        for (BSCSDocuments document : financialDocuments) {
            res.add(bscsObjectFactory.createBscsFinancialDocumentSearch(document));
        }
        return res;
    }

    /**
     * execute FINANCIAL_DOCUMENT.SEARCH and search for invoices, ignoring
     * business unit and get newest entries first
     * 
     * @param criteria
     * @return
     */
    public List<BscsFinancialDocumentSearch> findInvoices(BscsFinancialDocumentSearchCriteria criteria) {
        // ingore BU
        criteria.setIgnoreBusinessUnitIndicator(true);
        // document type: invoice
        List<String> documentTypes = new ArrayList<String>();
        documentTypes.add("IN");
        criteria.setDocumentTypes(documentTypes);
        // descending order (newest entries first)
        criteria.setOrder('d');
        return findFinancialDocuments(criteria);
    }

    /**
     * execute BUSINESS_TRANSACTION.READ
     * 
     * @param transactionId
     * @return
     * @throws BscsInvalidIdException
     */
    public BscsBusinessTransaction getBusinessTransaction(Long transactionId) throws BscsInvalidIdException {
        try {
            BusinessTransaction transaction = accountingServiceAdapter.businessTransactionRead(transactionId);
            if (transaction == null) {
                throw new BscsInvalidIdException(BscsFieldExceptionEnum.BUSINESS_TRANSACTION_ID,
                        ObjectUtils.toString(transactionId, null), "Business transaction {" + transactionId + "}");
            }
            return bscsObjectFactory.createBscsBusinessTransaction(transaction);
        } catch (SOIException ex) {
            logger.debug("BSCS BUSINESS_TRANSACTION.READ error with code: " + ex.getCode());
            if (ex.getCode() != null) {
                if (ex.getCode().contains("billdoc_fail_searchtrans") || ex.getCode().contains("RC2002-001")) {
                    // not found
                    throw new BscsInvalidIdException(BscsFieldExceptionEnum.BUSINESS_TRANSACTION_ID,
                            ObjectUtils.toString(transactionId, null), ex.getMessage());
                }
            }
            throw ex;
        }
    }

    /**
     * Search for invoice. execute BUSINESS_TRANSACTIONS.SEARCH
     * 
     * @param criteria
     * @return
     */
    public List<BscsBusinessTransactionSearch> findBusinessTransactions(
            BscsBusinessTransactionSearchCriteria criteria) {
        try {
            // document type: invoice
            criteria.setTransactionType("IN");
            List<BusinessTransaction> transactions = accountingServiceAdapter
                    .businessTransactionSearch(criteria.getBscsModel());
            // transactions are in the ascending order (the first is the oldest)
            // => we want to reverse them
            List<BscsBusinessTransactionSearch> res = new ArrayList<>();

            // Generate an iterator. Start just after the last element.
            ListIterator<BusinessTransaction> li = transactions.listIterator(transactions.size());
            while (li.hasPrevious()) {
                res.add(bscsObjectFactory.createBscsBusinessTransactionSearch(li.previous()));
            }
            return res;
        } catch (SOIException ex) {
            logger.debug("BSCS BUSINESS_TRANSACTIONS.SEARCH error with code: " + ex.getCode());
            if (ex.getCode() != null && ex.getCode().contains("BILLSRV.common_map_pub_key_0")) {
                // bad public key given
                return new ArrayList<>();
            }
            throw ex;
        }

    }

    /**
     * Execute BOOKING_REQUEST.WRITE and optionally commit
     * 
     * @param bookingRequest
     * @param commit
     * @throws BscsInvalidIdException
     * @throws BscsInvalidFieldException
     */
    public void writeBookingRequest(BscsBookingRequest bookingRequest, boolean commit)
            throws BscsInvalidIdException, BscsInvalidFieldException {
        try {
            accountingServiceAdapter.bookingRequestWrite(bookingRequest.getBscsModel());
            if (commit) {
                ConnectionHolder.getCurrentConnection().commit();
            }
        } catch (SOIException ex) {
            logger.debug("BSCS BOOKING_REQUEST.WRITE error with code: " + ex.getCode());
            if (ex.getCode() != null) {
                if (ex.getCode().contains("FUNC_FRMWK_SRV.id0468")) {
                    // we do not know which is the bad field
                    throw new BscsInvalidIdException(null, ex.getFirstArg(), ex.getMessage());
                }
                if (ex.getCode().contains("RC6701")) {
                    throw new BscsInvalidIdException(BscsFieldExceptionEnum.CUSTOMER_ID, ex.getFirstArg(),
                            ex.getMessage());
                }
                if (ex.getCode().contains("RC6700")) {
                    // unknown contract id
                    throw new BscsInvalidIdException(BscsFieldExceptionEnum.CONTRACT_ID, ex.getFirstArg(),
                            ex.getMessage());
                }
                if (ex.getCode().contains("RC6703-001")) {
                    // service does not belong to service package in rate plan
                    throw new BscsInvalidFieldException(BscsFieldExceptionEnum.SERVICE_ID, ex.getFirstArg(),
                            ex.getMessage());
                }
            }
            throw ex;
        }
    }
    
    /**
     * Execute PAYMENT.WRITE and optionally commit
     * 
     * @param payment
     * @param commit
     * @return
     * @throws BscsInvalidIdException
     */
    public String writePayment(BscsPayment payment, boolean commit) throws BscsInvalidIdException {
        String payementID = null;
        try {
            payementID = accountingServiceAdapter.paymentWrite(payment.getModel()).toString();
            if (commit) {
                ConnectionHolder.getCurrentConnection().commit();
            }
        } catch (SOIException ex) {
            logger.debug("BSCS PAYMENT.WRITE error with code: " + ex.getCode());
            // TODO verify the error code
            if (ex.getCode() != null) {
                if (ex.getCode().contains("BILLSRV.common_map_pub_key_0")) {
                    // unknown contract id
                    throw new BscsInvalidIdException(BscsFieldExceptionEnum.CUSTOMER_ID, ex.getFirstArg(),
                            ex.getMessage());
                }
            }
            throw ex;
        }
        return payementID;
    }
   
   
    /**
     * Search for payments. execute REFERENCED_TRANSACTION.SEARCH
     *
     * @param criteria
     * @return
     * @throws BscsInvalidIdException
     * @throws BscsInvalidFieldException 
     */
    public List<BscsReferencedTransactionSearch> findReferencedTransactions(
            BscsReferencedTransactionSearchCriteria criteria) throws BscsInvalidIdException, BscsInvalidFieldException {
        try {
            List<BSCSModel> referencedtransactions = accountingServiceAdapter
                    .referencedTransactionSearch(criteria.getBscsModel());
            List<BscsReferencedTransactionSearch> res = new ArrayList<>();
            for (BSCSModel transaction : referencedtransactions) {
                res.add(bscsObjectFactory.createBscsReferencedTransactionSearch(transaction));
            }
            return res;
        } catch (SOIException ex) {
            logger.debug("BSCS REFERENCED_TRANSACTIONS.SEARCH error with code: " + ex.getCode());
            throw ex;
        }

    }

    /**
     * DEBITSBYCREDIT.READ
     * 
     * @param idTransaction
     * @return
     * @throws BscsInvalidIdException
     * @throws BscsInvalidFieldException
     */
    public List<BscsDebitsByCredit> getDebitsByCredit(Long idTransaction)
            throws BscsInvalidIdException, BscsInvalidFieldException {
        try {

            List<BSCSModel> referencedtransactions = accountingServiceAdapter.debitsByCreditRead(idTransaction);
            List<BscsDebitsByCredit> res = new ArrayList<>();
            for (BSCSModel transaction : referencedtransactions) {
                res.add(bscsObjectFactory.createBscsDebitByCredit(transaction));
            }
            return res;
        } catch (SOIException ex) {
            logger.debug("BDEBITSBYCREDIT.READ: " + ex.getCode());
            throw ex;
        }

    }

    /**
     * execute FINANCIAL_TRANSACTION.READ
     * 
     * @param transactionId
     * @return
     * @throws BscsInvalidIdException
     */
    public BscsFinancialTransaction getFinancialTransaction(Long transactionId) throws BscsInvalidIdException {
        try {
            BSCSModel transaction = accountingServiceAdapter.financialTransactionRead(transactionId);
            return bscsObjectFactory.createBscsFinancialTransaction(transaction);
        } catch (SOIException ex) {
            logger.debug("BSCS FINANCIAL_TRANSACTION.READ error with code: " + ex.getCode());
            if (ex.getCode() != null) {
                if (ex.getCode().contains("FinancialTransactionNotFound") || ex.getCode().contains("RC2002-001")) {
                    // not found
                    throw new BscsInvalidIdException(BscsFieldExceptionEnum.FINANCIAL_TRANSACTION_ID,
                            ObjectUtils.toString(transactionId, null), ex.getMessage());
                }
            }
            throw ex;
        }
    }

    /**
     * Execute FINANCIAL_TRANSACTION.SEARCH
     * 
     * @param criteria
     * @return
     */
    public List<BscsFinancialTransactionSearch> findFinancialTransactions(
            BscsFinancialTransactionSearchCriteria criteria) {
        try {
            List<BSCSModel> transactions = accountingServiceAdapter.financialTransactionSearch(criteria.getBscsModel());
            List<BscsFinancialTransactionSearch> res = new ArrayList<>();
            for (BSCSModel transaction : transactions) {
                res.add(bscsObjectFactory.createBscsFinancialTransactionSearch(transaction));
            }
            return res;
        } catch (SOIException ex) {
            logger.debug("BSCS FINANCIAL_TRANSACTION.SEARCH error with code: " + ex.getCode());
            if (ex.getCode() != null && ex.getCode().contains("FUNC_FRMWK_SRV.id0468")) {
                // bad public key given
                return new ArrayList<>();
            }
            throw ex;
        }

    }

    public String writeFinancialTransaction(BscsFinancialTransaction transaction, boolean commit)
            throws BscsInvalidIdException, BscsInvalidFieldException {
        try {
            Long transactionId = accountingServiceAdapter.financialTransactionWrite(transaction.getBscsModel());
            if (commit) {
                ConnectionHolder.getCurrentConnection().commit();
            }
            return transactionId.toString();
        } catch (SOIException ex) {
            logger.debug("BSCS FINANCIAL_TRANSACTION.WRITE error with code: " + ex.getCode());
            if (ex.getCode() != null) {
                if (ex.getCode().contains("FUNC_FRMWK_SRV.id0468")) {
                    // unknown public key (customer?)
                    throw new BscsInvalidIdException(BscsFieldExceptionEnum.CUSTOMER_ID, ex.getFirstArg(),
                            ex.getMessage());
                }
                if (ex.getCode().contains("WrongUseCaseCode")) {
                    // bad use case code
                    throw new BscsInvalidFieldException(BscsFieldExceptionEnum.USE_CASE_CODE, ex.getFirstArg(),
                            ex.getMessage());
                }
                if (ex.getCode().contains("FinancialDocumentNotFound")) {
                    // Bad financial document id
                    throw new BscsInvalidIdException(BscsFieldExceptionEnum.FINANCIAL_DOCUMENT_ID, ex.getFirstArg(),
                            ex.getMessage());
                }
                if (ex.getCode().contains("TransactionCouldNotBeProcessed")) {
                    // invalid amount, bill not owned by customer ...
                    throw new BscsInvalidFieldException(null, null, ex.getMessage());
                }
                if (ex.getCode().contains("NoValidCurrency")) {
                    throw new BscsInvalidFieldException(BscsFieldExceptionEnum.CURRENCY, ex.getFirstArg(),
                            "Invalid currency: " + ex.getFirstArg());
                }
            }
            throw ex;
        }
    }

    /**
     * execute FINANCIAL_TRANSACTION_DETAIL.READ
     * 
     * @param transactionId
     * @return
     * @throws BscsInvalidIdException
     */
    public List<BscsFinancialTransactionDetail> getFinancialTransactionDetails(Long transactionId)
            throws BscsInvalidIdException {
        try {
            List<BSCSModel> details = accountingServiceAdapter.financialTransactionDetailRead(transactionId);
            List<BscsFinancialTransactionDetail> res = new ArrayList<>();
            for (BSCSModel detail : details) {
                res.add(bscsObjectFactory.createBscsFinancialTransactionDetail(detail));
            }
            return res;
        } catch (SOIException ex) {
            logger.debug("BSCS FINANCIAL_TRANSACTION_DETAIL.READ error with code: " + ex.getCode());
            if (ex.getCode() != null) {
                if (ex.getCode().contains("FinancialTransactionNotFound") || ex.getCode().contains("RC2002-001")) {
                    // not found
                    throw new BscsInvalidIdException(BscsFieldExceptionEnum.FINANCIAL_TRANSACTION_ID,
                            ObjectUtils.toString(transactionId, null), ex.getMessage());
                }
            }
            throw ex;
        }
    }

    /**
     * execute FINANCIAL_DOCUMENT_DETAIL.READ
     * 
     * @param documentId
     * @return
     * @throws BscsInvalidIdException
     */
    public List<BscsFinancialDocumentDetail> getFinancialDocumentDetails(Long documentId)
            throws BscsInvalidIdException {
        try {
            List<BSCSModel> details = accountingServiceAdapter.financialDocumentDetailRead(documentId);
            List<BscsFinancialDocumentDetail> res = new ArrayList<>();
            for (BSCSModel detail : details) {
                res.add(bscsObjectFactory.createBscsFinancialDocumentDetail(detail));
            }
            return res;
        } catch (SOIException ex) {
            logger.debug("BSCS FINANCIAL_DOCUMENT_DETAIL.READ error with code: " + ex.getCode());
            if (ex.getCode() != null) {
                if (ex.getCode().contains("FinancialDocumentNotFound")) {
                    // not found
                    throw new BscsInvalidIdException(BscsFieldExceptionEnum.FINANCIAL_DOCUMENT_ID,
                            ObjectUtils.toString(documentId, null), ex.getMessage());
                }
            }
            throw ex;
        }
    }

    /**
     * execute CREDITSBYDEBIT.READ
     * 
     * @param transactionId
     * @return
     */
    public List<BscsReferencedTransactionSearch> getCreditsByDebit(Long transactionId) {
        List<BSCSModel> referencedTransactions = accountingServiceAdapter.creditsByDebitsRead(transactionId);
        List<BscsReferencedTransactionSearch> res = new ArrayList<>();
        for (BSCSModel transaction : referencedTransactions) {
            res.add(bscsObjectFactory.createBscsReferencedTransactionSearch(transaction));
        }
        return res;
    }

}
