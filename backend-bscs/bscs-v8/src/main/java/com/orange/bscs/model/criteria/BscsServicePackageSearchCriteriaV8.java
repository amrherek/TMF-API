package com.orange.bscs.model.criteria;

import org.apache.commons.lang.ObjectUtils;

public class BscsServicePackageSearchCriteriaV8 extends BscsServicePackageSearchCriteria {

    public BscsServicePackageSearchCriteriaV8() {
        super();
    }

    @Override
    public void setCode(String servicePackageId) {
        // SP_CODE
        servicePackage.setLongValue("SP_CODE", Long.valueOf(servicePackageId));
    }

    @Override
    public String getCode() {
        // SP_CODE
        return ObjectUtils.toString(servicePackage.getLongValue("SP_CODE"), null);
    }

}
