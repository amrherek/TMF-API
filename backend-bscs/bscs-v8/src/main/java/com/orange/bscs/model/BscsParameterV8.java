package com.orange.bscs.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;

import com.orange.bscs.api.utils.config.Constants;
import com.orange.bscs.model.contract.BSCSContractServiceParameter.ParamValue;
import com.orange.bscs.model.contract.BSCSParameter;
import com.orange.bscs.model.contract.EnumParameterDataType;
import com.orange.bscs.model.contract.EnumParameterType;

public class BscsParameterV8 extends BscsParameter {

    public BscsParameterV8(BSCSParameter parameter) {
        super(parameter);
    }

    @Override
    public String getId() {
        // PRM_ID
        return ObjectUtils.toString(parameter.getId(), null);
    }

    @Override
    public Long getNumber() {
        // PARM_NO
        return parameter.getLongValue("PARM_NO");
    }

    @Override
    public EnumParameterType getType() {
        // TYPE
        return parameter.getParameterType();
    }

    @Override
    public List<BscsParameterValue> getValues() {
        // N_VALUES
        final List<BscsParameterValue> res = new ArrayList<>();
        final List<ParamValue> values = parameter.getListOfBSCSModelValue(Constants.P_N_VALUES, ParamValue.class);
        for (ParamValue value : values) {
            res.add(new BscsParameterValueV8(value));
        }
        return res;
    }

    @Override
    public String getDescription() {
        // PRM_DES
        return parameter.getDescription();
    }

    @Override
    public EnumParameterDataType getDataType() {
        // DATA_TYPE
        return parameter.getDataType();
    }

    @Override
    public String getFormat() {
        // FORMAT
        return parameter.getStringValue("FORMAT");
    }


}
