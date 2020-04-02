package com.orange.bscs.model.accounting;

import java.util.Date;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

/**
 *
 * <code>
 *   SEARCH_IS_COMPLETE   =  : java.lang.Boolean
 *   bookingrequests      = sub element : com.lhs.ccb.common.soiimpl.NamedValueCont
 * ainerList
 *  -[0]AMOUNT               =  : com.lhs.ccb.common.soiimpl.SVLMoneyImpl
 *  -[0]AMOUNT_REL           =  : java.lang.Float
 *  -[0]BASE_PART_ID         =  : java.lang.Integer
 *  -[0]BILLING_ACCOUNT_ID   =  : java.lang.Long
 *  -[0]CHARGE_PART_ID       =  : java.lang.Integer
 *  -[0]CO_ID                =  : java.lang.Long
 *  -[0]CO_ID_PUB            =  : java.lang.String
 *  -[0]CS_ID                =  : java.lang.Long
 *  -[0]CS_ID_PUB            =  : java.lang.String
 *  -[0]EVENT_CODE           =  : java.lang.Long
 *  -[0]FEE_CLASS            =  : java.lang.Integer
 *  -[0]FU_NUM               =  : java.lang.Float
 *  -[0]FU_PACK_ID           =  : java.lang.Integer
 *  -[0]FU_PKELSQ            =  : java.lang.Integer
 *  -[0]FU_PKVER             =  : java.lang.Integer
 *  -[0]FU_VER               =  : java.lang.Integer
 *  -[0]GLCODE               =  : java.lang.String
 *  -[0]IV_ID                =  : java.lang.Long
 *  -[0]JOBCOST              =  : java.lang.Long
 *  -[0]NUM_PERIODS          =  : java.lang.Integer
 *  -[0]RECORD_ID            =  : java.lang.Long
 *  -[0]RECORD_SUB_ID        =  : java.lang.Integer
 *  -[0]REMARK               =  : java.lang.String
 *  -[0]RPCODE               =  : java.lang.Long
 *  -[0]RP_VSCODE            =  : java.lang.Long
 *  -[0]SEQNO                =  : java.lang.Long
 *  -[0]SERV_CAT_CODE        =  : java.lang.String
 *  -[0]SERV_CODE            =  : java.lang.String
 *  -[0]SERV_TYPE            =  : java.lang.String
 *  -[0]SNCODE               =  : java.lang.Long
 *  -[0]SPCODE               =  : java.lang.Long
 *  -[0]TYPE                 =  : java.lang.Character
 *  -[0]VALID_FROM           =  : com.lhs.ccb.common.soiimpl.SVLDateImpl
 * }
 * </code>
 *
 * BOOKING_REQUEST.WRITE : Other Fields
 * for FEE_CLASS=2
 * <code>
 *    BILLING_ACCOUNT_CODE = : java.lang.String
 *    BILL_FORMAT = : java.lang.String
 *    CALL_DATE = : com.lhs.ccb.common.soiimpl.SVLDateImpl
 *    DEVICENUM = : java.lang.String
 *    DIR_NUM = : java.lang.String
 *  * FEE_TYPE = : java.lang.String
 *    FU_PACK_ID_PUB = : java.lang.String
 *    GLCODEDISC = : java.lang.String
 *    GLCODEMIN = : java.lang.String
 *    JOBCOSTDISC = : java.lang.Long
 *    JOBCOSTDISC_PUB = : java.lang.String
 *    JOBCOSTMIN = :  java.lang.Long
 *    JOBCOSTMIN_PUB = : java.lang.String
 *    JOBCOST_PUB = : java.lang.String
 *    RPCODE_PUB = : java.lang.String
 *    RERATE_SEQNO = : java.lang.Integer
 * </code>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public class BSCSBookingRequest extends BSCSModel {

    /**
     * <p>getContractId.</p>
     *
     * @return CO_ID : 99999 : java.lang.Long
     */
    public Long getContractId() {
        return getLongValue(Constants.P_CO_ID);
    }

    /**
     * <p>setContractId.</p>
     *
     * @param id a {@link java.lang.Long} object.
     */
    public void setContractId(Long id) {
        setLongValue(Constants.P_CO_ID, id);
    }

    /**
     * CO_ID_PUB = CONTR0000000999 : java.lang.String
     *
     * @return a {@link java.lang.String} object.
     */
    public String getContractIdPub() {
        return getStringValue(Constants.P_CO_ID_PUB);
    }

    /**
     * <p>setContractIdPub.</p>
     *
     * @param idpub a {@link java.lang.String} object.
     */
    public void setContractIdPub(String idpub) {
        setStringValue(Constants.P_CO_ID_PUB, idpub);
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
        setLongValue(Constants.P_CS_ID, csid);
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
     * <p>getBillingAccountId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getBillingAccountId() {
        return getLongValue(Constants.P_BILLING_ACCOUNT_ID);
    }

    /**
     * <p>setBillingAccountId.</p>
     *
     * @param baid a {@link java.lang.Long} object.
     */
    public void setBillingAccountId(Long baid) {
        setLongValue(Constants.P_BILLING_ACCOUNT_ID, baid);
    }

    /**
     * bookingRequest.Write
     *
     * @param billingAccountCode a {@link java.lang.String} object.
     */
    public void setBillingAccountCode(String billingAccountCode) {
        setStringValue(Constants.P_BILLING_ACCOUNT_CODE, billingAccountCode);
    }

    /**
     * <p>
     * getFeeType.
     * </p>
     * Used on BOOKING_REQUESTS.SEARCH result
     *
     * @return Type of fee: Recurring or Non-recurring.
     */
    public EnumBookingFeeType getFeeType() {
        return EnumBookingFeeType.fromString(getStringValue(Constants.P_TYPE));
    }

    /**
     * <p>
     * setFeeType.
     * </p>
     * Used for BOOKING_REQUEST.WRITE request
     *
     * @param type
     *            a {@link com.orange.bscs.model.accounting.EnumBookingFeeType}
     *            object.
     */
    public void setFeeType(EnumBookingFeeType type) {
        setStringValue(Constants.P_FEE_TYPE, EnumBookingFeeType.toString(type));
    }

    /**
     * <p>getValidFrom.</p>
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getValidFrom() {
        return getDateValue(Constants.P_VALID_FROM);
    }

    /**
     * <p>setValidFrom.</p>
     *
     * @param validfrom a {@link java.util.Date} object.
     */
    public void setValidFrom(Date validfrom) {
        setDateValue(Constants.P_VALID_FROM, validfrom);
    }

    /**
     * Search Only
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getStartValidFrom() {
        return getDateValue(Constants.P_START_VALID_FROM);
    }

    /**
     * Search Only
     *
     * @param validfrom a {@link java.util.Date} object.
     */
    public void setStartValidFrom(Date validfrom) {
        setDateValue(Constants.P_START_VALID_FROM, validfrom);
    }

    /**
     * Search Only
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getEndValidFrom() {
        return getDateValue(Constants.P_END_VALID_FROM);
    }

    /**
     * Search Only
     *
     * @param validfrom a {@link java.util.Date} object.
     */
    public void setEndValidFrom(Date validfrom) {
        setDateValue(Constants.P_END_VALID_FROM, validfrom);
    }

    /**
     * order : 'a' : ascending, older first, 'd' : descending, youngest first
     * <p>
     * Search Only
     * </p>
     *
     * @return a {@link java.lang.Character} object.
     */
    public Character getOrderValidFrom() {
        return getCharacterValue(Constants.P_ORDR_VALID_FROM);
    }

    /**
     * order : 'a' : ascending, older first, 'd' : descending, youngest first
     * <p>
     * Search Only
     * </p>
     *
     * @param order a {@link java.lang.Character} object.
     */
    public void setOrderValidFrom(Character order) {
        setCharacterValue(Constants.P_ORDR_VALID_FROM, order);
    }

    /**
     * Search Only
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getSearchCount() {
        return getIntegerValue(Constants.P_SRCH_COUNT);
    }

    /**
     * Search Only
     *
     * @param value a {@link java.lang.Integer} object.
     */
    public void setSearchCount(Integer value) {
        setIntegerValue(Constants.P_SRCH_COUNT, value);
    }

    /**
     * <p>getUDRBasePartId.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getUDRBasePartId() {
        return getIntegerValue(Constants.P_BASE_PART_ID);
    }

    /**
     * <p>setUDRBasePartId.</p>
     *
     * @param basePartId a {@link java.lang.Integer} object.
     */
    public void setUDRBasePartId(Integer basePartId) {
        setIntegerValue(Constants.P_BASE_PART_ID, basePartId);
    }

    /**
     * <p>getUDRChargePartId.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getUDRChargePartId() {
        return getIntegerValue(Constants.P_CHARGE_PART_ID);
    }

    /**
     * <p>setUDRChargePartId.</p>
     *
     * @param chargePartId a {@link java.lang.Integer} object.
     */
    public void setUDRChargePartId(Integer chargePartId) {
        setIntegerValue(Constants.P_CHARGE_PART_ID, chargePartId);
    }

    /**
     * <p>getInvoiceId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getInvoiceId() {
        return getLongValue(Constants.P_IV_ID);
    }

    /**
     * <p>setInvoiceId.</p>
     *
     * @param ivId a {@link java.lang.Long} object.
     */
    public void setInvoiceId(Long ivId) {
        setLongValue(Constants.P_IV_ID, ivId);
    }

    // WARN : IXR3 - RecordID = Integer
    /**
     * <p>getCallDataRecordId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getCallDataRecordId() {
        return getLongValue(Constants.P_RECORD_ID);
    }

    /**
     * <p>setCallDataRecordId.</p>
     *
     * @param recordId a {@link java.lang.Long} object.
     */
    public void setCallDataRecordId(Long recordId) {
        setLongValue(Constants.P_RECORD_ID, recordId);
    }

    /**
     * <p>getCallDataRecordSubId.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getCallDataRecordSubId() {
        return getIntegerValue(Constants.P_RECORD_SUB_ID);
    }

    /**
     * <p>setCallDataRecordSubId.</p>
     *
     * @param recordSubId a {@link java.lang.Integer} object.
     */
    public void setCallDataRecordSubId(Integer recordSubId) {
        setIntegerValue(Constants.P_RECORD_SUB_ID, recordSubId);
    }

    /**
     * <p>getCallDataRecordReRatingSeq.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getCallDataRecordReRatingSeq() {
        return getIntegerValue(Constants.P_RERATE_SEQNO);
    }

    /**
     * <p>setCallDataRecordReRatingSeq.</p>
     *
     * @param reRateSeqNo a int.
     */
    public void setCallDataRecordReRatingSeq(int reRateSeqNo) {
        setIntegerValue(Constants.P_RERATE_SEQNO, reRateSeqNo);
    }

    /**
     * <p>getCallDate.</p>
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getCallDate() {
        return getDateTimeValue(Constants.P_CALL_DATE);
    }

    /**
     * <p>setCallDate.</p>
     *
     * @param callDate a {@link java.util.Date} object.
     */
    public void setCallDate(Date callDate) {
        setDateTimeValue(Constants.P_CALL_DATE, callDate);
    }

    /**
     * Directory number of the Calling (served) party, used for the remark of a
     * fee with FEE_CLASS=2 only.
     *
     * @return a {@link java.lang.String} object.
     */
    public String getCallDirnum() {
        return getStringValue(Constants.P_DIRNUM);
    }

    /**
     * Directory number of the Calling (served) party, used for the remark of a
     * fee with FEE_CLASS=2 only.
     *
     * @param dirnum a {@link java.lang.String} object.
     */
    public void setCallDirnum(String dirnum) {
        setStringValue(Constants.P_DIRNUM, dirnum);
    }

    /**
     * Contract device (Storage medium / port) number, used for the remark of a
     * fee with FEE_CLASS=2 only.
     *
     * @return a {@link java.lang.String} object.
     */
    public String getCallDevicenum() {
        return getStringValue(Constants.P_DEVICENUM);
    }

    /**
     * Contract device (Storage medium / port) number, used for the remark of a
     * fee with FEE_CLASS=2 only.
     *
     * @param devicenum a {@link java.lang.String} object.
     */
    public void setCallDevicenum(String devicenum) {
        setStringValue(Constants.P_DEVICENUM, devicenum);
    }

    /**
     * Event Code (Booking_request.write, class=2)
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getEventCode() {
        return getLongValue(Constants.P_EVENT_CODE);
    }

    /**
     * Event Code (Booking_request.write, class=2)
     *
     * @param eventcode a {@link java.lang.Long} object.
     */
    public void setEventCode(Long eventcode) {
        setLongValue(Constants.P_EVENT_CODE, eventcode);
    }

    /**
     * <p>getRatePlanCode.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getRatePlanCode() {
        return getLongValue(Constants.P_RPCODE);
    }

    /**
     * <p>setRatePlanCode.</p>
     *
     * @param rpCode a {@link java.lang.Long} object.
     */
    public void setRatePlanCode(Long rpCode) {
        setLongValue(Constants.P_RPCODE, rpCode);
    }

    /**
     * <p>getRatePlanVersionCode.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getRatePlanVersionCode() {
        return getLongValue(Constants.P_RP_VSCODE);
    }

    /**
     * <p>setRatePlanVersionCode.</p>
     *
     * @param rpCode a {@link java.lang.Long} object.
     */
    public void setRatePlanVersionCode(Long rpCode) {
        setLongValue(Constants.P_RP_VSCODE, rpCode);
    }

    /**
     * <p>getServicePackageCode.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getServicePackageCode() {
        return getLongValue(Constants.P_SPCODE);
    }

    /**
     * <p>setServicePackageCode.</p>
     *
     * @param spcode a {@link java.lang.Long} object.
     */
    public void setServicePackageCode(Long spcode) {
        setLongValue(Constants.P_SPCODE, spcode);
    }

    /**
     * <p>getServiceCode.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getServiceCode() {
        return getLongValue(Constants.P_SNCODE);
    }

    /**
     * <p>setServiceCode.</p>
     *
     * @param sncode a {@link java.lang.Long} object.
     */
    public void setServiceCode(Long sncode) {
        setLongValue(Constants.P_SNCODE, sncode);
    }

    /**
     * <p>getSequenceNumber.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getSequenceNumber() {
        return getLongValue(Constants.P_SEQNO);
    }

    /**
     * <p>setSequenceNumber.</p>
     *
     * @param seqno a {@link java.lang.Long} object.
     */
    public void setSequenceNumber(Long seqno) {
        setLongValue(Constants.P_SEQNO, seqno);
    }

    /**
     * <p>getFeeClass.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getFeeClass() {
        return getIntegerValue(Constants.P_FEE_CLASS);
    }

    /**
     * <p>setFeeClass.</p>
     *
     * @param feeclass a {@link java.lang.Integer} object.
     */
    public void setFeeClass(Integer feeclass) {
        setIntegerValue(Constants.P_FEE_CLASS, feeclass);
    }

    /**
     * <p>getAmount.</p>
     *
     * @return a {@link java.lang.Double} object.
     */
    public Double getAmount() {
        return getMoneyAmountValue(Constants.P_AMOUNT);
    }

    /**
     * <p>setAmount.</p>
     *
     * @param amount a {@link java.lang.Double} object.
     */
    public void setAmount(Double amount) {
        setMoneyValue(Constants.P_AMOUNT, amount);
    }

    /**
     * <p>getAmountCurrencyCode.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getAmountCurrencyCode() {
        return getMoneyCurrencyCodeValue(Constants.P_AMOUNT);
    }

    /**
     * <p>getAmountRel.</p>
     *
     * @return a {@link java.lang.Double} object.
     */
    public Double getAmountRel() {
        return getDoubleValue(Constants.P_AMOUNT_REL);
    }

    /**
     * <p>setAmountRel.</p>
     *
     * @param amountrel a {@link java.lang.Double} object.
     */
    public void setAmountRel(Double amountrel) {
        setDoubleValue(Constants.P_AMOUNT_REL, amountrel);
    }

    /**
     * Number of free units
     *
     * @return a {@link java.lang.Double} object.
     */
    public Double getFUNumber() {
        return getDoubleValue(Constants.P_FU_NUM);
    }

    /**
     * Number of free units
     *
     * @param number a {@link java.lang.Double} object.
     */
    public void setFUNumber(Double number) {
        setDoubleValue(Constants.P_FU_NUM, number);
    }

    /**
     * <p>getFUPackageId.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getFUPackageId() {
        return getIntegerValue(Constants.P_FU_PACK_ID);
    }

    /**
     * <p>setFUPackageId.</p>
     *
     * @param packId a {@link java.lang.Integer} object.
     */
    public void setFUPackageId(Integer packId) {
        setIntegerValue(Constants.P_FU_PACK_ID, packId);
    }

    /**
     * <p>getFUPackageVersion.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getFUPackageVersion() {
        return getIntegerValue(Constants.P_FU_PKVER);
    }

    /**
     * <p>setFUPackageVersion.</p>
     *
     * @param version a {@link java.lang.Integer} object.
     */
    public void setFUPackageVersion(Integer version) {
        setIntegerValue(Constants.P_FU_PKVER, version);
    }

    /**
     * <p>getFUPackageEltSequence.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getFUPackageEltSequence() {
        return getIntegerValue(Constants.P_FU_PKELSQ);
    }

    /**
     * <p>setFUPackageEltSequence.</p>
     *
     * @param elementSequence a {@link java.lang.Integer} object.
     */
    public void setFUPackageEltSequence(Integer elementSequence) {
        setIntegerValue(Constants.P_FU_PKELSQ, elementSequence);
    }

    /**
     * <p>getFUPackageEltVersion.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getFUPackageEltVersion() {
        return getIntegerValue(Constants.P_FU_VER);
    }

    /**
     * <p>setFUPackageEltVersion.</p>
     *
     * @param elementVersion a {@link java.lang.Integer} object.
     */
    public void setFUPackageEltVersion(Integer elementVersion) {
        setIntegerValue(Constants.P_FU_VER, elementVersion);
    }

    /**
     * <p>getActionCode.</p>
     *
     * @return a {@link com.orange.bscs.model.accounting.EnumBookingActionCode} object.
     */
    public EnumBookingActionCode getActionCode() {
        return EnumBookingActionCode.fromCaracter(getCharacterValue(Constants.P_ACTION_CODE));
    }

    /**
     * <p>setActionCode.</p>
     *
     * @param action a {@link com.orange.bscs.model.accounting.EnumBookingActionCode} object.
     */
    public void setActionCode(EnumBookingActionCode action) {
        setCharacterValue(Constants.P_ACTION_CODE, EnumBookingActionCode.toCharacter(action));
    }

    /**
     * <p>getRemark.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getRemark() {
        return getStringValue(Constants.P_REMARK);
    }

    /**
     * <p>setRemark.</p>
     *
     * @param remark a {@link java.lang.String} object.
     */
    public void setRemark(String remark) {
        setStringValue(Constants.P_REMARK, remark);
    }

    /**
     * <p>getGLCode.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getGLCode() {
        return getStringValue(Constants.P_GLCODE);
    }

    /**
     * <p>setGLCode.</p>
     *
     * @param glcode a {@link java.lang.String} object.
     */
    public void setGLCode(String glcode) {
        setStringValue(Constants.P_GLCODE, glcode);
    }

    /**
     * <p>getNumPeriods.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getNumPeriods() {
        return getIntegerValue(Constants.P_NUM_PERIODS);
    }

    /**
     * <p>setNumPeriods.</p>
     *
     * @param num a {@link java.lang.Integer} object.
     */
    public void setNumPeriods(Integer num) {
        setIntegerValue(Constants.P_NUM_PERIODS, num);
    }

    public void setServiceCodePub(String snCodePub) {
        setStringValue(Constants.P_SNCODE_PUB, snCodePub);
    }

    public void setServicePackageCodePub(String spCodePub) {
        setStringValue(Constants.P_SPCODE_PUB, spCodePub);
    }

}
