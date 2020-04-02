package com.orange.bscs.model.usagedata;

import java.util.Date;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

/**
 * USAGE_DATA_RECORDS.READ { BILL_DATE = :
 * com.lhs.ccb.common.soiimpl.SVLDateTimeImpl BILL_IND = : java.lang.Integer
 * BOP_ALTERNATIVE_IND = : java.lang.Boolean CALLEDPARTY = : java.lang.String
 * CALLINGPARTY = : java.lang.String CALL_TYPE = : java.lang.Long CDR_ID = :
 * java.lang.Long CO_ID = : java.lang.Long CO_ID_PUB = : java.lang.String CS_ID
 * = : java.lang.Long CS_ID_PUB = : java.lang.String DEALER_ID = :
 * java.lang.Long DEALER_ID_PUB = : java.lang.String DEANONYM_ALLOWED = :
 * java.lang.Boolean DESTINATION = : java.lang.String DISCOUNTED = :
 * java.lang.Boolean ENTRY_FROM_DATE = :
 * com.lhs.ccb.common.soiimpl.SVLDateTimeImpl ENTRY_TO_DATE = :
 * com.lhs.ccb.common.soiimpl.SVLDateTimeImpl * SEARCH_LIMIT = :
 * java.lang.Integer SNCODE = : java.lang.Long SNCODE_PUB = : java.lang.String
 * START_FROM_DATE = : com.lhs.ccb.common.soiimpl.SVLDateTimeImpl START_TO_DATE
 * = : com.lhs.ccb.common.soiimpl.SVLDateTimeImpl } => { OUTPUT = sub element :
 * com.lhs.ccb.common.soiimpl.NamedValue ...
 *
 * @author IT&Labs
 * @version $Id: $
 */
public class BSCSUsageDataRecordRead extends BSCSModel {

    /**
     * The record identifier.
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getRecordIdentifier() {
        return getLongValue(Constants.P_CDR_ID);
    }

    /**
     * The record identifier.
     *
     * @param cdrId a {@link java.lang.Long} object.
     */
    public void setRecordIdentifier(Long cdrId) {
        setLongValue(Constants.P_CDR_ID, cdrId);
    }

    /**
     * <p>getCustomerId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getCustomerId() {
        return getLongValue(Constants.P_CS_ID);
    }

    /**
     * <p>setCustomerId.</p>
     *
     * @param csid a {@link java.lang.Long} object.
     */
    public void setCustomerId(Long csid) {
        setLongValue(Constants.P_CS_ID_PUB, csid);
    }

    /**
     * <p>getCustomerIdPub.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getCustomerIdPub() {
        return getStringValue(Constants.P_CS_ID_PUB);
    }

    /**
     * <p>setCustomerIdPub.</p>
     *
     * @param csidpub a {@link java.lang.String} object.
     */
    public void setCustomerIdPub(String csidpub) {
        setStringValue(Constants.P_CS_ID_PUB, csidpub);
    }

    /**
     * Calls made from the specified date
     *
     * @param startFromDate a {@link java.util.Date} object.
     */
    public void setStartFromDate(Date startFromDate) {
        setDateTimeValue(Constants.P_START_FROM_DATE, startFromDate);
    }

    /**
     * Limit the number of search results
     *
     * @param limit a int.
     */
    public void setSearchLimit(int limit) {
        setIntegerValue(Constants.P_SEARCH_LIMIT, limit);
    }
    /**
     * <p>getCoId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public String getCoIdPub() {
        return getStringValue(Constants.P_CO_ID_PUB);
    }

    /**
     * <p>setCoId.</p>
     *
     * @param coid a {@link java.lang.Long} object.
     */
    public void setCoIdPub(String coid) {
        setStringValue(Constants.P_CO_ID_PUB, coid);
    }
        
    /**
     * <p>getCallType.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getCallType() {
        return getLongValue(Constants.P_CALL_TYPE);
    }

    /**
     * <p>
     * setCallType.
     * </p>
     * 
     * @param callType
     *            a {@link java.lang.Long} object.
     */
    public void setCallType(Long callType) {
        setLongValue(Constants.P_CALL_TYPE, callType);
    }
	
}
