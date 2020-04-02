package com.orange.mea.apisi.billingaccount.backend;

import java.util.List;

import com.orange.apibss.billingaccount.model.BillingAccount;
import com.orange.apibss.common.exceptions.ApiException;

public interface FindBillingAccountsByMsisdnBackend {

    /**
     * UC29, search by msisdn
     * 
     * @param msisdn
     * @return
     * @throws ApiException
     */
    List<BillingAccount> findBillingAccountsByMsisdn(String msisdn) throws ApiException;

}
