package com.orange.bscs.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.orange.bscs.api.connection.ConnectionHolder;
import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.model.exception.SOIException;
import com.orange.bscs.cms.servicelayeradapter.ContractServiceAdapterI;
import com.orange.bscs.model.BscsBalance;
import com.orange.bscs.model.BscsContract;
import com.orange.bscs.model.BscsContractFreeUnitAccount;
import com.orange.bscs.model.BscsContractInfo;
import com.orange.bscs.model.BscsContractResource;
import com.orange.bscs.model.BscsContractSearch;
import com.orange.bscs.model.BscsContractService;
import com.orange.bscs.model.contract.BSCSContract;
import com.orange.bscs.model.contract.BSCSContractService;
import com.orange.bscs.model.contract.BSCSProductChange;
import com.orange.bscs.model.criteria.BscsContractSearchCriteria;
import com.orange.bscs.model.criteria.BscsContractServiceSearchCriteria;
import com.orange.bscs.model.factory.BscsObjectFactory;
import com.orange.bscs.service.exception.BscsFieldExceptionEnum;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;
import com.orange.bscs.service.exception.BscsInvalidStateException;
import com.orange.bscs.service.exception.BscsPendingException;

/**
 *
 *
 *
 * @author Thibault Duperron
 *
 */
public abstract class BscsContractServiceAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected ContractServiceAdapterI contractServiceAdapter;

    @Autowired
    protected BscsObjectFactory bscsObjectFactory;

    // #### CONTRACTS.SEARCH ####

    /**
     * Find the latest contract associated to this msisdn (CONTRACTS.SEARCH)
     *
     * @param msisdn
     *            contract's msisdn
     * @return all contract with this msisdn
     * @throws BscsInvalidFieldException
     */
    public BscsContractSearch findContractByMsisdn(final String msisdn) throws BscsInvalidFieldException {
        List<BscsContractSearch> contracts = findContractsByMsisdn(msisdn);
        if (contracts.isEmpty()) {
            throw new BscsInvalidFieldException(BscsFieldExceptionEnum.MSISDN, msisdn,
                    "No contract found with misdn {" + msisdn + "}");
        }
        if (contracts.size() > 1) {
            logger.error(contracts.size() + " contracts currently assigned to msisdn {" + msisdn + "}");
        }
        return contracts.get(0);
    }

    /**
     * Find a list of contracts associated to the specified msisdn (may contain
     * * char)
     * 
     * @param msisdn
     * @return
     */
    public abstract List<BscsContractSearch> findContractsByMsisdn(final String msisdn);

    /**
     * Search a contract by IccId (CONTRACTS.SEARCH), return the latest one
     *
     * @param iccId
     *            the icc ID
     * @return all contract with this iccid
     * @throws BscsInvalidFieldException
     */
    public BscsContractSearch findContractByIccId(final String iccId) throws BscsInvalidFieldException {
        List<BscsContractSearch> contracts = findContractsByIccId(iccId);
        if (contracts.isEmpty()) {
            throw new BscsInvalidFieldException(BscsFieldExceptionEnum.STORAGE_MEDIUM, iccId,
                    "No contract found with iccId {" + iccId + "}");
        }
        if (contracts.size() > 1) {
            logger.error(contracts.size() + " contracts currently assigned to iccId {" + iccId + "}");
        }
        return contracts.get(0);
    }
    
    /**
     * * Find a list of contracts associated to the specified iccId (may contain
     * * char)
     * 
     * @param iccId
     * @return
     */
    public abstract List<BscsContractSearch> findContractsByIccId(final String iccId);

    /**
     * Search contracts by party Id (CONTRACTS.SEARCH)
     *
     * @param partyId
     *            the party's id
     * @return all contracts of this party id
     */
    public List<BscsContractSearch> findContractsByPartyId(final Long partyId) {
        final BscsContractSearchCriteria contractSearchCriteria = bscsObjectFactory.createBscsContractSearchCriteria();
        contractSearchCriteria.setCustomerNumericId(partyId);
        return findContracts(contractSearchCriteria);
    }

    /**
     * Search contract based on its id (CONTRACTS.SEARCH)
     *
     * @param contractId
     * @return
     * @throws BscsInvalidIdException
     */
    public BscsContractSearch findContractById(final String contractId) throws BscsInvalidIdException {
        final BscsContractSearchCriteria contractSearchCriteria = bscsObjectFactory.createBscsContractSearchCriteria();
        contractSearchCriteria.setId(contractId);
        List<BscsContractSearch> contracts = findContracts(contractSearchCriteria);
        if (contracts.size() > 1) {
            logger.error(contracts.size() + " contracts exist with id {" + contractId + "}");
        }
        if (!contracts.isEmpty()) {
            return contracts.get(0);
        }
        // contract not found, based on its id
        throw new BscsInvalidIdException(BscsFieldExceptionEnum.CONTRACT_ID, contractId,
                "No contract found with id {" + contractId + "}");
    }

    /**
     * Search a contract based on the criteria (CONTRACTS.SEARCH)
     *
     * @param contractSearchCriteria
     *            search criteria
     * @return all matching contract
     */
    protected List<BscsContractSearch> findContracts(final BscsContractSearchCriteria contractSearchCriteria) {
        List<BscsContractSearch> contractModels = new ArrayList<>();
        try {
            List<BSCSContract> contracts = contractServiceAdapter.contractSearch(contractSearchCriteria.getBscsModel());
            for (BSCSContract contract : contracts) {
                contractModels.add(bscsObjectFactory.createBscsContractSearch(contract));
            }
        } catch (final SOIException exception) {
            if (exception.getCode() != null && exception.getCode().contains("RC2015")) {
                // no contract found
            } else
                throw exception;
        }
        return contractModels;
    }

    // #### CONTRACT.READ ####

    /**
     * Search contract with bdd sync ON (CONTRACT.READ)
     *
     * @param contractId
     *            contract's id
     * @param contractIdPub
     *            public contract's id
     * @return the contract
     * @throws BscsInvalidIdException
     */
    protected BscsContract getContract(final Long contractId, final String contractIdPub)
            throws BscsInvalidIdException {
        try {
            final BSCSContract bscsContract = contractServiceAdapter.contractRead(contractId, contractIdPub, true);
            return bscsObjectFactory.createBscsContract(bscsContract);
        } catch (final SOIException exception) {
            if (exception.getCode() != null && (exception.getCode().contains("RC6700")
                    || exception.getCode().contains("FUNC_FRMWK_SRV.id0468"))) {
                // contract not found, based on its id
                String coId = contractIdPub != null ? contractIdPub : contractId.toString();
                throw new BscsInvalidIdException(BscsFieldExceptionEnum.CONTRACT_ID, coId,
                        "No contract found with id {" + coId + "}");
            } else {
                throw exception;
            }
        }
    }

    /**
     * Read contract based on its String id (CONTRACT.READ)
     *
     * @param contractId
     * @return
     * @throws BscsInvalidIdException
     */
    public BscsContract getContract(final String contractId) throws BscsInvalidIdException {
        return getContract(null, contractId);
    }

    /**
     * Read contract based on its numeric id (CONTRACT.READ)
     *
     * @param contractId
     * @return
     * @throws BscsInvalidIdException
     */
    public BscsContract getContract(final Long contractId) throws BscsInvalidIdException {
        return getContract(contractId, null);
    }

    // #### CONTRACT.WRITE ####

    /**
     * Execute CONTRACT.WRITE and optionally commit
     * 
     * @param contract
     * @param commit
     * @throws BscsInvalidIdException
     * @throws BscsInvalidFieldException
     * @throws BscsPendingException
     */
    public void writeContract(BscsContract contract, boolean commit)
            throws BscsInvalidIdException, BscsInvalidFieldException, BscsPendingException {
        try {
            contractServiceAdapter.contractWrite(contract.getBscsModel());
            if (commit) {
                ConnectionHolder.getCurrentConnection().commit();
            }
        } catch (final SOIException e) {
            logger.debug("BSCS CONTRACT.WRITE error with code: " + e.getCode());
            if (e.getCode() != null) {
                if (e.getCode().contains("RC6700") || e.getCode().contains("FUNC_FRMWK_SRV.id0468")) {
                    throw new BscsInvalidIdException(BscsFieldExceptionEnum.CONTRACT_ID, e.getFirstArg(),
                            "Invalid contract id: {" + e.getFirstArg() + "}");
                }
                if (e.getCode().contains("RC6707")) {
                    if (e.getFirstArg() == null) {
                        // invalid state transition
                        throw new BscsInvalidFieldException(BscsFieldExceptionEnum.STATUS, null,
                                "Invalid state transition");
                    }
                    throw new BscsInvalidFieldException(BscsFieldExceptionEnum.REASON, e.getFirstArg(),
                            "Unknown reason");
                }
                if (e.getCode().contains("RC6004-003") || e.getCode().contains("InvalidReasonForStateTransition")) {
                    // reason does not match state transition
                    throw new BscsInvalidFieldException(BscsFieldExceptionEnum.REASON, e.getFirstArg(),
                            "Invalid reason");
                }
                if (e.getCode().contains("RC6005-001") || e.getCode().contains("InvalidStateTransition")
                        || e.getCode().contains("InvalidState")) {
                    throw new BscsInvalidFieldException(BscsFieldExceptionEnum.STATUS, e.getFirstArg(),
                            "Invalid state transition");
                }
                if (e.getCode().contains("RC5008-002") || e.getCode().contains("RC5008-003")
                        || e.getCode().contains("RC6246") || e.getCode().contains("PendingContract")) {
                    throw new BscsPendingException(e.getMessage());
                }
            }
            throw e;
        }
    }

    // #### CONTRACT_SERVICES.READ ####

    /**
     * Execute CONTRACT_SERVICES.READ
     * 
     * @param contractId
     * @param serviceId
     * @return
     * @throws BscsInvalidFieldException
     * @throws BscsInvalidIdException
     */
    public BscsContractService getContractService(final String contractId, final String serviceId)
            throws BscsInvalidIdException, BscsInvalidFieldException {
        try {
            final BscsContractServiceSearchCriteria criteria = bscsObjectFactory
                    .createBscsContractServiceSearchCriteria();
            criteria.setCode(serviceId);
            criteria.setContractId(contractId);
            return getContractService(criteria);
        } catch (BscsInvalidFieldException e) {
            if (e.getName() != null && e.getName() == BscsFieldExceptionEnum.SERVICE_ID) {
                throw new BscsInvalidFieldException(e.getName(), serviceId, "The service with id {" + serviceId
                        + "} was not found in the contract with id {" + contractId + "}");
            }
            throw e;
        }
    }

    /**
     * Execute CONTRACT_SERVICES.READ
     * 
     * @param contractId
     * @param serviceId
     * @return
     * @throws BscsInvalidFieldException
     * @throws BscsInvalidIdException
     */
    public BscsContractService getContractService(final Long contractId, final Long serviceId)
            throws BscsInvalidIdException, BscsInvalidFieldException {
        final BscsContractServiceSearchCriteria criteria = bscsObjectFactory.createBscsContractServiceSearchCriteria();
        criteria.setNumericCode(serviceId);
        criteria.setContractNumericId(contractId);
        return getContractService(criteria);
    }

    /**
     * Search contract services by contract Id and service package id, with
     * CONTRACT_SERVICES.READ command
     *
     * @param contractId
     *            contract's id
     * @param servicePackageId
     *            service package id
     * @return list of {@link BscsContractService}
     */
    public List<BscsContractService> getServicesByContractIdAndServicePackageId(String contractId,
            String servicePackageId) {
        final BscsContractServiceSearchCriteria criteria = bscsObjectFactory.createBscsContractServiceSearchCriteria();
        criteria.setContractId(contractId);
        criteria.setServicePackageCode(servicePackageId);
        return getContractServices(criteria);
    }

    /**
     * Execute CONTRACT_SERVICES.READ
     * 
     * @param contractId
     * @return
     */
    public List<BscsContractService> getServicesByContractId(Long contractId) {
        final BscsContractServiceSearchCriteria criteria = bscsObjectFactory.createBscsContractServiceSearchCriteria();
        criteria.setContractNumericId(contractId);
        return getContractServices(criteria);
    }

    /**
     * Search contract services by criteria. execute CONTRACT_SERVICES.READ
     * command
     *
     * @param criteria
     * @return
     */
    protected List<BscsContractService> getContractServices(final BscsContractServiceSearchCriteria criteria) {
        List<BscsContractService> res = new ArrayList<>();
        try {
            List<BSCSContractService> contractServices = contractServicesRead(criteria.getBscsModel());
            for (BSCSContractService contractService : contractServices) {
                res.add(bscsObjectFactory.createBscsContractService(contractService));
            }
        } catch (BscsInvalidFieldException | BscsInvalidIdException e) {
            // invalid id/field: do nothing => empty res
        }
        return res;
    }

    protected List<BSCSContractService> contractServicesRead(BSCSContractService criteria)
            throws BscsInvalidFieldException, BscsInvalidIdException {
        try {
            return contractServiceAdapter.contractServicesRead(criteria);
        } catch (SOIException ex) {
            logger.debug("BSCS CONTRACT_SERVICES.READ error with code: " + ex.getCode());
            if (ex.getCode() != null) {
                if (ex.getCode().contains("RC6608-004")) {
                    // service does not belong to contract
                    throw new BscsInvalidFieldException(BscsFieldExceptionEnum.SERVICE_ID, ex.getArg(0),
                            "The service with id {" + ex.getArg(0) + "} was not found in the contract with id {"
                            + ex.getArg(1) + "}");
                }
                if (ex.getCode().contains("RC6700")) {
                    // contract not found, based on its id
                    throw new BscsInvalidIdException(BscsFieldExceptionEnum.CONTRACT_ID, ex.getFirstArg(),
                            "No contract found with id {" + ex.getFirstArg() + "}");
                }
                if (ex.getCode().contains("RC6704-001") || ex.getCode().contains("RC6704-002")) {
                    // Service package not found in rateplan corresponding to
                    // the contract
                    throw new BscsInvalidFieldException(BscsFieldExceptionEnum.SERVICE_PACKAGE_ID, ex.getArg(0),
                            "The service package with id {" + ex.getArg(0) + "} was not found in the contract");
                }
                if (ex.getCode().contains("FUNC_FRMWK_SRV.id0468")) {
                    // invalid public key
                    throw new BscsInvalidIdException(null, ex.getFirstArg(),
                            "Invalid public key: {" + ex.getFirstArg() + "}");
                }
            }
            throw ex;
        }
    }

    /**
     * Execute CONTRACT_SERVICES.READ
     * 
     * @param criteria
     * @return
     * @throws BscsInvalidIdException
     * @throws BscsInvalidFieldException
     */
    protected BscsContractService getContractService(final BscsContractServiceSearchCriteria criteria)
            throws BscsInvalidIdException, BscsInvalidFieldException {
        List<BSCSContractService> contractServices = contractServicesRead(criteria.getBscsModel());
        if (!contractServices.isEmpty()) {
            return bscsObjectFactory.createBscsContractService(contractServices.get(0));
        }
        // no corresponding service in this contract
        throw new BscsInvalidFieldException(BscsFieldExceptionEnum.SERVICE_ID, null,
                "No service found in contract matching the search criteria");
    }

    // #### CONTRACT_SERVICES.ADD ####

    /**
     * execute CONTRACT_SERVICES.ADD and optionally commit
     * 
     * @param contractId
     * @param contractService
     * @param commit
     * @throws BscsInvalidFieldException
     * @throws BscsInvalidIdException
     * @throws BscsPendingException
     * @throws BscsInvalidStateException
     */
    public abstract void addContractService(String contractId, BscsContractService contractService, boolean commit)
            throws BscsInvalidFieldException, BscsInvalidIdException, BscsPendingException, BscsInvalidStateException;

    // #### CONTRACT_SERVICES.WRITE ####

    /**
     * execute CONTRACT_SERVICES.WRITE and optionally commit
     * 
     * @param contractId
     * @param contractService
     * @param commit
     * @throws BscsInvalidFieldException
     * @throws BscsInvalidIdException
     * @throws BscsPendingException
     * @throws BscsInvalidStateException
     */
    public abstract void writeContractService(String contractId, BscsContractService contractService, boolean commit)
            throws BscsInvalidFieldException, BscsInvalidIdException, BscsPendingException, BscsInvalidStateException;

    // #### CONTRACT_INFO.READ ####

    /**
     * execute CONTRACT_INFO.READ command
     * 
     * @param contractId
     * 
     * @return
     * @throws BscsInvalidIdException
     */
    public BscsContractInfo getContractInfo(Long contractId) throws BscsInvalidIdException {
        try {
            BSCSModel contractInfoRead = contractServiceAdapter.contractInfoRead(contractId, null);
            return bscsObjectFactory.createBscsContractInfo(contractInfoRead);
        } catch (final SOIException e) {
            logger.debug("BSCS CONTRACT_INFO.READ error with code: " + e.getCode());
            if (e.getCode() != null && (e.getCode().contains("RC9001-003") || e.getCode().contains("RC6700"))) {
                throw new BscsInvalidIdException(BscsFieldExceptionEnum.CONTRACT_ID, contractId.toString(),
                        "No contract found with id {" + contractId + "}");
            }
            throw e;
        }
    }

    // #### CONTRACT_INFO.WRITE ####

    /**
     * Call to CONTRACT_INFO.WRITE and optionally commit
     * 
     * @param info
     * @param commit
     * @throws BscsInvalidIdException
     */
    public void writeContractInfo(BscsContractInfo info, boolean commit) throws BscsInvalidIdException {
        try {
            contractServiceAdapter.contractInfoWrite(info.getBscsModel());
            if (commit) {
                ConnectionHolder.getCurrentConnection().commit();
            }
        } catch (final SOIException e) {
            logger.debug("BSCS CONTRACT_INFO.WRITE error with code: " + e.getCode());
            if (e.getCode() != null && (e.getCode().contains("RC9001-003") || e.getCode().contains("RC6700"))) {
                throw new BscsInvalidIdException(BscsFieldExceptionEnum.CONTRACT_ID,
                        info.getContractNumericId().toString(),
                        "No contract found with id {" + info.getContractNumericId() + "}");
            }
            throw e;
        }
    }

    // #### BALANCES.READ ####

    /**
     * Execute BALANCES.READ command. Return an empty list if no balance found
     * 
     * @param contractId
     * @return
     */
    public abstract List<BscsBalance> getBalances(String contractId);

    // #### CONTRACT_FUA.READ ####

    /**
     * Execute CONTRACT_FUA.READ command. Return an empty list if no free unit
     * found
     * 
     * @param contractId
     * @return
     */
    public abstract List<BscsContractFreeUnitAccount> getFreeUnits(String contractId);

    // ### CONTRACT_RESOURCES.REPLACE ###
    /**
     * Call to CONTRACT_RESOURCES.REPLACE and optionally commit Return true if a
     * 
     * @param resource
     * @param commit
     * @throws BscsInvalidFieldException
     * @throws BscsInvalidIdException
     * @throws BscsPendingException
     * @throws BscsInvalidStateException
     */
    public void replaceContractResource(BscsContractResource resource, boolean commit)
            throws BscsInvalidFieldException, BscsInvalidIdException, BscsPendingException, BscsInvalidStateException {
        try {
            contractServiceAdapter.contractResourcesReplace(resource.getBscsModel());
            if (commit) {
                ConnectionHolder.getCurrentConnection().commit();
            }
        } catch (SOIException e) {
            logger.debug("BSCS CONTRACT_RESOURCES.REPLACE error with code: " + e.getCode());
            if (e.getCode() != null) {
                if (e.getCode().contains("RC3026")) {
                    throw new BscsInvalidFieldException(BscsFieldExceptionEnum.RESOURCE_NUM, null,
                            "The old resource is not assigned to the contract");
                }
                if (e.getCode().contains("RC6700") || e.getCode().contains("FUNC_FRMWK_SRV.id0468")) {
                    throw new BscsInvalidIdException(BscsFieldExceptionEnum.CONTRACT_ID, e.getFirstArg(),
                            "No contract found with id {" + e.getFirstArg() + "}");
                }
                if (e.getCode().contains("RC6745")) {
                    throw new BscsInvalidFieldException(BscsFieldExceptionEnum.RESOURCE_NUM, e.getFirstArg(),
                            "The new resource has an unknown id");
                }
                if (e.getCode().contains("RC6137")) {
                    throw new BscsInvalidFieldException(BscsFieldExceptionEnum.RESOURCE_NUM, e.getFirstArg(),
                            e.getMessage());
                }
                if (e.getCode().contains("RC5008-002") || e.getCode().contains("RC5008-003")) {
                    throw new BscsPendingException(e.getMessage());
                }
                if (e.getCode().contains("RC6020-004")) {
                    throw new BscsInvalidStateException(BscsFieldExceptionEnum.CONTRACT_ID,
                            "Contract is not in active state");
                }
                if (e.getCode().contains("RC6020-005")) {
                    throw new BscsInvalidStateException(BscsFieldExceptionEnum.CONTRACT_ID,
                            "Contract is not in pending_active or active state");
                }
                if (e.getCode().contains("RC6020-008")) {
                    throw new BscsInvalidStateException(BscsFieldExceptionEnum.CONTRACT_ID,
                            "Contract is not in pending_active, active, or suspended state");
                }
                if (e.getCode().contains("HlrNotSupportActionOnSuspendedContracts")
                        || e.getCode().contains("ActionNotSupportOnSuspendedContracts")) {
                    throw new BscsInvalidStateException(BscsFieldExceptionEnum.CONTRACT_ID, e.getMessage());
                }
            }
            throw e;
        }
    }

    // #### PRODUCT.CHANGE ####
    public void changeRatePlan(String contractId, String newRatePlanId, boolean commit)
            throws BscsInvalidIdException, BscsPendingException, BscsInvalidStateException, BscsInvalidFieldException {
        try {
            BSCSProductChange input = new BSCSProductChange(contractId, newRatePlanId);
            contractServiceAdapter.productChange(input);
            if (commit) {
                ConnectionHolder.getCurrentConnection().commit();
            }
        } catch (SOIException e) {
            logger.debug("BSCS CONTRACT_RESOURCES.REPLACE error with code: " + e.getCode());
            if (e.getCode() != null) {
                if (e.getCode().contains("FUNC_FRMWK_SRV.id0468")) {
                    // invalid public key
                    throw new BscsInvalidIdException(null, e.getFirstArg(),
                            "Invalid public key: {" + e.getFirstArg() + "}");
                }
                if (e.getCode().contains("InvalidListOfServicePackages")) {
                    throw new BscsInvalidFieldException(BscsFieldExceptionEnum.RATE_PLAN_ID, newRatePlanId,
                            "A service package of the old rateplan does not belong to the new rateplan");
                }
                if (e.getCode().contains("RC6003-002")) {
                    throw new BscsInvalidFieldException(BscsFieldExceptionEnum.RATE_PLAN_ID, newRatePlanId,
                            "Invalid new rate plan");
                }
                if (e.getCode().contains("RC5008-002") || e.getCode().contains("RC5008-003")
                        || e.getCode().contains("PendingContractStatusNotAllowedOnSuspDeact")) {
                    throw new BscsPendingException(e.getMessage());
                }
                if (e.getCode().contains("RC6020-005")) {
                    throw new BscsInvalidStateException(BscsFieldExceptionEnum.CONTRACT_ID,
                            "Contract is not in pending_active or active state");
                }
                if (e.getCode().contains("RC6020-008")) {
                    throw new BscsInvalidStateException(BscsFieldExceptionEnum.CONTRACT_ID,
                            "Contract is not in pending_active, active or suspended state");
                }
                if (e.getCode().contains("HlrNotSupportActionOnSuspendedContracts")
                        || e.getCode().contains("ActionNotSupportOnSuspendedContracts")) {
                    throw new BscsInvalidStateException(BscsFieldExceptionEnum.CONTRACT_ID, e.getMessage());
                }
            }
            throw e;
        }
    }

}
