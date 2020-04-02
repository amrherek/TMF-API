package com.orange.apisbss.common.configuration.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.orange.apibss.common.configuration.APIConfigurationResolver;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:apibss-configuration-test.xml")
public class APIConfigurationTest {

	@Autowired
	@Qualifier("APIConfigurationResolverDefault")
	private APIConfigurationResolver apiConfiguration;
	
	@Autowired
	@Qualifier("configurationHolderTest")
	private TestConfigurationHolder configurationHolderTest;
	
	@Autowired
	@Qualifier("configurationHolderTestConstantes")
	private TestConfigurationHolder configurationHolderTestConstante;
	
	@Test
	public void checkConfigurationHolderTest() {
		Assert.assertEquals("readonly",
				configurationHolderTest.getFileBackendFactoryRacine());
		Assert.assertEquals("/mock/",
				configurationHolderTest.getFileBackendFactoryMode());		
	}
	
	@Test
	public void checkConfigurationHolderConstateTest() {
		Assert.assertEquals("constante.readonly",
				configurationHolderTestConstante.getFileBackendFactoryRacine());
		Assert.assertEquals("constante./mock/",
				configurationHolderTestConstante.getFileBackendFactoryMode());		
	}

	@Test
	public void getPropertyFromFile1Test() {
		Assert.assertEquals("recBackendFactory",
				apiConfiguration.getString("backendFactory"));
	}

	@Test
	public void getPropertyFromFile2Test() {
		Assert.assertEquals("1", apiConfiguration.getString("targetSiblingNo"));
	}

	@Test
	public void getPropertyFromFile2AsIntTest() {
		Assert.assertEquals(1, apiConfiguration.getInt("targetSiblingNo"));
	}

	@Test
	public void getFormattedMessageTest1() {
		Assert.assertEquals("message code 1",
				apiConfiguration.getMessage("CODE1", new Object[] {}));
		Assert.assertEquals("message code 2",
				apiConfiguration.getMessage("CODE2", new Object[] {}));
		Assert.assertEquals("message code test",
				apiConfiguration.getMessage("CODE3", new Object[] { "test" }));
		Assert.assertEquals("message code 4",
				apiConfiguration.getMessage("CODE4", new Object[] {}));
		Assert.assertEquals("message code test2",
				apiConfiguration.getMessage("CODE5", new Object[] { "test2" }));
	}

	@Test
	public void getFormattedMessageTest2() {
		Assert.assertEquals("message code 1",
				apiConfiguration.getMessage("CODE1", new Object[] {}));
		Assert.assertEquals("message code 2",
				apiConfiguration.getMessage("CODE2", new Object[] {}));
		Assert.assertEquals("message code test",
				apiConfiguration.getMessage("CODE3", new Object[] { "test" }));
		Assert.assertEquals("message code 4",
				apiConfiguration.getMessage("CODE4", new Object[] {}));
		Assert.assertEquals("message code test2",
				apiConfiguration.getMessage("CODE5", new Object[] { "test2" }));
	}
	

}
