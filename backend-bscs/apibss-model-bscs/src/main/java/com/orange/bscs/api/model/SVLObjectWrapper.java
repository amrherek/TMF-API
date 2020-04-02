package com.orange.bscs.api.model;

import java.util.Date;

/**
 * An interface to wrap different SVLObject version (IXr4 and V8)
 */
public interface SVLObjectWrapper {

    void setValue(String name, boolean b);
    void setValue(String name, char b);
    void setValue(String name, int i);
    void setValue(String name, long l);
    void setNullLongValue(String name);
    void setValue(String name, double v);
    void setValue(String name, String value);
    void setNullStringValue(String name);
    void setValue(String name, Date date);
    void setValueDateTime(String name, Date dateTime);
    void setValue(String name, Double value, String currency);
    void setValue(String name, SVLObjectWrapper svlObject);
    void setValue(String name, SVLObjectListWrapper svlol);
    void setValue(String name, Object value);

    void setValue(String name, byte[] value);

    Boolean getBooleanValue(String name);

    Character getCharValue(String name);

    Integer getIntValue(String name);

    Long getLongValue(String name);

    Double getFloatValue(String name);

    String getStringValue(String name);

    Date getDateValue(String name);

    Date getDateTimeValue(String name);

    Double getMoneyValue(String name);

    String getMoneyCurrencyCode(String name);

    SVLObjectListWrapper getSVLObjectList(String name);

    byte[] getBinaryValue(String name);

    boolean isEmpty();


    void removeAttribute(String name);

    String[] getAttributeNames();

    SVLObjectWrapper clone() throws CloneNotSupportedException;

    <T extends BSCSModel> T getBSCSModelValue(String name, Class<T> clazz);

    <T extends BSCSModel> T newInstance(Class<T> outClass);

    SVLObjectListWrapper newSVLObjectList();

    Object getValue(String s);

    @Override
    String toString();
}
