package com.orange.bscs.model.topology;

import java.util.List;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;


/**
 * Read-only Market class.
 * 
 * (used from MARKET.READ)
 * 
 * @author itlabs
 *
 */
public class BSCSMarket extends BSCSModel {

    private static final String P_NUMBERPORTABILITY_SUPP = "NUMBERPORTABILITY_SUPP";
    private static final String P_SHARED_RES = "SHARED_RES";
    private static final String P_MRKT_PREFIX = "MRKT_PREFIX";
    private static final String P_MRKT_STORMED_ACTIVE = "MRKT_STORMED_ACTIVE";
    private static final String P_MRKT_NETWORK_USAGE_IND = "MRKT_NETWORK_USAGE_IND";
    private static final String P_MRKT_TYPE = "MRKT_TYPE";
    private static final String P_MRKT_TRIAL = "MRKT_TRIAL";
    private static final String P_CO_TRIAL_PERIOD_DEF = "CO_TRIAL_PERIOD_DEF";
    private static final String P_CO_TRIAL_RATING = "CO_TRIAL_RATING";
    private static final String P_MRKT_CUG_ALLOWED = "MRKT_CUG_ALLOWED";
    private static final String P_CUG_RESET = "CUG_RESET";
    private static final String P_MRKT_MAX_CUG_MEMBERS = "MRKT_MAX_CUG_MEMBERS";
    private static final String P_MRKT_CUG_LABEL = "MRKT_CUG_LABEL";
    private static final String P_MRKT_INST_ADDR_SUPP = "MRKT_INST_ADDR_SUPP";
    private static final String P_MRKT_INST_ADDR_LABEL = "MRKT_INST_ADDR_LABEL";
    private static final String P_MRKT_INST_ADDR_CHNG = "MRKT_INST_ADDR_CHNG";
    private static final String P_IS_ROAMING_ALLOWED = "IS_ROAMING_ALLOWED";
    private static final String P_MASSRATEPLANCHANGE = "MASSRATEPLANCHANGE";
    private static final String P_MRKT_CALL_CREDIT_SUPP = "MRKT_CALL_CREDIT_SUPP";
    private static final String P_MICROCELL = "MICROCELL";
    private static final String P_NUM_S = "NUM_S";

    
    /** SCCODE */
    public Long getMarketId(){ return getLongValue(Constants.P_SCCODE);}

    /** SCCODE_PUB */
    public String getMarketIdPub(){ return getStringValue(Constants.P_SCCODE_PUB);}
    
    /** Market Prefix : MRKT_PREFIX */
    public String getPrefix(){ return getStringValue(P_MRKT_PREFIX);}
    
    public String getDescription(){ return getStringValue(Constants.P_DESCRIPTION);}
    
    /** Market Type (MRKT_TYPE) */
    public EnumMarketType getMarketType(){ return EnumMarketType.fromCharacter(getCharacterValue(P_MRKT_TYPE));}
    
    
    /** Indicates whether network services are used/rated. (MRKT_NETWORK_USAGE_IND)
     * <ul><li>M (mandatory): every network service is rated in this market, 
     * <li>O (optional): the user decides in ST whether a network services is rated in this market, 
     * <li>N (none): network services are not rated in this market.
     * </ul>
     **/
    public EnumMarketNetworkUsage getNetworkUsage(){ return EnumMarketNetworkUsage.fromCharacter(getCharacterValue(P_MRKT_NETWORK_USAGE_IND));}
    
    /**Market trial support. True if trial is supported (MRKT_TRIAL)*/
    public Boolean isTrialSupport(){ return getBooleanValue(P_MRKT_TRIAL);}
    
    /** default trial period for contracts and services in days. (CO_TRIAL_PERIOD_DEF) */
    public Integer getTrialDefaultPeriod(){ return getIntegerValue(P_CO_TRIAL_PERIOD_DEF );}
    
    /** True if on trial contracts are rated.(CO_TRIAL_RATING)*/
    public Boolean isTrialRating(){ return getBooleanValue(P_CO_TRIAL_RATING);}
    
    
    /** Flag indicating whether CUGs are allowed for this market (MRKT_CUG_ALLOWED) */
    public Boolean isCUGAllowed(){ return getBooleanValue(P_MRKT_CUG_ALLOWED);}
    
    /** Reset period in days for CUGs. Set only if market supports CUG. */
    public Integer getCUGResetPeriod(){ return getIntegerValue(P_CUG_RESET);}
    
    /** Indicates the maximum number of members of a CUG defined for this market. This is set only in case 
     * the MRKT_CUG_ALLOWED is true. (MRKT_MAX_CUG_MEMBERS)*/
    public Long getCUGMaxMembers(){ return getLongValue(P_MRKT_MAX_CUG_MEMBERS);}
    
    /** Name of the CUG resource for this market (MRKT_CUG_LABEL) */
    public String getCUGResourceLabel(){ return getStringValue(P_MRKT_CUG_LABEL);}
    
    /** Flag indicating whether a contract installation address can be specified for the current market.
     * (MRKT_INST_ADDR_SUPP )*/
    public Boolean isInstallationAddressSupport(){ return getBooleanValue(P_MRKT_INST_ADDR_SUPP );}

    /** Label denominating the installation address term for the current market. The value is set only if the MRKT_INST_ADDR_SUPP 
     * is true. (MRKT_INST_ADDR_LABEL) */
    public String getInstallationAddressLabel(){ return getStringValue(P_MRKT_INST_ADDR_LABEL);}

    /** Flag indicating whether the installation address can be changed once entered. The value is set only if 
     * the MRKT_INST_ADDR_SUPP is true. (MRKT_INST_ADDR_CHNG)*/
    public Boolean isInstallationAddressChangeSupport(){ return getBooleanValue(P_MRKT_INST_ADDR_CHNG);}
    
    /**true if market allowed the sharing of storage medium (MRKT_STORMED_ACTIVE) */
    public Boolean isStormedActive(){ return getBooleanValue(P_MRKT_STORMED_ACTIVE);}

    /** True, if roaming is possible (IS_ROAMING_ALLOWED) */
    public Boolean isRoamingSupport(){ return getBooleanValue(P_IS_ROAMING_ALLOWED);}
    
    /**True, if mass change of rate plan is possible (MASSRATEPLANCHANGE) */
    public Boolean isMassRateplanChange(){ return getBooleanValue(P_MASSRATEPLANCHANGE);}

    /** true if market supports porting of directory numbers. (NUMBERPORTABILITY_SUPP) */
    public Boolean isNumberPortingSupport() { return getBooleanValue(P_NUMBERPORTABILITY_SUPP);}
    
    /** Flag if sharing of resources is allowed (Storage Medium,MPDSCTAB.STORMED_ACTIVE.) */
    public Boolean isSharedResource(){ return getBooleanValue(P_SHARED_RES);}
    
    /** Flag indicating whether the call crediting is allowed for the market (MRKT_CALL_CREDIT_SUPP) */
    public Boolean isCallCreditSupport(){ return getBooleanValue(P_MRKT_CALL_CREDIT_SUPP); }
    
    /** Values can be O, Z. 
     * <ul><li>In case it is O, only origins are supported, that means only ultra weak is supported. 
     * <li>For Z Zones consisting of destination and origins are permitted, that means weak and ultra weak is supported. 
     * <li>If no value is returned microcells are not supported for this market.</ul> */
    public Character getMicroCellInd(){ return getCharacterValue(P_MICROCELL);}
    
    /** List of submarkets */
    public List<BSCSSubMarket> getSubMarkets(){ return getListOfBSCSModelValue(P_NUM_S, BSCSSubMarket.class);}
    
}
