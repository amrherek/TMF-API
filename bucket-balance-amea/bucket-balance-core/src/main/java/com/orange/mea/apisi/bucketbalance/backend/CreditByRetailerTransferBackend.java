package com.orange.mea.apisi.bucketbalance.backend;

import com.orange.apibss.common.exceptions.ApiException;

public interface CreditByRetailerTransferBackend {

    /**
     * @param id
     * @param retailerMsisdn
     * @param targetMsisdn
     * @param amount
     * @param unit
     * @param pin
     * @throws ApiException
     */
    void creditBucketBalanceByRetailerTransfert(String id, String retailerMsisdn, String targetMsisdn, Float amount,
            String unit, String pin) throws ApiException;
}
