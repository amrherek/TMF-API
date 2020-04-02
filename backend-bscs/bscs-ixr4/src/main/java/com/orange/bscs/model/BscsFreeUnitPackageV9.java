package com.orange.bscs.model;

import com.orange.bscs.model.contract.BSCSFreeUnitPackage;

public class BscsFreeUnitPackageV9 extends BscsFreeUnitPackage {

    public BscsFreeUnitPackageV9(BSCSFreeUnitPackage freeUnit) {
        super(freeUnit);
    }

    @Override
    public Long getId() {
        // FU_PACK_ID
        return freeUnit.getFUPackageId();
    }

    @Override
    public String getDescription() {
        // FUP_DES
        return freeUnit.getFUPackageDescription();
    }

}
