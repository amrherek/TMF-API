package com.orange.bscs.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.orange.bscs.api.connection.ConnectionHolder;
import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.model.exception.CMSException;
import com.orange.bscs.api.model.exception.SOIException;
import com.orange.bscs.cms.servicelayeradapter.BusinessPartnerServiceAdapterI;
import com.orange.bscs.model.BscsAddress;
import com.orange.bscs.model.BscsCustomer;
import com.orange.bscs.model.BscsCustomerGroup;
import com.orange.bscs.model.BscsCustomerSearch;
import com.orange.bscs.model.BscsIdentificationDocumentType;
import com.orange.bscs.model.BscsPaymentArrangement;
import com.orange.bscs.model.BscsTitle;
import com.orange.bscs.model.businesspartner.BSCSAddress;
import com.orange.bscs.model.businesspartner.BSCSCustomer;
import com.orange.bscs.model.businesspartner.BSCSCustomerWriteInput;
import com.orange.bscs.model.businesspartner.BSCSCustomersSearchRequest;
import com.orange.bscs.model.businesspartner.BSCSPaymentArrangement;
import com.orange.bscs.model.businesspartner.BSCSPaymentArrangementAssignment;
import com.orange.bscs.model.businesspartner.EnumAddressRole;
import com.orange.bscs.model.businesspartner.EnumCustomerLevelCode;
import com.orange.bscs.model.criteria.BscsCustomerSearchCriteria;
import com.orange.bscs.model.factory.BscsObjectFactory;
import com.orange.bscs.service.exception.BscsFieldExceptionEnum;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;

public abstract class BscsBusinessPartnerServiceAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected BusinessPartnerServiceAdapterI businessPartnerServiceAdapter;

    @Autowired
    protected BscsObjectFactory bscsObjectFactory;

    /**
     * Call to CUSTOMERS.SEARCH
     * 
     * @param criteria
     * @return
     */
    public List<BscsCustomerSearch> findCustomersByCriteria(BscsCustomerSearchCriteria criteria) {
        final List<BSCSCustomer> bscsCustomers = businessPartnerServiceAdapter
                .searchByCriterias(criteria.getBscsModel());
        List<BscsCustomerSearch> res = new ArrayList<>();
        for (BSCSCustomer customer : bscsCustomers) {
            res.add(bscsObjectFactory.createBscsCustomerSearch(customer));
        }
        return res;
    }

    /**
     * Call to CUSTOMER.READ
     * 
     * @param customerId
     * @return
     * @throws BscsInvalidIdException
     */
    public abstract BscsCustomer getCustomer(String customerId) throws BscsInvalidIdException;

    /**
     * Call to ADDRESS.READ
     * 
     * @param customerId
     * @param role
     * @return
     * @throws BscsInvalidIdException
     */
    public abstract BscsAddress getAddress(String customerId, EnumAddressRole role) throws BscsInvalidIdException;

    /**
     * Call to ADDRESS_WRITE and optionally commit
     * 
     * @param address
     * @param commit
     * @throws BscsInvalidIdException
     * @throws BscsInvalidFieldException
     */
    public void writeAddress(BscsAddress address, boolean commit)
            throws BscsInvalidIdException, BscsInvalidFieldException {
        try {
            businessPartnerServiceAdapter.addressWriteNoReturn(address.getBscsModel());
            if (commit) {
                ConnectionHolder.getCurrentConnection().commit();
            }
        } catch (final SOIException e) {
            logger.debug("BSCS ADDRESS_WRITE error with code: " + e.getCode());
            if (e.getCode() != null) {
                if (e.getCode().contains("FUNC_FRMWK_SRV.id0468") || e.getCode().contains("RC6701")) {
                    throw new BscsInvalidIdException(BscsFieldExceptionEnum.CUSTOMER_ID, e.getFirstArg(),
                            "Invalid customer id: {" + e.getFirstArg() + "}");
                }
                if (e.getCode().contains("RC6721")) {
                    // unknown title
                    throw new BscsInvalidFieldException(BscsFieldExceptionEnum.TITLE,
                            address.getTitleId().toString(),
                            e.getMessage());
                }
                if (e.getCode().contains("RC6723")) {
                    // unknown identification type
                    throw new BscsInvalidFieldException(BscsFieldExceptionEnum.IDENTIFICATION_TYPE, e.getFirstArg(),
                            e.getMessage());
                }
                if (e.getCode().contains("BusinessPartner.GenderTitleNotMatch")) {
                    // error during commit on field validation: title not
                    // compatible with gender
                    throw new BscsInvalidFieldException(BscsFieldExceptionEnum.TITLE, null,
                            "Gender and title are not compatible");
                }
                if (e.getCode().contains("BusinessPartner.MandatoryCharNoFound")) {
                    // error during commit on field validation e.g. missing
                    // '@' in email address
                    throw new BscsInvalidFieldException(BscsFieldExceptionEnum.buildBscsFieldExceptionEnum(e.getArg(1)),
                            null, "Bad field in individual content");
                }
                if (e.getCode().contains("BusinessPartner.InvalidPostalCode")) {
                    // error during commit on field validation: invalid postcode
                    // format
                    throw new BscsInvalidFieldException(BscsFieldExceptionEnum.POSTAL_CODE, address.getPostalCode(),
                            "Bad format for zip code");
                }
            }
            throw e;
        }
    }

    /**
     * Call to CUSTOMER_GROUPS.READ
     * 
     * @return
     */
    public List<BscsCustomerGroup> getCustomerGroups() {
        List<BSCSModel> customerGroups = businessPartnerServiceAdapter.customerGroupsRead();
        List<BscsCustomerGroup> res = new ArrayList<>();
        for (BSCSModel customerGroup : customerGroups) {
            res.add(bscsObjectFactory.createBscsCustomerGroup(customerGroup));
        }
        return res;
    }

    /**
     * Call to CUSTOMER_GROUPS.READ and return the customer group associated to
     * the given customer group code
     * 
     * @param customerGroupCode
     * 
     * @return
     * @throws BscsInvalidIdException
     */
    public BscsCustomerGroup getCustomerGroup(String customerGroupCode) throws BscsInvalidIdException {
        List<BscsCustomerGroup> customerGroups = getCustomerGroups();
        for(BscsCustomerGroup customerGroup : customerGroups) {
            if (customerGroup.getCode().equals(customerGroupCode)) {
                return customerGroup;
            }
        }
        throw new BscsInvalidIdException(BscsFieldExceptionEnum.CUSTOMER_GROUP_CODE, customerGroupCode,
                "Invalid customer group code: {" + customerGroupCode + "}");
    }

    /**
     * Call to PAYMENT_ARRANGEMENTS.READ to 0 current payment arrangement
     * (FLAG_CURRENT set to true)
     * 
     * @param customerId
     * @return
     * @throws BscsInvalidIdException
     */
    public abstract BscsPaymentArrangement getCurrentPaymentArrangement(String customerId)
            throws BscsInvalidIdException;

    /**
     * Call to TITLES.READ
     * 
     * @return
     */
    public List<BscsTitle> getTitles() {
        List<BSCSModel> titles = businessPartnerServiceAdapter.titlesRead();
        List<BscsTitle> res = new ArrayList<>();
        for (BSCSModel title : titles) {
            res.add(bscsObjectFactory.createBscsTitle(title));
        }
        return res;
    }

    /**
     * Call to IDENTIFICATION_DOCUMENT_TYPE.READ
     * 
     * @return
     */
    public List<BscsIdentificationDocumentType> getIdentificationDocumentTypes() {
        List<BSCSModel> types = businessPartnerServiceAdapter.documentTypesRead();
        List<BscsIdentificationDocumentType> res = new ArrayList<>();
        for (BSCSModel type : types) {
            res.add(bscsObjectFactory.createBscsIdentificationDocumentType(type));
        }
        return res;
    }
    
    
    public BSCSCustomer writeNewCustomer(BSCSCustomerWriteInput customer, boolean commit)
    		  throws BscsInvalidIdException, BscsInvalidFieldException
    		{
    		  try
    		  {
    		    BSCSCustomer newCustomer = this.businessPartnerServiceAdapter.customerNew(customer);
    		    if (commit) {
    		      ConnectionHolder.getCurrentConnection().commit();
    		    }
    		    return newCustomer;
    		  }
    		  catch (SOIException e)
    		  {
    		    this.logger.debug("BSCS CUSTOMER.NEW error with code: " + e.getCode());
    		    return null;
    		  }
    		  
    		}

    		public BSCSPaymentArrangement writePaymentArrangement(BSCSPaymentArrangement payArrang, boolean commit)
    		  throws BscsInvalidIdException, BscsInvalidFieldException
    		{
    		  try
    		  {
    		    BSCSPaymentArrangement payArrangement = this.businessPartnerServiceAdapter.paymentArrangementWrite(payArrang);
    		    if (commit) {
    		      ConnectionHolder.getCurrentConnection().commit();
    		    }
    		    return payArrangement;
    		  }
    		  catch (SOIException e)
    		  {
    		    this.logger.debug("BSCS PAYMENT_ARRANGEMENT.WRITE error with code: " + e.getCode());
    		  }
    		  return null;
    		}
    		
    		public BSCSPaymentArrangementAssignment writePaymentArrangementAssignement(BSCSPaymentArrangementAssignment payArrang, boolean commit)
    	    		  throws BscsInvalidIdException, BscsInvalidFieldException
    	    		{
    	    		  try
    	    		  {
    	    		    BSCSPaymentArrangementAssignment payArrangement = this.businessPartnerServiceAdapter.paymentArrangementAssignementWrite(payArrang);
    	    		    if (commit) {
    	    		      ConnectionHolder.getCurrentConnection().commit();
    	    		    }
    	    		    return payArrangement;
    	    		  }
    	    		  catch (SOIException e)
    	    		  {
    	    		    this.logger.debug("BSCS PAYMENT_ARRANGEMENT_ASSIGNEMENT.WRITE error with code: " + e.getCode());
    	    		  }
    	    		  return null;
    	    		}

    		public void writeCustomer(BSCSCustomerWriteInput customer, boolean commit)
    		  throws BscsInvalidIdException, BscsInvalidFieldException, CMSException
    		{
    		  try
    		  {
    		    this.businessPartnerServiceAdapter.customerWrite(customer);
    		    if (commit) {
    		      ConnectionHolder.getCurrentConnection().commit();
    		    }
    		  }
    		  catch (SOIException e)
    		  {
    		    this.logger.debug("BSCS CUSTOMER.WRITE error with code: " + e.getCode());
    		    throw new CMSException("BSCS CUSTOMER.WRITE error with code: " + e.getCode());
    		  }
    		}
    		
    		public BSCSCustomer writeLAMember(BSCSCustomer customer, boolean commit)
    	    		  throws BscsInvalidIdException, BscsInvalidFieldException
    	    		{
    	    		  
    			     BSCSCustomer result = null;
    				try
    	    		  {
    					result = businessPartnerServiceAdapter.laMemberNewOne(customer);
    	    		    if (commit) {
    	    		      ConnectionHolder.getCurrentConnection().commit();
    	    		    }
    	    		  }
    	    		  catch (SOIException e)
    	    		  {
    	    		    this.logger.debug("BSCS CUSTOMER.WRITE error with code: " + e.getCode());
    	    		  }
    				return result;
    	    		}
    
    		public Long writeAddress(BSCSAddress address, boolean commit)
    				   throws BscsInvalidIdException, BscsInvalidFieldException
    				 {
    				   Long result = null;
    				   try
    				   {
    				     result = Long.valueOf(this.businessPartnerServiceAdapter.addressWrite(address));
    				     if (commit) {
    				       ConnectionHolder.getCurrentConnection().commit();
    				     }
    				   }
    				   catch (SOIException e)
    				   {
    				     this.logger.debug("BSCS ADDRESS_WRITE error with code: " + e.getCode());
    				     if (e.getCode() != null)
    				     {
    				       if ((e.getCode().contains("FUNC_FRMWK_SRV.id0468")) || (e.getCode().contains("RC6701"))) {
    				         throw new BscsInvalidIdException(BscsFieldExceptionEnum.CUSTOMER_ID, e.getFirstArg(), "Invalid customer id: {" + e.getFirstArg() + "}");
    				       }
    				       if (e.getCode().contains("RC6721")) {
    				         throw new BscsInvalidFieldException(BscsFieldExceptionEnum.TITLE, address.getTitleId().toString(), e.getMessage());
    				       }
    				       if (e.getCode().contains("RC6723")) {
    				         throw new BscsInvalidFieldException(BscsFieldExceptionEnum.IDENTIFICATION_TYPE, e.getFirstArg(), e.getMessage());
    				       }
    				       if (e.getCode().contains("BusinessPartner.GenderTitleNotMatch")) {
    				         throw new BscsInvalidFieldException(BscsFieldExceptionEnum.TITLE, null, "Gender and title are not compatible");
    				       }
    				       if (e.getCode().contains("BusinessPartner.MandatoryCharNoFound")) {
    				         throw new BscsInvalidFieldException(BscsFieldExceptionEnum.buildBscsFieldExceptionEnum(e.getArg(1)), null, "Bad field in individual content");
    				       }
    				       if (e.getCode().contains("BusinessPartner.InvalidPostalCode")) {
    				         throw new BscsInvalidFieldException(BscsFieldExceptionEnum.POSTAL_CODE, address.getPostalCode(), "Bad format for zip code");
    				       }
    				     }
    				     throw e;
    				   }
    				   return result;
    				 }
    		
    		public BSCSCustomer searchCustomer(Long csid, String csidpub, boolean syncWithDB)
    	    		{
    				BSCSCustomer customer = businessPartnerServiceAdapter.customerRead(csid, csidpub, syncWithDB);
    				return customer ;

    	    		}	
    		
    		public List<BSCSCustomer> searchCustomer(String csExterId, String csExterSetId, boolean syncWithDB)
    		{
    			BSCSCustomersSearchRequest customerSearchParams = new BSCSCustomersSearchRequest();
    			customerSearchParams.setExternalCustomerId(csExterId);
    			customerSearchParams.setExternalCustomerIdSet(csExterSetId);
			List<BSCSCustomer> customer = businessPartnerServiceAdapter.searchByCriterias(customerSearchParams);
			
			return customer ;

    		}
    		
    		public void writeCustomerHierarchy(Long csId, String csIdPub, Long csIdHigh, String csIdHighPub, Boolean isPaymentResponsible, EnumCustomerLevelCode hierarchyLevel, boolean commit)
    	    		  throws BscsInvalidIdException, BscsInvalidFieldException
    	    		{
    	    		  try
    	    		  {
    	    		    this.businessPartnerServiceAdapter.customerHierarchyWrite(csId, csIdPub, csIdHigh, csIdHighPub, isPaymentResponsible, hierarchyLevel);
    	    		    if (commit) {
    	    		      ConnectionHolder.getCurrentConnection().commit();
    	    		    }
    	    		  }
    	    		  catch (SOIException e)
    	    		  {
    	    		    this.logger.debug("BSCS CUSTOMER.WRITE error with code: " + e.getCode());
    	    		  }
    }
}
