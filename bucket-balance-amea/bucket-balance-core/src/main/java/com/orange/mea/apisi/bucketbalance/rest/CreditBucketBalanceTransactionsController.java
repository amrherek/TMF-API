package com.orange.mea.apisi.bucketbalance.rest;

import javax.annotation.security.RolesAllowed;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orange.apibss.bucketBalance.model.CreditBucketBalanceTransaction;
import com.orange.apibss.bucketBalance.model.RatingType;
import com.orange.apibss.bucketBalance.model.Type;
import com.orange.apibss.common.audit.AuditContext;
import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.model.PartialResult;
import com.orange.apibss.common.utils.EnumCaseInsensitiveConverter;
import com.orange.mea.apisi.bucketbalance.service.BucketBalanceService;

/**
 * Rest Controller for Credit bucket balance transactions
 *
 * @author JWCK2987
 *
 */
@RestController
@RequestMapping("/creditBucketBalanceTransactions")
public class CreditBucketBalanceTransactionsController {

    @Autowired
    private BucketBalanceService bucketBalanceService;

    @Autowired
    private AuditContext auditContext;

    /**
     * Query the historic refill on a subscriber identified by its public key
     *
     * @param msisdn
     * @param startDate
     * @param endDate
     * @param limit
     * @param type
     * @param ratingType
     * @param pinCode
     * @return
     * @throws ApiException
     */
    @RolesAllowed("ROLE_BUCKET_BALANCE")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PartialResult<CreditBucketBalanceTransaction> getCreditBucketBalanceTransactions(
            @RequestParam(required = true, name = "publicKey.id") final String msisdn,
            @RequestParam(required = true, name = "date.gte") @DateTimeFormat(iso = ISO.DATE) final LocalDate startDate,
            @RequestParam(required = false, name = "date.lte") @DateTimeFormat(iso = ISO.DATE) final LocalDate endDate,
            @RequestParam(required = false, name = "limit") final Integer limit,
            @RequestParam(required = false, name = "type") final Type type,
            @RequestParam(required = false, name = "ratingType") final RatingType ratingType,
            @RequestHeader(value = "X-OAPI-PinCode", required = false) String pinCode)
            throws ApiException {
        auditContext.open("getCreditBucketBalanceTransactions", msisdn);

        return bucketBalanceService.findRefillHistoric(msisdn, ratingType, startDate, endDate, limit, type, pinCode);
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // enum
        binder.registerCustomEditor(Type.class, new EnumCaseInsensitiveConverter<Type>(Type.class));
        binder.registerCustomEditor(RatingType.class, new EnumCaseInsensitiveConverter<RatingType>(RatingType.class));
    }

}
