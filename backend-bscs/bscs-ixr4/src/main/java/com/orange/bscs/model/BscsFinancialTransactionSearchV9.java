package com.orange.bscs.model;

import java.util.Date;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

public class BscsFinancialTransactionSearchV9 extends BscsFinancialTransactionSearch {

    public BscsFinancialTransactionSearchV9(BSCSModel transaction) {
        super(transaction);
    }

    @Override
    public Long getId() {
        return transaction.getLongValue("TRANSACTION_ID");
    }

    @Override
    public String getRef() {
        return transaction.getStringValue("TRANSACTION_REFNUM");
    }

    @Override
    public Date getRefDate() {
        return transaction.getDateValue("REF_DATE");
    }

    @Override
    public Double getAmount() {
        return transaction.getMoneyAmountValue("AMOUNT_CASH_PAY");
    }

    @Override
    public String getCustomerId() {
        return transaction.getStringValue(Constants.P_CS_ID_PUB);
    }

}
