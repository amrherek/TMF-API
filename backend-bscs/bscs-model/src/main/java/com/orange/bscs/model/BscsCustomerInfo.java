package com.orange.bscs.model;

import com.orange.bscs.api.model.BSCSModel;

/**
 * Model used for result of CUSTOMER_INFO.READ command and input of
 * CUSTOMER_INFO.WRITE command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsCustomerInfo {

    protected BSCSModel info;

    public BscsCustomerInfo(BSCSModel info) {
        this.info = info;
    }

    public BscsCustomerInfo() {
        info = new BSCSModel();
    }

    public BSCSModel getBscsModel() {
        return info;
    }

    public abstract String getText(String number);

    public abstract void setText(String number, String value);

    public abstract void setCustomerId(String id);

    public abstract String getCustomerId();
}
