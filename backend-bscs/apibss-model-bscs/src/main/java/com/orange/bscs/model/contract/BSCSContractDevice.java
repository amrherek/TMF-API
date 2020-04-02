package com.orange.bscs.model.contract;

import java.util.Date;
import java.util.List;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

/**
 * <pre>{@code
 * CONTRACT_DEVICES.READ {
 *   CO_ID                =  : java.lang.Long
 *   CO_ID_PUB            =  : java.lang.String
 * }
 * => {
 *   CO_ID                =  : java.lang.Long
 *   CO_ID_PUB            =  : java.lang.String
 *   CO_STMEDNO           =  : java.lang.String
 *   CO_STMED_DEALER_ID   =  : java.lang.Long
 *   CO_STMED_DEALER_ID_PUB =  : java.lang.String
 *   DESC_E               =  : java.lang.String
 *   DESC_G               =  : java.lang.String
 *   HLCODE               =  : java.lang.Long
 *   HLCODE_PUB           =  : java.lang.String
 *   ID_E                 =  : java.lang.Long
 *   ID_G                 =  : java.lang.Long
 *   PIN                  =  : java.lang.String
 *   PIN_2                =  : java.lang.String
 *   PLCODE               =  : java.lang.Long
 *   PLCODE_PUB           =  : java.lang.String
 *   PUK                  =  : java.lang.String
 *   PUK_2                =  : java.lang.String
 *   SMC_ID               =  : java.lang.Long
 *   SM_LAST_MOD_USER     =  : java.lang.String
 *   SM_MODDATE           =  : com.lhs.ccb.common.soiimpl.SVLDateTimeImpl
 *   directory numbers    = sub element : com.lhs.ccb.common.soiimpl.NamedValueCont
 * ainerList
 *  -[0]DIRNUM               =  : java.lang.String
 *  -[0]DN_NPCODE            =  : java.lang.Long
 *  -[0]DN_NPCODE_PUB        =  : java.lang.String
 *   ports                = sub element : com.lhs.ccb.common.soiimpl.NamedValueCont
 * ainerList
 *  -[0]PORT_NPCODE          =  : java.lang.Long
 *  -[0]PORT_NPCODE_PUB      =  : java.lang.String
 *  -[0]PORT_NUM             =  : java.lang.String
 * } 
 * }</pre>
 * 
 *
 * @author IT&Labs
 * @version $Id: $
 */
public class BSCSContractDevice extends BSCSModel {
 // CSA
    private static final String P_CO_STMEDNO = "CO_STMEDNO";
    private static final String P_CO_STMED_DEALER_ID_PUB = "CO_STMED_DEALER_ID_PUB";
    private static final String P_SMC_ID = "SMC_ID";
    private static final String P_CO_STMED_DEALER_ID = "CO_STMED_DEALER_ID";
    private static final String P_SM_LAST_MOD_USER = "SM_LAST_MOD_USER";
    private static final String P_SM_MODDATE = "SM_MODDATE";

    private static final String P_DN_NPCODE = "DN_NPCODE_PUB";
    private static final String P_DN_NPCODE_PUB = "DN_NPCODE_PUB";

    private static final String P_PORT_NPCODE = "PORT_NPCODE";
    private static final String P_PORT_NPCODE_PUB = "PORT_NPCODE_PUB";

    /**
     * <p>getContractIdPub.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getContractIdPub() {
        return getStringValue(Constants.P_CO_ID_PUB);
    }

    /**
     * <p>setContractIdPub.</p>
     *
     * @param coidpub a {@link java.lang.String} object.
     */
    public void setContractIdPub(String coidpub) {
        setStringValue(Constants.P_CO_ID_PUB, coidpub);
    }

    /**
     * Mode : Input
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getContractId() {
        return getLongValue(Constants.P_CO_ID);
    }

    /**
     * <p>setContractId.</p>
     *
     * @param coid a {@link java.lang.Long} object.
     */
    public void setContractId(Long coid) {
        setLongValue(Constants.P_CO_ID, coid);
    }

    /**
     * <p>getSMCId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getSMCId() {
        return getLongValue(P_SMC_ID);
    }

    /**
     * <p>setSMCId.</p>
     *
     * @param id a {@link java.lang.Long} object.
     */
    public void setSMCId(Long id) {
        setLongValue(P_SMC_ID, id);
    }

    /**
     * <p>getNetworkPublicCode.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getNetworkPublicCode() {
        return getStringValue(Constants.P_PLCODE_PUB);
    }

    /**
     * <p>setNetworkPublicCode.</p>
     *
     * @param copub a {@link java.lang.String} object.
     */
    public void setNetworkPublicCode(String copub) {
        setStringValue(Constants.P_PLCODE_PUB, copub);
    }

    /**
     * <p>getHLRCode.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getHLRCode() {
        return getLongValue(Constants.P_HLCODE);
    }

    /**
     * <p>setHLRCode.</p>
     *
     * @param hlcode a {@link java.lang.Long} object.
     */
    public void setHLRCode(Long hlcode) {
        setLongValue(Constants.P_HLCODE, hlcode);
    }

    /**
     * <p>getHLRCodePub.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getHLRCodePub() {
        return getStringValue(Constants.P_HLCODE_PUB);
    }

    public Long getNumberingPlan() {
        return getLongValue(Constants.P_NPCODE);
    }
    /** For CONTRACT_DEVICE.ADD (NPCODE)*/
    public void setNumberingPlan(Long npcode) {
        setLongValue(Constants.P_NPCODE, npcode);
    }
    
    public Integer getResourceType(){return getIntegerValue(Constants.P_RES_TYPE);}
    /** For CONTRACT_DEVICE.ADD (RES_TYPE)*/
    public void setResourceType(Integer resTypeDevice) {
        setIntegerValue(Constants.P_RES_TYPE, resTypeDevice);
    }

    
    /**
     * <p>setHLRCodePub.</p>
     *
     * @param hlcodepub a {@link java.lang.String} object.
     */
    public void setHLRCodePub(String hlcodepub) {
        setStringValue(Constants.P_HLCODE_PUB, hlcodepub);
    }

    /**
     * <p>getStorageMediumNumber.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getStorageMediumNumber() {
        return getStringValue(P_CO_STMEDNO);
    }

    /**
     * <p>setStorageMediumNumber.</p>
     *
     * @param stmedno a {@link java.lang.String} object.
     */
    public void setStorageMediumNumber(String stmedno) {
        setStringValue(P_CO_STMEDNO, stmedno);
    }

    /**
     * <p>getStorageMediumDealerId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getStorageMediumDealerId() {
        return getLongValue(P_CO_STMED_DEALER_ID);
    }

    /**
     * <p>setStorageMediumDealerId.</p>
     *
     * @param dealerId a {@link java.lang.Long} object.
     */
    public void setStorageMediumDealerId(Long dealerId) {
        setLongValue(P_CO_STMED_DEALER_ID, dealerId);
    }

    /**
     * <p>getStorageMediumDealerIdPub.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getStorageMediumDealerIdPub() {
        return getStringValue(P_CO_STMED_DEALER_ID_PUB);
    }

    /**
     * <p>setStorageMediumDealerIdPub.</p>
     *
     * @param dealerIdPub a {@link java.lang.String} object.
     */
    public void setStorageMediumDealerIdPub(String dealerIdPub) {
        setStringValue(P_CO_STMED_DEALER_ID_PUB, dealerIdPub);
    }

    /**
     * <p>getStorageMediumLastUserMod.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getStorageMediumLastUserMod() {
        return getStringValue(P_SM_LAST_MOD_USER);
    }

    /**
     * <p>setStorageMediumLastUserMod.</p>
     *
     * @param user a {@link java.lang.String} object.
     */
    public void setStorageMediumLastUserMod(String user) {
        setStringValue(P_SM_LAST_MOD_USER, user);
    }

    /**
     * <p>getStorageMediumLastDateMod.</p>
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getStorageMediumLastDateMod() {
        return getDateTimeValue(P_SM_MODDATE);
    }

    /**
     * <p>setStorageMediumLastDateMod.</p>
     *
     * @param dtmod a {@link java.util.Date} object.
     */
    public void setStorageMediumLastDateMod(Date dtmod) {
        setDateTimeValue(P_SM_MODDATE, dtmod);
    }

    /**
     * <p>getPin.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getPin() {
        return getStringValue(Constants.P_PIN);
    }

    /**
     * <p>setPin.</p>
     *
     * @param pin a {@link java.lang.String} object.
     */
    public void setPin(String pin) {
        setStringValue(Constants.P_PIN, pin);
    }

    /**
     * <p>getPin2.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getPin2() {
        return getStringValue(Constants.P_PIN2);
    }

    /**
     * <p>setPin2.</p>
     *
     * @param pin a {@link java.lang.String} object.
     */
    public void setPin2(String pin) {
        setStringValue(Constants.P_PIN2, pin);
    }

    /**
     * <p>getPuk.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getPuk() {
        return getStringValue(Constants.P_PUK);
    }

    /**
     * <p>setPuk.</p>
     *
     * @param puk a {@link java.lang.String} object.
     */
    public void setPuk(String puk) {
        setStringValue(Constants.P_PUK, puk);
    }

    /**
     * <p>getPuk2.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getPuk2() {
        return getStringValue(Constants.P_PUK2);
    }

    /**
     * <p>setPuk2.</p>
     *
     * @param puk a {@link java.lang.String} object.
     */
    public void setPuk2(String puk) {
        setStringValue(Constants.P_PUK2, puk);
    }

    /**
     * <p>getDirectoryNumbers.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<BSCSDeviceDirNum> getDirectoryNumbers() {
        return getListOfBSCSModelValue(Constants.P_DIRECTORY_NUMBERS, BSCSDeviceDirNum.class);
    }

    /**
     * <p>getPorts.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<BSCSDevicePort> getPorts() {
        return getListOfBSCSModelValue(Constants.P_PORTS, BSCSDevicePort.class);
    }

    public static class BSCSDeviceDirNum extends BSCSModel {

        public String getPhoneNumber() {
            return getStringValue(Constants.P_DIRNUM);
        }

        public void setPhoneNumber(String dirnum) {
            setStringValue(Constants.P_DIRNUM, dirnum);
        }

        public Long getNumberingPlanId() {
            return getLongValue(P_DN_NPCODE);
        }

        public void setNumberingPlanId(Long npcode) {
            setLongValue(P_DN_NPCODE, npcode);
        }

        public String getNumberingPlanIdPub() {
            return getStringValue(P_DN_NPCODE_PUB);
        }

        public void setNumberingPlanIdPub(String npcodepub) {
            setStringValue(P_DN_NPCODE_PUB, npcodepub);
        }
    }

    public static class BSCSDevicePort extends BSCSModel {
        public String getPortNumber() {
            return getStringValue(Constants.P_PORT_NUM);
        }

        public void setPortNumber(String portnum) {
            setStringValue(Constants.P_PORT_NUM, portnum);
        }

        public Long getNumberingPlanId() {
            return getLongValue(P_PORT_NPCODE);
        }

        public void setNumberingPlanId(Long npcode) {
            setLongValue(P_PORT_NPCODE, npcode);
        }

        public String getNumberingPlanIdPub() {
            return getStringValue(P_PORT_NPCODE_PUB);
        }

        public void setNumberingPlanIdPub(String npcodepub) {
            setStringValue(P_PORT_NPCODE_PUB, npcodepub);
        }

    }


}
