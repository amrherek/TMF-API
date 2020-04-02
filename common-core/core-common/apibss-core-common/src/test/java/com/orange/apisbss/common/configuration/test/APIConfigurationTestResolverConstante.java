package com.orange.apisbss.common.configuration.test;

import com.orange.apibss.common.configuration.impl.APIConfigurationResolverDefault;

public class APIConfigurationTestResolverConstante extends
		APIConfigurationResolverDefault {

	@Override
	public String getString(String key) {
		return "constante." + super.getString(key);
	}
}
	