package com.orange.mea.apisi.bucketbalance.backend;

import com.orange.apibss.common.exceptions.ApiException;

public interface DebitRetailerByValueBackend {

    /**
     * 
     * @param id
     * @param msisdn
     * @param value
     * @param unit
     * @param pin
     * @throws ApiException
     */
    void debitRetailerByValue(String id, String msisdn, Float value, String unit, String pin) throws ApiException;

}
