package com.orange.bscs.model;

import java.util.Date;
import java.util.Objects;

import com.orange.bscs.model.businesspartner.BSCSAddress;

public class BscsAddressV8 extends BscsAddress {

    public BscsAddressV8() {
        super();
    }

    public BscsAddressV8(BSCSAddress address) {
        super(address);
    }

    @Override
    public String getCustomerId() {
        // CS_ID
        return Objects.toString(address.getCustomerId(), null);
    }

    @Override
    public void setCustomerId(String customerId) {
        // CS_ID
        address.setCustomerId(Long.valueOf(customerId));
    }

    @Override
    public void setCustomerNumericId(Long customerId) {
        // CS_ID
        address.setCustomerId(customerId);
    }

    @Override
    public String getFirstName() {
        // ADR_FNAME
        return address.getFirstName();
    }

    @Override
    public void setFirstName(String name) {
        // ADR_FNAME
        address.setFirstName(name);
    }

    @Override
    public String getLastName() {
        // ADR_LNAME
        return address.getLastName();
    }

    @Override
    public void setLastName(String name) {
        // ADR_LNAME
        address.setLastName(name);
    }

    @Override
    public Long getMaritalStatusId() {
        // MAS_CODE
        return address.getMaritalStatusId();
    }

    @Override
    public void setMaritalStatusId(Long status) {
        // MAS_CODE
        address.setMaritalStatusId(status);
    }

    @Override
    public Character getSex() {
        // ADR_SEX
        return address.getSex();
    }

    @Override
    public void setSex(Character sex) {
        // ADR_SEX
        address.setSex(sex);
    }

    @Override
    public Date getBirthDate() {
        // ADR_BIRTHDT
        return address.getBirthDate();
    }

    @Override
    public void setBirthDate(Date birthDate) {
        // ADR_BIRTHDT
        address.setBirthDate(birthDate);
    }

    @Override
    public Long getNationalityId() {
        // ADR_NATIONALITY
        return address.getNationalityId();
    }

    @Override
    public void setNationalityId(Long nationality) {
        // ADR_NATIONALITY
        address.setNationalityId(nationality);
    }

    @Override
    public Long getTitleId() {
        // TTL_ID
        return address.getTitleId();
    }

    @Override
    public void setTitleId(Long titleId) {
        // TTL_ID
        address.setTitleId(titleId);
    }

    @Override
    public String getEmail() {
        // ADR_EMAIL
        return address.getEmail();
    }

    @Override
    public void setEmail(String emailAddress) {
        // ADR_EMAIL
        address.setEmail(emailAddress);
    }

    @Override
    public String getJobDescription() {
        // ADR_JBDES
        return address.getJobDescription();
    }

    @Override
    public void setJobDescription(String job) {
        // ADR_JBDES
        address.setJobDescription(job);
    }

    @Override
    public String getTelephone1() {
        // ADR_PHN1
        return address.getTelephone1();
    }

    @Override
    public void setTelephone1(String number) {
        // ADR_PHN1
        address.setTelephone1(number);
    }

    @Override
    public String getStreet() {
        // ADR_STREET
        return address.getStreet();
    }

    @Override
    public void setStreet(String street) {
        // ADR_STREET
        address.setStreet(street);
    }

    @Override
    public Long getCountryId() {
        // COUNTRY_ID
        return address.getCountryId();
    }

    @Override
    public void setCountryId(Long countryId) {
        // COUNTRY_ID
        address.setCountryId(countryId);
    }

    @Override
    public String getCity() {
        // ADR_CITY
        return address.getCity();
    }

    @Override
    public void setCity(String city) {
        // ADR_CITY
        address.setCity(city);
    }

    @Override
    public String getStreetNumber() {
        // ADR_STREETNO
        return address.getStreetNumber();
    }

    @Override
    public void setStreetNumber(String number) {
        // ADR_STREETNO
        address.setStreetNumber(number);
    }

    @Override
    public String getPostalCode() {
        // ADR_ZIP
        return address.getPostalCode();
    }

    @Override
    public void setPostalCode(String postcode) {
        // ADR_ZIP
        address.setPostalCode(postcode);
    }

    @Override
    public String getState() {
        // ADR_STATE
        return address.getState();
    }

    @Override
    public void setState(String state) {
        // ADR_STATE
        address.setState(state);
    }

    @Override
    public String getNationalCard() {
        // ADR_IDNO
        return address.getNationalCard();
    }

    @Override
    public void setNationalCard(String cardNumber) {
        // ADR_IDNO
        address.setNationalCard(cardNumber);
    }

    @Override
    public Long getNationalIdTypeCode() {
        // IDTYPE_CODE
        return address.getNationalIdTypeCode();
    }

    @Override
    public void setNationalIdTypeCode(Long idType) {
        // IDTYPE_CODE
        address.setNationalIdTypeCode(idType);
    }

    @Override
    public void setSequenceNumber(Long seqNo) {
        // ADR_SEQ
        address.setSequenceNumber(seqNo);
    }

    @Override
    public String getNote1() {
        // ADR_NOTE1
        return address.getAddrNote1();
    }

    @Override
    public void setNote1(String value) {
        // ADR_NOTE1
        address.setAddrNote1(value);
    }

    @Override
    public String getCompanyName() {
        // ADR_NAME
        return address.getAddressName();
    }

    @Override
    public void setCompanyName(String companyName) {
        // ADR_NAME
        address.setAddressName(companyName);
    }

}
