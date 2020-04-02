package com.orange.bscs.cms.servicelayeradapter;

import static com.orange.bscs.api.utils.config.Constants.P_CO_ID;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.orange.bscs.api.connection.ConnectionHolder;
import com.orange.bscs.api.exceptions.ErrorDictionary;
import com.orange.bscs.api.exceptions.factory.FaultFactory;
import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.model.exception.SOIException;
import com.orange.bscs.api.utils.config.Constants;
import com.orange.bscs.api.utils.tools.Tools;
import com.orange.bscs.commands.BaseDAO;
import com.orange.bscs.model.businesspartner.BSCSAddress;
import com.orange.bscs.model.contract.BSCSContract;
//import com.orange.bscs.model.contract.BSCSContractCUG;
import com.orange.bscs.model.contract.BSCSContractDevice;
import com.orange.bscs.model.contract.BSCSContractFreeUnit;
import com.orange.bscs.model.contract.BSCSContractResource;
import com.orange.bscs.model.contract.BSCSContractSearch;
import com.orange.bscs.model.contract.BSCSContractService;
import com.orange.bscs.model.contract.BSCSContractServicePackage;
import com.orange.bscs.model.contract.BSCSProductChange;
import com.orange.bscs.model.product.BSCSRatePlanChange;
import com.orange.bscs.model.usagedata.Balances;

@Repository
public class ContractServiceAdapter extends BaseDAO implements
		ContractServiceAdapterI {

	private static final Logger LOG = LoggerFactory
			.getLogger(ContractServiceAdapter.class);

	private static final String P_SYNC_WITH_DB = "SYNC_WITH_DB";

	/** {@inheritDoc} */
	@Override
	public void contractServicesAdd(Long coid, String coidpub,
			List<BSCSContractService> services) {
		String cmd = Constants.CMD_CONTRACT_SERVICES_ADD;

		if (null == coid && Tools.isNullOrEmpty(coidpub)) {
			throw new SOIException(String.format(ERR_RC2000, cmd,
					Constants.P_CO_ID_PUB));
		}

		BSCSContractServicePackage insert = new BSCSContractServicePackage();
		if (!Tools.isNullOrEmpty(coidpub)) {
			insert.setContractIdPub(coidpub);
		} else {
			insert.setContractId(coid);
		}
		insert.addServiceAll(services);

		execute(cmd, insert);
	}

	/** {@inheritDoc} */
	@Override
	public List<BSCSContractService> contractServicesRead(Long coid,
			String coidpub, Long profileId, Long spcode, String spcodepub) {
		List<BSCSContractService> result = null;

		BSCSContractService ct = new BSCSContractService();
		if (null != coidpub) {
			ct.setContractIdPub(coidpub);
		} else {
			ct.setContractId(coid);
		}

		ct.setProfileId(profileId);

		if (null != spcodepub) {
			ct.setServicePackagePublicCode(spcodepub);
		} else {
			ct.setServicePackageCode(spcode);
		}

		BSCSContract res = execute(Constants.CMD_CONTRACT_SERVICES_READ, ct,
				BSCSContract.class);
		if (null != res) {
			// We use BSCSContract, so each BSCSContractService contains coId &
			// coIdPub ;-)
			result = res.getServices();
		}
		return result;
	}

	/** {@inheritDoc} */
	@Override
	public List<BSCSContractService> contractServicesRead(String coidpub,Long snCode) {
		List<BSCSContractService> result = null;

		BSCSContractService ct = new BSCSContractService();
		if (null != coidpub) {
			ct.setContractIdPub(coidpub);
		}
		if (null != snCode){
			ct.setServiceCode(snCode);
		}

		BSCSContract res = execute(Constants.CMD_CONTRACT_SERVICES_READ, ct,
				BSCSContract.class);
		if (null != res) {
			result = res.getServices();
		}
		return result;
	}

    @Override
    public List<BSCSContractService> contractServicesRead(BSCSContractService criteria) {
        List<BSCSContractService> result = null;
        BSCSContract res = execute(Constants.CMD_CONTRACT_SERVICES_READ, criteria, BSCSContract.class);
        if (null != res) {
            result = res.getServices();
        }
        return result;
    }

	/** {@inheritDoc} */
	@Override
	public BSCSContractService contractServicesRead(Long coId, String coIdPub,
			Long profileId, Long spcode, String spcodepub, Long sncode,
			String sncodepub) {
		List<BSCSContractService> services = contractServicesSearch(coId,
				coIdPub, profileId, spcode, spcodepub, sncode, sncodepub);
		BSCSContractService service = null;
		if (null != services && 0 < services.size()) {
			service = services.get(0);
		}
		return service;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * Execute CONTRACT_SERVICES.READ (like method contractServicesRead) but
	 * return a list of Service and not only One.
	 */
	@Override
	public List<BSCSContractService> contractServicesSearch(Long coId,
			String coIdPub, Long profileId, Long spcode, String spcodepub,
			Long sncode, String sncodepub) {
		BSCSContractService criteria = new BSCSContractService();
		if (null != coIdPub) {
			criteria.setContractIdPub(coIdPub);
		} else {
			criteria.setContractId(coId);
		}
		criteria.setProfileId(profileId);
		if (null != spcodepub) {
			criteria.setServicePackagePublicCode(spcodepub);
		} else {
			criteria.setServicePackageCode(spcode);
		}
		if (null != sncodepub) {
			criteria.setServiceCodePub(sncodepub);
		} else {
			criteria.setServiceCode(sncode);
		}

        BSCSContract out = execute(
				Constants.CMD_CONTRACT_SERVICES_READ, criteria,
                BSCSContract.class);
		return out.getServices();
	}

	/** {@inheritDoc} */
	@Override
	public void contractServicesWrite(Long coid, String coidpub,
			List<BSCSContractService> services) {
		String cmd = Constants.CMD_CONTRACT_SERVICES_WRITE;

		if (null == coid && Tools.isNullOrEmpty(coidpub)) {
			throw new SOIException(String.format(ERR_RC2000, cmd,
					Constants.P_PLCODE));
		}

		BSCSContractServicePackage update = new BSCSContractServicePackage();
		if (!Tools.isNullOrEmpty(coidpub)) {
			update.setContractIdPub(coidpub);
		} else {
			update.setContractId(coid);
		}
		update.addServiceAll(services);

		execute(cmd, update);
	}

	/** {@inheritDoc} */
	@Override
	public void contractServicesWriteCoIdPub(String coidpub,
			BSCSContractService services) {
		String cmd = Constants.CMD_CONTRACT_SERVICES_WRITE;

		BSCSContractServicePackage update = new BSCSContractServicePackage();
		if (!Tools.isNullOrEmpty(coidpub)) {
			update.setContractIdPub(coidpub);
		}
		update.removeService(services);
		
		execute(cmd, update);
	}

	/** {@inheritDoc} */
	@Override
	public List<BSCSContract> contractSearch(
			BSCSContractSearch contractSearchInputModel) {
        BSCSModel bscsModel = execute(Constants.CMD_CONTRACTS_SEARCH,
				contractSearchInputModel);
        return bscsModel.getListOfBSCSModelValue(
				Constants.P_CONTRACTS, BSCSContract.class);
	}

	private void addInfoField(BSCSModel update, String name, String value) {
		if (name == null) {
			throw FaultFactory.getInstance().newInvalidInputException(
					ErrorDictionary.API_1406, (Exception) null,
					Constants.CST_NAME, Constants.CST_NAME);
		}
		if (value == null) {
			// Migration Multi-plays => Mono play (ex FMI=>Wimax) , need to
			// reset TEXT15
			update.setNullStringValue(name);
		} else {
			String valueUpperCase = value.toUpperCase();
			if (name.matches(Constants.CST_TEXT_COMBO_INFOFIELD_REGEX)) {
				update.setStringValue(name, value);
			} else if (name.matches(Constants.CST_CHECK_INFOFIELD_REGEX)) {
				// we check values before concerning CHECK
				if (!(valueUpperCase.equals(Constants.CST_TRUE_UPPER_TEXT) || valueUpperCase
						.equals(Constants.CST_FALSE_UPPER_TEXT))) {
					throw FaultFactory.getInstance().newInvalidInputException(
							ErrorDictionary.API_1421, (Exception) null, value);
				}
				update.setBooleanValue(name, valueUpperCase
						.equals(Constants.CST_TRUE_UPPER_TEXT) ? true : false);
			} else {
				throw FaultFactory.getInstance().newInvalidInputException(
						ErrorDictionary.API_1422, (Exception) null, name, null);
			}
		}
	}

	@Override
	public void contractCancel(Long coid, String coidpub, Integer dnRetention,
			Integer devRetention, Integer portRetention) {
		BSCSModel input = initContractModel(coid, coidpub,
				Constants.CMD_CONTRACT_CANCEL);
		input.setIntegerValue(Constants.P_CO_DN_RETENTION, dnRetention);
		input.setIntegerValue(Constants.P_CO_DEV_RETENTION, devRetention);
		input.setIntegerValue(Constants.P_CO_PORT_RETENTION, portRetention);

		execute(Constants.CMD_CONTRACT_CANCEL, input);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * <pre>
	 * CONTRACT_HISTORY.READ {
	 *   CO_ID                =  : java.lang.Long
	 *   CO_ID_PUB            =  : java.lang.String
	 * }
	 * => {
	 *   DEVICES_NUM          =  : java.lang.Integer
	 *   NUM_STATES           =  : java.lang.Integer
	 *   contract_history     = sub element : com.lhs.ccb.common.soiimpl.NamedValueContainerList
	 *  -[0]CONTRACT_ENTRY_DATE  =  : com.lhs.ccb.common.soiimpl.SVLDateTimeImpl
	 *  -[0]CONTRACT_PENDING     =  : java.lang.Boolean
	 *  -[0]CONTRACT_REASON      =  : java.lang.Long
	 *  -[0]CONTRACT_STATUS      =  : java.lang.Character
	 *  -[0]CONTRACT_USER_LAST_MOD =  : java.lang.String
	 *  -[0]CONTRACT_VALID_FROM  =  : com.lhs.ccb.common.soiimpl.SVLDateTimeImpl
	 *   contract_device_history = sub element : com.lhs.ccb.common.soiimpl.NamedValueContainerList
	 *  -[0]DEVICE_ENTRY_DATE    =  : com.lhs.ccb.common.soiimpl.SVLDateTimeImpl
	 *  -[0]DEVICE_ID            =  : java.lang.Long
	 *  -[0]DEVICE_MOD_DATE      =  : com.lhs.ccb.common.soiimpl.SVLDateTimeImpl
	 *  -[0]DEVICE_PORT          =  : java.lang.String
	 *  -[0]DEVICE_SEQNO         =  : java.lang.Integer
	 *  -[0]DEVICE_SM_NUM        =  : java.lang.String
	 *  -[0]DEVICE_STATUS        =  : java.lang.String
	 *  -[0]DEVICE_USER_LAST_MOD =  : java.lang.String
	 *  -[0]DEVICE_VALID_FROM    =  : com.lhs.ccb.common.soiimpl.SVLDateTimeImpl
	 *  -[0]HLCODE               =  : java.lang.Long
	 *  -[0]HLCODE_PUB           =  : java.lang.String
	 * *-[0]REASON               =  : java.lang.Long
	 * }
	 * </pre>
	 */
	@Override
	public BSCSModel contractHistoryRead(Long coId, String coIdPub) {
		BSCSContract contract = initContractModel(coId, coIdPub,
				Constants.CMD_CONTRACT_HISTORY_READ);
		return execute(Constants.CMD_CONTRACT_HISTORY_READ, contract);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * Read Infofields of a contrat
	 */
	@Override
	public BSCSModel contractInfoRead(Long coid, String coidpub) {
		BSCSContract criteria = new BSCSContract();
		if (!Tools.isNullOrEmpty(coidpub)) {
			criteria.setContractIdPub(coidpub);
		} else {
			criteria.setContractId(coid);
		}
		return execute(Constants.CMD_CONTRACT_INFO_READ, criteria);
	}

	/** {@inheritDoc} */
	@Override
	public void contractInfoWrite(Long coid, String coidpub,
			Map<String, String> infoFields) {
		final String cmd = Constants.CMD_CONTRACT_INFO_WRITE;
		if (null == coid && Tools.isNullOrEmpty(coidpub)) {
			throw new SOIException(String.format(ERR_RC2000, cmd, P_CO_ID));
		}

		BSCSModel update = new BSCSModel();
		if (!Tools.isNullOrEmpty(coidpub)) {
			update.setStringValue(Constants.P_CO_ID_PUB, coidpub);
		} else {
			update.setLongValue(P_CO_ID, coid);
		}

		for (Entry<String, String> entry : infoFields.entrySet()) {
			addInfoField(update, entry.getKey(), entry.getValue());
		}
		execute(cmd, update);
	}

    @Override
    public void contractInfoWrite(BSCSModel info) {
        final String cmd = Constants.CMD_CONTRACT_INFO_WRITE;
        execute(cmd, info);
    }

	/** {@inheritDoc} */
	@Override
	public BSCSContract contractNew(BSCSContract newContract) {
		String cmd = Constants.CMD_CONTRACT_NEW;

		if (null == newContract.getCustomerId()
				&& Tools.isNullOrEmpty(newContract.getCustomerIdPub())) {
			LOG.warn("Trying to create contract : {}", newContract);
			throw new SOIException(String.format(ERR_RC2000, cmd,
					Constants.P_CS_ID));
		}

		if (null == newContract.getRatePlanCode()
				&& Tools.isNullOrEmpty(newContract.getRatePlanPublicCode())) {
			LOG.warn("Trying to create contract : {}", newContract);
			throw new SOIException(String.format(ERR_RC2000, cmd,
					Constants.P_RPCODE));
		}

		if (null == newContract.getNetworkId()
				&& Tools.isNullOrEmpty(newContract.getNetworkPublicCode())) {
			LOG.warn("Trying to create contract : {}", newContract);
			throw new SOIException(String.format(ERR_RC2000, cmd,
					Constants.P_PLCODE));
		}

		if (null == newContract.getSubmarketId()) {
			LOG.warn("Trying to create contract : {}", newContract);
			throw new SOIException(String.format(ERR_RC2000, cmd,
					Constants.P_SUBM_ID));
		}

		if (null == newContract.getMarketId()
				&& Tools.isNullOrEmpty(newContract.getMarketIdPub())) {
			LOG.warn("Trying to create contract : {}", newContract);
			throw new SOIException(String.format(ERR_RC2000, cmd,
					Constants.P_SCCODE));
		}

		newContract.setBooleanValue(Constants.P_AUTO_ASSIGN_CORE_SERV,
				Boolean.FALSE);
		newContract.setBooleanValue(Constants.P_CO_ARCHIVE, Boolean.TRUE);

		return execute(Constants.CMD_CONTRACT_NEW, newContract,
				BSCSContract.class);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @Override public BSCSContractCUG contractNewCug(BSCSContractCUG
	 *           newContract) { String cmd = CMD_CUG_AGREEMENT_WRITE;
	 * 
	 *           if (null == newContract.getAdminCustomerId() &&
	 *           Tools.isNullOrEmpty(newContract.getAdminCustomerIdPub())) {
	 *           LOG.warn("Trying to create contract : {}",newContract); throw
	 *           new SOIException(String.format(ERR_RC2000, cmd,
	 *           P_ADMIN_CS_ID)); }
	 * 
	 *           if (null == newContract.getPaymentCustomerId() &&
	 *           Tools.isNullOrEmpty(newContract.getPaymentCustomerIdPub())) {
	 *           LOG.warn("Trying to create contract : {}",newContract); throw
	 *           new SOIException(String.format(ERR_RC2000, cmd,
	 *           P_PAYMENT_CS_ID)); }
	 * 
	 *           if (null == newContract.getRatePlanCode() &&
	 *           Tools.isNullOrEmpty(newContract.getRatePlanPublicCode())) {
	 *           LOG.warn("Trying to create contract : {}",newContract); throw
	 *           new SOIException(String.format(ERR_RC2000, cmd, P_RPCODE)); }
	 * 
	 *           if (null == newContract.getCugInterLockCode()) {
	 *           LOG.warn("Trying to create contract : {}",newContract); throw
	 *           new SOIException(String.format(ERR_RC2000, cmd,
	 *           "CUG_INTERLOCKCODE")); }
	 * 
	 *           if (null == newContract.getCugAction()) {
	 *           LOG.warn("Trying to create contract : {}",newContract); throw
	 *           new SOIException(String.format(ERR_RC2000, cmd, "CUG_ACTION"));
	 *           }
	 * 
	 *           if (null == newContract.getMarketId() &&
	 *           Tools.isNullOrEmpty(newContract.getMarketIdPub())) {
	 *           LOG.warn("Trying to create contract : {}",newContract); throw
	 *           new SOIException(String.format(ERR_RC2000, cmd, P_SCCODE)); }
	 * 
	 *           newContract.setBooleanValue(P_AUTO_ASSIGN_CORE_SERV,
	 *           Boolean.FALSE); newContract.setBooleanValue(P_CO_ARCHIVE,
	 *           Boolean.TRUE);
	 * 
	 *           return execute(CMD_CUG_AGREEMENT_WRITE, newContract,
	 *           BSCSContractCUG.class); }
	 */

	/**
	 * {@inheritDoc}
	 * 
	 * execute CONTRACT.READ
	 */
	@Override
	public BSCSContract contractRead(Long coId, String coIdPub,
			boolean syncWithDB) {
		BSCSContract input = initContractModel(coId, coIdPub,
				Constants.CMD_CONTRACT_READ);
		if (syncWithDB) {
			input.setBooleanValue(P_SYNC_WITH_DB, Boolean.TRUE);
		}
		return execute(Constants.CMD_CONTRACT_READ, input, BSCSContract.class);
	}

	@Override
	public void contractWrite(BSCSContract contract) {
		final String cmd = Constants.CMD_CONTRACT_WRITE;
		if (Tools.isNullOrEmpty(contract.getContractIdPub())
				&& null == contract.getContractId()) {
			throw new SOIException(String.format(ERR_RC2000, cmd, P_CO_ID));
		}

		execute(Constants.CMD_CONTRACT_WRITE, contract);
	}

	/** {@inheritDoc} */
	@Override
	public BSCSContractDevice contractDevicesRead(Long coId, String coIdPub) {
		BSCSContract contract = initContractModel(coId, coIdPub,
				Constants.CMD_CONTRACT_DEVICES_READ);
		return execute(Constants.CMD_CONTRACT_DEVICES_READ, contract,
				BSCSContractDevice.class);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * CONTRACT_RATEPLAN_HISTORY.READ { CO_ID = : java.lang.Long CO_ID_PUB = :
	 * java.lang.String } => { CO_ID = : java.lang.Long CO_ID_PUB = :
	 * java.lang.String contracted composite product history = sub element :
	 * com.lhs.ccb.common.soiimpl.NamedValueContainerList -[0]RPCODE = :
	 * java.lang.Long -[0]RPCODE_PUB = : java.lang.String -[0]RP_SEQNO = :
	 * java.lang.Long -[0]RP_USERLASTMOD = : java.lang.String -[0]RP_VALID_FROM
	 * = : com.lhs.ccb.common.soiimpl.SVLDateImpl }
	 */
	@Override
	public BSCSModel contractRatePlanHistoryRead(Long coId, String coIdPub) {
		BSCSContract contract = initContractModel(coId, coIdPub,
				Constants.CMD_CONTRACT_RATEPLAN_HISTORY_READ);
		return execute(Constants.CMD_CONTRACT_RATEPLAN_HISTORY_READ, contract);
	}

	@Override
	public void contractDeviceAdd(BSCSContractDevice cmdContractDeviceAdd) {
		final String cmd = Constants.CMD_CONTRACT_DEVICE_ADD;
		if (null == cmdContractDeviceAdd.getContractId()
				&& Tools.isNullOrEmpty(cmdContractDeviceAdd.getContractIdPub())) {
			throw new SOIException(String.format(ERR_RC2000, cmd, P_CO_ID));
		}

		ConnectionHolder.getCurrentConnection().execute(cmd,
				cmdContractDeviceAdd);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * Execute CONTRACT_DEVICE.ADD
	 */
	@Override
	public void contractDeviceAdd(Long coid, String coIdPub, Long smId) {
		final String cmd = Constants.CMD_CONTRACT_DEVICE_ADD;

		Integer resType = Integer.valueOf(Constants.CST_RES_TYPE);

		if (null == coid && Tools.isNullOrEmpty(coIdPub)) {
			throw new SOIException(String.format(ERR_RC2000, cmd, P_CO_ID));
		}

		BSCSContractDevice simCardInputModel = new BSCSContractDevice();
		if (null != coIdPub) {
			simCardInputModel.setContractIdPub(coIdPub);
		} else {
			simCardInputModel.setContractId(coid);
		}
		simCardInputModel.setLongValue(Constants.P_RES_ID, smId);
		simCardInputModel.setIntegerValue(Constants.P_RES_TYPE, resType);

		contractDeviceAdd(simCardInputModel);

	}

	/**
	 * 
	 * Execute PRODUCT.CHANGE.
	 * 
	 * List of Dropped service (return by PRODUCT_CHANGE_SERVICES.READ) must be
	 * empty otherwise : Error code: CMS.Contract.InvalidListOfServicePackages<br/>
	 * Error message: The new Service Package does not contains all services of
	 * the original Service Package
	 * 
	 */
	@Override
	public BSCSRatePlanChange productChange(BSCSProductChange input) {

		if (StringUtils.isBlank(input.getContractIdPub())) {
			throw new SOIException(String.format(ERR_RC2000,
					Constants.CMD_PRODUCT_CHANGE, Constants.P_CO_ID_PUB));
		}
		if (StringUtils.isBlank(input.getNewRatePlanIdPub())) {
			throw new SOIException(String.format(ERR_RC2000,
					Constants.CMD_PRODUCT_CHANGE, Constants.P_RPCODE_NEW_PUB));
		}

		return execute(Constants.CMD_PRODUCT_CHANGE, input,
				BSCSRatePlanChange.class);
	}

	/** {@inheritDoc} */
	@Override
	public List<BSCSAddress> installationAddressRead(Long coId, String coIdPub) {
		BSCSContract input = new BSCSContract();
		if (null != coIdPub) {
			input.setContractIdPub(coIdPub);
		} else {
			input.setContractId(coId);
		}
		BSCSModel out = execute(Constants.CMD_INSTALLATION_ADDRESS_READ, input);
		return out.getListOfBSCSModelValue(Constants.P_INSTALLATION_ADDRESSES,
				BSCSAddress.class);

	}

	/** {@inheritDoc} */
	@Override
	public void installationAddressWrite(BSCSAddress addrWrite) {
		execute(Constants.CMD_INSTALLATION_ADDRESS_WRITE, addrWrite);
	}

	@Override
	public BSCSContractResource contractResourcesReplace(BSCSContractResource bscsContractResource) {
		return execute(Constants.CMD_CONTRACT_RESOURCES_REPLACE, bscsContractResource, BSCSContractResource.class);

	}

    /** {@inheritDoc} */
    @Override
    public List<BSCSContractFreeUnit> contractFreeUnitsReadByCoIdPub(Long coid, String coidpub) {

        BSCSContractFreeUnit bscsContractFreeUnit = new BSCSContractFreeUnit();
        if (coid != null) {
            bscsContractFreeUnit.setCoId(coid);
        }
        if (coidpub != null) {
            bscsContractFreeUnit.setContractIdPub(coidpub);
        }
        bscsContractFreeUnit.setBalanceType("I");

        BSCSContractFreeUnit res = execute(Constants.CMD_CONTRACT_FUA_READ, bscsContractFreeUnit,
                BSCSContractFreeUnit.class);
        return res.getBscsContractFreeUnit();
    }

    /** {@inheritDoc} */
    @Override
    public List<Balances> balancesRead(Long coId, String coIdPub) {
        BSCSContract input = new BSCSContract();
        if (null != coIdPub) {
            input.setContractIdPub(coIdPub);
        } else {
            input.setContractId(coId);
        }
        BSCSModel out = execute(Constants.CMD_BALANCES_READ, input);
        return out.getListOfBSCSModelValue(Constants.P_LIST_OF_BALANCES, Balances.class);
    }
}
