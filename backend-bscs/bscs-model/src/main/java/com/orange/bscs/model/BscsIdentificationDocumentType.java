package com.orange.bscs.model;

import com.orange.bscs.api.model.BSCSModel;

/**
 * Model used for result of IDENTIFICATION_DOCUMENT_TYPE.READ command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsIdentificationDocumentType {

    protected BSCSModel value;

    public BscsIdentificationDocumentType(BSCSModel value) {
        this.value = value;
    }

    public abstract Long getNumericCode();

    public abstract String getName();

}
