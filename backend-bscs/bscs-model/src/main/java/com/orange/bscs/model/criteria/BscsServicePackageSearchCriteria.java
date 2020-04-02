package com.orange.bscs.model.criteria;

import com.orange.bscs.model.product.BSCSServicePackage;

/**
 * Model used to set criteria for SERVICE_PACKAGES.READ command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsServicePackageSearchCriteria {

    protected BSCSServicePackage servicePackage;

    public BscsServicePackageSearchCriteria() {
        servicePackage = new BSCSServicePackage();
    }

    public BSCSServicePackage getBscsModel() {
        return servicePackage;
    }

    public abstract void setCode(String servicePackagecode);

    public abstract String getCode();

}
