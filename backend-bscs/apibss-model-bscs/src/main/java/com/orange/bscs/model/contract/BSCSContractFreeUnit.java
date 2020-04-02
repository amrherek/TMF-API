package com.orange.bscs.model.contract;

import java.util.Date;
import java.util.List;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

/**
 * 
 *  CONTRACT_FREE_UNITS.READ {
 *   CO_ID                =  : java.lang.Long
 *   CO_ID_PUB            =  : java.lang.String
 * }
 * => {
 *   CO_ID                =  : java.lang.Long
 *   CO_ID_PUB            =  : java.lang.String
 *   free_units           = sub element : com.lhs.ccb.common.soiimpl.NamedValueContainerList
 *  -[0]FUP_ASS_LEVEL        =  : java.lang.String
 *  -[0]FUP_SEQ              =  : java.lang.Integer
 *  -[0]FUP_UOM_CODE         =  : java.lang.Integer
 *  -[0]FUP_VOLUME           =  : java.lang.Float
 *  -[0]FU_ACC_KEY           =  : java.lang.Integer
 *  -[0]FU_PACK_ID           =  : java.lang.Integer
 *  -[0]FU_PACK_ID_PUB       =  : java.lang.String
 *  -[0]FU_PACK_NAME         =  : java.lang.String
 * }
 * 
 */
/**
 * <p>BSCSContractFreeUnit class.</p>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public class BSCSContractFreeUnit extends BSCSModel {

    private static final String FU_VOLUME = "FU_VOLUME";

    /**
     * <p>getSequenceNumber.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getSequenceNumber() {
        return getIntegerValue(Constants.P_FUP_SEQ);
    }

    /**
     * <p>setSequenceNumber.</p>
     *
     * @param seq a {@link java.lang.Integer} object.
     */
    public void setSequenceNumber(Integer seq) {
        setIntegerValue(Constants.P_FUP_SEQ, seq);
    }

    /**
     * <p>getVolume.</p>
     *
     * @return a {@link java.lang.Double} object.
     */
    public Double getVolume() {
        return getDoubleValue(FU_VOLUME);
    }

    /**
     * <p>setVolume.</p>
     *
     * @param volume a {@link java.lang.Double} object.
     */
    public void setVolume(Double volume) {
        setDoubleValue(FU_VOLUME, volume);
    }

    /**
     * <p>getFUPackageId.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getFUPackageId() {
        return getIntegerValue(Constants.P_FU_PACK_ID);
    }

    /**
     * <p>setFUPackageId.</p>
     *
     * @param packId a {@link java.lang.Integer} object.
     */
    public void setFUPackageId(Integer packId) {
        setIntegerValue(Constants.P_FU_PACK_ID, packId);
    }

    /**
     * <p>getFUPackageIdPub.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getFUPackageIdPub() {
        return getStringValue(Constants.P_FU_PACK_ID_PUB);
    }

    /**
     * <p>setFUPackageIdPub.</p>
     *
     * @param packIdPub a {@link java.lang.String} object.
     */
    public void setFUPackageIdPub(String packIdPub) {
        setStringValue(Constants.P_FU_PACK_ID_PUB, packIdPub);
    }

    /**
     * <p>getFUPackageName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getFUPackageName() {
        return getStringValue(Constants.P_FU_PACK_NAME);
    }

    /**
     * <p>setFUPackageName.</p>
     *
     * @param packName a {@link java.lang.String} object.
     */
    public void setFUPackageName(String packName) {
        setStringValue(Constants.P_FU_PACK_NAME, packName);
    }

	/**
	 * 
	 * @return P_CO_ID_PUB
	 */
	public String getContractIdPub() {
		return getStringValue(Constants.P_CO_ID_PUB);
	}

	/**
	 * 
	 * @param P_CO_ID_PUB
	 */
	public void setContractIdPub(String coIdPub) {
		setStringValue(Constants.P_CO_ID_PUB, coIdPub);
	}

	/**
	 * 
	 * @param P_CO_ID
	 */
	public void setCoId(Long coId) {
		setLongValue(Constants.P_CO_ID, coId);
	}

	/**
	 * 
	 * @return P_CO_ID
	 */
	public Long getsetCoId() {
		return getLongValue(Constants.P_CO_ID);
	}

	/**
	 * 
	 * @return List<BillDocument>
	 */
	public List<BSCSContractFreeUnit> getBscsContractFreeUnit() {
		List<BSCSContractFreeUnit> res = this.getListOfBSCSModelValue(Constants.P_FUA_DATA, BSCSContractFreeUnit.class);
		return res;
	}

	/**
     * <p>
     * getGrantedUnits.
     * </p>
     *
     * @return a {@link java.lang.Double} object.
     */
	public Double getGrantedUnits() {
		return getDoubleValue(Constants.P_FU_GRANT_INT);
	}

	/**
     * <p>
     * setGrantedUnits.
     * </p>
     *
     * @param volume
     *            a {@link java.lang.Double} object.
     */
    public void setGrantedUnits(Double volume) {
		setDoubleValue(Constants.P_FU_GRANT_INT, volume);
	}

	/**
     * <p>
     * getAppliedUnits.
     * </p>
     *
     * @return a {@link java.lang.Double} object.
     */
    public Double getAppliedUnits() {
		return getDoubleValue(Constants.P_FU_APPL_INT);
	}

	/**
     * <p>
     * setAppliedUnits.
     * </p>
     *
     * @param volume
     *            a {@link java.lang.Double} object.
     */
    public void setAppliedUnits(Double volume) {
		setDoubleValue(Constants.P_FU_APPL_INT, volume);
	}

	/**
	 * Date when contract is activate (status=2)
	 *
	 * @return a {@link java.util.Date} object.
	 */
	public Date getPeriodFrom() {
		return getDateValue(Constants.P_PERIOD_FROM);
	}

	/**
	 * <p>
	 * setPeriodFrom.
	 * </p>
	 *
	 * @param dt
	 *            a {@link java.util.Date} object.
	 */
	public void setPeriodFrom(Date dt) {
		setDateValue(Constants.P_PERIOD_FROM, dt);
	}

	/**
	 * Date when contract is activate (status=2)
	 *
	 * @return a {@link java.util.Date} object.
	 */
	public Date getPeriodTo() {
		return getDateValue(Constants.P_PERIOD_TO);
	}

	/**
	 * <p>
	 * setPeriodFrom.
	 * </p>
	 *
	 * @param dt
	 *            a {@link java.util.Date} object.
	 */
	public void setPeriodTo(Date dt) {
		setDateValue(Constants.P_PERIOD_TO, dt);
	}

	/**
	 * 
	 * @return P_FU_UOM_PUB
	 */
	public String getUomtPub() {
		return getStringValue(Constants.P_FU_UOM_PUB);
	}

	/**
	 * 
	 * @param P_FU_UOM_PUB
	 */
	public void setUomtPub(String uomPub) {
		setStringValue(Constants.P_FU_UOM_PUB, uomPub);
	}

	/**
	 * 
	 * @return P_BALANCE_TYPE
	 */
	public String getBalanceType() {
		return getStringValue(Constants.P_BALANCE_TYPE);
	}

	/**
	 * 
	 * @param P_BALANCE_TYPE
	 */
	public void setBalanceType(String balType) {
		setStringValue(Constants.P_BALANCE_TYPE, balType);
	}

	/**
	 * 
	 * @return P_LAST_CALL_DATE
	 */
	public Date getlastCallDate() {
		return getDateValue(Constants.P_LAST_CALL_DATE);
	}

	/**
	 * 
	 * @param lastCallDate
	 */
	public void setlastCallDate(Date lastCallDate) {
		setDateValue(Constants.P_LAST_CALL_DATE, lastCallDate);
	}

    public String getCurrencyPub() {
        return getStringValue("CURR_PUB");
    }

}
