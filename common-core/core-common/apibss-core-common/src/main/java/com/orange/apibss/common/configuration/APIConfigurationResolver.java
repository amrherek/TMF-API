package com.orange.apibss.common.configuration;

import java.math.BigDecimal;

/**
 * This interface is the same as the {@link Configuration} but remove
 * the method that return a configuration object.
 * @author hakim.hejam
 */
public interface APIConfigurationResolver extends APIMessageResolver  {

   /**
     * Get a string associated with the given configuration key.
     *
     * @param key The configuration key.
     * @return The associated string.
     *
     */
    String getString(String key);


    /**
     * Get a string associated with the given configuration key.
     * If the key doesn't map to an existing object, the default value
     * is returned.
     *
     * @param key The configuration key.
     * @param defaultValue The default value.
     * @return The associated string if key is found and has valid
     *         format, default value otherwise.
     *
     */
    String getString(String key, String defaultValue);


    /**
     * Get a boolean associated with the given configuration key.
     *
     * @param key The configuration key.
     * @return The associated boolean.
     *
     * @throws ConversionException is thrown if the key maps to an
     *         object that is not a Boolean.
     */
    boolean getBoolean(String key);


    /**
     * Get a double associated with the given configuration key.
     *
     * @param key The configuration key.
     * @return The associated double.
     *
     * @throws ConversionException is thrown if the key maps to an
     *         object that is not a Double.
     */
    double getDouble(String key);


    /**
     * Get a float associated with the given configuration key.
     *
     * @param key The configuration key.
     * @return The associated float.
     * @throws ConversionException is thrown if the key maps to an
     *         object that is not a Float.
     */
    float getFloat(String key);

   

    /**
     * Get a int associated with the given configuration key.
     *
     * @param key The configuration key.
     * @return The associated int.
     *
     * @throws ConversionException is thrown if the key maps to an
     *         object that is not a Integer.
     */
    int getInt(String key);


    /**
     * Get a long associated with the given configuration key.
     *
     * @param key The configuration key.
     * @return The associated long.
     *
     * @throws ConversionException is thrown if the key maps to an
     *         object that is not a Long.
     */
    long getLong(String key);

    /**
     * Get a short associated with the given configuration key.
     *
     * @param key The configuration key.
     * @return The associated short.
     *
     * @throws ConversionException is thrown if the key maps to an
     *         object that is not a Short.
     */
    short getShort(String key);


    /**
     * Get a {@link BigDecimal} associated with the given configuration key.
     *
     * @param key The configuration key.
     * @return The associated BigDecimal if key is found and has valid format
     */
    BigDecimal getBigDecimal(String key);


}
