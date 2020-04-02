package com.orange.bscs.model.businesspartner;

import java.util.List;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

/**
 * <pre>{@code
 * CUSTOMER.WRITE {
 *   AREA_CODE            =  : java.lang.Long
 *   BC_COMMENT           =  : java.lang.String
 *   COST_CODE_PUB        =  : java.lang.String
 *   COST_ID              =  : java.lang.Long
 *   CS_BILLCYCLE         =  : java.lang.String
 *   CS_BILL_INFORMATION  =  : java.lang.Boolean
 *   CS_CLIMIT            =  : com.lhs.ccb.common.soiimpl.SVLMoneyImpl
 *   CS_COLLECTOR         =  : java.lang.String
 *   CS_CONVRATETYPE_PAYMENT =  : java.lang.Long
 *   CS_CONVRATETYPE_PAYMENT_PUB =  : java.lang.String
 *   CS_CONVRATETYPE_REFUND =  : java.lang.Long
 *   CS_CONVRATETYPE_REFUND_PUB =  : java.lang.String
 *   CS_CRCHECK_AGREED    =  : java.lang.Boolean
 *   CS_CSCREDIT_REMARK   =  : java.lang.String
 *   CS_DEALERID          =  : java.lang.Long
 *   CS_DEALERID_PUB      =  : java.lang.String
 *   CS_DEPOSIT           =  : com.lhs.ccb.common.soiimpl.SVLMoneyImpl
 *   CS_DUNNING           =  : java.lang.Boolean
 *   CS_ID                =  : java.lang.Long
 *   CS_ID_PUB            =  : java.lang.String
 *   CS_INCORPORATED_IND  =  : java.lang.Boolean
 *   CS_JURISDICTION_CODE =  : java.lang.String
 *   CS_JURISDICTION_ID   =  : java.lang.Long
 *   CS_LIMIT_TR1         =  : java.lang.Long
 *   CS_LIMIT_TR1_PUB     =  : java.lang.String
 *   CS_LIMIT_TR2         =  : java.lang.Long
 *   CS_LIMIT_TR2_PUB     =  : java.lang.String
 *   CS_LIMIT_TR3         =  : java.lang.Long
 *   CS_LIMIT_TR3_PUB     =  : java.lang.String
 *   CS_PASSWORD          =  : java.lang.String
 *   CS_PREPAYMENT        =  : java.lang.Boolean
 *   CS_REMARK1           =  : java.lang.String
 *   CS_REMARK2           =  : java.lang.String
 *   CS_STATUS            =  : java.lang.Character
 *   CUSTCAT_CODE         =  : java.lang.Long
 *   CUSTCAT_CODE_PUB     =  : java.lang.String
 *   EXPECT_PAY_CURRENCY_ID =  : java.lang.Long
 *   EXPECT_PAY_CURRENCY_ID_PUB =  : java.lang.String
 *   EXTERNAL_CUSTOMER_ID =  : java.lang.String
 *   EXTERNAL_CUSTOMER_SET_ID =  : java.lang.String
 *   FAMILY_ID            =  : java.lang.Long
 *   IS_INDIVIDUAL_OVERDISC_DISABLED =  : java.lang.Boolean
 *   PARTY_ROLE_ASSIGNMENTS = sub element : com.lhs.ccb.common.soiimpl.NamedValueCo
 * ntainerList
 *  -[0]PARTY_ROLE_ID        =  : java.lang.Long
 *  -[0]PARTY_ROLE_SHNAME    =  : java.lang.String
 *   PAYMENT_RESP         =  : java.lang.Boolean
 *   PRG_CODE             =  : java.lang.String
 *   REFUND_CURRENCY_ID   =  : java.lang.Long
 *   REFUND_CURRENCY_ID_PUB =  : java.lang.String
 *   RPCODE               =  : java.lang.Long
 *   RPCODE_PUB           =  : java.lang.String
 *   RS_CODE              =  : java.lang.Long
 *   SR_CODE              =  : java.lang.Long
 *   TB_AMOUNT            =  : java.lang.Float
 *   TB_CURRENCY_ID       =  : java.lang.Long
 *   TB_CURRENCY_ID_PUB   =  : java.lang.String
 *   TB_MODE              =  : java.lang.String
 *   TB_PURPOSE           =  : java.lang.String
 *   TRADE_CODE           =  : java.lang.String
 *   WConstants.P_CODE              =  : java.lang.Long
 * }
 * => {
 * }
 * }</pre>
 * @author IT&Labs
 * @version $Id: $
 */
public class BSCSCustomerWriteInput extends BSCSModel {

    // AREA_CODE
    /**
     * <p>getAreaCode.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getAreaCode() {
        return getLongValue(Constants.P_AREA_CODE);
    }

    /**
     * <p>setAreaCode.</p>
     *
     * @param value a {@link java.lang.Long} object.
     */
    public void setAreaCode(Long value) {
        setLongValue(Constants.P_AREA_CODE, value);
    }

    // BC_COMMENT
    /**
     * <p>(BC_COMMENT).</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getBillingCycleComment() {
        return getStringValue("BC_COMMENT");
    }

    /**
     * <p>setBcComment.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setBillingCycleComment(String value) {
        setStringValue("BC_COMMENT", value);
    }
    
    
    // PARTY_TYPE

    
    public String getPartyType() {
        return getStringValue(Constants.P_PARTY_TYPE);
    }

    /**
     * <p>setPartyType.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setPartyType(String value) {
        setStringValue(Constants.P_PARTY_TYPE, value);
    }
    

    // COST_CODE_PUB
    /**
     * <p>getCostCenterCode.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getCostCenterCode() {
        return getStringValue(Constants.P_COST_CODE_PUB);
    }

    /**
     * <p>setCostCenterCode.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setCostCenterCode(String value) {
        setStringValue(Constants.P_COST_CODE_PUB, value);
    }

    // COST_ID
    /**
     * <p>getCostCenterId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getCostCenterId() {
        return getLongValue(Constants.P_COST_ID);
    }

    /**
     * <p>setCostCenterId.</p>
     *
     * @param value a {@link java.lang.Long} object.
     */
    public void setCostCenterId(Long value) {
        setLongValue(Constants.P_COST_ID, value);
    }

    // CS_BILL_INFORMATION
    /**
     * <p>getBillingInformation.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getBillingInformation() {
        return getBooleanValue(Constants.P_CS_BILL_INFORMATION);
    }

    /**
     * <p>setBillingInformation.</p>
     *
     * @param value a {@link java.lang.Boolean} object.
     */
    public void setBillingInformation(Boolean value) {
        setBooleanValue(Constants.P_CS_BILL_INFORMATION, value);
    }

    // CS_BILLCYCLE
    /**
     * <p>getBillingCycle.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getBillingCycle() {
        return getStringValue(Constants.P_CS_BILLCYCLE);
    }

    /**
     * <p>setBillingCycle.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setBillingCycle(String value) {
        setStringValue(Constants.P_CS_BILLCYCLE, value);
    }

    // CS_CLIMIT
    /**
     * <p>getCreditLimit.</p>
     *
     * @return a {@link java.lang.Double} object.
     */
    public Double getCreditLimit() {
        return getDoubleValue(Constants.P_CS_CLIMIT);
    }

    /**
     * <p>setCreditLimit.</p>
     *
     * @param value a {@link java.lang.Double} object.
     */
    public void setCreditLimit(Double value) {
        setDoubleValue(Constants.P_CS_CLIMIT, value);
    }

    // CS_COLLECTOR
    /**
     * <p>getCashCollector.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getCashCollector() {
        return getStringValue(Constants.P_CS_COLLECTOR);
    }

    /**
     * <p>setCashCollector.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setCashCollector(String value) {
        setStringValue(Constants.P_CS_COLLECTOR, value);
    }

    // CS_CONVRATETYPE_PAYMENT
    /**
     * <p>getConvRateTypePayment.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getConvRateTypePayment() {
        return getLongValue(Constants.P_CS_CONVRATETYPE_PAYMENT);
    }

    /**
     * <p>setConvRateTypePayment.</p>
     *
     * @param value a {@link java.lang.Long} object.
     */
    public void setConvRateTypePayment(Long value) {
        setLongValue(Constants.P_CS_CONVRATETYPE_PAYMENT, value);
    }

    // CS_CONVRATETYPE_PAYMENT_PUB
    /**
     * <p>getConvRateTypePaymentPub.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getConvRateTypePaymentPub() {
        return getStringValue(Constants.P_CS_CONVRATETYPE_PAYMENT_PUB);
    }

    /**
     * <p>setConvRateTypePaymentPub.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setConvRateTypePaymentPub(String value) {
        setStringValue(Constants.P_CS_CONVRATETYPE_PAYMENT_PUB, value);
    }

    // CS_CONVRATETYPE_REFUND
    /**
     * <p>getConvRateTypeRefund.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getConvRateTypeRefund() {
        return getLongValue(Constants.P_CS_CONVRATETYPE_REFUND);
    }

    /**
     * <p>setConvRateTypeRefund.</p>
     *
     * @param value a {@link java.lang.Long} object.
     */
    public void setConvRateTypeRefund(Long value) {
        setLongValue(Constants.P_CS_CONVRATETYPE_REFUND, value);
    }

    // CS_CONVRATETYPE_REFUND_PUB
    /**
     * <p>getConvRateTypeRefundPub.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getConvRateTypeRefundPub() {
        return getStringValue(Constants.P_CS_CONVRATETYPE_REFUND_PUB);
    }

    /**
     * <p>setConvRateTypeRefundPub.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setConvRateTypeRefundPub(String value) {
        setStringValue(Constants.P_CS_CONVRATETYPE_REFUND_PUB, value);
    }

    // CS_CRCHECK_AGREED
    /**
     * <p>getCrCheckAgreed.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getCrCheckAgreed() {
        return getBooleanValue(Constants.P_CS_CRCHECK_AGREED);
    }

    /**
     * <p>setCrCheckAgreed.</p>
     *
     * @param value a {@link java.lang.Boolean} object.
     */
    public void setCrCheckAgreed(Boolean value) {
        setBooleanValue(Constants.P_CS_CRCHECK_AGREED, value);
    }

    // CS_CSCREDIT_REMARK
    /**
     * <p>getCsCreditRemark.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getCsCreditRemark() {
        return getStringValue(Constants.P_CS_CSCREDIT_REMARK);
    }

    /**
     * <p>setCsCreditRemark.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setCsCreditRemark(String value) {
        setStringValue(Constants.P_CS_CSCREDIT_REMARK, value);
    }

    // CS_DEALERID
    /**
     * <p>getDealerId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getDealerId() {
        return getLongValue(Constants.P_CS_DEALERID);
    }

    /**
     * <p>setDealerId.</p>
     *
     * @param value a {@link java.lang.Long} object.
     */
    public void setDealerId(Long value) {
        setLongValue(Constants.P_CS_DEALERID, value);
    }

    // CS_DEALERID_PUB
    /**
     * <p>getDealerIdPub.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getDealerIdPub() {
        return getStringValue(Constants.P_CS_DEALERID_PUB);
    }

    /**
     * <p>setDealerIdPub.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setDealerIdPub(String value) {
        setStringValue(Constants.P_CS_DEALERID_PUB, value);
    }

    // CS_DEPOSIT
    /**
     * <p>getDeposit.</p>
     *
     * @return a {@link java.lang.Double} object.
     */
    public Double getDeposit() {
        return getDoubleValue(Constants.P_CS_DEPOSIT);
    }

    /**
     * <p>setDeposit.</p>
     *
     * @param value a {@link java.lang.Double} object.
     */
    public void setDeposit(Double value) {
        setDoubleValue(Constants.P_CS_DEPOSIT, value);
    }

    // CS_DUNNING
    /**
     * <p>getDunning.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getDunning() {
        return getBooleanValue(Constants.P_CS_DUNNING);
    }

    /**
     * <p>setDunning.</p>
     *
     * @param value a {@link java.lang.Boolean} object.
     */
    public void setDunning(Boolean value) {
        setBooleanValue(Constants.P_CS_DUNNING, value);
    }

    // CS_ID
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
     * @param value a {@link java.lang.Long} object.
     */
    public void setCustomerId(Long value) {
        setLongValue(Constants.P_CS_ID, value);
    }

    // CS_ID_PUB
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
     * @param value a {@link java.lang.String} object.
     */
    public void setCustomerIdPub(String value) {
        setStringValue(Constants.P_CS_ID_PUB, value);
    }

    // CS_INCORPORATED_IND
    /**
     * <p>getIncorporatedInd.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getIncorporatedInd() {
        return getBooleanValue(Constants.P_CS_INCORPORATED_IND);
    }

    /**
     * <p>setIncorporatedInd.</p>
     *
     * @param value a {@link java.lang.Boolean} object.
     */
    public void setIncorporatedInd(Boolean value) {
        setBooleanValue(Constants.P_CS_INCORPORATED_IND, value);
    }

    // CS_JURISDICTION_CODE
    /**
     * <p>getJuridictionCode.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getJuridictionCode() {
        return getStringValue(Constants.P_CS_JURISDICTION_CODE);
    }

    /**
     * <p>setJuridictionCode.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setJuridictionCode(String value) {
        setStringValue(Constants.P_CS_JURISDICTION_CODE, value);
    }

    // CS_JURISDICTION_ID
    /**
     * <p>getJuridictionId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getJuridictionId() {
        return getLongValue(Constants.P_CS_JURISDICTION_ID);
    }

    /**
     * <p>setJuridictionId.</p>
     *
     * @param value a {@link java.lang.Long} object.
     */
    public void setJuridictionId(Long value) {
        setLongValue(Constants.P_CS_JURISDICTION_ID, value);
    }

    // CS_LIMIT_TR1
    /**
     * <p>getOpenAmountThreshold1.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getOpenAmountThreshold1() {
        return getLongValue(Constants.P_CS_LIMIT_TR1);
    }

    /**
     * <p>setOpenAmountThreshold1.</p>
     *
     * @param value a {@link java.lang.Long} object.
     */
    public void setOpenAmountThreshold1(Long value) {
        setLongValue(Constants.P_CS_LIMIT_TR1, value);
    }

    // CS_LIMIT_TR1_PUB
    /**
     * <p>getOpenAmountThreshold1Code.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getOpenAmountThreshold1Code() {
        return getStringValue(Constants.P_CS_LIMIT_TR1_PUB);
    }

    /**
     * <p>setOpenAmountThreshold1Code.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setOpenAmountThreshold1Code(String value) {
        setStringValue(Constants.P_CS_LIMIT_TR1_PUB, value);
    }

    // CS_LIMIT_TR2
    /**
     * <p>getOpenAmountThreshold2.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getOpenAmountThreshold2() {
        return getLongValue(Constants.P_CS_LIMIT_TR2);
    }

    /**
     * <p>setOpenAmountThreshold2.</p>
     *
     * @param value a {@link java.lang.Long} object.
     */
    public void setOpenAmountThreshold2(Long value) {
        setLongValue(Constants.P_CS_LIMIT_TR2, value);
    }

    // CS_LIMIT_TR2_PUB
    /**
     * <p>getOpenAmountThreshold2Code.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getOpenAmountThreshold2Code() {
        return getStringValue(Constants.P_CS_LIMIT_TR2_PUB);
    }

    /**
     * <p>setOpenAmountThreshold2Code.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setOpenAmountThreshold2Code(String value) {
        setStringValue(Constants.P_CS_LIMIT_TR2_PUB, value);
    }

    // CS_LIMIT_TR3
    /**
     * <p>getOpenAmountThreshold3.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getOpenAmountThreshold3() {
        return getLongValue(Constants.P_CS_LIMIT_TR3);
    }

    /**
     * <p>setOpenAmountThreshold3.</p>
     *
     * @param value a {@link java.lang.Long} object.
     */
    public void setOpenAmountThreshold3(Long value) {
        setLongValue(Constants.P_CS_LIMIT_TR3, value);
    }

    // CS_LIMIT_TR3_PUB
    /**
     * <p>getOpenAmountThreshold3Code.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getOpenAmountThreshold3Code() {
        return getStringValue(Constants.P_CS_LIMIT_TR3_PUB);
    }

    /**
     * <p>setOpenAmountThreshold3Code.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setOpenAmountThreshold3Code(String value) {
        setStringValue(Constants.P_CS_LIMIT_TR3_PUB, value);
    }

    // CS_PASSWORD
    /**
     * <p>getPassword.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getPassword() {
        return getStringValue(Constants.P_CS_PASSWORD);
    }

    /**
     * <p>setPassword.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setPassword(String value) {
        setStringValue(Constants.P_CS_PASSWORD, value);
    }

    // CS_PREPAYMENT
    /**
     * <p>getIsPrepayment.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getIsPrepayment() {
        return getBooleanValue(Constants.P_CS_PREPAYMENT);
    }

    /**
     * <p>setIsPrepayment.</p>
     *
     * @param value a {@link java.lang.Boolean} object.
     */
    public void setIsPrepayment(Boolean value) {
        setBooleanValue(Constants.P_CS_PREPAYMENT, value);
    }

    // CS_REMARK1
    /**
     * <p>getRemark1.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getRemark1() {
        return getStringValue(Constants.P_CS_REMARK1);
    }

    /**
     * <p>setRemark1.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setRemark1(String value) {
        setStringValue(Constants.P_CS_REMARK1, value);
    }

    // CS_REMARK2
    /**
     * <p>getRemark2.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getRemark2() {
        return getStringValue(Constants.P_CS_REMARK2);
    }

    /**
     * <p>setRemark2.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setRemark2(String value) {
        setStringValue(Constants.P_CS_REMARK2, value);
    }

    // CS_STATUS
    /**
     * <p>getStatusAsCharacter.</p>
     *
     * @return a {@link java.lang.Character} object.
     */
    public Character getStatusAsCharacter() {
        return getCharacterValue(Constants.P_CS_STATUS);
    }

    /**
     * <p>setStatusAsCharacter.</p>
     *
     * @param value a {@link java.lang.Character} object.
     */
    public void setStatusAsCharacter(Character value) {
        setCharacterValue(Constants.P_CS_STATUS, value);
    }

    /**
     * <p>getStatus.</p>
     *
     * @return a {@link com.orange.bscs.model.businesspartner.EnumCustomerStatus} object.
     */
    public EnumCustomerStatus getStatus() {
        return EnumCustomerStatus.parse(getCharacterValue(Constants.P_CS_STATUS));
    }

    /**
     * <p>setStatus.</p>
     *
     * @param status a {@link com.orange.bscs.model.businesspartner.EnumCustomerStatus} object.
     */
    public void setStatus(EnumCustomerStatus status) {
        setCharacterValue(Constants.P_CS_STATUS, (null == status ? null : status.getCharacter()));
    }

    // CUSTCAT_CODE
    /**
     * <p>getCustomerCategory.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getCustomerCategory() {
        return getLongValue(Constants.P_CUSTCAT_CODE);
    }

    /**
     * <p>setCustomerCategory.</p>
     *
     * @param value a {@link java.lang.Long} object.
     */
    public void setCustomerCategory(Long value) {
        setLongValue(Constants.P_CUSTCAT_CODE, value);
    }

    // CUSTCAT_CODE_PUB
    /**
     * <p>getCustomerCategoryCode.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getCustomerCategoryCode() {
        return getStringValue(Constants.P_CUSTCAT_CODE_PUB);
    }

    /**
     * <p>setCustomerCategoryCode.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setCustomerCategoryCode(String value) {
        setStringValue(Constants.P_CUSTCAT_CODE_PUB, value);
    }

    // EXPECT_PAY_CURRENCY_ID
    /**
     * <p>getExpectPayCurrencyId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getExpectPayCurrencyId() {
        return getLongValue("EXPECT_PAY_CURRENCY_ID");
    }

    /**
     * <p>setExpectPayCurrencyId.</p>
     *
     * @param value a {@link java.lang.Long} object.
     */
    public void setExpectPayCurrencyId(Long value) {
        setLongValue("EXPECT_PAY_CURRENCY_ID", value);
    }

    // EXPECT_PAY_CURRENCY_ID_PUB
    /**
     * <p>getExpectPayCurrencyIdPub.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getExpectPayCurrencyIdPub() {
        return getStringValue("EXPECT_PAY_CURRENCY_ID_PUB");
    }

    /**
     * <p>setExpectPayCurrencyIdPub.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setExpectPayCurrencyIdPub(String value) {
        setStringValue("EXPECT_PAY_CURRENCY_ID_PUB", value);
    }

    // EXTERNAL_CUSTOMER_ID
    /**
     * <p>getExternalCustomerId.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getExternalCustomerId() {
        return getStringValue(Constants.P_EXTERNAL_CUSTOMER_ID);
    }

    /**
     * <p>setExternalCustomerId.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setExternalCustomerId(String value) {
        setStringValue(Constants.P_EXTERNAL_CUSTOMER_ID, value);
    }

    // EXTERNAL_CUSTOMER_SET_ID
    /**
     * <p>getExternalCustomerIdSet.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getExternalCustomerIdSet() {
        return getStringValue(Constants.P_EXTERNAL_CUSTOMER_SET_ID);
    }

    /**
     * <p>setExternalCustomerIdSet.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setExternalCustomerIdSet(String value) {
        setStringValue(Constants.P_EXTERNAL_CUSTOMER_SET_ID, value);
    }

    // FAMILY_ID
    /**
     * <p>getFamilyId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getFamilyId() {
        return getLongValue(Constants.P_FAMILY_ID);
    }

    /**
     * <p>setFamilyId.</p>
     *
     * @param value a {@link java.lang.Long} object.
     */
    public void setFamilyId(Long value) {
        setLongValue(Constants.P_FAMILY_ID, value);
    }

    // IS_INDIVIDUAL_OVERDISC_DISABLED
    /**
     * <p>getIsIndividualOverdiscDisabled.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getIsIndividualOverdiscDisabled() {
        return getBooleanValue("IS_INDIVIDUAL_OVERDISC_DISABLED");
    }

    /**
     * <p>setIsIndividualOverdiscDisabled.</p>
     *
     * @param value a {@link java.lang.Boolean} object.
     */
    public void setIsIndividualOverdiscDisabled(Boolean value) {
        setBooleanValue("IS_INDIVIDUAL_OVERDISC_DISABLED", value);
    }

    // PARTY_ROLE_ASSIGNMENTS
    /**
     * <p>getPartyRoleAssignments.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<BSCSPartyRoleAssignment> getPartyRoleAssignments() {
        return getListOfBSCSModelValue("PARTY_ROLE_ASSIGNMENTS", BSCSPartyRoleAssignment.class);
    }

    /**
     * <p>setPartyRoleAssignments.</p>
     *
     * @param value a {@link java.util.List} object.
     */
    public void setPartyRoleAssignments(List<BSCSPartyRoleAssignment> value) {
        setListOfBSCSModelValue("PARTY_ROLE_ASSIGNMENTS", value);
    }

    // PAYMENT_RESP
    /**
     * <p>getIsPaymentResponsible.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getIsPaymentResponsible() {
        return getBooleanValue(Constants.P_PAYMENT_RESP);
    }

    /**
     * <p>setIsPaymentResponsible.</p>
     *
     * @param value a {@link java.lang.Boolean} object.
     */
    public void setIsPaymentResponsible(Boolean value) {
        setBooleanValue(Constants.P_PAYMENT_RESP, value);
    }

    // PRG_CODE
    /**
     * <p>getPriceGroupCode.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getPriceGroupCode() {
        return getStringValue(Constants.P_PRG_CODE);
    }

    /**
     * <p>setPriceGroupCode.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setPriceGroupCode(String value) {
        setStringValue(Constants.P_PRG_CODE, value);
    }

    // REFUND_CURRENCY_ID
    /**
     * <p>getRefundCurrencyId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getRefundCurrencyId() {
        return getLongValue(Constants.P_REFUND_CURRENCY_ID);
    }

    /**
     * <p>setRefundCurrencyId.</p>
     *
     * @param value a {@link java.lang.Long} object.
     */
    public void setRefundCurrencyId(Long value) {
        setLongValue(Constants.P_REFUND_CURRENCY_ID, value);
    }

    // REFUND_CURRENCY_ID_PUB
    /**
     * <p>getRefundCurrencyIdPub.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getRefundCurrencyIdPub() {
        return getStringValue(Constants.P_REFUND_CURRENCY_ID_PUB);
    }

    /**
     * <p>setRefundCurrencyIdPub.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setRefundCurrencyIdPub(String value) {
        setStringValue(Constants.P_REFUND_CURRENCY_ID_PUB, value);
    }

    // RPCODE
    /**
     * <p>getRatePlan.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getRatePlan() {
        return getLongValue(Constants.P_RPCODE);
    }

    /**
     * <p>setRatePlan.</p>
     *
     * @param value a {@link java.lang.Long} object.
     */
    public void setRatePlan(Long value) {
        setLongValue(Constants.P_RPCODE, value);
    }

    // RPCODE_PUB
    /**
     * <p>getRatePlanCode.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getRatePlanCode() {
        return getStringValue(Constants.P_RPCODE_PUB);
    }

    /**
     * <p>setRatePlanCode.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setRatePlanCode(String value) {
        setStringValue(Constants.P_RPCODE_PUB, value);
    }

    // RS_CODE
    /**
     * <p>getReasonCode.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getReasonCode() {
        return getLongValue(Constants.P_RS_CODE);
    }

    /**
     * <p>setReasonCode.</p>
     *
     * @param value a {@link java.lang.Long} object.
     */
    public void setReasonCode(Long value) {
        setLongValue(Constants.P_RS_CODE, value);
    }

    // SR_CODE
    /**
     * <p>getSRCode.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getSRCode() {
        return getLongValue(Constants.P_SR_CODE);
    }

    /**
     * <p>setSRCode.</p>
     *
     * @param value a {@link java.lang.Long} object.
     */
    public void setSRCode(Long value) {
        setLongValue(Constants.P_SR_CODE, value);
    }

    // TB_AMOUNT
    /**
     * <p>getTBAmount.</p>
     *
     * @return a {@link java.lang.Float} object.
     */
    public Float getTBAmount() {
        Double d = getDoubleValue(Constants.P_TB_AMOUNT);
        return (null == d ? null : d.floatValue());
    }

    /**
     * <p>setTBAmount.</p>
     *
     * @param value a {@link java.lang.Float} object.
     */
    public void setTBAmount(Float value) {
        setDoubleValue(Constants.P_TB_AMOUNT, (null == value ? null : value.doubleValue()));
    }

    // TB_CURRENCY_ID
    /**
     * <p>getTBCurrencyId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getTBCurrencyId() {
        return getLongValue(Constants.P_TB_CURRENCY_ID);
    }

    /**
     * <p>setTBCurrencyId.</p>
     *
     * @param value a {@link java.lang.Long} object.
     */
    public void setTBCurrencyId(Long value) {
        setLongValue(Constants.P_TB_CURRENCY_ID, value);
    }

    // TB_CURRENCY_ID_PUB
    /**
     * <p>getTBCurrencyIdPub.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getTBCurrencyIdPub() {
        return getStringValue(Constants.P_TB_CURRENCY_ID_PUB);
    }

    /**
     * <p>setTBCurrencyIdPub.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setTBCurrencyIdPub(String value) {
        setStringValue(Constants.P_TB_CURRENCY_ID_PUB, value);
    }

    // TB_MODE
    /**
     * <p>getTBMode.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getTBMode() {
        return getStringValue(Constants.P_TB_MODE);
    }

    /**
     * <p>setTBMode.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setTBMode(String value) {
        setStringValue(Constants.P_TB_MODE, value);
    }

    // TB_PURPOSE
    /**
     * <p>getTBPurpose.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getTBPurpose() {
        return getStringValue(Constants.P_TB_PURPOSE);
    }

    /**
     * <p>setTBPurpose.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setTBPurpose(String value) {
        setStringValue(Constants.P_TB_PURPOSE, value);
    }

    // TRADE_CODE
    /**
     * <p>getTradeCode.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getTradeCode() {
        return getStringValue(Constants.P_TRADE_CODE);
    }

    /**
     * <p>setTradeCode.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setTradeCode(String value) {
        setStringValue(Constants.P_TRADE_CODE, value);
    }

    // WConstants.P_CODE
    /**
     * <p>getWelcomProcedureCode.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getWelcomProcedureCode() {
        return getLongValue(Constants.P_WP_CODE);
    }

    /**
     * <p>setWelcomProcedureCode.</p>
     *
     * @param value a {@link java.lang.Long} object.
     */
    public void setWelcomProcedureCode(Long value) {
        setLongValue(Constants.P_WP_CODE, value);
    }

}
