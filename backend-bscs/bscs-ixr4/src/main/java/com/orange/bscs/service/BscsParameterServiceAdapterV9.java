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
import com.orange.bscs.model.contract.BSCSContractServiceParameter;
import com.orange.bscs.model.contract.BSCSParameter;
import com.orange.bscs.service.exception.BscsFieldExceptionEnum;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;
import com.orange.bscs.service.exception.BscsInvalidStateException;
import com.orange.bscs.service.exception.BscsPendingException;

@Service
public class BscsParameterServiceAdapterV9 extends BscsParameterServiceAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<BscsParameter> getParameters(String serviceId) throws BscsInvalidIdException {
        try {
            List<BSCSParameter> parameters = parameterServiceAdapter.serviceParametersRead(null, serviceId);
            List<BscsParameter> res = new ArrayList<>();
            for (BSCSParameter parameter : parameters) {
                res.add(bscsObjectFactory.createBscsParameter(parameter));
            }
            return res;
        } catch (SOIException ex) {
            logger.debug("BSCS SERVICE_PARAMETERS.READ error with code: " + ex.getCode());
            if (ex.getCode() != null) {
                if (ex.getCode().contains("FUNC_FRMWK_SRV.id0468")) {
                    // invalid service public key
                    throw new BscsInvalidIdException(BscsFieldExceptionEnum.SERVICE_ID, serviceId, ex.getMessage());
                }
            }
            throw ex;
        }
    }

    @Override
    public void writeContractServiceParameters(String contractId, String serviceId,
            List<BscsContractServiceParameter> params, Long profileId, boolean commit)
            throws BscsInvalidIdException, BscsInvalidFieldException, BscsPendingException, BscsInvalidStateException {
        try {
            List<BSCSContractServiceParameter> bscsParams = new ArrayList<>();
            for (BscsContractServiceParameter param : params) {
                bscsParams.add(param.getBscsModel());
            }
            parameterServiceAdapter.contractServiceParameterswrite(null, contractId, profileId, null, serviceId,
                    bscsParams);
            if (commit) {
                ConnectionHolder.getCurrentConnection().commit();
            }
        } catch (SOIException ex) {
            logger.debug("BSCS CONTRACT_SERVICE_PARAMETERS.WRITE error with code: " + ex.getCode());
            if (ex.getCode() != null) {
                if (ex.getCode().contains("FUNC_FRMWK_SRV.id0468")) {
                    // invalid public key
                    throw new BscsInvalidIdException(null, ex.getFirstArg(), ex.getMessage());
                }
                if (ex.getCode().contains("RC6608-004")) {
                    // service does not belong to contract - translate message
                    // to report public key
                    throw new BscsInvalidIdException(BscsFieldExceptionEnum.SERVICE_ID, serviceId,
                            "The service with id {" + serviceId + "} was not found in the contract with id {"
                                    + contractId + "}");
                }
                if (ex.getCode().contains("RC2018") || ex.getCode().contains("CommonDomain.Parameter.Invalid")
                        || ex.getCode().contains("CommonDomain.Parameter.IsNull")) {
                    // invalid parameter value
                    throw new BscsInvalidFieldException(BscsFieldExceptionEnum.PARAMETER_VALUE, ex.getFirstArg(),
                            ex.getMessage());
                }
                if (ex.getCode().contains("ValidationError")) {
                    String message = ex.getMessage();
                    // invalid parameter value
                    throw new BscsInvalidFieldException(BscsFieldExceptionEnum.PARAMETER_VALUE, "?",
                            message.substring(0, message.indexOf("Error code") - 2));
                }
                if (ex.getCode().contains("RC6236-004")
                        || ex.getCode().contains("CommonDomain.Parameter.ParameterChangeNotAllowed")) {
                    throw new BscsInvalidStateException(BscsFieldExceptionEnum.CONTRACT_ID,
                            "Contract state do not allow parameter modification");
                }
                if (ex.getCode().contains("ParameterRequestPending")) {
                    throw new BscsPendingException(ex.getMessage());
                }
            }
            throw ex;
        }
    }

}
