package com.orange.bscs.model;

import com.orange.bscs.model.product.BSCSServicePackage;

public class BscsServicePackageV9 extends BscsServicePackage {

    public BscsServicePackageV9(BSCSServicePackage servicePackage) {
        super(servicePackage);
    }

    @Override
    public String getCode() {
        // SPCODE_PUB
        return servicePackage.getServicePackageCodePub();
    }

    @Override
    public String getDescription() {
        // SP_DES
        return servicePackage.getDescription();
    }

}
