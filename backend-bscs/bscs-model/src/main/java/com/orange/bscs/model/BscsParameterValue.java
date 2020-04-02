package com.orange.bscs.model;

import com.orange.bscs.model.contract.BSCSContractServiceParameter.ParamValue;

/**
 * Model used for partial result of CONTRACT_SERVICE_PARAMETERS.READ and
 * SERVICE_PARAMETERS.READ commands and partial input of
 * CONTRACT_SERVICE_PARAMETERS.WRITE
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsParameterValue {

    protected ParamValue paramValue;

    public BscsParameterValue() {
        this.paramValue = new ParamValue();
    }

    public BscsParameterValue(ParamValue value) {
        this.paramValue = value;
    }

    public ParamValue getBscsModel() {
        return paramValue;
    }

    public abstract String getValue();

    public abstract void setValue(String value);

    public abstract String getValueDescription();

    public abstract void setDescription(String description);

    public abstract Long getSequenceNumber();

}
