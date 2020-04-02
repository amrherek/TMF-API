package com.orange.mea.apisi.billingaccount.backend.bscs.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orange.bscs.model.BscsBillingAccount;
import com.orange.bscs.model.BscsBillingAccountSearch;
import com.orange.bscs.model.BscsBillingAssignment;
import com.orange.bscs.model.BscsContractSearch;
import com.orange.bscs.model.BscsCustomer;
import com.orange.bscs.model.BscsPaymentArrangement;
import com.orange.bscs.model.billing.EnumBillingAccountReadMod;
import com.orange.bscs.model.billing.EnumBillingAccountSearchMod;
import com.orange.bscs.service.BscsBillingServiceAdapter;
import com.orange.bscs.service.BscsBusinessPartnerServiceAdapter;
import com.orange.bscs.service.BscsContractServiceAdapter;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;

/**
 * Client for bscs calls
 *
 * @author ecus6396
 *
 */
@Service
public class BscsBillingAccountService {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BscsContractServiceAdapter contractService;

    @Autowired
    private BscsBusinessPartnerServiceAdapter businessPartnerServiceAdapter;

    @Autowired
    private BscsBillingServiceAdapter billingServiceAdapter;

    /**
     * call CONTRACTS.SEARCH.
     *
     * @param msisdn
     * @return String customerIdPub
     * @throws BscsInvalidFieldException
     */
    public BscsContractSearch getContract(final String msisdn) throws BscsInvalidFieldException {
        // execute contracts.search (without history)
        return contractService.findContractByMsisdn(msisdn);
    }

    /**
     * call CUSTOMER.READ
     * 
     * @param customerId
     * @return BscsCustomer customer
     * @throws BscsInvalidIdException
     */
    public BscsCustomer getCustomer(final String customerId) throws BscsInvalidIdException {
        // execute customer.read
        return businessPartnerServiceAdapter.getCustomer(customerId);
    }

    /**
     * call BILLING_ACCOUNT.READ
     *
     * @param code
     * @return BscsBillingAccount billing account
     * @throws BscsInvalidIdException
     */
    public BscsBillingAccount getBillingAccountRead(final String code) throws BscsInvalidIdException {
        return billingServiceAdapter.getBillingAccount(code, EnumBillingAccountReadMod.LATEST_VERSION);
    }

    /**
     * call PAYMENT_ARRANGEMENTS.READ
     *
     * @param customerId
     * @return
     * @throws BscsInvalidIdException
     */
    public BscsPaymentArrangement getPaymentArrangement(final String customerId) throws BscsInvalidIdException {
        return businessPartnerServiceAdapter.getCurrentPaymentArrangement(customerId);
    }

    /**
     * Get a list of BscsBillingAssignment from a contract public id
     *
     * @param contractId
     * @return
     */
    public List<BscsBillingAssignment> getBillingAssignments(final String contractId) {
        return billingServiceAdapter.getBillingAssignmentByContract(contractId);
    }

    /**
     * Search billing accounts from a customer id pub
     *
     * @param customerIdPub
     * @return
     */
    public List<BscsBillingAccountSearch> searchBillingAccounts(final String customerIdPub) {
        // execute BILLING_ACCOUNT.SEARCH
        return billingServiceAdapter.findBillingAccountsByCustomer(customerIdPub, EnumBillingAccountSearchMod.ALL);
    }

}
