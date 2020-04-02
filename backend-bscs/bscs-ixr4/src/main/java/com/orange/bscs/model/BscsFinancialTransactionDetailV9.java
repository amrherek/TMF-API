package com.orange.bscs.model;

import com.orange.bscs.api.model.BSCSModel;

public class BscsFinancialTransactionDetailV9 extends BscsFinancialTransactionDetail {

    public BscsFinancialTransactionDetailV9(BSCSModel detail) {
        super(detail);
    }

    public BscsFinancialTransactionDetailV9() {
        super();
    }

    @Override
    public void setDocumentId(Long documentId) {
        model.setLongValue("DOCUMENT_ID", documentId);
    }

    @Override
    public void setCredit(boolean isCredit) {
        Character debit = 'D';
        if (isCredit) {
            debit = 'C';
        }
        model.setCharacterValue("CREDIT_DEBIT", debit);
    }

    @Override
    public void setAmount(Double amount) {
        model.setMoneyValue("ASS_AMOUNT_CASH_PAY", amount);
    }

    @Override
    public Double getAmount() {
        return model.getMoneyAmountValue("AMOUNT_CASH_PAY");
    }

    @Override
    public Long getDocumentId() {
        return model.getLongValue("DOCUMENT_ID");
    }

    @Override
    public boolean isCredit() {
        Character c = model.getCharacterValue("ITEM_CREDIT_DEBIT_TYPE");
        if (c.equals('C')) {
            return true;
        }
        return false;
    }

    @Override
    public void setAmount(Double amount, String currency) {
        model.setMoneyValue("ASS_AMOUNT_CASH_PAY", amount, currency);
    }

    @Override
    public String getAmountCurrency() {
        return model.getMoneyCurrencyCodeValue("AMOUNT_CASH_PAY");
    }

    @Override
    public String getType() {
        return model.getStringValue("DOC_TYPE");
    }

}
