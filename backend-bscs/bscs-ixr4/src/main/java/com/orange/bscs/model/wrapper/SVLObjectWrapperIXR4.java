package com.orange.bscs.model.wrapper;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lhs.ccb.common.soi.ExchangeFormatFactory;
import com.lhs.ccb.common.soi.InvalidParameterTypeException;
import com.lhs.ccb.common.soi.SVLDate;
import com.lhs.ccb.common.soi.SVLDateTime;
import com.lhs.ccb.common.soi.SVLMoney;
import com.lhs.ccb.common.soi.SVLNullConversion;
import com.lhs.ccb.common.soi.SVLObject;
import com.lhs.ccb.common.soi.SVLObjectList;
import com.lhs.ccb.common.soi.SVLType;
import com.lhs.ccb.common.soi.SignatureMismatchException;
import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.model.SVLObjectListWrapper;
import com.orange.bscs.api.model.SVLObjectWrapper;

/**
 * Created by deyb6792 on 12/08/2016.
 */
public class SVLObjectWrapperIXR4 implements SVLObjectWrapper {

    private SVLObject svlObject;
    private static final Logger LOG = LoggerFactory.getLogger(BSCSModel.class);

    private static final String EXCEPTION_IN_BSCSMODEL = "Exception occured in BSCSModel";


    public SVLObjectWrapperIXR4() {
        this.svlObject = ExchangeFormatFactory.instance().createSVLObject();
    }

    public SVLObjectWrapperIXR4(SVLObject svlObject) {
        this.svlObject = svlObject;
    }

    public SVLObject getSVLObject() {
        return this.svlObject;
    }

    @Override
    public void setValue(String name, boolean b) {
        this.svlObject.setValue(name, b);
    }

    @Override
    public void setValue(String name, char b) {
        this.svlObject.setValue(name, b);
    }

    @Override
    public void setValue(String name, int i) {
        this.svlObject.setValue(name, i);
    }

    @Override
    public void setValue(String name, long l) {
        this.svlObject.setValue(name, l);
    }

    @Override
    public void setValue(String name, double v) {
        this.svlObject.setValue(name, v);
    }

    @Override
    public void setValue(String name, String value) {
        this.svlObject.setValue(name, value);
    }

    @Override
    public void setNullStringValue(String name) {
        this.svlObject.setValue(name, SVLNullConversion.STRING_NULL_VALUE);
    }
    
    @Override
    public void setNullLongValue(String name) {
        this.svlObject.setValue(name, SVLNullConversion.LONG_NULL_VALUE);
    }

    @Override
    public void setValue(String name, Date date) {
        Date localDate = null;
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.setTimeZone(TimeZone.getTimeZone("UTC"));
            if (cal.get(Calendar.HOUR) == 0 && cal.get(Calendar.MINUTE) == 0 && cal.get(Calendar.SECOND) == 0
                    && cal.get(Calendar.MILLISECOND) == 0) {
                // date is 00:00 in UTC => convert it to 00:00 local timezone
                cal.setTimeZone(TimeZone.getDefault());
                int tzOffset = -(cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET)) / (1000 * 60);
                cal.add(Calendar.MINUTE, tzOffset);
                localDate = cal.getTime();
            } else {
                // It's a dateTime, do not convert it
                localDate = date;
            }
        }
        this.svlObject.setValue(name, ExchangeFormatFactory.instance().createSVLDate(localDate));
    }

    @Override
    public void setValueDateTime(String name, Date dateTime) {
        this.svlObject.setValue(name, ExchangeFormatFactory.instance().createSVLDateTime(dateTime));
    }

    @Override
    public void setValue(String name, Double value, String currency) {
        this.svlObject.setValue(name, ExchangeFormatFactory.instance().createSVLMoney(value, currency));
    }

    @Override
    public void setValue(String name, SVLObjectWrapper svlObject) {
        this.svlObject.setValue(name, ((SVLObjectWrapperIXR4)svlObject).getSVLObject());
    }

    @Override
    public void setValue(String name, SVLObjectListWrapper svlol) {
        this.svlObject.setValue(name, ((SVLObjectListWrapperIXR4)svlol).getSVLObjectList());
    }

    @Override
    public void setValue(String name, Object value) {
        this.svlObject.setValue(name, value);
    }

    @Override
    public void setValue(String name, byte[] value) {
        this.svlObject.setValue(name, value);
    }

    @Override
    public Boolean getBooleanValue(String name) {
        Boolean value = null;
        try {
            value = svlObject.getBooleanValue(name);
        } catch (InvalidParameterTypeException e) {
            LOG.error(EXCEPTION_IN_BSCSMODEL, e);
        }
        return value;
    }

    @Override
    public Character getCharValue(String name) {
        Character valueC = null;
        try {
            char value = svlObject.getCharValue(name);
            valueC = SVLNullConversion.isNull(value) ? null : value;
        } catch (InvalidParameterTypeException e) {
            LOG.error(EXCEPTION_IN_BSCSMODEL, e);
        }
        return valueC;
    }

    @Override
    public Integer getIntValue(String name) {
        Integer value = null;
        try {
            int valueL = svlObject.getIntValue(name);
            value = SVLNullConversion.isNull(valueL) ? null : valueL;
        } catch (InvalidParameterTypeException e) {
            LOG.error(EXCEPTION_IN_BSCSMODEL, e);
        }
        return value;
    }

    @Override
    public Long getLongValue(String name) {
        Long value = null;
        try {
            long valueL = svlObject.getLongValue(name);
            value = SVLNullConversion.isNull(valueL) ? null : valueL;
        } catch (InvalidParameterTypeException e) {
            LOG.error(EXCEPTION_IN_BSCSMODEL, e);
        }
        return value;
    }

    @Override
    public Double getFloatValue(String name) {
        Double value = null;
        try {
            double valueL = svlObject.getFloatValue(name);
            value = SVLNullConversion.isNull(valueL) ? null : valueL;
        } catch (InvalidParameterTypeException e) {
            LOG.error(EXCEPTION_IN_BSCSMODEL, e);
        }
        return value;
    }

    @Override
    public String getStringValue(String name) {
        String value = null;
        try {
            value = svlObject.getStringValue(name);
            // Attention: LHS considers empty strings as null strings, so we
            // need a special treatment here
            if (value == null && svlObject.getSVLType(name) == SVLType.SVL_STRING) {
                // if true, then we have an empty string
                value = "";
            }
        } catch (InvalidParameterTypeException e) {
            LOG.error(EXCEPTION_IN_BSCSMODEL, e);
        } catch (SignatureMismatchException e) {
            LOG.error(EXCEPTION_IN_BSCSMODEL, e);
        }
        return value;
    }

    @Override
    public Date getDateValue(String name) {
        Date value = null;
        try {
            SVLDate valueL = svlObject.getDateValue(name);
            if (!SVLNullConversion.isNull(valueL)) {
                // convert to 00:00 UTC Date
                Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                cal.set(valueL.getYear(), valueL.getMonth(), valueL.getDay(), 0, 0, 0);
                cal.set(Calendar.MILLISECOND, 0);
                value = cal.getTime();
            }
        } catch (InvalidParameterTypeException e) {
            LOG.error(EXCEPTION_IN_BSCSMODEL, e);
        }
        return value;

    }

    @Override
    public Date getDateTimeValue(String name) {
        Date value = null;
        try {
            SVLDateTime valueL = svlObject.getDateTimeValue(name);
            value = SVLNullConversion.isNull(valueL) ? null : valueL.getDateTime();
        } catch (InvalidParameterTypeException e) {
            LOG.error(EXCEPTION_IN_BSCSMODEL, e);
        }
        return value;
    }

    @Override
    public Double getMoneyValue(String name) {
        Double value = null;
        try {
            SVLMoney valueL = svlObject.getMoneyValue(name);
            value = SVLNullConversion.isNull(valueL) ? null : valueL.getAmount();
        } catch (InvalidParameterTypeException e) {
            LOG.error(EXCEPTION_IN_BSCSMODEL, e);
        }
        return value;
    }

    @Override
    public String getMoneyCurrencyCode(String name) {
        String value = null;
        try {
            SVLMoney valueL = svlObject.getMoneyValue(name);
            value = SVLNullConversion.isNull(valueL) ? null : valueL.getCurrencyCode();
        } catch (InvalidParameterTypeException e) {
            LOG.error(EXCEPTION_IN_BSCSMODEL, e);
        }
        return value;
    }

    @Override
    public SVLObjectListWrapper getSVLObjectList(String name) {
        SVLObjectList result;
        try {
            result = svlObject.getSVLObjectList(name);
            if (SVLNullConversion.isNull(result)) {
                result = ExchangeFormatFactory.instance().createSVLObjectList();
            }
        } catch (InvalidParameterTypeException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
        return new SVLObjectListWrapperIXR4(result);
    }

    @Override
    public byte[] getBinaryValue(String name) {
        byte[] value = null;
        try {
            byte[] valueL = svlObject.getBinaryValue(name);
            value = SVLNullConversion.isNull(valueL) ? null : valueL;
        } catch (InvalidParameterTypeException e) {
            LOG.error(EXCEPTION_IN_BSCSMODEL, e);
        }
        return value;
    }

    @Override
    public boolean isEmpty() {
        return this.svlObject.isEmpty();
    }

    @Override
    public void removeAttribute(String name) {
        this.svlObject.removeAttribute(name);
    }

    @Override
    public String[] getAttributeNames() {
        return this.svlObject.getAttributeNames();
    }

    @Override
    public SVLObjectWrapper clone() throws CloneNotSupportedException {
        return new SVLObjectWrapperIXR4((SVLObject) this.svlObject.clone());
    }

    @Override
    public <T extends BSCSModel> T getBSCSModelValue(String name, Class<T> clazz) {
        T value = null;
        try {
            SVLObject svlo = svlObject.getSVLObject(name);
            // Attention: svlObject.getSVLObject(name) also returns null when
            // the underlying SVLObject is empty
            if (svlo == null && svlObject.getSVLType(name) == SVLType.SVL_LIST) {
                // Note: SVLType.SVL_LIST is the SVLObject type...
                svlo = ExchangeFormatFactory.instance().createSVLObject();

            }
            if (svlo != null) {
                value = newInstance(clazz, svlo);
            }
        } catch (InvalidParameterTypeException e) {
            LOG.error("Exception occured in BSCSModel.getBSCSModelValue('" + name + "')", e);
        } catch (SignatureMismatchException e) {
            LOG.error("SignatureMismatchException occured in BSCSModel.getBSCSModelValue('" + name + "')", e);
        }
        return value;
    }

    @Override
    public <T extends BSCSModel> T newInstance(Class<T> outClass) {
        return newInstance(outClass, this.getSVLObject());
    }

    @Override
    public SVLObjectListWrapper newSVLObjectList() {
        return new SVLObjectListWrapperIXR4(ExchangeFormatFactory.instance().createSVLObjectList());
    }

    @Override
    public Object getValue(String s) {
        return svlObject.getValue(s);
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
    public static <T extends BSCSModel> T newInstance(Class<T> clazz, SVLObject source) {
        T result;
        try {
            result = clazz.newInstance();
            SVLObject svlo = source;
            if (null == svlo) {
                svlo = ExchangeFormatFactory.instance().createSVLObject();
            }
            result.setSVLObject(new SVLObjectWrapperIXR4(svlo));
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public String toString() {
        return this.svlObject.toString();
    }
}
