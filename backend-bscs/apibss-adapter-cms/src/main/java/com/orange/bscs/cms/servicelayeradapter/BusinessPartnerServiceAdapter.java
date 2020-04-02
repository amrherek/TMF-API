package com.orange.bscs.cms.servicelayeradapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.orange.bscs.api.connection.ConnectionHolder;
import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;
import com.orange.bscs.commands.BaseDAO;
import com.orange.bscs.model.Caches;
import com.orange.bscs.model.businesspartner.BSCSAddress;
import com.orange.bscs.model.businesspartner.BSCSAddresses;
import com.orange.bscs.model.businesspartner.BSCSCustomer;
import com.orange.bscs.model.businesspartner.BSCSCustomerWriteInput;
import com.orange.bscs.model.businesspartner.BSCSCustomersSearchRequest;
import com.orange.bscs.model.businesspartner.BSCSPaymentArrangement;
import com.orange.bscs.model.businesspartner.EnumAddressRole;
import com.orange.bscs.model.businesspartner.EnumCustomerLevelCode;

/**
 * <pre>
 * {@code
 * ADDRESS.CHECK
 * ADDRESS.READ
 * ADDRESS.WRITE
 * ADDRESSES.READ
 * ADDRESSROLES.READ
 * AREAS.READ
 * CUST_RATEPLAN_HISTORY.READ
 * CUSTCATCODE.READ
 * CUSTOMER_DOCUMENTTYPES.READ
 * CUSTOMER_DOCUMENTTYPES.WRITE
 * CUSTOMER_FLAT.WRITE
 * CUSTOMER_HIERARCHY.WRITE
 * CUSTOMER_LEVELS.READ
 * CUSTOMER_STATES.READ
 * CUSTOMER_TAX_EXEMPTIONS.READ
 * CUSTOMER_TAX_EXEMPTIONS.WRITE
 * CUSTOMERS.SEARCH
 * DEALERS.READ
 * DEALERS.SEARCH
 * IDENTIFICATION_DOCUMENT_TYPE.READ
 * INDIVIDUAL_TAXATION_HISTORY.READ
 * LA_MEMBER.NEW
 * LANGUAGES.READ
 * MARITAL_STATUS.READ
 * PAYMENT_ARRANGEMENT.WRITE
 * PAYMENT_ARRANGEMENTS.READ
 * TITLES.READ
 * TRADES.READ
 * }
 * </pre>
 * 
 * And others commands
 * 
 * *
 * 
 * <pre>
 * {@code
 * AddressConvert
 * AssignedResourcesCheck
 * BusinessUnitChange
 * BusinessUnitCheck
 * CostcentersRead
 * CustomerClone
 * CustomerDuplicateCheck
 * CustomerExport
 * CustomerGroupsRead
 * CustomerImport
 * CustomerMailItemDelete
 * CustomerMailItemRead
 * CustomerMailItemWrite
 * CustomerNew
 * CustomerRead
 * CustomerWrite
 * DealerDataMove
 * DummyCustomerNew
 * DummyCustomerRead
 * OutboundDialedDigitsRead
 * PartyRoleConfigurationRead
 * PosconnectRead
 * TrunkRead
 * }
 * </pre>
 * 
 * @author IT&Labs
 * 
 */
@Repository
public class BusinessPartnerServiceAdapter extends BaseDAO implements BusinessPartnerServiceAdapterI {
    
	/** {@inheritDoc} */
    @Cacheable(value = Caches.REFERENTIAL, key = QUOTE + Constants.CMD_CUSTCATCODE_READ + QUOTE)
    @Override
    public Map<Long, String> custCatCodeReadAsMap() {
        List<BSCSModel> custCatCodeReadResList = execute(Constants.CMD_CUSTCATCODE_READ).getListOfBSCSModelValue(
                Constants.P_LIST_OF_CUSTOMER_CATEGORIES);
        Map<Long, String> result = new HashMap<Long, String>();
        for (BSCSModel elt : custCatCodeReadResList) {
            result.put(elt.getLongValue(Constants.P_CUSTCAT_CODE), elt.getStringValue(Constants.P_CUSTCAT_DES));
        }
        return result;
    }

    /** {@inheritDoc} */
    @Cacheable(value = Caches.REFERENTIAL, key = QUOTE + Constants.CMD_LANGUAGES_READ + QUOTE)
    @Override
    public List<BSCSModel> languagesRead() {
        return execute(Constants.CMD_LANGUAGES_READ).getListOfBSCSModelValue(Constants.P_LIST_OF_LANGUAGES);
    }

    /** {@inheritDoc} */
    @Cacheable(value = Caches.REFERENTIAL, key = QUOTE + Constants.CMD_MARITAL_STATUS_READ + QUOTE)
    @Override
    public List<BSCSModel> maritalStatusRead() {
        return execute(Constants.CMD_MARITAL_STATUS_READ).getListOfBSCSModelValue(Constants.P_LIST_OF_MARITAL_STATUS);
    }

    /** {@inheritDoc} */
    @Cacheable(value = Caches.REFERENTIAL, key = QUOTE + Constants.CMD_TITLES_READ + QUOTE)
    @Override
    public List<BSCSModel> titlesRead() {
        return execute(Constants.CMD_TITLES_READ).getListOfBSCSModelValue(Constants.P_LIST_OF_TITLES);
    }

    @Cacheable(value = Caches.REFERENTIAL, key = QUOTE + Constants.CMD_IDENTIFICATION_DOCUMENT_TYPE_READ + QUOTE)
    @Override
    public List<BSCSModel> documentTypesRead() {
        return execute(Constants.CMD_IDENTIFICATION_DOCUMENT_TYPE_READ).getListOfBSCSModelValue(Constants.P_LIST_OF_IDENTIFICATION_DOCUMENT_TYPE);
    }

    @Override
    @Cacheable(value = Caches.REFERENTIAL, key = QUOTE + Constants.CMD_CUSTOMER_GROUPS_READ + QUOTE)
    public List<BSCSModel> customerGroupsRead() {
        BSCSModel res = execute(Constants.CMD_CUSTOMER_GROUPS_READ);
        if (null != res) {
            return res.getListOfBSCSModelValue(Constants.P_LIST_OF_CUSTOMER_GROUPS);
        }
        return new ArrayList<BSCSModel>();
    }

    /** {@inheritDoc} */
    @Override
    public List<BSCSPaymentArrangement> paymentArrangementsRead(Long csId, String csIdPub, Boolean flagCurrent) {
        BSCSPaymentArrangement input = new BSCSPaymentArrangement();
        if (null != csIdPub) {
            input.setCustomerIDPub(csIdPub);
        } else {
            input.setCustomerID(csId);
        }
        input.setSearchCurrent(flagCurrent);

        BSCSModel out = ConnectionHolder.getCurrentConnection().execute(Constants.CMD_PAYMENT_ARRANGEMENTS_READ, input);
        List<BSCSPaymentArrangement> ass = out.getListOfBSCSModelValue(Constants.P_LIST_OF_PAYMENT_ARRANGEMENTS,
                BSCSPaymentArrangement.class);

        return ass;
    }

    /**
     * {@inheritDoc}
     * 
     * Execute paymentArrangementWrite
     * 
     * result : pa.getId will contains new Id (if pa.seqNumber was =0)
     */
    @Override
    public BSCSPaymentArrangement paymentArrangementWrite(BSCSPaymentArrangement pa) {
        BSCSPaymentArrangement res = ConnectionHolder.getCurrentConnection().execute(Constants.CMD_PAYMENT_ARRANGEMENT_WRITE, pa,
                BSCSPaymentArrangement.class);
        pa.setId(res.getId());
        pa.setSequenceNumber(res.getSequenceNumber());
        return pa;
    }

    /**
     * {@inheritDoc}
     * 
     * Read all addresses of a particular customer.
     * 
     * <pre>
     * {@code
     * ADDRESSES.READ {
     *   CS_ID                =  : java.lang.Long
     *   CS_ID_PUB            =  : java.lang.String
     * }
     * => {
     *   CS_ID                =  : java.lang.Long
     *   CS_ID_PUB            =  : java.lang.String
     *   LIST_OF_ALL_ADDRESSES = sub element
     * }
     * }
     * </pre>
     */
    @Override
    public BSCSAddresses addressesRead(Long csid, String csidpub) {
        BSCSCustomer input = new BSCSCustomer();
        if (null != csidpub) {
            input.setCustomerIDPub(csidpub);
        } else {
            input.setCustomerID(csid);
        }

        BSCSAddresses adrs = execute(Constants.CMD_ADDRESSES_READ, input, BSCSAddresses.class);

        // Fixe, this command doesn't return cs_id_pub
        if (null != csidpub) {
            adrs.setStringValue(Constants.P_CS_ID_PUB, csidpub);
        }
        return adrs;
    }

    /** {@inheritDoc} */
    @Override
    public BSCSAddress addressRead(Long csid, String csidpub, EnumAddressRole role) {
        BSCSAddress input = new BSCSAddress();
        if (null != csidpub) {
            input.setCustomerIdPub(csidpub);
        } else {
            input.setCustomerId(csid);
        }
        input.setAddressReadRole(EnumAddressRole.toCharacter(role));

        return execute(Constants.CMD_ADDRESS_READ, input, BSCSAddress.class);
    }

    /**
     * {@inheritDoc}
     * 
     * Writes the given address data to a customer's address (Party). .
     * <p>
     * Be aware of the handling of the ADR_SEQ (CCONTACT_ALL.CCSEQ): .
     * <ul>
     * <li>n = 0 : Create a new address.
     * <li>n >= 1 : Update address with given seq_no. .
     * 
     * <p>
     * Note: The creation of an UserInstallationAddress is handled in a seperate
     * command.
     * <p>
     * 
     * <pre>
     * {@code 
     * ADDRESS.WRITE { 
     *   BSCSAddress
     * } => { 
     *   ADR_SEQ = : java.lang.Long 
     * }
     * }
     * </pre>
     */
    @Override
    public long addressWrite(BSCSAddress addrWrite) {
        BSCSModel out = execute(Constants.CMD_ADDRESS_WRITE, addrWrite);
        return out.getLongValue(Constants.P_ADR_SEQ);

    }
    
    @Override
    public void addressWriteNoReturn(BSCSAddress addrWrite) {
    	execute(Constants.CMD_ADDRESS_WRITE, addrWrite);
    }

    /** {@inheritDoc} */
    @Override
    public void customerFlatWrite(Long csid, String csidpub) {
        BSCSCustomer input = new BSCSCustomer();
        if (null != csidpub) {
            input.setCustomerIDPub(csidpub);
        } else {
            input.setCustomerID(csid);
        }
        execute(Constants.CMD_CUSTOMER_FLAT_WRITE, input);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.orange.bscs.bl.dao.customer.CustomerDAO#customerHierarchyWrite(java
     * .lang.String, java.lang.String)
     */
    /** {@inheritDoc} */
    @Override
    public void customerHierarchyWrite(Long csId, String csIdPub, Long csIdHigh, String csIdHighPub, Boolean isPaymentResponsible,
            EnumCustomerLevelCode hierarchyLevel) {
        BSCSCustomer input = new BSCSCustomer();
        if (null != csIdPub) {
            input.setCustomerIDPub(csIdPub);
        } else {
            input.setCustomerID(csId);
        }
        if (null != csIdHighPub) {
            input.setParentCustomerIdPub(csIdHighPub);
        } else {
            input.setParentCustomerId(csIdHigh);
        }
        input.setIsPaymentResponsible(isPaymentResponsible);
        input.setCustomerLevelCode(hierarchyLevel);

        execute(Constants.CMD_CUSTOMER_HIERARCHY_WRITE, input);
    }

    /** {@inheritDoc} */
    @Override
    public BSCSCustomer customerNew(BSCSCustomerWriteInput customer) {
        return execute(Constants.CMD_CUSTOMER_NEW, customer, BSCSCustomer.class);
    }

    /** {@inheritDoc} */
    @Override
    public BSCSCustomer customerRead(Long csid, String csidpub, boolean syncWithDB) {
        BSCSCustomer search = new BSCSCustomer();
        if (null != csidpub) {
            search.setCustomerIDPub(csidpub);
        } else {
            search.setCustomerID(csid);
        }
        if (syncWithDB) {
            search.setBooleanValue(Constants.P_SYNC_WITH_DB, syncWithDB);
        }
        return execute(Constants.CMD_CUSTOMER_READ, search, BSCSCustomer.class);
    }

	/** {@inheritDoc} */
	@Override
	public List<BSCSCustomer> searchByCriterias(BSCSCustomersSearchRequest customerSearchParams) {
		BSCSModel out = customerSearch(customerSearchParams);
		return out.getListOfBSCSModelValue(Constants.P_SEARCH_RESULT, BSCSCustomer.class);
	}

    /** {@inheritDoc} */
    @Override
    public BSCSModel customerSearch(BSCSCustomersSearchRequest customerSearchParams) {
        return execute(Constants.CMD_CUSTOMERS_SEARCH, customerSearchParams);
    }

    /** {@inheritDoc} */
    @Override
    public void customerWrite(BSCSCustomerWriteInput customer) {
        execute(Constants.CMD_CUSTOMER_WRITE, customer);
    }

	/**
	 * {@inheritDoc}
	 *
     * @return */
    @Override
    public BSCSCustomer laMemberNew(String flatCsIdPub, String parentLAIdPub, EnumCustomerLevelCode csLevelCode, Boolean isPaymentResponsible) {
        BSCSCustomer input = new BSCSCustomer();
        input.setCustomerIDPub(flatCsIdPub);
        input.setParentCustomerIdPub(parentLAIdPub);
        input.setCustomerLevelCode(csLevelCode);
        input.setIsPaymentResponsible(isPaymentResponsible);

        return execute(Constants.CMD_LA_MEMBER_NEW, input, BSCSCustomer.class);
    }

	/**
	 * {@inheritDoc}
	 *
     * @return */
    @Override
    public BSCSCustomer laMemberNew(Long flatCsId, Long parentLAId, EnumCustomerLevelCode csLevelCode, Boolean isPaymentResponsible) {
        BSCSCustomer input = new BSCSCustomer();
        input.setCustomerID(flatCsId);
        input.setParentCustomerId(parentLAId);
        input.setCustomerLevelCode(csLevelCode);
        input.setIsPaymentResponsible(isPaymentResponsible);

        return execute(Constants.CMD_LA_MEMBER_NEW, input, BSCSCustomer.class);
    }
    
}

