package com.orange.bscs.model.criteria;

import java.util.Date;

import com.orange.bscs.model.billing.BusinessTransaction;

/**
 * Model used to set criteria for BUSINESS_TRANSACTIONS.SEARCH command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsBusinessTransactionSearchCriteria {

    protected BusinessTransaction transactionSearchCriteria;

    public BscsBusinessTransactionSearchCriteria() {
        this.transactionSearchCriteria = new BusinessTransaction();
    }

    public BusinessTransaction getBscsModel() {
        return transactionSearchCriteria;
    }

    public abstract void setCustomerId(String customerId);
    
    public abstract void setStartDate(Date startDate);
    
    public abstract void setEndDate(Date endDate);
    
    public abstract void setWishedRecords(Integer wishedResults);

    public abstract Integer getWishedRecords();

    public abstract void setTransactionType(String type);

}
