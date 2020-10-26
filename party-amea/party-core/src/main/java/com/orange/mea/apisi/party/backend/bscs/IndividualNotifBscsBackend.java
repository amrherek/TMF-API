package com.orange.mea.apisi.party.backend.bscs;

import com.orange.apibss.party.model.obw.Characteristic;
import com.orange.apibss.party.model.obw.CharacteristicNameEnum;
import com.orange.apibss.party.model.obw.ContactMedium;
import com.orange.apibss.party.model.obw.ContactMediumType;
import com.orange.apibss.party.model.obw.Gender;
import com.orange.apibss.party.model.obw.IdentificationType;
import com.orange.apibss.party.model.obw.Individual;
import com.orange.apibss.party.model.obw.IndividualIdentification;
import com.orange.apibss.party.model.obw.MediumCharacteristic;
import com.orange.apibss.party.model.obw.RelatedParty;
import com.orange.apibss.party.model.obw.Role;
import com.orange.apibss.party.model.obw.TitleEnum;
import com.orange.bscs.api.aop.TransactionalBscs;
import com.orange.bscs.api.exceptions.APIException;
import com.orange.bscs.model.businesspartner.BSCSCustomer;
import com.orange.mea.apisi.party.backend.CreateIndividualBackend;
import com.orange.mea.apisi.party.backend.bscs.transformer.BscsAddressToIndividualTransformer;
import com.orange.mea.apisi.party.backend.bscs.transformer.BscsCustomerInfoToIndividualTransformer;
import com.orange.mea.apisi.party.backend.bscs.transformer.IndividualToBscsAddressTransformer;
import com.orange.mea.apisi.party.backend.bscs.transformer.IndividualToBscsCustomerInfoTransformer;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
  public String createIndividual(Individual individual)
    throws APIException
  {
    boolean adressExists = false;
    List<ContactMedium> contactMediumList = individual.getContactMedium();
    if ((null != contactMediumList) && (!contactMediumList.isEmpty())) {
      for (ContactMedium tmfContactMedium : contactMediumList)
      {
        MediumCharacteristic medium = tmfContactMedium.getCharacteristic();
        if (null != medium) {
          adressExists = true;
        } else {
          throw new APIException("addressIsMandatoryException", null);
        }
      }
    } else {
      throw new APIException("addressIsMandatoryException", null);
    }
    if (!adressExists) {
      throw new APIException("addressIsMandatoryException", null);
    }
    List<Characteristic> characteristics = individual.getCharacteristic();
    
    String ratePlan = null;
    String billCycle = null;
    String prgCode = null;
    String isPayResp = null;
    String job = null;
    String payMethod = null;
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
          }
        }
        else {
          throw new APIException("missing mandatory Filds", null);
        }
      }
    }
    try
    {
      String key = "INITIATOR";
      boolean paymentResp = true;
      Long custCat = null;

      String role = null;
      if ((null != ((RelatedParty)individual.getRelatedParty().get(0)).getRole()) && (((RelatedParty)individual.getRelatedParty().get(0)).getRole().getValue().length() > 0)) {
        role = ((RelatedParty)individual.getRelatedParty().get(0)).getRole().getValue();
      }
      BSCSCustomer bscscustomer = this.bscsIndividualService.executeCustomerNewForePostPartyIndividual(billCycle, prgCode, ratePlan, null, 
        Boolean.valueOf(paymentResp), null, null, role);
      

      String csIdPub = bscscustomer.getCustomerIDPub();
      if (null != payMethod) {
        this.bscsIndividualService.executePaymentArrangementWriteForPostPartyIndividual(csIdPub, new Long(0L), payMethod);
      } else {
        throw new APIException("MISSING MANDATORY FIELDS", null);
      }
      List<ContactMedium> contactMeduimList = individual.getContactMedium();
      if ((null != contactMeduimList) && (!contactMeduimList.isEmpty())) {
        for (ContactMedium contactMedium : contactMeduimList)
        {
          String idNo = null;
          Long idType = null;
          String licence = null;
          String social = null;
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
          
          adrRoles = "B";
          if ((null != contactMedium.getMediumType()) && 
            (contactMedium.getMediumType().getValue().equalsIgnoreCase(ContactMediumType.POSTALADDRESS.getValue())))
          {
            adrCsType = Character.valueOf('C');
            if (null != contactMedium.getCharacteristic().getStateOrProvince()) {}
            adrStreetNo = contactMedium.getCharacteristic().getStateOrProvince();
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
          Date birthDate = null;
          String givenName = null;
          if ((null != individual.getGivenName()) && (individual.getGivenName().length() > 0)) {
            givenName = individual.getGivenName();
          }
          String familyName = null;
          if ((null != individual.getFamilyName()) && (individual.getFamilyName().length() > 0)) {
            familyName = individual.getFamilyName();
          }
          String middleName = null;
          Character gender = null;
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
          String title = null;
          if (null != individual.getTitle()) {
            title = individual.getTitle().getValue().toUpperCase();
          }
          String nationality = null;
          if ((null != individual.getNationality()) && (individual.getNationality().length() > 0)) {
            nationality = individual.getNationality();
          }
          this.bscsIndividualService.executeAddressWriteForPostPartyIndividual(csIdPub, adrCsType, new Long(0L), givenName, familyName, middleName, adrStreetNo, adrCity, gender, adrStreet, adrZip, countryIdPub, job, birthDate, title, nationality, adrRoles, licence, social, idNo, idType, email, fax, tel);
        }
      }
      Character csStatus = Character.valueOf('a');

      String rsCodeS = null;
      Long rsCode = Long.valueOf(1L);

      this.bscsIndividualService.executeCustomerWriteForPostPartyIndividual(csIdPub, csStatus, rsCode, ratePlan, custCat);
      

      return bscscustomer.getCustomerIDPub();
    }
    catch (Exception e)
    {
      throw new APIException(e);
    }
  }
}

