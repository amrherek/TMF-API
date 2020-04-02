package com.orange.bscs.model;

import com.orange.bscs.api.model.BSCSModel;

/**
 * Model used for result of FINANCIAL_TRANSACTION_DETAIL.READ and input of
 * FINANCIAL_TRANSACTION.WRITE
 * 
 * @author jwck2987
 *
 */
public abstract class BscsFinancialTransactionDetail {

    protected BSCSModel model;

    public BscsFinancialTransactionDetail() {
        model = new BSCSModel();
    }

    public BscsFinancialTransactionDetail(BSCSModel detail) {
        model = detail;
    }

    public BSCSModel getBscsModel() {
        return model;
    }

    public abstract void setDocumentId(Long documentId);

    public abstract void setCredit(boolean isCredit);

    public abstract void setAmount(Double amount);

    public abstract Double getAmount();

    public abstract Long getDocumentId();

    public abstract boolean isCredit();

    public abstract void setAmount(Double amount, String currency);

    public abstract String getAmountCurrency();

    public abstract String getType();
}
