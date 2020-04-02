package com.orange.bscs.model.criteria;

import com.orange.bscs.model.contract.BSCSContractService;

/**
 * Model used to set criteria for CONTRACT_SERVICE_PARAMETERS.READ command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsContractServiceParameterSearchCriteria {

    protected BSCSContractService contractService;

    public BscsContractServiceParameterSearchCriteria() {
        this.contractService = new BSCSContractService();
    }

    public BSCSContractService getBscsModel() {
        return contractService;
    }

    public abstract void setCode(String serviceId);

    public abstract void setNumericCode(Long serviceId);

    public abstract void setContractNumericId(Long contractId);

    public abstract void setContractId(String contractId);

    public abstract void setServicePackageCode(String servicePackageId);

    public abstract void setProfileId(Long profileId);

}
