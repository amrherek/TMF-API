package com.orange.bscs.model;

import java.util.Date;

import com.orange.bscs.api.model.BSCSModel;

/**
 * Model used for result of FINANCIAL_TRANSACTION.SEARCH command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsFinancialTransactionSearch {

    protected BSCSModel transaction;

    public BscsFinancialTransactionSearch(BSCSModel transaction) {
        this.transaction = transaction;
    }

    public abstract Long getId();

    public abstract String getRef();

    public abstract Date getRefDate();

    public abstract Double getAmount();

    public abstract String getCustomerId();

}
