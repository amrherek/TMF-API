package com.orange.bscs.model;

import com.orange.bscs.api.model.BSCSModel;

public class BscsCustomerGroupV9 extends BscsCustomerGroup {

    public BscsCustomerGroupV9(BSCSModel value) {
        super(value);
    }

    @Override
    public String getCode() {
        // PRG_CODE
        return value.getStringValue("PRG_CODE");
    }

    @Override
    public String getDescription() {
        // PRG_DES
        return value.getStringValue("PRG_DES");
    }

}
