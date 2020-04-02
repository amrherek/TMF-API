package com.orange.bscs.model;

import com.orange.bscs.api.model.BSCSModel;

/**
 * Model used for result of BILLDOCUMENT.READ command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsBillDocument {

    protected BSCSModel model;

    public BscsBillDocument(BSCSModel model) {
        this.model = model;
    }

    public abstract byte[] getDocument();

    public abstract String getFormat();

}
