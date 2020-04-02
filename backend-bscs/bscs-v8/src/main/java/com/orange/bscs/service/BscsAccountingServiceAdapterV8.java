package com.orange.bscs.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.orange.bscs.model.BscsBookingRequest;
import com.orange.bscs.model.BscsBusinessTransactionSearch;
import com.orange.bscs.model.BscsFinancialDocument;
import com.orange.bscs.model.BscsFinancialDocumentSearch;
import com.orange.bscs.model.BscsFinancialTransaction;
import com.orange.bscs.model.BscsFinancialTransactionSearch;
import com.orange.bscs.model.criteria.BscsBusinessTransactionSearchCriteria;
import com.orange.bscs.model.criteria.BscsFinancialDocumentSearchCriteria;
import com.orange.bscs.model.criteria.BscsFinancialTransactionSearchCriteria;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;

@Service
public class BscsAccountingServiceAdapterV8 extends BscsAccountingServiceAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public BscsFinancialDocument getFinancialDocument(Long documentId) {
        logger.error("FINANCIAL_DOCUMENT.READ method does not exist in V8");
        throw new RuntimeException("Not available in V8");
    }

    @Override
    public List<BscsFinancialDocumentSearch> findFinancialDocuments(BscsFinancialDocumentSearchCriteria criteria) {
        logger.error("FINANCIAL_DOCUMENT.SEARCH method does not exist in IXR2");
        throw new RuntimeException("Not available in IXR2");
    }

    @Override
    public List<BscsBusinessTransactionSearch> findBusinessTransactions(
            BscsBusinessTransactionSearchCriteria criteria) {
        List<BscsBusinessTransactionSearch> transactions = super.findBusinessTransactions(criteria);
        List<BscsBusinessTransactionSearch> res = new ArrayList<>();
        // manually process wishedResult
        Integer wishedResults = criteria.getWishedRecords();
        int size = transactions.size();
        if (wishedResults != null && wishedResults > 0 && wishedResults < size) {
            size = wishedResults;
        }
        for (int i = 0; i < size; i++) {
            res.add(transactions.get(i));
        }
        return res;
    }

    @Override
    public void writeBookingRequest(BscsBookingRequest bookingRequest, boolean commit)
            throws BscsInvalidIdException, BscsInvalidFieldException {
        try {
            super.writeBookingRequest(bookingRequest, commit);
        } catch (BscsInvalidIdException ex) {
            // explicit message
            if (ex.getName() == null) {
                throw new BscsInvalidIdException(null, ex.getId(), "Unknown public key: {" + ex.getId() + "}");
            } else {
                switch (ex.getName()) {
                case CUSTOMER_ID:
                    throw new BscsInvalidIdException(ex.getName(), ex.getId(),
                            "Unknown customer with id {" + ex.getId() + "}");
                case CONTRACT_ID:
                    throw new BscsInvalidIdException(ex.getName(), ex.getId(),
                            "Unknown contract with id {" + ex.getId() + "}");
                default:
                    throw ex;
                }
            }
        } catch (BscsInvalidFieldException ex) {
            throw new BscsInvalidFieldException(ex.getName(), ex.getValue(),
                    "Service does not belong to service package in rate plan");
        }
    }

    @Override
    public BscsFinancialTransaction getFinancialTransaction(Long transactionId) throws BscsInvalidIdException {
        logger.error("FINANCIAL_TRANSACTION.READ method does not exist in V8");
        throw new RuntimeException("Not available in V8");
    }

    @Override
    public List<BscsFinancialTransactionSearch> findFinancialTransactions(
            BscsFinancialTransactionSearchCriteria criteria) {
        logger.error("FINANCIAL_TRANSACTION.SEARCH method does not exist in V8");
        throw new RuntimeException("Not available in V8");
    }

    @Override
    public String writeFinancialTransaction(BscsFinancialTransaction transaction, boolean commit) {
        logger.error("FINANCIAL_TRANSACTION.WRITE method does not exist in V8");
        throw new RuntimeException("Not available in V8");
    }

}
