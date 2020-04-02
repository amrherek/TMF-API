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

import com.orange.apibss.bucketBalance.model.CreditBucketBalanceByValue;
import com.orange.apibss.common.audit.AuditContext;
import com.orange.apibss.common.exceptions.ApiException;
import com.orange.mea.apisi.bucketbalance.service.BucketBalanceService;

/**
 * Rest Controller for Credit bucket balance by value
 *
 * @author JWCK2987
 *
 */
@RestController
@RequestMapping("/creditBucketBalanceByValue")
public class CreditBucketBalanceByValueController {

    @Autowired
    private BucketBalanceService bucketBalanceService;

    @Autowired
    private AuditContext auditContext;

    /**
     * Credit prepaid account using cash or credit card (by value) on the
     * subscriber with the given msisdn
     *
     * @param creditBucketBalanceByValue
     * @return
     * @throws ApiException
     */
    @RolesAllowed("ROLE_BUCKET_BALANCE")
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CreditBucketBalanceByValue postCreditBucketBalanceByValue(
            @RequestBody final CreditBucketBalanceByValue creditBucketBalanceByValue) throws ApiException {
        auditContext.open("creditBucketBalanceByValue", creditBucketBalanceByValue.getPublicKey() != null
                ? creditBucketBalanceByValue.getPublicKey().getId() : null);

        return bucketBalanceService.creditByValue(creditBucketBalanceByValue);
    }

}
