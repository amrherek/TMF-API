package com.orange.mea.apisi.customerbill.transformer;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import com.orange.apibss.customerbill.model.AppliedPayment;
import com.orange.apibss.customerbill.model.Attachment;
import com.orange.apibss.customerbill.model.CustomerBill;
import com.orange.apibss.customerbill.model.Money;
import com.orange.apibss.customerbill.model.PaymentRef;
import com.orange.apibss.customerbill.model.RelatedPartyRef;
import com.orange.apibss.customerbill.model.StateValue;
import com.orange.bscs.model.BscsBusinessTransaction;
import com.orange.bscs.model.BscsBusinessTransactionSearch;
import com.orange.bscs.model.BscsReferencedTransactionSearch;

/**
 * Transform BusinessTransaction into CustomerBill
 *
 * @author Denis Borscia (deyb6792]
 */
@Component
public class BusinessTransactionTransformer {

    public CustomerBill transform(BscsBusinessTransaction document,
            List<BscsReferencedTransactionSearch> referencedTransactions) {
        CustomerBill customerBill = new CustomerBill();

        // Id
        if (document.getId() != null) {
            customerBill.setId(document.getId().toString());
        }

        // Date
        Date customerBillDate = document.getEntryDate();
        if (customerBillDate != null) {
            customerBill.setBillDate(new DateTime(customerBillDate));
        }

        // DueDate
        Date customerBillDueDate = document.getDueDate();
        if (customerBillDueDate != null) {
            customerBill.setPaymentDueDate(new DateTime(customerBillDueDate));
        }

        // Currency bill
        String currency = document.getAmountCurrency();

        // Bill amount
        if (document.getAmount() != null) {
            Money amountDue = new Money();
            amountDue.setValue(document.getAmount().floatValue());
            amountDue.setUnit(currency);
            customerBill.setAmountDue(amountDue);
        }

        // Open amount
        if (document.getOpenAmount() != null) {
            Money remainingAmount = new Money();
            remainingAmount.setValue(document.getOpenAmount().floatValue());
            remainingAmount.setUnit(currency);
            customerBill.setRemainingAmount(remainingAmount);
        }

        // Status
        fillState(customerBill);

        // Related Party
        RelatedPartyRef party = new RelatedPartyRef();
        party.setId(document.getCustomerId());
        customerBill.addRelatedPartyItem(party);

        // Bill Document
        if (hasBillDocument()) {
            Attachment billDoc = new Attachment();
            customerBill.addBillDocumentItem(billDoc);
        }

        // payment ref
        addPaymentRef(customerBill, referencedTransactions);

        return customerBill;
    }

    private void addPaymentRef(CustomerBill customerBill,
            List<BscsReferencedTransactionSearch> referencedTransactions) {
        for (BscsReferencedTransactionSearch trans : referencedTransactions) {
            if (trans.getAmount() != null) {
                AppliedPayment payment = new AppliedPayment();
                Money appliedAmount = new Money();
                appliedAmount.setValue(trans.getAmount().floatValue());
                appliedAmount.setUnit(trans.getAmountCurrency());
                payment.setAppliedAmount(appliedAmount);
                PaymentRef paymentRef = new PaymentRef();
                paymentRef.setId(trans.getId().toString());
                if (trans.getDate() != null) {
                    paymentRef.setPaymentDate(new DateTime(trans.getDate()));
                }
                payment.setPayment(paymentRef);
                customerBill.addAppliedPaymentItem(payment);
            }
        }
    }

    public CustomerBill transform(BscsBusinessTransactionSearch document,
            List<BscsReferencedTransactionSearch> referencedTransactions) {
        CustomerBill customerBill = new CustomerBill();

        // Id
        if (document.getId() != null) {
            customerBill.setId(document.getId().toString());
        }

        // Date
        Date customerBillDate = document.getEntryDate();
        if (customerBillDate != null) {
            customerBill.setBillDate(new DateTime(customerBillDate));
        }

        // DueDate
        Date customerBillDueDate = document.getDueDate();
        if (customerBillDueDate != null) {
            customerBill.setPaymentDueDate(new DateTime(customerBillDueDate));
        }

        // Currency bill
        String currency = document.getAmountCurrency();

        // Bill amount
        if (document.getAmount() != null) {
            Money amountDue = new Money();
            amountDue.setValue(document.getAmount().floatValue());
            amountDue.setUnit(currency);
            customerBill.setAmountDue(amountDue);
        }

        // Open amount
        if (document.getOpenAmount() != null) {
            Money remainingAmount = new Money();
            remainingAmount.setValue(document.getOpenAmount().floatValue());
            remainingAmount.setUnit(currency);
            customerBill.setRemainingAmount(remainingAmount);
        }

        // Status
        fillState(customerBill);

        // Related Party
        RelatedPartyRef party = new RelatedPartyRef();
        party.setId(document.getCustomerId());
        customerBill.addRelatedPartyItem(party);

        // Bill Document
        if (hasBillDocument()) {
            Attachment billDoc = new Attachment();
            customerBill.addBillDocumentItem(billDoc);
        }

        // payment ref
        addPaymentRef(customerBill, referencedTransactions);

        return customerBill;
    }

    /**
     * Fill the state field if no set
     * 
     * @param customerBill
     */
    private void fillState(CustomerBill customerBill) {
        if (customerBill.getState() != null) {
            return;
        }
        if (customerBill.getRemainingAmount() != null && customerBill.getRemainingAmount().getValue() != null
                && customerBill.getRemainingAmount().getValue() == 0) {
            // no more open amount => settled
            customerBill.setState(StateValue.SETTLED);
        } else {
            // default => validated
            customerBill.setState(StateValue.VALIDATED);
        }
    }

    protected boolean hasBillDocument() {
        // no bill image associated
        return false;
    }

}
