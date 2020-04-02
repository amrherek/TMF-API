package com.orange.bscs.model.wrapper;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.model.SVLObjectListWrapper;
import com.orange.bscs.api.model.SVLObjectWrapper;
import com.semagroup.targys.framework.common.InvalidParameterTypeException;
import com.semagroup.targys.framework.common.SVLDate;
import com.semagroup.targys.framework.common.SVLDateTime;
import com.semagroup.targys.framework.common.SVLMoney;
import com.semagroup.targys.framework.common.SVLNullConversion;
import com.semagroup.targys.framework.common.SVLObject;
import com.semagroup.targys.framework.common.SVLObjectList;
import com.semagroup.targys.servicelayer.common.NVExchangeFormatFactory;

/**
 * V8 impl of {@link com.orange.bscs.api.model.SVLObjectWrapper}
 */
public class SVLObjectWrapperV8 implements SVLObjectWrapper {

	private int timezoneOffset;

    public SVLObject getSVLObject() {
        return svlObject;
    }

    private final SVLObject svlObject;
    private static final Logger LOG = LoggerFactory.getLogger(BSCSModel.class);

    private static final String EXCEPTION_IN_BSCSMODEL = "Exception occured in BSCSModel";

	public SVLObjectWrapperV8(SVLObject svlObject, int timezoneOffset) {
        this.svlObject = svlObject;
		this.timezoneOffset = timezoneOffset;
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
        this.svlObject.setValue(name, NVExchangeFormatFactory.instance().createSVLDate(date));
    }

    @Override
    public void setValueDateTime(String name, Date dateTime) {
        this.svlObject.setValue(name, NVExchangeFormatFactory.instance().createSVLDateTime(dateTime));
    }

    @Override
    public void setValue(String name, Double value, String currency) {
        this.svlObject.setValue(name, NVExchangeFormatFactory.instance().createSVLMoney(value, currency));
    }

    @Override
    public void setValue(String name, SVLObjectWrapper svlObject) {
        this.svlObject.setValue(name, ((SVLObjectWrapperV8)svlObject).getSVLObject());
    }

    @Override
    public void setValue(String name, SVLObjectListWrapper svlol) {
        this.svlObject.setValue(name, ((SVLObjectListWrapperV8)svlol).getSVLObjectList());
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
            if (value == null) {
                // if true, then we have an empty string
                value = "";
            }
        } catch (InvalidParameterTypeException e) {
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
                // transform BSCS Date in midnight UTC Date
                Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
                cal.setTime(valueL.getDate());
                // round to nearest 00:00 day in UTC calendar (to ignore local
                // timezone offset is less than 12h)
                cal = DateUtils.round(cal, Calendar.DAY_OF_MONTH);
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

			if (!SVLNullConversion.isNull(valueL)) {
				value = new Date();
				// fix bug on BSCS v8 timezone
				// add bscs offset
				value.setTime(valueL.getDateTime().getTime() + 60 * 60 * 1000 * timezoneOffset);
				if (timezoneOffset != 0) {
					Calendar cal = Calendar.getInstance();
					cal.setTime(valueL.getDateTime());
					int calendarOffset = (cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET))
							/ (60 * 60 * 1000);
					// local date
					value.setTime(value.getTime() - 60 * 60 * 1000 * calendarOffset);
				}
			}
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
                result = NVExchangeFormatFactory.instance().createSVLObjectList();
            }
        } catch (InvalidParameterTypeException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
        return new SVLObjectListWrapperV8(result, timezoneOffset);
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
		return new SVLObjectWrapperV8((SVLObject) this.svlObject.clone(), this.timezoneOffset);
    }

    @Override
    public <T extends BSCSModel> T getBSCSModelValue(String name, Class<T> clazz) {
        T value = null;
        try {
            SVLObject svlo = svlObject.getSVLObject(name);
            // Attention: svlObject.getSVLObject(name) also returns null when
            // the underlying SVLObject is empty
            if (svlo != null) {
				value = newInstance(clazz, svlo, timezoneOffset);
            }
        } catch (InvalidParameterTypeException e) {
            LOG.error("Exception occured in BSCSModel.getBSCSModelValue('" + name + "')", e);
        }
        return value;
    }

    @Override
    public <T extends BSCSModel> T newInstance(Class<T> outClass) {
		return newInstance(outClass, this.getSVLObject(), timezoneOffset);
    }

    @Override
    public SVLObjectListWrapper newSVLObjectList() {
		return new SVLObjectListWrapperV8(NVExchangeFormatFactory.instance().createSVLObjectList(), timezoneOffset);
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
	public static <T extends BSCSModel> T newInstance(Class<T> clazz, SVLObject source, int timezoneOffset) {
        T result;
        try {
            result = clazz.newInstance();
            SVLObject svlo = source;
            if (null == svlo) {
                svlo = NVExchangeFormatFactory.instance().createSVLObject();
            }
			result.setSVLObject(new SVLObjectWrapperV8(svlo, timezoneOffset));
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public String toString() {
        return this.svlObject == null ? "null" : this.svlObject.toString();
    }
}
