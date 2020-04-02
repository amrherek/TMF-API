package com.orange.mea.apisi.customerbill.transformer;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.orange.apibss.customerbill.model.AppliedPayment;
import com.orange.apibss.customerbill.model.Attachment;
import com.orange.apibss.customerbill.model.CustomerBill;
import com.orange.apibss.customerbill.model.Money;
import com.orange.apibss.customerbill.model.PaymentRef;
import com.orange.apibss.customerbill.model.RelatedPartyRef;
import com.orange.apibss.customerbill.model.StateValue;
import com.orange.bscs.model.BscsFinancialDocument;
import com.orange.bscs.model.BscsFinancialDocumentDetail;
import com.orange.bscs.model.BscsFinancialDocumentSearch;

/**
 * Transform BscsFinancialDocument into CustomerBill
 *
 * @author Denis Borscia (deyb6792]
 */
@Component
public class FinancialDocumentTransformer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public CustomerBill transform(BscsFinancialDocument document, List<BscsFinancialDocumentDetail> details) {
        CustomerBill customerBill = new CustomerBill();

        // Id
        if (document.getId() != null) {
            customerBill.setId(document.getId().toString());
        }

        // Date
        Date customerBillDate = document.getRefDate();
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
        customerBill.setState(transformInvoiceStatus(document.getStatus()));
        fillState(customerBill);

        // Related Party
        RelatedPartyRef party = new RelatedPartyRef();
        party.setId(document.getCustomerId());
        customerBill.addRelatedPartyItem(party);

        // Bill Document
        if (document.getImageGenerated()) {
            Attachment billDoc = new Attachment();
            customerBill.addBillDocumentItem(billDoc);
        }

        // payment ref
        addPaymentRef(customerBill, details);

        return customerBill;
    }

    /**
     * transform
     * 
     * @param customerBill
     *            BscsFinancialDocumentDetail into PaymentRef and add them to
     *            customerBill
     * @param details
     */
    private void addPaymentRef(CustomerBill customerBill, List<BscsFinancialDocumentDetail> details) {
        for (BscsFinancialDocumentDetail detail : details) {
            if (detail.getAmount() != null) {
                AppliedPayment payment = new AppliedPayment();
                Money appliedAmount = new Money();
                appliedAmount.setValue(detail.getAmount().floatValue());
                appliedAmount.setUnit(detail.getAmountCurrency());
                payment.setAppliedAmount(appliedAmount);
                if (isPayment(detail)) {
                    PaymentRef paymentRef = new PaymentRef();
                    paymentRef.setId(detail.getId().toString());
                    if (detail.getRefDate() != null) {
                        paymentRef.setPaymentDate(new DateTime(detail.getRefDate()));
                    }
                    payment.setPayment(paymentRef);
                }
                customerBill.addAppliedPaymentItem(payment);
            }
        }
    }

    private boolean isPayment(BscsFinancialDocumentDetail detail) {
        String useCaseCode = detail.getUseCaseCode();
        if (useCaseCode == null) {
            logger.error("No USE_CASE_CODE returned");
            return false;
        }
        switch (useCaseCode) {
        case "CE2CO":
        case "CE2DD":
        case "CE2DP":
            return true;
        default:
            return false;
        }
    }

    public CustomerBill transform(BscsFinancialDocumentSearch document,
            List<BscsFinancialDocumentDetail> details) {
        CustomerBill customerBill = new CustomerBill();

        // Id
        if (document.getId() != null) {
            customerBill.setId(document.getId().toString());
        }

        // Date
        Date customerBillDate = document.getRefDate();
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
        customerBill.setState(transformInvoiceStatus(document.getStatus()));
        fillState(customerBill);

        // Related Party
        RelatedPartyRef party = new RelatedPartyRef();
        party.setId(document.getCustomerId());
        customerBill.addRelatedPartyItem(party);

        // Bill Document
        if (document.getImageGenerated()) {
            Attachment billDoc = new Attachment();
            customerBill.addBillDocumentItem(billDoc);
        }

        // payment ref
        addPaymentRef(customerBill, details);

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

    /**
     *
     * @param status
     *            the status of the document to transform
     * @return the bill state
     */
    private StateValue transformInvoiceStatus(String status) {

        // TODO: check this case: always null !!!
        if (status == null) {
            return null;
        }

        switch (status) {
        case "FI": // finished
            return StateValue.SETTLED;
        case "RE": // rejected
            return StateValue.ONHOLD;
        case "IW": // in work
            return StateValue.NEW;
        case "AP": // approved
        case "RA": // ready for approval
            return StateValue.VALIDATED;
        default:
            // TODO: check this case
            return null;
        }
    }

}
