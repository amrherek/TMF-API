package com.orange.bscs.model;

import com.orange.bscs.model.contract.BSCSFreeUnitPackage;

/**
 * Model used for result of FREE_UNIT_PACKAGES.READ command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsFreeUnitPackage {

    protected BSCSFreeUnitPackage freeUnit;

    public BscsFreeUnitPackage(BSCSFreeUnitPackage freeUnit) {
        this.freeUnit = freeUnit;
    }

    public abstract Long getId();

    public abstract String getDescription();

}
