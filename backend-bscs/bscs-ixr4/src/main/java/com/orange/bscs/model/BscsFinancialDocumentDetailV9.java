package com.orange.bscs.model;

import java.util.Date;

import com.orange.bscs.api.model.BSCSModel;

public class BscsFinancialDocumentDetailV9 extends BscsFinancialDocumentDetail {

    public BscsFinancialDocumentDetailV9(BSCSModel detail) {
        super(detail);
    }

    @Override
    public Double getAmount() {
        return model.getMoneyAmountValue("AMOUNT_CASH_PAY");
    }

    @Override
    public String getAmountCurrency() {
        return model.getMoneyCurrencyCodeValue("AMOUNT_CASH_PAY");
    }

    @Override
    public Long getId() {
        return model.getLongValue("TRANSACTION_ID");
    }

    @Override
    public Date getRefDate() {
        return model.getDateValue("REF_DATE");
    }

    @Override
    public String getUseCaseCode() {
        return model.getStringValue("USE_CASE_CODE");
    }

}
