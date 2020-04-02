package com.orange.bscs.model;

import java.util.Date;

public class BscsPaymentV8 extends BscsPayment {

    public BscsPaymentV8() {
        super();
    }

	@Override
	public void setTransactionId(Long transactionId) {
		model.setLongValue("BT_OHXACT", transactionId);
		
	}
	
	@Override
	public void setPaymentMode(String paymentMode) {
		model.setStringValue("PAYMENT_MODE", paymentMode);	
		
	}

	@Override
	public void setReferenceDate(Date date) {
        model.setDateValue("RT_CACHKDATE", date);
		
	}

	@Override
	public void setReferenceNumber(String reference) {
		model.setStringValue("RT_CACHKNUM", reference);	
		
	}

	@Override
	public void setRemark(String referenceTpApplication) {
		model.setStringValue("RT_CAREM", referenceTpApplication);	
		
	}

	@Override
	public void setCauserName(String causerName) {
		model.setStringValue("RT_CAUSERNAME", causerName);	
		
	}

	@Override
	public void setSynchronousMode(Boolean synchronousMode) {
		model.setBooleanValue("SYNCHRONOUS_MODE", synchronousMode);	
		
	}

	@Override
	public void setTransactionCode(String transactionCode) {
		model.setStringValue("TRANSX_CODE", transactionCode);	
		
	}

	@Override
	public void setCustomerNumericId(Long customerNumericId) {
        model.setLongValue("CUSTOMER_ID", customerNumericId);
		
	}

	@Override
	public void setCustomerId(String customerPublicId) {
        if (customerPublicId != null) {
            setCustomerNumericId(Long.parseLong(customerPublicId));
        }
	}

	@Override
    public void setAmount(Double amount, String currency) {
        model.setMoneyValue("RT_CACHKAMT_PAY", amount, currency);
	}
	@Override
	public void setReceiptDate(Date date) {
        model.setDateValue("RT_CARECDATE", date);
		
	}
   
}
