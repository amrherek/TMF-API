package com.orange.bscs.model.resource;

import java.util.Date;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

/**
 * <p>BSCSDirectoryNumber class.</p>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public class BSCSDirectoryNumber extends BSCSModel {

    private static final String DN_CODE = "DN_CODE";

    /**
     * <p>get DN_ID.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getId() {
        return getLongValue(Constants.P_DN_ID);
    }

    /**
     * <p>set DN_ID.</p>
     *
     * @param id a {@link java.lang.Long} object.
     */
    public BSCSDirectoryNumber setId(Long id) {
        setLongValue(Constants.P_DN_ID, id);
        return this;
    }

    /**
     * <p>get NumberingPlan Id (NPCODE).</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getNumberingPlanId() {
        return getLongValue(Constants.P_NPCODE);
    }

    /**
     * <p>setNumberingPlanId (NPCODE).</p>
     *
     * @param npcode a {@link java.lang.Long} object.
     */
    public BSCSDirectoryNumber setNumberingPlanId(Long npcode) {
        setLongValue(Constants.P_NPCODE, npcode);
        return this;
    }

    /**
     * <p>get HLR code.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getHLRCode() {
        return getLongValue(Constants.P_HLCODE);
    }

    /**
     * <p>set HLR Code.</p>
     *
     * @param hlcode a {@link java.lang.Long} object.
     */
    public BSCSDirectoryNumber setHLRCode(Long hlcode) {
        setLongValue(Constants.P_HLCODE, hlcode);
        return this;
    }

    /**
     * <p>get NetworkId (PLCODE).</p>
     *
     * Use by GENERIC_DIRECTORY_NUMBER.READ, PHONE_NUMBER_EXPORT.SEARCH
     * but not by GENERIC_DIRECTORY_NUMBER.SEARCH
     * 
     * @return a {@link java.lang.Long} object.
     */
    public Long getNetworkId() {
        return getLongValue(Constants.P_PLCODE);
    }

    /**
     * <p>set NetworkId (PLCODE).</p>
     *
     * @param plcode a {@link java.lang.Long} object.
     */
    public BSCSDirectoryNumber setNetworkId(Long plcode) {
        setLongValue(Constants.P_PLCODE, plcode);
        return this;
    }
    
    
    /**
     * <p>getPhoneNumber (DIRNUM).</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getPhoneNumber() {
        return getStringValue(Constants.P_DIRNUM);
    }

    /**
     * <p>set PhoneNumber (DIRNUM).</p>
     *
     * @param dirnum a {@link java.lang.String} object.
     */
    public BSCSDirectoryNumber setPhoneNumber(String dirnum) {
        setStringValue(Constants.P_DIRNUM, dirnum);
        return this;
    }

    /**
     * <p>getDirectoryCode.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getDirectoryCode() {
        return getLongValue(DN_CODE);
    }

    /**
     * <p>setDirectoryCode.</p>
     *
     * @param dncode a {@link java.lang.Long} object.
     */
    public BSCSDirectoryNumber setDirectoryCode(Long dncode) {
        setLongValue(DN_CODE, dncode);
        return this;
    }

    /**
     * <p>getStatusCode.</p>
     *
     * @return a {@link com.orange.bscs.model.resource.EnumDirectoryNumberStatus} object.
     */
    public EnumDirectoryNumberStatus getStatusCode() {
        return EnumDirectoryNumberStatus.parseChar(getStatusChar());
    }

    /**
     * <p>setStatusCode.</p>
     *
     * @param status a {@link com.orange.bscs.model.resource.EnumDirectoryNumberStatus} object.
     */
    public BSCSDirectoryNumber setStatusCode(EnumDirectoryNumberStatus status) {
        setStatusChar(status.getCode());
        return this;
    }

    /**
     * <p>getStatusChar.</p>
     *
     * @return a {@link java.lang.Character} object.
     */
    public Character getStatusChar() {
        return getCharacterValue(Constants.P_DN_STATUS);
    }

    /**
     * <p>setStatusChar.</p>
     *
     * @param status a {@link java.lang.Character} object.
     */
    public BSCSDirectoryNumber setStatusChar(Character status) {
        setCharacterValue(Constants.P_DN_STATUS, status);
        return this;
    }

    /**
     * <p>getLocalPrefix.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getLocalPrefix() {
        return getStringValue(Constants.P_LOCAL_PREFIX);
    }

    /**
     * <p>setLocalPrefix.</p>
     *
     * @param prefix a {@link java.lang.String} object.
     */
    public BSCSDirectoryNumber setLocalPrefix(String prefix) {
        setStringValue(Constants.P_LOCAL_PREFIX, prefix);
        return this;
    }

    /**
     * <p>getSuffix.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getSuffix() {
        return getStringValue(Constants.P_SUFFIX);
    }

    /**
     * <p>setSuffix.</p>
     *
     * @param suffix a {@link java.lang.String} object.
     */
    public BSCSDirectoryNumber setSuffix(String suffix) {
        setStringValue(Constants.P_SUFFIX, suffix);
        return this;
    }

    /**
     * <p>getPortId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getPortId() {
        return getLongValue(Constants.P_PORT_ID);
    }

    /**
     * <p>setPortId.</p>
     *
     * @param portId a {@link java.lang.Long} object.
     */
    public BSCSDirectoryNumber setPortId(Long portId) {
        setLongValue(Constants.P_PORT_ID, portId);
        return this;
    }

    /**
     * <p>isMainNum.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean isMainNum() {
        return getBooleanValue(Constants.P_MAIN_DIRNUM);
    }

    /**
     * A contract need 1 Main MSISDN before trying to activate it HLR assignment
     * method 2 violated. Exactly one directory number must belongs to a user
     * profile and be marked as main in contract
     *
     * @param bool
     *            Set to true when Adding a (first) DirectoryNumber in a
     *            service.
     */
    public BSCSDirectoryNumber setMainNum(boolean bool) {
        setBooleanValue(Constants.P_MAIN_DIRNUM, bool);
        return this;
    }

    /**
     * Phone_Number.Export/import/reimport - PORTING_DATE
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getPortingDate() {
        return getDateValue(Constants.P_PORTING_DATE);
    }

    /**
     * Phone_Number.Export/import/reimport - PORTING_DATE
     *
     * @param portingDate a {@link java.util.Date} object.
     */
    public BSCSDirectoryNumber setPortingDate(Date portingDate) {
        setDateValue(Constants.P_PORTING_DATE, portingDate);
        return this;
    }

}
