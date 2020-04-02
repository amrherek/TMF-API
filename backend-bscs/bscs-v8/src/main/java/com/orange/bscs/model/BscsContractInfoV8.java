package com.orange.bscs.model;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

public class BscsContractInfoV8 extends BscsContractInfo {

    public BscsContractInfoV8(BSCSModel info) {
        super(info);
    }

    public BscsContractInfoV8() {
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
    public void setContractNumericId(Long contractId) {
        // CO_ID
        info.setLongValue(Constants.P_CO_ID, contractId);
    }

    @Override
    public Long getContractNumericId() {
        // CO_ID
        return info.getLongValue(Constants.P_CO_ID);
    }

}
