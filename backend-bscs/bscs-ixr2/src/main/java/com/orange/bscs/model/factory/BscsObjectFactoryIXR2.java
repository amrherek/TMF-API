package com.orange.bscs.model.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.model.BscsContractSearch;
import com.orange.bscs.model.BscsContractSearchIXR2;
import com.orange.bscs.model.BscsFinancialDocument;
import com.orange.bscs.model.BscsFinancialDocumentDetail;
import com.orange.bscs.model.BscsFinancialDocumentSearch;
import com.orange.bscs.model.BscsFinancialTransaction;
import com.orange.bscs.model.BscsFinancialTransactionDetail;
import com.orange.bscs.model.BscsFinancialTransactionSearch;
import com.orange.bscs.model.BscsRatePlan;
import com.orange.bscs.model.BscsRatePlanIXR2;
import com.orange.bscs.model.billing.BSCSDocuments;
import com.orange.bscs.model.contract.BSCSContract;
import com.orange.bscs.model.criteria.BscsBillingAssignmentSearchCriteria;
import com.orange.bscs.model.criteria.BscsBillingAssignmentSearchCriteriaIXR2;
import com.orange.bscs.model.criteria.BscsBusinessTransactionSearchCriteria;
import com.orange.bscs.model.criteria.BscsBusinessTransactionSearchCriteriaIXR2;
import com.orange.bscs.model.criteria.BscsFinancialDocumentSearchCriteria;
import com.orange.bscs.model.criteria.BscsFinancialTransactionSearchCriteria;
import com.orange.bscs.model.product.BSCSRatePlan;

@Component
@Primary
public class BscsObjectFactoryIXR2 extends BscsObjectFactoryV9 {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public BscsRatePlan createBscsRatePlan(BSCSRatePlan ratePlan) {
        if (ratePlan == null) {
            return null;
        }
        return new BscsRatePlanIXR2(ratePlan);
    }

    @Override
    public BscsContractSearch createBscsContractSearch(BSCSContract contract) {
        if (contract == null) {
            return null;
        }
        return new BscsContractSearchIXR2(contract);
    }

    @Override
    public BscsBillingAssignmentSearchCriteria createBscsBillingAssignmentSearchCriteria() {
        return new BscsBillingAssignmentSearchCriteriaIXR2();
    }

    @Override
    public BscsFinancialDocument createBscsFinancialDocument(BSCSDocuments document) {
        logger.error("FINANCIAL_DOCUMENT does not exist in IXR2");
        throw new RuntimeException("Not available in IXR2");
    }

    @Override
    public BscsFinancialDocumentSearch createBscsFinancialDocumentSearch(BSCSDocuments document) {
        logger.error("FINANCIAL_DOCUMENT does not exist in IXR2");
        throw new RuntimeException("Not available in IXR2");
    }

    @Override
    public BscsFinancialDocumentSearchCriteria createBscsFinancialDocumentSearchCriteria() {
        logger.error("FINANCIAL_DOCUMENT does not exist in IXR2");
        throw new RuntimeException("Not available in IXR2");
    }

    @Override
    public BscsBusinessTransactionSearchCriteria createBscsBusinessTransactionSearchCriteria() {
        return new BscsBusinessTransactionSearchCriteriaIXR2();
    }

    @Override
    public BscsFinancialTransaction createBscsFinancialTransaction(BSCSModel transaction) {
        logger.error("FINANCIAL_TRANSACTION does not exist in IXR2");
        throw new RuntimeException("Not available in IXR2");
    }

    @Override
    public BscsFinancialTransactionSearch createBscsFinancialTransactionSearch(BSCSModel transaction) {
        logger.error("FINANCIAL_TRANSACTION does not exist in IXR2");
        throw new RuntimeException("Not available in IXR2");
    }

    @Override
    public BscsFinancialTransactionSearchCriteria createBscsFinancialTransactionSearchCriteria() {
        logger.error("FINANCIAL_TRANSACTION does not exist in IXR2");
        throw new RuntimeException("Not available in IXR2");
    }

    @Override
    public BscsFinancialTransaction createBscsFinancialTransaction() {
        logger.error("FINANCIAL_TRANSACTION does not exist in IXR2");
        throw new RuntimeException("Not available in IXR2");
    }

    @Override
    public BscsFinancialTransactionDetail createBscsFinancialTransactionDetail() {
        logger.error("FINANCIAL_TRANSACTION does not exist in IXR2");
        throw new RuntimeException("Not available in IXR2");
    }

    @Override
    public BscsFinancialTransactionDetail createBscsFinancialTransactionDetail(BSCSModel detail) {
        logger.error("FINANCIAL_TRANSACTION does not exist in IXR2");
        throw new RuntimeException("Not available in IXR2");
    }

    @Override
    public BscsFinancialDocumentDetail createBscsFinancialDocumentDetail(BSCSModel detail) {
        logger.error("FINANCIAL_DOCUMENT does not exist in IXR2");
        throw new RuntimeException("Not available in IXR2");
    }

}
