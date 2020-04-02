package com.orange.bscs.model.contract;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;
import com.orange.bscs.model.businesspartner.EnumContractStatus;
import com.orange.bscs.model.resource.BSCSDirectoryNumber;

/**
 * <p>BSCSContractService class.</p>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public class BSCSContractService extends BSCSModel {

    // WARN This field in not serialized if this service is add to an other BSCSModel as a field
    // or element of a list-field.
    private List<BSCSContractServiceParameter> changedParams;
  
    /**
     * <p>getServicePackageCode.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getServicePackageCode() {
        return getLongValue(Constants.P_SPCODE);
    }

    /**
     * <p>setServicePackageCode.</p>
     *
     * @param spcode a {@link java.lang.Long} object.
     */
    public void setServicePackageCode(Long spcode) {
        setLongValue(Constants.P_SPCODE, spcode);
    }

    /**
     * <p>getServicePackagePublicCode.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getServicePackagePublicCode() {
        return getStringValue(Constants.P_SPCODE_PUB);
    }

    /**
     * <p>setServicePackagePublicCode.</p>
     *
     * @param spcodepub a {@link java.lang.String} object.
     */
    public void setServicePackagePublicCode(String spcodepub) {
        setStringValue(Constants.P_SPCODE_PUB, spcodepub);
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
     * @param sncode a {@link java.lang.Long} object.
     */
    public void setServiceCode(Long sncode) {
        setLongValue(Constants.P_SNCODE, sncode);
    }

    /**
     * <p>getServiceCodePub.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getServiceCodePub() {
        return getStringValue(Constants.P_SNCODE_PUB);
    }

    /**
     * <p>setServiceCodePub.</p>
     *
     * @param sncodepub a {@link java.lang.String} object.
     */
    public void setServiceCodePub(String sncodepub) {
        setStringValue(Constants.P_SNCODE_PUB, sncodepub);
    }

    /**
     * <p>getProfileId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getProfileId() {
        return getLongValue(Constants.P_PROFILE_ID);
    }

    /**
     * <p>setProfileId.</p>
     *
     * @param profileId a {@link java.lang.Long} object.
     */
    public void setProfileId(Long profileId) {
        setLongValue(Constants.P_PROFILE_ID, profileId);
    }

    /**
     * <p>getPendingStatusDate (COS_PENDING_STATUS_DATE).</p>
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getPendingStatusDate() {
        return getDateValue(Constants.P_COS_PENDING_STATUS_DATE);
    }

    /**
     * <p>setPendingStatusDate (COS_PENDING_STATUS_DATE).</p>
     *
     * @param validFrom a {@link java.util.Date} object.
     */
    public void setPendingStatusDate(Date validFrom) {
        setDateValue(Constants.P_COS_PENDING_STATUS_DATE, validFrom);
    }

    /**
     * <p>getPendingStatus.</p>
     *  if used with contract commands (CONTRACT.READ/CONTRACTS.SEARCH), return P_CO_SN_PENDING_STATUS
     *  if used with contract_services commands (CONTRACT_SERVICES.READ), return P_COS_PENDING_STATUS
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getPendingStatusAsInt() {
        Integer pending = getIntegerValue(Constants.P_COS_PENDING_STATUS);
        if(null==pending){
            pending = getIntegerValue(Constants.P_CO_SN_PENDING_STATUS) ;
        }
        return pending;
    }

    public EnumContractStatus getPendingStatus() {
        return EnumContractStatus.fromInt(getPendingStatusAsInt());
    }

    public void setPendingStatus(EnumContractStatus status) {
        setIntegerValue(Constants.P_COS_PENDING_STATUS, EnumContractStatus.toInt(status));
    }
    
    /**
     * <p>setPendingStatus.</p>
     *
     * @param status a {@link java.lang.Integer} object.
     */
    public void setPendingStatus(Integer status) {
        setIntegerValue(Constants.P_COS_PENDING_STATUS, status);
    }

    /**
     * <p>getLastActivationDate.</p>
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getLastActivationDate() {
        return getDateTimeValue(Constants.P_COS_LAST_ACT_DATE);
    }

    /**
     * <p>setLastActivationDate.</p>
     *
     * @param dtAct a {@link java.util.Date} object.
     */
    public void setLastActivationDate(Date dtAct) {
        setDateTimeValue(Constants.P_COS_LAST_ACT_DATE, dtAct);
    }

    /**
     * <p>getDirectoryNumbers.</p>
     *
     * @return toujours une liste (même vide)
     */
    public List<BSCSDirectoryNumber> getDirectoryNumbers() {
        List<BSCSDirectoryNumber> l = getListOfBSCSModelValue(Constants.P_DIRECTORY_NUMBERS, BSCSDirectoryNumber.class);
        if (null == l) {
            l = new ArrayList<BSCSDirectoryNumber>();
        }
        return l;
    }

    /**
     * <p>addDirectoryNumber.</p>
     *
     * @param dirnum a {@link com.orange.bscs.model.resource.BSCSDirectoryNumber} object.
     */
    public void addDirectoryNumber(BSCSDirectoryNumber dirnum) {
        if (null == dirnum){  return; }
        
        List<BSCSDirectoryNumber> l = getDirectoryNumbers();
        l.add(dirnum);
        setListOfBSCSModelValue(Constants.P_DIRECTORY_NUMBERS, l);
    }

    /**
     * <p>getParametersWrite.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<BSCSContractServiceParameter> getParametersWrite() {
        // CONTRACT_SERVICE_PARAMETERS.WRITE
        List<BSCSContractServiceParameter> l;
        l = getListOfBSCSModelValue(Constants.P_PARAM_VALUES, BSCSContractServiceParameter.class);
        if (null == l) {
            l = new ArrayList<BSCSContractServiceParameter>();
        }
        return l;
    }

    /**
     * <p>getParametersRead.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<BSCSContractServiceParameter> getParametersRead() {
        List<BSCSContractServiceParameter> l;
        // CONTRACT_SERVICE_PARAMETERS.READ,
        // CONTRACT_SERVICE_PARAMETERS_HISTORY.READ
        l = getListOfBSCSModelValue(Constants.P_NUM_PARAMS, BSCSContractServiceParameter.class);
        if (null == l) {
            l = new ArrayList<BSCSContractServiceParameter>();
        }
        return l;
    }

    /**
     * Status au niveau du detail d'un service
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getCOSStatusAsInt() {
        return getIntegerValue(Constants.P_COS_STATUS);
    }
    
    /**
     * <p>setCOSStatus.</p>
     *
     * @param status a {@link java.lang.Integer} object.
     */
    public void setCOSStatusAsInt(Integer status) {
        setIntegerValue(Constants.P_COS_STATUS, status);
    }

    
    /**
     * Status au niveau du detail d'un service
     * 
     * @return
     */ 
    public EnumContractStatus getCOSStatus(){
        return EnumContractStatus.fromInt(getCOSStatusAsInt());
    }
    
    /**
     * Status au niveau du detail d'un service
     * 
     * @param status
     */ 
    public void setCOSStatus(EnumContractStatus status){
        setCOSStatusAsInt( EnumContractStatus.toInt(status));
    }
    
    /**
     * <p>getCOSStatusDate.</p>
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getCOSStatusDate() {
        return getDateTimeValue(Constants.P_COS_STATUS_DATE);
    }

    /**
     * <p>setCOSStatusDate.</p>
     *
     * @param dt a {@link java.util.Date} object.
     */
    public void setCOSStatusDate(Date dt) {
        setDateTimeValue(Constants.P_COS_STATUS_DATE, dt);
    }

    /**
     * Status retourné dans la liste des services d'un contrat
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getCOSNStatus() {
        return getIntegerValue(Constants.P_CO_SN_STATUS);
    }

    /**
     * Return Pending Status or COSStatus (or COSNStatus if service provided by
     * Contract.read (list of service))
     *
     * @return a {@link java.lang.Integer} object.
     */
    public EnumContractStatus retrieveStatus() {
        // Future status
        Integer status = getPendingStatusAsInt();
        if (null == status  || 0 == status) {
            // Status from CONTRACT_SERVICES.READ, don't keep (pending status=0=>no pending status)
            status = getCOSStatusAsInt();
        }
        if (null == status) {
            // Status from CONTRACT.READ
            status = getCOSNStatus();
        }
        return EnumContractStatus.fromInt(status);
    }

    /**
     * CONTRACT_SERVICES.WRITE (USER_REASON) cf REASONS.READ for list of all
     * Reasons. All combinaisons are not allowed.
     * 
     * @return
     **/
    public Long getUserReason() { return getLongValue(Constants.P_USER_REASON);}

    /**
     * CONTRACT_SERVICES.WRITE (USER_REASON) cf REASONS.READ for list of all
     * Reasons. All combinaisons are not allowed.
     * 
     * @param userReason
     **/
    public void setUserReason(Long userReason) {
        setLongValue(Constants.P_USER_REASON, userReason);
    }

    /**
     * <p>getChangedParameters.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<BSCSContractServiceParameter> getChangedParameters() {
        if (null == changedParams) {
            changedParams = new ArrayList<BSCSContractServiceParameter>();
        }
        return changedParams;
    }

    /**
     * <p>setChangedParameters.</p>
     *
     * @param params a {@link java.util.List} object.
     */
    public void setChangedParameters(List<BSCSContractServiceParameter> params) {
        changedParams = params;
    }

    /**
     * <p>addChangedParameter.</p>
     *
     * @param param a {@link com.orange.bscs.model.contract.BSCSContractServiceParameter} object.
     */
    public void addChangedParameter(BSCSContractServiceParameter param) {
        if (null == param) {
            return;
        }
        getChangedParameters().add(param);
    }

    /**
     * <p>setParametersWrite.</p>
     *
     * @param params a {@link java.util.List} object.
     */
    public void setParametersWrite(List<BSCSContractServiceParameter> params) {
        // CONTRACT_SERVICE_PARAMETERS.WRITE
        setListOfBSCSModelValue(Constants.P_PARAM_VALUES, params);
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
    
  

    public BSCSContractServiceParameter getParameterRead(String paramIdPub) {
        BSCSContractServiceParameter result=null;
        for(BSCSContractServiceParameter param : getParametersRead()){
            if(param.getIdPub().equals(paramIdPub)){
                result=param;
                break;
            }
        }
        return result;
    }
    /**
     * <p>getOpAssistedAction.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getOpAssistedAction() {
        return getStringValue(Constants.P_OP_ASSISTED_ACTION);
    }

    /**
     * <p>setOpAssistedAction.</p>
     *
     * @param action a {@link java.lang.String} object.
     */
    public void setOpAssistedAction(String action) {
        setStringValue(Constants.P_OP_ASSISTED_ACTION, action);
    }
    


}
