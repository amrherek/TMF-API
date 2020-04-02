package com.orange.mea.apisi.bucketbalance.rest;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.orange.apibss.bucketBalance.model.CreditBucketBalanceByTransfer;
import com.orange.apibss.common.audit.AuditContext;
import com.orange.apibss.common.exceptions.ApiException;
import com.orange.mea.apisi.bucketbalance.service.BucketBalanceService;

/**
 * Rest Controller for Credit bucket balance by transfer
 *
 * @author JWCK2987
 *
 */
@RestController
@RequestMapping("/creditBucketBalanceByTransfer")
public class CreditBucketBalanceByTransferController {

    @Autowired
    private BucketBalanceService bucketBalanceService;

    @Autowired
    private AuditContext auditContext;

    /**
     * Credit prepaid account using transfer between accounts
     * 
     * @param creditBucketBalanceByTransfer
     * @return
     * @throws ApiException
     */
    @RolesAllowed("ROLE_BUCKET_BALANCE")
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CreditBucketBalanceByTransfer postCreditBucketBalanceByTransfer(
            @RequestBody final CreditBucketBalanceByTransfer creditBucketBalanceByTransfer) throws ApiException {
        auditContext.open("creditBucketBalanceByTransfer", creditBucketBalanceByTransfer.getTargetPublicKey() != null
                ? creditBucketBalanceByTransfer.getTargetPublicKey().getId() : null);

        return bucketBalanceService.creditByTransfer(creditBucketBalanceByTransfer);
    }

}
