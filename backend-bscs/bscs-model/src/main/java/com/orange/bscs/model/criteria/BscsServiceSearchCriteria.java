package com.orange.bscs.model.criteria;

import com.orange.bscs.model.contract.BSCSService;

/**
 * Model used to set criteria for SERVICES.READ command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsServiceSearchCriteria {

    protected BSCSService service;

    public BscsServiceSearchCriteria() {
        service = new BSCSService();
    }

    public BSCSService getBscsModel() {
        return service;
    }

    public abstract void setCode(String serviceId);

    public abstract String getCode();

    public abstract void setServicePackageCode(String servicePackageId);

    public abstract void setRatePlanCode(String rpCode);

    public abstract void setRatePlanVersion(Long version);

}
