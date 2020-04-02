package com.orange.bscs.model;

import java.util.Date;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

public class BscsDebitsByCreditV9 extends BscsDebitsByCredit {

    public BscsDebitsByCreditV9(BSCSModel model) {
    	super(model);
    }

	@Override
	public Long getInvoiceType() {
		return model.getLongValue(Constants.P_BT_INVTYPE);
	}

	@Override
	public Date getDueDate() {
		return model.getDateTimeValue(Constants.P_BT_OHDUEDATE);
	}

	@Override
	public Double getInvoiceAmount() {
		return model.getMoneyAmountValue(Constants.P_BT_OHINVAMT_DOC);
	}

    @Override
    public String getInvoiceCurrency() {
        return model.getMoneyCurrencyCodeValue(Constants.P_BT_OHINVAMT_DOC);
    }

	@Override
	public Double getCurrentAmount() {
		return model.getMoneyAmountValue(Constants.P_BT_OHOPNAMT_DOC);
	}

	@Override
	public Date getReferenceDate() {
		return model.getDateTimeValue(Constants.P_BT_OHREFDATE);
	}

	@Override
	public String getDocumentReference() {
		return model.getStringValue(Constants.P_BT_OHREFNUM);
	}

	@Override
	public String getDocumentStatus() {
		return model.getStringValue(Constants.P_BT_OHSTATUS);
	}

	@Override
	public Long getTransactionId() {
		return model.getLongValue(Constants.P_BT_OHXACT);
	}

	@Override
	public Long getCustomerNumericId() {
		return model.getLongValue(Constants.P_CS_ID);
	}

	@Override
	public String getCustomerId() {
		return model.getStringValue(Constants.P_CS_ID_PUB);
	}

	@Override
	public Long getCurrencyId() {
		return model.getLongValue(Constants.P_CURRENCY_ID);
	}

	@Override
	public String getCurrencyCode() {
		return model.getStringValue(Constants.P_RT_FCCODE);
	}

   
}
