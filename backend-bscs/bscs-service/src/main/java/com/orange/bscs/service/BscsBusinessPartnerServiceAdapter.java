package com.orange.bscs.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.orange.bscs.api.connection.ConnectionHolder;
import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.model.exception.SOIException;
import com.orange.bscs.cms.servicelayeradapter.BusinessPartnerServiceAdapterI;
import com.orange.bscs.model.BscsAddress;
import com.orange.bscs.model.BscsCustomer;
import com.orange.bscs.model.BscsCustomerGroup;
import com.orange.bscs.model.BscsCustomerSearch;
import com.orange.bscs.model.BscsIdentificationDocumentType;
import com.orange.bscs.model.BscsPaymentArrangement;
import com.orange.bscs.model.BscsTitle;
import com.orange.bscs.model.businesspartner.BSCSCustomer;
import com.orange.bscs.model.businesspartner.EnumAddressRole;
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

}
