package com.orange.bscs.model.businesspartner;

import java.util.Date;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

/**
 * <p>BSCSCustomersSearchRequest class.</p>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public class BSCSCustomersSearchRequest extends BSCSModel {

    // ADR_BIRTHDT
    /**
     * <p>getBirthDate.</p>
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getBirthDate() {
        return getDateValue(Constants.P_ADR_BIRTHDT);
    }

    /**
     * <p>setBirthDate.</p>
     *
     * @param value a {@link java.util.Date} object.
     */
    public BSCSCustomersSearchRequest setBirthDate(Date value) {
        setDateValue(Constants.P_ADR_BIRTHDT, value);
        return this;
    }

    // ADR_CITY
    /**
     * <p>getCity.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getCity() {
        return getStringValue(Constants.P_ADR_CITY);
    }

    /**
     * <p>setCity.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public BSCSCustomersSearchRequest setCity(String value) {
        setStringValue(Constants.P_ADR_CITY, value);
        return this;
    }

    // ADR_COMPNO
    /**
     * <p>getNationalOrganisationIdentifier.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getNationalOrganisationIdentifier() {
        return getStringValue(Constants.P_ADR_COMPNO);
    }

    /**
     * <p>setNationalOrganisationIdentifier.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public BSCSCustomersSearchRequest setNationalOrganisationIdentifier(String value) {
        setStringValue(Constants.P_ADR_COMPNO, value);
        return this;
    }

    // ADR_DRIVELICENCE
    /**
     * <p>getDrivingLicence.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getDrivingLicence() {
        return getStringValue(Constants.P_ADR_DRIVELICENCE);
    }

    /**
     * <p>setDrivingLicence.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public BSCSCustomersSearchRequest setDrivingLicence(String value) {
        setStringValue(Constants.P_ADR_DRIVELICENCE, value);  
        return this;
    }

    // ADR_EMAIL
    /**
     * <p>getEmail.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getEmail() {
        return getStringValue(Constants.P_ADR_EMAIL);
    }

    /**
     * <p>setEmail.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public BSCSCustomersSearchRequest setEmail(String value) {
        setStringValue(Constants.P_ADR_EMAIL, value);
        return this;
    }

    // ADR_FNAME
    /**
     * <p>getFirstName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getFirstName() {
        return getStringValue(Constants.P_ADR_FNAME);
    }

    /**
     * <p>setFirstName.</p>
     *
     * @param value a {@link java.lang.String} object.
     * @return 
     */
    public BSCSCustomersSearchRequest setFirstName(String value) {
        setStringValue(Constants.P_ADR_FNAME, value);
        return this;
    }

    // ADR_LNAME
    /**
     * <p>getLastName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getLastName() {
        return getStringValue(Constants.P_ADR_LNAME);
    }

    /**
     * <p>setLastName.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public BSCSCustomersSearchRequest setLastName(String value) {
        setStringValue(Constants.P_ADR_LNAME, value);
        return this;
    }

    // ADR_NAME
    /**
     * <p>getAddressName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getAddressName() {
        return getStringValue(Constants.P_ADR_NAME);
    }

    /**
     * <p>setAddressName.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public BSCSCustomersSearchRequest setAddressName(String value) {
        setStringValue(Constants.P_ADR_NAME, value);
        return this;
    }

    // ADR_PASSPORTNO
    /**
     * <p>getPassport.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getPassport() {
        return getStringValue(Constants.P_ADR_PASSPORTNO);
    }

    /**
     * <p>setPassport.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public BSCSCustomersSearchRequest setPassport(String value) {
        setStringValue(Constants.P_ADR_PASSPORTNO, value);
        return this;
    }

    // ADR_ROLES
    /**
     * <p>getAddressRoles.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getAddressRoles() {
        return getStringValue(Constants.P_ADR_ROLES);
    }

    /**
     * <p>setAddressRoles.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public BSCSCustomersSearchRequest setAddressRoles(String value) {
        setStringValue(Constants.P_ADR_ROLES, value);
        return this;
    }

    // ADR_SOCIALSENO
    /**
     * <p>getSecuritySocialCard.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getSocialNumber() {
        return getStringValue(Constants.P_ADR_SOCIALSENO);
    }

    /**
     * <p>setSecuritySocialCard.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public BSCSCustomersSearchRequest setSocialNumber(String value) {
        setStringValue(Constants.P_ADR_SOCIALSENO, value);
        return this;
    }

    // ADR_STATE
    /**
     * <p>getState.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getState() {
        return getStringValue(Constants.P_ADR_STATE);
    }

    /**
     * <p>setState.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public BSCSCustomersSearchRequest setState(String value) {
        setStringValue(Constants.P_ADR_STATE, value);
        return this;
    }

    // ADR_STREET
    /**
     * <p>getStreet.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getStreet() {
        return getStringValue(Constants.P_ADR_STREET);
    }

    /**
     * <p>setStreet.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public BSCSCustomersSearchRequest setStreet(String value) {
        setStringValue(Constants.P_ADR_STREET, value);
        return this;
    }

    // ADR_STREETNO
    /**
     * <p>getStreetNumber.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getStreetNumber() {
        return getStringValue(Constants.P_ADR_STREETNO);
    }

    /**
     * <p>setStreetNumber.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public BSCSCustomersSearchRequest setStreetNumber(String value) {
        setStringValue(Constants.P_ADR_STREETNO, value);
        return this;
    }

    // ADR_TAXNO
    /**
     * <p>getTaxRegistrationNumber.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getTaxRegistrationNumber() {
        return getStringValue(Constants.P_ADR_TAXNO);
    }

    /**
     * <p>setTaxRegistrationNumber.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public BSCSCustomersSearchRequest setTaxRegistrationNumber(String value) {
        setStringValue(Constants.P_ADR_TAXNO, value);
        return this;
    }

    // ADR_ZIP
    /**
     * <p>getPostalCode.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getPostalCode() {
        return getStringValue(Constants.P_ADR_ZIP);
    }

    /**
     * <p>setPostalCode.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public BSCSCustomersSearchRequest setPostalCode(String value) {
        setStringValue(Constants.P_ADR_ZIP, value);
        return this;
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
    public BSCSCustomersSearchRequest setCostCenterCode(String value) {
        setStringValue(Constants.P_COST_CODE_PUB, value);
        return this;
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
    public BSCSCustomersSearchRequest setCostCenterId(Long value) {
        setLongValue(Constants.P_COST_ID, value);
        return this;
    }

    // COUNTRY_ID
    /**
     * <p>getCountryId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getCountryId() {
        return getLongValue(Constants.P_COUNTRY_ID);
    }

    /**
     * <p>setCountryId.</p>
     *
     * @param value a {@link java.lang.Long} object.
     */
    public BSCSCustomersSearchRequest setCountryId(Long value) {
        setLongValue(Constants.P_COUNTRY_ID, value);
        return this;
    }

    // COUNTRY_ID_PUB
    /**
     * <p>getCountryCode.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getCountryCode() {
        return getStringValue(Constants.P_COUNTRY_ID_PUB);
    }

    /**
     * <p>setCountryCode (COUNTRY_ID_PUB).</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public BSCSCustomersSearchRequest setCountryCode(String value) {
        setStringValue(Constants.P_COUNTRY_ID_PUB, value);
        return this;
    }

    // CS_CODE
    /**
     * <p>getCustomerCode (COUNTRY_ID_PUB).</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getCustomerCode() {
        return getStringValue(Constants.P_CS_CODE);
    }

    /**
     * <p>setCustomerCode.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public BSCSCustomersSearchRequest setCustomerCode(String value) {
        setStringValue(Constants.P_CS_CODE, value);
        return this;
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
    public BSCSCustomersSearchRequest setDealerId(Long value) {
        setLongValue(Constants.P_CS_DEALERID, value);
        return this;
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
    public BSCSCustomersSearchRequest setDealerIdPub(String value) {
        setStringValue(Constants.P_CS_DEALERID_PUB, value);
        return this;
    }

    // CS_ID_HIGH
    /**
     * <p>getParentCustomerId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getParentCustomerId() {
        return getLongValue(Constants.P_CS_ID_HIGH);
    }

    /**
     * <p>setParentCustomerId.</p>
     *
     * @param value a {@link java.lang.Long} object.
     */
    public BSCSCustomersSearchRequest setParentCustomerId(Long value) {
        setLongValue(Constants.P_CS_ID_HIGH, value);
        return this;
    }

    // CS_ID_HIGH_PUB
    /**
     * <p>getParentCustomerIdPub.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getParentCustomerIdPub() {
        return getStringValue(Constants.P_CS_ID_HIGH_PUB);
    }

    /**
     * <p>setParentCustomerIdPub.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public BSCSCustomersSearchRequest setParentCustomerIdPub(String value) {
        setStringValue(Constants.P_CS_ID_HIGH_PUB, value);
        return this;
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
    public BSCSCustomersSearchRequest setCustomerIdPub(String value) {
        setStringValue(Constants.P_CS_ID_PUB, value);
        return this;
    }

    /**
     * CS_LEVEL_CODE (10, 20, 30 or 40)
     *
     * @return a {@link java.lang.String} object.
     */
    public EnumCustomerLevelCode getCustomerLevelCode() {
        return EnumCustomerLevelCode.parse(getStringValue(Constants.P_CS_LEVEL_CODE));
    }

    /**
     * CS_LEVEL_CODE (10, 20, 30 or 40)
     *
     * @param value a {@link java.lang.String} object.
     */
    public BSCSCustomersSearchRequest setCustomerLevelCode(EnumCustomerLevelCode value) {
        setStringValue(Constants.P_CS_LEVEL_CODE, EnumCustomerLevelCode.toString(value));
        return this;
    }

    // CS_STATUS
    /**
     * <p>(CS_STATUS).</p>
     *
     * @return a {@link com.orange.bscs.model.businesspartner.EnumCustomerStatus} object.
     */
    public EnumCustomerStatus getStatus() {
        return EnumCustomerStatus.parse(getCharacterValue(Constants.P_CS_STATUS));
    }
    
    /**
     * <p>setStatus.</p>
     *
     * @param value a {@link java.lang.Integer} object.
     */
    public BSCSCustomersSearchRequest setStatus(EnumCustomerStatus value) {
        if(null!=value){
            setCharacterValue(Constants.P_CS_STATUS, value.getCharacter());
        }
        return this;
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
    public BSCSCustomersSearchRequest setExternalCustomerId(String value) {
        setStringValue(Constants.P_EXTERNAL_CUSTOMER_ID, value);
        return this;
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
    public BSCSCustomersSearchRequest setExternalCustomerIdSet(String value) {
        setStringValue(Constants.P_EXTERNAL_CUSTOMER_SET_ID, value);
        return this;
    }

    // FLAG_CASE
    /**
     * <p>getFlagMatchCase.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getFlagMatchCase() {
        return getBooleanValue(Constants.P_FLAG_CASE);
    }

    /**
     * <p>setFlagMatchCase.</p>
     *
     * @param value a {@link java.lang.Boolean} object.
     */
    public BSCSCustomersSearchRequest setFlagMatchCase(Boolean value) {
        setBooleanValue(Constants.P_FLAG_CASE, value);
        return this;
    }

    // FLAG_MATCHCODE
    /**
     * <p>getFlagMatchCode.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getFlagMatchCode() {
        return getBooleanValue(Constants.P_FLAG_MATCHCODE);
    }

    /**
     * <p>setFlagMatchCode.</p>
     *
     * @param value a {@link java.lang.Boolean} object.
     */
    public BSCSCustomersSearchRequest setFlagMatchCode(Boolean value) {
        setBooleanValue(Constants.P_FLAG_MATCHCODE, value);
        return this;
    }

    // IGNORE_BU_IND
    /**
     * <p>getIgnoreBuInd.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getIgnoreBuInd() {
        return getBooleanValue("IGNORE_BU_IND");
    }

    /**
     * <p>setIgnoreBuInd.</p>
     *
     * @param value a {@link java.lang.Boolean} object.
     */
    public BSCSCustomersSearchRequest setIgnoreBuInd(Boolean value) {
        setBooleanValue("IGNORE_BU_IND", value);
        return this;
    }

    // LA_MEMBER
    /**
     * <p>getLargeAccountMember.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getLargeAccountMember() {
        return getBooleanValue(Constants.P_LA_MEMBER);
    }

    /**
     * <p>setLargeAccountMember.</p>
     *
     * @param value a {@link java.lang.Boolean} object.
     */
    public BSCSCustomersSearchRequest setLargeAccountMember(Boolean value) {
        setBooleanValue(Constants.P_LA_MEMBER, value);
        return this;
    }

    // PARTY_ROLE_ID
    /**
     * <p>getPartyRoleId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getPartyRoleId() {
        return getLongValue(Constants.P_PARTY_ROLE_ID);
    }

    /**
     * <p>setPartyRoleId.</p>
     *
     * @param value a {@link java.lang.Long} object.
     */
    public BSCSCustomersSearchRequest setPartyRoleId(Long value) {
        setLongValue(Constants.P_PARTY_ROLE_ID, value);
        return this;
    }

    // PARTY_ROLE_SHNAME
    /**
     * <p>getPartyRoleShortName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getPartyRoleShortName() {
        return getStringValue(Constants.P_PART_ROLE_SHNAME);
    }

    /**
     * <p>setPartyRoleShortName.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public BSCSCustomersSearchRequest setPartyRoleShortName(String value) {
        setStringValue(Constants.P_PART_ROLE_SHNAME, value);
        return this;
    }

    // PARTY_TYPE
    /**
     * <p>getPartyType.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getPartyType() {
        return getStringValue(Constants.P_PARTY_TYPE);
    }

    /**
     * <p>setPartyType.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public BSCSCustomersSearchRequest setPartyType(String value) {
        setStringValue(Constants.P_PARTY_TYPE, value);
        return this;
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
    public BSCSCustomersSearchRequest setIsPaymentResponsible(Boolean value) {
        setBooleanValue(Constants.P_PAYMENT_RESP, value);
        return this;
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
    public BSCSCustomersSearchRequest setPriceGroupCode(String value) {
        setStringValue(Constants.P_PRG_CODE, value);
        return this;
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
    public BSCSCustomersSearchRequest setRatePlan(Long value) {
        setLongValue(Constants.P_RPCODE, value);
        return this;
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
    public BSCSCustomersSearchRequest setRatePlanCode(String value) {
        setStringValue(Constants.P_RPCODE_PUB, value);
        return this;
    }

    // SRCH_COUNT
    /**
     * <p>getSearchCount.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getSearchCount() {
        return getIntegerValue(Constants.P_SRCH_COUNT);
    }

    /**
     * <p>setSearchCount.</p>
     *
     * @param value a {@link java.lang.Integer} object.
     */
    public BSCSCustomersSearchRequest setSearchCount(Integer value) {
        setIntegerValue(Constants.P_SRCH_COUNT, value);
        return this;
    }

    // START_INDEX
    /**
     * <p>getStartIndex.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getStartIndex() {
        return getLongValue("START_INDEX");
    }

    /**
     * <p>setSartIndex.</p>
     *
     * @param value a {@link java.lang.Long} object.
     */
    public BSCSCustomersSearchRequest setSartIndex(Long value) {
        setLongValue("START_INDEX", value);
        return this;
    }

    // USE_CSR_ROLES
    /**
     * <p>(USE_CSR_ROLES).</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getUseCsrRoles() {
        return getBooleanValue("USE_CSR_ROLES");
    }

    /**
     * <p>(USE_CSR_ROLES).</p>
     *
     * @param value a {@link java.lang.Boolean} object.
     */
    public BSCSCustomersSearchRequest setUseCsrRoles(Boolean value) {
        setBooleanValue("USE_CSR_ROLES", value);
        return this;
    }

}
