package com.orange.bscs.model;

import java.util.Date;

import com.orange.bscs.model.billing.BusinessTransaction;

/**
 * Model used for result of BUSINESS_TRANSACTIONS.SEARCH command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsBusinessTransactionSearch {

    protected BusinessTransaction transaction;

    public BscsBusinessTransactionSearch(BusinessTransaction transaction) {
        this.transaction = transaction;
    }

    public abstract Long getId();
    
    public abstract Date getEntryDate();
    
    public abstract Date getDueDate();
    
    public abstract Double getAmount();
    
    public abstract Double getOpenAmount();
    
    public abstract String getCustomerId();
    
    public abstract String getAmountCurrency();

}
