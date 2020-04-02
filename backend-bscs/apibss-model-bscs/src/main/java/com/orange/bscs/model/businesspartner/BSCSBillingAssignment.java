package com.orange.bscs.model.businesspartner;

import java.util.Date;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

/**
 * <p>BSCSBillingAssignment class.</p>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public class BSCSBillingAssignment extends BSCSModel {

    /**
     * <p>setSearchAll.</p>
     *
     * @param all a boolean.
     */
    public void setSearchAll(boolean all) {
        setBooleanValue(Constants.P_ALL, all);
    }

    /**
     * <p>isSearchAll.</p>
     *
     * @return a boolean.
     */
    public boolean isSearchAll() {
        return Boolean.TRUE.equals(getBooleanValue(Constants.P_ALL));
    }

    /**
     * <p>getBillingAccountCode.</p>
     *
     * @return le billingAccountCode
     */
    public String getBillingAccountCode() {
        return getStringValue(Constants.P_BILLING_ACCOUNT_CODE);
    }

    /**
     * <p>setBillingAccountCode.</p>
     *
     * @param billingAccountCode
     *            le billingAccountCode à définir
     */
    public void setBillingAccountCode(String billingAccountCode) {
        setStringValue(Constants.P_BILLING_ACCOUNT_CODE, billingAccountCode);
    }

    /**
     * <p>getBillingAccountId.</p>
     *
     * @return le billingAccountId
     */
    public Long getBillingAccountId() {
        return getLongValue(Constants.P_BILLING_ACCOUNT_ID);
    }

    /**
     * <p>setBillingAccountId.</p>
     *
     * @param billingAccountId
     *            le billingAccountId à définir
     */
    public void setBillingAccountId(Long billingAccountId) {
        setLongValue(Constants.P_BILLING_ACCOUNT_ID, billingAccountId);
    }

    /**
     * <p>getChargeTypeId.</p>
     *
     * @return le chargeTypeId
     */
    public Long getChargeTypeId() {
        return getLongValue(Constants.P_CHARGE_TYPE_ID);
    }

    /**
     * <p>setChargeTypeId.</p>
     *
     * @param chargeTypeId
     *            le chargeTypeId à définir
     */
    public void setChargeTypeId(Long chargeTypeId) {
        setLongValue(Constants.P_CHARGE_TYPE_ID, chargeTypeId);
    }

    /**
     * <p>getContractId.</p>
     *
     * @return le contractId
     */
    public Long getContractId() {
        return getLongValue(Constants.P_CO_ID);
    }

    /**
     * <p>setContractId.</p>
     *
     * @param contractId
     *            le contractId à définir
     */
    public void setContractId(Long contractId) {
        setLongValue(Constants.P_CO_ID, contractId);
    }

    /**
     * <p>getContractIdPub.</p>
     *
     * @return le contractIdPub
     */
    public String getContractIdPub() {
        return getStringValue(Constants.P_CO_ID_PUB);
    }

    /**
     * <p>setContractIdPub.</p>
     *
     * @param contractIdPub
     *            le contractIdPub à définir
     */
    public void setContractIdPub(String contractIdPub) {
        setStringValue(Constants.P_CO_ID_PUB, contractIdPub);
    }

    /**
     * <p>getProfileId.</p>
     *
     * @return le profileId
     */
    public Long getProfileId() {
        return getLongValue(Constants.P_PROFILE_ID);
    }

    /**
     * <p>setProfileId.</p>
     *
     * @param profileId
     *            le profileId à définir
     */
    public void setProfileId(Long profileId) {
        setLongValue(Constants.P_PROFILE_ID, profileId);
    }

    /**
     * <p>getServiceId.</p>
     *
     * @return le serviceId
     */
    public Long getServiceId() {
        return getLongValue(Constants.P_SNCODE);
    }

    /**
     * <p>setServiceId.</p>
     *
     * @param sncode
     *            le serviceId à définir
     */
    public void setServiceId(Long sncode) {
        setLongValue(Constants.P_SNCODE, sncode);
    }

    /**
     * <p>getServiceIdPub.</p>
     *
     * @return le serviceIdPub
     */
    public String getServiceIdPub() {
        return getStringValue(Constants.P_SNCODE_PUB);
    }

    /**
     * <p>setServiceIdPub.</p>
     *
     * @param sncodepub
     *            le serviceIdPub à définir
     */
    public void setServiceIdPub(String sncodepub) {
        setStringValue(Constants.P_SNCODE_PUB, sncodepub);
    }

    /**
     * <p>getAssignmentSequenceNumber.</p>
     *
     * @return le assignmentSequenceNumber
     */
    public Long getAssignmentSequenceNumber() {
        return getLongValue(Constants.P_ASSIGNMENT_SEQNO);
    }

    /**
     * <p>setAssignmentSequenceNumber.</p>
     *
     * @param assignmentSequenceNumber
     *            le assignmentSequenceNumber à définir
     */
    public void setAssignmentSequenceNumber(Long assignmentSequenceNumber) {
        setLongValue(Constants.P_ASSIGNMENT_SEQNO, assignmentSequenceNumber);
    }

    /**
     * <p>getAssignmentTypeChar.</p>
     *
     * @return le assignmentType 'I' ou 'E'
     */
    public Character getAssignmentTypeChar() {
        return getCharacterValue(Constants.P_ASSIGNMENT_TYPE);
    }

    /**
     * <p>setAssignmentTypeChar.</p>
     *
     * @param assignmentType
     *            le assignmentType à définir
     */
    public void setAssignmentTypeChar(Character assignmentType) {
        setCharacterValue(Constants.P_ASSIGNMENT_TYPE, assignmentType);
    }

    /**
     * <p>getAssignmentType.</p>
     *
     * @return le assignmentType 'I' ou 'E'
     */
    public EnumBillingAssignmentType getAssignmentType() {
        return EnumBillingAssignmentType.fromChar(getAssignmentTypeChar());
    }

    /**
     * <p>setAssignmentType.</p>
     *
     * @param assignmentType
     *            le assignmentType à définir
     */
    public void setAssignmentType(EnumBillingAssignmentType assignmentType) {
        setAssignmentTypeChar(null == assignmentType ? null : assignmentType.toCharacter());
    }

    /**
     * <p>USAGE_TYPE_ID.</p>
     *
     * @return le usageTypeId
     */
    public Long getUsageTypeId() {
        return getLongValue(Constants.P_USAGE_TYPE_ID);
    }

    /**
     * <p>setUsageTypeId.</p>
     *
     * @param usageTypeId USAGE_TYPE_ID
     */
    public void setUsageTypeId(Long usageTypeId) {
        setLongValue(Constants.P_USAGE_TYPE_ID, usageTypeId);
    }

    /**
     * <p>USAGE_TYPE_SHNAME.</p>
     *
     * @return le usageTypeShortName
     */
    public String getUsageTypeShortName() {
        return getStringValue(Constants.P_USAGE_TYPE_SHNAME);
    }

    /**
     * <p>setUsageTypeShortName.</p>
     *
     * @param usageTypeShortName USAGE_TYPE_SHNAME
     */
    public void setUsageTypeShortName(String usageTypeShortName) {
        setStringValue(Constants.P_USAGE_TYPE_SHNAME, usageTypeShortName);
    }

    /**
     * <p>getValidFrom.</p>
     *
     * @return start Date
     */
    public Date getValidFrom() {
        return getDateTimeValue(Constants.P_VALID_FROM);
    }

    /**
     * <p>setValidFrom.</p>
     *
     * @param validFrom
     *            start date
     */
    public void setValidFrom(Date validFrom) {
        setDateTimeValue(Constants.P_VALID_FROM, validFrom);
    }

    /**
     * <p>getValidTo.</p>
     *
     * @return expiration Date
     */
    public Date getValidTo() {
        return getDateTimeValue(Constants.P_VALID_TO);
    }

    /**
     * <p>setValidTo.</p>
     *
     * @param validTo
     *            expiration date
     */
    public void setValidTo(Date validTo) {
        setDateTimeValue(Constants.P_VALID_TO, validTo);
    }

}
