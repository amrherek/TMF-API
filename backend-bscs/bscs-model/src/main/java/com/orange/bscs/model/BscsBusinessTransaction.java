package com.orange.bscs.model;

import java.util.Date;

import com.orange.bscs.model.billing.BusinessTransaction;

/**
 * Model used for result of BUSINESS_TRANSACTION.READ command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsBusinessTransaction {

    protected BusinessTransaction transaction;

    public BscsBusinessTransaction(BusinessTransaction transaction) {
        this.transaction = transaction;
    }

    public abstract Long getId();
    
    public abstract Date getEntryDate();
    
    public abstract Date getDueDate();
    
    public abstract Double getAmount();
    
    public abstract Double getOpenAmount();
    
    public abstract String getCustomerId();
    
    public abstract String getAmountCurrency();

    public abstract String getRef();

    public abstract String getStatus();

}
