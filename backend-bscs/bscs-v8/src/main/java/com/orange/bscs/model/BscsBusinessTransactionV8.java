package com.orange.bscs.model;

import java.util.Date;

import org.apache.commons.lang.ObjectUtils;

import com.orange.bscs.api.utils.config.Constants;
import com.orange.bscs.model.billing.BusinessTransaction;

public class BscsBusinessTransactionV8 extends BscsBusinessTransaction {

    public BscsBusinessTransactionV8(BusinessTransaction transaction) {
        super(transaction);
    }

    @Override
    public Long getId() {
        // BT_OHXACT
        return transaction.getTransactionId();
    }

    @Override
    public Date getEntryDate() {
        // BT_OHENTDATE as Date
        return transaction.getDateValue(Constants.P_BT_OHENTDATE);
    }

    @Override
    public Date getDueDate() {
        // BT_OHDUEDATE as Date
        return transaction.getDateValue(Constants.P_BT_OHDUEDATE);
    }

    @Override
    public Double getAmount() {
        // BT_OHINVAMT_DOC
        return transaction.getBtOhInvAmtDoc();
    }

    @Override
    public Double getOpenAmount() {
        // BT_OHOPNAMT_DOC
        return transaction.getBtOhOpAmtDoc();
    }

    @Override
    public String getCustomerId() {
        // BT_CUSTOMER_ID
        return ObjectUtils.toString(transaction.getLongValue("BT_CUSTOMER_ID"), null);
    }

    @Override
    public String getAmountCurrency() {
        // BT_OHINVAMT_DOC#currency
        return transaction.getBtOhInvAmtDoc_currency();
    }

    @Override
    public String getRef() {
        // BT_OHREFNUM
        return transaction.getStringValue(Constants.P_BT_OHREFNUM);
    }

    @Override
    public String getStatus() {
        // BT_OHSTATUS
        return transaction.getStringValue(Constants.P_BT_OHSTATUS);
    }

}
