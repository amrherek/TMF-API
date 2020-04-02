package com.orange.mea.apisi.payment.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.orange.apibss.common.exceptions.badrequest.BadParameterValueException;
import com.orange.apibss.common.exceptions.badrequest.BadRequestException;
import com.orange.apibss.common.exceptions.badrequest.MissingParameterException;
import com.orange.apibss.payment.model.Amount;
import com.orange.apibss.payment.model.Item;
import com.orange.apibss.payment.model.Payment;
import com.orange.apibss.payment.model.PaymentItem;
import com.orange.apibss.payment.model.RelatedParty;
import com.orange.apibss.payment.model.TransactionType;

@RunWith(JUnit4.class)
public class PaymentServiceValidatorTest {

    // -------------------------
    // validate date and limit
    // -------------------------

    @Test
    public void validate_noLimit_noDate_ok() throws BadParameterValueException {
        PaymentServiceValidator validator = new PaymentServiceValidator();
        validator.validate(null, null, null);
    }

    @Test
    public void validate_startDate_endDate_ok() throws BadParameterValueException, ParseException {
        PaymentServiceValidator validator = new PaymentServiceValidator();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        validator.validate(dateFormat.parse("2016-01-01"), dateFormat.parse("2017-01-01"), null);
    }

    @Test(expected = BadParameterValueException.class)
    public void validate_startDate_after_endDate_error() throws BadParameterValueException, ParseException {
        PaymentServiceValidator validator = new PaymentServiceValidator();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        validator.validate(dateFormat.parse("2017-01-01"), dateFormat.parse("2016-01-01"), null);
    }

    @Test(expected = BadParameterValueException.class)
    public void validate_negativeLimit_error() throws BadParameterValueException, ParseException {
        PaymentServiceValidator validator = new PaymentServiceValidator();
        validator.validate(null, null, -10);
    }

    @Test
    public void validate_limit_ok() throws BadParameterValueException {
        PaymentServiceValidator validator = new PaymentServiceValidator();
        validator.validate(null, null, 10);
    }

    @Test
    public void validate_limit_noLimit_ok() throws BadParameterValueException {
        PaymentServiceValidator validator = new PaymentServiceValidator();
        validator.validate(null, null, -1);
    }

    // -------------------------
    // validate payment object
    // -------------------------

    @Test
    public void validate_paymentok() throws BadRequestException {
        PaymentServiceValidator validator = new PaymentServiceValidator();
        Payment payment = buildPayment();
        validator.validate(payment);
    }

    private Payment buildPayment() {
        Payment res = new Payment();
        res.setCorrelatorId("correlatorId");
        res.setTransactionType(TransactionType.ADVANCE);
        RelatedParty payer = new RelatedParty();
        payer.setId("payerId");
        res.setPayer(payer);
        Amount totalAmount = new Amount();
        totalAmount.setAmount(10.0f);
        res.setTotalAmount(totalAmount);
        PaymentItem paymentItem = new PaymentItem();
        paymentItem.setTotalAmount(totalAmount);
        Item item = new Item();
        item.setId("12");
        paymentItem.setItem(item);
        res.addPaymentItemItem(paymentItem);
        return res;
    }

    @Test(expected = MissingParameterException.class)
    public void validate_payment_missingPayer_error() throws BadRequestException {
        PaymentServiceValidator validator = new PaymentServiceValidator();
        Payment payment = buildPayment();
        payment.getPayer().setId(null);
        validator.validate(payment);
    }

}
