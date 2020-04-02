package com.orange.bscs.model;

import java.util.Date;
import java.util.List;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

public class BscsFinancialTransactionV9 extends BscsFinancialTransaction {

    public BscsFinancialTransactionV9(BSCSModel transaction) {
        super(transaction);
    }

    public BscsFinancialTransactionV9() {
        super();
    }

    @Override
    public void setUseCaseCode(String code) {
        transaction.setStringValue("USE_CASE_CODE", code);
    }

    @Override
    public void setCustomerId(String csIdPub) {
        transaction.setStringValue(Constants.P_CS_ID_PUB, csIdPub);
    }

    @Override
    public void setRef(String ref) {
        transaction.setStringValue("TRANSACTION_REFNUM", ref);
    }

    @Override
    public void addDetail(BscsFinancialTransactionDetail detail) {
        List<BSCSModel> details = transaction.getListOfBSCSModelValue("DETAILS");
        details.add(detail.getBscsModel());
        transaction.setListOfBSCSModelValue("DETAILS", details);
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
    public String getAmountCurrency() {
        return transaction.getMoneyCurrencyCodeValue("AMOUNT_CASH_PAY");
    }

    @Override
    public String getCustomerId() {
        return transaction.getStringValue(Constants.P_CS_ID_PUB);
    }

    @Override
    public String getRemark() {
        return transaction.getStringValue("REMARK");
    }

    @Override
    public void setAmount(Double amount) {
        transaction.setMoneyValue("AMOUNT_CASH_PAY", amount);
    }

    @Override
    public void setAmount(Double amount, String currency) {
        transaction.setMoneyValue("AMOUNT_CASH_PAY", amount, currency);
    }

    @Override
    public void setRemark(String remark) {
        transaction.setStringValue("REMARK", remark);
    }

    @Override
    public void setRefDate(Date refDate) {
        transaction.setDateValue("REF_DATE", refDate);
    }

    @Override
    public void setPaymentMethodId(String paymentMethod) {
        transaction.setStringValue("PAY_METHOD_ID_PUB", paymentMethod);
    }

    @Override
    public String getPaymentMethodId() {
        return transaction.getStringValue("PAY_METHOD_ID_PUB");
    }

    @Override
    public Long getPaymentMethodNumericId() {
        return transaction.getLongValue("PAY_METHOD_ID");
    }

    @Override
    public void setPaymentMethodNumericId(Long paymentMethodId) {
        transaction.setLongValue("PAY_METHOD_ID", paymentMethodId);
    }

    @Override
    public String getUseCaseCode() {
        return transaction.getStringValue("USE_CASE_CODE");
    }

}
