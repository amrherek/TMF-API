package com.orange.bscs.model.criteria;

public class BscsContractServiceSearchCriteriaV9 extends BscsContractServiceSearchCriteria {

    public BscsContractServiceSearchCriteriaV9() {
        super();
    }

    @Override
    public void setCode(String serviceId) {
        // SNCODE_PUB
        contractService.setServiceCodePub(serviceId);
    }

    @Override
    public void setNumericCode(Long serviceId) {
        // SNCODE
        contractService.setServiceCode(serviceId);
    }

    @Override
    public void setContractNumericId(Long contractId) {
        // CO_ID
        contractService.setContractId(contractId);
    }

    @Override
    public void setContractId(String contractId) {
        // CO_ID_PUB
        contractService.setContractIdPub(contractId);
    }

    @Override
    public void setServicePackageCode(String servicePackageId) {
        // SPCODE_PUB
        contractService.setServicePackagePublicCode(servicePackageId);
    }

    @Override
    public void setProfileId(Long profileId) {
        // PROFILE_ID
        contractService.setProfileId(profileId);
    }

}
