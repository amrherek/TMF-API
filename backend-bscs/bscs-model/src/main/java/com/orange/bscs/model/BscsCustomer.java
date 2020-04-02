package com.orange.bscs.model;

import java.util.Date;

import com.orange.bscs.model.businesspartner.BSCSCustomer;
import com.orange.bscs.model.businesspartner.EnumCustomerStatus;

/**
 * Model used for result of CUSTOMER.READ command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsCustomer {

    protected BSCSCustomer customer;

    public BscsCustomer(BSCSCustomer customer) {
        this.customer = customer;
    }

    public abstract String getId();

    public abstract String getBillingCycle();

    public abstract Date getLastBillingCycleDate();

    public abstract String getPriceGroupCode();

    public abstract EnumCustomerStatus getStatus();

    public abstract String getCode();

    public abstract Long getNumericId();
}
