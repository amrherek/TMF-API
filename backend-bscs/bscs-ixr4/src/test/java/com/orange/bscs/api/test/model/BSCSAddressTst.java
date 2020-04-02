package com.orange.bscs.api.test.model;

import java.util.Date;

import com.orange.bscs.api.model.BSCSModel;


/*
ADDRESSES.READ (542l)

BSCSModel {
  logger=org.slf4j.impl.Log4jLoggerAdapter(com.orange.bscs.api.model.BSCSModel)
  svlObject=
CS_ID_PUB = CUST0000000470 : java.lang.String
CS_ID = 542 : java.lang.Long
LIST_OF_ALL_ADDRESSES = sub element : com.lhs.ccb.common.soiimpl.NamedValueContainerList
-[0]ADR_ZIP = 45000 : java.lang.String
-[0]ADR_FNAME = Marjorie : java.lang.String
...
-[1]ADR_STREET = 33 rue des acacias : java.lang.String
-[1]ADR_FORWARD = false : java.lang.Boolean
  }
  
 
ADDRESS.READ :   
BSCSModel {
  logger=org.slf4j.impl.Log4jLoggerAdapter(com.orange.bscs.api.model.BSCSModel)
  svlObject=
CS_ID_PUB = CUST0000000470 : java.lang.String
CS_ID = 542 : java.lang.Long
ADR_ZIP = 45000 : java.lang.String
ADR_FNAME = Marjorie : java.lang.String
ADR_CUSTTYPE = C : java.lang.Character
..
}
*/
public class BSCSAddressTst extends BSCSModel {

	// ===    PROPERTIES TO CONVERT (by Lookup Services) between EBS and SOI ===
	
	private String ebsTitle;
	private String ebsCountryCode;
	private String ebsCountryName;
	
	// ===    PROPERTIES TO CONVERT (by Lookup Services) between EBS and SOI ===
	
	
	/*
	public BSCSAddressTst(){
		super();
	}
	
	public BSCSAddressTst(SVLObject sVLObject) {
		this();
		setSVLObject(sVLObject);
	}

	public Long getSequenceNumber(){ return getLongValue(AddressEnum.SequenceNumber);}

	public Character getCustomerType(){ return getCharacterValue(AddressEnum.CustomerType);}
	public java.lang.String getAddressRoles(){ return getStringValue(AddressEnum.AddressRoles);}
	
	
	public java.lang.Long getTitleId(){ return getLongValue(AddressEnum.TitleId);}
	public java.lang.String getTitleCode(){ return getStringValue(AddressEnum.TitleCode);}
	public java.lang.String getFirstName(){ return getStringValue(AddressEnum.FirstName);}
	public java.lang.String getMidNames(){ return getStringValue(AddressEnum.MidNames);}
	public java.lang.String getLastName(){ return getStringValue(AddressEnum.LastName);}

	public java.lang.String getTelephone1(){ return getStringValue(AddressEnum.Telephone1);}
	public java.lang.String getTelephone2(){ return getStringValue(AddressEnum.Telephone2);}
	public java.lang.String getTelephoneMobile(){ return getStringValue(AddressEnum.TelephoneMobile);}
	public java.lang.String getFax(){ return getStringValue(AddressEnum.Fax);}
	public java.lang.String getEmail(){ return getStringValue(AddressEnum.Email);}
		
	public java.lang.Long getNationalityId(){ return getLongValue(AddressEnum.NationalityId);}
	public java.lang.String getNationalityCode(){ return getStringValue(AddressEnum.NationalityCode);}

	public java.lang.Long getLangueId(){ return getLongValue(AddressEnum.LangueId);}
	public java.lang.String getLangueCode(){ return getStringValue(AddressEnum.LangueCode);}
	
	public java.lang.String getStreetNumber(){ return getStringValue(AddressEnum.StreetNumber);}
	public java.lang.String getStreet(){ return getStringValue(AddressEnum.Street);}
	public java.lang.String getAddrNote1(){ return getStringValue(AddressEnum.AddrNote1);}
	public java.lang.String getAddrNote2(){ return getStringValue(AddressEnum.AddrNote2);}
	public java.lang.String getAddrNote3(){ return getStringValue(AddressEnum.AddrNote3);}
	public java.lang.String getPostalCode(){ return getStringValue(AddressEnum.PostalCode);}
	public java.lang.String getCity(){ return getStringValue(AddressEnum.City);}
	public java.lang.Long getCountryId(){ return getLongValue(AddressEnum.CountryId);}
	public java.lang.String getCountryCode(){ return getStringValue(AddressEnum.CountryCode);}
	
	public java.lang.String getDrivingLicense(){ return getStringValue(AddressEnum.DrivingLicense);}
	public java.lang.String getSocialNumber(){ return getStringValue(AddressEnum.SecuritySocialCard);}
	public java.lang.Long getMaritalStatusId(){ return getLongValue(AddressEnum.MaritalStatusId);}
	public java.lang.String getMaritalStatusCode(){ return getStringValue(AddressEnum.MaritalStatusCode);}
	public java.lang.Character getSex(){ return getCharacterValue(AddressEnum.Sex);}
	public Date getBirthDate(){ return getDateValue(AddressEnum.BirthDate);}
	
	public java.lang.Boolean getFlagEmployee(){ return getBooleanValue(AddressEnum.FlagEmployee);}
	public java.lang.Boolean getFlagForward(){ return getBooleanValue(AddressEnum.FlagForward);}
	public java.lang.Boolean getFlagUrgent(){ return getBooleanValue(AddressEnum.FlagUrgent);}
	public java.lang.Boolean getFlagTempBillingOverridden(){ return getBooleanValue(AddressEnum.FlagTempBillingOverridden);}
	public java.lang.Boolean getFlagADR_JUR_TAX_OVERRIDDEN(){ return getBooleanValue(AddressEnum.FlagADR_JUR_TAX_OVERRIDDEN);}


	// Setters
	public void setSequenceNumber(Long value){ setLongValue(AddressEnum.SequenceNumber,value);}

	public void setCustomerType(Character value) { setCharacterValue(AddressEnum.CustomerType,value);}
	public void setAddressRoles(String value) { setStringValue(AddressEnum.AddressRoles,value);}
	
	
	public void setTitleId(Long value) { setLongValue(AddressEnum.TitleId,value);}
	public void setTitleCode(String value) { setStringValue(AddressEnum.TitleCode,value);}
	public void setFirstName(String value) { setStringValue(AddressEnum.FirstName,value);}
	public void setMidNames(String value) { setStringValue(AddressEnum.MidNames,value);}
	public void setLastName(String value) { setStringValue(AddressEnum.LastName,value);}

	public void setTelephone1(String value) { setStringValue(AddressEnum.Telephone1,value);}
	public void setTelephone2(String value) { setStringValue(AddressEnum.Telephone2,value);}
	public void setTelephoneMobile(String value) { setStringValue(AddressEnum.TelephoneMobile,value);}
	public void setFax(String value) { setStringValue(AddressEnum.Fax,value);}
	public void setEmail(String value) { setStringValue(AddressEnum.Email,value);}
		
	public void setNationalityId(Long value) { setLongValue(AddressEnum.NationalityId,value);}
	public void setNationalityCode(String value) { setStringValue(AddressEnum.NationalityCode,value);}

	public void setLangueId(Long value) { setLongValue(AddressEnum.LangueId,value);}
	public void setLangueCode(String value) { setStringValue(AddressEnum.LangueCode,value);}
	
	public void setStreetNumber(String value) { setStringValue(AddressEnum.StreetNumber,value);}
	public void setStreet(String value) { setStringValue(AddressEnum.Street,value);}
	public void setAddrNote1(String value) { setStringValue(AddressEnum.AddrNote1,value);}
	public void setAddrNote2(String value) { setStringValue(AddressEnum.AddrNote2,value);}
	public void setAddrNote3(String value) { setStringValue(AddressEnum.AddrNote3,value);}
	public void setPostalCode(String value) { setStringValue(AddressEnum.PostalCode,value);}
	public void setCity(String value) { setStringValue(AddressEnum.City,value);}
	public void setCountryId(Long value) { setLongValue(AddressEnum.CountryId,value);}
	public void setCountryCode(String value) { setStringValue(AddressEnum.CountryCode,value);}
	
	public void setDrivingLicense(String value) { setStringValue(AddressEnum.DrivingLicense,value);}
	public void setSocialNumber(String value) { setStringValue(AddressEnum.SecuritySocialCard,value);}
	public void setMaritalStatusId(Long value) { setLongValue(AddressEnum.MaritalStatusId,value);}
	public void setMaritalStatusCode(String value) { setStringValue(AddressEnum.MaritalStatusCode,value);}
	public void setSex(Character value) { setCharacterValue(AddressEnum.Sex,value);}
	public void setBirthDate(Date value) { setDateValue(AddressEnum.BirthDate,value);}
	
	public void setFlagEmployee(Boolean value) { setBooleanValue(AddressEnum.FlagEmployee,value);}
	public void setFlagForward(Boolean value) { setBooleanValue(AddressEnum.FlagForward,value);}
	public void setFlagUrgent(Boolean value) { setBooleanValue(AddressEnum.FlagUrgent,value);}
	public void setFlagTempBillingOverridden(Boolean value) { setBooleanValue(AddressEnum.FlagTempBillingOverridden,value);}
	public void setFlagADR_JUR_TAX_OVERRIDDEN(Boolean value) { setBooleanValue(AddressEnum.FlagADR_JUR_TAX_OVERRIDDEN,value);}
	
	
	
	
	// Utilis� par ADRESS.READ en Param�tre d'entr�e
	//*=========================================================
	public char getCustomerAddressType(){ return getCharacterValue(CustomerEnum.AddressType);}
	public void setCustomerAddressType(char value){ setCharacterValue(CustomerEnum.AddressType, value);}
		
	
	// Retourn� par ADDRESS.READ MAIS PAS PAR ADDRESSES.READ
	//*=========================================================
	public Long getCustomerId() { return getLongValue(CustomerEnum.CustomerId);}
	public void setCustomerId(Long customerId) {
		setLongValue(CustomerEnum.CustomerId, customerId);
	}

	public String getCustomerIdPub() { return getStringValue(CustomerEnum.CustomerIdPub);}
	public void setCustomerIdPub(String customerIdPub) {
		setStringValue(CustomerEnum.CustomerIdPub, customerIdPub);
	}

	public void setNationalOrganisationIdentifier(String nationalID) {
		setStringValue(AddressEnum.NationalOrganisationIdentifier, nationalID);
	}
	public String getNationalOrganisationIdentifier() {
		return getStringValue(AddressEnum.NationalOrganisationIdentifier);
	}

	public void setPassport(String passport) {setStringValue(AddressEnum.Passport, passport);}
	public String getPassport() {return getStringValue(AddressEnum.Passport);}
	*/
	
	// ===    PROPERTIES TO CONVERT (by Lookup Services) between EBS and SOI ===

	public String getEbsTitle() {
		return ebsTitle;
	}

	public void setEbsTitle(String ebsTitle) {
		this.ebsTitle = ebsTitle;
	}

	public String getEbsCountryCode() {
		return ebsCountryCode;
	}

	public void setEbsCountryCode(String ebsCountryCode) {
		this.ebsCountryCode = ebsCountryCode;
	}

	public String getEbsCountryName() {
		return ebsCountryName;
	}

	public void setEbsCountryName(String ebsCountryName) {
		this.ebsCountryName = ebsCountryName;
	}


    public Long getCustomerId() { return getLongValue("CS_ID");}
    public void setCustomerId(long l) { setLongValue("CS_ID",l);}


    public String getCustomerIdPub() { return getStringValue("CS_ID_PUB");}
    public void setCustomerIdPub(String csidpub) { setStringValue("CS_ID_PUB", csidpub);}

    public Long getSequenceNumber() { return getLongValue("ADR_SEQ");}
    public void setSequenceNumber(long l) { setLongValue("ADR_SEQ", l);}

    public String getFirstName() { return getStringValue("ADR_FNAME"); }
    public void setFirstName(String fname) { setStringValue("ADR_FNAME", fname); }
    

    public String getLastName() { return getStringValue("ADR_LNAME"); }
    public void setLastName(String name) { setStringValue("ADR_LNAME", name); }

    public Date getBirthDate() { return getDateValue("ADR_BIRTHDATE"); }
    public void setBirthDate(Date date) { setDateValue("ADR_BIRTHDATE", date); }

    public String getPostalCode() { return getStringValue("ADR_ZIP");}
    public void setPostalCode(String zip) { setStringValue("ADR_ZIP", zip);}

    public String getCity() { return getStringValue("ADR_CITY");}
    public void setCity(String city) { setStringValue("ADR_CITY", city);}
        
    

	// ===    PROPERTIES TO CONVERT (by Lookup Services) between EBS and SOI ===
}
