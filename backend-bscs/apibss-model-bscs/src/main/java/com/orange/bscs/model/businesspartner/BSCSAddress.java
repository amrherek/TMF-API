package com.orange.bscs.model.businesspartner;


import java.util.Date;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.model.SVLObjectWrapper;
import com.orange.bscs.api.utils.config.Constants;

/**
 * <code>
 * ADDRESSES.READ {
 *   CS_ID                =  : java.lang.Long
 *   CS_ID_PUB            =  : java.lang.String
 * }
 * => {
 *   CS_ID                =  : java.lang.Long
 *   CS_ID_PUB            =  : java.lang.String
 *   LIST_OF_ALL_ADDRESSES = sub element : com.lhs.ccb.common.soiimpl.NamedValueCon
 * tainerList
 *   BSCSAddress
 * }
 *
 *  ADDRESS.READ {
 *   ADR_TYPE             =  : java.lang.Character
 *   CS_ID                =  : java.lang.Long
 *   CS_ID_PUB            =  : java.lang.String
 * }
 * => {
 *   BSCSAddress
 * }
 *
 *
 *   ADR_BIRTHDT          =  : com.lhs.ccb.common.soiimpl.SVLDateImpl
 * * ADR_CITY             =  : java.lang.String
 *   ADR_COMPNO           =  : java.lang.String
 *   ADR_COUNTY           =  : java.lang.String
 *   ADR_CUSTTYPE         =  : java.lang.Character
 *   ADR_DRIVELICENCE     =  : java.lang.String
 *   ADR_EMAIL            =  : java.lang.String
 *   ADR_EMPLOYEE         =  : java.lang.Boolean
 *   ADR_EMPLOYER         =  : java.lang.String
 *   ADR_FAX              =  : java.lang.String
 *   ADR_FAX_AREA         =  : java.lang.String
 *   ADR_FNAME            =  : java.lang.String
 *   ADR_FORWARD          =  : java.lang.Boolean
 *   ADR_IDNO             =  : java.lang.String
 *   ADR_INCCODE          =  : java.lang.String
 *   ADR_JBDES            =  : java.lang.String
 *   ADR_JUR_TAX_OVERRIDDEN =  : java.lang.Boolean
 * * ADR_LNAME            =  : java.lang.String
 *   ADR_LOCATION_1       =  : java.lang.String
 *   ADR_LOCATION_2       =  : java.lang.String
 *   ADR_MNAME            =  : java.lang.String
 *   ADR_NAME             =  : java.lang.String
 *   ADR_NATIONALITY      =  : java.lang.Long
 *   ADR_NATIONALITY_PUB  =  : java.lang.String
 *   ADR_NOTE1            =  : java.lang.String
 *   ADR_NOTE2            =  : java.lang.String
 *   ADR_NOTE3            =  : java.lang.String
 *   ADR_PHN1             =  : java.lang.String
 *   ADR_PHN1_AREA        =  : java.lang.String
 *   ADR_PHN2             =  : java.lang.String
 *   ADR_PHN2_AREA        =  : java.lang.String
 *   ADR_REMARK           =  : java.lang.String
 *   ADR_ROLES            =  : java.lang.String
 * * ADR_SEQ              =  : java.lang.Long
 *   ADR_SEX              =  : java.lang.Character
 *   ADR_SMSNO            =  : java.lang.String
 *   ADR_SOCIALSENO       =  : java.lang.String
 *   ADR_STATE            =  : java.lang.String
 *   ADR_STREET           =  : java.lang.String
 *   ADR_STREETNO         =  : java.lang.String
 *   ADR_TAXNO            =  : java.lang.String
 *   ADR_TEMPBILL_OVERRIDDEN =  : java.lang.Boolean
 *   ADR_URGENT           =  : java.lang.Boolean
 *   ADR_VALIDDATE        =  : com.lhs.ccb.common.soiimpl.SVLDateTimeImpl
 *   ADR_YEARS            =  : java.lang.Integer
 * * ADR_ZIP              =  : java.lang.String
 *   COUNTRY_ID           =  : java.lang.Long
 *   COUNTRY_ID_PUB       =  : java.lang.String
 *   CS_ID                =  : java.lang.Long
 *   CS_ID_PUB            =  : java.lang.String
 *   IDTYPE_CODE          =  : java.lang.Long
 *   LNG_CODE             =  : java.lang.Long
 *   LNG_CODE_PUB         =  : java.lang.String
 *   MAS_CODE             =  : java.lang.Long
 *   MAS_CODE_PUB         =  : java.lang.String
 *   TTL_ID               =  : java.lang.Long
 *   TTL_ID_PUB           =  : java.lang.String
 * }
 *
 * </code>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public class BSCSAddress extends BSCSModel {

    // === PROPERTIES TO CONVERT (by Lookup Services) between EBS and SOI ===

    private String ebsTitle;
    private String ebsCountryCode;
    private String ebsCountryName;
    private String maritalStatus;
    private String languageCode;

    // === PROPERTIES TO CONVERT (by Lookup Services) between EBS and SOI ===

    /**
     * <p>Constructor for BSCSAddress.</p>
     */
    public BSCSAddress() {
        super();
    }

    /**
     * <p>Constructor for BSCSAddress.</p>
     *
     * @param sVLObject
     */
    public BSCSAddress(SVLObjectWrapper sVLObject) {
        super(sVLObject);
    }

    /**
     * <p>getSequenceNumber.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getSequenceNumber() {
        return getLongValue(Constants.P_ADR_SEQ);
    }

    /**
     * <p>getCustomerType.</p>
     *
     * @return a {@link java.lang.Character} object.
     */
    public Character getCustomerType() {
        return getCharacterValue(Constants.P_ADR_CUSTTYPE);
    }

    /**
     * <p>getAddressRoles.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public java.lang.String getAddressRoles() {
        return getStringValue(Constants.P_ADR_ROLES);
    }

    /**
     * <p>getTitleId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public java.lang.Long getTitleId() {
        return getLongValue(Constants.P_TTL_ID);
    }

    /**
     * <p>getTitleCode.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public java.lang.String getTitleCode() {
        return getStringValue(Constants.P_TTL_ID_PUB);
    }

    /**
     * <p>getFirstName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public java.lang.String getFirstName() {
        return getStringValue(Constants.P_ADR_FNAME);
    }

    /**
     * <p>getMidNames.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public java.lang.String getMidNames() {
        return getStringValue(Constants.P_ADR_MNAME);
    }

    /**
     * <p>getLastName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public java.lang.String getLastName() {
        return getStringValue(Constants.P_ADR_LNAME);
    }

    /**
     * <p>getTelephone1.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public java.lang.String getTelephone1() {
        return getStringValue(Constants.P_ADR_PHN1);
    }

    /**
     * <p>getTelephone2.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public java.lang.String getTelephone2() {
        return getStringValue(Constants.P_ADR_PHN2);
    }

    /**
     * <p>getTelephoneMobile (ADR_SMSNO).</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public java.lang.String getTelephoneMobile() {
        return getStringValue(Constants.P_ADR_SMSNO);
    }

    /**
     * <p>getFax.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public java.lang.String getFax() {
        return getStringValue(Constants.P_ADR_FAX);
    }

    /**
     * <p>getEmail.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public java.lang.String getEmail() {
        return getStringValue(Constants.P_ADR_EMAIL);
    }

    /**
     * <p>getNationalityId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public java.lang.Long getNationalityId() {
        return getLongValue(Constants.P_ADR_NATIONALITY);
    }

    /**
     * <p>getNationalityCode.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public java.lang.String getNationalityCode() {
        return getStringValue(Constants.P_ADR_NATIONALITY_PUB);
    }

    /**
     * <p>getLangueId. (LNG_CODE)</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public java.lang.Long getLangueId() {
        return getLongValue(Constants.P_LNG_CODE);
    }

    /**
     * <p>getLangueCode(LNG_CODE_PUB).</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public java.lang.String getLangueCode() {
        return getStringValue(Constants.P_LNG_CODE_PUB);
    }

    /**
     * <p>getAddressName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public java.lang.String getAddressName() {
        return getStringValue(Constants.P_ADR_NAME);
    }

    /**
     * <p>getStreetNumber.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public java.lang.String getStreetNumber() {
        return getStringValue(Constants.P_ADR_STREETNO);
    }

    /**
     * <p>getStreet.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public java.lang.String getStreet() {
        return getStringValue(Constants.P_ADR_STREET);
    }

    /**
     * <p>getAddrLocation1.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public java.lang.String getAddrLocation1() {
        return getStringValue(Constants.P_ADR_LOCATION_1);
    }

    /**
     * <p>getAddrLocation2.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public java.lang.String getAddrLocation2() {
        return getStringValue(Constants.P_ADR_LOCATION_2);
    }

    /**
     * <p>getAddrNote1.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public java.lang.String getAddrNote1() {
        return getStringValue(Constants.P_ADR_NOTE1);
    }

    /**
     * <p>getAddrNote2.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public java.lang.String getAddrNote2() {
        return getStringValue(Constants.P_ADR_NOTE2);
    }

    /**
     * <p>getAddrNote3.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public java.lang.String getAddrNote3() {
        return getStringValue(Constants.P_ADR_NOTE3);
    }

    /**
     * <p>getPostalCode.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public java.lang.String getPostalCode() {
        return getStringValue(Constants.P_ADR_ZIP);
    }

    /**
     * <p>getCity.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public java.lang.String getCity() {
        return getStringValue(Constants.P_ADR_CITY);
    }

    /**
     * <p>getCounty.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public java.lang.String getCounty() {
        return getStringValue(Constants.P_ADR_COUNTY);
    }

    /**
     * <p>getState.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public java.lang.String getState() {
        return getStringValue(Constants.P_ADR_STATE);
    }

    /**
     * <p>getCountryId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public java.lang.Long getCountryId() {
        return getLongValue(Constants.P_COUNTRY_ID);
    }

    /**
     * <p>getCountryCode.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public java.lang.String getCountryCode() {
        return getStringValue(Constants.P_COUNTRY_ID_PUB);
    }

    /**
     * <p>getDrivingLicence.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public java.lang.String getDrivingLicence() {
        return getStringValue(Constants.P_ADR_DRIVELICENCE);
    }

    /**
     * <p>getSecuritySocialCard.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public java.lang.String getSecuritySocialCard() {
        return getStringValue(Constants.P_ADR_SOCIALSENO);
    }

    /**
     * <p>getMaritalStatusId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public java.lang.Long getMaritalStatusId() {
        return getLongValue(Constants.P_MAS_CODE);
    }

    /**
     * <p>getMaritalStatusCode.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public java.lang.String getMaritalStatusCode() {
        return getStringValue(Constants.P_MAS_CODE_PUB);
    }

    /**
     * <p>getSex.</p>
     *
     * @return a {@link java.lang.Character} object.
     */
    public java.lang.Character getSex() {
        return getCharacterValue(Constants.P_ADR_SEX);
    }

    /**
     * <p>getBirthDate.</p>
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getBirthDate() {
        return getDateValue(Constants.P_ADR_BIRTHDT);
    }

    /**
     * <p>getFlagEmployee.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public java.lang.Boolean getFlagEmployee() {
        return getBooleanValue(Constants.P_ADR_EMPLOYEE);
    }

    /**
     * <p>getFlagForward.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public java.lang.Boolean getFlagForward() {
        return getBooleanValue(Constants.P_ADR_FORWARD);
    }

    /**
     * <p>getFlagUrgent.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public java.lang.Boolean getFlagUrgent() {
        return getBooleanValue(Constants.P_ADR_URGENT);
    }

    /**
     * <p>getFlagTempBillingOverridden.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public java.lang.Boolean getFlagTempBillingOverridden() {
        return getBooleanValue(Constants.P_ADR_TEMPBILL_OVERRIDDEN);
    }

    /**
     * <p>getFlagAdrJurTaxOverriden.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public java.lang.Boolean getFlagAdrJurTaxOverriden() {
        return getBooleanValue(Constants.P_ADR_JUR_TAX_OVERRIDDEN);
    }

    /**
     * <p>getJobDescription.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getJobDescription() {
        return getStringValue(Constants.P_ADR_JBDES);
    }

    // Setters
    /**
     * <p>setSequenceNumber.</p>
     *
     * @param value a {@link java.lang.Long} object.
     */
    public void setSequenceNumber(Long value) {
        setLongValue(Constants.P_ADR_SEQ, value);
    }

    /**
     * <p>setCustomerType.</p>
     *
     * @param value a {@link java.lang.Character} object.
     */
    public void setCustomerType(Character value) {
        setCharacterValue(Constants.P_ADR_CUSTTYPE, value);
    }

    /**
     * <p>setAddressRoles.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setAddressRoles(String value) {
        setStringValue(Constants.P_ADR_ROLES, value);
    }

    /**
     * <p>setTitleId.</p>
     *
     * @param value a {@link java.lang.Long} object.
     */
    public void setTitleId(Long value) {
        setLongValue(Constants.P_TTL_ID, value);
    }

    /**
     * <p>setTitleCode.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setTitleCode(String value) {
        setStringValue(Constants.P_TTL_ID_PUB, value);
    }

    /**
     * <p>setFirstName.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setFirstName(String value) {
        setStringValue(Constants.P_ADR_FNAME, value);
    }

    /**
     * <p>setMidNames.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setMidNames(String value) {
        setStringValue(Constants.P_ADR_MNAME, value);
    }

    /**
     * <p>setLastName.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setLastName(String value) {
        setStringValue(Constants.P_ADR_LNAME, value);
    }

    /**
     * <p>setTelephone1.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setTelephone1(String value) {
        setStringValue(Constants.P_ADR_PHN1, value);
    }

    /**
     * <p>setTelephone2.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setTelephone2(String value) {
        setStringValue(Constants.P_ADR_PHN2, value);
    }

    /**
     * <p>setTelephoneMobile.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setTelephoneMobile(String value) {
        setStringValue(Constants.P_ADR_SMSNO, value);
    }

    /**
     * <p>setFax.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setFax(String value) {
        setStringValue(Constants.P_ADR_FAX, value);
    }

    /**
     * <p>setEmail.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setEmail(String value) {
        setStringValue(Constants.P_ADR_EMAIL, value);
    }

    /**
     * <p>setNationalityId.</p>
     *
     * @param value a {@link java.lang.Long} object.
     */
    public void setNationalityId(Long value) {
        setLongValue(Constants.P_ADR_NATIONALITY, value);
    }

    /**
     * <p>setNationalityCode.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setNationalityCode(String value) {
        setStringValue(Constants.P_ADR_NATIONALITY_PUB, value);
    }

    /**
     * <p>setLangueId.</p>
     *
     * @param value a {@link java.lang.Long} object.
     */
    public void setLangueId(Long value) {
        setLongValue(Constants.P_LNG_CODE, value);
    }

    /**
     * <p>setLangueCode.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setLangueCode(String value) {
        setStringValue(Constants.P_LNG_CODE_PUB, value);
    }

    /**
     * <p>setAddressName.</p>
     *
     * @param organisationTradingName a {@link java.lang.String} object.
     */
    public void setAddressName(String organisationTradingName) {
        setStringValue(Constants.P_ADR_NAME, organisationTradingName);
    }

    /**
     * <p>setStreetNumber.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setStreetNumber(String value) {
        setStringValue(Constants.P_ADR_STREETNO, value);
    }

    /**
     * <p>setStreet.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setStreet(String value) {
        setStringValue(Constants.P_ADR_STREET, value);
    }

    /**
     * <p>setAddrLocation1.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setAddrLocation1(String value) {
        setStringValue(Constants.P_ADR_LOCATION_1, value);
    }

    /**
     * <p>setAddrLocation2.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setAddrLocation2(String value) {
        setStringValue(Constants.P_ADR_LOCATION_2, value);
    }

    /**
     * <p>setAddrNote1.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setAddrNote1(String value) {
        setStringValue(Constants.P_ADR_NOTE1, value);
    }

    /**
     * <p>setAddrNote2.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setAddrNote2(String value) {
        setStringValue(Constants.P_ADR_NOTE2, value);
    }

    /**
     * <p>setAddrNote3.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setAddrNote3(String value) {
        setStringValue(Constants.P_ADR_NOTE3, value);
    }

    /**
     * <p>setPostalCode.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setPostalCode(String value) {
        setStringValue(Constants.P_ADR_ZIP, value);
    }

    /**
     * <p>setCity.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setCity(String value) {
        setStringValue(Constants.P_ADR_CITY, value);
    }

    /**
     * <p>setCounty.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setCounty(String value) {
        setStringValue(Constants.P_ADR_COUNTY, value);
    }

    /**
     * <p>setState.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setState(String value) {
        setStringValue(Constants.P_ADR_STATE, value);
    }

    /**
     * <p>setCountryId.</p>
     *
     * @param value a {@link java.lang.Long} object.
     */
    public void setCountryId(Long value) {
        setLongValue(Constants.P_COUNTRY_ID, value);
    }

    /**
     * <p>setCountryCode.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setCountryCode(String value) {
        setStringValue(Constants.P_COUNTRY_ID_PUB, value);
    }

    /**
     * <p>setDrivingLicense.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setDrivingLicense(String value) {
        setStringValue(Constants.P_ADR_DRIVELICENCE, value);
    }

    /**
     * <p>setSocialNumber.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setSocialNumber(String value) {
        setStringValue(Constants.P_ADR_SOCIALSENO, value);
    }

    /**
     * <p>setMaritalStatusId.</p>
     *
     * @param value a {@link java.lang.Long} object.
     */
    public void setMaritalStatusId(Long value) {
        setLongValue(Constants.P_MAS_CODE, value);
    }

    /**
     * <p>setMaritalStatusCode.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setMaritalStatusCode(String value) {
        setStringValue(Constants.P_MAS_CODE_PUB, value);
    }

    /**
     * <p>setSex.</p>
     *
     * @param value a {@link java.lang.Character} object.
     */
    public void setSex(Character value) {
        setCharacterValue(Constants.P_ADR_SEX, value);
    }

    /**
     * <p>setBirthDate.</p>
     *
     * @param value a {@link java.util.Date} object.
     */
    public void setBirthDate(Date value) {
        setDateValue(Constants.P_ADR_BIRTHDT, value);
    }

    /**
     * <p>setFlagEmployee.</p>
     *
     * @param value a {@link java.lang.Boolean} object.
     */
    public void setFlagEmployee(Boolean value) {
        setBooleanValue(Constants.P_ADR_EMPLOYEE, value);
    }

    /**
     * <p>setFlagForward.</p>
     *
     * @param value a {@link java.lang.Boolean} object.
     */
    public void setFlagForward(Boolean value) {
        setBooleanValue(Constants.P_ADR_FORWARD, value);
    }

    /**
     * <p>setFlagUrgent.</p>
     *
     * @param value a {@link java.lang.Boolean} object.
     */
    public void setFlagUrgent(Boolean value) {
        setBooleanValue(Constants.P_ADR_URGENT, value);
    }

    /**
     * <p>setFlagTempBillingOverridden.</p>
     *
     * @param value a {@link java.lang.Boolean} object.
     */
    public void setFlagTempBillingOverridden(Boolean value) {
        setBooleanValue(Constants.P_ADR_TEMPBILL_OVERRIDDEN, value);
    }

    /**
     * <p>setFlagAdrJurTaxOverriden.</p>
     *
     * @param value a {@link java.lang.Boolean} object.
     */
    public void setFlagAdrJurTaxOverriden(Boolean value) {
        setBooleanValue(Constants.P_ADR_JUR_TAX_OVERRIDDEN, value);
    }

    /**
     * <p>setJobDescription.</p>
     *
     * @param jbdes a {@link java.lang.String} object.
     */
    public void setJobDescription(String jbdes) {
        setStringValue(Constants.P_ADR_JBDES, jbdes);
    }

    // Utilise par ADRESS.READ en Parametre d'entree, search for one of
    // ADR_ROLES.
    // *=========================================================
    /**
     * <p>getAddressReadRole.</p>
     *
     * @return a char.
     */
    public char getAddressReadRole() {
        return getCharacterValue(Constants.P_ADR_TYPE);
    }

    /**
     * <p>setAddressReadRole.</p>
     *
     * @param value a char.
     */
    public void setAddressReadRole(char value) {
        setCharacterValue(Constants.P_ADR_TYPE, value);
    }

    /**
     * <p>getCustomerAddressType.</p>
     *
     * @return a char.
     */
    public char getCustomerAddressType() {
        return getCharacterValue(Constants.P_ADR_CUSTTYPE);
    }

    /**
     * <p>setCustomerAddressType.</p>
     *
     * @param value a char.
     */
    public void setCustomerAddressType(char value) {
        setCharacterValue(Constants.P_ADR_CUSTTYPE, value);
    }

    // Retourne par ADDRESS.READ MAIS PAS PAR ADDRESSES.READ
    // *=========================================================
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
     * @param customerId a {@link java.lang.Long} object.
     */
    public void setCustomerId(Long customerId) {
        setLongValue(Constants.P_CS_ID, customerId);
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
     * @param customerIdPub a {@link java.lang.String} object.
     */
    public void setCustomerIdPub(String customerIdPub) {
        setStringValue(Constants.P_CS_ID_PUB, customerIdPub);
    }

    /**
     * <p>setNationalOrganisationIdentifier.</p>
     *
     * @param nationalID a {@link java.lang.String} object.
     */
    public void setNationalOrganisationIdentifier(String nationalID) {
        setStringValue(Constants.P_ADR_COMPNO, nationalID);
    }

    /**
     * <p>getNationalOrganisationIdentifier (ADR_COMPNO).</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getNationalOrganisationIdentifier() {
        return getStringValue(Constants.P_ADR_COMPNO);
    }

    /**
     * <p>setNationalCard.</p>
     *
     * @param natId a {@link java.lang.String} object.
     */
    public void setNationalCard(String natId) {
        setStringValue(Constants.P_ADR_IDNO, natId);
    }

    /**
     * <p>getNationalCard (ADR_IDNO).</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getNationalCard() {
        return getStringValue(Constants.P_ADR_IDNO);
    }

    // === PROPERTIES TO CONVERT (by Lookup Services) between EBS and SOI ===

    /**
     * <p>Getter for the field <code>ebsTitle</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getEbsTitle() {
        return ebsTitle;
    }

    /**
     * <p>Setter for the field <code>ebsTitle</code>.</p>
     *
     * @param ebsTitle a {@link java.lang.String} object.
     */
    public void setEbsTitle(String ebsTitle) {
        this.ebsTitle = ebsTitle;
    }

    /**
     * <p>Getter for the field <code>ebsCountryCode</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getEbsCountryCode() {
        return ebsCountryCode;
    }

    /**
     * <p>Setter for the field <code>ebsCountryCode</code>.</p>
     *
     * @param ebsCountryCode a {@link java.lang.String} object.
     */
    public void setEbsCountryCode(String ebsCountryCode) {
        this.ebsCountryCode = ebsCountryCode;
    }

    /**
     * <p>Getter for the field <code>ebsCountryName</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getEbsCountryName() {
        return ebsCountryName;
    }

    /**
     * <p>Setter for the field <code>ebsCountryName</code>.</p>
     *
     * @param ebsCountryName a {@link java.lang.String} object.
     */
    public void setEbsCountryName(String ebsCountryName) {
        this.ebsCountryName = ebsCountryName;
    }

    /**
     * <p>getEbsMaritalStatus.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getEbsMaritalStatus() {
        return maritalStatus;
    }

    /**
     * <p>setEbsMaritalStatus.</p>
     *
     * @param maritalStatus a {@link java.lang.String} object.
     */
    public void setEbsMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    /**
     * <p>getEbsLanguageCode.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getEbsLanguageCode() {
        return languageCode;
    }

    /**
     * <p>setEbsLanguageCode.</p>
     *
     * @param languageCode a {@link java.lang.String} object.
     */
    public void setEbsLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    
    /**
     * <p>getNationalId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getNationalIdTypeCode() {
        return getLongValue(Constants.P_IDTYPE_CODE);
    }
    /**
     * <p>setNationalId.</p>
     *
     * @param value a {@link java.lang.Long} object.
     */
    public void setNationalIdTypeCode(Long value) {
        setLongValue(Constants.P_IDTYPE_CODE, value);
    }
    // === PROPERTIES TO CONVERT (by Lookup Services) between EBS and SOI ===
}
