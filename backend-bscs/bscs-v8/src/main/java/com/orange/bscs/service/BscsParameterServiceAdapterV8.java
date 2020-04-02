package com.orange.bscs.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.orange.bscs.api.connection.ConnectionHolder;
import com.orange.bscs.api.model.exception.SOIException;
import com.orange.bscs.model.BscsContractServiceParameter;
import com.orange.bscs.model.BscsParameter;
import com.orange.bscs.model.contract.BSCSContractService;
import com.orange.bscs.model.contract.BSCSContractServiceParameter;
import com.orange.bscs.model.contract.BSCSParameter;
import com.orange.bscs.model.contract.BSCSService;
import com.orange.bscs.service.exception.BscsFieldExceptionEnum;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;
import com.orange.bscs.service.exception.BscsInvalidStateException;

@Service
public class BscsParameterServiceAdapterV8 extends BscsParameterServiceAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<BscsParameter> getParameters(String serviceId) throws BscsInvalidIdException {
        try {
            BSCSService input = new BSCSService();
            input.setLongValue("SN_CODE", Long.valueOf(serviceId));
            List<BSCSParameter> parameters = parameterServiceAdapter.serviceParametersRead(input);
            List<BscsParameter> res = new ArrayList<>();
            for (BSCSParameter parameter : parameters) {
                res.add(bscsObjectFactory.createBscsParameter(parameter));
            }
            return res;
        } catch (SOIException ex) {
            logger.debug("BSCS SERVICE_PARAMETERS.READ error with code: " + ex.getCode());
            if (ex.getCode() != null) {
                if (ex.getCode().contains("RC6703")) {
                    // service not found, based on its id
                    throw new BscsInvalidIdException(BscsFieldExceptionEnum.SERVICE_ID, serviceId,
                            "Invalid service id: {" + serviceId + "}");
                }
            }
            throw ex;
        }
    }

    @Override
    public void writeContractServiceParameters(String contractId, String serviceId,
            List<BscsContractServiceParameter> params, Long profileId, boolean commit)
            throws BscsInvalidIdException, BscsInvalidFieldException, BscsInvalidStateException {
        try {
            List<BSCSContractServiceParameter> bscsParams = new ArrayList<>();
            for (BscsContractServiceParameter param : params) {
                bscsParams.add(param.getBscsModel());
            }
            BSCSContractService input = new BSCSContractService();
            input.setContractId(Long.valueOf(contractId));
            input.setProfileId(profileId);
            input.setLongValue("COS_SNCODE", Long.valueOf(serviceId));
            input.setParametersWrite(bscsParams);

            parameterServiceAdapter.contractServiceParameterswrite(input);
            if (commit) {
                ConnectionHolder.getCurrentConnection().commit();
            }
        } catch (SOIException ex) {
            logger.debug("BSCS CONTRACT_SERVICE_PARAMETERS.WRITE error with code: " + ex.getCode());
            if (ex.getCode() != null) {
                if (ex.getCode().contains("RC6700")) {
                    // contract not found, based on its id
                    throw new BscsInvalidIdException(BscsFieldExceptionEnum.CONTRACT_ID, ex.getFirstArg(),
                            "Invalid contract id: {" + ex.getFirstArg() + "}");
                }
                if (ex.getCode().contains("RC6608-004")) {
                    // service does not belong to contract
                    throw new BscsInvalidIdException(BscsFieldExceptionEnum.SERVICE_ID, ex.getFirstArg(),
                            "The service with id {" + serviceId + "} was not found in the contract with id {"
                                    + contractId + "}");
                }
                if (ex.getCode().contains("RC2018") || ex.getCode().contains("RC2002-001")) {
                    // invalid parameter value
                    throw new BscsInvalidFieldException(BscsFieldExceptionEnum.PARAMETER_VALUE, ex.getFirstArg(),
                            "Invalid parameter value: " + ex.getFirstArg());
                }
                if (ex.getCode().contains("RC6236-004")) {
                    throw new BscsInvalidStateException(BscsFieldExceptionEnum.CONTRACT_ID,
                            "Contract state do not allow parameter modification");
                }
            }
            throw ex;
        }
    }

    @Override
    public BscsContractServiceParameter buildContractServiceParameter(BscsParameter bscsParameter, String paramValue,
            String paramValueDescription, Character action) {
        BscsContractServiceParameter param = super.buildContractServiceParameter(bscsParameter, paramValue,
                paramValueDescription, action);
        param.setId(bscsParameter.getId());
        return param;
    }

    @Override
    public BscsContractServiceParameter buildContractServiceParameter(
            BscsContractServiceParameter bscsContractParameter, String paramValue, String paramValueDescription,
            Character action) {
        BscsContractServiceParameter res = super.buildContractServiceParameter(bscsContractParameter, paramValue,
                paramValueDescription, action);
        // syntax is different in output from CONTRACT_SERVICE_PARAMETERS.READ
        // and input for CONTRACT_SERVICE_PARAMETERS.WRITE
        res.setNumber(res.getNumber());
        return res;
    }

}
