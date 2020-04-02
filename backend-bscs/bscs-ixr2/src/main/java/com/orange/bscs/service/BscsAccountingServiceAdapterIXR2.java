package com.orange.bscs.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.orange.bscs.model.BscsBusinessTransactionSearch;
import com.orange.bscs.model.BscsFinancialDocument;
import com.orange.bscs.model.BscsFinancialDocumentSearch;
import com.orange.bscs.model.BscsFinancialTransaction;
import com.orange.bscs.model.BscsFinancialTransactionSearch;
import com.orange.bscs.model.criteria.BscsBusinessTransactionSearchCriteria;
import com.orange.bscs.model.criteria.BscsFinancialDocumentSearchCriteria;
import com.orange.bscs.model.criteria.BscsFinancialTransactionSearchCriteria;
import com.orange.bscs.service.exception.BscsInvalidIdException;

@Service
@Primary
public class BscsAccountingServiceAdapterIXR2 extends BscsAccountingServiceAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public BscsFinancialDocument getFinancialDocument(Long documentId) {
        logger.error("FINANCIAL_DOCUMENT.READ method does not exist in IXR2");
        throw new RuntimeException("Not available in IXR2");
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
        if (wishedResults != null && wishedResults >= 0 && wishedResults < size) {
            size = wishedResults;
        }
        for (int i = 0; i < size; i++) {
            res.add(transactions.get(i));
        }
        return res;
    }

    @Override
    public BscsFinancialTransaction getFinancialTransaction(Long transactionId) throws BscsInvalidIdException {
        logger.error("FINANCIAL_TRANSACTION.READ method does not exist in IXR2");
        throw new RuntimeException("Not available in IXR2");
    }

    @Override
    public List<BscsFinancialTransactionSearch> findFinancialTransactions(
            BscsFinancialTransactionSearchCriteria criteria) {
        logger.error("FINANCIAL_TRANSACTION.SEARCH method does not exist in IXR2");
        throw new RuntimeException("Not available in IXR2");
    }

    @Override
    public String writeFinancialTransaction(BscsFinancialTransaction transaction, boolean commit) {
        logger.error("FINANCIAL_TRANSACTION.WRITE method does not exist in IXR2");
        throw new RuntimeException("Not available in IXR2");
    }

}
