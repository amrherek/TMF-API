package com.orange.api.standard.bindings;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author jwck2987
 */
public class CustomDateTimeAdapter extends XmlAdapter<String, Date> {

    @Override
    public String marshal(Date date) throws Exception {
        if (date == null) {
            return null;
        }
        // UTC representation
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        cal.setTime(date);
        return DatatypeConverter.printDateTime(cal);

    }

    @Override
    public Date unmarshal(String date) throws Exception {
        return DatatypeConverter.parseDateTime(date).getTime();
    }
}