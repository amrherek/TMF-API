package com.orange.bscs.model;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

public class BscsCountryV9 extends BscsCountry {

    public BscsCountryV9(BSCSModel value) {
        super(value);
    }

    @Override
    public Long getNumericId() {
        // COUNTRY_ID
        return value.getLongValue(Constants.P_COUNTRY_ID);
    }

    @Override
    public String getDescription() {
        // CNTR_DES
        return value.getStringValue(Constants.P_CNTR_DES);
    }

    @Override
    public String getIso2() {
        // ISO2_3166
        return value.getStringValue(Constants.P_ISO2_3166);
    }

}
