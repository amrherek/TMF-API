package com.orange.apibss.common.message.formatter;

import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodeFormatter;

import com.orange.apibss.common.configuration.APIMessageResolver;

public class APIMessageCodeFormatter implements MessageCodeFormatter {

	private APIMessageResolver messageResolver;
	
	/**
	 * Build and return a message code consisting of the given fields, usually delimited
	 * by {@link DefaultMessageCodesResolver#CODE_SEPARATOR}.
	 * @param errorCode e.g.: "typeMismatch"
	 * @param objectName e.g.: "user"
	 * @param field e.g. "age"
	 * @return concatenated message code, e.g.: "typeMismatch.user.age"
	 * @see DefaultMessageCodesResolver.Format
	 */
	public String format(String errorCode, String objectName, String field) {
		return messageResolver.getMessage(errorCode, new Object[]{objectName, field});
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

	
	
	
}
