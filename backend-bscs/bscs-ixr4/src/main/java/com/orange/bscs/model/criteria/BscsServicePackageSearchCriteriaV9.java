package com.orange.bscs.model.criteria;

public class BscsServicePackageSearchCriteriaV9 extends BscsServicePackageSearchCriteria {

    public BscsServicePackageSearchCriteriaV9() {
        super();
    }

    @Override
    public void setCode(String servicePackageCode) {
        // SPCODE_PUB
        servicePackage.setServicePackageCodePub(servicePackageCode);
    }

    @Override
    public String getCode() {
        // SPCODE_PUB
        return servicePackage.getServicePackageCodePub();
    }

}
