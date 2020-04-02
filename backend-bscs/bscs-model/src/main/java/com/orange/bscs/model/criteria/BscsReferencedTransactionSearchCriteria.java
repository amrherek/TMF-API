package com.orange.bscs.model.criteria;

import java.util.Date;

import com.orange.bscs.api.model.BSCSModel;


/**
 * Model used to set criteria for REFERENCED_TRANSACTIONS.SEARCH command
 * 
 * @author elarbih
 *
 */
public abstract class BscsReferencedTransactionSearchCriteria {

    protected BSCSModel model;

    public BscsReferencedTransactionSearchCriteria() {
        this.model = new BSCSModel();
    }

    public BSCSModel getBscsModel() {
        return model;
    }

    public abstract void setCustomerId(String customerId);
    
    public abstract void setCustomerNumericId(Long customerId);
    
    public abstract void setStartDate(Date startDate);
    
    public abstract void setEndDate(Date endDate);
    
    public abstract void setReferenceNumber(String referenceNumber);

}
