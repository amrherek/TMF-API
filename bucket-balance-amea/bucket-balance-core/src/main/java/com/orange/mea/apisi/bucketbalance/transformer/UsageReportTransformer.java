package com.orange.mea.apisi.bucketbalance.transformer;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import com.orange.apibss.bucketBalance.model.PublicKey;
import com.orange.apibss.bucketBalance.model.UsageReport;

@Component
public class UsageReportTransformer {

    /**
     * Build a usage report with the current date and the given msisdn
     * 
     * @param msisdn
     * @return
     */
    public UsageReport buildUsageReport(String msisdn) {
        UsageReport usageReport = new UsageReport();
        usageReport.setDate(DateTime.now());
        PublicKey publicKey = new PublicKey();
        publicKey.setId(msisdn);
        publicKey.setName("msisdn");
        usageReport.setPublicKey(publicKey);
        return usageReport;
    }

}
