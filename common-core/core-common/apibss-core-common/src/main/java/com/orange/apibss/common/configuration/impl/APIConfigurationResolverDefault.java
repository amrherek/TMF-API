package com.orange.apibss.common.configuration.impl;

import java.math.BigDecimal;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.orange.apibss.common.configuration.APIConfigurationResolver;
import com.orange.apibss.common.configuration.APIMessageResolver;

/**
 * A implementation of the interface {@link APIConfigurationResolver} that use
 * the spring context to resolve properties.
 * 
 * @author hakim.hejam
 */
// FIXME Remove ?
public class APIConfigurationResolverDefault implements
		APIConfigurationResolver {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(APIConfigurationResolver.class);
	
	private APIMessageResolver messageResolver;
	
	private SpringPropertiesExposer springPropertiesExposer;

	@Override
	public String getString(String key) {
		String value = springPropertiesExposer.getProperty(key);
		LOGGER.debug("The value for the key {} is {}", key, value);
		return value;
	}

	@Override
	public String getString(String key, String defaultValue) {

		String value = getString(key);
		if (value == null) {
			value = defaultValue;
		}
		return value;
	}

	@Override
	public String getMessage(String code, Object[] args) {
		return messageResolver.getMessage(code, args);
	}

    @Override
    public String getMessage(String code, Object[] args, String defaultMessage) {
        return messageResolver.getMessage(code, args, defaultMessage);
    }

	@Override
	public String getMessage(String code, Object[] args, String defaultMessage,
			Locale locale) {
		return messageResolver.getMessage(code, args, defaultMessage, locale);
	}

	@Override
	public String getMessage(String code, Object[] args, Locale locale) {
		return messageResolver.getMessage(code, args, locale);
	}

	@Override
	public boolean getBoolean(String key) {
		String value = getString(key);
		return Boolean.valueOf(value);
	}

	@Override
	public double getDouble(String key) {
		String value = getString(key);
		return Double.valueOf(value);
	}

	@Override
	public float getFloat(String key) {
		String value = getString(key);
		return Float.valueOf(value);
	}

	@Override
	public int getInt(String key) {
		String value = getString(key);
		return Integer.valueOf(value);
	}

	@Override
	public long getLong(String key) {
		String value = getString(key);
		return Long.valueOf(value);
	}

	@Override
	public short getShort(String key) {
		String value = getString(key);
		return Short.valueOf(value);
	}

	@Override
	public BigDecimal getBigDecimal(String key) {
		String value = getString(key);
		return new BigDecimal(value);
	}

	/**
	 * @return the messageResolver
	 */
	public APIMessageResolver getMessageResolver() {
		return messageResolver;
	}

	/**
	 * @param messageResolver the messageResolver to set
	 */
	public void setMessageResolver(APIMessageResolver messageResolver) {
		this.messageResolver = messageResolver;
	}

	/**
	 * @return the springPropertiesExposer
	 */
	public SpringPropertiesExposer getSpringPropertiesExposer() {
		return springPropertiesExposer;
	}

	/**
	 * @param springPropertiesExposer the springPropertiesExposer to set
	 */
	public void setSpringPropertiesExposer(
			SpringPropertiesExposer springPropertiesExposer) {
		this.springPropertiesExposer = springPropertiesExposer;
	}
	
	
	
}
