package com.orange.bscs.model;

import java.util.Date;

import com.orange.bscs.model.contract.BSCSContractResource;

/**
 * Model used for input of CONTRACT_RESOURCES.REPLACE command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsContractResource {

    protected BSCSContractResource resource;

    public BscsContractResource() {
        this.resource = new BSCSContractResource();
    }

    public BscsContractResource(BSCSContractResource resource) {
        this.resource = resource;
    }

    public BSCSContractResource getBscsModel() {
        return resource;
    }

    public abstract void setContractId(String contractId);

    public abstract void setActivationDate(Date activationDate);

    public abstract void setType(Integer deviceType);

    /**
     * Set old device number
     * 
     * @param oldDeviceNumber
     */
    public abstract void setOldNumber(String oldDeviceNumber);

    /**
     * Set new device id
     * 
     * @param deviceId
     */
    public abstract void setNewId(Long deviceId);

}
