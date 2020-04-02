package com.orange.bscs.model;

import com.orange.bscs.model.billing.BSCSBillingAccount.BAVersion;
import com.orange.bscs.model.billing.EnumBillingAccountStatus;

/**
 * Model used for partial result of BILLING_ACCOUNT.READ command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsBillingAccountVersion {

    protected BAVersion billingAccountVersion;

    public BscsBillingAccountVersion(BAVersion billingAccountVersion) {
        this.billingAccountVersion = billingAccountVersion;
    }

    public abstract EnumBillingAccountStatus getStatus();

}
