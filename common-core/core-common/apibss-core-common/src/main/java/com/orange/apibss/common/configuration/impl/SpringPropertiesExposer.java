package com.orange.apibss.common.configuration.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

//FIXME Remove ?
public class SpringPropertiesExposer extends PropertyPlaceholderConfigurer {

	private Map<String, String> propertiesMap;
    // Default as in PropertyPlaceholderConfigurer
    private int springSystemPropertiesMode = SYSTEM_PROPERTIES_MODE_FALLBACK;

	public SpringPropertiesExposer() {
		propertiesMap = new HashMap<String, String>();
	}

    @Override
    public void setSystemPropertiesMode(int systemPropertiesMode) {
        super.setSystemPropertiesMode(systemPropertiesMode);
        springSystemPropertiesMode = systemPropertiesMode;
    }

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {
        super.processProperties(beanFactory, props);

        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String valueStr = resolvePlaceholder(keyStr, props, springSystemPropertiesMode);
            propertiesMap.put(keyStr, valueStr);
        }
    }

    public  String getProperty(String name) {
        return propertiesMap.get(name);
    }

}