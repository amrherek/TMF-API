package com.orange.apibss.cucumber.bucketBalance;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.orange.apibss.bucketBalance.model.CreditBucketBalanceByTransfer;
import com.orange.apibss.bucketBalance.model.CreditBucketBalanceByValue;
import com.orange.apibss.bucketBalance.model.CreditBucketBalanceByVoucher;
import com.orange.apibss.bucketBalance.model.CreditBucketBalanceTransaction;
import com.orange.apibss.bucketBalance.model.UsageReport;

import lombok.Data;

@Component
// New bean for each scenario
@Scope("cucumber-glue")
@Data
public class BucketBalanceSharedData {

    private List<UsageReport> usageReports;
    
    private List<CreditBucketBalanceTransaction> creditBucketBalanceTransaction;
    
    /**
     * CreditBucketBalanceByValue used for calling the API
     */
    private CreditBucketBalanceByValue testCreditBucketBalanceByValue;
    
    /**
     * CreditBucketBalanceByValue result of an API call
     */
    private CreditBucketBalanceByValue creditBucketBalanceByValue;
    
    /**
     * CreditBucketBalanceByVoucher used for calling the API
     */
    private CreditBucketBalanceByVoucher testCreditBucketBalanceByVoucher;
    
    /**
     * CreditBucketBalanceByVoucher result of an API call
     */
    private CreditBucketBalanceByVoucher creditBucketBalanceByVoucher;
    
    /**
     * CreditBucketBalanceByTransfer used for calling the API
     */
    private CreditBucketBalanceByTransfer testCreditBucketBalanceByTransfer;
    
    /**
     * CreditBucketBalanceByVoucher result of an API call
     */
    private CreditBucketBalanceByTransfer creditBucketBalanceByTransfer;
    
}
