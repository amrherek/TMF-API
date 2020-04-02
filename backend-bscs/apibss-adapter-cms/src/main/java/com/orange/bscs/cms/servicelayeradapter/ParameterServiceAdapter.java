package com.orange.bscs.cms.servicelayeradapter;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.orange.bscs.api.connection.ConnectionHolder;
import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.model.exception.SOIException;
import com.orange.bscs.api.utils.config.Constants;
import com.orange.bscs.api.utils.tools.Tools;
import com.orange.bscs.commands.BaseDAO;
import com.orange.bscs.model.Caches;
import com.orange.bscs.model.contract.BSCSContractService;
import com.orange.bscs.model.contract.BSCSContractServiceParameter;
import com.orange.bscs.model.contract.BSCSParameter;
import com.orange.bscs.model.contract.BSCSService;

@Repository
public class ParameterServiceAdapter extends BaseDAO implements
		ParameterServiceAdapterI {

	/** {@inheritDoc} */
	@Override
	public BSCSContractService contractServiceParametersRead(Long coid,
			String coidpub, Long profileId, String spcodepub, String sncodepub) {
		final String cmd = Constants.CMD_CONTRACT_SERVICE_PARAMETERS_READ;

		if (null == coid && Tools.isNullOrEmpty(coidpub)) {
			throw new SOIException(String.format(ERR_RC2000, cmd,
					Constants.P_CO_ID));
		}

		BSCSContractService criteria = new BSCSContractService();

		if (!Tools.isNullOrEmpty(coidpub)) {
			criteria.setStringValue(Constants.P_CO_ID_PUB, coidpub);
		} else {
			criteria.setLongValue(Constants.P_CO_ID, coid);
		}
		criteria.setProfileId(profileId);
		criteria.setServicePackagePublicCode(spcodepub);
		criteria.setServiceCodePub(sncodepub);

		return execute(cmd, criteria, BSCSContractService.class);
	}

    @Override
    public BSCSContractService contractServiceParametersRead(BSCSContractService criteria) {
        return execute(Constants.CMD_CONTRACT_SERVICE_PARAMETERS_READ, criteria, BSCSContractService.class);
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void contractServiceParameterswrite(Long coid, String coidpub,
			Long profileId, String spcodepub, String sncodepub,
			List<BSCSContractServiceParameter> params) {
		String cmd = Constants.CMD_CONTRACT_SERVICE_PARAMETERS_WRITE;

		if (null == coid && Tools.isNullOrEmpty(coidpub)) {
			throw new SOIException(String.format(ERR_RC2000, cmd,
					Constants.P_CO_ID));
		}

		if (null == profileId) {
			throw new SOIException(String.format(ERR_RC2000, cmd,
					Constants.P_PROFILE_ID));
		}

		if (Tools.isNullOrEmpty(sncodepub)) {
			throw new SOIException(String.format(ERR_RC2000, cmd,
					Constants.P_SNCODE));
		}

		BSCSContractService input = new BSCSContractService();
		if (!Tools.isNullOrEmpty(coidpub)) {
			input.setStringValue(Constants.P_CO_ID_PUB, coidpub);
		} else {
			input.setLongValue(Constants.P_CO_ID, coid);
		}
		input.setProfileId(profileId);
		input.setServicePackagePublicCode(spcodepub);
		input.setServiceCodePub(sncodepub);
		input.setParametersWrite(params);

		execute(cmd, input);

	}

    @Override
    public void contractServiceParameterswrite(BSCSContractService input) {
        execute(Constants.CMD_CONTRACT_SERVICE_PARAMETERS_WRITE, input);
    }

	/** {@inheritDoc} */
	@Cacheable(Caches.REF_SERVICEPARAMS_BY_SERVICE)
	@Override
	public List<BSCSParameter> serviceParametersRead(String sccodepub,
			String sncodepub) {
		BSCSService input = new BSCSService();
		input.setStringValue(Constants.P_SCCODE_PUB, sccodepub);
		input.setServicePublicCode(sncodepub);

        return serviceParametersRead(input);
	}

    @Override
    public List<BSCSParameter> serviceParametersRead(BSCSService input) {
        BSCSModel res = ConnectionHolder.getCurrentConnection().execute(Constants.CMD_SERVICE_PARAMETERS_READ, input);
        List<BSCSModel> services = res.getListOfBSCSModelValue(Constants.P_NUM_SERV);
        if (!services.isEmpty()) {
            return services.get(0).getListOfBSCSModelValue(Constants.P_NUM_PARAM, BSCSParameter.class);
        }
        return Collections.emptyList();
    }

	/** {@inheritDoc} */
	@Override
	public BSCSParameter parameterRead(Long csid, String csidpub,
			String sccodepub, Long prmId) {

		final String cmd = Constants.CMD_PARAMETER_READ;
		if (null == csid) {
			if (Tools.isNullOrEmpty(csidpub))
				throw new SOIException(String.format(ERR_RC2000, cmd,
						Constants.P_CO_ID));
		}

		BSCSModel input = new BSCSModel();
		if (!Tools.isNullOrEmpty(csidpub)) {
			input.setStringValue(Constants.P_CS_ID_PUB, csidpub);
		} else {
			input.setLongValue(Constants.P_CS_ID, csid);
		}

		if (Tools.isNullOrEmpty(sccodepub)) {
			throw new SOIException(String.format(ERR_RC2000, cmd,
					Constants.P_SCCODE_PUB));
		}
		input.setStringValue(Constants.P_SCCODE_PUB, sccodepub);
		input.setBooleanValue(Constants.P_GET_VALUES, true);
		input.setLongValue(Constants.P_PRM_ID, prmId);

		BSCSParameter res = ConnectionHolder.getCurrentConnection().execute(
				cmd, input, BSCSParameter.class);
		return res;
	}

    @Override
    public List<BSCSContractServiceParameter> contractServiceParametersHistoryRead(String coidpub, String spcodepub,
            String sncodepub, Long profileId, Date validAt) {

        final String command = Constants.CMD_CONTRACT_SERVICE_PARAMETERS__HISTORY_READ;

        BSCSContractService input = new BSCSContractService();
        if (coidpub != null) {
            input.setContractIdPub(coidpub);
        }
        if (!Tools.isNullOrEmpty(sncodepub)) {
            input.setServiceCodePub(sncodepub);
        }
        if (!Tools.isNullOrEmpty(spcodepub)) {
            input.setServicePackagePublicCode(spcodepub);
        }
        if (validAt != null) {
            input.setDateTimeValue(Constants.P_VALID_AT, validAt);
        }
        input.setProfileId(profileId);

        BSCSContractService results = execute(command, input, BSCSContractService.class);

        List<BSCSContractServiceParameter> serviceParametersReadResult = results.getParametersRead();

        return serviceParametersReadResult;

    }

}
