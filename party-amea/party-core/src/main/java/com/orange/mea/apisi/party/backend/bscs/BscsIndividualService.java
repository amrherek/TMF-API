package com.orange.mea.apisi.party.backend.bscs;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.badrequest.BadParameterValueException;
import com.orange.apibss.common.exceptions.badrequest.notfound.NotFoundIdException;
import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.apibss.party.model.obw.MaritalStatus;
import com.orange.bscs.api.exceptions.APIException;
import com.orange.bscs.api.model.exception.CMSException;
import com.orange.bscs.api.model.exception.SOIException;
import com.orange.bscs.model.BscsAddress;
import com.orange.bscs.model.BscsCustomer;
import com.orange.bscs.model.BscsCustomerInfo;
import com.orange.bscs.model.BscsCustomerSearch;
import com.orange.bscs.model.businesspartner.BSCSAddress;
import com.orange.bscs.model.businesspartner.BSCSCustomer;
import com.orange.bscs.model.businesspartner.BSCSCustomerWriteInput;
import com.orange.bscs.model.businesspartner.BSCSPartyRoleAssignment;
import com.orange.bscs.model.businesspartner.BSCSPaymentArrangement;
import com.orange.bscs.model.businesspartner.BSCSPaymentArrangementAssignment;
import com.orange.bscs.model.businesspartner.EnumAddressRole;
import com.orange.bscs.model.businesspartner.EnumCustomerLevelCode;
import com.orange.bscs.model.businesspartner.EnumCustomerStatus;
import com.orange.bscs.model.criteria.BscsCustomerSearchCriteria;
import com.orange.bscs.model.factory.BscsObjectFactory;
import com.orange.bscs.service.BscsBusinessPartnerServiceAdapter;
import com.orange.bscs.service.BscsMarketSegmentServiceAdapter;
import com.orange.bscs.service.exception.BscsFieldExceptionEnum;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;
import com.orange.mea.apisi.party.beans.IndividualFilteringCriteria;

/**
 * Client for bscs calls
 *
 * @author xbbs3851
 *
 */
@Service
public class BscsIndividualService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String PARTY = "party";
    private static final String UNKNOWN_CUSTOMER_ID = "Unknown customer id";

    @Autowired
    protected BscsBusinessPartnerServiceAdapter businessPartnerAdapter;

    @Autowired
    protected BscsMarketSegmentServiceAdapter marketSegmentAdapter;

    @Autowired
    protected BscsObjectFactory bscsObjectFactory;

    /**
     * Finds a single BscsAddress using the customerId of the matching
     * Individual
     *
     * @param customerId
     *            the id of the customer we are looking for
     * @return
     * @throws ApiException
     */
    public BscsAddress getBscsAddress(final String customerId) throws ApiException {

        // BSCS call
        BscsAddress address = null;
        try {
            address = businessPartnerAdapter.getAddress(customerId, EnumAddressRole.BILL);
        } catch (BscsInvalidIdException e) {
            logger.debug(UNKNOWN_CUSTOMER_ID, e);
            throw new NotFoundIdException(PARTY, customerId);
        }

        if (address == null) {
            logger.debug("BSCS ADDRESS.READ error, null result");
            throw new NotFoundIdException(PARTY, customerId);
        }

        return address;
    }

    /**
     * Finds a list of BscsCustomerSearch matching provided criteria
     *
     * @param criteria
     * @return
     * @throws NoResultException
     */
    public List<BscsCustomerSearch> findCustomersByCriteria(final IndividualFilteringCriteria criteria) {

        final BscsCustomerSearchCriteria customerSearchParams = bscsObjectFactory.createBscsCustomerSearchCriteria();

        customerSearchParams.setFirstName(criteria.getGivenName());
        customerSearchParams.setLastName(criteria.getFamilyName());
        customerSearchParams.setEmail(criteria.getEmail());
        customerSearchParams.setCompanyName(criteria.getTradingName());
        customerSearchParams.setFlagCase(false);

        return businessPartnerAdapter.findCustomersByCriteria(customerSearchParams);
    }

    /**
     * Sends a request to bscs to update an existing BscsAddress
     *
     * @param address
     * @throws ApiException
     */
    public void updateIndividual(final BscsAddress address) throws ApiException {
        try {
            businessPartnerAdapter.writeAddress(address, true);
        } catch (BscsInvalidIdException e) {
            logger.debug(UNKNOWN_CUSTOMER_ID, e);
            throw new NotFoundIdException(PARTY, address.getCustomerId());
        } catch (BscsInvalidFieldException e) {
            logger.debug("Invalid field", e);
            BscsFieldExceptionEnum field = e.getName();
            if (field != null) {
                switch (field) {
                case TITLE:
                    throw new BadParameterValueException("title", address.getTitleId().toString());
                case IDENTIFICATION_TYPE:
                    throw new BadParameterValueException("identification.type",
                            address.getNationalIdTypeCode().toString());
                case EMAIL:
                    throw new BadParameterValueException("contactMedium.medium.emailAddress", address.getEmail());
                case POSTAL_CODE:
                    throw new BadParameterValueException("contactMedium.medium.postcode", address.getPostalCode());
                default:
                    throw new BadParameterValueException(e.getName().toString(), e.getValue());
                }
            } else {
                throw new TechnicalException(e.getMessage(), e);
            }
        }

    }

    /**
     * Send a request to BSCS to get additional customer info
     * 
     * @param customerId
     * @return
     * @throws ApiException
     */
    public BscsCustomerInfo getCustomerInfo(final String customerId) throws ApiException {

        try {
            return marketSegmentAdapter.getCustomerInfo(customerId);
        } catch (BscsInvalidIdException e) {
            // if customer should exist if we get there
            logger.error(UNKNOWN_CUSTOMER_ID, e);
            throw new TechnicalException(e.getMessage(), e);
        }
    }

    /**
     * Sends a request to bscs to update customer info
     * 
     * @param info
     * @throws TechnicalException
     */
    public void updateCustomerInfo(BscsCustomerInfo info) throws TechnicalException {
        try {
            marketSegmentAdapter.writeCustomerInfo(info, true);
        } catch (BscsInvalidIdException e) {
            logger.error(UNKNOWN_CUSTOMER_ID, e);
            // if customer should exist if we get there
            throw new TechnicalException(e.getMessage(), e);
        }
    }


    /**
     * Finds a single BSCS Customer using the customerId of the matching
     * Individual
     *
     * @param customerId
     * @return
     * @throws ApiException
     */
    public BscsCustomer getBscsCustomer(final String customerId) throws ApiException {

        BscsCustomer customer = null;
        try {
            customer = businessPartnerAdapter.getCustomer(customerId);
        } catch (BscsInvalidIdException e) {
            logger.debug(UNKNOWN_CUSTOMER_ID, e);
            throw new NotFoundIdException(PARTY, customerId);
        }

        if (customer == null) {
            logger.debug("BSCS CUSTOMER.READ error, null result");
            throw new NotFoundIdException("party", customerId);
        }


        return customer;
    }
    
    public BSCSCustomer customerRead(final String customerIdPub) throws ApiException {

    	BSCSCustomer customer = null;
        customer = this.businessPartnerAdapter.searchCustomer(1l, customerIdPub, true);

        if (customer == null) {
            logger.debug("BSCS CUSTOMER.READ error, null result");
            throw new NotFoundIdException("party", customerIdPub);
        }
        return customer;
    }
    
    public List<BSCSCustomer> customerSearch(final String customerExternelId, String customerExternelSetId) throws ApiException {

    	List<BSCSCustomer> customerList  = null;
        customerList = this.businessPartnerAdapter.searchCustomer(customerExternelId, customerExternelSetId, true);

        return customerList;
    }
    
    
    
    
    public BSCSCustomer executeCustomerNewForePostPartyIndividual(String csBillCycle, String prgCode, String rpCodePub, String partyType, Boolean paymentResp, String externalCustomerId, String externalCustomerSetId, String partyRoleSHName)
    	     throws ApiException, BscsInvalidIdException, BscsInvalidFieldException, CMSException
    	   {
    	     try
    	     {
    	       BSCSCustomerWriteInput inCms = new BSCSCustomerWriteInput();
    	       if (null != csBillCycle) {
    	         inCms.setBillingCycle(csBillCycle);
    	       }
    	       if (null != prgCode) {
    	         inCms.setPriceGroupCode(prgCode);
    	       }
    	       if (null != rpCodePub) {
    	         inCms.setRatePlanCode(rpCodePub);
    	       }
    	       if (null != partyType) {
    	         inCms.setPartyType(partyType);
    	       }
    	       if (null != paymentResp) {
    	         inCms.setIsPaymentResponsible(paymentResp);
    	       }
    	       if (null != externalCustomerId) {
    	         inCms.setExternalCustomerId(externalCustomerId);
    	       }
    	       if (null != externalCustomerSetId) {
    	         inCms.setExternalCustomerIdSet(externalCustomerSetId);
    	       }
    	       inCms.setCustomerLevelCode(EnumCustomerLevelCode.SUBSCRIBER);
    	      
    	       
    	 
    	       BSCSCustomer newCustomer = this.businessPartnerAdapter.writeNewCustomer(inCms, false);
    	       if (null != newCustomer) {
    	         return newCustomer;
    	       }
    	       throw new CMSException("command cms CUSTOMER.NEW result is null");
    	     }
    	     catch (CMSException e)
    	     {
    	       throw e;
    	     }
    	   }
    
    	   
    	   public void executeAddressWriteForPostParty(String csIdPub, Character adrCsType, Long adrSeq, String adrFName, 
    			   String adrLName, String middleName, String adrStreetNo, String adrCity, Character adrSex, String adrStreet,
    			   String adrZip, String countryIdPub, String adrJBDes, Date birthDate, String title, String nationality, String adrRoles, 
    			   String licence, String social, String idNo, Long idType, String email, String fax, String tel,String tradingName, String compno,
    			   String maritalStatus, String state, boolean commit)
    	     throws CMSException, ApiException
    	   {
    	     try
    	     {
    	       BSCSAddress inCms = new BSCSAddress();
    	       if (null != csIdPub) {
    	         inCms.setCustomerIdPub(csIdPub);
    	       }
    	      /* if (null != type) {
      	         inCms.setAddressReadRole(type);
      	       }*/
    	       if (null != adrCsType) {
    	         inCms.setCustomerType(adrCsType);
    	       }
    	       if (null != adrSeq) {
    	         inCms.setSequenceNumber(adrSeq);
    	       }
    	       if (null != adrFName) {
    	         inCms.setFirstName(adrFName);
    	       }
    	       if (null != tradingName) {
      	         inCms.setAddressName(tradingName);
      	       }
    	       if (null != adrLName) {
    	         inCms.setLastName(adrLName);
    	       }
    	       if (null != middleName) {
    	         inCms.setMidNames(middleName);
    	       }
    	       if (null != adrStreetNo) {
    	         inCms.setStreetNumber(adrStreetNo);
    	       }
    	       if (null != adrCity) {
    	         inCms.setCity(adrCity);
    	       }
    	       if (null != adrSex) {
    	         inCms.setSex(adrSex);
    	       }
    	       if (null != adrStreet) {
    	         inCms.setStreet(adrStreet);
    	       }
    	       if (null != state) {
      	         inCms.setState(state);
      	       }
    	       if (null != adrZip) {
    	         inCms.setPostalCode(adrZip);
    	       }
    	       if (null != countryIdPub) {
    	         inCms.setCountryCode(countryIdPub);
    	       }
    	       if (null != adrJBDes) {
    	         inCms.setJobDescription(adrJBDes);
    	       }
    	       if (null != birthDate) {
    	         inCms.setBirthDate(birthDate);
    	       }
    	       if (null != title) {
    	         inCms.setTitleCode(title);
    	       }
    	       if (null != nationality) {
    	         inCms.setNationalityCode(nationality);
    	       }
    	       if (null != adrRoles) {
    	         inCms.setAddressRoles(adrRoles);
    	       }
    	       if (null != licence) {
    	         inCms.setDrivingLicense(licence);
    	       }
    	       if (null != social) {
    	         inCms.setSocialNumber(social);
    	       }
    	       if (null != compno) {
        	         inCms.setNationalOrganisationIdentifier(compno);
        	         inCms.setNationalCard(compno);
        	       }
    	       if (null != idNo) {
    	         inCms.setNationalCard(idNo);
    	       }
    	       
    	       if (null != maritalStatus) {
    	    	   Long msid = null;
    	         	  if (maritalStatus.equals(MaritalStatus.SINGLE.getValue()))
    	         		 msid = 1L;
    	         	  if (maritalStatus.equals(MaritalStatus.MARRIED.getValue()))
    	         		 msid = 2L;
    	         	  if (maritalStatus.equals(MaritalStatus.DIVORCED.getValue()))
    	         		 msid = 3L;
    	         	  if (maritalStatus.equals(MaritalStatus.WIDOWED.getValue()))
    	         		 msid = 4L;
    	    	   inCms.setMaritalStatusId(msid);
      	       }
    	       
    	       if (null != idType) {
    	         inCms.setNationalIdTypeCode(idType);
    	       }
    	       if (null != email) {
    	         inCms.setEmail(email);
    	       }
    	       if (null != fax) {
    	         inCms.setFax(fax);
    	       }
    	       if (null != tel) {
    	         inCms.setTelephone1(tel);
    	       }
    	       inCms.setAddressRoles("BRSEU");
    	       
    	       Long localLong = this.businessPartnerAdapter.writeAddress(inCms, commit);
    	     }
    	     catch (BscsInvalidIdException e)
    	     {
    	       this.logger.debug("Unknown customer id", e);
    	       throw new NotFoundIdException("party", csIdPub);
    	     }
    	     catch (BscsInvalidFieldException e)
    	     {
    	       this.logger.debug("Invalid field", e);
    	       
    	       throw new TechnicalException(e.getMessage(), e);
    	     }
    	   }
    	   
    	   public void executePaymentArrangementWriteForPostPartyIndividual(String csIdPub, Long cspSeqNo, String cspPmntIdPub,String bankAccount,
    			   String bankAccountOwner,String bankCity,String bankCode, String bankCountry, String bankName, String bankProvince, String bankStreetName,
    			   String bankStreetNum, String bankZip, String bankZone, String creditCardCompany, String creditCardLimit, String creditCardExpirDate, String swiftCode, Boolean isUpdate,  boolean commit)
    	     throws CMSException, ApiException
    	   {
    	     try
    	     {
    	       BSCSPaymentArrangement inCms = new BSCSPaymentArrangement();
    	       
    	       
    	       if (null != csIdPub) {
    	         inCms.setCustomerIDPub(csIdPub);
    	       }
    	       if (null != cspPmntIdPub) {
    	         inCms.setPaymentMethodId(Long.valueOf(cspPmntIdPub));
    	       }
    	       if (null != bankAccount) {
      	         inCms.setAccountNumber(bankAccount);
      	       }
    	       if (null != bankAccountOwner) {
        	         inCms.setAccountOwner(bankAccountOwner);
        	   }
    	       if (null != bankCity) {
      	         inCms.setBankCity(bankCity);
        	   }
    	       if (null != bankCode) {
        	         inCms.setBankCode(bankCode);
          	   }
    	       if (null != bankCountry) {
      	         inCms.setBankCounty(bankCountry);
        	   }
    	       if (null != bankName) {
        	         inCms.setBankName(bankName);
          	   }  
    	       if (null != bankName) {
      	         inCms.setBankName(bankName);
        	   }     	       
    	       if (null != bankProvince) {
        	         inCms.setBankState(bankProvince);
          	   } 
    	       if (null != bankStreetName) {
      	         inCms.setBankStreet(bankStreetName);
        	   }
    	       if (null != bankStreetNum) {
        	         inCms.setBankStringNumber(bankStreetNum);
          	   }
    	       if (null != bankZip) {
      	         inCms.setBankZipCode(bankZip);
        	   }
    	       if (null != bankZone) {
        	         inCms.setBankCounty(bankZone);
          	   }
    	       if (null != creditCardCompany) {
      	         inCms.setFinancialInstituteId(Long.valueOf(creditCardCompany));
        	   }
    	       if (null != creditCardLimit) {
        	         inCms.setCreditCardPaymentsCeilingAmount(Double.valueOf(creditCardLimit));
          	   }
    	       if (null != creditCardExpirDate) {
      	         inCms.setCreditCardValidity(creditCardExpirDate);
        	   }
    	       if (null != swiftCode) {
        	         inCms.setInternationalBankIdentifier(swiftCode);
          	   }
 
    	       this.logger.trace("CMS PAYMENT_ARRANGEMENT.WRITE");
    	       BSCSPaymentArrangement localBSCSPaymentArrangement1 = this.businessPartnerAdapter.writePaymentArrangement(inCms, commit);
    	       
    	       if (isUpdate){
    	    	   BSCSPaymentArrangementAssignment inCmsAssign = new BSCSPaymentArrangementAssignment();
    	    	   
    	    	   inCmsAssign.setPaymentId(localBSCSPaymentArrangement1.getId());
    	    	   inCmsAssign.setTermeCode(1L);
    	    	   Date today = new Date();
    	    	   inCmsAssign.setValidFrom(new Date(today.getTime() + (1000 * 60 * 60 * 24)));
        	       
    	    	   this.logger.trace("CMS PAYMENT_ARRANGEMENT_ASSIGNEMENT.WRITE");
        	       BSCSPaymentArrangementAssignment localBSCSPaymentArrangementAssign = this.businessPartnerAdapter.writePaymentArrangementAssignement(inCmsAssign, commit);
    	    	   
    	       }
    	       
    	     }
    	     catch (BscsInvalidIdException e)
    	     {
    	       this.logger.debug("Unknown customer id", e);
    	       throw new NotFoundIdException("party", csIdPub);
    	     }
    	     catch (BscsInvalidFieldException e)
    	     {
    	       this.logger.debug("Invalid field", e);
    	       
    	       throw new TechnicalException(e.getMessage(), e);
    	     }
    	   }
    	   
    	   public void executeCustomerWriteForPostPartyIndividual(String csIdPub, Character csStatus, Long rsCode, String rpCodePub, Long custCatCode, 
    			   String extrId, String extrIdSet, String prgcode, String billcycle)
    	     throws CMSException, BscsInvalidIdException, BscsInvalidFieldException
    	   {
    	     BSCSCustomerWriteInput inCms = new BSCSCustomerWriteInput();
    	     if (null != csIdPub) {
    	       inCms.setCustomerIdPub(csIdPub);
    	     }
    	     if (null != csStatus) {
    	       inCms.setStatus(EnumCustomerStatus.parse(csStatus));
    	       this.logger.debug("############################csStatus     :"+csStatus);
    	       this.logger.debug("############################EnumCustomerStatus.parse(csStatus)     :"+EnumCustomerStatus.parse(csStatus));
    	     }
    	     if (null != rsCode) {
    	       inCms.setReasonCode(rsCode);
    	     }
    	     if (null != rpCodePub) {
    	       inCms.setRatePlanCode(rpCodePub);
    	     }
    	     if (null != custCatCode) {
    	       inCms.setCustomerCategory(custCatCode);
    	     }
    	     if (null != extrId) {
      	       inCms.setExternalCustomerId(extrId);
      	     }
    	     if (null != extrIdSet) {
      	       inCms.setExternalCustomerIdSet(extrIdSet);
      	     }
    	     if (null != prgcode) {
        	       inCms.setPriceGroupCode(prgcode);
        	     }
    	     if (null != billcycle) {
      	       inCms.setBillingCycle(billcycle);
      	       inCms.setBillingCycleComment("CRM");
      	     }
    	     
    	     //if (null != partyRoleSHName) {
					BSCSPartyRoleAssignment partyRoleAssign = new BSCSPartyRoleAssignment();
					List<BSCSPartyRoleAssignment> listPartyRoleAssign = new ArrayList<BSCSPartyRoleAssignment>();
					partyRoleAssign.setPartyRoleId(1L);
					listPartyRoleAssign.add(partyRoleAssign);
					inCms.setPartyRoleAssignments(listPartyRoleAssign);
				//}
    	     this.logger.debug("CMS CUSTOMER.WRITE");
    	     this.businessPartnerAdapter.writeCustomer(inCms, true);
    	    
    	   }
    	   
    		public BSCSCustomer executeLaMemberNewForPostPartyOrganization(String csBillCycle, String prgCode,
    				String rpCodePub, String partyType, Boolean paymentResp, String externalCustomerId,
    				String externalCustomerSetId, Date creationDate, Long csIdHigh, EnumCustomerLevelCode level) throws CMSException, BscsInvalidIdException, BscsInvalidFieldException {

    				try {
    					BSCSCustomer inCms = new BSCSCustomer();
    					if (null != csBillCycle)
    						inCms.setBillingCycle(csBillCycle);
    					if (null != prgCode)
    						inCms.setPriceGroupCode(prgCode);
    					if (null != rpCodePub)
    						inCms.setRatePlan(rpCodePub);
    					if (null != paymentResp)
    						inCms.setIsPaymentResponsible(paymentResp);
    					if (null != externalCustomerId)
    						inCms.setExternalCustomerId(externalCustomerId);
    					if (null != externalCustomerSetId)
    						inCms.setExternalCustomerIdSet(externalCustomerSetId);
    					// if(null!=creationDate)
    					// inCms.put("CS_CREATION_DATE", creationDate);
    					if (null != partyType)
    						inCms.setPartyType(partyType);
    					if (null != csIdHigh)
    						inCms.setParentCustomerId(csIdHigh);
    					if (null != level)
    						inCms.setCustomerLevelCode(level);
    					 
    					 BSCSCustomer newCustomer = this.businessPartnerAdapter.writeLAMember(inCms, false);
    		    	       if (null != newCustomer) {
    		    	         return newCustomer;
    		    	       }
    						throw new CMSException("command cms LA_MEMBER.NEW result is null");
    				} catch (CMSException e) {
    					throw e;
    				} finally {
    					// cmsClient.disconnect();
    				}
    		}
    		
    		public void executeCustomerHierarchyWriteForPatchPartyOrganization(Long csId, String csIdPub, Long csIdHight,
    				Boolean paymentResp, EnumCustomerLevelCode level) throws CMSException, BscsInvalidIdException, BscsInvalidFieldException {
    			logger.trace("=> executeCustomerHierarchyWriteForPatchPartyOrganization()");
    			logger.trace("csIdPub" + csIdPub + ",csIdHight" + csIdHight + ",paymentResp" + paymentResp);

    				try {
    					
    					 this.businessPartnerAdapter.writeCustomerHierarchy(csId, csIdPub, csIdHight,null, paymentResp, level, true);
    					
    				} finally {
    					// cmsClient.disconnect();
    				}
    		}
    		
    		public BscsAddress executeAddressReadForPatchParty(String csId, EnumAddressRole adrRoles)
    				throws CMSException, BscsInvalidIdException {
    			logger.trace("=> executeAddressReadForPatchPartyIndividual()");
    			logger.trace("csIdPub" + csId + ",adrRoles" + adrRoles);
    			if (null != csId && csId.length() > 0) {

    					try {
    						BscsAddress addressResult = null;

    						addressResult = this.businessPartnerAdapter.getAddress(csId, adrRoles);

    						if (addressResult == null)
    							throw new CMSException("command cms ADDRESS.READ result is null");
    						
							return addressResult;
							
    					} catch (CMSException e) {
    						throw e;
    					} finally {
    						// cmsClient.disconnect();
    					}

    			} else
    				throw new APIException("missing mandatory fields",null);
    		}
    	   
}
