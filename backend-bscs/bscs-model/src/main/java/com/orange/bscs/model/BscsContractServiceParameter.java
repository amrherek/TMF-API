package com.orange.bscs.model;

import java.util.List;

import com.orange.bscs.model.contract.BSCSContractServiceParameter;
import com.orange.bscs.model.contract.EnumParameterDataType;
import com.orange.bscs.model.contract.EnumParameterType;

/**
 * Model used for result of CONTRACT_SERVICE_PARAMETERS.READ command and input
 * of CONTRACT_SERVICE_PARAMETERS.WRITE command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsContractServiceParameter {

    protected BSCSContractServiceParameter parameter;

    public BscsContractServiceParameter() {
        this.parameter = new BSCSContractServiceParameter();
    }

    public BscsContractServiceParameter(BSCSContractServiceParameter parameter) {
        this.parameter = parameter;
    }

    public BSCSContractServiceParameter getBscsModel() {
        return parameter;
    }

    public abstract List<BscsParameterValue> getParametersValues();

    public abstract void setParametersValues(List<BscsParameterValue> values, Long targetSiblingNumber);

    public abstract String getId();

    public abstract Long getNumber();

    public abstract void setNumber(Long paramNo);

    public abstract void setComplexNumber(Long complexNo);

    public abstract void setComplexLevel(Long complexLevel);

    public abstract void setParentNumber(Long parentNo);

    public abstract void setSiblingNumber(Long siblingNo);

    public abstract void setAction(Character action);

    public abstract void setId(String id);

    public abstract EnumParameterType getType();

    public abstract EnumParameterDataType getDateType();

}
