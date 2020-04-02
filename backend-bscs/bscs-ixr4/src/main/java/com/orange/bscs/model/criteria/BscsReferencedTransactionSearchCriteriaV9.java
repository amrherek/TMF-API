package com.orange.bscs.model.criteria;

import java.util.Date;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

public class BscsReferencedTransactionSearchCriteriaV9 extends BscsReferencedTransactionSearchCriteria {

    public BscsReferencedTransactionSearchCriteriaV9() {
        super();
    }
    
	public BSCSModel getModel() {
		return model;
	}

	@Override
	public void setCustomerId(String customerId) {
		// CS_ID_PUB
	       model.setStringValue(Constants.P_CS_ID_PUB, customerId);
		
	}

	@Override
	public void setCustomerNumericId(Long customerId) {
		// CS_ID
		model.setLongValue(Constants.P_CS_ID, customerId);
		
	}
	
	@Override
	public void setStartDate(Date startDate) {
		//RT_STARTDATE
		 model.setDateTimeValue(Constants.P_RT_STARTDATE, startDate);
		
	}

	@Override
	public void setEndDate(Date endDate) {
		 // RT_ENDDATE
		 model.setDateTimeValue(Constants.P_RT_ENDDATE,endDate);
		
	}

	@Override
	public void setReferenceNumber(String referenceNumber) {
		// RT_CACHKNUM
		model.setStringValue(Constants.P_RT_CACHKNUM, referenceNumber);
		
	}
	  

}
