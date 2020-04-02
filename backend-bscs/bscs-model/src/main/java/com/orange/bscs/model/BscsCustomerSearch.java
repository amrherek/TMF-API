package com.orange.bscs.model;

import com.orange.bscs.model.businesspartner.BSCSCustomer;

/**
 * Model used for result of CUSTOMERS.SEARCH command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsCustomerSearch {

    protected BSCSCustomer customer;

    public BscsCustomerSearch(BSCSCustomer customer) {
        this.customer = customer;
    }

    public abstract String getId();
}
