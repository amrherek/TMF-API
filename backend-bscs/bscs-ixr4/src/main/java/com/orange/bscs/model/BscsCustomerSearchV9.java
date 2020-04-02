package com.orange.bscs.model;

import com.orange.bscs.model.businesspartner.BSCSCustomer;

public class BscsCustomerSearchV9 extends BscsCustomerSearch {

    public BscsCustomerSearchV9(BSCSCustomer customer) {
        super(customer);
    }

    @Override
    public String getId() {
        // CS_ID_PUB
        return customer.getCustomerIDPub();
    }

}
