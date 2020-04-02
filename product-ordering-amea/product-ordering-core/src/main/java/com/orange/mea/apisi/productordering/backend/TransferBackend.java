package com.orange.mea.apisi.productordering.backend;

import com.orange.apibss.common.exceptions.ApiException;

public interface TransferBackend {

    /**
     * Perform a transfer
     * 
     * @param msisdn
     * @param targetMsisdn
     * @param volume
     * @throws ApiException
     */
    void transfer(String msisdn, String targetMsisdn, String volume) throws ApiException;

}
