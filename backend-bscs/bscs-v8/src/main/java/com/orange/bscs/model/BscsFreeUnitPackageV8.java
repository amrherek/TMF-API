package com.orange.bscs.model;

import com.orange.bscs.model.contract.BSCSFreeUnitPackage;

public class BscsFreeUnitPackageV8 extends BscsFreeUnitPackage {

    public BscsFreeUnitPackageV8(BSCSFreeUnitPackage freeUnit) {
        super(freeUnit);
    }

    @Override
    public Long getId() {
        // FUP_ID
        return freeUnit.getLongValue("FUP_ID");
    }

    @Override
    public String getDescription() {
        // FUP_DES
        return freeUnit.getFUPackageDescription();
    }

}
