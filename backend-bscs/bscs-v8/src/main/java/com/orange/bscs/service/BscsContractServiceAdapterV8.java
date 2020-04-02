package com.orange.bscs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.orange.bscs.api.connection.ConnectionHolder;
import com.orange.bscs.api.model.exception.SOIException;
import com.orange.bscs.api.utils.config.Constants;
import com.orange.bscs.model.BscsBalance;
import com.orange.bscs.model.BscsContract;
import com.orange.bscs.model.BscsContractFreeUnitAccount;
import com.orange.bscs.model.BscsContractResource;
import com.orange.bscs.model.BscsContractSearch;
import com.orange.bscs.model.BscsContractService;
import com.orange.bscs.model.businesspartner.EnumContractStatus;
import com.orange.bscs.model.contract.BSCSContractFreeUnit;
import com.orange.bscs.model.contract.BSCSContractService;
import com.orange.bscs.model.contract.BSCSProductChange;
import com.orange.bscs.model.criteria.BscsContractSearchCriteria;
import com.orange.bscs.model.usagedata.Balances;
import com.orange.bscs.service.exception.BscsFieldExceptionEnum;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;
import com.orange.bscs.service.exception.BscsInvalidStateException;
import com.orange.bscs.service.exception.BscsPendingException;

@Service
public class BscsContractServiceAdapterV8 extends BscsContractServiceAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public BscsContract getContract(final String contractId) throws BscsInvalidIdException {
        return getContract(Long.valueOf(contractId));
    }

    @Override
    public List<BscsBalance> getBalances(String contractId) {
        List<BscsBalance> res = new ArrayList<>();
        try {
            List<Balances> balances = contractServiceAdapter.balancesRead(Long.valueOf(contractId), null);
            for (Balances balance : balances) {
                res.add(bscsObjectFactory.createBscsBalance(balance));
            }
        } catch (final SOIException e) {
            logger.debug("BSCS BALANCES.READ error with code: " + e.getCode());
            if (e.getCode() != null && e.getCode().contains("RC6700")) {
                // no contract found
            } else {
                throw e;
            }
        }
        return res;
    }

    @Override
    public List<BscsContractFreeUnitAccount> getFreeUnits(String contractId) {
        List<BscsContractFreeUnitAccount> res = new ArrayList<>();
        try {
            List<BSCSContractFreeUnit> freeUnits = contractServiceAdapter
                    .contractFreeUnitsReadByCoIdPub(Long.valueOf(contractId), null);
            for (BSCSContractFreeUnit freeUnit : freeUnits) {
                res.add(bscsObjectFactory.createBscsContractFreeUnitAccount(freeUnit));
            }
        } catch (final SOIException e) {
            logger.debug("BSCS CONTRACT_FUA.READ error with code: " + e.getCode());
            if (e.getCode() != null && e.getCode().contains("RC6700")) {
                // no contract found
            } else {
                throw e;
            }
        }
        return res;
    }

    @Override
    public void addContractService(String contractId, BscsContractService contractService, boolean commit)
            throws BscsInvalidFieldException, BscsInvalidIdException, BscsPendingException, BscsInvalidStateException {
        try {
            final List<BSCSContractService> services = new ArrayList<>();
            services.add(contractService.getBscsModel());
            contractServiceAdapter.contractServicesAdd(Long.valueOf(contractId), null, services);
            if (commit) {
                ConnectionHolder.getCurrentConnection().commit();
            }
        } catch (final SOIException e) {
            logger.debug("BSCS CONTRACT_SERVICES.ADD error with code: " + e.getCode());
            if (e.getCode() != null) {
                if (e.getCode().contains("RC6226")) {
                    String snCode = contractService.getBscsModel().getServiceCode().toString();
                    throw new BscsInvalidFieldException(BscsFieldExceptionEnum.SERVICE_ID, snCode,
                            "Service already assigned to contract");
                }
                if (e.getCode().contains("RC6704-001") || e.getCode().contains("RC6704-002")) {
                    // Service package not found in rateplan corresponding to
                    // the contract
                    throw new BscsInvalidFieldException(BscsFieldExceptionEnum.SERVICE_PACKAGE_ID, e.getArg(0),
                            "The service package with id {" + e.getArg(0) + "} was not found in the contract");
                }
                if (e.getCode().contains("RC6700")) {
                    // contract not found, based on its id
                    throw new BscsInvalidIdException(BscsFieldExceptionEnum.CONTRACT_ID, e.getFirstArg(),
                            "No contract found with id {" + e.getFirstArg() + "}");
                }
                if (e.getCode().contains("RC6703-001") || e.getCode().contains("RC6703-002")
                        || e.getCode().contains("RC6200")) {
                    throw new BscsInvalidFieldException(BscsFieldExceptionEnum.SERVICE_ID, e.getArg(0),
                            "The service with id {" + e.getArg(0)
                                    + "} was not found in service package of the contract");
                }
                if (e.getCode().contains("RC5008-002") || e.getCode().contains("RC5008-003")) {
                    throw new BscsPendingException(e.getMessage());
                }
                if (e.getCode().contains("RC6020-004")) {
                    throw new BscsInvalidStateException(BscsFieldExceptionEnum.CONTRACT_ID, "Contract is not in active state");
                }
                if (e.getCode().contains("RC6020-005")) {
                    throw new BscsInvalidStateException(BscsFieldExceptionEnum.CONTRACT_ID, "Contract is not in pending_active or active state");
                }
            }
            throw e;
        }
    }

    @Override
    public void writeContractService(String contractId, BscsContractService contractService, boolean commit)
            throws BscsInvalidFieldException, BscsInvalidIdException, BscsPendingException, BscsInvalidStateException {
        try {
            final List<BSCSContractService> services = new ArrayList<>();
            services.add(contractService.getBscsModel());
            contractServiceAdapter.contractServicesWrite(Long.valueOf(contractId), null, services);
            if (commit) {
                ConnectionHolder.getCurrentConnection().commit();
            }
        } catch (final SOIException e) {
            logger.debug("BSCS CONTRACT_SERVICES.WRITE error with code: " + e.getCode());
            if (e.getCode() != null) {
                if (e.getCode().contains("RC6608-003") || e.getCode().contains("RC6608-004")) {
                    // service not in profile or contract
                    throw new BscsInvalidFieldException(BscsFieldExceptionEnum.SERVICE_ID, e.getFirstArg(),
                            "Service not in contract");
                }
                if (e.getCode().contains("RC6620-001")) {
                    throw new BscsInvalidFieldException(BscsFieldExceptionEnum.STATUS, e.getFirstArg(),
                            "Invalid status transition");
                }
                if (e.getCode().contains("RC6700")) {
                    // contract not found, based on its id
                    throw new BscsInvalidIdException(BscsFieldExceptionEnum.CONTRACT_ID, e.getFirstArg(),
                            "No contract found with id {" + e.getFirstArg() + "}");
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
                if (e.getCode().contains("RC3033")) {
                    // The user is not allowed to erase the core service because he has not right to do so
                    SOIException ex = new SOIException("Operation not allowed on a core service", e);
                    ex.setCode(e.getCode());
                    throw ex;
                }
            }
            throw e;
        }
    }

    private List<BscsContractSearch> filterLatestContractsForMsisdn(List<BscsContractSearch> contracts) {
        Map<String, BscsContractSearch> contractsByMsisdn = new HashMap<>();
        for (BscsContractSearch contract : contracts) {
            if (!contractsByMsisdn.containsKey(contract.getDirectoryNumber())) {
                contractsByMsisdn.put(contract.getDirectoryNumber(), contract);
            } else {
                BscsContractSearch memorizedContract = contractsByMsisdn.get(contract.getDirectoryNumber());
                if (memorizedContract.getStatus() == EnumContractStatus.ON_HOLD
                        || memorizedContract.getStatus() == EnumContractStatus.ACTIVATED) {
                    // most recent contract already found
                    continue;
                }
                if (memorizedContract.getDateActivated() == null // no date in memorized contract
                        || contract.getStatus() == EnumContractStatus.ON_HOLD // current contract is on hold (no date) => most recent 
                        || (contract.getDateActivated() != null
                                && contract.getDateActivated().after(memorizedContract.getDateActivated()))) { // activation date is more recent 
                    contractsByMsisdn.put(contract.getDirectoryNumber(), contract);
                }
            }
        }
        return new ArrayList<>(contractsByMsisdn.values());
    }

    private List<BscsContractSearch> filterLatestContractsForIccId(List<BscsContractSearch> contracts) {
        Map<String, BscsContractSearch> contractsByIccId = new HashMap<>();
        for (BscsContractSearch contract : contracts) {
            if (!contractsByIccId.containsKey(contract.getStorageMediumNum())) {
                contractsByIccId.put(contract.getStorageMediumNum(), contract);
            } else {
                BscsContractSearch memorizedContract = contractsByIccId.get(contract.getStorageMediumNum());
                if (memorizedContract.getStatus() == EnumContractStatus.ON_HOLD
                        || memorizedContract.getStatus() == EnumContractStatus.ACTIVATED) {
                    // most recent contract already found
                    continue;
                }
                if (memorizedContract.getDateActivated() == null // no date in memorized contract
                        || contract.getStatus() == EnumContractStatus.ON_HOLD // current contract is on hold (no date) => most recent 
                        || (contract.getDateActivated() != null 
                                && contract.getDateActivated().after(memorizedContract.getDateActivated()))) // activation date is more recent 
                {
                    contractsByIccId.put(contract.getStorageMediumNum(), contract);
                }
            }
        }
        return new ArrayList<>(contractsByIccId.values());
    }

    @Override
    public List<BscsContractSearch> findContractsByIccId(String iccId) {
        final BscsContractSearchCriteria contractSearchCriteria = bscsObjectFactory.createBscsContractSearchCriteria();
        contractSearchCriteria.setStorageMedium(iccId);
        // find all contracts once assigned to this iccId
        List<BscsContractSearch> contracts = findContracts(contractSearchCriteria);
        return filterLatestContractsForIccId(contracts);
    }

    @Override
    public List<BscsContractSearch> findContractsByMsisdn(String msisdn) {
        final BscsContractSearchCriteria contractSearchCriteria = bscsObjectFactory.createBscsContractSearchCriteria();
        contractSearchCriteria.setDirectoryNumber(msisdn);
        // find all contracts once assigned to this msisdn
        List<BscsContractSearch> contracts = findContracts(contractSearchCriteria);
        return filterLatestContractsForMsisdn(contracts);
    }

    @Override
    public void replaceContractResource(BscsContractResource resource, boolean commit)
            throws BscsInvalidFieldException, BscsInvalidIdException, BscsPendingException, BscsInvalidStateException {
        try {
            super.replaceContractResource(resource, commit);
        } catch (BscsInvalidFieldException e) {
            if (e.getName() == BscsFieldExceptionEnum.RESOURCE_NUM && e.getValue() == null) {
                throw new BscsInvalidFieldException(BscsFieldExceptionEnum.RESOURCE_NUM, null,
                        "The old resource is not assigned to the contract or the new resource is not available");
            }
            throw e;
        }
    }

    @Override
    public void changeRatePlan(String contractId, String newRatePlanId, boolean commit)
            throws BscsInvalidIdException, BscsInvalidStateException, BscsPendingException, BscsInvalidFieldException {
        try {
            BSCSProductChange input = new BSCSProductChange(contractId, newRatePlanId);
            input.setLongValue(Constants.P_CO_ID, Long.valueOf(contractId));
            input.setLongValue("CO_NEW_RPCODE", Long.valueOf(newRatePlanId));
            contractServiceAdapter.productChange(input);
            if (commit) {
                ConnectionHolder.getCurrentConnection().commit();
            }
        } catch (SOIException e) {
            logger.debug("BSCS CONTRACT_RESOURCES.REPLACE error with code: " + e.getCode());
            if (e.getCode() != null) {
                if (e.getCode().contains("RC6700")) {
                    throw new BscsInvalidIdException(BscsFieldExceptionEnum.CONTRACT_ID, e.getFirstArg(),
                            "No contract found with id {" + e.getFirstArg() + "}");
                }
                if (e.getCode().contains("RC6705")) {
                    throw new BscsInvalidIdException(BscsFieldExceptionEnum.RATE_PLAN_ID, e.getFirstArg(),
                            "No rateplan found with id {" + e.getFirstArg() + "}");
                }
                if (e.getCode().contains("RC6032")) {
                    throw new BscsInvalidFieldException(BscsFieldExceptionEnum.RATE_PLAN_ID, newRatePlanId,
                            "A service package of the old rateplan does not belong to the new rateplan");
                }
                if (e.getCode().contains("RC6003-002")) {
                    throw new BscsInvalidFieldException(BscsFieldExceptionEnum.RATE_PLAN_ID, newRatePlanId,
                            "Invalid new rate plan");
                }
                if (e.getCode().contains("RC5008-002") || e.getCode().contains("RC5008-003")) {
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
            }
            throw e;
        }
    }

}
