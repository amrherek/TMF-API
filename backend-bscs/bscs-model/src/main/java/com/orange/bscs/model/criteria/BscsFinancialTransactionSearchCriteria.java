package com.orange.bscs.model.criteria;

import java.util.Date;
import java.util.List;

import com.orange.bscs.api.model.BSCSModel;

/**
 * Model used to set criteria for FINANCIAL_TRANSACTION.SEARCH command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsFinancialTransactionSearchCriteria {

    protected BSCSModel transactionSearchCriteria;

    public BscsFinancialTransactionSearchCriteria() {
        this.transactionSearchCriteria = new BSCSModel();
    }

    public BSCSModel getBscsModel() {
        return transactionSearchCriteria;
    }

    public abstract void setStartDate(Date startDate);

    /**
     * Use CS_ID instead of CS_ID_PUB because with CS_ID_PUB the command is not
     * responding
     * 
     * @param csId
     */
    public abstract void setCustomerNumericId(Long csId);

    public abstract void setEndDate(Date endDate);

    public abstract void setMinAmount(Double minAmount);

    public abstract void setLimit(Integer limit);

    public abstract void setTransactionCodes(List<String> transactionCodes);

}
