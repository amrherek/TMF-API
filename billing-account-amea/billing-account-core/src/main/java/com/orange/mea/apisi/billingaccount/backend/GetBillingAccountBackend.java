package com.orange.mea.apisi.billingaccount.backend;

import com.orange.apibss.billingaccount.model.BillingAccount;
import com.orange.apibss.common.exceptions.ApiException;

public interface GetBillingAccountBackend {

    /**
     * UC29: search by billinc account id
     * 
     * @param billingAccountId
     * @return
     * @throws ApiException
     */
    BillingAccount getBillingAccount(String billingAccountId) throws ApiException;

}
