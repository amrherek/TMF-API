package com.orange.bscs.model.usagedata;

import java.util.Date;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

public class Balances extends BSCSModel {

	/**
	 * 
	 * @param P_ACTUAL_VALUE
	 */
	public void setActualValue(Double actualValue) {
		setDoubleValue(Constants.P_ACTUAL_VALUE, actualValue);
	}

	/**
	 * 
	 * @return P_ACTUAL_VALUE
	 */
	public Double getActualValue() {
		return getDoubleValue(Constants.P_ACTUAL_VALUE);
	}

	/**
	 * 
	 * @param P_CREDIT_VALUE
	 */
	public void setCreditValue(Double actualValue) {
		setDoubleValue(Constants.P_CREDIT_VALUE, actualValue);
	}

	/**
	 * 
	 * @return P_CREDIT_VALUE
	 */
	public Double getCreditValue() {
		return getDoubleValue(Constants.P_CREDIT_VALUE);
	}

	/**
	 * <p>
	 * P_NEXT_RESET_DATE.
	 * </p>
	 * 
	 * @return a {@link java.util.Date} object.
	 */
	public Date getNextFreeResetDate() {
		return getDateTimeValue(Constants.P_NEXT_RESET_DATE);
	}
	/**
     * <p>getUmCodePub.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getUmCodePub() {
        return getStringValue(Constants.P_UMCODE_PUB);
    }

    /**
     * <p>setUmCodePub.</p>
     *
     * @param umcodepub a {@link java.lang.String} object.
     */
    public void setUmCodePub(String umcodepub) {
        setStringValue(Constants.P_UMCODE_PUB, umcodepub);
    }

    /**
     * 
     * @param snCode
     */
    public void setSnCode(Long snCode) {    	
    	setLongValue(Constants.P_SNCODE, snCode);
    }
    /**
     * 
     * @return P_SNCODE
     */
    public Long getSnCode() {
        return getLongValue(Constants.P_SNCODE);
    }
    /**
     * 
     * @param snCodePub
     */
    public void setSnCodePub(String snCodePub) {    	
    	setStringValue(Constants.P_SNCODE_PUB, snCodePub);
    }
    /**
     * 
     * @return P_SNCODE_PUB
     */
    public String getSnCodePub() {
        return getStringValue(Constants.P_SNCODE_PUB);
    }
    
    /**
	 * 
	 * @return P_BALANCE_SNAPSHOT_DATE
	 */
	public Date getBalanceSnapshotDate() {
		return getDateTimeValue(Constants.P_BALANCE_SNAPSHOT_DATE);
	}
	/**
	 * 
	 * @param lastCallDate
	 */
	public void setBalanceSnapshotDate(Date lastCallDate) {
		setDateTimeValue(Constants.P_BALANCE_SNAPSHOT_DATE, lastCallDate);
	}
	
    public String getCurrencyIdPub() {
        return getStringValue(Constants.P_CURRENCY_ID_PUB);
    }

}
