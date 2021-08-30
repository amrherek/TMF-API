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
import com.orange.apibss.party.model.obw.MediumCharacteristic;
import com.orange.apibss.party.model.obw.Organization;
import com.orange.apibss.party.model.obw.OrganizationIdentification;
import com.orange.apibss.party.model.obw.StatusEnum;
import com.orange.apibss.party.model.obw.StatusEnumOrg;
import com.orange.bscs.api.aop.TransactionalBscs;
import com.orange.bscs.api.exceptions.APIException;
import com.orange.bscs.model.BscsAddress;
import com.orange.bscs.model.businesspartner.BSCSCustomer;
import com.orange.bscs.model.businesspartner.EnumAddressRole;
import com.orange.bscs.model.businesspartner.EnumCustomerLevelCode;
import com.orange.mea.apisi.party.backend.CreateOrganizationBackend;
import com.orange.mea.apisi.party.backend.bscs.transformer.BscsAddressToIndividualTransformer;
import com.orange.mea.apisi.party.backend.bscs.transformer.BscsCustomerInfoToIndividualTransformer;
import com.orange.mea.apisi.party.backend.bscs.transformer.IndividualToBscsAddressTransformer;
import com.orange.mea.apisi.party.backend.bscs.transformer.IndividualToBscsCustomerInfoTransformer;

@Service
public class OrganizationNotifBscsBackend
  implements CreateOrganizationBackend
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
  public BSCSCustomer createOrganization(Organization organization)
    throws ApiException, MissingParameterException
  {
	  
  try
	{
    boolean adressExists = false;
    List<ContactMedium> contactMediumList = organization.getContactMedium();
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

    List<Characteristic> characteristics = organization.getCharacteristic();
    
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
          } else if (characteristic.getName().getValue().equalsIgnoreCase(CharacteristicNameEnum.REGISTRATIONSTATUS.getValue())) {
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
    }else {
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
    if (null==isPayResp)
    	throw new MissingParameterException("Characteristic.isPaymentResp");

    	/*  String key = "INITIATOR";
      boolean paymentResp = true;
      Long custCat = null;

      String role = null;
      if ((null != ((RelatedParty)organization.getRelatedParty().get(0)).getRole()) && (((RelatedParty)organization.getRelatedParty().get(0)).getRole().getValue().length() > 0)) {
        role = ((RelatedParty)organization.getRelatedParty().get(0)).getRole().getValue();
      }
      BSCSCustomer bscscustomer = this.bscsIndividualService.executeCustomerNewForePostPartyIndividual(billCycle, prgCode, ratePlan, null, 
        Boolean.valueOf(paymentResp), null, null, role);*/
      

			//LA_MEMBER.NEW

			Boolean paymentResp=null;
			Long custCat=null;

				if(isPayResp.equalsIgnoreCase("true")|| isPayResp.equalsIgnoreCase("x")){
					paymentResp=true;
				}
				if(isPayResp.equalsIgnoreCase("false")||isPayResp.trim().equalsIgnoreCase(""))
					paymentResp=false;

			String externalId1=null;
			String externalSetId1=null;
			List<ExternalReference> extRef = organization.getExternalReference();
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
				    		 throw new BadParameterFormatException("Max size for ExternalReference.name is 15 ");
				    	}
				   		if (externalSetId1.length()!=5){
				    		 throw new BadParameterFormatException("ExternalReference.ExternalReferenceType should countain 5 characters");
				    	}
				    }
		  //CUSTOMERS.SEARCH
			List<BSCSCustomer> customerResult=null;
		    if( null!=externalId1 && null !=externalSetId1 ){
				customerResult = bscsIndividualService.customerSearch(externalId1, externalSetId1);
		    }
		    
			if(null!=customerResult && customerResult.size()> 0)
				throw new BadParameterValueException("Customer with ExternalReference :  " + externalId1+ " already exist in data base" );

		    
		    
		    
		    
			Long csIdHigh=null;
			EnumCustomerLevelCode level=EnumCustomerLevelCode.ROOT;
			if(null!=organization.getOrganizationParentRelationship()){
				if(null!=organization.getOrganizationParentRelationship().getId()){
					BSCSCustomer  customerHigh = bscsIndividualService.customerRead(organization.getOrganizationParentRelationship().getId());
					if(null==customerHigh)
						throw new NotFoundException("CUSTOMER HIGH NOT FOUND : OrganizationParentRelationship.id = " +organization.getOrganizationParentRelationship().getId());

					if (null != customerHigh.getCustomerID()) {
							csIdHigh=Long.valueOf(customerHigh.getCustomerID());
						}
					if (null != customerHigh.getCustomerID()) {
						csIdHigh=Long.valueOf(customerHigh.getCustomerID());
					}

					if (null != customerHigh.getCustomerLevelCode()) {
						if(EnumCustomerLevelCode.SUBSCRIBER.getLevelCode().equals(customerHigh.getCustomerLevelCode()))
						throw new BadParameterValueException("Wrong Parent level, the level of OrganizationParentRelationship.id is a subscriber : OrganizationParentRelationship.id = " +organization.getOrganizationParentRelationship().getId());
						
						if (EnumCustomerLevelCode.ROOT.getLevelCode().equals(customerHigh.getCustomerLevelCode().getLevelCode())){
							level =EnumCustomerLevelCode.DIVISION;
						}else if (EnumCustomerLevelCode.DIVISION.getLevelCode().equals(customerHigh.getCustomerLevelCode().getLevelCode())){
							level =EnumCustomerLevelCode.COST_CENTER;
						}else if(EnumCustomerLevelCode.COST_CENTER.getLevelCode().equals(customerHigh.getCustomerLevelCode().getLevelCode())){
							level =EnumCustomerLevelCode.SUBSCRIBER;
						}
						
					}
				}						

			}
			
			BSCSCustomer laMemberResult = bscsIndividualService.executeLaMemberNewForPostPartyOrganization(billCycle, prgCode, ratePlan,null, paymentResp, externalId1, externalSetId1, null,csIdHigh,level);
			//LA_MEMBER.NEW RESULT MAPPING
		      String csIdPub = laMemberResult.getCustomerIDPub();

		      this.bscsIndividualService.executePaymentArrangementWriteForPostPartyIndividual(csIdPub, new Long(0L), payMethod,bankAccount,
		    		  bankAccountOwner,bankCity,bankCode,bankCountry,bankName, bankProvince, bankStreetName,bankStreetNum,bankZip,bankZone,
		    		  creditCardCompany,creditCardLimit,creditCardExpirDate,swiftCode, false, false);
      List<ContactMedium> contactMeduimList = organization.getContactMedium();
      if ((null != contactMeduimList) && (!contactMeduimList.isEmpty())) {
          String idNo = null;
          Long idType = null;
          String compno = null;
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
          String tradingName = null;
          String adrState = null;
        for (ContactMedium contactMedium : contactMeduimList)
        {

          if (null != organization.getOrganizationIdentification())
          {
           OrganizationIdentification organisationIdentification = organization.getOrganizationIdentification();
                if ((null != organisationIdentification.getIdentificationType().getValue()) &&
                  (organisationIdentification.getIdentificationId().length() > 0))
                {
                	compno=organisationIdentification.getIdentificationId();
                }                         
          }
        	  

          
          adrRoles = "B";
          if ((null != contactMedium.getMediumType()) && 
            (contactMedium.getMediumType().getValue().equalsIgnoreCase(ContactMediumType.POSTALADDRESS.getValue())))
          {
            adrCsType = Character.valueOf('B');
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
          
          if ((null != organization.getTradingName()) && (organization.getTradingName().length() > 0)) {
            tradingName = organization.getTradingName();
          }
        }
          this.bscsIndividualService.executeAddressWriteForPostParty(csIdPub, adrCsType, new Long(0L), null, null, null, adrStreetNo, adrCity, null, adrStreet, adrZip, countryIdPub, job, birthDate, null, null, adrRoles, null, null, idNo, idType, email, fax, tel, tradingName, compno, null ,adrState, false);
        
      }
      
      Character csStatus = Character.valueOf('a');
      if (null != organization.getStatus())
      {
        if (organization.getStatus().getValue().equalsIgnoreCase(StatusEnumOrg.INITIALIZED.getValue())) {
        	csStatus = Character.valueOf('i');
        }
        if (organization.getStatus().getValue().equalsIgnoreCase(StatusEnumOrg.VALIDATED.getValue())) {
        	csStatus = Character.valueOf('a');
        }
        if (organization.getStatus().getValue().equalsIgnoreCase(StatusEnumOrg.CLOSED.getValue())) {
        	csStatus = Character.valueOf('d');
        }
      }

      

      this.bscsIndividualService.executeCustomerWriteForPostPartyIndividual(csIdPub, csStatus, rsCode, ratePlan, custCat, null, null, prgCode, billCycle);
      
      if(null!=csIdHigh)
    	  this.bscsIndividualService.executeCustomerHierarchyWriteForPatchPartyOrganization(laMemberResult.getCustomerID(), csIdPub, csIdHigh, paymentResp, level);
      

      return laMemberResult;
	 }catch (ApiException e ){
		  throw e ;
	  }catch (Exception e ){
		  throw new TechnicalException(e.getMessage());
	  }
  }
  
 //************Patch Organization
  @TransactionalBscs
  public Organization patchOrganization(Organization organization) 
			throws APIException, ApiException {
		
	  try{
	  String csIdPub=null;

			//CUSTOMERS.SEARCH
			BSCSCustomer customerResult=null;
			String externalId1=null;
			String externalSetId1=null;
			  List<ExternalReference> extRef = organization.getExternalReference();
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
			   		if (externalSetId1.length()!=5){
			    		 throw new BadParameterFormatException("ExternalReference.ExternalReferenceType should countain 5 characters ");
			    	}
			    }

			if( null!=organization.getId() && organization.getId().length()>0 )
				customerResult = bscsIndividualService.customerRead(organization.getId());
			else
				throw new MissingParameterException("organization.id");
			//CUSTOMERS.SEARCH RESULT MAPPING
			if(null==customerResult)
				throw new NotFoundException("CUSTOMER NOT FOUND : organization.id = " + organization.getId().toString());
			csIdPub = customerResult.getCustomerIDPub();

			
			//CUSTOMER.WRITE

		      Character csStatus = null;
		      if (null != organization.getStatus())
		      {
		        if (organization.getStatus().getValue().equalsIgnoreCase(StatusEnumOrg.INITIALIZED.getValue())) {
		        	csStatus = Character.valueOf('i');
		        }
		        if (organization.getStatus().getValue().equalsIgnoreCase(StatusEnumOrg.VALIDATED.getValue())) {
		        	csStatus = Character.valueOf('a');
		        }
		        if (organization.getStatus().getValue().equalsIgnoreCase(StatusEnumOrg.CLOSED.getValue())) {
		        	csStatus = Character.valueOf('d');
		        }
				if(!(organization.getStatus().getValue().equalsIgnoreCase(StatusEnumOrg.INITIALIZED.getValue())
						||organization.getStatus().getValue().equalsIgnoreCase(StatusEnumOrg.VALIDATED.getValue())
						||organization.getStatus().getValue().equalsIgnoreCase(StatusEnumOrg.CLOSED.getValue())))
					throw new BadParameterValueException("WRONG STATUS VALUE : " + organization.getStatus().getValue());
			}
		      List<Characteristic> characteristics = organization.getCharacteristic();
		      
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
			this.bscsIndividualService.executeCustomerWriteForPostPartyIndividual(csIdPub, csStatus, rsCode, ratePlan, null, externalId1, externalSetId1, prgCode, billCycle);
			//ADDRESS

			BscsAddress oldbadress = this.bscsIndividualService.executeAddressReadForPatchParty(csIdPub, EnumAddressRole.BILL);
			
			 List<ContactMedium> contactMeduimList = organization.getContactMedium();
		      if ((null != contactMeduimList) && (!contactMeduimList.isEmpty())) {
	        	  
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
	          String idNo = null;
	          Long idType = null;
	          String compno = null;
	          Date birthDate = null;
	          String tradingName = null;
	          String adrState = null;
		        for (ContactMedium contactMedium : contactMeduimList)
		        {

		          if (null != organization.getOrganizationIdentification())
		          {
		           OrganizationIdentification organisationIdentification = organization.getOrganizationIdentification();
		                if ((null != organisationIdentification.getIdentificationType().getValue()) &&
		                  (organisationIdentification.getIdentificationId().length() > 0))
		                {
		                	compno=organisationIdentification.getIdentificationId();
		                }                         
		          }
	          
		          adrRoles = "B";
		          if ((null != contactMedium.getMediumType()) && 
		            (contactMedium.getMediumType().getValue().equalsIgnoreCase(ContactMediumType.POSTALADDRESS.getValue())))
		          {
		            adrCsType = Character.valueOf('B');
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

		          if ((null != organization.getTradingName()) && (organization.getTradingName().length() > 0)) {
		            tradingName = organization.getTradingName();
		          }else{
		        	  tradingName = oldbadress.getCompanyName();
		          }
		          
		          if(null!=organization.getOrganizationIdentification()
		        		  && null!=organization.getOrganizationIdentification().getIdentificationId()
		        		  && organization.getOrganizationIdentification().getIdentificationId().length()>0){
		        	      compno=organization.getOrganizationIdentification().getIdentificationId();
					}else{
						compno = oldbadress.getBscsModel().getNationalOrganisationIdentifier();
					}
		        }
		          this.bscsIndividualService.executeAddressWriteForPostParty(csIdPub, adrCsType, new Long(0L), null, null, null, adrStreetNo, adrCity, null, adrStreet, adrZip, countryIdPub, job, birthDate, null, null, adrRoles, null, null, idNo, idType, email, fax, tel, tradingName, compno, null, adrState,  true);
		        
		      }

			//PAYMENT_ARRANGEMENT.WRITE
		      if (null != payMethod || null != bankAccount || null != bankAccountOwner || null != bankCity || null != bankCode  || null != bankCountry
		    		  || null != bankName || null != bankProvince || null !=  bankStreetName || null != bankStreetNum || null != bankZip || null != bankZone
		    		  || null != creditCardCompany || null != creditCardLimit || null != creditCardExpirDate || null != swiftCode) {
		          this.bscsIndividualService.executePaymentArrangementWriteForPostPartyIndividual(csIdPub, new Long(0L), payMethod,bankAccount,
		        		  bankAccountOwner,bankCity,bankCode,bankCountry,bankName, bankProvince, bankStreetName,bankStreetNum,bankZip,bankZone,
		        		  creditCardCompany,creditCardLimit,creditCardExpirDate,swiftCode, true, true);
		        }
	        //------------------
			Long csIdHigh=null;
			EnumCustomerLevelCode level=null;
			if(null!=organization.getOrganizationParentRelationship()){
				if(null!=organization.getOrganizationParentRelationship().getId() && StringUtils.isNotBlank(organization.getOrganizationParentRelationship().getId())){
					BSCSCustomer  customerHigh = bscsIndividualService.customerRead(organization.getOrganizationParentRelationship().getId());
					if(null==customerHigh)
						throw new NotFoundException("CUSTOMER HIGH NOT FOUND : OrganizationParentRelationship.id = " +organization.getOrganizationParentRelationship().getId());
					
					if (null != customerHigh.getCustomerID()) {
							csIdHigh=Long.valueOf(customerHigh.getCustomerID());
						}
					if (null != customerHigh.getCustomerID()) {
						csIdHigh=Long.valueOf(customerHigh.getCustomerID());
					}

					if (null != customerHigh.getCustomerLevelCode()) {
						if(EnumCustomerLevelCode.SUBSCRIBER.getLevelCode().equals(customerHigh.getCustomerLevelCode()))
							throw new BadParameterValueException("Wrong Parent level, the level of OrganizationParentRelationship.id is a subscriber : OrganizationParentRelationship.id = " +organization.getOrganizationParentRelationship().getId());
												
						if (EnumCustomerLevelCode.ROOT.getLevelCode().equals(customerHigh.getCustomerLevelCode().getLevelCode())){
							level =EnumCustomerLevelCode.DIVISION;
						}else if (EnumCustomerLevelCode.DIVISION.getLevelCode().equals(customerHigh.getCustomerLevelCode().getLevelCode())){
							level =EnumCustomerLevelCode.COST_CENTER;
						}else if(EnumCustomerLevelCode.COST_CENTER.getLevelCode().equals(customerHigh.getCustomerLevelCode().getLevelCode())){
							level =EnumCustomerLevelCode.SUBSCRIBER;
						}					
					}
				}else {
					level =EnumCustomerLevelCode.ROOT;
				}
			}
			
			Boolean paymentResp=null;
			Long custCat=null;

			if (null == isPayResp){
				paymentResp = customerResult.getIsPaymentResponsible();
			}	
			else if(isPayResp.equalsIgnoreCase("true")|| isPayResp.equalsIgnoreCase("x")){
					paymentResp=true;
				}
			else if(isPayResp.equalsIgnoreCase("false")||isPayResp.trim().equalsIgnoreCase(""))
					paymentResp=false;
				
			
			if(null!=csIdHigh || null!=level )
					    	  this.bscsIndividualService.executeCustomerHierarchyWriteForPatchPartyOrganization(null, csIdPub, csIdHigh, paymentResp, level);
					
			return organization;
	  }catch (ApiException e ){
		  throw e ;
	  }catch (Exception e ){
		  throw new TechnicalException(e.getMessage());
	  }
}
  
}

