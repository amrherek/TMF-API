package com.orange.bscs.cms.servicelayeradapter;

import java.util.List;
import java.util.Map;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.model.businesspartner.BSCSAddress;
import com.orange.bscs.model.contract.BSCSContract;
import com.orange.bscs.model.contract.BSCSContractDevice;
import com.orange.bscs.model.contract.BSCSContractFreeUnit;
import com.orange.bscs.model.contract.BSCSContractResource;
import com.orange.bscs.model.contract.BSCSContractSearch;
import com.orange.bscs.model.contract.BSCSContractService;
import com.orange.bscs.model.contract.BSCSProductChange;
import com.orange.bscs.model.product.BSCSRatePlanChange;
import com.orange.bscs.model.usagedata.Balances;

/**
 * <pre>
 * {@code
 * CONTRACTS.SEARCH
 * CONTRACT_STATES.READ
 * CONTRACT_RATEPLAN_HISTORY.READ
 * CONTRACT.MOVE
 * CONTRACT.TAKEOVER
 * ANONYMOUS_DIRECTORY_NUMBERS.READ
 * ANONYMOUS_DIRECTORY_NUMBERS.WRITE
 * MICRO_CELLS.READ
 * MICRO_CELLS.WRITE
 * CONTRACT_DEVICE.ADD
 * CONTRACT_DEVICES.READ
 * CONTRACT_EXCHANGE_CARRIER_HISTORY.READ
 * CONTRACT_RESOURCES.REPLACE
 * CONTRACT_SERVICE_RESOURCES.WRITE
 * CONTRACT_SERVICES.READ
 * CONTRACT_SERVICES.WRITE
 * CONTRACT_EXCHANGE_CARRIER_HISTORY.WRITE
 * CONTRACT_SERVICES.ADD
 * PRODUCT.CHANGE
 * PROFILE_SERVICES.CHANGE
 * VPN_CONTRACT.READ
 * CONTRACT.IN_READ
 * EXTERNAL_CARRIERS.READ
 * INSTALLATION_ADDRESS.READ
 * INSTALLATION_ADDRESS.WRITE
 * CONTRACT_XFUP.ADD
 * CONTRACT_FUA.READ
 * CREDIT_LIMIT.READ
 * CREDIT_LIMIT.WRITE
 * CONTRACT_SERVICES.ADD
 * CONTRACT_SERVICES.WRITE
 * CONTRACT_SERVICE_RESOURCES.WRITE
 * CONTRACT.TAKEOVER
 * }
 * </pre>
 * 
 * And others commands :
 * 
 * <pre>
 * {@code
 * AllowedServicePackagesRead
 * BOPHistoryRead
 * ContractBundleRead
 * ContractBundleWrite
 * ContractCancel
 * ContractDataExchangeCheck
 * ContractDataExchangeHistoryRead
 * ContractDataExchangeRead
 * ContractDataExchangeWrite
 * ContractExport
 * ContractHistoryRead
 * ContractImport
 * ContractInfoRead
 * ContractInfoWrite
 * ContractNew
 * ContractProductRead
 * ContractProductWrite
 * ContractRatingInvoiceHistoryRead
 * ContractRatingInvoiceRead
 * ContractRatingInvoiceWrite
 * ContractRead
 * ContractResourcesRead
 * ContractSectionsRead
 * ContractSubsectionsRead
 * ContractTemplateResourceRead
 * ContractTemplateResourceWrite
 * ContractTemplateServiceResourcesRead
 * ContractTemplateServiceResourcesWrite
 * ContractTypesRead
 * ContractWrite
 * CreditLimitHistoryRead
 * RoamingAgreementGroupsRead
 * }
 * </pre>
 * 
 * @author IT&Labs
 *
 */
public interface ContractServiceAdapterI {

	/**
	 * <p>
	 * contractServicesAdd.
	 * </p>
	 * 
	 * @param coid
	 *            a contract internal id.
	 * @param coidpub
	 *            a contract public key.
	 * @param services
	 *            a {@link java.util.List} of new services.
	 */
	void contractServicesAdd(Long coid, String coidpub,
			List<BSCSContractService> services);

	/**
	 * <p>
	 * contractServicesRead.
	 * </p>
	 * 
	 * @param coid
	 *            a contract internal Id.
	 * @param coidpub
	 *            a contract public key.
	 * @param profileId
	 *            a profile ID.
	 * @param spcode
	 *            a ServicePackage code.
	 * @param spcodepub
	 *            a Service Package public code.
	 * @return a {@link java.util.List} of Services configured in this Service
	 *         Package.
	 */
	List<BSCSContractService> contractServicesRead(Long coid, String coidpub,
			Long profileId, Long spcode, String spcodepub);

	/**
	 * 
	 * Reads the data of the contract services identified by the in-parameters.
	 * <p>
	 * This command is also used to read the services assigned to Prepaid
	 * Contract Templates.
	 * <p>
	 * In order to read the services assigned to a Prepaid Contract Template the
	 * user must have the permission for Prepaid Contract Templates.
	 * <p>
	 * &nbsp;
	 * </p>
	 * <p>
	 * Input Arguments The identification of the services.
	 * <ul>
	 * <li>When only CO_ID/CO_ID_PUB id is passed, all services currently on the
	 * contract are returned.
	 * <li>When CO_ID/CO_ID_PUB and SPCODE/SPCODE_PUB are passed, all contracted
	 * services in the SP are returned.
	 * <li>When SNCODE/SNCODE_PUB is passed, only the single service is
	 * returned. .
	 * <li>When PROFILE_ID id is passed, only the services associated with the
	 * given profile are returned.
	 * </ul>
	 * <p>
	 * It is up to the client to ensure that the SPCODE, SNCODE, PROFILE_ID are
	 * consistent.
	 * </p>
	 * <p>
	 * If inconsistent input is passed, then no items would be returned.
	 * </p>
	 * 
	 * @param coid
	 *            a contract internal id.
	 * @param coidpub
	 *            a contract public key.
	 * @param profileId
	 *            a profile Id.
	 * @param spcode
	 *            a service package internal id.
	 * @param spcodepub
	 *            a service package public key.
	 * @param sncode
	 *            a service internal id.
	 * @param sncodepub
	 *            a service public key.
	 * @return a {@link com.orange.bscs.model.contract.BSCSContractService}.
	 */
	BSCSContractService contractServicesRead(Long coid, String coidpub,
			Long profileId, Long spcode, String spcodepub, Long sncode,
			String sncodepub);

	/**
	 * <p>
	 * contractServicesSearch.
	 * </p>
	 * 
	 * @param coId
	 *            a contract internal id.
	 * @param coIdPub
	 *            a contract public key.
	 * @param profileId
	 *            a profile Id (optional).
	 * @param spcode
	 *            a service package internal id (optional).
	 * @param spcodepub
	 *            a service package public key (optional).
	 * @param sncode
	 *            a service internal id (optional).
	 * @param sncodepub
	 *            a service public key (optional).
	 * @return a {@link java.util.List} of services in the contract.
	 */
	List<BSCSContractService> contractServicesSearch(Long coId, String coIdPub,
			Long profileId, Long spcode, String spcodepub, Long sncode,
			String sncodepub);

	/**
	 * <p>
	 * contractServicesWrite.
	 * </p>
	 * 
	 * @param coid
	 *            a contract internal id.
	 * @param coidpub
	 *            a contract public key.
	 * @param services
	 *            a {@link java.util.List} services to modify.
	 */
	void contractServicesWrite(Long coid, String coidpub,
			List<BSCSContractService> services);

	/**
	 * 
	 * Search for contracts using the passed criteria.
	 * <p>
	 * This command is also used to search for Prepaid Contract Templates.
	 * <p>
	 * In order to search for Prepaid Contract Templates
	 * <ul>
	 * <li>the SEARCHER attribute is set to "PrepaidContractTemplates"
	 * <li>the user must have the permission for Prepaid Contract Templates
	 * </ul>
	 * 
	 * @param contractSearchInputModel
	 *            a {@link com.orange.bscs.api.model.BSCSModel} criteria.
	 * @return a BSCSModel with list of contracts in "contracts" field.
	 */
	List<BSCSContract> contractSearch(
			BSCSContractSearch contractSearchInputModel);

	/**
	 * Remove a contract that is ON_HOLD
	 **/
	void contractCancel(Long coid, String coidpub, Integer dnRetention,
			Integer devRetention, Integer portRetention);

	/**
	 * <p>
	 * contractHistoryRead.
	 * </p>
	 * 
	 * @param coId
	 *            a contract internal id.
	 * @param coIdPub
	 *            a contract public key.
	 * @return a {@link com.orange.bscs.api.model.BSCSModel} of rateplan
	 *         history.
	 */
	BSCSModel contractHistoryRead(Long coId, String coIdPub);

	/**
	 * Read Contract info fields
	 * 
	 * <pre>
	 * {@code
	 * CONTRACT_INFO.READ {
	 *   CO_ID                =  : java.lang.Long
	 *   CO_ID_PUB            =  : java.lang.String
	 * } => {  
	 *   CO_ID                =  : java.lang.Long
	 *   CO_ID_PUB            =  : java.lang.String
	 *   CHECK01              =  : java.lang.Boolean
	 *   ....
	 *   CHECK20              =  : java.lang.Boolean
	 *   COMBO01              =  : java.lang.String
	 *   ...
	 *   COMBO20              =  : java.lang.String
	 *   TEXT01               =  : java.lang.String
	 *   ...
	 *   TEXT30               =  : java.lang.String
	 * }
	 * }
	 * </pre>
	 * 
	 * @param coid
	 *            a contract internal id.
	 * @param coidpub
	 *            a contract public key.
	 * @return a generic BSCSModel of infofields
	 */
	BSCSModel contractInfoRead(Long coid, String coidpub);

	/**
	 * Initialise or modify values of the info fields for this contract.
	 * 
	 * <pre>
	 * {@code
	 * CONTRACT_INFO.WRITE {
	 *   CO_ID                =  : java.lang.Long
	 *   CO_ID_PUB            =  : java.lang.String
	 *   CHECK01              =  : java.lang.Boolean
	 *   ....
	 *   CHECK20              =  : java.lang.Boolean
	 *   COMBO01              =  : java.lang.String
	 *   ...
	 *   COMBO20              =  : java.lang.String
	 *   TEXT01               =  : java.lang.String
	 *   ...
	 *   TEXT30               =  : java.lang.String
	 * }
	 * => {
	 * }
	 * }
	 * </pre>
	 * 
	 * @param coid
	 *            a contract internal Id.
	 * @param coidpub
	 *            a contract public key.
	 * @param infoFields
	 *            a {@link java.util.Map} of key/value of info-fields.
	 */
	void contractInfoWrite(Long coid, String coidpub,
			Map<String, String> infoFields);

    /**
     * execute CONTRACT_INFO.WRITE
     * 
     * @param info
     */
    void contractInfoWrite(BSCSModel info);

	/**
	 * 
	 * Creates a new contract with specified data of the in-parameters.
	 * <p>
	 * The type of the contract to create is determined by the CONTRACT_TYPE_ID
	 * input parameter. .
	 * 
	 * This command supports the creation of the following types of contracts: .
	 * <ol>
	 * <li>content provider contract
	 * <li>subscriber contract
	 * <li>subscriber contract template
	 * <li>pre-activated subscriber contract
	 * <li>TAP incollect contract
	 * <li>TAP outcollect contract
	 * <li>CIBER incollect parent contract
	 * <li>CIBER outcollect parent contract
	 * <li>CIBER incollect child contract
	 * <li>CIBER outcollect child contract
	 * <li>service provider contract
	 * <li>interexchange carrier inbound contract
	 * <li>interexchange carrier outbound contract
	 * <li>external carrier inbound contract
	 * <li>external carrier outbound contract
	 * <li>dealer contract .
	 * </ol>
	 * 
	 * Party type and party role combinations are assigned to the user.
	 * <p>
	 * This determines whether a user is allowed to maintain a contract.
	 * <p>
	 * The user must be allowed to use the party role and party type of the
	 * second contract holder.
	 * <p>
	 * The contract type cannot be changed after the contract creation.
	 * <p>
	 * A contract is divided into separate contract sections and sub sections.
	 * <p>
	 * Depending on the contract type certain contract (sub)section must or must
	 * not be filled.
	 * <p>
	 * The following commands are used in order to fill contract (sub)sections:
	 * <ol>
	 * <li>CONTRACT.NEW/WRITE (general contract section)
	 * <li>CREDIT_LIMIT.WRITE (payment section)
	 * <li>CONTRACT_DATA_EXCHANGE.WRITE (data exchange section)
	 * <li>CONTRACT_PRODUCT.WRITE (product section)
	 * <li>CONTRACT_EXCHANGE_CARRIER_HISTORY.WRITE (product section)
	 * <li>CONTRACT_RATING_INVOICE_WRITE (rating and invoice section)
	 * </ol>
	 * 
	 * @param newContract
	 *            mandatory : CS_ID or CS_ID_PUB - Customer Id RPCODE - RatePlan
	 * @throws com.orange.bscs.api.model.exception.SOIException
	 *             if Customer is not in ACTIVE state :
	 *             com.lhs.ccb.func.ect.ComponentException:
	 *             Contract.ConsistencyCheckFailed
	 *             .StatesOfContractHolderAndContractNotCompatible
	 * @return a {@link com.orange.bscs.model.contract.BSCSContract} object.
	 */
	BSCSContract contractNew(BSCSContract newContract);

	/**
	 * 
	 * Reads the data of the contract with id as specified in the in-parameter
	 * CONTRACT.
	 * <p>
	 * This command read agreement and customer contract.
	 * 
	 * <code>
	 * CONTRACT.READ {
	 *   CO_ID                =  : java.lang.Long
	 *   CO_ID_PUB            =  : java.lang.String
	 *   SYNC_WITH_DB         =  : java.lang.Boolean
	 * }
	 * => {
	 *   BSCSContract
	 * }
	 * </code>
	 * 
	 * @param coId
	 *            contract internal Id
	 * @param coIdPub
	 *            a contract public key.
	 * @param syncWithDB
	 *            a flag asking to refresh cached data if needed.
	 * @return contract if found
	 */
	BSCSContract contractRead(Long coId, String coIdPub, boolean syncWithDB);

	/**
	 * 
	 * Modifies the data of a existing contract identified by the in-parameter
	 * CO_ID.
	 * <p>
	 * For an agreement contract only the descriprion, input currency, the
	 * signing date and status can be changed .
	 * <p>
	 * For a customer contract all input values are relavant
	 * 
	 * <pre>
	 * {@code
	 * CONTRACT.WRITE {
	 *    BSCSContract
	 * } => {
	 * }
	 * }
	 * </pre>
	 * 
	 * @param update
	 *            new data for contract.
	 */
	void contractWrite(BSCSContract update);

	/**
	 * <p>
	 * contractDevicesRead.
	 * </p>
	 * 
	 * @param coId
	 *            a contract internal id.
	 * @param coIdPub
	 *            a contract public key.
	 * @return a {@link com.orange.bscs.model.contract.BSCSContractDevice}
	 *         object.
	 */
	BSCSContractDevice contractDevicesRead(Long coId, String coIdPub);

	/**
	 * <p>
	 * contractRatePlanHistoryRead.
	 * </p>
	 * 
	 * @param coId
	 *            a contract internal id.
	 * @param coIdPub
	 *            a contract public key.
	 * @return a {@link com.orange.bscs.api.model.BSCSModel} object.
	 */
	BSCSModel contractRatePlanHistoryRead(Long coId, String coIdPub);

	/**
	 *
	 *
	 * Add a contract device .
	 *
	 * Input Arguments Data of the new contract device.
	 * <p>
	 * If the client pass the RES_TYPE and the RES_ID then the resource of this
	 * type with the given id will be assigned to the
	 * ContractedCompositeProduct. .
	 * </p>
	 * <p>
	 * If these parameters are not specified, the system will get the assignable
	 * resources, check if those type of resources are system determined, and if
	 * yes, will get and assign resource to the ContractedCompositeProduct,
	 * using the information given as parameter: NetworkId, HLR,
	 * StorageMediumTemplate, ResourceIssuer, in case that the assignable
	 * resource is a storage medium, and NetworkId, HLR, Numbering Plan for
	 * Port, in case that the assignable resource is a port.
	 * </p>
	 * 
	 * <pre>
	 * {@code
	 * CONTRACT_DEVICE.ADD {
	 *   CO_ID                =  : java.lang.Long
	 *   CO_ID_PUB            =  : java.lang.String
	 *   HLCODE               =  : java.lang.Long
	 *   HLCODE_PUB           =  : java.lang.String
	 *   NPCODE               =  : java.lang.Long
	 *   NPCODE_PUB           =  : java.lang.String
	 *   PI_ID                =  : java.lang.Long
	 *   RES_ID               =  : java.lang.Long
	 *   RES_TYPE             =  : java.lang.Integer
	 *   SMC_ID               =  : java.lang.Long
	 * }
	 * => {
	 *   DEALER_CODE          =  : java.lang.String
	 *   DEALER_ID            =  : java.lang.Long
	 *   DEALER_ID_PUB        =  : java.lang.String
	 * }
	 * }
	 * </pre>
	 *
	 * @param coId
	 *            a {@link java.lang.Long} contract internal Id.
	 * @param coIdPub
	 *            a {@link java.lang.String} contract public key.
	 * @param smId
	 *            a {@link java.lang.Long} storage medium internal Id.
	 */
	void contractDeviceAdd(Long coId, String coIdPub, Long smId);

	void contractDeviceAdd(BSCSContractDevice cmdCOntractDeviceAdd);

	/**
	 * execution of PRODUCT.CHANGE to use case "Change RatePlan"
	 * 
	 * @param changeRatePlan
	 * @return
	 */
	BSCSRatePlanChange productChange(BSCSProductChange changeRatePlan);

	/**
	 * Reads an address of a customer on a contract level. .
	 * <ul>
	 * <li>See also ADDRESS.READ - Command to Read a address of a particular
	 * customer.
	 * <li>See also ADDRESS.WRITE - Command to add, modify or delete a address
	 * of a particular customer.
	 * <li>See also ADDRESSES.READ - Command to read all addresses of a
	 * particular customer.
	 * <li>See also ADDRESSROLES.READ - Command to retrieve a list of the 9
	 * address roles defined in BSCS.
	 * </ul>
	 * Input Arguments : The contract identification
	 *
	 *
	 * <pre>
	 * {@code
	 * INSTALLATION_ADDRESS.READ {
	 *   CO_ID                =  : java.lang.Long
	 *   CO_ID_PUB            =  : java.lang.String
	 * }
	 * => {
	 *   installation addresses = sub element : com.lhs.ccb.common.soiimpl.NamedValueCo
	 * ntainerList
	 *   -[0]BSCSAddress
	 * }
	 * }
	 * </pre>
	 *
	 * @param coId
	 *            contract internal Id
	 * @param coIdPub
	 *            a {@link java.lang.String} object.
	 * @return a {@link java.util.List} list of installation addresses.
	 */
	List<BSCSAddress> installationAddressRead(Long coId, String coIdPub);

	/**
	 * Writes the existing addresses of a customer on a contract level.
	 * <ul>
	 * <li>See also ADDRESS.READ - Command to Read a address of a particular
	 * customer.
	 * <li>See also ADDRESS.WRITE - Command to add, modify or delete a address
	 * of a particular customer.
	 * <li>See also ADDRESSES.READ - Command to read all addresses of a
	 * particular customer.
	 * <li>See also ADDRESSROLES.READ - Command to retrieve a list of the 9
	 * address roles defined in BSCS.
	 * </ul>
	 * 
	 * <pre>
	 * {@code
	 * INSTALLATION_ADDRESS.WRITE {
	 *   CO_ID                =  : java.lang.Long
	 *   CO_ID_PUB            =  : java.lang.String
	 *   installation addresses = sub element : com.lhs.ccb.common.soiimpl.NamedValueCo
	 * ntainerList
	 * *-[0]ACTION               =  : java.lang.String
	 *  -[0]BSCSAddress
	 *  } => {
	 *  }
	 * }
	 * </pre>
	 *
	 * Action: to add a new installation adress ('a'), delete ('d') or modify an
	 * installation adress ('m') Seqno: sequence number of the party (required
	 * for action d,m), if no given a new party will be created
	 *
	 * @param addrWrite
	 *            an {@link com.orange.bscs.model.businesspartner.BSCSAddress}
	 *            installationAddress to save or remove.
	 */
	void installationAddressWrite(BSCSAddress addrWrite);

	BSCSContractResource contractResourcesReplace(BSCSContractResource bscsContractResource);

	void contractServicesWriteCoIdPub(String coidpub,
			BSCSContractService services);

	List<BSCSContractService> contractServicesRead(String coidpub, Long snCode);

    /**
     * Execute command CONTRACT_SERVICES.READ base on given criteria
     * 
     * @param criteria
     * @return
     */
    List<BSCSContractService> contractServicesRead(BSCSContractService criteria);

    /**
     * <p>
     * contractFreeUnitsReadByCoIdPub.
     * </p>
     * 
     * @param coid
     *            a contract internal Id.
     * @param coidpub
     *            a contract public key.
     * @return a {@link java.util.List} object.
     */
    List<BSCSContractFreeUnit> contractFreeUnitsReadByCoIdPub(Long coid, String coidpub);

    /**
     * <p>
     * contractBalances.
     * </p>
     * 
     * @param coidpub
     *            a contract public key.
     * @return a {@link java.util.List} object.
     */
    List<Balances> balancesRead(Long coId, String coIdPub);

}
