package com.orange.bscs.model;

import com.orange.bscs.model.billing.BSCSBillingAccount;

/**
 * Model used for result of BILLING_ACCOUNT.SEARCH command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsBillingAccountSearch {

    protected BSCSBillingAccount billingAccount;

    public BscsBillingAccountSearch(BSCSBillingAccount billingAccount) {
        this.billingAccount = billingAccount;
    }

    public abstract Long getId();

    public abstract String getCode();

}
