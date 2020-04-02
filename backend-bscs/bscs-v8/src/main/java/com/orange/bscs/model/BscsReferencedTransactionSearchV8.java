package com.orange.bscs.model;

import java.util.Date;

import org.apache.commons.lang.ObjectUtils;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

public class BscsReferencedTransactionSearchV8 extends BscsReferencedTransactionSearch {

    public BscsReferencedTransactionSearchV8(BSCSModel model) {
        super(model);
    }

    @Override
    public Double getAmount() {
        return model.getMoneyAmountValue("RT_CACHKAMT_PAY");
    }

    @Override
    public String getAmountCurrency() {
        return model.getMoneyCurrencyCodeValue("RT_CACHKAMT_PAY");
    }

    @Override
    public Long getId() {
        return model.getLongValue("RT_CAXACT");
    }

    @Override
    public Date getDate() {
        return model.getDateValue("RT_CACHKDATE");
    }

    @Override
    public Long getCustomerNumericId() {
        return model.getLongValue("RT_CUSTOMER_ID");
    }

    @Override
    public String getCaType() {
        // RT_CATYPE
        return model.getStringValue(Constants.P_RT_CATYPE);
    }

    @Override
    public Long getReasonPayment() {
        // • RT_CAREASONCODE
        return model.getLongValue(Constants.P_RT_CAREASONCODE);
    }

    @Override
    public String getReferencePayment() {
        // • RT_CACHKNUM
        return model.getStringValue(Constants.P_RT_CACHKNUM);
    }

    @Override
    public Double getPaymentAmount() {
        // • RT_CACHKAMT_PAY
        return model.getMoneyAmountValue(Constants.P_RT_CACHKAMT_PAY);
    }

    @Override
    public String getPaymentCurrency() {
        // • RT_CACHKAMT_PAY
        return model.getMoneyCurrencyCodeValue(Constants.P_RT_CACHKAMT_PAY);
    }

    @Override
    public String getDocumentCurrency() {
        return model.getStringValue("RT_CURRENCY");
    }

    @Override
    public String getRemark() {
        // • RT_CAREM
        return model.getStringValue(Constants.P_RT_CAREM);
    }

    @Override
    public Date getCheckDate() {
        // RT_CACHKDATE
        return model.getDateValue(Constants.P_RT_CACHKDATE);
    }

    @Override
    public Date getReceiptDate() {
        // RT_CARECDATE
        return model.getDateValue(Constants.P_RT_CARECDATE);
    }

    @Override
    public String getCustomerId() {
        // CS_ID_PUB
        return ObjectUtils.toString(model.getLongValue("RT_CUSTOMER_ID"), null);
    }

    @Override
    public Date getEntryDate() {
        // • RT_CAENTDATE
        return model.getDateValue(Constants.P_RT_CAENTDATE);
    }

}
