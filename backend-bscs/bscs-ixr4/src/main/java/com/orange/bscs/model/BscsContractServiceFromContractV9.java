package com.orange.bscs.model;

import com.orange.bscs.model.contract.BSCSContractService;

public class BscsContractServiceFromContractV9 extends BscsContractServiceFromContract {

    public BscsContractServiceFromContractV9(BSCSContractService contractService) {
        super(contractService);
    }

    @Override
    public Long getNumericCode() {
        // SNCODE
        return contractService.getServiceCode();
    }

    @Override
    public String getCode() {
        // SNCODE_PUB
        return contractService.getServiceCodePub();
    }

    @Override
    public String getServicePackageCode() {
        // SPCODE_PUB
        return contractService.getServicePackagePublicCode();
    }

    @Override
    public String getContractId() {
        // CO_ID_PUB
        return contractService.getContractIdPub();
    }

}
