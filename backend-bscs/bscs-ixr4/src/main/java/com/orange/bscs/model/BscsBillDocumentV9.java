package com.orange.bscs.model;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

public class BscsBillDocumentV9 extends BscsBillDocument {

    public BscsBillDocumentV9(BSCSModel model) {
        super(model);
    }

    @Override
    public byte[] getDocument() {
        // DOCUMENT
        return model.getBinaryValue(Constants.P_DOCUMENT);
    }

    @Override
    public String getFormat() {
        // DOCUMENT_FORMAT
        return model.getStringValue(Constants.P_DOCUMENT_FORMAT);
    }

}
