package com.orange.bscs.api.connection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.orange.bscs.api.connection.backend.ConnectionBackendFactoryLog;
import com.orange.bscs.api.connection.backend.ConnectionBackendFactoryMock;
import com.orange.bscs.api.connection.backend.ConnectionBackendFactoryRecorder;
import com.orange.bscs.api.connection.backend.ConnectionBackendMock;
import com.orange.bscs.api.connection.backend.ConnectionBackendRecorder;
import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.test.model.BSCSAddressTst;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { SOIConnectionTest.SOIConnectionTestConfiguration.class })
@SuppressWarnings("deprecation")
public class SOIConnectionTest {

    @ComponentScan(basePackages = { "com.orange.bscs.autoconfigure" })
    @PropertySources({ @PropertySource("classpath:bscs_mock.properties") })
	static class SOIConnectionTestConfiguration {
    }
	private static final String COMMAND="ADDRESS.READ";

    private static final String CS_ID = "CS_ID";

    private static final String ADR_SEQ = "ADR_SEQ";

    private static final String ADR_ZIP = "ADR_ZIP";
	
	@Autowired
	IConnectionBackendFactory currentBackendFactory;
	
	@Autowired
	SOITransactionsManager soiTransactionsManager;

	
	
	@Test
	public void executeSVLObject() throws Exception{

		SOIConnectionImpl conn = getConnection();
		
		BSCSModel input = new BSCSModel();
		input.setLongValue(CS_ID, 542L);
		input.setLongValue(ADR_SEQ, 1L);
		
		BSCSModel output =  conn.execute(COMMAND, input);
		assertNotNull("output",output);
		
		String zip = output.getStringValue(ADR_ZIP);
		
		assertEquals("zip","45000", zip);
	}
	
	@Test
	public void executeTypedModel() throws Exception{

		SOIConnectionImpl conn = getConnection();
		
		BSCSAddressTst input = new BSCSAddressTst();
		input.setCustomerId(542L);
		input.setSequenceNumber(1L);
		
		BSCSAddressTst output = conn.execute(COMMAND, input, BSCSAddressTst.class);
		assertNotNull("output",output);
		
		String zip = output.getPostalCode();
		
		assertEquals("zip","45000", zip);
	}


	@Test
	public void testOpenCommitClose() throws Exception{

		SOIConnectionImpl conn = getConnection();
		
		assertTrue("new connection should be alive",conn.isAlive());

		conn.commit();
		assertTrue("Connection should keep alive after commit",conn.isAlive());
		
		conn.rollback();
		assertTrue("Connection should keep alive after rollback",conn.isAlive());

		conn.close();
		assertFalse("Connection should not be alive after close",conn.isAlive());
		
		// should not throw exception
		conn.commit();
		// should not throw exception
		conn.rollback();
		
		conn.reopen();
		assertTrue("Connection should be alive after reopen",conn.isAlive());
		
	}

	@Test
	public void testAccessors() throws Exception{
		SOIConnectionImpl conn = getConnection();
		
		assertNotNull("ConnectionId",conn.getConnectionId());
		assertNotNull("TransactionId",conn.getTransactionId());
		assertFalse("same connectionId", conn.getConnectionId().equals(conn.getTransactionId()));

		assertNotNull("bscsLogin", conn.getBscsUserId());
		assertNotNull("bscsBusinessUnit",conn.getBusinessUnit());
	}

	
	@Test
	public void testRetreiveBackend() throws Exception{
				
		SOIConnectionImpl conn = getConnection();

		IConnectionBackendFactory factoryMock = new ConnectionBackendFactoryMock();
		IConnectionBackendFactory factoryRec = new ConnectionBackendFactoryRecorder(factoryMock);
		IConnectionBackendFactory factoryLog = new ConnectionBackendFactoryLog(factoryRec);
		
		IConnectionBackend newBackend = factoryLog.newInstance();

		conn.setBackend(newBackend);
		
		
		IConnectionBackend backend = conn.getBackend(ConnectionBackendMock.class);
		Assert.assertNotNull(backend);
		
		backend = conn.getBackend(ConnectionBackendRecorder.class);
		Assert.assertNotNull(backend);
		
		backend = conn.getBackend(null);
		Assert.assertNull(backend);
	}

	
	
	private SOIConnectionImpl getConnection() throws Exception{
		
		String requestTransactionId=null;
		String applicationName="TESTAPPL";
		String bscsUserId="TEST";
		String bscsBusinessUnit="1";
		return soiTransactionsManager.getSOIConnection(requestTransactionId, applicationName, bscsUserId,
				bscsBusinessUnit);
	}
	
	@Before
	public void initMockDatas() throws ParseException{
		BSCSAddressTst expected = new BSCSAddressTst();
		expected.setCustomerId(542L);
		expected.setSequenceNumber(1L);
		expected.setCustomerIdPub("CUST00000470");
		expected.setFirstName("Marjorie");
		expected.setLastName("LUCZAK");
		expected.setBirthDate( new SimpleDateFormat("dd/MM/yyy").parse("10/02/1965"));
		expected.setPostalCode("45000");
		expected.setCity("ORLEANS");
	

		ConnectionBackendFactoryMock mock = (ConnectionBackendFactoryMock) currentBackendFactory;
		mock.clearCommands();
		mock.addCommand("ADDRESS.READ", expected.getSVLObject());
	}
}
