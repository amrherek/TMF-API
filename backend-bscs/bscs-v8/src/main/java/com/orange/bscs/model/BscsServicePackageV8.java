package com.orange.bscs.model;

import org.apache.commons.lang.ObjectUtils;

import com.orange.bscs.model.product.BSCSServicePackage;

public class BscsServicePackageV8 extends BscsServicePackage {

    public BscsServicePackageV8(BSCSServicePackage servicePackage) {
        super(servicePackage);
    }

    @Override
    public String getCode() {
        // SP_CODE
        return ObjectUtils.toString(servicePackage.getLongValue("SP_CODE"), null);
    }

    @Override
    public String getDescription() {
        // SP_DES
        return servicePackage.getDescription();
    }

}
