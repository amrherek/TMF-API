package com.orange.bscs.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.orange.bscs.api.utils.config.Constants;
import com.orange.bscs.model.contract.BSCSContractServiceParameter;
import com.orange.bscs.model.contract.BSCSContractServiceParameter.ParamValue;
import com.orange.bscs.model.contract.BSCSContractServiceParameter.TargetParamValue;
import com.orange.bscs.model.contract.EnumParameterDataType;
import com.orange.bscs.model.contract.EnumParameterType;

public class BscsContractServiceParameterV9 extends BscsContractServiceParameter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public BscsContractServiceParameterV9(BSCSContractServiceParameter parameter) {
        super(parameter);
    }

    public BscsContractServiceParameterV9() {
        super();
    }

    @Override
    public String getId() {
        // PRM_ID_PUB
        return parameter.getIdPub();
    }

    @Override
    public void setId(String id) {
        // not available as input of CONTRACT_SERVICE_PARAMETERS.WRITE command
        logger.error("PRM_ID_PUB can not be set for  CONTRACT_SERVICE_PARAMETERS.WRITE in V9");
        throw new RuntimeException("Not available in V9");
    }

    @Override
    public List<BscsParameterValue> getParametersValues() {
        // MULT_VALUES
        final List<BscsParameterValue> res = new ArrayList<>();
        final List<ParamValue> values = parameter.getListOfBSCSModelValue(Constants.P_MULT_VALUES, ParamValue.class);
        for (ParamValue value : values) {
            res.add(new BscsParameterValueV9(value));
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
        // PRM_NO
        return parameter.getParamNo();
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
