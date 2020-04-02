package com.orange.bscs.model;

import java.util.Date;

import com.orange.bscs.model.businesspartner.BSCSAddress;

/**
 * Model used for result of ADDRESS.READ command and input of ADDRESS.WRITE
 * command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsAddress {

    protected BSCSAddress address;

    public BscsAddress(BSCSAddress address) {
        this.address = address;
    }

    public BscsAddress() {
        this.address = new BSCSAddress();
    }

    public BSCSAddress getBscsModel() {
        return address;
    }

    public abstract String getCustomerId();

    public abstract void setCustomerId(String customerId);

    public abstract String getFirstName();

    public abstract void setFirstName(String name);

    public abstract String getLastName();

    public abstract void setLastName(String name);

    public abstract Long getMaritalStatusId();

    public abstract void setMaritalStatusId(Long status);

    public abstract Character getSex();

    public abstract void setSex(Character sex);

    public abstract Date getBirthDate();

    public abstract void setBirthDate(Date birthDate);

    public abstract Long getNationalityId();

    public abstract void setNationalityId(Long nationality);

    public abstract Long getTitleId();

    public abstract void setTitleId(Long titleId);

    public abstract String getEmail();

    public abstract void setEmail(String emailAddress);

    public abstract String getJobDescription();

    public abstract void setJobDescription(String job);

    public abstract String getTelephone1();

    public abstract void setTelephone1(String number);

    public abstract String getStreet();

    public abstract void setStreet(String street);

    public abstract Long getCountryId();

    public abstract void setCountryId(Long countryId);

    public abstract String getCity();

    public abstract void setCity(String city);

    public abstract String getStreetNumber();

    public abstract void setStreetNumber(String number);

    public abstract String getPostalCode();

    public abstract void setPostalCode(String postcode);

    public abstract String getState();

    public abstract void setState(String state);

    public abstract String getNationalCard();

    public abstract void setNationalCard(String cardNumber);

    public abstract Long getNationalIdTypeCode();

    public abstract void setNationalIdTypeCode(Long idType);

    public abstract void setSequenceNumber(Long seqNo);

    public abstract void setCustomerNumericId(Long customerId);

    public abstract String getNote1();

    public abstract void setNote1(String value);

    public abstract String getCompanyName();

    public abstract void setCompanyName(String companyName);

}
