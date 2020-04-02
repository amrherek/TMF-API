package com.orange.mea.apisi.payment.backend.bscs;

import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.orange.apibss.common.exceptions.badrequest.BadParameterValueException;
import com.orange.apibss.common.exceptions.technical.EnumTechnicalException;
import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.apibss.payment.model.Amount;
import com.orange.apibss.payment.model.Item;
import com.orange.apibss.payment.model.Payment;
import com.orange.apibss.payment.model.PaymentItem;
import com.orange.apibss.payment.model.PaymentMethod;
import com.orange.apibss.payment.model.PaymentMethodType;
import com.orange.apibss.payment.model.RelatedParty;
import com.orange.apibss.payment.model.TransactionType;
import com.orange.bscs.model.BscsFinancialTransaction;
import com.orange.bscs.model.BscsFinancialTransactionDetail;
import com.orange.bscs.model.factory.BscsObjectFactory;

@Component
public class BscsFinancialTransactionTransformer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected BscsObjectFactory bscsObjectFactory;

    /**
     * Transform a BscsFinancialTransaction to a TMF Payment
     * 
     * @param transaction
     * @param transactionDetails
     * @return
     * @throws TechnicalException
     */
    public Payment doTransform(BscsFinancialTransaction transaction,
            List<BscsFinancialTransactionDetail> transactionDetails) throws TechnicalException {
        Payment payment = new Payment();
        payment.setId(transaction.getId().toString());
        payment.setCorrelatorId(transaction.getRef());
        payment.setTransactionType(transformToTransactionType(transaction.getUseCaseCode()));
        if (transaction.getRefDate() != null) {
            payment.setPaymentDate(new DateTime(transaction.getRefDate()));
        }
        payment.setDescription(transaction.getRemark());
        Amount totalAmount = new Amount();
        totalAmount.setAmount(transaction.getAmount().floatValue());
        totalAmount.setUnits(transaction.getAmountCurrency());
        payment.setTotalAmount(totalAmount);
        RelatedParty payer = new RelatedParty();
        payer.setId(transaction.getCustomerId());
        payment.setPayer(payer);
        Long paymentMethodId = transaction.getPaymentMethodNumericId();
        if (paymentMethodId != null) {
            PaymentMethod paymentMethodItem = new PaymentMethod();
            paymentMethodItem.setType(transformPaymentMethodType(paymentMethodId));
            payment.addPaymentMethodItem(paymentMethodItem);
        }
        for (BscsFinancialTransactionDetail detail : transactionDetails) {
            PaymentItem paymentItem = new PaymentItem();
            if (detail.getAmount() != null) {
                Amount totalItemAmount = new Amount();
                totalItemAmount.setAmount(detail.getAmount().floatValue());
                totalItemAmount.setUnits(detail.getAmountCurrency());
                paymentItem.setTotalAmount(totalItemAmount);
            }
            Item item = new Item();
            item.setId(detail.getDocumentId().toString());
            item.setType(detail.getType());
            paymentItem.setItem(item);
            payment.addPaymentItemItem(paymentItem);
        }
        return payment;
    }

    /**
     * Transform a TMF Payment to a BscsFinancialTransaction
     * 
     * @param payment
     * @return
     * @throws BadParameterValueException
     */
    public BscsFinancialTransaction doTransform(Payment payment) throws BadParameterValueException {
        BscsFinancialTransaction transaction = bscsObjectFactory.createBscsFinancialTransaction();
        transaction.setUseCaseCode(transform(payment.getTransactionType()));
        transaction.setCustomerId(payment.getPayer().getId());
        transaction.setRef(payment.getCorrelatorId());
        transaction.setAmount(payment.getTotalAmount().getAmount().doubleValue(), payment.getTotalAmount().getUnits());
        transaction.setRemark(payment.getDescription());
        if (payment.getPaymentDate() != null) {
            transaction.setRefDate(payment.getPaymentDate().toDate());
        }
        for (PaymentItem paymentItem : payment.getPaymentItem()) {
            BscsFinancialTransactionDetail detail = bscsObjectFactory.createBscsFinancialTransactionDetail();
            detail.setDocumentId(Long.valueOf(paymentItem.getItem().getId()));
            detail.setCredit(false);
            detail.setAmount(paymentItem.getTotalAmount().getAmount().doubleValue(),
                    paymentItem.getTotalAmount().getUnits());
            transaction.addDetail(detail);
        }
        if (payment.getPaymentMethod() != null && !payment.getPaymentMethod().isEmpty()) {
            transaction.setPaymentMethodNumericId(transform(payment.getPaymentMethod().get(0).getType()));
        }
        return transaction;
    }

    private String transform(TransactionType transactionType) throws BadParameterValueException {
        switch (transactionType) {
        case ADVANCE:
            return PaymentConstants.ADVANCE_PAYMENT_CODE;
        case DEBT:
            return PaymentConstants.ASSIGN_CASH_ENTRY;
        case DEPOSIT:
            return PaymentConstants.DEPOSIT;
        default:
            throw new BadParameterValueException("transactionType", transactionType.getValue());
        }
    }

    private TransactionType transformToTransactionType(String useCaseCode) throws EnumTechnicalException {
        switch (useCaseCode) {
        case PaymentConstants.ADVANCE_PAYMENT_CODE:
            return TransactionType.ADVANCE;
        case PaymentConstants.ASSIGN_CASH_ENTRY:
            return TransactionType.DEBT;
        case PaymentConstants.DEPOSIT:
            return TransactionType.DEPOSIT;
        default:
            logger.error("Use case code " + useCaseCode + " not recognized");
            throw new EnumTechnicalException("Use case code " + useCaseCode + " not recognized");
        }
    }

    private Long transform(PaymentMethodType type) throws BadParameterValueException {
        switch (type) {
        case CASH:
            return -1l;
        case CHECK:
            return -2l;
        case CREDITCARD:
            return -5l;
        case DIRECTDEBIT:
            return -3l;
        case TRANSFER:
            return -4l;
        case VOUCHER:
            return -7l;
        case TOKENIZEDPAYMENT:
        case LOYALTYPOINT:
        default:
            throw new BadParameterValueException("paymentMethod.type", type.getValue());
        }
    }

    private PaymentMethodType transformPaymentMethodType(Long paymentMethodId) throws TechnicalException {
        if (paymentMethodId == null) {
            return null;
        }
        switch (paymentMethodId.intValue()) {
        case -1:
            return PaymentMethodType.CASH;
        case -2:
            return PaymentMethodType.CHECK;
        case -5:
            return PaymentMethodType.CREDITCARD;
        case -3:
            return PaymentMethodType.DIRECTDEBIT;
        case -4:
            return PaymentMethodType.TRANSFER;
        case -7:
            return PaymentMethodType.VOUCHER;
        default:
            throw new EnumTechnicalException("PaymentMethodType not found for value: " + paymentMethodId);
        }
    }

}
