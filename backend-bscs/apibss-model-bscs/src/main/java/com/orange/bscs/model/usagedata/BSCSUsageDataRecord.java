package com.orange.bscs.model.usagedata;

import java.util.Date;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

/**
 * Response of  USAGE_DATA_RECORDS.READ
 * <pre>{@code 
 * USAGE_DATA_RECORDS.READ {
 *   ... (cf BSCSUsageDataRecordRead)
 * }
 * => {
 *   OUTPUT               = sub element : com.lhs.ccb.common.soiimpl.NamedValueContainerList
 *  -[0]ACTION_DESC          =  : java.lang.String
 *  -[0]ALT_RPCODE           =  : java.lang.Long
 *  -[0]ALT_RPCODE_PUB       =  : java.lang.String
 *  -[0]ALT_SPCODE           =  : java.lang.Long
 *  -[0]ALT_SPCODE_PUB       =  : java.lang.String
 *  -[0]ALT_USED_RPCODE      =  : java.lang.Long
 *  -[0]ALT_USED_RP_VERSION  =  : java.lang.Long
 *  -[0]ALT_USED_SPCODE      =  : java.lang.Long
 *  -[0]ALT_USED_SPCODE_PUB  =  : java.lang.String
 * *-[0]ANONYMCALLEDPARTY    =  : java.lang.String
 *  -[0]BILLED_IND           =  : java.lang.Boolean
 *  -[0]BOP_ALTERNATIVE_IND  =  : java.lang.Boolean
 *  -[0]BOP_PKG_ID           =  : java.lang.Long
 *  -[0]BOP_PKG_ID_PUB       =  : java.lang.String
 * *-[0]BOP_PKG_VERS         =  : java.lang.Long
 *  -[0]BOP_PURPOSE_ID       =  : java.lang.Long
 *  -[0]BOP_PURPOSE_ID_PUB   =  : java.lang.String
 * *-[0]BS_ID                =  : java.lang.Long
 *  -[0]BUNDLE_ID            =  : java.lang.Long
 *  -[0]BUNDLE_PURCHASE      =  : java.lang.Boolean
 *  -[0]BUNDLE_PURCHASE_ID   =  : java.lang.String
 *  -[0]BUNDLE_USAGE         =  : java.lang.Boolean
 *  -[0]BUSINESS_INFO_C_P_FIELD_REF =  : java.lang.Long
 *  -[0]BUSINESS_INFO_O_P_FIELD_REF =  : java.lang.Long
 * *-[0]CALLEDPARTY          =  : java.lang.String
 *  -[0]CALLINGPARTY         =  : java.lang.String
 * *-[0]CALL_ID              =  : java.lang.Long
 * *-[0]CAMEL_MSC_ADDRESS    =  : java.lang.String
 * *-[0]CAMEL_REF_NUM        =  : java.lang.String
 *  -[0]CASH_FLOW_DIRECTION  =  : java.lang.String
 *  -[0]CCH_DISCOUNTS_LIST   = sub element : com.lhs.ccb.common.soiimpl.NamedValueContainerList
 *  -[0][0]BP_ID                =  : java.lang.Long
 *  -[0][0]CCIP_CO_ID           =  : java.lang.Long
 *  -[0][0]CCIP_CO_ID_PUB       =  : java.lang.String
 * *-[0][0]CCIP_FREE_AM         =  : com.lhs.ccb.common.soiimpl.SVLMoneyImpl
 * *-[0][0]CCIP_FREE_CL         =  : java.lang.Long
 * *-[0][0]CCIP_FREE_EXT_TAX    =  : java.lang.Float
 * *-[0][0]CCIP_FREE_RATE_VOL   =  : java.lang.Long
 *  -[0][0]CCIP_PROFILE_ID      =  : java.lang.Long
 * *-[0][0]CCIP_RND_VOL         =  : java.lang.Long
 *  -[0][0]CCIP_SNCODE          =  : java.lang.Long
 *  -[0]CDR_GENERATOR        =  : java.lang.Character
 * *-[0]CDR_ID               =  : java.lang.Long
 * *-[0]CDR_SUB_ID           =  : java.lang.Long
 *  -[0]CELL_ADDR            =  : java.lang.String
 * *-[0]CE_ID                =  : java.lang.Long
 * *-[0]CHARGE_TYPE          =  : java.lang.Character
 *  -[0]CHARGING_CHARACTERISTICS =  : java.lang.Character
 * *-[0]CLIR_STATUS          =  : java.lang.Character
 *  -[0]CO_ID                =  : java.lang.Long
 *  -[0]CO_ID_PUB            =  : java.lang.String
 *  -[0]CP_ID_PUB            =  : java.lang.String
 *  -[0]CS_ID                =  : java.lang.Long
 *  -[0]CS_ID_PUB            =  : java.lang.String
 * *-[0]CT_ID                =  : java.lang.Long
 * *-[0]CT_VSN               =  : java.lang.Long
 *  -[0]CURRENCY_ID          =  : java.lang.Long
 *  -[0]CURRENCY_ID_PUB      =  : java.lang.String
 *  -[0]CUST_USER_PROFILE_ID =  : java.lang.Long
 *  -[0]DATA_VOLUME          =  : java.lang.Float
 *  -[0]DATA_VOLUME_UMCODE   =  : java.lang.Long
 *  -[0]DESTINATION          =  : java.lang.String
 *  -[0]DEVICE_NUM           =  : java.lang.String
 *  -[0]DOWNLINK_VOLUME      =  : java.lang.Float
 *  -[0]DOWNLINK_VOLUME_UMCODE =  : java.lang.Long
 * *-[0]DURATION             =  : java.lang.Float
 *  -[0]EFF_DISCOUNTED_AMOUNT =  : com.lhs.ccb.common.soiimpl.SVLMoneyImpl
 *  -[0]EFF_EXT_AM_TAXED     =  : java.lang.Integer
 *  -[0]EFF_EXT_TAX          =  : java.lang.Float
 *  -[0]EFF_EXT_TAX_AMOUNT   =  : com.lhs.ccb.common.soiimpl.SVLMoneyImpl
 *  -[0]EFF_EXT_TAX_TAXED    =  : java.lang.Integer
 *  -[0]EFF_FREE_AM          =  : java.lang.Float
 *  -[0]EFF_RAT_CL           =  : java.lang.Long
 *  -[0]EFF_RAT_V            =  : java.lang.Long
 *  -[0]EFF_ROUND_V          =  : java.lang.Long
 *  -[0]EQ_CLASS             =  : java.lang.Character
 *  -[0]EQ_NUMBER            =  : java.lang.String
 *  -[0]EVENT_VOLUME         =  : java.lang.Float
 *  -[0]EVENT_VOLUME_UMCODE  =  : java.lang.Long
 *  -[0]FOLLOW_UP_CALL_TYPE  =  : java.lang.Long
 *  -[0]FREE_UNITS_LIST      = sub element : com.lhs.ccb.common.soiimpl.NamedValueContainerList
 * *-[0][0]FUP_ACC_HIST         =  : java.lang.Long
 * *-[0][0]FUP_ACC_KEY          =  : java.lang.Long
 * *-[0][0]FUP_FREE_AM          =  : com.lhs.ccb.common.soiimpl.SVLMoneyImpl
 * *-[0][0]FUP_FREE_EXT_TAX     =  : java.lang.Float
 * *-[0][0]FUP_PAC_EL           =  : java.lang.Long
 * *-[0][0]FUP_PAC_ELV          =  : java.lang.Long
 * *-[0][0]FUP_RATE_CL          =  : java.lang.Long
 * *-[0][0]FUP_RATE_VOL         =  : java.lang.Long
 * *-[0][0]FUP_RND_VOL          =  : java.lang.Long
 *  -[0][0]FU_PACK_ID           =  : java.lang.Long
 *  -[0][0]FU_PACK_ID_PUB       =  : java.lang.String
 *  -[0]GPRS_NEG_DELAY       =  : java.lang.Long
 *  -[0]GPRS_NEG_MEAN_THROUGHPUT =  : java.lang.Long
 *  -[0]GPRS_NEG_PEAK_THROUGHPUT =  : java.lang.Long
 *  -[0]GPRS_NEG_PRECEDENCE  =  : java.lang.Long
 *  -[0]GPRS_NEG_RELIABILITY =  : java.lang.Long
 *  -[0]GPRS_PROFILE         =  : java.lang.Long
 *  -[0]GPRS_REQ_DELAY       =  : java.lang.Long
 *  -[0]GPRS_REQ_MEAN_THROUGHPUT =  : java.lang.Long
 *  -[0]GPRS_REQ_PEAK_THROUGHPUT =  : java.lang.Long
 *  -[0]GPRS_REQ_PRECEDENCE  =  : java.lang.Long
 *  -[0]GPRS_REQ_RELIABILITY =  : java.lang.Long
 * *-[0]HOME_PL_CODE         =  : java.lang.Long
 *  -[0]HOME_TERMINATED_IND  =  : java.lang.Boolean
 *  -[0]HSCSD_AIUR           =  : java.lang.Long
 *  -[0]HSCSD_CHANNELS_CODING_ACC =  : java.lang.Long
 *  -[0]HSCSD_CHANNELS_CODING_USED =  : java.lang.Long
 *  -[0]HSCSD_CHANNELS_MAX   =  : java.lang.Long
 *  -[0]HSCSD_CHANNELS_USED  =  : java.lang.Long
 *  -[0]HSCSD_FNUR           =  : java.lang.Long
 *  -[0]HSCSD_INIT_PARTY     =  : java.lang.Long
 * *-[0]INVOICENO            =  : java.lang.Long
 *  -[0]MESSAGE_VOLUME       =  : java.lang.Float
 *  -[0]MESSAGE_VOLUME_UMCODE =  : java.lang.Long
 * *-[0]NAV_POINT            =  : java.lang.Integer
 * *-[0]NET_ELEMENT_NETWORK_CODE =  : java.lang.Long
 * *-[0]ORIG_CDR_ID          =  : java.lang.Long
 * *-[0]ORIG_START           =  : com.lhs.ccb.common.soiimpl.SVLDateTimeImpl
 * *-[0]PL_ID                =  : java.lang.Long
 *  -[0]PREPAY_IND           =  : java.lang.Character
 * *-[0]PRIORITY_CODE        =  : java.lang.Character
 *  -[0]RAP_SEQ_NUM          =  : java.lang.Long
 * *-[0]RATED_FLAT_AMOUNT    =  : com.lhs.ccb.common.soiimpl.SVLMoneyImpl
 * *-[0]RATED_TAX_AMOUNT     =  : com.lhs.ccb.common.soiimpl.SVLMoneyImpl
 * *-[0]RATED_VOLUME         =  : java.lang.Float
 * *-[0]RATED_VOLUME_UOM     =  : java.lang.Long
 *  -[0]RATE_TYPE            =  : java.lang.String
 *  -[0]REMARK               =  : java.lang.String
 *  -[0]RESULT               =  : java.lang.String
 *  -[0]REVERSE_CHARGING     =  : java.lang.Boolean
 * *-[0]RFA                  =  : java.lang.Float
 * *-[0]RFA_CURR             =  : java.lang.Long
 *  -[0]ROAMING_IND          =  : java.lang.Boolean
 *  -[0]RPCODE               =  : java.lang.Long
 *  -[0]RPCODE_PUB           =  : java.lang.String
 *  -[0]RP_VERSION           =  : java.lang.Long
 * *-[0]SCU_ID               =  : java.lang.String
 *  -[0]SERVED_PDP_ADDRESS   =  : java.lang.String
 * *-[0]SERVICE_CALL_ID      =  : java.lang.Integer
 *  -[0]SNCODE               =  : java.lang.Long
 *  -[0]SNCODE_PUB           =  : java.lang.String
 *  -[0]SPCODE               =  : java.lang.Long
 *  -[0]SPCODE_PUB           =  : java.lang.String
 *  -[0]SWITCH_ADDR          =  : java.lang.String
 *  -[0]TAP_SEQ_NUM          =  : java.lang.Long
 * *-[0]TAXRATE              =  : java.lang.Float
 *  -[0]THIRD_PARTY_INFO     =  : java.lang.String
 * *-[0]TTNAME               =  : java.lang.String
 * *-[0]TYPE                 =  : java.lang.Long
 * *-[0]TZNAME               =  : java.lang.String
 * *-[0]UDR_BASEPART_ID      =  : java.lang.Long
 * *-[0]UDR_CHARGEPART_ID    =  : java.lang.Long
 * *-[0]UDR_FILE_ID          =  : java.lang.Long
 * *-[0]UDR_ID               =  : java.lang.Integer
 *  -[0]UMCODE               =  : java.lang.Long
 *  -[0]UMCODE_PUB           =  : java.lang.String
 *  -[0]UMTS_NEG_ALLC_RETN_PRIOR =  : java.lang.Long
 *  -[0]UMTS_NEG_DELIVERY_ORDER =  : java.lang.Long
 *  -[0]UMTS_NEG_ERRORNEOUS_SDU =  : java.lang.Long
 *  -[0]UMTS_NEG_HANDLING_PRIORITY =  : java.lang.Long
 *  -[0]UMTS_NEG_MAX_BITRATE_DOWN =  : java.lang.Long
 *  -[0]UMTS_NEG_MAX_BITRATE_UP =  : java.lang.Long
 *  -[0]UMTS_NEG_MAX_SDU_SIZE =  : java.lang.Long
 *  -[0]UMTS_NEG_RESIDUAL_BER =  : java.lang.Long
 *  -[0]UMTS_NEG_SDU_ERR_RATIO =  : java.lang.Long
 *  -[0]UMTS_NEG_TRAFFIC     =  : java.lang.Long
 *  -[0]UMTS_NEG_TRANSFER_DELAY =  : java.lang.Long
 *  -[0]UMTS_REQ_ALLC_RETN_PRIOR =  : java.lang.Long
 *  -[0]UMTS_REQ_DELIVERY_ORDER =  : java.lang.Long
 *  -[0]UMTS_REQ_ERRORNEOUS_SDU =  : java.lang.Long
 *  -[0]UMTS_REQ_HANDLING_PRIORITY =  : java.lang.Long
 *  -[0]UMTS_REQ_MAX_BITRATE_DOWN =  : java.lang.Long
 *  -[0]UMTS_REQ_MAX_BITRATE_UP =  : java.lang.Long
 *  -[0]UMTS_REQ_MAX_SDU_SIZE =  : java.lang.Long
 *  -[0]UMTS_REQ_RESIDUAL_BER =  : java.lang.Long
 *  -[0]UMTS_REQ_SDU_ERR_RATIO =  : java.lang.Long
 *  -[0]UMTS_REQ_TRAFFIC     =  : java.lang.Long
 *  -[0]UMTS_REQ_TRANSFER_DELAY =  : java.lang.Long
 *  -[0]UOM_CODE             =  : java.lang.Long
 *  -[0]UOM_CODE_PUB         =  : java.lang.String
 *  -[0]UPLINK_VOLUME        =  : java.lang.Float
 *  -[0]UPLINK_VOLUME_UMCODE =  : java.lang.Long
 * *-[0]VAS_CODE             =  : java.lang.String
 *  -[0]VPN_CALL_TYPE        =  : java.lang.Long
 * *-[0]XFILE_EXTERNAL_CHARGE =  : java.lang.Float
 * *-[0]XFILE_NET_RATE       =  : java.lang.Float
 *  -[0]XFILE_NET_RATE_IC    =  : java.lang.Float
 *  -[0]XFILE_NET_RATE_IC_AMOUNT =  : com.lhs.ccb.common.soiimpl.SVLMoneyImpl
 *   SEARCH_IS_COMPLETE   =  : java.lang.Boolean
 * }
 *}</pre>
 *
 **/
/**
 * <p>BSCSUsageDataRecord class.</p>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public class BSCSUsageDataRecord extends BSCSModel {

    /**
     * <p>getRecordId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getRecordId() {
        return getLongValue(Constants.P_CDR_ID);
    }

    /**
     * <p>getRecordSubId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getRecordSubId() {
        return getLongValue(Constants.P_CDR_SUB_ID);
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
     * <p>getContractId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getContractId() {
        return getLongValue(Constants.P_CO_ID);
    }

    /**
     * <p>setContractId.</p>
     *
     * @param id a {@link java.lang.Long} object.
     */
    public void setContractId(Long id) {
        setLongValue(Constants.P_CO_ID, id);
    }

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
     * @param idpub a {@link java.lang.String} object.
     */
    public void setContractIdPub(String idpub) {
        setStringValue(Constants.P_CO_ID_PUB, idpub);
    }

    /**
     * <p>getCustomerId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getCustomerId() {
        return getLongValue(Constants.P_CS_ID);
    }

    /**
     * <p>setCustomerId.</p>
     *
     * @param csid a {@link java.lang.Long} object.
     */
    public void setCustomerId(Long csid) {
        setLongValue(Constants.P_CS_ID, csid);
    }

    /**
     * <p>getCustomerIdPub.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getCustomerIdPub() {
        return getStringValue(Constants.P_CS_ID_PUB);
    }

    /**
     * <p>setCustomerIdPub.</p>
     *
     * @param csidpub a {@link java.lang.String} object.
     */
    public void setCustomerIdPub(String csidpub) {
        setStringValue(Constants.P_CS_ID_PUB, csidpub);
    }

    /**
     * <p>getUDRBasePartId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getUDRBasePartId() {
        return getLongValue(Constants.P_UDR_BASEPART_ID);
    }

    /**
     * <p>getReRatingSequence.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getReRatingSequence() {
        return getIntegerValue(Constants.P_RERATE_SEQNO);
    }

    /**
     * <p>getStartDate.</p>
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getStartDate() {
        return getDateTimeValue(Constants.P_ORIG_START);
    }

    /**
     * Directory number of the Calling (served) party
     *
     * @return a {@link java.lang.String} object.
     */
    public String getCallingParty() {
        return getStringValue(Constants.P_CALLINGPARTY);
    }

    /**
     * <p>getDevicenum.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getDevicenum() {
        return getStringValue(Constants.P_DEVICE_NUM);
    }
    /**
	 * 
	 * @param P_RATED_FLAT_AMOUNT
	 */
	public void setRatedFlatAmount(Double ratedFlatAmt) {
		setDoubleValue(Constants.P_RATED_FLAT_AMOUNT, ratedFlatAmt);
	}

	/**
	 * 
	 * @return P_RATED_FLAT_AMOUNT
	 */
	public Double getRatedFlatAmount() {
		return getMoneyAmountValue(Constants.P_RATED_FLAT_AMOUNT);
	}

    public String getRatedFlatAmountCurrencyCode() {
        return getMoneyCurrencyCodeValue(Constants.P_RATED_FLAT_AMOUNT);
    }

	/**
     * <p>getEntryDate.</p>
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getEntryDate() {
        return getDateTimeValue(Constants.P_ENTRY_DATE);
    }

    /**
     * <p>setEntryDate.</p>
     *
     * @param dtAct a {@link java.util.Date} object.
     */
    public void setEntryDate(Date dtAct) {
        setDateTimeValue(Constants.P_ENTRY_DATE, dtAct);
    }
    
    /**
     * <p>getVasCode.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getVasCode() {
        return getStringValue(Constants.P_VAS_CODE);
    }

    /**
     * <p>setVasCode.</p>
     *
     * @param vasCode a {@link java.lang.String} object.
     */
    public void setVasCode(String vasCode) {
        setStringValue(Constants.P_VAS_CODE, vasCode);
    }
    
    /**
     * <p>getUomtPub.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getUomtPub() {
        return getStringValue(Constants.P_UMCODE_PUB);
    }

    /**
     * <p>setUomtPub.</p>
     *
     * @param vasCode a {@link java.lang.String} object.
     */
    public void setUomtPub(String uomtPub) {
        setStringValue(Constants.P_UMCODE_PUB, uomtPub);
    }

    public String getCurrencyIdPub() {
        return getStringValue(Constants.P_CURRENCY_ID_PUB);
    }
}
