package com.orange.mea.apisi.billingaccount.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orange.apibss.billingaccount.model.BillingAccount;
import com.orange.apibss.common.exceptions.ApiException;
import com.orange.mea.apisi.billingaccount.backend.FindBillingAccountsByMsisdnBackend;
import com.orange.mea.apisi.billingaccount.backend.FindBillingAccountsByPartyBackend;
import com.orange.mea.apisi.billingaccount.backend.GetBillingAccountBackend;

/**
 * Service for BillingAccount requests management
 *
 * @author ecus6396
 *
 */
@Service
public class BillingAccountService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FindBillingAccountsByMsisdnBackend findBillingAccountsByMsisdnBackend;

    @Autowired
    private FindBillingAccountsByPartyBackend findBillingAccountsByPartyBackend;

    @Autowired
    private GetBillingAccountBackend getBillingAccountBackend;

    public List<BillingAccount> findBillingAccountsByMsisdn(final String msisdn) throws ApiException {
        logger.debug("Finding billingAccounts by msisdn with [{}]", msisdn);
        return findBillingAccountsByMsisdnBackend.findBillingAccountsByMsisdn(msisdn);
    }

    public BillingAccount getBillingAccount(final String billingAccountId) throws ApiException {
        logger.debug("Get billingAccount with [{}]", billingAccountId);
        return getBillingAccountBackend.getBillingAccount(billingAccountId);
    }

    public List<BillingAccount> findBillingAccountsByParty(final String customerIdPub) throws ApiException {
        logger.debug("Finding billingAccounts by party id with [{}]", customerIdPub);
        return findBillingAccountsByPartyBackend.findBillingAccountsByParty(customerIdPub);
    }

}
