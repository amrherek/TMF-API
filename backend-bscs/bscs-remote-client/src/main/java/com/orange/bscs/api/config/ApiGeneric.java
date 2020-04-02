package com.orange.bscs.api.config;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class used to retrieve properties store in different bundles:
 * defaults are : 
 * 	apibscs-default
 *  apibscs-current. 
 * 
 * Not used by Framework but exposed to other type of application (soicli by example).
 * 
 * @author IT&Labs
 *
 */
public final class ApiGeneric {

    private static final Logger LOG = LoggerFactory.getLogger(ApiGeneric.class);

    public static final String APPLICATION_CONFIG_BUNDLE_NAME = "apibscs-default";

    private static ResourceBundle bundle;

    static{
        try {
            bundle = PropertyResourceBundle.getBundle(APPLICATION_CONFIG_BUNDLE_NAME, Locale.getDefault());
        } catch(Exception e){
            // Do nothing
            LOG.debug("WARN, {} not found : {}", APPLICATION_CONFIG_BUNDLE_NAME, e);
        }
    }

    public static final String LOGIN = "bscs.login";
    public static final String APPLID = "bscs.applicationid";
    public static final String BUSINESS_UNIT = "bscs.bu";


    private static final String WARN_PROPERTY_NOT_FOUND = "WARN, property {} not found : {}";
    private static final String WARN_PROPERTY_NOT_NUMERIC = "WARN, property {} not numeric : {}";


    public static String getBscsLogin(){ 
        return getProperty(LOGIN);
    }

    public static String getBscsApplicationID(){ 
        return getProperty(APPLID);
    }

    public static String getBscsBusinessUnit(){ 
        return getProperty(BUSINESS_UNIT);
    }


    private ApiGeneric(){
        // Hide utility class constructor
    }


    /**
     * Returns a property value from the application config file (api-generic.properties)
     * as a String
     * 
     * @param propertyName
     * @return
     */
    public static String getProperty(String propertyName, String... propertyArgs)  {
        String res=null;
        if(null==propertyName || null==bundle){
            return res;
        }
        try {
            res= MessageFormat.format(bundle.getString(propertyName), (Object[]) propertyArgs);
        } catch(IllegalArgumentException e){
            LOG.trace(WARN_PROPERTY_NOT_FOUND, propertyName,e);
        } catch(MissingResourceException e){
            LOG.trace(WARN_PROPERTY_NOT_FOUND, propertyName,e);
        } catch(ClassCastException e){
            LOG.trace(WARN_PROPERTY_NOT_FOUND, propertyName,e);
        }
        return res;
    }

    /**
     * Returns a property value from the application config file (api-generic.properties)
     * as a Long
     * 
     * @param propertyName
     * @return
     */
    public static Long getLongProperty(String propertyName, String... propertyArgs) {
        try {
            return Long.parseLong(getProperty(propertyName, propertyArgs));
        } catch (NumberFormatException nfe){
            LOG.trace(WARN_PROPERTY_NOT_NUMERIC, propertyName,nfe);
        }
        return null;
    }

    /**
     * Returns a property value from the application config file (api-generic.properties)
     * as an Integer
     * 
     * @param propertyName
     * @return
     */
    public static Integer getIntegerProperty(String propertyName, String... propertyArgs) {
        try {
            return Integer.parseInt(getProperty(propertyName, propertyArgs));
        } catch (NumberFormatException nfe){
            LOG.trace(WARN_PROPERTY_NOT_NUMERIC, propertyName,nfe);
        }
        return null;
    }

    /**
     * Returns a property value from the application config file (api-generic.properties)
     * as a Boolean
     * 
     * @param propertyName
     * @return
     */
    public static Boolean getBooleanProperty(String propertyName, String... propertyArgs) {
        return Boolean.parseBoolean(getProperty(propertyName, propertyArgs));
    }

    /**
     * Returns a property value from the application config file (api-generic.properties)
     * as a Character
     * 
     * @param propertyName
     * 
     * @return First caractere of property value.
     */
    public static Character getCharacterProperty(String propertyName, String... propertyArgs) {
        String value = getProperty(propertyName, propertyArgs);
        if(null!=value && 1<= value.length()){
            return Character.valueOf(value.charAt(0));
        }
        return null;
    }


    /**
     * 
//	 * @param locations Comma separed list of properties/bundle file
     */
    public static void setLocation(String locations){
        if(null==locations || 0==locations.length()){
            return;
        }
        String[] aLocations = locations.split(",");
        List<String> lLocations = new ArrayList<String>();
        for(String loc : aLocations){
            loc=loc.trim();
            if(StringUtils.isNotEmpty(loc)){
                lLocations.add(loc);
            }
        }
        setLocations(lLocations);
    }


    /**
     * @param locations list of bundles
     */
    public static void setLocations(List<String> locations){
        if(null==locations || locations.isEmpty()){
            return;
        }
        bundle = PropertyResourceBundle.getBundle(extractBaseName(locations.get(0)), Locale.getDefault());
        for(int l=1; l<locations.size();l++){
            bundle = APIGenericResourceBundle.getBundle(extractBaseName(locations.get(l)), bundle);
        }
    }

    /**
     * Remove "classpath:" and ".property" from location.
     * 
     * @param location One location (may be an absolute path file:/D:/...)
     * 
     * @return a basename accepted by ResourceBundle framework.
     */
    public static String extractBaseName(final String location){
        final String classpath = "classpath:";
        final String properties = ".properties";

        String baseName=location;
        if(null==baseName){
            return null;
        }
        if(baseName.endsWith(properties)){
            baseName=baseName.substring(0,baseName.indexOf(properties));
        }
        if(baseName.startsWith(classpath)){
            baseName = baseName.substring(classpath.length());
        }
        return baseName;
    }
}

