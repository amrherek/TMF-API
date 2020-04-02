package com.orange.bscs.model.criteria;

import org.apache.commons.lang.ObjectUtils;

public class BscsServiceSearchCriteriaV8 extends BscsServiceSearchCriteria {

    public BscsServiceSearchCriteriaV8() {
        super();
    }

    @Override
    public void setCode(String serviceId) {
        // SV_CODE
        service.setLongValue("SV_CODE", Long.valueOf(serviceId));
    }

    @Override
    public void setServicePackageCode(String servicePackageId) {
        // SP_CODE
        service.setLongValue("SP_CODE", Long.valueOf(servicePackageId));
    }

    @Override
    public void setRatePlanCode(String rpCode) {
        // RP_CODE
        service.setLongValue("RP_CODE", Long.valueOf(rpCode));
    }

    @Override
    public void setRatePlanVersion(Long version) {
        // RP_VSCODE
        service.setRatePlanVersionCode(version);
    }

    @Override
    public String getCode() {
        // SV_CODE
        return ObjectUtils.toString(service.getLongValue("SV_CODE"), null);
    }

}
