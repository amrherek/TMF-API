package com.orange.bscs.model;

import com.orange.bscs.model.billing.BSCSBillingAccount.BAVersion;
import com.orange.bscs.model.billing.EnumBillingAccountStatus;

public class BscsBillingAccountVersionV9 extends BscsBillingAccountVersion {

    public BscsBillingAccountVersionV9(BAVersion billingAccountVersion) {
        super(billingAccountVersion);
    }

    @Override
    public EnumBillingAccountStatus getStatus() {
        // STATUS
        return billingAccountVersion.getStatus();
    }

}
