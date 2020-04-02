package com.orange.bscs.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.orange.bscs.model.BscsBillDocument;
import com.orange.bscs.model.BscsBillingAccount;
import com.orange.bscs.model.BscsBillingAccountSearch;
import com.orange.bscs.model.BscsBillingAssignment;
import com.orange.bscs.model.billing.EnumBillingAccountReadMod;
import com.orange.bscs.model.billing.EnumBillingAccountSearchMod;

@Service
public class BscsBillingServiceAdapterV8 extends BscsBillingServiceAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<BscsBillingAssignment> getBillingAssignmentByContract(String contractId) {
        logger.error("BILLING_ACCOUNT_ASSIGNMENT.READ method does not exist in V8");
        throw new RuntimeException("Not available in V8");
    }

    @Override
    public List<BscsBillingAccountSearch> findBillingAccountsByCustomer(String customerId,
            EnumBillingAccountSearchMod mode) {
        logger.error("BILLING_ACCOUNT.SEARCH method does not exist in V8");
        throw new RuntimeException("Not available in V8");
    }

    @Override
    public BscsBillingAccount getBillingAccount(String billingAccountCode, EnumBillingAccountReadMod mode) {
        logger.error("BILLING_ACCOUNT.READ method does not exist in V8");
        throw new RuntimeException("Not available in V8");
    }

    @Override
    public BscsBillDocument getBillDocument(String docRef) {
        logger.error("BILLINGDOCUMENT.READ method does not exist in V8");
        throw new RuntimeException("Not available in V8");
    }

}
