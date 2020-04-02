package com.orange.bscs.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.orange.bscs.model.businesspartner.EnumContractStatus;
import com.orange.bscs.model.contract.BSCSContract;

/**
 * Model used for result of CONTRACT.READ command (getters) and input of
 * CONTRACT.WRITE command (setters)
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsContract {

    protected BSCSContract contract;

    public BscsContract() {
        this.contract = new BSCSContract();
    }

    public BscsContract(BSCSContract contract) {
        this.contract = contract;
    }

    public BSCSContract getBscsModel() {
        return contract;
    }

    public abstract String getId();

    public abstract void setId(String id);

    public abstract Long getNumericId();

    public abstract String getRatePlanCode();

    public abstract Long getRatePlanNumericCode();

    public abstract String getStorageMediumNum();

    public abstract EnumContractStatus getStatus();

    public abstract void setStatus(EnumContractStatus status);

    public abstract Date getDateActivated();

    public abstract Date getStatusDate();

    public abstract String getCustomerId();

    public abstract Long getLastReasonChangeStatus();

    public abstract void setReasonChangeStatus(Long reason);

    public abstract List<BscsContractServiceFromContract> getServices();

    /**
     * Return list of subscribed services
     *
     * @return
     */
    public Set<Long> getSubscribedServices() {
        final Set<Long> res = new HashSet<>();
        final List<BscsContractServiceFromContract> services = getServices();
        for (final BscsContractServiceFromContract service : services) {
            res.add(service.getNumericCode());
        }
        return res;
    }

    /**
     * Set the requested status modification date
     * 
     * @param statusModificationDate
     */
    public abstract void setValidFrom(Date statusModificationDate);

}
