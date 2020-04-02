package com.orange.bscs.model;

import java.util.Date;

import com.orange.bscs.model.businesspartner.EnumContractStatus;
import com.orange.bscs.model.contract.BSCSContract;

/**
 * Model used for result of CONTRACTS.SEARCH command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsContractSearch {

    protected BSCSContract contract;

    public BscsContractSearch(BSCSContract contract) {
        this.contract = contract;
    }

    public abstract String getId();

    public abstract Long getNumericId();

    public abstract EnumContractStatus getStatus();

    public abstract String getDirectoryNumber();

    public abstract String getStorageMediumNum();

    public abstract String getCustomerId();

    public abstract Date getDateActivated();

    public abstract Long getNumericRatePlanCode();

    public abstract Character getPaymentMethodIndicator();

}
