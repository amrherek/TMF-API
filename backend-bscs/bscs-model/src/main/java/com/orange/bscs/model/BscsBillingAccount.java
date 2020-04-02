package com.orange.bscs.model;

import java.util.List;

import com.orange.bscs.model.billing.BSCSBillingAccount;

/**
 * Model used for result of BILLING_ACCOUNT.READ command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsBillingAccount {

    protected BSCSBillingAccount billingAccount;

    public BscsBillingAccount(BSCSBillingAccount billingAccount) {
        this.billingAccount = billingAccount;
    }

    public abstract String getCode();

    public abstract String getCustomerId();

    public abstract List<BscsBillingAccountVersion> getVersions();

}
