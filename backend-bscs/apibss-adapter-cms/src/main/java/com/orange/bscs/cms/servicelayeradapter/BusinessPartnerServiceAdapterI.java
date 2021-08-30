package com.orange.bscs.cms.servicelayeradapter;

import java.util.List;
import java.util.Map;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.model.businesspartner.BSCSAddress;
import com.orange.bscs.model.businesspartner.BSCSAddresses;
import com.orange.bscs.model.businesspartner.BSCSCustomer;
import com.orange.bscs.model.businesspartner.BSCSCustomerWriteInput;
import com.orange.bscs.model.businesspartner.BSCSCustomersSearchRequest;
import com.orange.bscs.model.businesspartner.BSCSPaymentArrangement;
import com.orange.bscs.model.businesspartner.BSCSPaymentArrangementAssignment;
import com.orange.bscs.model.businesspartner.EnumAddressRole;
import com.orange.bscs.model.businesspartner.EnumCustomerLevelCode;

/**
 * <pre>
 * {@code
 * ADDRESS.CHECK
 * ADDRESS.READ
 * ADDRESS.WRITE
 * ADDRESSES.READ
 * ADDRESSROLES.READ
 * AREAS.READ
 * CUST_RATEPLAN_HISTORY.READ
 * CUSTCATCODE.READ
 * CUSTOMER_DOCUMENTTYPES.READ
 * CUSTOMER_DOCUMENTTYPES.WRITE
 * CUSTOMER_FLAT.WRITE
 * CUSTOMER_HIERARCHY.WRITE
 * CUSTOMER_LEVELS.READ
 * CUSTOMER_STATES.READ
 * CUSTOMER_TAX_EXEMPTIONS.READ
 * CUSTOMER_TAX_EXEMPTIONS.WRITE
 * CUSTOMERS.SEARCH
 * DEALERS.READ
 * DEALERS.SEARCH
 * IDENTIFICATION_DOCUMENT_TYPE.READ
 * INDIVIDUAL_TAXATION_HISTORY.READ
 * LA_MEMBER.NEW
 * LANGUAGES.READ
 * MARITAL_STATUS.READ
 * PAYMENT_ARRANGEMENT.WRITE
 * PAYMENT_ARRANGEMENTS.READ
 * TITLES.READ
 * TRADES.READ
 * }</pre>
 * 
 * And others commands
 * 
 *  * <pre>
 * {@code
 * AddressConvert
 * AssignedResourcesCheck
 * BusinessUnitChange
 * BusinessUnitCheck
 * CostcentersRead
 * CustomerClone
 * CustomerDuplicateCheck
 * CustomerExport
 * CustomerGroupsRead
 * CustomerImport
 * CustomerMailItemDelete
 * CustomerMailItemRead
 * CustomerMailItemWrite
 * CustomerNew
 * CustomerRead
 * CustomerWrite
 * DealerDataMove
 * DummyCustomerNew
 * DummyCustomerRead
 * OutboundDialedDigitsRead
 * PartyRoleConfigurationRead
 * PosconnectRead
 * TrunkRead
 * }</pre>
 * @author IT&Labs
 *
 */
public interface BusinessPartnerServiceAdapterI {

    /**
     * Retrieve a list of customer categories as defined in the database.
     * <p>
     * <pre>{@code
     * CUSTCATCODE.READ {
     * }
     * => {
     *   CUSTCAT_CODE_DEF     =  : java.lang.Long
     *   CUSTCAT_CODE_DEF_PUB =  : java.lang.String
     *   LIST_OF_CUSTOMER_CATEGORIES = sub element : com.lhs.ccb.common.soiimpl.NamedVa
     * lueContainerList
     *  -[0]CUSTCAT_CODE         =  : java.lang.Long
     *  -[0]CUSTCAT_CODE_PUB     =  : java.lang.String
     * *-[0]CUSTCAT_DES          =  : java.lang.String
     * }
     * }</pre>
     *
     * @return a {@link java.util.Map} object.
     */
    Map<Long, String> custCatCodeReadAsMap();

    /**
     * Retrieve a list of languages defined in DB.
     * <p>
     * <pre>{@code
     * LANGUAGES.READ {
     * }
     * => {
     *   LIST_OF_LANGUAGES    = sub element : com.lhs.ccb.common.soiimpl.NamedValueCont
     * ainerList
     *  -[0]LNG_CODE             =  : java.lang.Long
     *  -[0]LNG_CODE_PUB         =  : java.lang.String
     *  -[0]LNG_DES              =  : java.lang.String
     *  -[0]LNG_SHDES            =  : java.lang.String
     *   LNG_CODE_DEF         =  : java.lang.Long
     * }
     * }</pre>
     *
     * @return a {@link java.util.List} object.
     */
    List<BSCSModel> languagesRead();

    /**
     * Retrieve a list of marital status codes and descriptions defined in DB.
     * If a default is specified, the id of the default will be also returned.
     * <pre>{@code
     * MARITAL_STATUS.READ {
     * }
     * => {
     *   LIST_OF_MARITAL_STATUS = sub element : com.lhs.ccb.common.soiimpl.NamedValueCo
     * ntainerList
     *  -[0]MAS_CODE             =  : java.lang.Long
     *  -[0]MAS_CODE_PUB         =  : java.lang.String
     * *-[0]MAS_DES              =  : java.lang.String
     *   MAS_CODE_DEF         =  : java.lang.Long
     *   MAS_CODE_DEF_PUB     =  : java.lang.String
     * }
     * }</pre>
     *
     * @return a {@link java.util.List} object.
     */
    List<BSCSModel> maritalStatusRead();

    /**
     * Retrieve a list of titles defined in DB.
     * <p>
     * <pre>{@code
     * TITLES.READ {
     * }
     * => {
     *   LIST_OF_TITLES       = sub element : com.lhs.ccb.common.soiimpl.NamedValueCont
     * ainerList
     * *-[0]TTL_DES              =  : java.lang.String
     *  -[0]TTL_GENDER           =  : java.lang.Character
     *  -[0]TTL_ID               =  : java.lang.Long
     *  -[0]TTL_ID_PUB           =  : java.lang.String
     *   TTL_DEF              =  : java.lang.Long
     *   TTL_DEF_PUB          =  : java.lang.String
     * }
     * }</pre>
     *
     * @return a {@link java.util.List} object.
     */
    List<BSCSModel> titlesRead();

    List<BSCSModel> documentTypesRead();

    /**
     * <pre>{@code
     *  CUSTOMER_GROUPS.READ {
     *  }
     *  => {
     *  LIST_OF_CUSTOMER_GROUPS = sub element : com.lhs.ccb.common.soiimpl.NamedValueContainerList
     *  -[0]LIST_OF_RELEASED_RATEPLANS = sub element : com.lhs.ccb.common.soiimpl.NamedValueContainerList
     *  -[0][0]RPCODE               =  : java.lang.Long
     *  -[0][0]RPCODE_PUB           =  : java.lang.String
     * *-[0]PRG_CODE             =  : java.lang.String
     * *-[0]PRG_DES              =  : java.lang.String
     *  PRG_CODE_DEF         =  : java.lang.String
     *  }
     *  }</pre>
     */  
    
    List<BSCSModel> customerGroupsRead();

    /**
     *
     * <code>
     * PAYMENT_ARRANGEMENTS.READ {
     *   CS_ID                =  : java.lang.Long
     *   CS_ID_PUB            =  : java.lang.String
     * * FLAG_CURRENT         =  : java.lang.Boolean
     * }
     * => {
     *   LIST_OF_PAYMENT_ARRANGEMENTS = sub element : com.lhs.ccb.common.soiimpl.NamedV
     * alueContainerList
     *   -[0]BSCSPaymentArrangement
     *  }
     *  }</pre>
     *
     * @param csId    a customer internal Id
     * @param csIdPub a {@link java.lang.String} customer public key.
     * @param flagCurrent a {@link java.lang.Boolean} flag indicating to return only current arrangements.
     * @return a {@link java.util.List} of payment arrangement.
     */
    List<BSCSPaymentArrangement> paymentArrangementsRead(Long csId, String csIdPub, Boolean flagCurrent);

    /**
     * Command to create and modify a payment arrangement.
     * <p>
     * Be aware of the handling of the CSP_ID (PAYMENT_ALL.PAYMENT_ID):
     * <ul>
     * <li>Create a new payment arrangement if CSP_ID value is null.
     * <li>Modify the existing payment arrangement if CSP_ID value is specified.
     * </ul>
     * <p>
     * Only the actually used payment arrangement will be validated by
     * commiting. <code>
     * PAYMENT_ARRANGEMENT.WRITE {
     *    BSCSPaymentArrangement
     * } => {
     *    CSP_SEQNO            =  : java.lang.Long
     * }
     *
     * @param pa
     *            the paymentAssignment
     * @return same pa with identifier
     */
    BSCSPaymentArrangement paymentArrangementWrite(BSCSPaymentArrangement pa);
    
    BSCSPaymentArrangementAssignment paymentArrangementAssignementWrite(BSCSPaymentArrangementAssignment pa);

    /**
     * Read all addresses of a particular customer.
     * <p>
     * <pre>{@code
     * ADDRESSES.READ {
     *   CS_ID                =  : java.lang.Long
     *   CS_ID_PUB            =  : java.lang.String
     * }
     * => {
     *   CS_ID                =  : java.lang.Long
     *   CS_ID_PUB            =  : java.lang.String
     *   LIST_OF_ALL_ADDRESSES = sub element
     * }
     * }</pre>
     * <p>
     *
     * @param csid
     *            internal customer identifier
     * @param csidpub
     *            customer public key
     * @return a {@link com.orange.bscs.model.businesspartner.BSCSAddresses} BSCSAddresse containing one or more BSCSAddress.
     */
    BSCSAddresses addressesRead(Long csid, String csidpub);

    /**
     * Read the address of a particular customer, which are represents the given
     * address role.
     * <p>
     * If no address role is given, the address for the default address role is
     * returned. . 
     * <pre>{@code
     * ADDRESS.READ {
     *   CS_ID                =  : java.lang.Long
     *   CS_ID_PUB            =  : java.lang.String
     *   ADR_TYPE             =  : java.lang.Character
     * }
     * => {
     *   BSCSAddress
     * }
     * }</pre>
     *
     * @param csid a {@link java.lang.Long} customer internal Id.
     * @param csidpub a {@link java.lang.String} customer public key.
     * @param role a {@link com.orange.bscs.model.businesspartner.EnumAddressRole} Address role.
     * @return a {@link com.orange.bscs.model.businesspartner.BSCSAddress} single Address or null.
     */
    BSCSAddress addressRead(Long csid, String csidpub, EnumAddressRole role);

    /**
     * Writes the given address data to a customer's address (Party). .
     * <p>
     * Be aware of the handling of the ADR_SEQ (CCONTACT_ALL.CCSEQ): .
     * <ul>
     * <li>n = 0 : Create a new address.
     * <li>n >= 1 : Update address with given seq_no. .
     *
     * <p>
     * Note: The creation of an UserInstallationAddress is handled in a seperate
     * command. .
     * <p>
     * 
     * <pre>
     * {@code
     *  ADDRESS.WRITE {
     *  	BSCSAddress
     *  }=> {
     *  	ADR_SEQ = : java.lang.Long
     *  }
     *  }
     * </pre>
     *
     * @param addrWrite
     *            a {@link com.orange.bscs.model.businesspartner.BSCSAddress}
     *            BSCSAddress to save.
     * @return when adrSeq=0 (creation of new Address) return the newly created
     *         sequence.
     */
    long addressWrite(BSCSAddress addrWrite);
    
    void addressWriteNoReturn(BSCSAddress addrWrite);

    /**
     * Converts a customer which is assigned to a large account customer into a
     * single subscriber.
     * <p>
     * <pre>{@code
     * CUSTOMER_FLAT.WRITE {
     *   CS_ID                =  : java.lang.Long
     *   CS_ID_PUB            =  : java.lang.String
     * }
     * => {
     * }
     * }</pre>
     *
     * @param csId  a customer internal id
     * @param csIdPub a {@link java.lang.String} customer public key.
     */
    void customerFlatWrite(Long csId, String csIdPub);

    /**
     * Defines/modifies the customer position within a customer structure. This
     * command is not necessary for a new single subscriber because the values
     * for CS_ID_HIGH, CS_LEVEL_CODE and CS_PAYMNT_RESP are preset to match
     * those of a single subscriber. If the current customer has the same level
     * as the target customer(CS_ID_HIGH) then all sublevels of the current
     * customer are moved under the target customer. In the other case current
     * customer and all its sublevels are moved under the target
     * customer(CS_ID_HIGH). See also LA_MEMBER.NEW: for creating Large Account.
     *
     *
     * move customer to an other LA. or change customer responsibility
     *
     * Change and validation take effect after COMMIT.
     *
     * <pre>{@code
     * CUSTOMER_HIERARCHY.WRITE {
     *   CS_CONTRESP          =  : java.lang.Boolean
     *   CS_ID                =  : java.lang.Long
     *   CS_ID_HIGH           =  : java.lang.Long
     *   CS_ID_HIGH_PUB       =  : java.lang.String
     *   CS_ID_PUB            =  : java.lang.String
     *   CS_LEVEL_CODE        =  : java.lang.String
     *   PAYMENT_RESP         =  : java.lang.Boolean
     * }
     * => {
     * }
     * }</pre>
     *
     * @param csId
     *            customer internal id
     * @param csIdPub
     *            customer public key
     * @param csIdHigh
     *            parent customer internal id
     * @param csIdHighPub
     *            parent customer public key
     * @param isPaymentResponsible
     *            change responsibility of customer (of subscriber)
     * @param hierarchyLevel
     *            new level (10 root, 40 subscriber)
     */
    void customerHierarchyWrite(Long csId, String csIdPub, Long csIdHigh, String csIdHighPub, Boolean isPaymentResponsible,
            EnumCustomerLevelCode hierarchyLevel);

    /**
     *
     * Create a new customer. .
     * <p>
     * The input parameters are the same as CUSTOMER.WRITE except that
     * CUSTOMER.NEW does not have the CS_ID as input parameter, and all
     * parameters are optional. .
     * <p>
     * The new customer will be initialized with default values. .
     * <p>
     * These default values( as specified in the DefaultInitializer class) will
     * be overwritten with the parameters that are send by the client! .
     * <p>
     * In order to create a customer with the default values provided by the
     * server, the client should not send any parameters. .
     * <p>
     * The state of the new customer is set to interested .
     * <p>
     * In order to create a complete (BSCS) customer you also need ADDRESS.WRITE
     * and PAYMENT_ARRANGEMENT.WRITE.
     * <p>
     * the CUSTOMER.NEW command implies that all parameters are optional.
     * <p>
     *
     * <pre>{@code
     * CUSTOMER.NEW {
     *  BSCSCustomerWriteInput
     * } => {
     *  BSCSCustomer
     * }
     * }</pre>
     *
     * @param customer a {@link com.orange.bscs.model.businesspartner.BSCSCustomerWriteInput} customer to create.
     * @return a {@link com.orange.bscs.model.businesspartner.BSCSCustomer} with ID and IDPUB.
     */
    BSCSCustomer customerNew(BSCSCustomerWriteInput customer);

    
    /**
     * Read a particular customer/dealer through a given internal BSCS customer
     * ID. .
     *
     * Not all ouput paramter are returned for the dealer.
     *
     * <pre>{@code
     * CUSTOMER.READ {
     *   CS_ID                =  : java.lang.Long
     *   CS_ID_PUB            =  : java.lang.String
     *   SUM_UNBILLED_AMOUNT  =  : java.lang.Boolean
     *   SYNC_WITH_DB         =  : java.lang.Boolean
     * }
     * => {
     *   BSCSCustomer
     * }
     *
     *      }</pre>
     *
     *
     * SUM_UNBILLED_AMOUNT doesn't exist in IXR3.
     *
     * @param csid
     *            customer internal identifier Id (may be null if csIdPub is
     *            specify)
     * @param csidpub
     *            customer public key (may be null if csId is specificy)
     * @param syncWithDB
     *            true if a database read is forced. In case the customer was
     *            already loaded in the server cache, the customer data is
     *            refreshed.
     * @return a {@link com.orange.bscs.model.businesspartner.BSCSCustomer} object.
     */
    BSCSCustomer customerRead(Long csid, String csidpub, boolean syncWithDB);

    /**
     * <p>Search customers.</p>
     *
     * @param customerSearchParams a {@link com.orange.bscs.api.model.BSCSModel} criterias.
     * @return a Model containing a list of customers in field "SEARCH_RESULT" .
     */
    BSCSModel customerSearch(BSCSCustomersSearchRequest customerSearchParams);
    

    /**
     *
     * Modify the data of a customer or a business partner. .
     *
     * The input parameters are the same as CUSTOMER.NEW. .
     *
     * This command can also be used to modify the status of a existing
     * customer/business partner.
     *
     * <pre>{@code
     * CUSTOMER.WRITE {
     *    BSCSCustomerWriteInput
     * } => {
     * }
     * }</pre>
     *
     * @param customer a {@link com.orange.bscs.model.businesspartner.BSCSCustomerWriteInput} entity.
     */
    void customerWrite(BSCSCustomerWriteInput customer);

    /**
     * Call LA_MEMBER.NEW on Existing customer to convert a flat customer to a
     * Member/Subscriber of an other LargeAccount
     *
     *
     * <p>
     * Create a LA member or LA(root node). .
     * <p>
     * The input parameters are the input parameter of CUSTOMER.WRITE and 3
     * additional input parameter. .
     * <ul>
     * <li>parent id
     * <li>customer level(default: root level)
     * <li>payment responsibility(default : false) .
     * </ul>
     * CS_ID is not input parameter, and all parameters are optional. .
     * <p>
     * The state of the new memeber is set to interested. .
     * <p>
     * The new large account (member) will be initialized with default values. .
     * <p>
     * These default values( as specified in the DefaultInitializer class) will
     * be overwritten with the parameters that are send by the client! .
     * <p>
     * In order to create a customer with the default values provided by the
     * server, the client should not send any parameters. .
     * <p>
     * The large account(root node) has to be payment responsible. .
     * <p>
     * For the large account member the parent id should be given. .
     * <p>
     * In order to create a complete (BSCS)customer you also need ADDRESS.WRITE
     * and PAYMENT_ARRANGEMENT.WRITE. .
     * <p>
     * LA_MEMBER.NEW command implies that all parameters are optional
     *
     * <pre>{@code
     * LA_MEMBER.NEW {
     *     BSCSCustomerWriteInput
     * } => {
     *     BSCSCustomer
     * }
     * }</pre>
     *
     * @param existingFlatCsIdPub
     *            idPub of a flat customer
     * @param parentLAIdPub
     *            idPub of a large account
     * @param csLevelCode
     *            level of the member
     * @param isPaymentResponsible
     *            if true, member need a PAYMENT_ARRANGEMENT
     * @return  new
     */
    BSCSCustomer laMemberNew(String existingFlatCsIdPub, String parentLAIdPub, EnumCustomerLevelCode csLevelCode, Boolean isPaymentResponsible);

    BSCSCustomer laMemberNew(Long existingFlatCsIdPub, Long parentLAIdPub, EnumCustomerLevelCode csLevelCode, Boolean isPaymentResponsible);
    
	List<BSCSCustomer> searchByCriterias(BSCSCustomersSearchRequest customerSearchParams);
	
	BSCSCustomer laMemberNewOne(BSCSCustomer customer);

}
