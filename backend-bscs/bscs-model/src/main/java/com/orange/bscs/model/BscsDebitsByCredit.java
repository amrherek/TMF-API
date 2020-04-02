package com.orange.bscs.model;

import java.util.Date;

import com.orange.bscs.api.model.BSCSModel;

/**
 * Model used for result of DEBITSBYCREDIT.READ
 * command
 * 
 * @author elarbih
 *
 */
public abstract class BscsDebitsByCredit {

    protected BSCSModel model;

    
    public BscsDebitsByCredit(BSCSModel model) {
        this.model = model;
    }
        
    public BSCSModel getModel() {
		return model;
	}

    public abstract Long getInvoiceType();
    public abstract Date getDueDate();
    public abstract Double getInvoiceAmount();
    public abstract String getInvoiceCurrency();
    public abstract Double getCurrentAmount();
    public abstract Date getReferenceDate();
    public abstract String getDocumentReference();
    public abstract String getDocumentStatus();
    public abstract Long getTransactionId();
    public abstract Long getCustomerNumericId();
    public abstract String getCustomerId();
    public abstract Long getCurrencyId();
    public abstract String getCurrencyCode();


	
    
    
    
    
    
    
    
}
