package com.orange.mea.apisi.bucketbalance.backend;

import java.util.List;

import com.orange.apibss.bucketBalance.model.Characteristic;
import com.orange.apibss.common.exceptions.ApiException;

public interface CreditByValueBackend {

    /**
     * UM45: credit by value (cash or credit card)
     * 
     * @param id
     * @param msisdn
     * @param value
     * @param unit
     * @param comment
     * @param characteristics
     * @throws ApiException
     */
    void creditByValue(String id, String msisdn, Float value, String unit, String comment,
            List<Characteristic> characteristics) throws ApiException;

}
