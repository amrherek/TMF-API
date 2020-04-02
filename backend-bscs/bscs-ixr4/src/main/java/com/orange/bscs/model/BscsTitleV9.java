package com.orange.bscs.model;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

public class BscsTitleV9 extends BscsTitle {

    public BscsTitleV9(BSCSModel value) {
        super(value);
    }

    @Override
    public Long getNumericId() {
        // TTL_ID
        return value.getLongValue(Constants.P_TTL_ID);
    }

    @Override
    public String getDescription() {
        // TTL_DES
        return value.getStringValue(Constants.P_TTL_DES);
    }

}
