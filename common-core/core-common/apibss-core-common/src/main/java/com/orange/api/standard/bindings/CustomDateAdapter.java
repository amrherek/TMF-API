package com.orange.api.standard.bindings;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author ecus6396
 */
public class CustomDateAdapter extends XmlAdapter<String, Date> {

    @Override
    public String marshal(Date date) throws Exception {
        if (date == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(date);
    }

    @Override
    public Date unmarshal(String date) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.parse(date);
    }
}