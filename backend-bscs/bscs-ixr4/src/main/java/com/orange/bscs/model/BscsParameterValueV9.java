package com.orange.bscs.model;

import com.orange.bscs.api.utils.config.Constants;
import com.orange.bscs.model.contract.BSCSContractServiceParameter.ParamValue;

public class BscsParameterValueV9 extends BscsParameterValue {

    public BscsParameterValueV9(ParamValue paramValue) {
        super(paramValue);
    }

    public BscsParameterValueV9() {
        super();
    }

    @Override
    public String getValue() {
        // VALUE
        return paramValue.getValue();
    }

    @Override
    public void setValue(String value) {
        // VALUE
        paramValue.setValue(value);
    }

    @Override
    public String getValueDescription() {
        // VALUE_DES
        return paramValue.getStringNotEmpty(Constants.P_VALUE_DES);
    }

    @Override
    public void setDescription(String description) {
        // VALUE_DES
        paramValue.setValueDes(description);
    }

    @Override
    public Long getSequenceNumber() {
        // VALUE_SEQNO
        return paramValue.getLongValue(Constants.P_VALUE_SEQNO);
    }

}
