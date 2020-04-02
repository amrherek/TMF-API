package com.orange.bscs.model;

import java.util.Date;

import com.orange.bscs.model.billing.BusinessTransaction;

public class BscsBusinessTransactionSearchV9 extends BscsBusinessTransactionSearch {

    public BscsBusinessTransactionSearchV9(BusinessTransaction transaction) {
        super(transaction);
    }

    @Override
    public Long getId() {
        // BT_OHXACT
        return transaction.getTransactionId();
    }

    @Override
    public Date getEntryDate() {
        // BT_OHENTDATE
        return transaction.getBtOhEntDate();
    }

    @Override
    public Date getDueDate() {
        // BT_OHDUEDATE
        return transaction.getBtOhDueDate();
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
        // CS_ID_PUB
        return transaction.getCsIdPub();
    }

    @Override
    public String getAmountCurrency() {
        // BT_OHINVAMT_DOC#currency
        return transaction.getBtOhInvAmtDoc_currency();
    }

}
