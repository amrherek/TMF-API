package com.orange.bscs.model;

import java.util.Date;

import com.orange.bscs.model.businesspartner.EnumContractStatus;
import com.orange.bscs.model.contract.BSCSContractService;

/**
 * Model used for result of CONTRACT_SERVICES.READ command and input of
 * CONTRACT_SERVICES.ADD and CONTRACT_SERVICES.WRITE commands
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsContractService {

    protected BSCSContractService contractService;

    public BscsContractService(BSCSContractService contractService) {
        this.contractService = contractService;
    }

    public BscsContractService() {
        this.contractService = new BSCSContractService();
    }

    public BSCSContractService getBscsModel() {
        return contractService;
    }

    public abstract Long getNumericCode();

    public abstract String getCode();

    public abstract void setCode(String serviceCode);

    public abstract String getServicePackageCode();

    public abstract void setServicePackageCode(String servicePackageCode);

    public abstract String getContractId();

    /**
     * Get service activation date.
     * 
     * @return
     */
    public abstract Date getLastActivationDate();

    public abstract void setActivationDate(Date activationDate);

    /**
     * Get service status.
     * 
     * @return
     */
    public abstract EnumContractStatus getStatus();

    public abstract void setStatus(EnumContractStatus pendingStatus);

    /**
     * Get service status change date.
     * 
     * @return
     */
    public abstract Date getStatusDate();

    public abstract void setStatusDate(Date pendingStatusDate);

    public abstract void setProfileId(Long profileId);

}
