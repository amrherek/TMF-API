package com.orange.bscs.model;

import java.util.List;

import com.orange.bscs.model.contract.BSCSParameter;
import com.orange.bscs.model.contract.EnumParameterDataType;
import com.orange.bscs.model.contract.EnumParameterType;

/**
 * Model used for partial result of SERVICE_PARAMETERS.READ command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsParameter {

    protected BSCSParameter parameter;

    public BscsParameter(BSCSParameter parameter) {
        this.parameter = parameter;
    }

    public abstract String getId();

    public abstract Long getNumber();

    public abstract EnumParameterType getType();

    public abstract List<BscsParameterValue> getValues();

    public abstract String getDescription();

    public abstract EnumParameterDataType getDataType();

    public abstract String getFormat();

}
