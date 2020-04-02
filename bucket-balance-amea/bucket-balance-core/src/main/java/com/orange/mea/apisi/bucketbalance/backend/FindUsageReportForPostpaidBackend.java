package com.orange.mea.apisi.bucketbalance.backend;

import java.util.List;

import com.orange.apibss.bucketBalance.model.UsageReport;
import com.orange.apibss.common.exceptions.ApiException;

public interface FindUsageReportForPostpaidBackend {

    /**
     * UC19: find usage reports for postpaid contract
     * 
     * @param msisdn
     * @param units
     * @return
     * @throws ApiException
     */
    List<UsageReport> findUsageReportForPostpaid(String msisdn, List<String> units) throws ApiException;

}
