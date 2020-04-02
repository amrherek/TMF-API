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
public class BusinessTransaction extends BSCSModel {

	/**
	 * 
	 * @param invoiceid
	 */
	public void setTransactionId(Long transactionId) {
		setLongValue(Constants.P_BT_OHXACT, transactionId);
	}

	/**
	 * 
	 * @return P_BT_OHXACT
	 */
	public Long getTransactionId() {
		return getLongValue(Constants.P_BT_OHXACT);
	}

	/**
	 * 
	 * @return P_CO_ID_PUB
	 */
	public String getContractIdPub() {
		return getStringValue(Constants.P_CO_ID_PUB);
	}

	/**
	 * 
	 * @param P_CS_ID
	 */
	public void setCsId(Long coIdPub) {
		setLongValue(Constants.P_CS_ID, coIdPub);
	}

	/**
	 * 
	 * @return P_CS_ID
	 */
	public Long getsetCsId() {
		return getLongValue(Constants.P_CS_ID);
	}
	
	/**
	 * 
	 * @param P_CS_ID_PUB
	 */
	public void setCsIdPub(String coIdPub) {
		setStringValue(Constants.P_CS_ID_PUB, coIdPub);
	}

	/**
	 * 
	 * @return P_CS_ID_PUB
	 */
	public String getCsIdPub() {
		return getStringValue(Constants.P_CS_ID_PUB);
	}

	/**
	 * 
	 * @param P_CO_ID_PUB
	 */
	public void setContractIdPub(String coIdPub) {
		setStringValue(Constants.P_CO_ID_PUB, coIdPub);
	}

	/**
	 * 
	 * @param P_CURRENCY_ID
	 */
	public void setCurrencyId(Long currencyId) {
		setLongValue(Constants.P_CURRENCY_ID, currencyId);
	}

	/**
	 * 
	 * @return P_CURRENCY_ID_PUB
	 */
	public String getCurrencyIdPub() {
		return getStringValue(Constants.P_CURRENCY_ID_PUB);
	}
	
	/**
	 * 
	 * @param P_CURRENCY_ID_PUB
	 */
	public void setCurrencyIdPub(String currencyIdPub) {
		setStringValue(Constants.P_CURRENCY_ID_PUB, currencyIdPub);
	}

	/**
	 * 
	 * @return P_CURRENCY_ID
	 */
	public Long getCurrencyId() {
		return getLongValue(Constants.P_CURRENCY_ID);
	}

	/**
	 * 
	 * @return BT_OHDUEDATE
	 */
	public Date getBtOhDueDate() {
		return getDateTimeValue(Constants.P_BT_OHDUEDATE);
	}

	/**
	 * 
	 * @param ohDueDate
	 * @return
	 */
	public BusinessTransaction setBtOhDueDate(Date ohDueDate) {
		setDateTimeValue(Constants.P_BT_OHDUEDATE, ohDueDate);
		return this;
	}

	/**
	 * 
	 * @return P_BT_OHENTDATE
	 */
	public Date getBtOhEntDate() {
		return getDateTimeValue(Constants.P_BT_OHENTDATE);
	}

	/**
	 * 
	 * @param P_BT_OHENTDATE
	 * @return
	 */
	public BusinessTransaction setBtOhEntDate(Date ohEntDate) {
		setDateTimeValue(Constants.P_BT_OHENTDATE, ohEntDate);
		return this;
	}

	/**
	 * 
	 * @return ohDueDate
	 */
	public Date getOhRefDate() {
		return getDateTimeValue(Constants.P_BT_OHREFDATE);
	}

	/**
	 * 
	 * @param P_BT_OHREFDATE
	 * @return P_BT_OHREFDATE
	 */
	public BusinessTransaction setOhRefDate(Date ohRefDate) {
		setDateTimeValue(Constants.P_BT_OHREFDATE, ohRefDate);
		return this;
	}

	/**
	 * 
	 * @param P_BT_OHSTATUS
	 */
	public void setBtOhStatut(String ohStatus) {
		setStringValue(Constants.P_BT_OHSTATUS, ohStatus);
	}

	/**
	 * 
	 * @return BT_OHSTATUS
	 */
	public String getBtOhStatut() {
		return getStringValue(Constants.P_BT_OHSTATUS);
	}

	/**
	 * 
	 * @param P_BT_OHOPNAMT_DOC
	 */
    public void setBtohOpenAmount(Double ohOpenAmount) {
        setDoubleValue(Constants.P_BT_OHOPNAMT_DOC, ohOpenAmount);
	}

	/**
	 * 
	 * @return P_BT_OHOPNAMT_DOC
	 */
    public Double getBtOhOpAmtDoc() {
		return getMoneyAmountValue(Constants.P_BT_OHOPNAMT_DOC);
	}

	/**
	 * 
	 * @param P_BT_OHINVAMT_DOC
	 */
    public void setBtOhOpAmtDoc(Double ohOpAmtDoc) {
        setDoubleValue(Constants.P_BT_OHINVAMT_DOC, ohOpAmtDoc);
	}

	/**
	 * 
	 * @return P_BT_OHINVAMT_DOC
	 */

	public Double getBtOhInvAmtDoc() {
		return getMoneyAmountValue(Constants.P_BT_OHINVAMT_DOC);
	}

    public String getBtOhInvAmtDoc_currency() {
        return getMoneyCurrencyCodeValue(Constants.P_BT_OHINVAMT_DOC);
    }

	/**
	 * 
	 * @param P_START_BT_OHREFDATE
	 */
	public void setStartBtOhRefDate(Date startDate) {
		setDateTimeValue(Constants.P_START_BT_OHREFDATE, startDate);
	}

	/**
	 * 
	 * @return P_START_BT_OHREFDATE
	 */

	public Date getStartBtOhRefDate() {
		return getDateTimeValue(Constants.P_START_BT_OHREFDATE);
	}

	/**
	 * 
	 * @param P_END_BT_OHREFDATE
	 */
	public void setEndBtOhRefDate(Date endDate) {
		setDateTimeValue(Constants.P_END_BT_OHREFDATE, endDate);
	}

	/**
	 * 
	 * @return P_END_BT_OHREFDATE
	 */

	public Date getEndBtOhRefDate() {
		return getDateTimeValue(Constants.P_END_BT_OHREFDATE);
	}

	/**
	 * 
	 * @param MAX_NUMBER_OF_FETCHED_RECORDS
	 */
	public void setMaxNumOfFetchRecords(Long wichResult) {
		setLongValue(Constants.P_MAX_NUMBER_OF_FETCHED_RECORDS, wichResult);
	}

	/**
	 * 
	 * @return P_MAX_NUMBER_OF_FETCHED_RECORDS
	 */

	public Long getMaxNumOfFetchRecords() {
		return getLongValue(Constants.P_MAX_NUMBER_OF_FETCHED_RECORDS);
	}

	/**
	 * 
	 * @return List<BusinessTransaction>
	 */
	public List<BusinessTransaction> getBusinessTransactionSearch() {		
		List<BusinessTransaction> res = this.getListOfBSCSModelValue(
				Constants.P_REFERENCEDTRANSACTIONS, BusinessTransaction.class);		
		return res;
	}

	/**
	 * 
	 * @return List<BusinessTransaction>
	 */
	public List<BusinessTransaction> getBusinessTransactionRead() {
		List<BusinessTransaction> result = this.getListOfBSCSModelValue(
				Constants.P_REFERENCEDBTRANS, BusinessTransaction.class);
		return result;
	}
	
	/**
	 * 
	 * @param BT_OHTAXAMT_GL
	 */
	public void setBtOhTaxAmt(Double ohtaxamt) {
		setDoubleValue(Constants.P_BT_OHTAXAMT_GL, ohtaxamt);
	}

	/**
	 * 
	 * @return BT_OHTAXAMT_GL
	 */
	public Double getBtOhTaxAmt() {
		return getMoneyAmountValue(Constants.P_BT_OHTAXAMT_GL);
	}
	

}
