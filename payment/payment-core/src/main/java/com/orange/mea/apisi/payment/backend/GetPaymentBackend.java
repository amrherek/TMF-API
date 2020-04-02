package com.orange.mea.apisi.payment.backend;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.payment.model.Payment;

public interface GetPaymentBackend {

    /**
     * GET payment by id
     * 
     * @param paymentId
     * @return
     * @throws ApiException
     */
    Payment getPayment(String paymentId) throws ApiException;

}
