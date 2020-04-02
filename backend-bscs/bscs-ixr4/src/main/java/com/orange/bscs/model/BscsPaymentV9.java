package com.orange.bscs.model;

import java.util.Date;

public class BscsPaymentV9 extends BscsPayment {

    public BscsPaymentV9() {
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
		model.setDateTimeValue("RT_CACHKDATE", date);	
		
	}

	@Override
	public void setReferenceNumber(String reference) {
		model.setStringValue("RT_CACHKNUM", reference);	
		
	}

	@Override
	public void setRemark(String remark) {
		model.setStringValue("RT_CAREM", remark);	
		
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
		model.setLongValue("CS_ID", customerNumericId);
		
	}

	@Override
	public void setCustomerId(String customerPublicId) {
		model.setStringValue("CS_ID_PUB", customerPublicId);	
		
	}

	@Override
	public void setAmount(Double amount, String currency) {
        model.setMoneyValue("RT_CACHKAMT_PAY", amount, currency);
	}

	@Override
	public void setReceiptDate(Date date) {
		model.setDateTimeValue("RT_CARECDATE", date);
		
	}
}
