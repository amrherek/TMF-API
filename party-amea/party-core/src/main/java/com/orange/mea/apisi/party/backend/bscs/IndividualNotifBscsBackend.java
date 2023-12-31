package com.orange.mea.apisi.party.backend.bscs;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.badrequest.BadParameterFormatException;
import com.orange.apibss.common.exceptions.badrequest.BadParameterValueException;
import com.orange.apibss.common.exceptions.badrequest.MissingParameterException;
import com.orange.apibss.common.exceptions.badrequest.NotFoundException;
import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.apibss.party.model.obw.Characteristic;
import com.orange.apibss.party.model.obw.CharacteristicNameEnum;
import com.orange.apibss.party.model.obw.ContactMedium;
import com.orange.apibss.party.model.obw.ContactMediumType;
import com.orange.apibss.party.model.obw.ExternalReference;
import com.orange.apibss.party.model.obw.Gender;
import com.orange.apibss.party.model.obw.IdentificationType;
import com.orange.apibss.party.model.obw.Individual;
import com.orange.apibss.party.model.obw.IndividualIdentification;
import com.orange.apibss.party.model.obw.MediumCharacteristic;
import com.orange.apibss.party.model.obw.RelatedParty;
import com.orange.apibss.party.model.obw.StatusEnum;
import com.orange.bscs.api.aop.TransactionalBscs;
import com.orange.bscs.api.exceptions.APIException;
import com.orange.bscs.api.model.exception.CMSException;
import com.orange.bscs.model.BscsAddress;
import com.orange.bscs.model.businesspartner.BSCSCustomer;
import com.orange.bscs.model.businesspartner.EnumAddressRole;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;
import com.orange.mea.apisi.party.backend.CreateIndividualBackend;
import com.orange.mea.apisi.party.backend.bscs.transformer.BscsAddressToIndividualTransformer;
import com.orange.mea.apisi.party.backend.bscs.transformer.BscsCustomerInfoToIndividualTransformer;
import com.orange.mea.apisi.party.backend.bscs.transformer.IndividualToBscsAddressTransformer;
import com.orange.mea.apisi.party.backend.bscs.transformer.IndividualToBscsCustomerInfoTransformer;

@Service
public class IndividualNotifBscsBackend
  implements CreateIndividualBackend
{
  @Autowired
  protected BscsIndividualService bscsIndividualService;
  @Autowired
  protected BscsAddressToIndividualTransformer bscsAddressToIndividualTransformer;
  @Autowired
  protected BscsCustomerInfoToIndividualTransformer bscsCustomerInfoToIndividualTransformer;
  @Autowired
  protected IndividualToBscsAddressTransformer individualToBscsAddressTransformer;
  @Autowired
  protected IndividualToBscsCustomerInfoTransformer individualToBscsCustomerInfoTransformer;
  protected boolean customerInfoNeeded = false;
  
  @TransactionalBscs
  public BSCSCustomer createIndividual(Individual individual)
    throws ApiException, BscsInvalidIdException, BscsInvalidFieldException, CMSException
  {
   
	  try{
		  
	  
	  boolean adressExists = false;
    List<ContactMedium> contactMediumList = individual.getContactMedium();
    if ((null != contactMediumList) && (!contactMediumList.isEmpty())) {
      for (ContactMedium tmfContactMedium : contactMediumList)
      {
        MediumCharacteristic medium = tmfContactMedium.getCharacteristic();
        if (null != medium) {
          adressExists = true;
        } else {
          throw new MissingParameterException("MediumCharacteristic");
        }
      }
    } else {
       throw new MissingParameterException("ContactMedium");
    }

    List<Characteristic> characteristics = individual.getCharacteristic();
    List<ExternalReference> extRef = individual.getExternalReference();
    String refCRM = null;
    String refCRMSet = null;
    if(extRef != null && extRef.size()>0)
    {
    	refCRM = extRef.get(0).getName();
    	refCRMSet = extRef.get(0).getExternalReferenceType();
    	if (refCRM ==null){
    		 throw new MissingParameterException("ExternalReference.name");
    	}
    	if (refCRMSet ==null){
   		 throw new MissingParameterException("ExternalReference.ExternalReferenceType");
    	}
    	if (refCRM.length()>15){
   		 throw new BadParameterFormatException("Max size for ExternalReference.name = 15 ");
    	}
  		if (refCRMSet.length()>5){
   		 throw new BadParameterFormatException("Max size for ExternalReference.ExternalReferenceType = 5 ");
  		}
    }
  //CUSTOMERS.SEARCH
	List<BSCSCustomer> customerResult=null;
    if( null!=refCRMSet && null !=refCRM ){
		customerResult = bscsIndividualService.customerSearch(refCRM, refCRMSet);
    }
    
	if(null!=customerResult && customerResult.size()> 0)
		throw new BadParameterValueException("Customer with ExternalReference :  " + refCRM+ " already exist in data base" );
    
    String ratePlan = null;
    String billCycle = null;
    String prgCode = null;
    String isPayResp = null;
    String job = null;
    String payMethod = null;
    Long rsCode = Long.valueOf(1L);
    String bankAccount = null;
    String bankAccountOwner = null;
    String bankCity = null;
    String bankCode = null;
    String bankCountry = null;
    String bankName = null;
    String bankProvince = null;
    String bankStreetName = null;
    String bankStreetNum = null;
    String bankZip = null;
    String bankZone = null;
    String creditCardCompany = null;
    String creditCardLimit = null;
    String creditCardExpirDate = null;
    String swiftCode = null;
    if ((characteristics != null) && characteristics.size() > 0) {
      for (Characteristic characteristic : characteristics) {
        if (StringUtils.isNotBlank(characteristic.getName().getValue()))
        {
          if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.OCCRATEPLAN.getValue())) {
            ratePlan = characteristic.getValue();
          } else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.BILLCYCLE.getValue())) {
            billCycle = characteristic.getValue();
          } else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.CATEGORYCODE.getValue())) {
            prgCode = characteristic.getValue();
          } else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.ISPAYMENTRESP.getValue())) {
            isPayResp = characteristic.getValue();
          } else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.OCCUPATION.getValue())) {
            job = characteristic.getValue();
          } else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.PAYMENTMETHOD.getValue())) {
            payMethod = characteristic.getValue();
          }else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.REGISTRATIONSTATUS.getValue())) {
          	  rsCode = Long.valueOf(characteristic.getValue());
          }
          /*PayMethod*/
          else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.PMETHACCOUNTNUMBER.getValue())) {
          	  bankAccount = characteristic.getValue();
          } else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.PMETHACCOUNTOWNER.getValue())) {
          	  bankAccountOwner = characteristic.getValue();
          } else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.PMETHBANKCITY.getValue())) {
          	  bankCity = characteristic.getValue();
          } else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.PMETHBANKCODE.getValue())) {
          	  bankCode = characteristic.getValue();
          }else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.PMETHBANKCOUNTRY.getValue())) {
          	  bankCountry = characteristic.getValue();
          }else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.PMETHBANKNAME.getValue())) {
          	  bankName = characteristic.getValue();
          }else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.PMETHBANKPROVINCE.getValue())) {
          	  bankProvince = characteristic.getValue();
          }else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.PMETHBANKSTREETNAME.getValue())) {
          	  bankStreetName = characteristic.getValue();
          }else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.PMETHBANKSTREETNUM.getValue())) {
          	  bankStreetNum = characteristic.getValue();
          }else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.PMETHBANKSTREETNUM.getValue())) {
          	  bankStreetNum = characteristic.getValue();
          }else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.PMETHBANKZIP.getValue())) {
          	  bankZip = characteristic.getValue();
          }else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.PMETHBANKZONE.getValue())) {
          	  bankZone = characteristic.getValue();
          }else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.PMETHCREDITCARDCOMPAGNY.getValue())) {
          	  creditCardCompany = characteristic.getValue();
          }else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.PMETHCREDITCARDLIMIT.getValue())) {
          	  creditCardLimit = characteristic.getValue();
          }else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.PMETHEXPIRATIONDATE.getValue())) {
          	  creditCardExpirDate = characteristic.getValue();
          }else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.PMETHSWIFTCODE.getValue())) {
          	  swiftCode = characteristic.getValue();
          }
          /*End PayMethod*/
        }
        else {
          throw new MissingParameterException("Characteristic.name");
        }
      }
    } else {
        throw new MissingParameterException("Characteristic");
      }
    if (null==ratePlan)
    	throw new MissingParameterException("Characteristic.occRatePlan");
    if (null==billCycle)
    	throw new MissingParameterException("Characteristic.billCycle");
    if (null==prgCode)
    	throw new MissingParameterException("Characteristic.categoryCode");
    if (null==payMethod)
    	throw new MissingParameterException("Characteristic.paymentMethod");
    
      String key = "INITIATOR";
      boolean paymentResp = true;
      Long custCat = null;

      String role = null;
      if (null != individual.getRelatedParty() && !individual.getRelatedParty().isEmpty())
    		  {
		      if ((null != ((RelatedParty)individual.getRelatedParty().get(0)).getRole()) 
		    		  && (((RelatedParty)individual.getRelatedParty().get(0)).getRole().getValue().length() > 0)) {
		        role = ((RelatedParty)individual.getRelatedParty().get(0)).getRole().getValue();
		      }
    		  }
      BSCSCustomer bscscustomer = this.bscsIndividualService.executeCustomerNewForePostPartyIndividual(billCycle, prgCode, ratePlan, null, 
        Boolean.valueOf(paymentResp), refCRM, refCRMSet, role);
      

      String csIdPub = bscscustomer.getCustomerIDPub();
      this.bscsIndividualService.executePaymentArrangementWriteForPostPartyIndividual(csIdPub, new Long(0L), payMethod,bankAccount,
    		  bankAccountOwner,bankCity,bankCode,bankCountry,bankName, bankProvince, bankStreetName,bankStreetNum,bankZip,bankZone,
    		  creditCardCompany,creditCardLimit,creditCardExpirDate,swiftCode, false, false);

      
      List<ContactMedium> contactMeduimList = individual.getContactMedium();
      if ((null != contactMeduimList) && (!contactMeduimList.isEmpty())) {
          String idNo = null;
          Long idType = null;
          String licence = null;
          String social = null;
          Character adrCsType = null;
          String adrStreetNo = null;
          String adrCity = null;
          String adrStreet = null;
          String adrZip = null;
          String countryIdPub = null;
          String adrRoles = null;
          String fax = null;
          String tel = null;
          String email = null;       
          String maritalStatus= null;
          Date birthDate = null;
          String givenName = null;
          String familyName = null;
          String middleName = null;
          Character gender = null;
          String title = null;
          String nationality = null;
          String adrState = null ;
        for (ContactMedium contactMedium : contactMeduimList)
        {
          if (null != individual.getIndividualIdentification())
          {
            List<IndividualIdentification> individualIdentificationList = individual.getIndividualIdentification();
            if ((null != individualIdentificationList) && (!individualIdentificationList.isEmpty())) {
              for (IndividualIdentification tmfIndividualIdentification : individualIdentificationList) {
                if ((null != tmfIndividualIdentification.getIdentificationType().getValue()) && 
                  (null != tmfIndividualIdentification.getIdentificationId()) && 
                  (tmfIndividualIdentification.getIdentificationId().length() > 0))
                {
                  String type = tmfIndividualIdentification.getIdentificationType().getValue();
                  if ((!type.equalsIgnoreCase(IdentificationType.DRIVERLICENCE.getValue())) && 
                    (!type.equalsIgnoreCase(IdentificationType.SOCIALSECURITYNUMBER.getValue()))) {
                    idNo = tmfIndividualIdentification.getIdentificationId();
                    if (type.equalsIgnoreCase(IdentificationType.IDENTITYCARD.getValue())){
                    	idType= 1L;
                    }else if (type.equalsIgnoreCase(IdentificationType.PASSPORT.getValue())){
                    	idType= 2L ;
                    }
                  }
                  if (type.equalsIgnoreCase(IdentificationType.DRIVERLICENCE.getValue())) {
                    licence = tmfIndividualIdentification.getIdentificationId();
                  }
                  if (type.equalsIgnoreCase(IdentificationType.SOCIALSECURITYNUMBER.getValue())) {
                    social = tmfIndividualIdentification.getIdentificationId();
                  }
                }
              }
            }
          }
          
          if (null != individual.getMaritalStatus())
          {
        	  maritalStatus = individual.getMaritalStatus().getValue();
          }
          
          adrRoles = "B";
          if ((null != contactMedium.getMediumType()) && 
            (contactMedium.getMediumType().getValue().equalsIgnoreCase(ContactMediumType.POSTALADDRESS.getValue())))
          {
            adrCsType = Character.valueOf('C');
            if (null != contactMedium.getCharacteristic().getStateOrProvince()) {
            adrState = contactMedium.getCharacteristic().getStateOrProvince();
            }
            if (null != contactMedium.getCharacteristic().getPlot()) {
                adrStreetNo = contactMedium.getCharacteristic().getPlot();
                }
            if (null != contactMedium.getCharacteristic().getCity()) {
              adrCity = contactMedium.getCharacteristic().getCity();
            }
            if (null != contactMedium.getCharacteristic().getStreet1()) {
              adrStreet = contactMedium.getCharacteristic().getStreet1();
            }
            if (null != contactMedium.getCharacteristic().getPostcode()) {
              adrZip = contactMedium.getCharacteristic().getPostcode();
            }
            if (null != contactMedium.getCharacteristic().getCountry()) {
              countryIdPub = contactMedium.getCharacteristic().getCountry();
            }
          }
          if ((null != contactMedium.getMediumType()) && 
            (contactMedium.getMediumType().getValue().equalsIgnoreCase(ContactMediumType.EMAILADDRESS.getValue())) && 
            (null != contactMedium.getCharacteristic().getEmailAddress())) {
            email = contactMedium.getCharacteristic().getEmailAddress();
          }
          if ((null != contactMedium.getMediumType()) && 
            (contactMedium.getMediumType().getValue().equalsIgnoreCase(ContactMediumType.FAXNUMBER.getValue())) && 
            (null != contactMedium.getCharacteristic().getFaxNumber())) {
            fax = contactMedium.getCharacteristic().getFaxNumber();
          }
          if ((null != contactMedium.getMediumType()) && 
            (contactMedium.getMediumType().getValue().equalsIgnoreCase(ContactMediumType.PHONENUMBER.getValue())) && 
            (null != contactMedium.getCharacteristic().getPhoneNumber())) {
            tel = contactMedium.getCharacteristic().getPhoneNumber();
          }

          if ((null != individual.getGivenName()) && (individual.getGivenName().length() > 0)) {
            givenName = individual.getGivenName();
          }
          if ((null != individual.getBirthDate())) {
        	  birthDate = individual.getBirthDate().toDate();
            }

          if ((null != individual.getFamilyName()) && (individual.getFamilyName().length() > 0)) {
            familyName = individual.getFamilyName();
          }

          if (null != individual.getGender())
          {
            if (individual.getGender().getValue().equalsIgnoreCase(Gender.MALE.getValue())) {
              gender = Character.valueOf('M');
            }
            if (individual.getGender().getValue().equalsIgnoreCase(Gender.FEMALE.getValue())) {
              gender = Character.valueOf('F');
            } else {
              gender = Character.valueOf('M');
            }
          }

          if (null != individual.getTitle()) {
            title = individual.getTitle().getValue().toUpperCase();
          }
          
          if ((null != individual.getNationality()) && (individual.getNationality().length() > 0)) {
            nationality = individual.getNationality();
          }
        }
          this.bscsIndividualService.executeAddressWriteForPostParty(csIdPub, adrCsType, new Long(0L), givenName, familyName, middleName, adrStreetNo, adrCity, gender, adrStreet, adrZip, countryIdPub, job, birthDate, title, nationality, adrRoles, licence, social, idNo, idType, email, fax, tel, null, null, maritalStatus, adrState, false);
        
      }
      Character csStatus = Character.valueOf('a');
      if (null != individual.getStatus())
      {
        if (individual.getStatus().getValue().equalsIgnoreCase(StatusEnum.INITIALIZED.getValue())) {
        	csStatus = Character.valueOf('i');
        }
        if (individual.getStatus().getValue().equalsIgnoreCase(StatusEnum.VALIDATED.getValue())) {
        	csStatus = Character.valueOf('a');
        }
        if (individual.getStatus().getValue().equalsIgnoreCase(StatusEnum.DECEASED.getValue())) {
        	csStatus = Character.valueOf('d');
        }
      }

      this.bscsIndividualService.executeCustomerWriteForPostPartyIndividual(csIdPub, csStatus, rsCode, ratePlan, custCat, null, null, prgCode, billCycle);
      

      return bscscustomer;
	  }catch (ApiException e ){
		  throw e ;
	  }catch (Exception e ){
		  throw new TechnicalException(e.getMessage());
	  }
   
  }
  
  
  //************Patch Individual
  
  @TransactionalBscs
  public Individual patchIndividual(Individual individual) 
			throws APIException, ApiException {
		
	  try{
	  String csIdPub=null;

			//CUSTOMERS.SEARCH
			BSCSCustomer customerResult=null;
			String externalId1=null;
			String externalSetId1=null;
			  List<ExternalReference> extRef = individual.getExternalReference();
			    if(extRef != null && extRef.size()>0)
			    {
			    	externalId1 = extRef.get(0).getName();
			    	externalSetId1 = extRef.get(0).getExternalReferenceType();
			    	if (externalId1 ==null){
			    		 throw new MissingParameterException("ExternalReference.name");
			    	}
			    	if (externalSetId1 ==null){
			   		 throw new MissingParameterException("ExternalReference.ExternalReferenceType");
			    	}
			    	if (externalId1.length()>15){
			    		 throw new BadParameterFormatException("Max size for ExternalReference.name = 15 ");
			    	}
			   		if (externalSetId1.length()!= 5){
			    		 throw new BadParameterFormatException("ExternalReference.ExternalReferenceType should countain 5 characters");
			    	}
			   	
			    }

			if( null!=individual.getId() && individual.getId().length()>0 )
				customerResult = bscsIndividualService.customerRead(individual.getId());
			else
				throw new MissingParameterException("individual.id");
			//CUSTOMERS.SEARCH RESULT MAPPING
			if(null==customerResult)
				throw new NotFoundException("CUSTOMER NOT FOUND : id = " + individual.getId().toString());
			csIdPub = customerResult.getCustomerIDPub();

			
			//CUSTOMER.WRITE

		      Character csStatus = null;
		      if (null != individual.getStatus())
		      {
		        if (individual.getStatus().getValue().equalsIgnoreCase(StatusEnum.INITIALIZED.getValue())) {
		        	csStatus = Character.valueOf('i');
		        }
		        if (individual.getStatus().getValue().equalsIgnoreCase(StatusEnum.VALIDATED.getValue())) {
		        	csStatus = Character.valueOf('a');
		        }
		        if (individual.getStatus().getValue().equalsIgnoreCase(StatusEnum.DECEASED.getValue())) {
		        	csStatus = Character.valueOf('d');
		        }
				if(!(individual.getStatus().getValue().equalsIgnoreCase(StatusEnum.INITIALIZED.getValue())
						||individual.getStatus().getValue().equalsIgnoreCase(StatusEnum.VALIDATED.getValue())
						||individual.getStatus().getValue().equalsIgnoreCase(StatusEnum.DECEASED.getValue())))
					throw new BadParameterValueException("WRONG STATUS VALUE : " + individual.getStatus().getValue()); 
			}
		      List<Characteristic> characteristics = individual.getCharacteristic();
		      String maritalStatus= null;
		      String ratePlan = null;
		      String billCycle = null;
		      String prgCode = null;
		      String isPayResp = null;
		      String job = null;
		      String payMethod = null;
			 Long rsCode = null;
			 String bankAccount = null;
			    String bankAccountOwner = null;
			    String bankCity = null;
			    String bankCode = null;
			    String bankCountry = null;
			    String bankName = null;
			    String bankProvince = null;
			    String bankStreetName = null;
			    String bankStreetNum = null;
			    String bankZip = null;
			    String bankZone = null;
			    String creditCardCompany = null;
			    String creditCardLimit = null;
			    String creditCardExpirDate = null;
			    String swiftCode = null;
		      if ((characteristics != null) && characteristics.size() > 0) {
		        for (Characteristic characteristic : characteristics) {
		          if (StringUtils.isNotBlank(characteristic.getName().getValue()))
		          {
		            if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.OCCRATEPLAN.getValue())) {
		              ratePlan = characteristic.getValue();
		            } else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.BILLCYCLE.getValue())) {
		              billCycle = characteristic.getValue();
		            } else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.CATEGORYCODE.getValue())) {
		              prgCode = characteristic.getValue();
		            } else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.ISPAYMENTRESP.getValue())) {
		              isPayResp = characteristic.getValue();
		            } else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.OCCUPATION.getValue())) {
		              job = characteristic.getValue();
		            } else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.PAYMENTMETHOD.getValue())) {
		              payMethod = characteristic.getValue();
		            } else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.REGISTRATIONSTATUS.getValue())) {
			          	  if (null !=csStatus)
			          		  rsCode = Long.valueOf(characteristic.getValue());
			        }
		            /*PayMethod*/
		            else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.PMETHACCOUNTNUMBER.getValue())) {
		            	  bankAccount = characteristic.getValue();
		            } else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.PMETHACCOUNTOWNER.getValue())) {
		            	  bankAccountOwner = characteristic.getValue();
		            } else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.PMETHBANKCITY.getValue())) {
		            	  bankCity = characteristic.getValue();
		            } else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.PMETHBANKCODE.getValue())) {
		            	  bankCode = characteristic.getValue();
		            }else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.PMETHBANKCOUNTRY.getValue())) {
		            	  bankCountry = characteristic.getValue();
		            }else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.PMETHBANKNAME.getValue())) {
		            	  bankName = characteristic.getValue();
		            }else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.PMETHBANKPROVINCE.getValue())) {
		            	  bankProvince = characteristic.getValue();
		            }else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.PMETHBANKSTREETNAME.getValue())) {
		            	  bankStreetName = characteristic.getValue();
		            }else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.PMETHBANKSTREETNUM.getValue())) {
		            	  bankStreetNum = characteristic.getValue();
		            }else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.PMETHBANKSTREETNUM.getValue())) {
		            	  bankStreetNum = characteristic.getValue();
		            }else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.PMETHBANKZIP.getValue())) {
		            	  bankZip = characteristic.getValue();
		            }else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.PMETHBANKZONE.getValue())) {
		            	  bankZone = characteristic.getValue();
		            }else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.PMETHCREDITCARDCOMPAGNY.getValue())) {
		            	  creditCardCompany = characteristic.getValue();
		            }else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.PMETHCREDITCARDLIMIT.getValue())) {
		            	  creditCardLimit = characteristic.getValue();
		            }else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.PMETHEXPIRATIONDATE.getValue())) {
		            	  creditCardExpirDate = characteristic.getValue();
		            }else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.PMETHSWIFTCODE.getValue())) {
		            	  swiftCode = characteristic.getValue();
		            }
		            /*End PayMethod*/
		          }
		        }
		      }
		      if (null !=csStatus && null ==rsCode)
		    	  throw new MissingParameterException("Characteristic.registrationStatus");
		      

			String role=null;

			this.bscsIndividualService.executeCustomerWriteForPostPartyIndividual(csIdPub, csStatus, rsCode, ratePlan, null,externalId1, externalSetId1, prgCode, billCycle);
			//ADDRESS

			BscsAddress oldbadress = this.bscsIndividualService.executeAddressReadForPatchParty(csIdPub, EnumAddressRole.BILL);
			
			 List<ContactMedium> contactMeduimList = individual.getContactMedium();
			 if ((null != contactMeduimList) && (!contactMeduimList.isEmpty())) {
		          String idNo = null;
		          Long idType = null;
		          String licence = null;
		          String social = null;
		          Character adrCsType = null;
		          String adrStreetNo = null;
		          String adrCity = null;
		          String adrStreet = null;
		          String adrZip = null;
		          String countryIdPub = null;
		          String adrRoles = null;
		          String fax = null;
		          String tel = null;
		          String email = null;
		          Date birthDate = null;
		          String givenName = null;
		          String familyName = null;
		          String middleName = null;
		          Character gender = null;
		          String title = null;
		          String nationality = null;
		          String adrState = null;
					 
			        for (ContactMedium contactMedium : contactMeduimList)
			        {
			          if (null != individual.getIndividualIdentification())
			          {
			            List<IndividualIdentification> individualIdentificationList = individual.getIndividualIdentification();
			            if ((null != individualIdentificationList) && (!individualIdentificationList.isEmpty())) {
			              for (IndividualIdentification tmfIndividualIdentification : individualIdentificationList) {
			                if ((null != tmfIndividualIdentification.getIdentificationType().getValue()) && 
			                  (null != tmfIndividualIdentification.getIdentificationId()) && 
			                  (tmfIndividualIdentification.getIdentificationId().length() > 0))
			                {
			                  String type = tmfIndividualIdentification.getIdentificationType().getValue();
			                  if ((!type.equalsIgnoreCase(IdentificationType.DRIVERLICENCE.getValue())) && 
			                    (!type.equalsIgnoreCase(IdentificationType.SOCIALSECURITYNUMBER.getValue()))) {
			                    idNo = tmfIndividualIdentification.getIdentificationId();
			                    if (type.equalsIgnoreCase(IdentificationType.IDENTITYCARD.getValue())){
			                    	idType= 1L;
			                    }else if (type.equalsIgnoreCase(IdentificationType.PASSPORT.getValue())){
			                    	idType= 2L ;
			                    }
					            
			                  }
			                  if (type.equalsIgnoreCase(IdentificationType.DRIVERLICENCE.getValue())) {
			                    licence = tmfIndividualIdentification.getIdentificationId();
			                  }
			                  if (type.equalsIgnoreCase(IdentificationType.SOCIALSECURITYNUMBER.getValue())) {
			                    social = tmfIndividualIdentification.getIdentificationId();
			                  }
			                }
			              }
			            }
			          }
			          
			          adrRoles = "B";
			          if ((null != contactMedium.getMediumType()) && 
			            (contactMedium.getMediumType().getValue().equalsIgnoreCase(ContactMediumType.POSTALADDRESS.getValue())))
			          {
			            adrCsType = Character.valueOf('C');
			            if (null != contactMedium.getCharacteristic().getStateOrProvince()) {
			                adrState = contactMedium.getCharacteristic().getStateOrProvince();
			                }
			                if (null != contactMedium.getCharacteristic().getPlot()) {
			                    adrStreetNo = contactMedium.getCharacteristic().getPlot();
			                    }
			            if (null != contactMedium.getCharacteristic().getCity()) {
			              adrCity = contactMedium.getCharacteristic().getCity();
			            }
			            if (null != contactMedium.getCharacteristic().getStreet1()) {
			              adrStreet = contactMedium.getCharacteristic().getStreet1();
			            }
			            if (null != contactMedium.getCharacteristic().getPostcode()) {
			              adrZip = contactMedium.getCharacteristic().getPostcode();
			            }
			            if (null != contactMedium.getCharacteristic().getCountry()) {
			              countryIdPub = contactMedium.getCharacteristic().getCountry();
			            }
			          }
			          if ((null != contactMedium.getMediumType()) && 
			            (contactMedium.getMediumType().getValue().equalsIgnoreCase(ContactMediumType.EMAILADDRESS.getValue())) && 
			            (null != contactMedium.getCharacteristic().getEmailAddress())) {
			            email = contactMedium.getCharacteristic().getEmailAddress();
			          }
			          if ((null != contactMedium.getMediumType()) && 
			            (contactMedium.getMediumType().getValue().equalsIgnoreCase(ContactMediumType.FAXNUMBER.getValue())) && 
			            (null != contactMedium.getCharacteristic().getFaxNumber())) {
			            fax = contactMedium.getCharacteristic().getFaxNumber();
			          }
			          if ((null != contactMedium.getMediumType()) && 
			            (contactMedium.getMediumType().getValue().equalsIgnoreCase(ContactMediumType.PHONENUMBER.getValue())) && 
			            (null != contactMedium.getCharacteristic().getPhoneNumber())) {
			            tel = contactMedium.getCharacteristic().getPhoneNumber();
			          }
			          
			          if ((null != individual.getGivenName()) && (individual.getGivenName().length() > 0)) {
			            givenName = individual.getGivenName();
			          }
			          if ((null != individual.getBirthDate())) {
			        	  birthDate = individual.getBirthDate().toDate();
			            }

			          if ((null != individual.getFamilyName()) && (individual.getFamilyName().length() > 0)) {
			            familyName = individual.getFamilyName();
			          }
			          
			          if (null != individual.getGender())
			          {
			            if (individual.getGender().getValue().equalsIgnoreCase(Gender.MALE.getValue())) {
			              gender = Character.valueOf('M');
			            }
			            if (individual.getGender().getValue().equalsIgnoreCase(Gender.FEMALE.getValue())) {
			              gender = Character.valueOf('F');
			            } else {
			              gender = Character.valueOf('M');
			            }
			          }
			         
			          if (null != individual.getTitle()) {
			            title = individual.getTitle().getValue().toUpperCase();
			          } 
			          
			          if ((null != individual.getNationality()) && (individual.getNationality().length() > 0)) {
			            nationality = individual.getNationality();
			          }
			          
			          if (null != individual.getMaritalStatus())
			          {
			        	  maritalStatus = individual.getMaritalStatus().getValue();
			          }
			        } 
			          this.bscsIndividualService.executeAddressWriteForPostParty(csIdPub, adrCsType, new Long(0L), givenName, familyName, middleName, adrStreetNo, adrCity, gender, adrStreet, adrZip, countryIdPub, job, birthDate, title, nationality, adrRoles, licence, social, idNo, idType, email, fax, tel, null, null,maritalStatus , adrState, true);
			        
			      }

			//PAYMENT_ARRANGEMENT.WRITE
		      if (null != payMethod || null != bankAccount || null != bankAccountOwner || null != bankCity || null != bankCode  || null != bankCountry
		    		  || null != bankName || null != bankProvince || null !=  bankStreetName || null != bankStreetNum || null != bankZip || null != bankZone
		    		  || null != creditCardCompany || null != creditCardLimit || null != creditCardExpirDate || null != swiftCode) {
		          this.bscsIndividualService.executePaymentArrangementWriteForPostPartyIndividual(csIdPub, new Long(0L), payMethod,bankAccount,
		        		  bankAccountOwner,bankCity,bankCode,bankCountry,bankName, bankProvince, bankStreetName,bankStreetNum,bankZip,bankZone,
		        		  creditCardCompany,creditCardLimit,creditCardExpirDate,swiftCode,true, true);
		        }
	        //------------------
			return individual;
	  
		  }catch (ApiException e ){
			  throw e ;
		  }catch (Exception e ){
			  throw new TechnicalException(e.getMessage());
		  }
	  
}
}

