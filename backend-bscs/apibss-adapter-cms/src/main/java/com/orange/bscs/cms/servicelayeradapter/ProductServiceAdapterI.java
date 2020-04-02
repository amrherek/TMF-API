package com.orange.bscs.cms.servicelayeradapter;

import java.util.List;

import com.orange.bscs.model.contract.BSCSContract;
import com.orange.bscs.model.contract.BSCSFreeUnitPackage;
import com.orange.bscs.model.contract.BSCSService;
import com.orange.bscs.model.product.BSCSRatePlan;
import com.orange.bscs.model.product.BSCSRatePlanChange;
import com.orange.bscs.model.product.BSCSServicePackage;

/**
 * <pre>
 * {@code
 * ALLOWED_SERVICES.READ
 * ANONYMIZATION_PACKAGES.READ
 * BASIC_SERVICE_GROUP.READ
 * BASIC_SERVICE_GROUPS.SEARCH
 * BEARER_CAPABILITIES.READ
 * DEPENDENCIES.READ
 * EVENT_DRIVEN_VAS.READ
 * RATEPLAN_AVAILABILITY_PERIODS.READ
 * SERVICES.READ
 * SERVICE_FUNCTIONALITIES.READ
 * SERVICE_PACKAGES_HISTORY.READ
 * SERVICE_PACKAGES.READ
 * THRESHOLD_DEFAULTS.READ
 * }</pre>
 *
 * <pre>
 * {@code
 * AllowedRateplansRead
 * AlternateRateplansRead
 * BOPModesRead
 * BOPPackageAssignmentCheck
 * BOPPackagesRead
 * BOPPurposesRead
 * ContentProductItemDetailRead
 * FreeUnitPackageElementsRead
 * FreeUnitPackagesRead
 * OCCRateplansRead
 * PrepaidTimepackagesRead
 * ProductChangeServicesRead
 * RateplanHistoryRead
 * RateplansRead
 * }</pre>
 *
 *
 * @author IT&Labs
 *
 */
public interface ProductServiceAdapterI {

    /**
     * If the rateplan code is specified, retrieves the list of service packages
     * for this rateplan. If not, retrieves all the service packages defined in
     * the database.
     * <p>
     * <pre>{@code
     * SERVICE_PACKAGES.READ {
     *   EXT_PRODUCT_ID       =  : java.lang.Long
     *   EXT_PRODUCT_ID_PUB   =  : java.lang.String
     *   RPCODE               =  : java.lang.Long
     *   RPCODE_PUB           =  : java.lang.String
     * }
     * => {
     *   NUM_SP               = sub element : com.lhs.ccb.common.soiimpl.NamedValueCont
     * ainerList
     * *-[0]PDE_SERVICE_PACKAGE  =  : java.lang.Boolean
     *  -[0]SPCODE               =  : java.lang.Long
     *  -[0]SPCODE_PUB           =  : java.lang.String
     * *-[0]SP_DES               =  : java.lang.String
     * *-[0]SP_SHDES             =  : java.lang.String
     *   RPCODE               =  : java.lang.Long
     *   RPCODE_PUB           =  : java.lang.String
     * }
     * }</pre>
     *
     * @return a {@link java.util.List} object.
     */
    List<BSCSServicePackage> servicePackagesRead();

    /**
     * execute SERVICE_PACKAGES.READ
     * 
     * @param input
     * @return
     */
    BSCSServicePackage servicePackagesRead(BSCSServicePackage input);

    /**
     * AllowedServicesRead
     *
     * Liste valids services for a given rateplan
     * @param rpcode rate plan internal id
     * @param rpcodepub rate plab public key
     * @param rpversion rate plan version (null=> current version)
     */
    BSCSRatePlan allowedServicesRead(Long rpcode, String rpcodepub, Long rpversion);

    /**
     * ALLOWED_SERVICES.READ
     * 
     * @param input
     * @return
     */
    BSCSRatePlan allowedServicesRead(BSCSRatePlan input);

    /**
     * ALLOWED_RATEPLANS.READ
     * 
     * @param input
     * @return
     */
    List<BSCSRatePlan> allowedRateplansRead(BSCSContract input);

    /**
     * Depending on the input parameters:
     * <ul>
     * <li>a) if the rate plan code and the service package code are entered,
     * the command will return the list of services in that rate plan's service
     * package. If no rate plan version is given then the current rate plan
     * version is assumed. If the rate plan has no current version, no services
     * will be returned.
     * <li>b) If not, no input parameters, all the services will be returned
     * independently of the rate plan and service package.
     * </ul>
     * <p>
     * 
     * <pre>
     * {@code
     * SERVICES.READ {
     *   EXT_PRODUCT_ID       =  : java.lang.Long
     *   EXT_PRODUCT_ID_PUB   =  : java.lang.String
     *   EXT_SERVICE_ID       =  : java.lang.Long
     *   EXT_SERVICE_ID_PUB   =  : java.lang.String
     *   RPCODE               =  : java.lang.Long
     *   RPCODE_PUB           =  : java.lang.String
     *   RP_VSCODE            =  : java.lang.Long
     *   SNCODE               =  : java.lang.Long
     *   SNCODE_PUB           =  : java.lang.String
     *   SPCODE               =  : java.lang.Long
     *   SPCODE_PUB           =  : java.lang.String
     * }
     * => {
     *   EXT_PRODUCT_ID       =  : java.lang.Long
     *   EXT_PRODUCT_ID_PUB   =  : java.lang.String
     *   NUM_SV               = sub element : com.lhs.ccb.common.soiimpl.NamedValueCont
     * ainerList
     *  BSCSService
     * }
     * }
     * </pre>
     *
     * @param snCode
     *            service internal Id
     * @param snCodePub
     *            a {@link java.lang.String} service public code.
     * @return a {@link com.orange.bscs.model.contract.BSCSService}.
     */
    BSCSService servicesRead(Long snCode, String snCodePub);

    /**
     *
     * @param spCode
     *            service package internal Id
     * @param spCodePub
     *            service package public code
     * @param snCode
     *            service internal Id
     * @param snCodePub
     *            service public code
     * @param rpCode
     *            rate plan internal Id
     * @param rpCodePub
     *            rate plan public code
     * @param rpVersionCode
     *            rate plan version code
     * @return a {@link com.orange.bscs.model.contract.BSCSService}.
     */
    BSCSService servicesRead(Long spCode, String spCodePub, Long snCode, String snCodePub, Long rpCode,
            String rpCodePub, Long rpVersionCode);

    /**
     * execute SERVICES.READ
     * 
     * @param input
     * @return
     */
    BSCSService servicesRead(final BSCSService input);

    /**
     * <pre>{@code
     * FREE_UNIT_PACKAGE_ELEMENTS.READ {
     *   FU_PACK_ID           =  : java.lang.Long
     *   FU_PACK_ID_PUB       =  : java.lang.String
     * }
     * => {
     *   FUP_LIST             = sub element : com.lhs.ccb.common.soiimpl.NamedValueCont
     * ainerList
     *  -[0]FU_PACK_ID           =  : java.lang.Long
     *  -[0]FU_PACK_ID_PUB       =  : java.lang.String
     * *-[0]FUP_VERSION          =  : java.lang.Long
     *  -[0]ELEM_LIST            = sub element : com.lhs.ccb.common.soiimpl.NamedValueContainerList
     * *-[0][0]ELEM_SEQNUM          =  : java.lang.Long
     *  -[0][0]ELEMDEF_LIST         = sub element : com.lhs.ccb.common.soiimpl.NamedValueContainerList
     * *-[0][0][0]ELEM_FREEUNITSVOLUME =  : java.lang.Float
     * *-[0][0][0]ELEM_VERSION         =  : java.lang.Long
     *  -[0][0][0]FC_CODE              =  : java.lang.String
     *  -[0][0][0]FU_FREE_UNITS_TYPE   =  : java.lang.String
     * *-[0][0][0]MAX_FREEUNITS        =  : java.lang.Float
     *  -[0][0][0]UOM_CODE             =  : java.lang.Long
     *  -[0][0][0]UOM_CODE_PUB         =  : java.lang.String
     *  -[0][0][0]UOM_SHDES            =  : java.lang.String
     * *-[0][0][0]VALID_FROM           =  : com.lhs.ccb.common.soiimpl.SVLDateImpl
     * }
     * }</pre>

     * @param fuPackageId
     * @return
     */
    BSCSFreeUnitPackage freeUnitPackageElementsRead(Long fuPackageId);

    /**
     * <pre>{@code
     * FREE_UNIT_PACKAGES.READ {
     *   FUP_APP_METHOD       =  : java.lang.Character
     *   FUP_ASS_LEVEL        =  : java.lang.Character
     *   FU_PACK_ID           =  : java.lang.Long
     *   FU_PACK_ID_PUB       =  : java.lang.String
     *   PRG_CODE             =  : java.lang.String
     * }
     * => {
     *   FUP_LIST             = sub element : com.lhs.ccb.common.soiimpl.NamedValueCont
     * ainerList
     * *-[0]FUP_APPL_METHOD      =  : java.lang.String
     *  -[0]FUP_APPL_PERIOD_LENGTH =  : java.lang.Integer
     *  -[0]FUP_APPL_PERIOD_TYPE =  : java.lang.Character
     *  -[0]FUP_APP_METHOD       =  : java.lang.Character
     *  -[0]FUP_ASS_LEVEL        =  : java.lang.Character
     * *-[0]FUP_DES              =  : java.lang.String
     * *-[0]FUP_VERSION          =  : java.lang.Long
     *  -[0]FU_PACK_ID           =  : java.lang.Long
     *  -[0]FU_PACK_ID_PUB       =  : java.lang.String
     * }
     * }</pre>
     *
     * @return
     */
    List<BSCSFreeUnitPackage> freeUnitPackagesRead();

    /**
     * execute FREE_UNIT_PACKAGES.READ
     * 
     * @param fuPackageId
     * @return
     */
    BSCSFreeUnitPackage freeUnitPackagesRead(Long fuPackageId);

    /**
     * Retrieves a list with the current rateplans
     * <p>
     * Only rate plans valid for the current business unit are contained in the
     * result list.
     * </p>
     * <pre>{@code
     * RATEPLANS.READ {
     *   EXT_PRODUCT_ID       =  : java.lang.Long
     *   EXT_PRODUCT_ID_PUB   =  : java.lang.String
     *   RPCODE               =  : java.lang.Long
     *   RPCODE_PUB           =  : java.lang.String
     * }
     * => {
     *   NUM_RP               = sub element : com.lhs.ccb.common.soiimpl.NamedValueCont
     * ainerList
     * *-[0]AV_FROM              =  : com.lhs.ccb.common.soiimpl.SVLDateImpl
     *  -[0]AV_TO                =  : com.lhs.ccb.common.soiimpl.SVLDateImpl
     *  -[0]CREDIT_LIMITS        = sub element : com.lhs.ccb.common.soiimpl.NamedValueC
     * ontainerList
     *  -[0][0]CRL_CHANGEABLE       =  : java.lang.Boolean
     *  -[0][0]CRL_DAILY_AMOUNT     =  : java.lang.Float
     *  -[0][0]CRL_DAYS             =  : java.lang.Integer
     *  -[0][0]CRL_END              =  : com.lhs.ccb.common.soiimpl.SVLDateImpl
     *  -[0][0]CRL_PRDIC_AMOUNT     =  : java.lang.Float
     *  -[0][0]CRL_START            =  : com.lhs.ccb.common.soiimpl.SVLDateImpl
     *  -[0]PDE_RATEPLAN         =  : java.lang.Boolean
     *  -[0]PRERATED_TAP_IND     =  : java.lang.Boolean
     *  -[0]RPCODE               =  : java.lang.Long
     *  -[0]RPCODE_PUB           =  : java.lang.String
     * *-[0]RP_DES               =  : java.lang.String
     *  -[0]RP_IS_EXTERNAL       =  : java.lang.Boolean
     * *-[0]RP_OCC               =  : java.lang.Boolean
     * *-[0]RP_SHDES             =  : java.lang.String
     *  -[0]RP_VSCODE            =  : java.lang.Long
     * }
     * }</pre>
     *
     * @return a {@link java.util.List} object.
     */
    List<BSCSRatePlan> ratePlansRead();

    /**
     * Simulate a Contract Change Rate Plan
     *
     * @param coid contract internal Id
     * @param coidpub contract public key
     * @param newrpcode id of the changed/new rate plan
     * @param newrpcodepub public key of the changed/new rate plan
     *
     * @return
     */
    BSCSRatePlanChange productChangeServicesRead(Long coid, String coidpub, Long newrpcode, String newrpcodepub);

    /**
     * retrieve the current rateplan with the rpCodePub or the rpCode
     * 
     * @param rpCode
     * @param rpCodePub
     * @return
     */
    List<BSCSRatePlan> ratePlanRead(Long rpCode, String rpCodePub);

    /**
     * retreive freeunits based on the assignment level
     * 
     * @param assignementLevel
     * @return
     */
    List<BSCSFreeUnitPackage> freeUnitPackagesRead(Character assignementLevel);

}
