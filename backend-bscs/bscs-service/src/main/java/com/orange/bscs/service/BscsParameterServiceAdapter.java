package com.orange.bscs.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.orange.bscs.api.model.exception.SOIException;
import com.orange.bscs.cms.servicelayeradapter.ParameterServiceAdapterI;
import com.orange.bscs.model.BscsContractServiceParameter;
import com.orange.bscs.model.BscsParameter;
import com.orange.bscs.model.BscsParameterValue;
import com.orange.bscs.model.contract.BSCSContractServiceParameter;
import com.orange.bscs.model.criteria.BscsContractServiceParameterSearchCriteria;
import com.orange.bscs.model.factory.BscsObjectFactory;
import com.orange.bscs.service.exception.BscsFieldExceptionEnum;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;
import com.orange.bscs.service.exception.BscsInvalidStateException;
import com.orange.bscs.service.exception.BscsPendingException;

public abstract class BscsParameterServiceAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected ParameterServiceAdapterI parameterServiceAdapter;

    @Autowired
    protected BscsObjectFactory bscsObjectFactory;

    /**
     * Execute CONTRACT_SERVICE_PARAMETERS.READ
     * 
     * @param contractId
     * @param profileId
     * @param serviceId
     * @return
     */
    public List<BscsContractServiceParameter> getContractServiceParameters(String contractId, Long profileId,
            String serviceId) {
        try {
            final BscsContractServiceParameterSearchCriteria criteria = bscsObjectFactory
                    .createBscsContractServiceParameterSearchCriteria();
            criteria.setContractId(contractId);
            criteria.setCode(serviceId);
            criteria.setProfileId(profileId);
            List<BSCSContractServiceParameter> parameters = parameterServiceAdapter
                    .contractServiceParametersRead(criteria.getBscsModel()).getParametersRead();
            List<BscsContractServiceParameter> res = new ArrayList<>();
            for (BSCSContractServiceParameter param : parameters) {
                res.add(bscsObjectFactory.createBscsContractServiceParameter(param));
            }
            return res;
        } catch (SOIException ex) {
            logger.debug("BSCS CONTRACT_SERVICE_PARAMETERS.READ error with code: " + ex.getCode());
            if (ex.getCode() != null) {
                if (ex.getCode().contains("RC6608-004")) {
                    // service does not belong to contract
                    logger.error("The service with id {" + serviceId + "} was not found in the contract with id {"
                                    + contractId + "}");
                    return new ArrayList<>();
                }
                if (ex.getCode().contains("RC6700")) {
                    // contract not found, based on its id
                    logger.error("Invalid contract id: {" + ex.getFirstArg() + "}");
                    return new ArrayList<>();
                }
                if (ex.getCode().contains("FUNC_FRMWK_SRV.id0468")) {
                    // invalid public key (contract or service)
                    logger.error("Invalid public key: {" + ex.getFirstArg() + "}");
                    return new ArrayList<>();
                }
            }
            throw ex;
        }
    }

    /**
     * Execute CONTRACT_SERVICE_PARAMETERS.READ and retreive given parameter
     * 
     * @param contractId
     * @param serviceId
     * @param parameterId
     * @param profileId
     * @return
     * @throws BscsInvalidIdException
     */
    public BscsContractServiceParameter getContractServiceParameter(String contractId, String serviceId, String parameterId,
            Long profileId) throws BscsInvalidIdException {
        List<BscsContractServiceParameter> contractServiceParameters = getContractServiceParameters(contractId,
                profileId, serviceId);
        for (BscsContractServiceParameter param : contractServiceParameters) {
            if (param.getId().equals(parameterId)) {
                return param;
            }
        }
        throw new BscsInvalidIdException(BscsFieldExceptionEnum.PARAMETER_ID, parameterId,
                "The parameter with id {" + parameterId + "} was not found in the service with id {" + serviceId + "}");
    }

    /**
     * Get parameter of a service from service id and parameter id. Execute
     * SERVICE_PARAMETERS.READ
     *
     * @param serviceId
     *            Service ID
     * @param parameterId
     *            parameter ID
     * @return
     * @throws BscsInvalidIdException
     */
    public BscsParameter getServiceParameter(String serviceId, String parameterId)
            throws BscsInvalidIdException {
        try {
            // parameter id is not in the input of SERVICE_PARAMETERS.READ
            List<BscsParameter> parameters = getParameters(serviceId);
            // if result is empty: no parameters
            if (parameters.isEmpty()) {
                throw new BscsInvalidIdException(BscsFieldExceptionEnum.SERVICE_ID, serviceId,
                        "Service with id: {" + serviceId + "} does not have parameters");
            }
            for (BscsParameter param : parameters) {
                if (parameterId != null && parameterId.equals(param.getId())) {
                    return param;
                }
            }
            throw new BscsInvalidIdException(BscsFieldExceptionEnum.PARAMETER_ID, parameterId, "The parameter with id {"
                    + parameterId + "} was not found in the service with id {" + serviceId + "}");
        } catch (SOIException ex) {
            logger.debug("BSCS SERVICE_PARAMETERS.READ error with code: " + ex.getCode());
            if (ex.getCode() != null) {
                if (ex.getCode().contains("RC6703")) {
                    // service not found, based on its id
                    throw new BscsInvalidIdException(BscsFieldExceptionEnum.SERVICE_ID, ex.getFirstArg(),
                            "Invalid service id: {" + ex.getFirstArg() + "}");
                }
            }
            throw ex;
        }
    }

    /**
     * Execute SERVICE_PARAMETERS.READ
     * 
     * @param serviceId
     * @return
     * @throws BscsInvalidIdException
     */
    public abstract List<BscsParameter> getParameters(String serviceId) throws BscsInvalidIdException;

    /**
     * Write parameters to contract service (CONTRACT_SERVICE_PARAMETERS.WRITE)
     * and optionally commit
     *
     * @param contractId
     * @param serviceId
     * @param params
     * @param profileId
     * @param commit
     * @throws BscsInvalidIdException
     * @throws BscsInvalidFieldException
     * @throws BscsPendingException
     * @throws BscsInvalidStateException
     */
    public abstract void writeContractServiceParameters(String contractId, String serviceId,
            List<BscsContractServiceParameter> params, Long profileId, boolean commit)
            throws BscsInvalidIdException, BscsInvalidFieldException, BscsPendingException, BscsInvalidStateException;

    /**
     * Build a service parameter for BSCS request
     * CONTRACT_SERVICE_PARAMETERS.WRITE
     *
     * @param bscsParameter
     * @param paramValue
     * @param paramValueDescription
     * @param action
     * @return
     */
    public BscsContractServiceParameter buildContractServiceParameter(BscsParameter bscsParameter, String paramValue,
            String paramValueDescription, Character action) {
        BscsContractServiceParameter param = bscsObjectFactory.createBscsContractServiceParameter();
        param.setNumber(bscsParameter.getNumber());
        param.setComplexNumber(1L);
        param.setComplexLevel(1L);
        param.setParentNumber(1L);
        param.setSiblingNumber(1L);
        param.setAction(action);
        final BscsParameterValue pValue = bscsObjectFactory.createBscsParameterValue();
        pValue.setValue(paramValue);
        pValue.setDescription(paramValueDescription);
        param.setParametersValues(Arrays.asList(pValue), 1L);
        return param;
    }

    /**
     * Build a service parameter for BSCS request
     * CONTRACT_SERVICE_PARAMETERS.WRITE from an existing
     * BscsContractServiceParameter
     * 
     * @param bscsContractParameter
     * @param paramValue
     * @param paramValueDescription
     * @param action
     * @return
     */
    public BscsContractServiceParameter buildContractServiceParameter(
            BscsContractServiceParameter bscsContractParameter, String paramValue, String paramValueDescription,
            Character action) {
        bscsContractParameter.setAction(action);
        BscsParameterValue pValue;
        if (bscsContractParameter.getParametersValues().isEmpty()) {
            pValue = bscsObjectFactory.createBscsParameterValue();
        } else {
            pValue = bscsContractParameter.getParametersValues().get(0);
        }
        pValue.setValue(paramValue);
        pValue.setDescription(paramValueDescription);
        bscsContractParameter.setParametersValues(Arrays.asList(pValue), 1L);
        return bscsContractParameter;
    }
}
