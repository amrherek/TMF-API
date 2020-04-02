package com.orange.apibss.common.configuration.impl;

import java.util.Locale;

import org.springframework.context.MessageSource;

import com.orange.apibss.common.configuration.APIMessageResolver;

//FIXME Remove ?
public class APIMessageResolverDefault implements APIMessageResolver {
	
	private MessageSource messageSource;
	
	private static final Locale DEFAUTL_LOCAL = Locale.getDefault();
	
	private Locale defaultLocale = DEFAUTL_LOCAL;

	@Override
	public String getMessage(String code, Object[] args) {
		return this.messageSource.getMessage(code, args, defaultLocale);
	}

    @Override
    public String getMessage(String code, Object[] args, String defaultMessage) {
        return this.messageSource.getMessage(code, args, defaultMessage, defaultLocale);
    }

	@Override
	public String getMessage(String code, Object[] args, String defaultMessage,
			Locale locale) {
		return this.messageSource.getMessage(code, args, defaultMessage, locale == null ? defaultLocale : locale);
	}

	@Override
	public String getMessage(String code, Object[] args, Locale locale) {
		return this.messageSource.getMessage(code, args, locale == null ? defaultLocale : locale);
	}
	

	/**
	 * @return the messageSource
	 */
	public MessageSource getMessageSource() {
		return messageSource;
	}
	

	/**
	 * @param messageSource the messageSource to set
	 */
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/**
	 * @return the defaultLocale
	 */
	public Locale getDefaultLocale() {
		return defaultLocale;
	}

	/**
	 * @param defaultLocale the defaultLocale to set
	 */
	public void setDefaultLocale(Locale defaultLocale) {
		this.defaultLocale = defaultLocale;
	}


	
	

}
