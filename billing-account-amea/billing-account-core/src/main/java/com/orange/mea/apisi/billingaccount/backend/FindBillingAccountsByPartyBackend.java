package com.orange.mea.apisi.billingaccount.backend;

import java.util.List;

import com.orange.apibss.billingaccount.model.BillingAccount;
import com.orange.apibss.common.exceptions.ApiException;

public interface FindBillingAccountsByPartyBackend {

    /**
     * UC29: search by party id
     * 
     * @param customerId
     * @return
     * @throws ApiException
     */
    List<BillingAccount> findBillingAccountsByParty(String customerId) throws ApiException;

}
