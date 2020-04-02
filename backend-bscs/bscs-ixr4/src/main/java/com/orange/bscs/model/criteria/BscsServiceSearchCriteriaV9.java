package com.orange.bscs.model.criteria;

public class BscsServiceSearchCriteriaV9 extends BscsServiceSearchCriteria {

    public BscsServiceSearchCriteriaV9() {
        super();
    }

    @Override
    public void setCode(String serviceId) {
        // SNCODE_PUB
        service.setServicePublicCode(serviceId);
    }

    @Override
    public void setServicePackageCode(String servicePackageId) {
        // SPCODE_PUB
        service.setServicePackagePublicCode(servicePackageId);
    }

    @Override
    public void setRatePlanCode(String rpCode) {
        // RPCODE_PUB
        service.setRatePlanPublicCode(rpCode);
    }

    @Override
    public void setRatePlanVersion(Long version) {
        // RP_VSCODE
        service.setRatePlanVersionCode(version);
    }

    @Override
    public String getCode() {
        // SNCODE_PUB
        return service.getServicePublicCode();
    }

}
