package com.orange.bscs.model;

import com.orange.bscs.model.contract.BSCSContractService;

/**
 * Model used for partial result of CONTRACT.READ command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsContractServiceFromContract {

    protected BSCSContractService contractService;

    public BscsContractServiceFromContract(BSCSContractService contractService) {
        this.contractService = contractService;
    }

    public abstract Long getNumericCode();

    public abstract String getCode();

    public abstract String getServicePackageCode();

    public abstract String getContractId();

}
