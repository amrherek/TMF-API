package com.orange.bscs.model.contract;



import java.util.ArrayList;
import java.util.List;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

/**
 * <p>BSCSContractServiceParameter class.</p>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public class BSCSContractServiceParameter extends BSCSModel {



    /** PRM_NO */
    public Long getParamNo(){ return getLongValue(Constants.P_PRM_NO);}
    /**
     * <p>set PRM_NO.</p>
     *
     * @param paramNo a {@link java.lang.Long} object.
     */
    public void setParamNo(final Long paramNo) {
        setLongValue(Constants.P_PRM_NO, paramNo);
    }

    /** PARENT_NO */
    public Long getParentNo(){ return getLongValue(Constants.P_PARENT_NO);}

    /**
     * <p>set PARENT_NO.</p>
     *
     * @param parentNo a {@link java.lang.Long} object.
     */
    public void setParentNo(final Long parentNo) {
        setLongValue(Constants.P_PARENT_NO, parentNo);
    }

    /** SIBLING_NO */
    public Long getSiblingNo(){ return getLongValue(Constants.P_SIBLING_NO);}

    /**
     * <p>set SIBLING_NO.</p>
     *
     * @param siblingNo a {@link java.lang.Long} object.
     */
    public void setSiblingNo(final Long siblingNo) {
        setLongValue(Constants.P_SIBLING_NO, siblingNo);
    }

    /** COMPLEX_NO */
    public Long getComplexNo(){ return getLongValue(Constants.P_COMPLEX_NO);}
    /**
     * <p>set COMPLEX_NO.</p>
     *
     * @param complexNo a {@link java.lang.Long} object.
     */
    public void setComplexeNo(final Long complexNo) {
        setLongValue(Constants.P_COMPLEX_NO, complexNo);

    }

    /** COMPLEX_LEVEL */
    public Long getComplexLevel(){ return getLongValue(Constants.P_COMPLEX_LEVEL);}
    /**
     * <p>set COMPLEX_LEVEL.</p>
     *
     * @param complexLevel a {@link java.lang.Long} object.
     */
    public void setComplexLevel(final Long complexLevel) {
        setLongValue(Constants.P_COMPLEX_LEVEL, complexLevel);
    }

    /** COS_ACTION */
    public Character getCOSAction(){ return getCharacterValue(Constants.P_COS_ACTION);}
    /**
     * <p>set COS_ACTION.</p>
     *
     * @param cosAction a {@link java.lang.String} object.
     */
    public void setCOSAction(final String cosAction) {
        if (null == cosAction || 0 == cosAction.length()) {
            setCharacterValue(Constants.P_COS_ACTION, null);
        } else {
            setCharacterValue(Constants.P_COS_ACTION, cosAction.charAt(0));
        }
    }

    public void setCOSAction(final Character cosAction) {
        setCharacterValue(Constants.P_COS_ACTION, cosAction);
    }

    /**
     * set TARGET_PARAM_VALUES
     * @param targetParamValues
     */
    public void setTargetParamValues(final List<TargetParamValue> targetParamValues){
        setListOfBSCSModelValue(Constants.P_TARGET_PARAM_VALUES, targetParamValues);
    }

    /**
     * get TARGET_PARAM_VALUES
     * @return
     */
    public List<BSCSModel> getTargetParamValues(){
        return getListOfBSCSModelValue(Constants.P_TARGET_PARAM_VALUES, BSCSModel.class);
    }

    /** CONTRACT_SERVICE_PARAMETERS.READ Only */
    public String getDescription() {
        return getStringValue(Constants.P_PRM_DES);
    }
    public String getIdPub() {
        return getStringValue(Constants.P_PRM_ID_PUB);
    }

    public Long getId() {
        return getLongValue(Constants.P_PRM_ID);
    }

    public String extractValueDes(final int idx){
        final List<BSCSModel> values = getListOfBSCSModelValue(Constants.P_MULT_VALUES, BSCSModel.class);
        if(idx<values.size()){
            return values.get(idx).getStringValue(Constants.P_VALUE_DES);
        }
        return null;
    }

    public String extractValue(final int idx){
        final List<BSCSModel> values = getListOfBSCSModelValue(Constants.P_MULT_VALUES, BSCSModel.class);
        if(idx<values.size()){
            return values.get(idx).getStringValue(Constants.P_VALUE);
        }
        return null;
    }

    public List<ParamValue> extractParamValues(){
        final List<ParamValue> result = new ArrayList<BSCSContractServiceParameter.ParamValue>();
        final List<BSCSModel> values = getListOfBSCSModelValue(Constants.P_MULT_VALUES, BSCSModel.class);
        for(final BSCSModel val : values){
            result.add(new ParamValue(val.getLongValue(Constants.P_VALUE_SEQNO), val.getStringValue(Constants.P_VALUE), val.getStringValue(Constants.P_VALUE_DES), val.getStringValue(Constants.P_VALUE_PUB)));
        }
        return result;
    }


    public static class ParamValue extends BSCSModel{
        public ParamValue(){
            super();
        }

        public ParamValue(final Long seq, final String value, final String des, final String pub) {
            super();
            setSeqNo(seq);
            setValue(value);
            setValueDes(des);
            setValuePub(pub);
        }

        public Long getSeqNo() {
            return getLongValue(Constants.P_SEQNO);
        }
        public String getValue() {
            return getStringValue(Constants.P_VALUE);
        }
        public String getValueDes() {
            return getStringValue(Constants.P_VALUE_DES);
        }
        public String getValuePub() {
            return getStringValue(Constants.P_VALUE_PUB);
        }

        public void setSeqNo(final Long value) {
            setLongValue(Constants.P_SEQNO, value);
        }
        public void setValue(final String value){
            setStringValue(Constants.P_VALUE, value);
        }

        public void setValueDes(final String valueDes){
            setStringValue(Constants.P_VALUE_DES, valueDes);
        }

        public void setValuePub(final String valuePub){
            setStringValue(Constants.P_VALUE_PUB, valuePub);
        }

    }

    public static class TargetParamValue extends BSCSModel{
        public TargetParamValue(final Long targetSiblingNo,
                final List<ParamValue> multValues) {
            super();
            setTargetSiblingNo(targetSiblingNo);
            setMultValues(multValues);
        }

        public void setTargetSiblingNo(final Long targetSiblingNo) {
            setLongValue(Constants.P_TARGET_SIBLING_NO, targetSiblingNo);
        }

        public Long getTargetSiblingNo(){
            return getLongValue(Constants.P_TARGET_SIBLING_NO);
        }

        public void setMultValues(final List<ParamValue> multValues){
            setListOfBSCSModelValue(Constants.P_MULT_VALUES, multValues);
        }

        public List<BSCSModel> getMultValues(){
            return getListOfBSCSModelValue(Constants.P_MULT_VALUES, BSCSModel.class);
        }


    }
}
