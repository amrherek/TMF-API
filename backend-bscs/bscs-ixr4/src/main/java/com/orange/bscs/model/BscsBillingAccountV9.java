package com.orange.bscs.model;

import java.util.ArrayList;
import java.util.List;

import com.orange.bscs.model.billing.BSCSBillingAccount;
import com.orange.bscs.model.billing.BSCSBillingAccount.BAVersion;

public class BscsBillingAccountV9 extends BscsBillingAccount {

    public BscsBillingAccountV9(BSCSBillingAccount billingAccount) {
        super(billingAccount);
    }

    @Override
    public String getCode() {
        // BILLING_ACCOUNT_CODE
        return billingAccount.getBillingAccountCode();
    }

    @Override
    public String getCustomerId() {
        // CS_ID_PUB
        return billingAccount.getCustomerIdPub();
    }

    @Override
    public List<BscsBillingAccountVersion> getVersions() {
        // LIST_OF_BILLING_ACCOUNT_VERSIONS
        List<BAVersion> versions = billingAccount.getVersions();
        List<BscsBillingAccountVersion> res = new ArrayList<>();
        for (BAVersion version : versions) {
            res.add(new BscsBillingAccountVersionV9(version));
        }
        return res;
    }

}
