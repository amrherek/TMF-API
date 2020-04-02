package com.orange.mea.apisi.productordering.backend;

import com.orange.apibss.common.exceptions.ApiException;

public interface EmergencyBackend {

    /**
     * Add emergency credit
     * 
     * @param msisdn
     * @param productOfferingId
     * @param amount
     * @throws ApiException
     */
    void addEmergencyCredit(String msisdn, String productOfferingId, String amount) throws ApiException;

    /**
     * Add emergency data
     * 
     * @param msisdn
     * @param productOfferingId
     * @param amount
     * @throws ApiException
     */
    void addEmergencyData(String msisdn, String productOfferingId, String amount) throws ApiException;

    /**
     * Add emergency voice
     * 
     * @param msisdn
     * @param productOfferingId
     * @param amount
     * @throws ApiException
     */
    void addEmergencyVoice(String msisdn, String productOfferingId, String amount) throws ApiException;

}
