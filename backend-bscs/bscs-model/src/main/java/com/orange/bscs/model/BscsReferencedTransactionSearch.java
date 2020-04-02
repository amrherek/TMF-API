package com.orange.bscs.model;

import java.util.Date;

import com.orange.bscs.api.model.BSCSModel;

/**
 * Model used for result of CREDITSBYDEBIT.READ and
 * REFERENCED_TRANSACTION.SEARCH
 * 
 * @author jwck2987
 *
 */
public abstract class BscsReferencedTransactionSearch {

    protected BSCSModel model;

    public BscsReferencedTransactionSearch(BSCSModel model) {
        this.model = model;
    }

    public abstract Double getAmount();

    public abstract String getAmountCurrency();

    public abstract Long getId();

    public abstract Date getDate();
    
    public abstract Long getCustomerNumericId();
	
    public abstract String getCustomerId();
    
    public abstract String getCaType() ;
	
    public abstract Long getReasonPayment();
	
    public abstract String getReferencePayment();
	
    public abstract Date getEntryDate();
	
    public abstract Double getPaymentAmount();
	
    public abstract String getPaymentCurrency() ;
	
    public abstract String getDocumentCurrency();
	
    public abstract String getRemark();
	
    public abstract Date getCheckDate();
	
    public abstract Date getReceiptDate();

}
