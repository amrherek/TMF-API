package com.orange.mea.apisi.bucketbalance.rest;

import javax.annotation.security.RolesAllowed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.orange.apibss.bucketBalance.model.CreditBucketBalanceByRetailerTransfer;
import com.orange.apibss.common.audit.AuditContext;
import com.orange.apibss.common.exceptions.ApiException;
import com.orange.mea.apisi.bucketbalance.service.BucketBalanceService;

/**
 * Rest Controller for Debit Retailer Bucket Balance By Value
 *
 * @author wissem
 *
 */
@RestController
@RequestMapping("/creditBucketBalanceByRetailerTransfer")
public class CreditBucketBalanceByRetailerTransferController {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BucketBalanceService bucketBalanceService;

    @Autowired
    private AuditContext auditContext;

    /**
     * Credit Bucket Balance By Retailer Transfer
     *
     * @param creditBucketBalanceByRetailerTransfer
     * @return
     * @throws ApiException
     */
    @RolesAllowed("ROLE_BUCKET_BALANCE")
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CreditBucketBalanceByRetailerTransfer postCreditBucketBalanceByRetailerTransfert(
            @RequestBody final CreditBucketBalanceByRetailerTransfer creditBucketBalanceByRetailerTransfer)
            throws ApiException {
        auditContext.open("creditBucketBalanceByRetailerTransfer",
                creditBucketBalanceByRetailerTransfer.getTargetPublicKey() != null
                        ? creditBucketBalanceByRetailerTransfer.getTargetPublicKey().getId() : null);
        
        return bucketBalanceService.creditBucketBalanceByRetailerTransfer(creditBucketBalanceByRetailerTransfer);
    }

}
