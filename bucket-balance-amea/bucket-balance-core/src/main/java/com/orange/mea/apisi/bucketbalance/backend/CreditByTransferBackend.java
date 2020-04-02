package com.orange.mea.apisi.bucketbalance.backend;

import java.util.List;

import com.orange.apibss.bucketBalance.model.Characteristic;
import com.orange.apibss.common.exceptions.badrequest.BadRequestException;
import com.orange.apibss.common.exceptions.technical.TechnicalException;

public interface CreditByTransferBackend {

    /**
     * UM24: credit by transfer
     * 
     * @param originMsisdn
     * @param targetMsisdn
     * @param value
     * @param unit
     * @param comment
     * @param characteristic
     * @throws BadRequestException
     * @throws TechnicalException
     */
    void creditByTransfer(String originMsisdn, String targetMsisdn, Float value, String unit, String comment,
            List<Characteristic> characteristic) throws TechnicalException, BadRequestException;

}
