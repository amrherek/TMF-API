package com.orange.bscs.model.criteria;

public class BscsContractServiceParameterSearchCriteriaV8 extends BscsContractServiceParameterSearchCriteria {

    public BscsContractServiceParameterSearchCriteriaV8() {
        super();
    }

    @Override
    public void setCode(String serviceId) {
        // SN_CODE
        setNumericCode(Long.valueOf(serviceId));
    }

    @Override
    public void setNumericCode(Long serviceId) {
        // SN_CODE
        contractService.setLongValue("SN_CODE", serviceId);
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
        // SP_CODE
        contractService.setLongValue("SP_CODE", Long.valueOf(servicePackageId));
    }

    @Override
    public void setProfileId(Long profileId) {
        // SP_CODE
        contractService.setProfileId(profileId);
    }

}
