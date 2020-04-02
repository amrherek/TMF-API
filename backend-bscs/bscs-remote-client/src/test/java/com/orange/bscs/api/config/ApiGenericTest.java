package com.orange.bscs.api.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.MissingResourceException;

import org.junit.Assert;
import org.junit.Test;

public class ApiGenericTest {

	@Test
	public void loadPropertyTest(){
		
		// Force to check location with classpath and .properties
		ApiGeneric.setLocation("classpath:ApiGenericTest.properties");
		
		Assert.assertEquals("chaine", ApiGeneric.getProperty("StringProperty"));
		Assert.assertEquals(Integer.valueOf(123), ApiGeneric.getIntegerProperty("IntProperty"));
		Assert.assertEquals(Long.valueOf(456), ApiGeneric.getLongProperty("LongProperty"));
		Assert.assertEquals(Boolean.TRUE, ApiGeneric.getBooleanProperty("BoolProperty"));
		Assert.assertEquals(Character.valueOf('C'), ApiGeneric.getCharacterProperty("CharProperty"));
	}
	
	@Test
	public void bscsProperties(){
		List<String> locations = new ArrayList<String>();
		locations.add("apibscs-default");
		locations.add("ApiGenericTest");
		
		ApiGeneric.setLocations(locations);
		
		Assert.assertNotNull(ApiGeneric.getBscsApplicationID());
		Assert.assertNotNull(ApiGeneric.getBscsLogin());
		Assert.assertNotNull(ApiGeneric.getBscsBusinessUnit());
	}
	
	
	@Test(expected=MissingResourceException.class)
	public void notResource(){
        ApiGeneric.setLocations(Arrays.asList("NOTEXIST"));
        
        Assert.assertNotNull(ApiGeneric.getBscsLogin());
	}
}
