package com.orange.mea.apisi.productinventory.backend.bscs;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.technical.NotImplementedException;
import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.bscs.model.BscsBillingAssignment;
import com.orange.bscs.model.BscsContract;
import com.orange.bscs.model.BscsContractInfo;
import com.orange.bscs.model.BscsContractSearch;
import com.orange.bscs.model.BscsContractService;
import com.orange.bscs.model.BscsContractServiceParameter;
import com.orange.bscs.model.BscsRatePlan;
import com.orange.bscs.model.BscsService;
import com.orange.bscs.model.BscsServicePackage;
import com.orange.bscs.service.BscsBillingServiceAdapter;
import com.orange.bscs.service.BscsContractServiceAdapter;
import com.orange.bscs.service.BscsParameterServiceAdapter;
import com.orange.bscs.service.BscsProductServiceAdapter;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;
import com.orange.mea.apisi.productinventory.exception.RatePlanNotFoundException;

/**
 * Client for bscs calls
 *
 * @author Thibault Duperron
 *
 */
@Service
public class BscsFacadeService {

    @Value("${profileId:0}")
    protected Long profileId;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected BscsContractServiceAdapter contractAdapter;
    @Autowired
    protected BscsProductServiceAdapter productAdapter;
    @Autowired
    protected BscsParameterServiceAdapter parameterAdapter;
    @Autowired
    protected BscsBillingServiceAdapter billingServiceAdapter;

    /**
     *
     * @param ratePlanPublicCode
     *            public code of the rate plan
     * @return first rate plan which match the public code
     * @throws RatePlanNotFoundException
     */
    public BscsRatePlan getRatePlans(final String ratePlanPublicCode) throws RatePlanNotFoundException {
        BscsRatePlan ratePlanModel;
        try {
            ratePlanModel = productAdapter.getRatePlan(ratePlanPublicCode);
        } catch (BscsInvalidIdException e) {
            logger.debug("invalid ratePlanPublicCode", e);
            throw new RatePlanNotFoundException(ratePlanPublicCode);
        }
        return ratePlanModel;
    }

    /**
     *
     * @param contract
     *            contract with rate plan public code
     * @return rate plan from {@link #getRatePlans(String)}
     * @throws RatePlanNotFoundException
     */
    public BscsRatePlan getRatePlans(final BscsContract contract) throws RatePlanNotFoundException {
        return getRatePlans(contract.getRatePlanCode());
    }

    /**
     * Search contract services by contract Id
     *
     * @param contractId
     *            contract's id
     * @return list of {@link BscsContractService}
     */
    public List<BscsContractService> findContractServiceByContractId(final Long contractId) {
        return contractAdapter.getServicesByContractId(contractId);
    }

    /**
     * Find a contract service from coIdPub and snCodePub
     *
     * @param contractId
     * @param serviceId
     * @return
     * @throws BscsInvalidFieldException
     * @throws BscsInvalidIdException
     */
    public BscsContractService findContractServiceByContractIdAndSncode(final String contractId,
            final String serviceId) throws BscsInvalidIdException, BscsInvalidFieldException {
        return contractAdapter.getContractService(contractId, serviceId);
    }

    /**
     * Find parameters for a service
     *
     * @param service
     * @return
     * @throws NotImplementedException
     */
    public List<BscsContractServiceParameter> findContractServiceWithParameters(final BscsContractService service)
            throws NotImplementedException {
        return parameterAdapter.getContractServiceParameters(service.getContractId(), profileId, service.getCode());
    }


    /**
     *
     * @param servicePackageCode
     *            service package's public code
     * @return the service package
     * @throws BscsInvalidIdException
     */
    public BscsServicePackage findServicePackageById(final String servicePackageCode) throws BscsInvalidIdException {
        return productAdapter.getServicePackage(servicePackageCode);
    }

    /**
     * Return the BscsService of a contract service
     * 
     * @param contractDetail
     * @param service
     * @return
     * @throws BscsInvalidFieldException
     * @throws BscsInvalidIdException
     */
    public BscsService getServiceByContractAndContractService(final BscsContract contractDetail,
            final BscsContractService service) throws BscsInvalidFieldException, BscsInvalidIdException {
        return productAdapter.getServiceByRatePlanServicePackageAndServiceId(contractDetail.getRatePlanCode(),
                service.getServicePackageCode(), service.getCode());
    }

    public List<BscsBillingAssignment> getBillingAccounts(final BscsContract contractDetail) {
        return billingServiceAdapter.getBillingAssignmentByContract(contractDetail.getId());
    }

    public List<BscsContractSearch> findCurrentContractsByMsisdn(String msisdn) throws BscsInvalidFieldException {
        return contractAdapter.findContractsByMsisdn(msisdn);
    }

    public BscsContract getContract(Long numericId) throws BscsInvalidIdException {
        return contractAdapter.getContract(numericId);
    }

    public BscsContract getContract(String contractId) throws BscsInvalidIdException {
        return contractAdapter.getContract(contractId);
    }

    public List<BscsContractSearch> findContractsByIccId(String iccId) throws BscsInvalidFieldException {
        return contractAdapter.findContractsByIccId(iccId);
    }

    public BscsContractSearch findContractById(String productId) throws BscsInvalidIdException {
        return contractAdapter.findContractById(productId);
    }

    public List<BscsContractSearch> findContractByPartyId(Long partyId) throws TechnicalException {
        return contractAdapter.findContractsByPartyId(partyId);
    }

    public List<BscsContractService> getServicesByContractIdAndServicePackageId(String contractId,
            String servicePackageId) {
        return contractAdapter.getServicesByContractIdAndServicePackageId(contractId, servicePackageId);
    }

    public BscsContractInfo getContractInfo(Long numericId) throws BscsInvalidIdException {
        return contractAdapter.getContractInfo(numericId);
    }

    /**
     * Get msisdn from contract id
     * 
     * @param contractId
     * @return
     * @throws TechnicalException
     */
    public String getMsisdn(String contractId) throws TechnicalException {
        try {
            BscsContractSearch contract = findContractById(contractId);
            return contract.getDirectoryNumber();
        } catch (BscsInvalidIdException e) {
            throw new TechnicalException(e.getMessage(), e);
        }
    }

}
