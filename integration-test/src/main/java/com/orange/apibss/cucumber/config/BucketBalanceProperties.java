package com.orange.apibss.cucumber.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.orange.apibss.bucketBalance.model.CreditBucketBalanceTransaction;
import com.orange.apibss.bucketBalance.model.UsageReport;

import lombok.Data;

/**
 * Properties for product catalog tests
 *
 * @author Stéphanie Gerbaud
 */
@Data
@ConfigurationProperties(prefix = "bucketBalance", ignoreUnknownFields = false)
public class BucketBalanceProperties {
    
    private Msisdn msisdn;
    
    private List<UsageReport> prepaidUsageReports;
    
    private List<UsageReport> postpaidUsageReports;
    
    private List<CreditBucketBalanceTransaction> creditBucketBalanceTransactions;

}
