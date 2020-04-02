package com.orange.mea.apisi.payment.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.orange.apibss.common.exceptions.badrequest.BadParameterFormatException;
import com.orange.apibss.common.exceptions.badrequest.BadParameterValueException;
import com.orange.apibss.common.exceptions.badrequest.BadRequestException;
import com.orange.apibss.common.exceptions.badrequest.MissingParameterException;
import com.orange.apibss.payment.model.Payment;
import com.orange.apibss.payment.model.PaymentItem;

@Component
public class PaymentServiceValidator {

    /**
     * Validate required fields for payment creation
     * 
     * @param payment
     * @throws BadRequestException
     */
    public void validate(Payment payment) throws BadRequestException {
        if (StringUtils.isBlank(payment.getCorrelatorId())) {
            throw new MissingParameterException("correlatorId");
        }
        if (payment.getTransactionType() == null) {
            throw new MissingParameterException("transactionType");
        }
        if (payment.getPayer() == null || payment.getPayer().getId() == null) {
            throw new MissingParameterException("payer.id");
        }
        if (payment.getPaymentItem() == null || payment.getPaymentItem().isEmpty()) {
            throw new MissingParameterException("paymentItem");
        }
        if (payment.getTotalAmount() == null || payment.getTotalAmount().getAmount() == null) {
            throw new MissingParameterException("totalAmount.amount");
        }
        for (PaymentItem paymentItem : payment.getPaymentItem()) {
            if (paymentItem.getItem() == null || paymentItem.getItem().getId() == null) {
                throw new MissingParameterException("paymentItem.item.id");
            }
            if (!StringUtils.isNumeric(paymentItem.getItem().getId())) {
                throw new BadParameterFormatException("paymentItem.item.id", paymentItem.getItem().getId(),
                        "a numeric value");
            }
            if (paymentItem.getTotalAmount() == null || paymentItem.getTotalAmount().getAmount() == null) {
                throw new MissingParameterException("paymentItem.totalAmount.amount");
            }
        }
        if (payment.getPaymentMethod() != null && payment.getPaymentMethod().size() > 1) {
            throw new BadParameterValueException("only one paymentMethod allowed");
        }
        if (payment.getPaymentMethod() != null && !payment.getPaymentMethod().isEmpty()
                && payment.getPaymentMethod().get(0).getType() == null) {
            throw new MissingParameterException("paymentMethod.type");
        }
    }

    /**
     * Validate startDate, endDate and limit
     * 
     * @param startDate
     * @param endDate
     * @param limit
     * @throws BadParameterValueException
     */
    public void validate(Date startDate, Date endDate, Integer limit) throws BadParameterValueException {
        if (endDate != null && startDate != null && endDate.before(startDate)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            throw new BadParameterValueException("paymentDate.lte", dateFormat.format(endDate),
                    "after " + dateFormat.format(startDate));
        }
        if (limit != null && limit != -1 && limit < 0) {
            throw new BadParameterValueException("limit", limit.toString(), "a positive integer or -1");
        }
    }

}
