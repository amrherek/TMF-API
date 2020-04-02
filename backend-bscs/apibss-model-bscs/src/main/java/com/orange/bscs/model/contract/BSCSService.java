package com.orange.bscs.model.contract;

import java.util.List;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

/**
 * Service definition in referential
 *
 * @author IT&Labs
 *
 *
 *         Command SERVICES.READ
 *
 *         Inputs : EXT_PRODUCT_ID = : java.lang.Long EXT_PRODUCT_ID_PUB = :
 *         java.lang.String EXT_SERVICE_ID = : java.lang.Long EXT_SERVICE_ID_PUB
 *         = : java.lang.String RPCODE = : java.lang.Long RPCODE_PUB = :
 *         java.lang.String RP_VSCODE = : java.lang.Long SNCODE = :
 *         java.lang.Long SNCODE_PUB = : java.lang.String SPCODE = :
 *         java.lang.Long SPCODE_PUB = : java.lang.String
 *
 *         Output : EXT_PRODUCT_ID = : java.lang.Long EXT_PRODUCT_ID_PUB = :
 *         java.lang.String RPCODE = : java.lang.Long RPCODE_PUB = :
 *         java.lang.String RP_VSCODE = : java.lang.Long SPCODE = :
 *         java.lang.Long SPCODE_PUB = : java.lang.String NUM_SV = sub element
 *         [0]ACC_GLACCOUNT = : java.lang.String [0]ACC_GLACCOUNT_DISC = :
 *         java.lang.String [0]ACC_GLACCOUNT_MINCOM = : java.lang.String
 *         [0]ACC_JOBCOST = : java.lang.String [0]ACC_JOBCOST_DISC = :
 *         java.lang.String [0]ACC_JOBCOST_MINCOM = : java.lang.String [0]ADVIND
 *         = : java.lang.Character [0]AMTIND = : java.lang.Character
 *         [0]BALANCE_TYPE = : java.lang.String [0]BOP_MODE_ID = :
 *         java.lang.Long [0]BOP_MODE_ID_PUB = : java.lang.String [0]BSG_ID = :
 *         java.lang.Long [0]CHARGES = sub element [0][0]CHARGE_AMOUNT = :
 *         SVLMoneyImpl [0][0]CHARGE_TYPE_ID = : java.lang.Long
 *         [0]CONTRACT_BALANCE = : java.lang.Boolean [0]CP_EXT_KEY = :
 *         java.lang.String [0]CP_NAME = : java.lang.String [0]CREDITABLE = :
 *         java.lang.Boolean [0]DEF_PAYMENT_COND_USG_IND = : java.lang.Integer
 *         [0]DEF_TIME_PACKAGE_USG = : java.lang.Long [0]DIRNUM_NPCODE = :
 *         java.lang.Long [0]DIRNUM_NPCODE_PUB = : java.lang.String [0]ECHIND =
 *         : java.lang.Character [0]FREQUENCY_IND = : java.lang.Character
 *         [0]GPRS = : java.lang.Boolean [0]INTERVAL = : java.lang.Integer
 *         [0]INTERVAL_TYPE = : java.lang.Character [0]MONEY_WALLET = :
 *         java.lang.Boolean [0]NUM_ACTION = sub element [0][0]ACTION_ID = :
 *         java.lang.Long [0]PAYMENT_COND_CHNG_USG_IND = : java.lang.Boolean
 *         [0]PDE_IMPLICIT_IND = : java.lang.Boolean [0]PORT_NPCODE = :
 *         java.lang.Long [0]PORT_NPCODE_PUB = : java.lang.String
 *         [0]PREPAID_SUPPORTED_IND = : java.lang.Boolean [0]PROIND = :
 *         java.lang.Character [0]RESOURCES = sub element [0][0]MANDATORY = :
 *         java.lang.Boolean [0][0]RESOURCE_TYPE = : java.lang.Integer
 *         [0]SC_SCCODE = : java.lang.Long [0]SC_SNIND = : java.lang.Character
 *         [0]SELF_MONITORING = : java.lang.Boolean [0]SERVICE_PARAMERTER_IND =
 *         : java.lang.Boolean [0]SIMPLE_DISCOUNT = : java.lang.Boolean
 *         [0]SNCODE = : java.lang.Long [0]SNCODE_PUB = : java.lang.String
 *         [0]SRVIND = : java.lang.Character [0]SRV_SUBTYPE = :
 *         java.lang.Character [0]SRV_TYPE = : java.lang.Character [0]SUIP = :
 *         java.lang.Boolean [0]SUSIND = : java.lang.Boolean [0]SV_ALCOTYPE = :
 *         java.lang.Boolean [0]SV_CSIND = : java.lang.Boolean [0]SV_DEPOSIT = :
 *         com.lhs.ccb.common.soiimpl.SVLMoneyImpl [0]SV_DES = :
 *         java.lang.String [0]SV_MCOTYPE = : java.lang.Boolean
 *         [0]SV_NET_PROV_REQ = : java.lang.Boolean [0]SV_RCOTYPE = :
 *         java.lang.Boolean [0]SV_SHDES = : java.lang.String [0]SV_TYPE = :
 *         java.lang.Character [0]UMCODE = : java.lang.Long [0]UMCODE_PUB = :
 *         java.lang.String [0]VAS_MAPPING = : java.lang.Boolean [0]VPN = :
 *         java.lang.Boolean [0]VPN_ADMINISTRATOR_IND = : java.lang.Boolean
 *         [0]VPN_EXTERNAL_ACCESS_IND = : java.lang.Boolean
 *         [0]VPN_INTERNAL_ACCESS_IND = : java.lang.Boolean
 *         [0]VPN_OWNER_CONTRACT_IND = : java.lang.Boolean
 *         [0]VPN_USER_CONTRACT_IND = : java.lang.Boolean
 * @version $Id: $
 */
public class BSCSService extends BSCSModel {

    /** Constant <code>P_EXT_PRODUCT_ID="EXT_PRODUCT_ID"</code> */
    public static final String P_EXT_PRODUCT_ID = "EXT_PRODUCT_ID";
    /** Constant <code>P_EXT_PRODUCT_ID_PUB="EXT_PRODUCT_ID_PUB"</code> */
    public static final String P_EXT_PRODUCT_ID_PUB = "EXT_PRODUCT_ID_PUB";
    private static final String P_SERVICE_PARAMERTER_IND = "SERVICE_PARAMERTER_IND";
    private static final String P_EXT_SERVICE_ID = "EXT_SERVICE_ID";
    private static final String P_EXT_SERVICE_ID_PUB = "EXT_SERVICE_ID_PUB";

    private static final String P_SV_CSIND = "SV_CSIND";

    /**
     * <p>getExternalProductId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getExternalProductId() {
        return getLongValue(P_EXT_PRODUCT_ID);
    }

    /**
     * <p>setExternalProductId.</p>
     *
     * @param prodId a {@link java.lang.Long} object.
     */
    public void setExternalProductId(final Long prodId) {
        setLongValue(P_EXT_PRODUCT_ID, prodId);
    }

    /**
     * <p>getExternalProductIdPub.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getExternalProductIdPub() {
        return getStringValue(P_EXT_PRODUCT_ID_PUB);
    }

    /**
     * <p>setExternalProductIdPub.</p>
     *
     * @param prodId a {@link java.lang.String} object.
     */
    public void setExternalProductIdPub(final String prodId) {
        setStringValue(P_EXT_PRODUCT_ID_PUB, prodId);
    }

    /**
     * <p>getExternalServiceId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getExternalServiceId() {
        return getLongValue(P_EXT_SERVICE_ID);
    }

    /**
     * <p>setExternalServiceId.</p>
     *
     * @param prodId a {@link java.lang.Long} object.
     */
    public void setExternalServiceId(final Long prodId) {
        setLongValue(P_EXT_SERVICE_ID, prodId);
    }

    /**
     * <p>getExternalServiceIdPub.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getExternalServiceIdPub() {
        return getStringValue(P_EXT_SERVICE_ID_PUB);
    }

    /**
     * <p>setExternalServiceIdPub.</p>
     *
     * @param prodId a {@link java.lang.String} object.
     */
    public void setExternalServiceIdPub(final String prodId) {
        setStringValue(P_EXT_SERVICE_ID_PUB, prodId);
    }

    /**
     * <p>getServiceCode.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getServiceCode() {
        return getLongValue(Constants.P_SNCODE);
    }

    /**
     * <p>setServiceCode.</p>
     *
     * @param spcode a {@link java.lang.Long} object.
     */
    public void setServiceCode(final Long spcode) {
        setLongValue(Constants.P_SNCODE, spcode);
        // For BSCS V8
        setLongValue(Constants.P_SVCODE, spcode);
    }

    /**
     * <p>getServicePublicCode.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getServicePublicCode() {
        return getStringValue(Constants.P_SNCODE_PUB);
    }

    /**
     * <p>setServicePublicCode.</p>
     *
     * @param spcodepub a {@link java.lang.String} object.
     */
    public void setServicePublicCode(final String sncodepub) {
        setStringValue(Constants.P_SNCODE_PUB, sncodepub);
    }

    /**
     * <p>
     * get.{@value Constants#P_SPCODE_PUB}
     * </p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getServicePackagePublicCode() {
        return getStringValue(Constants.P_SPCODE_PUB);
    }

    /**
     * <p>
     * set {@value Constants#P_SPCODE_PUB}
     * </p>
     *
     * @param spcodepub
     *            a {@link java.lang.String} object.
     */
    public void setServicePackagePublicCode(final String spcodepub) {
        setStringValue(Constants.P_SPCODE_PUB, spcodepub);
    }

    /**
     * <p>
     * get.{@value Constants#P_SPCODE}
     * </p>
     *
     * @return a {@link java.lang.String} object.
     */
    public Long getServicePackageCode() {
        return getLongValue(Constants.P_SPCODE);
    }

    /**
     * <p>
     * set {@value Constants#P_SPCODE}
     * </p>
     *
     * @param spcode
     *            a {@link java.lang.String} object.
     */
    public void setServicePackageCode(final Long spcode) {
        setLongValue(Constants.P_SPCODE, spcode);
    }

    /**
     * <p>
     * get.{@value Constants#P_RPCODE}
     * </p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getRatePlanCode() {
        return getLongValue(Constants.P_RPCODE);
    }

    /**
     * <p>
     * set {@value Constants#P_RPCODE}
     * </p>
     *
     * @param rpcode
     *            a {@link java.lang.Long} object.
     */
    public void setRatePlanCode(final Long rpcode) {
        setLongValue(Constants.P_RPCODE, rpcode);
    }

    /**
     * <p>
     * get.{@value Constants#P_RPCODE_PUB}
     * </p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getRatePlanPublicCode() {
        return getStringValue(Constants.P_RPCODE_PUB);
    }

    /**
     * <p>
     * set {@value Constants#P_RPCODE_PUB}
     * </p>
     *
     * @param spcode
     *            a {@link java.lang.String} object.
     */
    public void setRatePlanPublicCode(final String rpcode) {
        setStringValue(Constants.P_RPCODE_PUB, rpcode);
    }

    /**
     * <p>
     * get.{@value Constants#P_RP_VSCODE}
     * </p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getRatePlanVersionCode() {
        return getLongValue(Constants.P_RP_VSCODE);
    }

    /**
     * <p>
     * set {@value Constants#P_RP_VSCODE}
     * </p>
     *
     * @param rpcode
     *            a {@link java.lang.Long} object.
     */
    public void setRatePlanVersionCode(final Long rpversioncode) {
        setLongValue(Constants.P_RP_VSCODE, rpversioncode);
    }

    /**
     * <p>
     * hasParameters.
     * </p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean hasParameters() {
        return getBooleanValue(P_SERVICE_PARAMERTER_IND);
    }

    /**
     * <p>setHasParameters.</p>
     *
     * @param flag a boolean.
     */
    public void setHasParameters(final boolean flag) {
        setBooleanValue(P_SERVICE_PARAMERTER_IND, flag);
    }

    /**
     * <p>getServiceType.</p>
     *
     * @return a {@link com.orange.bscs.model.contract.EnumServiceType} object.
     */
    public EnumServiceType getServiceType() {
        return EnumServiceType.parseChar(getCharacterValue(Constants.P_SV_TYPE));
    }

    /**
     * <p>setServiceType.</p>
     *
     * @param type a {@link com.orange.bscs.model.contract.EnumServiceType} object.
     */
    public void setServiceType(final EnumServiceType type) {
        setCharacterValue(Constants.P_SV_TYPE, EnumServiceType.toChar(type));
    }

    /**
     * <p>setServiceType.</p>
     *
     * @param type a {@link java.lang.Character} object.
     */
    public void setServiceType(final Character type) {
        setCharacterValue(Constants.P_SV_TYPE, type);
    }

    /**
     * <p>getServiceSubType.</p>
     *
     * @return a {@link com.orange.bscs.model.contract.EnumServiceSubType} object.
     */
    public EnumServiceSubType getServiceSubType() {
        return EnumServiceSubType.parseChar(getCharacterValue(Constants.P_SRV_SUBTYPE));
    }

    /**
     * <p>setServiceSubType.</p>
     *
     * @param type a {@link com.orange.bscs.model.contract.EnumServiceSubType} object.
     */
    public void setServiceSubType(final EnumServiceSubType type) {
        setCharacterValue(Constants.P_SRV_SUBTYPE, EnumServiceSubType.toChar(type));
    }

    /**
     * <p>setServiceSubType.</p>
     *
     * @param type a {@link java.lang.Character} object.
     */
    public void setServiceSubType(final Character type) {
        setCharacterValue(Constants.P_SRV_SUBTYPE, type);
    }

    /**
     * <p>getLabel.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getLabel() {
        return getStringValue(Constants.P_SV_DES);
    }

    /**
     * <p>setLabel.</p>
     *
     * @param snDescription a {@link java.lang.String} object.
     */
    public void setLabel(final String snDescription) {
        setStringValue(Constants.P_SV_DES, snDescription);
    }

    /**
     * {@value Constants#P_INTERVAL_TYPE}
     *
     * @return a {@link java.lang.String} object.
     */

    public Character getIntervalType() {
        return getCharacterValue(Constants.P_INTERVAL_TYPE);
    }

    /**
     * {@value Constants#P_INTERVAL_TYPE}
     *
     * @param intervalType
     *            a {@link java.lang.Character} object.
     */
    public void setIntervalType(final Character intervalType) {
        setCharacterValue(Constants.P_INTERVAL_TYPE, intervalType);
    }

    /**
     * {@value Constants#P_INTERVAL}
     *
     * @return a {@link java.lang.Integer} object.
     */

    public Integer getInterval() {
        return getIntegerValue(Constants.P_INTERVAL);
    }

    /**
     * {@value Constants#P_INTERVAL}
     *
     * @param interval
     *            a {@link java.lang.Integer} object.
     */
    public void setInterval(final Integer interval) {
        setIntegerValue(Constants.P_INTERVAL, interval);
    }

    /**
     * {@value Constants#P_CHARGES}
     *
     * @return a List of {@link BSCSCharges}.
     */

    public List<BSCSCharges> getChargesPackages() {
        return getListOfBSCSModelValue(Constants.P_CHARGES, BSCSCharges.class);
    }

    /**
     * {@value Constants#P_CHARGES}
     *
     * @param charges
     *            a List of {@link BSCSCharges}.
     */
    public void setChargesPackages(final List<BSCSCharges> charges) {
        setListOfBSCSModelValue(Constants.P_CHARGES, charges);
    }

    public Boolean isCoreService() {
        return getBooleanValue(P_SV_CSIND);
    }
}
