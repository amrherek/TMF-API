package com.orange.mea.apisi.bucketbalance.backend;

import java.util.List;

import com.orange.apibss.bucketBalance.model.UsageReport;
import com.orange.apibss.common.exceptions.ApiException;

public interface FindUsageReportForPrepaidHybridBackend {

    /**
     * UC19: find usage reports for prepaid/hybrid contract
     * 
     * @param msisdn
     * @param units
     * @return
     * @throws ApiException
     */
    List<UsageReport> findUsageReportForPrepaidHybrid(String msisdn, List<String> units) throws ApiException;

}
