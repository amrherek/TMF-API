package com.orange.bscs.model.contract;

import java.util.Date;
import java.util.List;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

/**
 * <pre>
 * {@code
 * CONTRACT_RESOURCES.REPLACE {
 *   RES_TYPE                   =  : java.lang.Integer
 *   OLD_RES_NUM 				=  : java.lang.String
 *   RES_ID						=  : java.lang.Long
 *   CO_ID_PUB                  =  : java.lang.String
 * }
 * => {
 *   
 *   ports  = sub element : com.lhs.ccb.common.soiimpl.NamedValueContainerList
 *  -[0]CO_ID_PUB              =  : java.lang.String
 *  -[0]CO_ID              	   =  : java.lang.Long
 * } *
 * 
 * @author shainy.ajit.jain
 */
public class BSCSContractResource extends BSCSModel {

	/**
	 * <p>
	 * getOldResNum.
	 * </p>
	 * 
	 * @return a {@link java.lang.String} object.
	 */
	public String getOldResNum() {
		return getStringValue(Constants.P_OLD_RES_NUM);
	}

	/**
	 * <p>
	 * setOldResNum.
	 * </p>
	 * 
	 * @param id
	 *            a {@link java.lang.String} object.
	 */
	public void setOldResNum(String resNum) {
		setStringValue(Constants.P_OLD_RES_NUM, resNum);
	}

	/**
	 * <p>
	 * getResNum.
	 * </p>
	 * 
	 * @return a {@link java.lang.String} object.
	 */
	public String getResNum() {
		return getStringValue(Constants.P_RES_NUM);
	}

	/**
	 * <p>
	 * setResNum.
	 * </p>
	 * 
	 * @param id
	 *            a {@link java.lang.String} object.
	 */
	public void setResNum(String resNum) {
		setStringValue(Constants.P_RES_NUM, resNum);
	}

	/**
	 * <p>
	 * getResId.
	 * </p>
	 * 
	 * @return a {@link java.lang.Long} object.
	 */
	public Long getResId() {
		return getLongValue(Constants.P_RES_ID);
	}

	/**
	 * <p>
	 * setResId.
	 * </p>
	 * 
	 * @param newResId
	 *            a {@link java.lang.Long} object.
	 */
	public void setResId(Long newResId) {
		setLongValue(Constants.P_RES_ID, newResId);
	}

	/**
	 * <p>
	 * getcoId.
	 * </p>
	 * 
	 * @return a {@link java.lang.Long} object.
	 */
	public Long getCoId() {
		return getLongValue(Constants.P_CO_ID);
	}

	/**
	 * <p>
	 * setCoId.
	 * </p>
	 * 
	 * @param coId
	 *            a {@link java.lang.Long} object.
	 */
	public void setCoId(Long coId) {
		setLongValue(Constants.P_CO_ID, coId);
	}

	/**
	 * <p>
	 * getCoIdPub.
	 * </p>
	 * 
	 * @return a {@link java.lang.String} object.
	 */
	public String getCoIdPub() {
		return getStringValue(Constants.P_CO_ID_PUB);
	}

	/**
	 * <p>
	 * setCoIdPub
	 * </p>
	 * 
	 * @param coIdPub
	 *            a {@link java.lang.String} object.
	 */
	public void setCoIdPub(String coIdPub) {
		setStringValue(Constants.P_CO_ID_PUB, coIdPub);
	}

	/**
	 * <p>
	 * getresType
	 * <p>
	 * 
	 * @return a {@link java.lang.Integer} object.
	 */
	public Integer getResType() {
		return getIntegerValue(Constants.P_RES_TYPE);
	}

	/**
	 * 
	 * @param resType
	 *            a {@link java.lang.Integer} object.
	 */
	public void setResType(Integer resType) {
		setIntegerValue(Constants.P_RES_TYPE, resType);
	}
	
	/**
     * <p>getDirectoryNumbers.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<BSCSContractResourceDirNum> getDirectoryNumbers() {
        return getListOfBSCSModelValue(Constants.P_DIRECTORY_NUMBERS, BSCSContractResourceDirNum.class);
    }

	/**
	 * <p>
	 * getPorts.
	 * </p>
	 * 
	 * @return a {@link java.util.List} object.
	 */
	public List<BSCSContractResourcePort> getPorts() {
		return getListOfBSCSModelValue(Constants.P_PORTS,
				BSCSContractResourcePort.class);
	}
	
	/**
	 * Returns activation date
	 * 
	 * @return
	 */
	public Date getActivDate() {
		return getDateTimeValue(Constants.P_ACTIV_DATE);
	}

	/**
	 * Sets activation date
	 * 
	 * @param activDate
	 */
	public void setActivDate(Date activDate) {
		setDateTimeValue(Constants.P_ACTIV_DATE, activDate);
	}

	public static class BSCSContractResourceDirNum extends BSCSModel {

        public String getPhoneNumber() {
            return getStringValue(Constants.P_DIRNUM);
        }

        public void setPhoneNumber(String dirnum) {
            setStringValue(Constants.P_DIRNUM, dirnum);
        }

        public Long getCoId() {
			return getLongValue(Constants.P_CO_ID);
		}

		public void setCoID(Long coId) {
			setLongValue(Constants.P_CO_ID, coId);
		}

		public String getCoIdPub() {
			return getStringValue(Constants.P_CO_ID_PUB);
		}

		public void setCoIdPub(String coIdPub) {
			setStringValue(Constants.P_CO_ID_PUB, coIdPub);
		}
    }

	public static class BSCSContractResourcePort extends BSCSModel {
		public String getPortNumber() {
			return getStringValue(Constants.P_PORT_NUM);
		}

		public void setPortNumber(String portnum) {
			setStringValue(Constants.P_PORT_NUM, portnum);
		}

		public Long getCoId() {
			return getLongValue(Constants.P_CO_ID);
		}

		public void setCoID(Long coId) {
			setLongValue(Constants.P_CO_ID, coId);
		}

		public String getCoIdPub() {
			return getStringValue(Constants.P_CO_ID_PUB);
		}

		public void setCoIdPub(String coIdPub) {
			setStringValue(Constants.P_CO_ID_PUB, coIdPub);
		}

	}
}
