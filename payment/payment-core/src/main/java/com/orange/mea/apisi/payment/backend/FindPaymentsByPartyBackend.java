package com.orange.mea.apisi.payment.backend;

import java.util.Date;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.model.PartialResult;
import com.orange.apibss.payment.model.Payment;

public interface FindPaymentsByPartyBackend {

    /**
     * Search payments by partyId, and optionally by dates
     * 
     * @param partyId
     * @param startDate
     * @param endDate
     * @param limit
     * @return
     * @throws ApiException
     */
    PartialResult<Payment> findPaymentsByParty(String partyId, Date startDate, Date endDate, Integer limit)
            throws ApiException;

}
