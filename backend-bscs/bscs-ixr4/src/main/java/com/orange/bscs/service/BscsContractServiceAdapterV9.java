package com.orange.bscs.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.orange.bscs.api.connection.ConnectionHolder;
import com.orange.bscs.api.model.exception.SOIException;
import com.orange.bscs.model.BscsBalance;
import com.orange.bscs.model.BscsContractFreeUnitAccount;
import com.orange.bscs.model.BscsContractSearch;
import com.orange.bscs.model.BscsContractService;
import com.orange.bscs.model.contract.BSCSContractFreeUnit;
import com.orange.bscs.model.contract.BSCSContractService;
import com.orange.bscs.model.criteria.BscsContractSearchCriteria;
import com.orange.bscs.model.criteria.BscsContractSearchCriteriaV9;
import com.orange.bscs.model.usagedata.Balances;
import com.orange.bscs.service.exception.BscsFieldExceptionEnum;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;
import com.orange.bscs.service.exception.BscsInvalidStateException;
import com.orange.bscs.service.exception.BscsPendingException;

@Service
public class BscsContractServiceAdapterV9 extends BscsContractServiceAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<BscsContractSearch> findContractsByMsisdn(String msisdn) {
        final BscsContractSearchCriteria contractSearchCriteria = new BscsContractSearchCriteriaV9();
        // get only currently assigned contract with this msisdn
        contractSearchCriteria.setSearcher("ContractSearchWithoutHistory");
        contractSearchCriteria.setDirectoryNumber(msisdn);
        return findContracts(contractSearchCriteria);
    }

    @Override
    public List<BscsContractSearch> findContractsByIccId(String iccId) {
        final BscsContractSearchCriteria contractSearchCriteria = bscsObjectFactory.createBscsContractSearchCriteria();
        contractSearchCriteria.setStorageMedium(iccId);
        // get only currently assigned contract with this iccId
        contractSearchCriteria.setSearcher("ContractSearchWithoutHistory");
        return findContracts(contractSearchCriteria);
    }

    @Override
    public List<BscsBalance> getBalances(String contractId) {
        List<BscsBalance> res = new ArrayList<>();
        try {
            List<Balances> balances = contractServiceAdapter.balancesRead(null, contractId);
            for (Balances balance : balances) {
                res.add(bscsObjectFactory.createBscsBalance(balance));
            }
        } catch (final SOIException e) {
            logger.debug("BSCS BALANCES.READ error with code: " + e.getCode());
            if (e.getCode() != null
                    && (e.getCode().contains("RC6700") || e.getCode().contains("FUNC_FRMWK_SRV.id0468"))) {
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
            List<BSCSContractFreeUnit> freeUnits = contractServiceAdapter.contractFreeUnitsReadByCoIdPub(null,
                    contractId);
            for (BSCSContractFreeUnit freeUnit : freeUnits) {
                res.add(bscsObjectFactory.createBscsContractFreeUnitAccount(freeUnit));
            }
        } catch (final SOIException e) {
            logger.debug("BSCS CONTRACT_FUA.READ error with code: " + e.getCode());
            if (e.getCode() != null
                    && (e.getCode().contains("RC6700") || e.getCode().contains("FUNC_FRMWK_SRV.id0468"))) {
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
        try{
            final List<BSCSContractService> services = new ArrayList<>();
            services.add(contractService.getBscsModel());
            contractServiceAdapter.contractServicesAdd(null, contractId, services);
            if (commit) {
                ConnectionHolder.getCurrentConnection().commit();
            }
        } catch (final SOIException e) {
            logger.debug("BSCS CONTRACT_SERVICES.ADD error with code: " + e.getCode());
            if (e.getCode() != null) {
                if (e.getCode().contains("RC6226")) {
                    throw new BscsInvalidFieldException(BscsFieldExceptionEnum.SERVICE_ID, contractService.getCode(),
                            "Service already assigned to contract");
                }
                if (e.getCode().contains("FUNC_FRMWK_SRV.id0468")) {
                    // invalid public key
                    throw new BscsInvalidIdException(null, e.getFirstArg(),
                            "Invalid public key: {" + e.getFirstArg() + "}");
                }
                if (e.getCode().contains("RC6704-001") || e.getCode().contains("RC6704-002")) {
                    // Service package not found in rateplan corresponding to
                    // the contract
                    throw new BscsInvalidFieldException(BscsFieldExceptionEnum.SERVICE_PACKAGE_ID,
                            contractService.getServicePackageCode(), "The service package with id {"
                                    + contractService.getServicePackageCode() + "} was not found in the contract");
                }
                if (e.getCode().contains("RC6700")) {
                    // contract not found, based on its id
                    throw new BscsInvalidIdException(BscsFieldExceptionEnum.CONTRACT_ID, e.getFirstArg(),
                            "No contract found with id {" + e.getFirstArg() + "}");
                }
                if (e.getCode().contains("RC6703-001") || e.getCode().contains("RC6703-002")
                        || e.getCode().contains("RC6200")) {
                    throw new BscsInvalidFieldException(BscsFieldExceptionEnum.SERVICE_ID, contractService.getCode(),
                            "The service with id {" + contractService.getCode()
                                    + "} was not found in service package of the contract");
                }
                if (e.getCode().contains("RC5008-002") || e.getCode().contains("RC5008-003")
                        || e.getCode().contains("PendingRequests")) {
                    throw new BscsPendingException(e.getMessage());
                }
                if (e.getCode().contains("RC6020-004") || e.getCode().contains("RC6020-005")
                        || e.getCode().contains("HlrNotSupportActionOnSuspendedContracts")
                        || e.getCode().contains("ActionNotSupportOnSuspendedContracts")) {
                    throw new BscsInvalidStateException(BscsFieldExceptionEnum.CONTRACT_ID, e.getMessage());
                }
            }
            throw e;
        }
    }

    @Override
    public void writeContractService(String contractId, BscsContractService contractService, boolean commit)
            throws BscsInvalidFieldException, BscsPendingException, BscsInvalidIdException, BscsInvalidStateException {
        try {
            final List<BSCSContractService> services = new ArrayList<>();
            services.add(contractService.getBscsModel());
            contractServiceAdapter.contractServicesWrite(null, contractId, services);
            if (commit) {
                ConnectionHolder.getCurrentConnection().commit();
            }
        } catch (final SOIException e) {
            logger.debug("BSCS CONTRACT_SERVICES.WRITE error with code: " + e.getCode());
            if (e.getCode() != null) {
                if (e.getCode().contains("FUNC_FRMWK_SRV.id0468")) {
                    // invalid public key
                    throw new BscsInvalidIdException(null, e.getFirstArg(),
                            "Invalid public key: {" + e.getFirstArg() + "}");
                }
                if (e.getCode().contains("RC6608-003") || e.getCode().contains("RC6608-004")) {
                    // service not in profile or contract
                    throw new BscsInvalidFieldException(BscsFieldExceptionEnum.SERVICE_ID, contractService.getCode(),
                            "Service not in contract");
                }
                if (e.getCode().contains("RC6620-001") || e.getCode().contains("InvalidNewStateForService")
                        || e.getCode().contains("RequestedStateAlreadySet")) {
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
                if (e.getCode().contains("RC6020-004") || e.getCode().contains("RC6020-005")
                        || e.getCode().contains("HlrNotSupportActionOnSuspendedContracts")
                        || e.getCode().contains("ActionNotSupportOnSuspendedContracts")) {
                    throw new BscsInvalidStateException(BscsFieldExceptionEnum.CONTRACT_ID, e.getMessage());
                }
            }
            throw e;
        }
    }

}
