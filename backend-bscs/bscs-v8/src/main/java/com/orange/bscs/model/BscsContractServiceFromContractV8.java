package com.orange.bscs.model;

import org.apache.commons.lang.ObjectUtils;

import com.orange.bscs.model.contract.BSCSContractService;

public class BscsContractServiceFromContractV8 extends BscsContractServiceFromContract {

    public BscsContractServiceFromContractV8(BSCSContractService contractService) {
        super(contractService);
    }

    @Override
    public Long getNumericCode() {
        // CO_SN_CODE
        return contractService.getLongValue("CO_SN_CODE");
    }

    @Override
    public String getCode() {
        // CO_SN_CODE
        return ObjectUtils.toString(getNumericCode());
    }

    @Override
    public String getServicePackageCode() {
        // CO_SP_CODE
        return ObjectUtils.toString(contractService.getLongValue("CO_SP_CODE"), null);
    }

    @Override
    public String getContractId() {
        // CO_ID
        return ObjectUtils.toString(contractService.getContractId(), null);
    }

}
