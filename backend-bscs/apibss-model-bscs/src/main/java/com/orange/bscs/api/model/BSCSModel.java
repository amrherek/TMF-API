package com.orange.bscs.api.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.orange.bscs.api.model.exception.SOIException;

/**
 * A BSCSModel object contains a list of (key, value) 2-uples.
 * <p>
 * It aims at storing the inputs/outputs of a SOI command. It actually
 * encapsulates an SVLObject object, proprietary of LHS.
 *
 * @author shamzi
 */
public class BSCSModel {
    private static final Logger LOG = LoggerFactory.getLogger(BSCSModel.class);

    private static final String EXCEPTION_IN_BSCSMODEL = "Exception occured in BSCSModel";

    private SVLObjectWrapper svlObject;

    /**
     * Default constructor, create a SVLObject
     */
    public BSCSModel() {
        svlObject = AbstractSVLObjectFactory.createSVLObject();
    }

    /**
     * Change the encapsulated SVLObject containing all properties.
     *
     * @param svlObject the new SVLObject. Should/Must not be null.
     */
    public BSCSModel(SVLObjectWrapper svlObject) {
        this.svlObject = svlObject;
    }

    /**
     * @return the encapsulated/internal SVLObject containing all properties.
     */
    public SVLObjectWrapper getSVLObject() {
        return svlObject;
    }

    /**
     * @param svlObject replace current SVLObject by this.
     * @return this object
     */
    public final BSCSModel setSVLObject(SVLObjectWrapper svlObject) {
        this.svlObject = svlObject;
        return this;
    }


    /**
     * @param name the Property name
     * @return a stored Boolean value (or null if the property value is not a
     * Boolean)
     */
    public Boolean getBooleanValue(String name) {
        Boolean value = null;
        try {
            value = svlObject.getBooleanValue(name);
        } catch (Exception e) {
            LOG.error(EXCEPTION_IN_BSCSMODEL, e);
        }
        return value;
    }

    /**
     * Store a boolean value. Don't reset/remove the property if the value
     * argument is null.
     *
     * @param name  the Property name
     * @param value value to store.
     */
    public <T extends BSCSModel> T setBooleanValue(String name, Boolean value) {
        if (value != null) {
            this.svlObject.setValue(name, value.booleanValue());
        }
        return (T) this;
    }

    /**
     * @param name the Property name
     * @return the char property
     */
    public Character getCharacterValue(String name) {
        return svlObject.getCharValue(name);
    }

    /**
     * Store a char property. Don't reset/remove the property if the value
     * argument is null.
     *
     * @param name  the property name
     * @param value the character to store
     */
    public <T extends BSCSModel> T setCharacterValue(String name, Character value) {
        if (value != null) {
            this.svlObject.setValue(name, value.charValue());
        }
        return (T) this;
    }

    /**
     * @param name the Property name
     * @return to value of an int property.
     */
    public Integer getIntegerValue(String name) {
        return this.svlObject.getIntValue(name);
    }

    /**
     * Store an int property Don't reset/remove actual value if value argument
     * is null.
     *
     * @param name  the property name
     * @param value the value to store.
     */
    public <T extends BSCSModel> T setIntegerValue(String name, Integer value) {
        if (value != null) {
            this.svlObject.setValue(name, value.intValue());
        }
        return (T) this;
    }

    /**
     * @param name the Property name
     * @return the value of a Long property (null if the property is not a Long)
     */
    public Long getLongValue(String name) {
        return this.svlObject.getLongValue(name);
    }

    /**
     * Store a Long value. Don't reset/remove property if value argument is
     * null.
     *
     * @param name  the property name
     * @param value the value.
     */
    public <T extends BSCSModel> T setLongValue(String name, Long value) {
        if (value != null) {
            this.svlObject.setValue(name, value.longValue());
        }
        return (T) this;
    }

    /**
     * @param name the Property name
     * @return the value (if stored as a double).
     */
    public Double getDoubleValue(String name) {
        return this.svlObject.getFloatValue(name);
    }

    /**
     * Store a double property. Don't reset/remove actual value if argument is
     * null.
     *
     * @param name  the property name
     * @param value the value to store.
     */
    public <T extends BSCSModel> T setDoubleValue(String name, Double value) {
        if (value != null) {
            this.svlObject.setValue(name, value.doubleValue());
        }
        return (T) this;
    }

    /**
     * @param name the property name
     * @return the value stored as a String.
     */
    public String getStringValue(String name) {
        return this.svlObject.getStringValue(name);
    }

    /**
     * @param name
     *            the property name
     * @return the value stored as a String if not empty.
     */
    public String getStringNotEmpty(String name) {
        String s = this.svlObject.getStringValue(name);
        return (s == null || s.isEmpty()) ? null : s;
    }

    /**
     * Store a string property. Don't reset actual value if argument is null.
     *
     * @param name
     *            the property name
     * @param value
     *            the value to store
     */
    public <T extends BSCSModel> T setStringValue(String name, String value) {
        if (value != null) {
            this.svlObject.setValue(name, value);
        }
        return (T) this;
    }

    /**
     * Force Reset of this Attribut (set Null value)
     */
    public <T extends BSCSModel> T setNullStringValue(String name) {
        this.svlObject.setNullStringValue(name);
        return (T) this;
    }


    /**
     * @param name the Property name
     * @return a stored SVLDate value.
     */
    public Date getDateValue(String name) {
        return svlObject.getDateValue(name);
    }

    /**
     * Store a date, loosing Time part of the date. Don't reset/remove actual
     * value if argument is null.
     *
     * @param name  the property name
     * @param value the date value
     */
    public <T extends BSCSModel> T setDateValue(String name, Date value) {
        if (value != null) {
            this.svlObject.setValue(name, value);
        }
        return (T) this;
    }

    /**
     * @param name the Property name
     * @return a Date property (if stored as SVLDateTime)
     */
    public Date getDateTimeValue(String name) {
        return svlObject.getDateTimeValue(name);
    }

    /**
     * Store a Date property (don't loose time part of the date)
     *
     * @param name  the property name
     * @param value the Date to store (don't reset actual value if this date is
     *              null).
     */
    public <T extends BSCSModel> T setDateTimeValue(String name, Date value) {
        if (value != null) {
            this.svlObject.setValueDateTime(name, value);
        }
        return (T) this;
    }

    /**
     * @param name the Property name
     * @return the Amount part of a SVLMoney property
     */
    public Double getMoneyAmountValue(String name) {
        return this.svlObject.getMoneyValue(name);
    }

    /**
     * @param name the Property name
     * @return the currency code part of a stored SVLMoney property
     */
    public String getMoneyCurrencyCodeValue(String name) {
        return this.svlObject.getMoneyCurrencyCode(name);
    }

    /**
     * @param name  the property name
     * @param value the amount to store (the currencyCode will be "").
     */
    public <T extends BSCSModel> T setMoneyValue(String name, Double value) {
        if (value != null) {
            this.svlObject.setValue(name, value, "");
        }
        return (T) this;
    }

    /**
     * @param name
     *            the property name
     * @param value
     *            the amount to store
     * @param currency
     *            the currencyCode to store
     */
    public <T extends BSCSModel> T setMoneyValue(String name, Double value, String currency) {
        if (value != null) {
            this.svlObject.setValue(name, value, currency);
        }
        return (T) this;
    }

    /**
     * @param name the Property name
     * @return a BSCSModel constructed from a SVLObject stored as a property
     */
    public BSCSModel getBSCSModelValue(String name) {
        return getBSCSModelValue(name, BSCSModel.class);
    }

    public <T extends BSCSModel> T getBSCSModelValue(String name, Class<T> clazz) {
        return this.svlObject.getBSCSModelValue(name, clazz);
    }

    /**
     * @param name  the property name
     * @param value an object to store as SVLObject property.
     *              if null, unset attribut but not send Null value.
     */
    public <T extends BSCSModel> void setBSCSModelValue(String name, T value) {
        if (value == null) {
            this.svlObject.removeAttribute(name);
            return;
        }
        this.svlObject.setValue(name, value.getSVLObject());
    }


    /**
     * Return native List, catching Exception
     */
    public SVLObjectListWrapper getSVLObjectList(String name) {
        return this.svlObject.getSVLObjectList(name);
    }

    /**
     * Returns a list contained in this BSCSModel, as a List of BSCSModel
     *
     * @param name Name of the list this BSCSModel
     * @return
     */
    public List<BSCSModel> getListOfBSCSModelValue(String name) {
        return getListOfBSCSModelValue(name, BSCSModel.class);
    }

    /**
     * Returns a list contained in this BSCSModel, as a List of <T extends
     * BSCSModel>.
     * <p>
     * Returns an empty list if there was no result.
     *
     * @param <T>  class of return type
     * @param name Name of the list this BSCSModel
     * @return
     * @throws com.orange.bscs.api.model.exception.APIException , IllegalArgumentException
     */
    public <T extends BSCSModel> List<T> getListOfBSCSModelValue(String name, Class<T> outClass) {

        List<T> list = new ArrayList<T>();
        SVLObjectListWrapper svlol = getSVLObjectList(name);
        if (svlol.isEmpty()) {
            return list;
        }
        int size = svlol.size();
        for (int i = 0; i < size; i++) {
            T value = svlol.get(i).newInstance(outClass);
            list.add(value);
        }
        return list;
    }

    /**
     * Returns a list contained in this BSCSModel, as a List of <T extends
     * BSCSModel>.
     * <p>
     * Returns an empty list if there was no result.
     *
     * @param <T>  class of return type
     * @param name Name of the list this BSCSModel
     * @return
     * @throws com.orange.bscs.api.model.exception.APIException , IllegalArgumentException
     */
    public <T extends BSCSModel> List<T> getListOfBSCSIvoiceModelValue(String name, Class<T> outClass, Integer wishedResults) {

        List<T> list = new ArrayList<T>();
        SVLObjectListWrapper svlol = getSVLObjectList(name);
        if (svlol.isEmpty()) {
            return list;
        }

        int size = svlol.size();
        //It seems that the CMS command dose not process the wishedResults correctly, this condition is to limit the result set to the desired number
        if (wishedResults != null && wishedResults > 0 && wishedResults < size)
            size = wishedResults;

        for (int i = 0; i < size; i++) {
            T value = svlol.get(i).newInstance(outClass);
            list.add(value);
        }
        return list;
    }

    /**
     * Construct an store a SVLObjectList property by adding SVLObject contains
     * in each value of the input list. don't reset actual value if the values
     * argument is Null.
     *
     * @param name   the Property name
     * @param values a list of BSCSModel object.
     */
    public <T extends BSCSModel> T setListOfBSCSModelValue(String name, List<? extends BSCSModel> values) {
        if (values != null) {
            SVLObjectListWrapper svlol = this.svlObject.newSVLObjectList();
            for (BSCSModel bscsModel : values) {
                svlol.add(bscsModel.getSVLObject());
            }
            this.svlObject.setValue(name, svlol);
        } else {
            this.svlObject.removeAttribute(name);
        }
        return (T) this;
    }

    /**
     * @param name the property name
     * @return the value stored as binary form.
     */
    public byte[] getBinaryValue(String name) {
        return this.svlObject.getBinaryValue(name);
    }

    /**
     * @param name  the property name
     * @param value store the array (don't reset actual value if the array
     *              argument is null).
     */
    public <T extends BSCSModel> T setBinaryValue(String name, byte[] value) {
        if (value == null) {
            this.removeAttribute(name);
        } else {
            this.svlObject.setValue(name, value);
        }
        return (T) this;
    }

    /**
     * Return the list of keys stored in this BSCSModel
     *
     * @return
     */
    public List<String> getKeyList() {
        return Arrays.asList(this.svlObject.getAttributeNames());
    }

    /**
     * Remove a property value (can't use setXXValue(null) for that).
     *
     * @param name the property name to remove.
     */
    public void removeAttribute(String name) {
        svlObject.removeAttribute(name);
    }

    /**
     * @return a copy of this object (call svlObject.clone())
     */
    public <T extends BSCSModel> T cloneModel() {
        T newT;
        try {
            newT = (T) this.getClass().newInstance();
            newT.setSVLObject(this.svlObject.clone());
        } catch (InstantiationException e) {
            throw new SOIException(String.format("Fail to clone/instantiate : %s", this.toString()), e);
        } catch (IllegalAccessException e) {
            throw new SOIException(String.format("Fail to clone: %s", this.toString()), e);
        } catch (CloneNotSupportedException e) {
            throw new SOIException(String.format("Fail to clone : %s", this.toString()), e);
        }
        return newT;
    }

    /**
     * Returns the current instance as a given subtype.
     *
     * @param type
     * @return
     */
    public <T extends BSCSModel> T as(Class<T> type) {
        return this.svlObject.newInstance(type);
    }

    public boolean isEmpty() {
        return null == svlObject || svlObject.isEmpty();
    }

    /**
     * Pretty print this object with values of internal svlObject
     */
    @Override
    public String toString() {
        if (null == svlObject) {
            return super.toString();
        }

        String fullId = super.toString();
        if (fullId.indexOf('.') > 0) {
            fullId = fullId.substring(fullId.lastIndexOf('.') + 1);
        }
        return fullId + "{" + svlObject.toString() + "}";
    }

    /**
     * Create a new instance of a BSCSModel or BSCSModel subclass.
     * <p>
     * Change : detect a constructor take much more time than creating a
     * BSCSModel (and a SVLObject in default constructor) and settings/replacing svlobject
     * eg : 70 < 280/600 ms depending on which constructor exist.
     * <p>
     * Nota : it is best if custom BSCSModel doesn't declare any constructor (50 < 70 ms).
     *
     * @param clazz  new instance Class.
     * @param source internal svlObject of the new instance.
     * @return the new instance.
     * @throws IllegalArgumentException
     */
    public static <T extends BSCSModel> T newInstance(Class<T> clazz, SVLObjectWrapper source) {
        T result = null;
        try {
            result = clazz.newInstance();
            SVLObjectWrapper svlo = source;
            if (null == svlo) {
                svlo = AbstractSVLObjectFactory.createSVLObject();
            }
            result.setSVLObject(svlo);
        } catch (SecurityException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        } catch (InstantiationException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
        return result;
    }

    public boolean match(BSCSModel input) {
        for (String key : input.getKeyList()) {

            Object value = getSVLObject().getValue(key);
            if (value == null || !value.equals(input.getSVLObject().getValue(key))) {
                return false;
            }
        }
        return true;
    }
}
