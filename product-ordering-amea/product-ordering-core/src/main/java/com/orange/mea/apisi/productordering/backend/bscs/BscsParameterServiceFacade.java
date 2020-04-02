package com.orange.mea.apisi.productordering.backend.bscs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.bscs.model.BscsContractServiceParameter;
import com.orange.bscs.model.BscsParameter;
import com.orange.bscs.service.BscsParameterServiceAdapter;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;
import com.orange.bscs.service.exception.BscsInvalidStateException;
import com.orange.bscs.service.exception.BscsPendingException;

@Service
public class BscsParameterServiceFacade {

    @Value("${profileId}")
    protected Long profileId;

    @Autowired
    private BscsParameterServiceAdapter parameterServiceAdapter;

    /**
     * execute SERVICE_PARAMETERS.READ
     * 
     * @param serviceId
     * @param parameterId
     * @return
     * @throws BscsInvalidIdException
     */
    public BscsParameter getParameter(String serviceId, String parameterId) throws BscsInvalidIdException {
        return parameterServiceAdapter.getServiceParameter(serviceId, parameterId);
    }

    /**
     * execute CONTRACT_SERVICE_PARAMETERS.READ
     * 
     * @param contractId
     * @param serviceId
     * @param parameterId
     * @return
     * @throws BscsInvalidIdException
     */
    public BscsContractServiceParameter getContractParameter(String contractId, String serviceId, String parameterId)
            throws BscsInvalidIdException {
        return parameterServiceAdapter.getContractServiceParameter(contractId, serviceId, parameterId, profileId);
    }

    /**
     * execute CONTRACT_SERVICE_PARAMETERS.WRITE. Do not commit
     * 
     * @param contractId
     * @param serviceId
     * @param params
     * @throws BscsInvalidFieldException
     * @throws BscsInvalidIdException
     * @throws BscsPendingException
     * @throws BscsInvalidStateException
     */
    public void writeParameter(String contractId, String serviceId,
            List<BscsContractServiceParameter> params)
            throws BscsInvalidIdException, BscsInvalidFieldException, BscsPendingException, BscsInvalidStateException {
        parameterServiceAdapter.writeContractServiceParameters(contractId, serviceId, params,
                profileId, false);

    }

    /**
     * Build a service parameter with modify action for BSCS request
     *
     * @param bscsContractParameter
     * @param paramValue
     * @param paramValueDescription
     * @return
     */
    public BscsContractServiceParameter buildParamToModify(BscsContractServiceParameter bscsContractParameter,
            String paramValue, String paramValueDescription) {
        return parameterServiceAdapter.buildContractServiceParameter(bscsContractParameter, paramValue,
                paramValueDescription, 'm');
    }

    /**
     * Build a service parameter with add action for BSCS request
     *
     * @param bscsParameter
     * @param paramValue
     * @param paramValueDescription
     * @return
     * @throws TechnicalException
     */
    public BscsContractServiceParameter buildParamToAdd(BscsParameter bscsParameter, String paramValue,
            String paramValueDescription) throws TechnicalException {
        return parameterServiceAdapter.buildContractServiceParameter(bscsParameter, paramValue, paramValueDescription,
                'a');
    }

}
