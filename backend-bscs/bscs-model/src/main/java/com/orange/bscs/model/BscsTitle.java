package com.orange.bscs.model;

import com.orange.bscs.api.model.BSCSModel;

/**
 * Model used for result of TITLES.READ command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsTitle {

    protected BSCSModel value;

    public BscsTitle(BSCSModel value) {
        this.value = value;
    }

    public abstract Long getNumericId();

    public abstract String getDescription();

}
