package com.orange.bscs.model;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

public class BscsIdentificationDocumentTypeV8 extends BscsIdentificationDocumentType {

    public BscsIdentificationDocumentTypeV8(BSCSModel value) {
        super(value);
    }

    @Override
    public Long getNumericCode() {
        // IDTYPE_CODE
        return value.getLongValue(Constants.P_IDTYPE_CODE);
    }

    @Override
    public String getName() {
        // IDTYPE_NAME
        return value.getStringValue(Constants.P_IDTYPE_NAME);
    }

}
