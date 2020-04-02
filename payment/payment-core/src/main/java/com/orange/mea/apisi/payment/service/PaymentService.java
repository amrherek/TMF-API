package com.orange.mea.apisi.payment.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.model.PartialResult;
import com.orange.apibss.payment.model.Payment;
import com.orange.mea.apisi.payment.backend.CreatePaymentBackend;
import com.orange.mea.apisi.payment.backend.FindPaymentsByPartyBackend;
import com.orange.mea.apisi.payment.backend.GetPaymentBackend;

@Service
public class PaymentService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected GetPaymentBackend getPaymentBackend;

    @Autowired
    private FindPaymentsByPartyBackend findPaymentByPartyBackend;

    @Autowired
    protected CreatePaymentBackend createPaymentBackend;

    @Autowired
    protected PaymentServiceValidator paymentServiceValidator;

    @Value("${payments.searchLimit:50}")
    private Integer searchLimit;

    public Payment getPayment(String paymentId) throws ApiException {
        logger.debug("Get payment with [{}]", paymentId);
        return getPaymentBackend.getPayment(paymentId);
    }

    public PartialResult<Payment> findPaymentsByParty(String payerId, Date startDate, Date endDate, Integer limit)
            throws ApiException {
        logger.debug("Finding payments by party with [{}]", payerId);
        // validation
        paymentServiceValidator.validate(startDate, endDate, limit);

        // transform limit
        limit = transformLimit(limit);

        return findPaymentByPartyBackend.findPaymentsByParty(payerId, startDate, endDate, limit);
    }

    private Integer transformLimit(Integer limit) {
        // limit from request
        Integer res = limit;
        if (limit == null) {
            // default limit
            res = searchLimit;
        } else if (limit == -1) {
            // no limit
            res = null;
        }
        return res;
    }

    public Payment create(Payment payment) throws ApiException {
        // validation
        paymentServiceValidator.validate(payment);

        // processing
        String paymentId = createPaymentBackend.createPayment(payment);

        // answer
        return getPaymentBackend.getPayment(paymentId);
    }

}
