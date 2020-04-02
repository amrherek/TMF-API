package com.orange.bscs.model.product;

import java.util.List;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;
import com.orange.bscs.model.contract.BSCSService;

/**
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
 * If input doesn't contains RPCODE/RPCODE_PUB, this object will not contains
 * RPCODE/RPCODE_PUB.
 *
 * @author IT&Labs
 * @version $Id: $
 */
public class BSCSServicePackage extends BSCSModel {

    /**
     * Rateplan code.
     * <p>
     * fill only if request contains a RP filter
     * </p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getRatePlanCode() {
        return getLongValue(Constants.P_RPCODE);
    }

    /**
     * Rateplan code.
     *
     * @param rpcode a {@link java.lang.Long} object.
     */
    public void setRatePlanCode(Long rpcode) {
        setLongValue(Constants.P_RPCODE, rpcode);
    }

    /**
     * public key of the rateplan
     * <p>
     * fill only if request contains a RP filter
     * </p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getRatePlanCodePub() {
        return getStringValue(Constants.P_RPCODE_PUB);
    }

    /**
     * <p>setRatePlanCodePub.</p>
     *
     * @param rpcodepub a {@link java.lang.String} object.
     */
    public void setRatePlanCodePub(String rpcodepub) {
        setStringValue(Constants.P_RPCODE_PUB, rpcodepub);
    }

    /**
     * service package code.
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getServicePackageCode() {
        return getLongValue(Constants.P_SPCODE);
    }

    /**
     * service package code.
     *
     * @param spcode a {@link java.lang.Long} object.
     */
    public void setServicePackageCode(Long spcode) {
        setLongValue(Constants.P_SPCODE, spcode);
    }

    /**
     * Public key of service package.
     *
     * @return a {@link java.lang.String} object.
     */
    public String getServicePackageCodePub() {
        return getStringValue(Constants.P_SPCODE_PUB);
    }

    /**
     * Public key of service package.
     *
     * @param spcodepub a {@link java.lang.String} object.
     */
    public void setServicePackageCodePub(String spcodepub) {
        setStringValue(Constants.P_SPCODE_PUB, spcodepub);
    }

    /**
     * <p>getDescription.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getDescription() {
        return getStringValue(Constants.P_SP_DES);
    }

    /**
     * <p>setDescription.</p>
     *
     * @param spdes a {@link java.lang.String} object.
     */
    public void setDescription(String spdes) {
        setStringValue(Constants.P_SP_DES, spdes);
    }

    /**
     * <p>getShortDescription.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getShortDescription() {
        return getStringValue(Constants.P_SP_SHDES);
    }

    /**
     * <p>setShortDescription.</p>
     *
     * @param spshdes a {@link java.lang.String} object.
     */
    public void setShortDescription(String spshdes) {
        setStringValue(Constants.P_SP_SHDES, spshdes);
    }

    /** filled by referentialService.findAllowedServices() */
    public List<BSCSService> getAllowedService() {
        return getListOfBSCSModelValue(Constants.P_LIST_SN_CODE, BSCSService.class);
    }
    
}
