package com.orange.apibss.common.configuration;

import java.util.Locale;

/**
 * This interface is used to resolve the message needed for the APIBSS.
 * @author hakim.hejam
 */
public interface APIMessageResolver  {

	String getMessage(String code, Object[] args);
	
    String getMessage(String code, Object[] args, String defaultMessage);

	String getMessage(String code, Object[] args, String defaultMessage, Locale locale);

	String getMessage(String code, Object[] args, Locale locale);
	

}
