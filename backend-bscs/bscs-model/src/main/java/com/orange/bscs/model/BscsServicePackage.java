package com.orange.bscs.model;

import com.orange.bscs.model.product.BSCSServicePackage;

/**
 * Model used for result of SERVICE_PACKAGES.READ command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsServicePackage {

    protected BSCSServicePackage servicePackage;

    public BscsServicePackage(BSCSServicePackage servicePackage) {
        this.servicePackage = servicePackage;
    }

    public abstract String getCode();

    public abstract String getDescription();

}
