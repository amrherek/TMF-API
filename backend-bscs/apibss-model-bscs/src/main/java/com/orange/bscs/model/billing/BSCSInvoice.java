package com.orange.bscs.model.billing;

import java.util.Date;
import java.util.List;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

/**
 *
 * <pre>
 * {@code
 * LIST_OF_ALL_BUSINESS_TRANSACTIONS {
 *   BILLING_ACCOUNT_CODE =  : java.lang.String
 *   BILLING_ACCOUNT_ID   =  : java.lang.Long
 *   BT_OHSTATUS          =  : java.lang.String
 *   CO_ID                =  : java.lang.Long
 *   CO_ID_PUB            =  : java.lang.String
 *   CS_ID                =  : java.lang.Long
 *   CS_ID_PUB            =  : java.lang.String
 *   END_BT_OHREFDATE     =  : com.lhs.ccb.common.soiimpl.SVLDateTimeImpl
 *   MAX_NUMBER_OF_FETCHED_RECORDS =  : java.lang.Long
 *   PARTY_TYPE           =  : java.lang.String
 *   START_BT_OHREFDATE   =  : com.lhs.ccb.common.soiimpl.SVLDateTimeImpl
 * *
 * }
 * => {
 *   BT_COMPLAINT          =  : java.lang.String
 *   BILLING_ACCOUNT_CODE  =  : java.lang.String
 *   BT_COMPLAINT_DATE     =  : com.lhs.ccb.common.soiimpl.SVLDateTimeImpl
 *   BILLING_ACCOUNT_ID    =  : java.lang.Long
 *   BT_INVTYPE            =  : java.lang.Integer
 *   BT_OHCANCELFLAG       =  : java.lang.String
 *   BT_OHDUEDATE          =  : com.lhs.ccb.common.soiimpl.SVLDateTimeImpl
 *   BT_OHENTDATE          =  : com.lhs.ccb.common.soiimpl.SVLDateTimeImpl
 *   BT_OHINVAMT_DOC       =  : com.lhs.ccb.soi.types.MoneyI
 *   BT_OHOPNAMT_DOC       =  : com.lhs.ccb.soi.types.MoneyI
 *   BT_OHREFDATE          =  : com.lhs.ccb.common.soiimpl.SVLDateTimeImpl
 *   BT_OHREFNUM           =  : java.lang.String
 *   BT_OHSTATUS           =  : java.lang.String
 *   BT_OHXACT             =  : java.lang.Long
 *   BT_PAYMENTFLAG        =  : java.lang.Boolean
 *   CS_ID                 =  : java.lang.Long
 *   CS_ID_PUB             =  : java.lang.String
 *   CS_ID                 =  : java.lang.Long
 *   CS_ID_PUB             =  : java.lang.String
 *   CURRENCY_ID           =  : java.lang.Long
 *   CURRENCY_ID_PUB       =  : java.lang.String   
 *  
 * }
 * </pre>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public class BSCSInvoice extends BSCSModel {

	/**
	 * KEY
	 */
	private static final String KEY = "LIST_OF_ALL_BUSINESS_TRANSACTIONS";

	/**
	 * 
	 * @return
	 */
	public static String getKey() {
		return KEY;
	}

	/**
	 * 
	 * @param invoiceid
	 */
	public void setInvoicedId(Long invoiceId) {
		setLongValue(Constants.P_BT_OHXACT, invoiceId);
	}

	/**
	 * 
	 * @return
	 */
	public Long getInvoiceId() {
		return getLongValue(Constants.P_BT_OHXACT);
	}

	/**
	 * 
	 * @param currencyId
	 */
	public void setInvoiceCurrencyId(Long currencyId) {
		setLongValue(Constants.P_CURRENCY_ID, currencyId);
	}

	/**
	 * 
	 * @return
	 */
	public Long getInvoiceCurrencyId() {
		return getLongValue(Constants.P_CURRENCY_ID);
	}

	/**
	 * 
	 * @return BT_OHDUEDATE
	 */
	public Date getInvoiceDueDate() {
		return getDateValue(Constants.P_BT_OHDUEDATE);
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public BSCSInvoice setInvoiceDueDate(Date ohDueDate) {
		setDateValue(Constants.P_BT_OHDUEDATE, ohDueDate);
		return this;
	}

	/**
	 * 
	 * @return ohDueDate
	 */
	public Date getInvoiceDate() {
		return getDateValue(Constants.P_BT_OHREFDATE);
	}

	/**
	 * 
	 * @param ohRefDate
	 * @return ohRefDate
	 */
	public BSCSInvoice setInvoiceDate(Date ohRefDate) {
		setDateValue(Constants.P_BT_OHREFDATE, ohRefDate);
		return this;
	}

	/**
	 * 
	 * @param ohStatus
	 */
	public void setInvoiceStatut(String ohStatus) {
		setStringValue(Constants.P_BT_OHSTATUS, ohStatus);
	}

	/**
	 * 
	 * @return BT_OHSTATUS
	 */
	public String getInvoiceStatut() {
		return getStringValue(Constants.P_BT_OHSTATUS);
	}

	/**
	 * 
	 * @param billingAccountCode
	 */
	public void setInvoiceRemainderAmount(String ohOpNameDoc) {
		setStringValue(Constants.P_BT_OHOPNAMT_DOC, ohOpNameDoc);
	}

	/**
	 * 
	 * @return BT_OHOPNAMT_DOC
	 */
	public String getInvoiceRemainderAmount() {
		return getStringValue(Constants.P_BT_OHOPNAMT_DOC);
	}

	/**
	 * 
	 * @param P_BT_OHINVAMT_DOC
	 */
	public void setInvoiceAmountDue(String ohInvAmtDoc) {
		setStringValue(Constants.P_BT_OHINVAMT_DOC, ohInvAmtDoc);
	}

	/**
	 * 
	 * @return P_BT_OHINVAMT_DOC
	 */

	public String getInvoiceAmountDue() {
		return getStringValue(Constants.P_BT_OHINVAMT_DOC);
	}
	
	/**
	 * 
	 * @param P_START_BT_OHREFDATE
	 */
	public void setInvoiceStartDate(Date startDate) {
		setDateTimeValue(Constants.P_START_BT_OHREFDATE, startDate);
	}

	/**
	 * 
	 * @return P_START_BT_OHREFDATE
	 */

	public Date getInvoiceStartDate() {
		return getDateValue(Constants.P_START_BT_OHREFDATE);
	}
	
	/**
	 * 
	 * @param P_END_BT_OHREFDATE
	 */
	public void setInvoiceEndDate(Date endDate) {
		setDateTimeValue(Constants.P_END_BT_OHREFDATE, endDate);
	}
	
	
	/**
	 * 
	 * @return P_END_BT_OHREFDATE
	 */

	public Date getInvoiceEndtDate() {
		return getDateValue(Constants.P_END_BT_OHREFDATE);
	}
	
	/**
	 * 
	 * @param P_END_BT_OHREFDATE
	 */
	public void setInvoiceWichResult(Long wichResult) {
		setLongValue(Constants.P_MAX_NUMBER_OF_FETCHED_RECORDS, wichResult);
	}
	
	
	/**
	 * 
	 * @return P_END_BT_OHREFDATE
	 */

	public Long getInvoiceWichResult() {
		return getLongValue(Constants.P_MAX_NUMBER_OF_FETCHED_RECORDS);
	}
	
	
	
	

	/**
	 * 
	 * @return List<BSCSModel>
	 */
	public List<BSCSModel> getAddresses() {
		List<BSCSModel> res = this
				.getListOfBSCSModelValue(KEY, BSCSModel.class);
		return res;
	}
	
	

}
