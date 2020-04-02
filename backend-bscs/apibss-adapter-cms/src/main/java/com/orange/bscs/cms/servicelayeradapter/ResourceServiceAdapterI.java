package com.orange.bscs.cms.servicelayeradapter;

import java.util.Date;
import java.util.List;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.model.resource.BSCSDirectoryNumber;
import com.orange.bscs.model.resource.BSCSDirectoryNumberSearch;
import com.orange.bscs.model.resource.BSCSPort;
import com.orange.bscs.model.resource.BSCSPortSearch;
import com.orange.bscs.model.resource.BSCSStorageMedium;
import com.orange.bscs.model.resource.BSCSStorageMediumSearch;

/**
 * <pre>
 * {@code
 * CUG_INTERLOCKS.SEARCH
 * CUG_AGREEMENT.READ
 * CUG_AGREEMENT.WRITE
 * CUG_AGREEMENTS.SEARCH
 * CUG_INTERNAL_MEMBERS.SEARCH
 * CUG_EXTERNAL_MEMBERS.READ
 * CUG_EXTERNAL_MEMBER.WRITE
 * DIRECTORY_NUMBER_TYPES.READ
 * DN_CONTRACT_HISTORY.READ
 * DN_HISTORY.READ
 * NUMBERING_PLANS.SEARCH
 * PHONE_NUMBER.EXPORT
 * PHONE_NUMBER.IMPORT
 * PHONE_NUMBER.REIMPORT
 * POOL_ISSUER.READ
 * PORT_CONTRACT_HISTORY.READ
 * PORT.CREATE
 * PORT_HISTORY.READ
 * PORTS.SEARCH
 * SM_CONTRACT_HISTORY.READ
 * SM_HISTORY.READ
 * STORAGE_MEDIUM_CLASS.READ
 * STORAGE_MEDIUM.CREATE
 * STORAGE_MEDIUM.SEARCH
 * VIRTUAL_PRIVATE_NETWORKS.READ
 * VANITY_NUMBER.CONVERT
 * }</pre>
 * 
 * <pre>
 * {@code
 * CUGInterlockCreate
 * DestinationsRead
 * EquipmentDelete
 * EquipmentsRead
 * EquipmentWrite
 * GenericDirectoryNumberCreate
 * GenericDirectoryNumberRead
 * GenericDirectoryNumberSearch
 * MicrocellMemberRead
 * MicrocellMembersWrite
 * MicrocellsRead
 * MicrocellsWrite
 * OriginsRead
 * PhoneNumberExportSearch
 * ResourceStateWrite
 * VirtualPrivateNetworkCreate
 * VirtualPrivateNetworkSearch
 * VirtualPrivateNetworksRead
 * VPNPrivateNumberRead * 
 * }</pre>
 * 
 * @author IT&Labs
 *
 */
public interface ResourceServiceAdapterI {

    /**
     * <p>
     * portHistoryRead.
     * </p>
     * 
     * @param coId
     *            a contract internal id.
     * @param coIdPub
     *            a contract public key.
     * @return a {@link java.util.List} of port history.
     */
    List<BSCSModel> portHistoryRead(Long coId, String coIdPub);

    /**
     *
     * This command is used to retrieve the directory number information. Used
     * in case of following Numbering Plan classes:
     * <ul>
     * <li>Telephone Number
     * <li>Directory number block
     * <li>PDP addresses
     * <li>APNs
     * <li>Instance Identifiers
     * <li>VPN owner
     * <li>VPN user
     * <p>
     * <code>
     * GENERIC_DIRECTORY_NUMBER.READ {
     * * DN_ID                =  : java.lang.Long
     * }
     * => {
     *   BSCSDirectoryNumber
     * }
     * </code>
     * <p>
     *
     * @see BSCSDirectoryNumber
     *
     *      Lots more options in BSCS IX R3
     * @param id a {@link java.lang.Long} object.
     * @return a {@link com.orange.bscs.model.resource.BSCSDirectoryNumber} object.
     */
    BSCSDirectoryNumber genericDirectoryNumberRead(Long id);

    /**
     * Send from the client to request a list of available directory numbers.
     * <p>
     * Multiple directory number types can be read with the optional performing
     * of temporary directory number reservation.
     * <p>
     * If the user is restricted to a dealer or service provider then the
     * resulting directory numbers are restricted to those belonging to the
     * dealer or service provider.
     * <p>
     * This command can be used instead of any of the following:
     * <ul>
     * <li>DIRECTORY_NUMBERS.SEARCH
     * <li>DIRECTORY_NUMBER_BLOCKS.SEARCH
     * <li>ACCESS_POINT_NAMES.SEARCH
     * <li>INSTANCE_IDENTIFIER.SEARCH
     * <li>PDP_ADDRESSES.SEARCH
     * <li>VPN_PRIVATE_NUMBERS.SEARCH
     * <li>VPN_NUMBERS.SEARCH
     * </ul>
     * </p>
     * <code>
     * GENERIC_DIRECTORY_NUMBER.SEARCH {
     *   CS_ID                =  : java.lang.Long
     *   CS_ID_PUB            =  : java.lang.String
     *   DIRNUM               =  : java.lang.String
     *   DNTypes              = sub element : com.lhs.ccb.common.soiimpl.NamedValueCont
     * ainerList
     *  -[0]DN_CODE              =  : java.lang.Long
     *   HLCODE               =  : java.lang.Long
     *   HLCODE_PUB           =  : java.lang.String
     *   HMCODE               =  : java.lang.Long
     *   HMCODE_PUB           =  : java.lang.String
     *   MAX_BLK_SIZE         =  : java.lang.Integer
     *   MIN_BLK_SIZE         =  : java.lang.Integer
     *   NPCODE               =  : java.lang.Long
     *   NPCODE_PUB           =  : java.lang.String
     *   PARAMETER_VALUES     = sub element : com.lhs.ccb.common.soiimpl.NamedValueCont
     * ainerList
     *  -[0]PRM_NO               =  : java.lang.Long
     *  -[0]PRM_VALUE_STRING     =  : java.lang.String
     *  -[0]RESOURCE_LIKE        =  : java.lang.Character
     *   PLCODE               =  : java.lang.Long
     *   PLCODE_PUB           =  : java.lang.String
     *   PREFIX               =  : java.lang.String
     *   PUBLIC_DN_NUM        =  : java.lang.String
     *   PUBLIC_NPCODE        =  : java.lang.Long
     *   PUBLIC_NPCODE_PUB    =  : java.lang.String
     *   REQUIRED_MAPPING_RULE =  : java.lang.Boolean
     *   RESERVATION          =  : java.lang.Boolean
     *   SEARCH_BLOCK         =  : java.lang.Boolean
     *   SEARCH_COUNT         =  : java.lang.Integer
     *   SNCODE               =  : java.lang.Long
     *   SNCODE_PUB           =  : java.lang.String
     *   STATUSES             = sub element : com.lhs.ccb.common.soiimpl.NamedValueCont
     * ainerList
     *  -[0]DN_STATUS            =  : java.lang.Character
     *   SUBM_ID              =  : java.lang.Long
     *   SUBM_ID_PUB          =  : java.lang.String
     *   VPN_ID               =  : java.lang.Long
     *   VPN_ID_PUB           =  : java.lang.String
     * }
     * => {
     *   DirectoryNumbers     = sub element : com.lhs.ccb.common.soiimpl.NamedValueCont
     * ainerList
     * </code>
     *
     * @param criteria a {@link com.orange.bscs.model.resource.BSCSDirectoryNumberSearch} object.
     * @return a {@link java.util.List} object.
     */
    List<BSCSDirectoryNumber> genericDirectoryNumberSearch(BSCSDirectoryNumberSearch criteria);

    /**
     * Search for A directory Number
     * <p>
     *
     * @param dirnum
     *            phone number
     * @param npCode a {@link java.lang.Long} object.
     * @param plCode a {@link java.lang.Long} object.
     * @param submid a {@link java.lang.Long} object.
     * @return a {@link com.orange.bscs.model.resource.BSCSDirectoryNumber} object.
     */
    BSCSDirectoryNumber genericDirectoryNumberSearch(String dirnum, Long npCode, Long plCode, Long submid);

    /**
     * Sent by the client to export a phone number for number portability.
     * <p>
     * <code>
     * PHONE_NUMBER.EXPORT {
     *   DEST_PLCODE          =  : java.lang.Long
     *   DEST_PLCODE_PUB      =  : java.lang.String
     *   DEST_PROVIDER        =  : java.lang.Long
     *   DEST_PROVIDER_PUB    =  : java.lang.String
     *   NPCODE               =  : java.lang.Long
     *   NPCODE_PUB           =  : java.lang.String
     * * PHONE_NUMBER         =  : java.lang.String
     * * PORTING_DATE         =  : com.lhs.ccb.common.soiimpl.SVLDateImpl
     * }
     * => {
     * }
     * </code>
     *
     * @param phoneNumber a {@link java.lang.String} object.
     * @param npCode a {@link java.lang.Long} object.
     * @param destPlCode a {@link java.lang.Long} object.
     * @param portingDate a {@link java.util.Date} object.
     */
    void phoneNumberExport(String phoneNumber, Long npCode, Long destPlCode, Date portingDate);

    /**
     * <p>phoneNumberExportSearch.</p>
     *
     * @param phoneNumber a {@link java.lang.String} object.
     * @return a {@link com.orange.bscs.model.resource.BSCSDirectoryNumber} object.
     */
    BSCSDirectoryNumber phoneNumberExportSearch(String phoneNumber);

    /**
     * Sent by the client to re-import a phone number for number portability.
     *
     * <code>
     * PHONE_NUMBER.REIMPORT {
     *   NPCODE               =  : java.lang.Long
     *   NPCODE_PUB           =  : java.lang.String
     * * PHONE_NUMBER         =  : java.lang.String
     * * PORTING_DATE         =  : com.lhs.ccb.common.soiimpl.SVLDateImpl
     * }
     * => {
     * }
     * </code>
     *
     * @param number a {@link java.lang.String} object.
     * @param npcode a {@link java.lang.Long} object.
     * @param portingDate a {@link java.util.Date} object.
     */
    void phoneNumberReImport(String number, Long npcode, Date portingDate);

    /**
     * Sent from the client to request the link status of serial number / port /
     * directory number(s) for a storage medium.
     * <p>
     * For an active storage medium the directory numbers linked to services are
     * included. If the user is restricted to a dealer or service provider then
     * the storage medium must be linked to the dealer or service provider
     * respectively.
     *
     * <pre>{@code
     * STORAGE_MEDIUM.SEARCH {
     *   DIRNUM               =  : java.lang.String
     *   HLCODE               =  : java.lang.Long
     *   HLCODE_PUB           =  : java.lang.String
     *   PLCODE               =  : java.lang.Long
     *   PLCODE_PUB           =  : java.lang.String
     *   RESERVATION          =  : java.lang.Boolean
     *   SMC_ID               =  : java.lang.Long
     *   SRCH_COUNT           =  : java.lang.Integer
     *   STMEDNO              =  : java.lang.String
     *   SUBM_ID              =  : java.lang.Long
     *   SUBM_ID_PUB          =  : java.lang.String
     * }
     * => {
     *   ALL STORAGEMEDIUMs   = sub element : com.lhs.ccb.common.soiimpl.NamedValueCont
     * ainerList
     *  -[0]DL_CODE              =  : java.lang.Long
     *  -[0]HLCODE               =  : java.lang.Long
     *  -[0]INITIAL_CREDIT       =  : com.lhs.ccb.common.soiimpl.SVLMoneyImpl
     *  -[0]LINKED_DN_ID         =  : java.lang.Long
     *  -[0]LINKED_PORT_NUM      =  : java.lang.String
     *  -[0]PORT_NPCODE          =  : java.lang.Long
     *  -[0]SMC_ID               =  : java.lang.Long
     *  -[0]SM_ID                =  : java.lang.Long
     *  -[0]SM_STATUS            =  : java.lang.Character
     * *-[0]STMEDNO              =  : java.lang.String
     *   SEARCH_IS_COMPLETE   =  : java.lang.Boolean
     * }
     * }</pre>
     *
     * @param criteria a {@link com.orange.bscs.model.resource.BSCSStorageMediumSearch} criterias.
     * @return a {@link java.util.List} of storage medium found.
     */
    List<BSCSStorageMedium> storageMediumSearch(BSCSStorageMediumSearch criteria);

    List<BSCSPort> portSearch(BSCSPortSearch criteria);

    
    /**
     * Modifies the state of resources in context of future dated requests.
     * <br/>
     * RESOURCE_STATE : New resource state. 
     * <ul><li>w - ordered</li>
     * <li>r - reserved</li></ul>
     * <br/>
     * RESOURCE_TYPE : Type of the resource. 
     * <ul>
     * <li>S - Storage Medium</li>
     * <li>P - Port</li>
     * <li>D - Directory Number</li>
     * <li>C - Closed User Group</li>
     * </ul>
     * <br/>
     *<pre>{@code
     * RESOURCE_STATE.WRITE {
     *   NPCODE               =  : java.lang.Long
     *   NPCODE_PUB           =  : java.lang.String
     *   PLCODE               =  : java.lang.Long
     *   PLCODE_PUB           =  : java.lang.String
     *   RESOURCE_ID          =  : java.lang.Long
     *   RESOURCE_NUMBER      =  : java.lang.String
     * * RESOURCE_STATE       =  : java.lang.Character
     * * RESOURCE_TYPE        =  : java.lang.Character
     *   SCCODE               =  : java.lang.Long
     *   SCCODE_PUB           =  : java.lang.String
     *   VPN_ID               =  : java.lang.Long
     *   VPN_ID_PUB           =  : java.lang.String
     * }
     * => {
     * }
     * }</pre>
     */
    void resourceStateWrite(BSCSModel input);
}
