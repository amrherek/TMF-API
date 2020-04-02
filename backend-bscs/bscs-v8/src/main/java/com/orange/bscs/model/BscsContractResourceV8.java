package com.orange.bscs.model;

import java.util.Date;

import com.orange.bscs.model.contract.BSCSContractResource;

public class BscsContractResourceV8 extends BscsContractResource {

    public BscsContractResourceV8(BSCSContractResource resource) {
        super(resource);
    }

    public BscsContractResourceV8() {
        super();
    }

    @Override
    public void setContractId(String contractId) {
        // CO_ID
        if (contractId != null) {
            resource.setCoId(Long.parseLong(contractId));
        }
    }

    @Override
    public void setActivationDate(Date activationDate) {
        // ACTIV_DATE
        resource.setActivDate(activationDate);
    }

    @Override
    public void setType(Integer deviceType) {
        // RES_TYPE
        resource.setResType(deviceType);
    }

    @Override
    public void setOldNumber(String oldDeviceNumber) {
        // OLD_RES_NUM
        resource.setOldResNum(oldDeviceNumber);
    }

    @Override
    public void setNewId(Long deviceId) {
        // RES_ID
        resource.setResId(deviceId);
    }

}
