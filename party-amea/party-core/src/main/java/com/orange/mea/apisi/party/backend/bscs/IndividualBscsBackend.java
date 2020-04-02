package com.orange.mea.apisi.party.backend.bscs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.party.model.Individual;
import com.orange.bscs.api.aop.TransactionalBscs;
import com.orange.bscs.model.BscsAddress;
import com.orange.bscs.model.BscsCustomer;
import com.orange.bscs.model.BscsCustomerInfo;
import com.orange.bscs.model.BscsCustomerSearch;
import com.orange.mea.apisi.party.backend.FindIndividualsByCriteriaBackend;
import com.orange.mea.apisi.party.backend.GetIndividualBackend;
import com.orange.mea.apisi.party.backend.UpdateIndividualBackend;
import com.orange.mea.apisi.party.backend.bscs.transformer.BscsAddressToIndividualTransformer;
import com.orange.mea.apisi.party.backend.bscs.transformer.BscsCustomerInfoToIndividualTransformer;
import com.orange.mea.apisi.party.backend.bscs.transformer.IndividualToBscsAddressTransformer;
import com.orange.mea.apisi.party.backend.bscs.transformer.IndividualToBscsCustomerInfoTransformer;
import com.orange.mea.apisi.party.beans.IndividualFilteringCriteria;

/**
 * Service for Individual requests management
 *
 * @author xbbs3851
 *
 */
@Service
public class IndividualBscsBackend
        implements FindIndividualsByCriteriaBackend, GetIndividualBackend, UpdateIndividualBackend {

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

    /**
     * If true, the BSCS command CUSTOMER_INFO.READ will be called
     */
    protected boolean customerInfoNeeded = false;

    /**
     * Returns an individual using its id (get)
     *
     * @param id
     * @return
     * @throws ApiException
     */
    @Override
    @TransactionalBscs
    public Individual getIndividual(final String id) throws ApiException {
        final BscsAddress address = bscsIndividualService.getBscsAddress(id);
        final BscsCustomer customer = bscsIndividualService.getBscsCustomer(id);

        BscsCustomerInfo customerInfo = null;
        if (customerInfoNeeded) {
            customerInfo = bscsIndividualService.getCustomerInfo(id);
        }

        Individual individual = bscsAddressToIndividualTransformer.doTransform(address, customer);
        if (customerInfoNeeded) {
            bscsCustomerInfoToIndividualTransformer.doTransform(customerInfo, individual);
        }
        return individual;
    }

    /**
     * Returns a list of individual matching the given filters (find)
     *
     * @return
     * @throws ApiException
     */
    @Override
    @TransactionalBscs
    public List<Individual> findIndividualsByCriteria(final IndividualFilteringCriteria criteria)
            throws ApiException {
        final List<BscsCustomerSearch> customerSearches = bscsIndividualService.findCustomersByCriteria(criteria);

        List<Individual> result = new ArrayList<Individual>();

        for(BscsCustomerSearch customerSearch : customerSearches) {
            final BscsAddress address = bscsIndividualService.getBscsAddress(customerSearch.getId());
            final BscsCustomer customer = bscsIndividualService.getBscsCustomer(customerSearch.getId());
            BscsCustomerInfo customerInfo = null;
            if (customerInfoNeeded) {
                customerInfo = bscsIndividualService.getCustomerInfo(customerSearch.getId());
            }

            Individual individual = bscsAddressToIndividualTransformer.doTransform(address, customer);
            if (customerInfoNeeded) {
                bscsCustomerInfoToIndividualTransformer.doTransform(customerInfo, individual);
            }
            result.add(individual);
        }

        return result;

    }

    /**
     * Updates an existing individual
     *
     * @throws ApiException
     */
    @Override
    @TransactionalBscs
    public void updateIndividual(final Individual individual) throws ApiException {

        final BscsAddress bscsAddress = individualToBscsAddressTransformer.doTransform(individual);
        bscsIndividualService.updateIndividual(bscsAddress);

        if (customerInfoNeeded) {
            final BscsCustomerInfo infos = individualToBscsCustomerInfoTransformer.doTransform(individual);
            if (infos != null) {
                bscsIndividualService.updateCustomerInfo(infos);
            }
        }
    }

}
