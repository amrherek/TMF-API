package com.orange.bscs.model;

import com.orange.bscs.model.billing.BSCSBillingAccount;

public class BscsBillingAccountSearchV9 extends BscsBillingAccountSearch {

    public BscsBillingAccountSearchV9(BSCSBillingAccount billingAccount) {
        super(billingAccount);
    }

    @Override
    public Long getId() {
        // BILLING_ACCOUNT_ID
        return billingAccount.getBillingAccountId();
    }

    @Override
    public String getCode() {
        // BILLING_ACCOUNT_CODE
        return billingAccount.getBillingAccountCode();
    }

}
