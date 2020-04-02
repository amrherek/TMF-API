package com.orange.bscs.model;

import java.util.Objects;

import com.orange.bscs.model.businesspartner.BSCSCustomer;

public class BscsCustomerSearchV8 extends BscsCustomerSearch {

    public BscsCustomerSearchV8(BSCSCustomer customer) {
        super(customer);
    }

    @Override
    public String getId() {
        // CS_ID
        return Objects.toString(customer.getCustomerID(), null);
    }

}
