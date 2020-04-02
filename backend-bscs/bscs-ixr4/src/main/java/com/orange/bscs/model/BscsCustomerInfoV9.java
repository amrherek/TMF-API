package com.orange.bscs.model;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

public class BscsCustomerInfoV9 extends BscsCustomerInfo {

    public BscsCustomerInfoV9(BSCSModel info) {
        super(info);
    }

    public BscsCustomerInfoV9() {
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
        // CS_ID_PUB
        info.setStringValue(Constants.P_CS_ID_PUB, customerId);
    }

    @Override
    public String getCustomerId() {
        // CS_ID_PUB
        return info.getStringValue("CS_ID_PUB");
    }

}
