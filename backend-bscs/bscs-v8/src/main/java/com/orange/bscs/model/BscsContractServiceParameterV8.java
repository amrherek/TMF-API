package com.orange.bscs.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;

import com.orange.bscs.api.utils.config.Constants;
import com.orange.bscs.model.contract.BSCSContractServiceParameter;
import com.orange.bscs.model.contract.BSCSContractServiceParameter.ParamValue;
import com.orange.bscs.model.contract.BSCSContractServiceParameter.TargetParamValue;
import com.orange.bscs.model.contract.EnumParameterDataType;
import com.orange.bscs.model.contract.EnumParameterType;

public class BscsContractServiceParameterV8 extends BscsContractServiceParameter {

    public BscsContractServiceParameterV8(BSCSContractServiceParameter parameter) {
        super(parameter);
    }

    public BscsContractServiceParameterV8() {
        super();
    }

    @Override
    public String getId() {
        // PRM_ID
        return ObjectUtils.toString(parameter.getId(), null);
    }

    @Override
    public void setId(String id) {
        // PRM_ID
        parameter.setLongValue(Constants.P_PRM_ID, Long.valueOf(id));
    }

    @Override
    public List<BscsParameterValue> getParametersValues() {
        // MULT_VALUES
        final List<BscsParameterValue> res = new ArrayList<>();
        final List<ParamValue> values = parameter.getListOfBSCSModelValue(Constants.P_MULT_VALUES, ParamValue.class);
        for (ParamValue value : values) {
            res.add(new BscsParameterValueV8(value));
        }
        return res;
    }

    @Override
    public void setParametersValues(List<BscsParameterValue> values, Long targetSiblingNumber) {
        List<ParamValue> bscsValues = new ArrayList<>();
        for (BscsParameterValue value : values) {
            bscsValues.add(value.getBscsModel());
        }
        // TARGET_PARAM_VALUES
        final TargetParamValue targetParamValue = new TargetParamValue(targetSiblingNumber, bscsValues);
        parameter.setTargetParamValues(Arrays.asList(targetParamValue));
    }

    @Override
    public void setNumber(Long paramNo) {
        // PRM_NO
        parameter.setParamNo(paramNo);
    }

    @Override
    public Long getNumber() {
        // PARM_NO
        return parameter.getLongValue("PARM_NO");
    }

    @Override
    public void setComplexNumber(Long complexNo) {
        // COMPLEX_NO
        parameter.setComplexeNo(complexNo);
    }

    @Override
    public void setComplexLevel(Long complexLevel) {
        // COMPLEX_LEVEL
        parameter.setComplexLevel(complexLevel);
    }

    @Override
    public void setParentNumber(Long parentNo) {
        // PARENT_NO
        parameter.setParentNo(parentNo);
    }

    @Override
    public void setSiblingNumber(Long siblingNo) {
        // SIBLING_NO
        parameter.setSiblingNo(siblingNo);
    }

    @Override
    public void setAction(Character action) {
        // COS_ACTION
        parameter.setCOSAction(action);
    }

    @Override
    public EnumParameterType getType() {
        // TYPE
        return EnumParameterType.parseString(parameter.getStringValue(Constants.P_TYPE));
    }

    @Override
    public EnumParameterDataType getDateType() {
        // DATA_TYPE
        return EnumParameterDataType.parseString(parameter.getStringValue(Constants.P_DATA_TYPE));
    }

}
