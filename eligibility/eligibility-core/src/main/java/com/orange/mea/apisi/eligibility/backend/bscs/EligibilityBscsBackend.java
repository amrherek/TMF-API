package com.orange.mea.apisi.eligibility.backend.bscs;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.badrequest.NotFoundException;
import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.apibss.eligibility.model.EligibleContract;
import com.orange.apibss.eligibility.model.EligibleOption;
import com.orange.bscs.api.aop.TransactionalBscs;
import com.orange.bscs.model.BscsContract;
import com.orange.bscs.model.BscsContractSearch;
import com.orange.bscs.model.BscsRatePlan;
import com.orange.bscs.model.BscsService;
import com.orange.bscs.service.BscsContractServiceAdapter;
import com.orange.bscs.service.BscsProductServiceAdapter;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;
import com.orange.mea.apisi.eligibility.backend.FindEligibleContractsBackend;
import com.orange.mea.apisi.eligibility.backend.FindEligibleOptionsBackend;
import com.orange.mea.apisi.eligibility.constants.EligibilityConstants;

/**
 * Eligibility service
 *
 * @author Thibault Duperron
 *
 */
@Service
public class EligibilityBscsBackend implements FindEligibleOptionsBackend, FindEligibleContractsBackend {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected BscsContractServiceAdapter contractServiceAdapter;
    @Autowired
    protected BscsProductServiceAdapter productServiceAdapter;
    @Autowired
    protected EligibilityBscsTransformer eligiblityTransformer;

    /**
     *
     * @param msisdn
     * @param productSpecId
     * @return
     * @throws TechnicalException
     */
    @Override
    @TransactionalBscs
    public List<EligibleOption> findEligibleOptions(final String msisdn, final String productSpecId)
            throws TechnicalException {
        if (productSpecId == null || EligibilityConstants.SERVICE_BSCS.equals(productSpecId)) {
            BscsContractSearch contract;
            try {
                contract = contractServiceAdapter.findContractByMsisdn(msisdn);
            } catch (BscsInvalidFieldException e) {
                logger.debug("Unkown msisdn", e);
                return new ArrayList<>();
            }
            BscsContract contractFull;
            try {
                contractFull = contractServiceAdapter.getContract(contract.getId());
            } catch (BscsInvalidIdException e) {
                throw new TechnicalException(e.getMessage(), e);
            }
            final List<BscsService> services = productServiceAdapter.getAvailableServicesFromContract(contractFull);

            return eligiblityTransformer.transformToEligibleOption(services);
        }
        return new ArrayList<>();
    }

    @Override
    @TransactionalBscs
    public EligibleContract getAvailableOfferForMigration(String msisdn) throws ApiException {
        BscsContractSearch contractSearch;
        try {
            contractSearch = contractServiceAdapter.findContractByMsisdn(msisdn);
        } catch (BscsInvalidFieldException e) {
            logger.debug("Unknown msisdn", e);
            throw new NotFoundException("Unknown msisdn: " + msisdn);
        }
        // note: payment method indicator is only available from BSCS IXR4
        List<BscsRatePlan> ratePlans = productServiceAdapter.getAllowedRatePlansFromContract(contractSearch.getId(),
                contractSearch.getPaymentMethodIndicator());
        // filter actual rateplan
        List<BscsRatePlan> migrationRatePlans = new ArrayList<>();
        for (BscsRatePlan ratePlan : ratePlans) {
            if (!ratePlan.getNumericCode().equals(contractSearch.getNumericRatePlanCode())) {
                migrationRatePlans.add(ratePlan);
            }
        }
        return eligiblityTransformer.transformToEligibleContract(migrationRatePlans);
    }

}
