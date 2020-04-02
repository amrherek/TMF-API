package com.orange.mea.apisi.bucketbalance.rest;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orange.apibss.bucketBalance.model.RatingType;
import com.orange.apibss.bucketBalance.model.UsageReport;
import com.orange.apibss.common.audit.AuditContext;
import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.utils.EnumCaseInsensitiveConverter;
import com.orange.mea.apisi.bucketbalance.service.BucketBalanceService;

/**
 * Rest Controller for Usage report
 *
 * @author JWCK2987
 *
 */
@RestController
@RequestMapping("/usageReport")
public class UsageReportController {

    @Autowired
    private BucketBalanceService bucketBalanceService;

    @Autowired
    private AuditContext auditContext;

    /**
     * Query the usage report related to a device identified by its public key
     *
     * @param msisdn
     * @param ratingType
     * @param units
     * @return
     * @throws ApiException
     */
    @RolesAllowed("ROLE_BUCKET_BALANCE")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UsageReport> getUsageReport(@RequestParam(required = true, name = "publicKey.id") final String msisdn,
            @RequestParam(required = true, name = "ratingType") final RatingType ratingType,
            @RequestParam(value = "buckets.bucketBalances.unit", required = false) List<String> units)
            throws ApiException {
        auditContext.open("getUsageReports", msisdn);

        return bucketBalanceService.findUsageReport(msisdn, ratingType, units);
    }

    @InitBinder
    protected void initBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(RatingType.class, new EnumCaseInsensitiveConverter<RatingType>(RatingType.class));
    }

}
