package com.orange.bscs.model;

import java.util.Date;

import com.orange.bscs.api.model.BSCSModel;

/**
 * Model used for result of PAYMENT.WRITE 
 * command
 * 
 * @author elarbih
 *
 */
public abstract class BscsPayment {

    protected BSCSModel model;

    
    public BscsPayment() {
        this.model = new BSCSModel();
    }
        
    public BSCSModel getModel() {
		return model;
	}
    
    public abstract void setTransactionId(Long transactionId);
  
    public abstract void setCustomerNumericId(Long customerNumericId);
  
    public abstract void setCustomerId(String customerPublicId);
    
    
    public abstract void setPaymentMode(String paymentMode);
    
    public abstract void setAmount(Double amount, String currency);
       

    public abstract void setReferenceDate(Date date);
    
    public abstract void setReferenceNumber(String reference);
    
    public abstract void setRemark(String referenceTpApplication);
    
    public abstract void setCauserName(String causerName);
    
    public abstract void setSynchronousMode(Boolean  synchronousMode);
    
    /*
     *  	CE2IN-X2  invoice payment
 			CE2CO  advance payment

     */
    public abstract void setTransactionCode(String  transactionCode);
    
    
    public abstract void setReceiptDate(Date date);
    
    
}
