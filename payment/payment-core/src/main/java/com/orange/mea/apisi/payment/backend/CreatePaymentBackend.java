package com.orange.mea.apisi.payment.backend;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.payment.model.Payment;

public interface CreatePaymentBackend {

    /**
     * Create a payment
     * 
     * @param payment
     * @return
     * @throws ApiException
     */
    String createPayment(Payment payment) throws ApiException;

}
