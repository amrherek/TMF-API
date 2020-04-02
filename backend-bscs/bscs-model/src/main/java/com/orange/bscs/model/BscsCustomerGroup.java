package com.orange.bscs.model;

import com.orange.bscs.api.model.BSCSModel;

/**
 * Model used for result of CUSTOMER_GROUPS.READ command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsCustomerGroup {

    protected BSCSModel value;

    public BscsCustomerGroup(BSCSModel value) {
        this.value = value;
    }

    public abstract String getCode();

    public abstract String getDescription();

}
