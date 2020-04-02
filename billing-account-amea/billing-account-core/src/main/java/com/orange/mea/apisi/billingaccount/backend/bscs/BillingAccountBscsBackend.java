package com.orange.mea.apisi.billingaccount.backend.bscs;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orange.apibss.billingaccount.model.BillingAccount;
import com.orange.apibss.common.exceptions.badrequest.BadRequestException;
import com.orange.apibss.common.exceptions.badrequest.NotFoundException;
import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.bscs.api.aop.TransactionalBscs;
import com.orange.bscs.model.BscsBillingAccount;
import com.orange.bscs.model.BscsBillingAccountSearch;
import com.orange.bscs.model.BscsBillingAssignment;
import com.orange.bscs.model.BscsContractSearch;
import com.orange.bscs.model.BscsCustomer;
import com.orange.bscs.model.BscsPaymentArrangement;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;
import com.orange.mea.apisi.billingaccount.backend.FindBillingAccountsByMsisdnBackend;
import com.orange.mea.apisi.billingaccount.backend.FindBillingAccountsByPartyBackend;
import com.orange.mea.apisi.billingaccount.backend.GetBillingAccountBackend;
import com.orange.mea.apisi.billingaccount.backend.bscs.service.BscsBillingAccountService;

/**
 * Service for BillingAccount requests management
 *
 * @author ecus6396
 *
 */
@Service
public class BillingAccountBscsBackend
        implements FindBillingAccountsByMsisdnBackend, FindBillingAccountsByPartyBackend, GetBillingAccountBackend {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected BscsBillingAccountService bscsBillingAccountService;

    @Autowired
    protected BscsToBillingAccountTransformer bscsToBillingAccountTransformer;

    @Override
    @TransactionalBscs
    public List<BillingAccount> findBillingAccountsByMsisdn(final String msisdn)
            throws BadRequestException, TechnicalException {
        final List<BillingAccount> res = new ArrayList<>();

        // CONTRACT.SEARCH => get contractIdPub
        BscsContractSearch contract;
        try {
            contract = bscsBillingAccountService.getContract(msisdn);
        } catch (BscsInvalidFieldException e) {
            logger.debug("no contract found with this msisdn", e);
            return res;
        }

        // BILLING_ACCOUNT_ASSIGNMENT.READ => get billingAccountId
        final List<BscsBillingAssignment> billingAssignments = bscsBillingAccountService
                .getBillingAssignments(contract.getId());
        try {
            for (final BscsBillingAssignment billing : billingAssignments) {
                // BILLING_ACCOUNT.READ
                BscsBillingAccount billingAccountRead = bscsBillingAccountService
                        .getBillingAccountRead(billing.getBillingAccountCode());
                // PAYMENT_ARRANGEMENT.READ
                BscsPaymentArrangement paymentArrangement = bscsBillingAccountService
                        .getPaymentArrangement(billingAccountRead.getCustomerId());
                // CUSTOMER.READ => to get billingCycle
                BscsCustomer customer = bscsBillingAccountService.getCustomer(billingAccountRead.getCustomerId());
                res.add(bscsToBillingAccountTransformer.doTransform(billingAccountRead, paymentArrangement, customer));
            }
            return res;
        } catch (BscsInvalidIdException e) {
            throw new TechnicalException(e.getMessage(), e);
        }

    }

    @Override
    @TransactionalBscs
    public BillingAccount getBillingAccount(final String billingAccountId) throws BadRequestException, TechnicalException {
        // BILLING_ACCOUNT.READ
        BscsBillingAccount billingAccountRead;
        try {
            billingAccountRead = bscsBillingAccountService.getBillingAccountRead(billingAccountId);
        } catch (BscsInvalidIdException e) {
            logger.debug("No Billing account found with this id", e);
            throw new NotFoundException("No Billing account found with id: " + billingAccountId);
        }
        try {
            // PAYMENT_ARRANGEMENT.READ
            final BscsPaymentArrangement paymentArrangement = bscsBillingAccountService
                    .getPaymentArrangement(billingAccountRead.getCustomerId());
            // CUSTOMER.READ => to get billingCycle
            BscsCustomer customer = bscsBillingAccountService.getCustomer(billingAccountRead.getCustomerId());
            return bscsToBillingAccountTransformer.doTransform(billingAccountRead, paymentArrangement, customer);
        } catch (BscsInvalidIdException e) {
            throw new TechnicalException(e.getMessage(), e);
        }
    }

    @Override
    @TransactionalBscs
    public List<BillingAccount> findBillingAccountsByParty(final String customerIdPub)
            throws BadRequestException, TechnicalException {
        final List<BillingAccount> res = new ArrayList<>();

        // BILLING_ACCOUNT.SEARCH => get billingAccountId
        final List<BscsBillingAccountSearch> billingAccounts = bscsBillingAccountService
                .searchBillingAccounts(customerIdPub);
        try {
            for (final BscsBillingAccountSearch bscsBa : billingAccounts) {
                // BILLING_ACCOUNT.READ
                BscsBillingAccount billingAccountRead = bscsBillingAccountService
                        .getBillingAccountRead(bscsBa.getCode());
                // PAYMENT_ARRANGEMENT.READ
                final BscsPaymentArrangement paymentArrangement = bscsBillingAccountService
                        .getPaymentArrangement(billingAccountRead.getCustomerId());
                // CUSTOMER.READ => to get billingCycle
                BscsCustomer customer = bscsBillingAccountService.getCustomer(customerIdPub);
                res.add(bscsToBillingAccountTransformer.doTransform(billingAccountRead, paymentArrangement, customer));
            }
            return res;
        } catch (BscsInvalidIdException e) {
            throw new TechnicalException(e.getMessage(), e);
        }
    }

}
