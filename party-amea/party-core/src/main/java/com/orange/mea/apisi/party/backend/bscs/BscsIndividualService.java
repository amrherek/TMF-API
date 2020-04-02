package com.orange.mea.apisi.party.backend.bscs;

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
import com.orange.bscs.model.BscsAddress;
import com.orange.bscs.model.BscsCustomer;
import com.orange.bscs.model.BscsCustomerInfo;
import com.orange.bscs.model.BscsCustomerSearch;
import com.orange.bscs.model.businesspartner.EnumAddressRole;
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
}
