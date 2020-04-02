package com.orange.mea.apisi.billingaccount.rest;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orange.apibss.billingaccount.model.BillingAccount;
import com.orange.apibss.common.audit.AuditContext;
import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.badrequest.BadRequestException;
import com.orange.apibss.common.exceptions.badrequest.MissingParameterException;
import com.orange.mea.apisi.billingaccount.service.BillingAccountService;

/**
 * Rest Controller for billingAccounts
 *
 */
@RestController
@RequestMapping("/billingAccount")
public class BillingAccountController {

    @Autowired
    private BillingAccountService billingAccountService;

    @Autowired
    private AuditContext auditContext;

    /**
     * Find all billingAccounts based on the publicKey
     *
     * @param msisdn
     * @return
     * @throws BadRequestException
     * @throws ApiException
     */
    @RolesAllowed("ROLE_BILLING_ACCOUNT")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, params = "publicKey")
    public List<BillingAccount> findBillingAccountsByPublicKey(
            @RequestParam(required = true, name = "publicKey") final String msisdn) throws ApiException {
        auditContext.open("findBillingAccountsByPublicKey", msisdn);
        if (msisdn.isEmpty()) {
            throw new MissingParameterException("publicKey");
        }
        return billingAccountService.findBillingAccountsByMsisdn(msisdn);
    }

    /**
     * Find all billingAccounts based on the party id
     *
     * @param partyId
     * @return
     * @throws BadRequestException
     * @throws ApiException
     */
    @RolesAllowed("ROLE_BILLING_ACCOUNT")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, params = "party.id")
    public List<BillingAccount> findBillingAccountsByParty(
            @RequestParam(required = true, name = "party.id") final String partyId) throws ApiException {
        auditContext.open("findBillingAccountsByParty", null);
        if (partyId.isEmpty()) {
            throw new MissingParameterException("party.id");
        }
        return billingAccountService.findBillingAccountsByParty(partyId);
    }

    /**
     * Get a billing account from its id
     *
     * @param billingAccountId
     * @return
     * @throws ApiException
     */
    @RolesAllowed("ROLE_BILLING_ACCOUNT")
    @RequestMapping(method = RequestMethod.GET, value = "/{billingAccountId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BillingAccount getBillingAccount(@PathVariable final String billingAccountId) throws ApiException {
        auditContext.open("getBillingAccount", null);
        return billingAccountService.getBillingAccount(billingAccountId);
    }

}
