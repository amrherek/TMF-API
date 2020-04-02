package com.orange.bscs.model.criteria;

public class BscsContractServiceSearchCriteriaV8 extends BscsContractServiceSearchCriteria {

    public BscsContractServiceSearchCriteriaV8() {
        super();
    }

    @Override
    public void setCode(String serviceId) {
        // COS_SNCODE
        setNumericCode(Long.valueOf(serviceId));
    }

    @Override
    public void setNumericCode(Long serviceId) {
        // COS_SNCODE
        contractService.setLongValue("COS_SNCODE", serviceId);
    }

    @Override
    public void setContractNumericId(Long contractId) {
        // CO_ID
        contractService.setContractId(contractId);
    }

    @Override
    public void setContractId(String contractId) {
        // CO_ID
        setContractNumericId(Long.valueOf(contractId));
    }

    @Override
    public void setServicePackageCode(String servicePackageId) {
        // COS_SPCODE
        contractService.setLongValue("COS_SPCODE", Long.valueOf(servicePackageId));
    }

    @Override
    public void setProfileId(Long profileId) {
        // PROFILE_ID
        contractService.setProfileId(profileId);
    }

}
