package com.orange.apibss.common.utils.transformer;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.stereotype.Component;

import com.orange.apibss.common.exceptions.badrequest.BadParameterFormatException;

/**
 * <h1>ConvertDateToXmlGregorianCalendar</h1>
 * <p>
 * <p>
 * ConvertDateToXmlGregorianCalendar action will convert to date at
 * XMLGregorianCalendar format , entry Date and exit
 * ConvertDateToXmlGregorianCalendar ...
 * </p>
 *
 * @author ihab.bensouda
 */
@Component
public class ConvertDateToXmlGregorianCalendar {

    /**
     * Format date
     *
     * @param date
     * @return XMLGregorianCalendar
     * @throws BadParameterFormatException
     */
    public XMLGregorianCalendar getDateXMLGregorianCalendar(Date date) throws BadParameterFormatException {
        XMLGregorianCalendar xmlGregorianCalendar = null;
        if (date != null) {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(date);
            try {
                xmlGregorianCalendar = DatatypeFactory.newInstance()
                        .newXMLGregorianCalendar(gregorianCalendar);
            } catch (DatatypeConfigurationException datatypeConfigurationException) {
                throw new BadParameterFormatException(datatypeConfigurationException.getMessage());
            }
        }
        return xmlGregorianCalendar;
    }

}