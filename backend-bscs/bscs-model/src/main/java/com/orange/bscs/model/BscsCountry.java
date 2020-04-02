package com.orange.bscs.model;

import com.orange.bscs.api.model.BSCSModel;

/**
 * Model used for result of COUNTRIES.READ command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsCountry {

    protected BSCSModel value;

    public BscsCountry(BSCSModel value) {
        this.value = value;
    }

    public abstract Long getNumericId();

    public abstract String getDescription();

    public abstract String getIso2();

}
