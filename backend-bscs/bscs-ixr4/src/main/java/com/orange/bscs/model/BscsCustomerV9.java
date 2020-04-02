package com.orange.bscs.model;

import java.util.Date;

import com.orange.bscs.model.businesspartner.BSCSCustomer;
import com.orange.bscs.model.businesspartner.EnumCustomerStatus;

public class BscsCustomerV9 extends BscsCustomer {

    public BscsCustomerV9(BSCSCustomer customer) {
        super(customer);
    }

    @Override
    public String getId() {
        // CS_ID_PUB
        return customer.getCustomerIDPub();
    }

    @Override
    public String getBillingCycle() {
        // CS_BILLCYCLE
        return customer.getBillingCycle();
    }

    @Override
    public Date getLastBillingCycleDate() {
        // CS_LAST_BC_DATE
        return customer.getLastBillingCycleDate();
    }

    @Override
    public String getPriceGroupCode() {
        // PRG_CODE
        return customer.getPriceGroupCode();
    }

    @Override
    public EnumCustomerStatus getStatus() {
        // CS_STATUS
        return customer.getStatus();
    }

    @Override
    public String getCode() {
        // CS_CODE
        return customer.getCustomerCode();
    }

    @Override
    public Long getNumericId() {
        // CS_ID
        return customer.getCustomerID();
    }
}
