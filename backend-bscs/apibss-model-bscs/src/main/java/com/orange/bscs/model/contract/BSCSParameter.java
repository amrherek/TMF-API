package com.orange.bscs.model.contract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

/**
 * Represente la description d'un parameter dans le réferentiel BSCS (et non un
 * parametre au niveau d'un service.)
 *
 * @author IT&Labs
 * @version $Id: $
 */
public class BSCSParameter extends BSCSModel {

    private static final String MANDATORY = "MANDATORY_VALUE";
    private static final String MULTIVAL = "MULT_VALUE_IND";
    private static final String MAX_VALUES = "MAX_VALUES";
    private static final String NUM_CH_PRM = "NUM_CH_PRM";

    private Map<String, String> cacheValues;
    private List<BSCSModel> values;

    /**
     * <p>getId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getId() {
        return getLongValue(Constants.P_PRM_ID);
    }

    /**
     * <p>setId.</p>
     *
     * @param id a long.
     */
    public void setId(long id) {
        setLongValue(Constants.P_PRM_ID, id);
    }

    /**
     * <p>getIdPub.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getIdPub() {
        return getStringValue(Constants.P_PRM_ID_PUB);
    }

    /**
     * <p>setIdPub.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setIdPub(String value) {
        setStringValue(Constants.P_PRM_ID_PUB, value);
    }

    /**
     * <p>getNumber.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getNumber() {
        return getLongValue(Constants.P_PRM_NO);
    }

    /**
     * <p>setNumero.</p>
     *
     * @param prmno a {@link java.lang.Long} object.
     */
    public void setNumero(Long prmno) {
        setLongValue(Constants.P_PRM_NO, prmno);
    }

    /**
     * <p>getParameterType.</p>
     *
     * @return a {@link com.orange.bscs.model.contract.EnumParameterType} object.
     */
    public EnumParameterType getParameterType() {
        return EnumParameterType.parseString(getStringValue(Constants.P_TYPE));
    }

    /**
     * <p>setParameterType.</p>
     *
     * @param t a {@link com.orange.bscs.model.contract.EnumParameterType} object.
     */
    public void setParameterType(EnumParameterType t) {
        setStringValue(Constants.P_TYPE, t.toString());
    }

    /**
     * <p>getDataType.</p>
     *
     * @return a {@link com.orange.bscs.model.contract.EnumParameterDataType} object.
     */
    public EnumParameterDataType getDataType() {
        return EnumParameterDataType.parseString(getStringValue(Constants.P_DATA_TYPE));
    }

    /**
     * <p>setDataType.</p>
     *
     * @param t a {@link com.orange.bscs.model.contract.EnumParameterDataType} object.
     */
    public void setDataType(EnumParameterDataType t) {
        setStringValue(Constants.P_DATA_TYPE, t.toString());
    }

    /**
     * <p>getDescription.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getDescription() {
        return getStringValue(Constants.P_PRM_DES);
    }

    /**
     * <p>setDescription.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setDescription(String value) {
        setStringValue(Constants.P_PRM_DES, value);
    }

    /**
     * <p>getNbMaxValues.</p>
     *
     * @return a int.
     */
    public int getNbMaxValues() {
        return getIntegerValue(MAX_VALUES);
    }

    /**
     * <p>setNbMaxValues.</p>
     *
     * @param nb a int.
     */
    public void setNbMaxValues(int nb) {
        setIntegerValue(MAX_VALUES, nb);
    }


    /**
     * <p>Getter for the field <code>values</code>.</p>
     *
     * @return a {@link java.util.Map} object.
     */
    public Map<String, String> getValues() {
        if (null != cacheValues) {
            return cacheValues;
        }

        Map<String, String> res;
        switch (getParameterType()) {
        case COMPLEX:
            res= getValuesChoise();
            break;
        case CHECKBOX:
        case LISTBOX:
            res = getValuesList();
            break;

        case DATAFIELD:
            res = getValuesDate();
            break;
        default:
            // DB
            res = new HashMap<String, String>();
            break;
        }

        cacheValues = res;
        return cacheValues;
    }

    private Map<String,String> getValuesChoise(){
        Map<String, String> res = new HashMap<String, String>();
        List<BSCSModel> choises = getListOfBSCSModelValue(NUM_CH_PRM);
        if (null != choises) {
            for (BSCSModel chose : choises) {
                res.put(chose.getLongValue("CH_PRM_ID").toString(), chose.getStringValue("CH_PRM_DES"));
            }
        }
        return res;
    }
    
    private Map<String,String> getValuesList(){
        Map<String, String> res = new HashMap<String, String>();
        values = getListOfBSCSModelValue(Constants.P_N_VALUES);
        if (null != values) {
            for (BSCSModel val : values) {
                res.put(val.getLongValue(Constants.P_VALUE_SEQNO).toString(), val.getStringValue(Constants.P_VALUE_DES));
            }
        }
        return res;
    }
    
    private Map<String, String> getValuesDate(){
        Map<String, String> res = new HashMap<String, String>();
        // [0]VALUE_DES = Min value : java.lang.String
        // [0]-[0]VALUE = : java.lang.String or VALUE=AAAAA or
        // VALUE=1000000000 or VALUE= 01/01/0002 00:00:00
        // [1]VALUE_DES = Max value : java.lang.String
        // [1]VALUE = : java.lang.String

        // Create format if : 2 Values not identicals
        values = getListOfBSCSModelValue(Constants.P_N_VALUES);
        if (null != values && 2 == values.size()) {
            String v1 = values.get(0).getStringValue(Constants.P_VALUE);
            String d1 = values.get(0).getStringValue(Constants.P_VALUE_DES);
            String v2 = values.get(1).getStringValue(Constants.P_VALUE);
            String d2 = values.get(1).getStringValue(Constants.P_VALUE_DES);

            if (null != v1 && !v1.equals(v2)) {
                res.put(v1, d1);
                res.put(v2, d2);
            }
        }
        return res;
    }
    /**
     * <p>isMandatory.</p>
     *
     * @return a boolean.
     */
    public boolean isMandatory() {
        boolean ismandatory = false;
        Boolean b = getBooleanValue(MANDATORY);
        if (null != b) {
            ismandatory = b.booleanValue();
        }
        return ismandatory;
    }

    /**
     * <p>setIsMandatory.</p>
     *
     * @param value a boolean.
     */
    public void setIsMandatory(boolean value) {
        setBooleanValue(MANDATORY, value);
    }

    /**
     * <p>isMultiValues.</p>
     *
     * @return a boolean.
     */
    public boolean isMultiValues() {
        boolean ismandatory = false;
        Boolean b = getBooleanValue(MULTIVAL);
        if (null != b) {
            ismandatory = b.booleanValue();
        }
        return ismandatory;
    }

    /**
     * <p>setIsMultiValues.</p>
     *
     * @param value a boolean.
     */
    public void setIsMultiValues(boolean value) {
        setBooleanValue(MULTIVAL, value);
    }
    
    // added by Prabhakar
    public List<BSCSModel> getValueDescriptions(){
    	List<BSCSModel> valueDes = getListOfBSCSModelValue(Constants.P_N_VALUES);
    	return valueDes;
    }

}
