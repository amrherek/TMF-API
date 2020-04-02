package com.orange.bscs.model.product;

import java.util.List;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

/**
 * <code>
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
 * </code>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public class BSCSRatePlan extends BSCSModel {

    /**
     * RPCODE
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getCode() {
        return getLongValue(Constants.P_RPCODE);
    }

    /**
     * RPCODE
     *
     * @param code a {@link java.lang.Long} object.
     */
    public void setCode(Long code) {
        setLongValue(Constants.P_RPCODE, code);
    }

    /**
     * RPCODE_PUB
     *
     * @return a {@link java.lang.String} object.
     */
    public String getCodePublic() {
        return getStringValue(Constants.P_RPCODE_PUB);
    }

    /**
     * RPCODE_PUB
     *
     * @param code a {@link java.lang.String} object.
     */
    public void setCodePublic(String code) {
        setStringValue(Constants.P_RPCODE_PUB, code);
    }

    /**
     * <p>getVersion.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getVersion() {
        return getLongValue(Constants.P_RP_VSCODE);
    }

    /**
     * <p>setVersion.</p>
     *
     * @param version a {@link java.lang.Long} object.
     */
    public void setVersion(Long version) {
        setLongValue(Constants.P_RP_VSCODE, version);
    }

    /**
     * RP_DES
     *
     * @return a {@link java.lang.String} object.
     */
    public String getDescription() {
        return getStringValue(Constants.P_RP_DES);
    }

    /**
     * <p>setDescription.</p>
     *
     * @param des a {@link java.lang.String} object.
     */
    public void setDescription(String des) {
        setStringValue(Constants.P_RP_DES, des);
    }

	/**
	 * RP_SHDES
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getShortDescription() {
		return getStringValue(Constants.P_RP_SHDES);
	}

	/**
	 * <p>
	 * setShortDescription.
	 * </p>
	 *
	 * @param des
	 *            a {@link java.lang.String} object.
	 */
	public void setShortDescription(String des) {
		setStringValue(Constants.P_RP_SHDES, des);
	}

    /** filled by referentialService.findAllowedServices() */
    public List<BSCSServicePackage> getAllowedServicePackages() {
        return getListOfBSCSModelValue(Constants.P_LIST_SP_CODE, BSCSServicePackage.class);
    }

    public void setPaymentMethodInd(Character value) {
        setCharacterValue(Constants.P_PAYMENT_METHOD_IND, value);
    }

    public Character getPaymentMethodInd() {
        return getCharacterValue(Constants.P_PAYMENT_METHOD_IND);
    }

}
