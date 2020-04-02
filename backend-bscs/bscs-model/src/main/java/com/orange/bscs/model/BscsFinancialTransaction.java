package com.orange.bscs.model;

import java.util.ArrayList;
import java.util.Date;

import com.orange.bscs.api.model.BSCSModel;

/**
 * Model used for result of FINANCIAL_TRANSACTION.READ an input of
 * FINANCIAL_TRANSACTION.WRITE commands
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsFinancialTransaction {

    protected BSCSModel transaction;

    public BscsFinancialTransaction(BSCSModel transaction) {
        this.transaction = transaction;
    }

    public BscsFinancialTransaction() {
        this.transaction = new BSCSModel();
        transaction.setListOfBSCSModelValue("DETAILS", new ArrayList<BSCSModel>());
    }

    public BSCSModel getBscsModel() {
        return transaction;
    }

    public abstract void setUseCaseCode(String code);

    public abstract void setCustomerId(String csIdPub);

    public abstract void setRef(String ref);

    public abstract void addDetail(BscsFinancialTransactionDetail detail);

    public abstract Long getId();

    public abstract String getRef();

    public abstract Date getRefDate();

    public abstract Double getAmount();

    public abstract String getCustomerId();

    public abstract String getRemark();

    public abstract void setAmount(Double amount);

    public abstract void setAmount(Double amount, String currency);

    public abstract void setRemark(String remark);

    public abstract void setRefDate(Date refDate);

    public abstract void setPaymentMethodId(String paymentMethod);

    public abstract String getPaymentMethodId();

    public abstract Long getPaymentMethodNumericId();

    public abstract void setPaymentMethodNumericId(Long paymentMethodId);

    public abstract String getAmountCurrency();

    public abstract String getUseCaseCode();

}
