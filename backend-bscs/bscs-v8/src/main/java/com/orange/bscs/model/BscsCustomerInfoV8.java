package com.orange.bscs.model;

import java.util.Objects;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

public class BscsCustomerInfoV8 extends BscsCustomerInfo {

    public BscsCustomerInfoV8(BSCSModel info) {
        super(info);
    }

    public BscsCustomerInfoV8() {
        super();
    }

    @Override
    public String getText(String number) {
        // TEXTXX
        return info.getStringNotEmpty("TEXT" + number);
    }

    @Override
    public void setText(String number, String value) {
        // TEXTXX
        info.setStringValue("TEXT" + number, value);
    }

    @Override
    public void setCustomerId(String customerId) {
        // CUSTOMER_ID
        info.setLongValue(Constants.P_CUSTOMER_ID, Long.valueOf(customerId));
    }

    @Override
    public String getCustomerId() {
        // CUSTOMER_ID
        return Objects.toString(info.getLongValue("CUSTOMER_ID"), null);
    }

}
