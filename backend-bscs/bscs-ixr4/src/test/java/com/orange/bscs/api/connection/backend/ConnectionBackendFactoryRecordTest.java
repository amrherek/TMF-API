package com.orange.bscs.api.connection.backend;

import java.security.NoSuchAlgorithmException;
import java.util.Set;

import com.orange.bscs.api.model.AbstractSVLObjectFactory;
import com.orange.bscs.api.model.SVLObjectWrapper;
import com.orange.bscs.model.factory.SVLObjectFactoryIXR4;

import org.junit.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import com.orange.bscs.api.command.BaseCommand;
import com.orange.bscs.api.model.BSCSModel;

public class ConnectionBackendFactoryRecordTest {
	@BeforeClass
	public static void initExchange() throws NoSuchAlgorithmException {
		AbstractSVLObjectFactory.init(new SVLObjectFactoryIXR4());
	}


	// Compilation Check that empty Constructor exists
	@Test
	public void defaultConstructor(){
		Assert.assertNotNull(new ConnectionBackendFactoryRecorder());
	}

	// Compilation Check that empty Constructor exists
	@Test
	public void sourcercConstructor(){
		Assert.assertNotNull(new ConnectionBackendFactoryRecorder(new ConnectionBackendFactoryMock()));
	}
	
	@Test
	public void checkIncr(){
		ConnectionBackendFactoryMock mockFactory = new ConnectionBackendFactoryMock();
		ConnectionBackendFactoryRecorder recFactory = new ConnectionBackendFactoryRecorder(mockFactory);
		
		mockFactory.addCommand("CONTRACT.READ", (SVLObjectWrapper) null);
		mockFactory.addCommand("CONTRACT_SERVICES.READ", (SVLObjectWrapper) null);
		mockFactory.addCommand("CONTRACT.READ", (SVLObjectWrapper) null);

		ConnectionBackendRecorder recorder = recFactory.newInstance();

		recorder.execute("CONTRACT.READ",null);
		recorder.execute("CONTRACT_SERVICES.READ",null);
		recorder.execute("CONTRACT.READ",null);
	
		Assert.assertEquals(2, recorder.retrieveNbExecution("CONTRACT.READ"));
		Assert.assertEquals(1, recorder.retrieveNbExecution("CONTRACT_SERVICES.READ"));
		
		Assert.assertTrue(recorder.containsAll("CONTRACT_SERVICES.READ","CONTRACT.READ") );
		Assert.assertFalse(recorder.containsAll("UNKNOWN.COMMAND","CONTRACT.READ") );
	}

	@Test
	public void checkRecordCommands(){
		ConnectionBackendFactoryMock mockFactory = new ConnectionBackendFactoryMock();
		ConnectionBackendFactoryRecorder recFactory = new ConnectionBackendFactoryRecorder(mockFactory);
		
		recFactory.setRecordParameters(true);
		
		ConnectionBackendMock svlCreator = mockFactory.newInstance();
		
		// because we know that mockFactory can create SVLObject
		SVLObjectWrapper o1 = svlCreator.createSVLObject();
		o1.setValue("CO_ID_PUB", "CONTR1");
		SVLObjectWrapper s1 = svlCreator.createSVLObject();
		s1.setValue("SNCODE_PUB", "SVC1");
		SVLObjectWrapper o2 = svlCreator.createSVLObject();
		o2.setValue("CO_ID_PUB", "CONTR2");
		
		mockFactory.addCommand("CONTRACT.READ", o1);
		mockFactory.addCommand("CONTRACT_SERVICES.READ", s1);
		mockFactory.addCommand("CONTRACT.READ", o2);

		ConnectionBackendRecorder recorder = recFactory.newInstance();


		SVLObjectWrapper i1 = svlCreator.createSVLObject();
		i1.setValue("CO_ID", Long.valueOf(1));
		
		recorder.execute("CONTRACT.READ",i1);
		recorder.execute("CONTRACT_SERVICES.READ",null);
		recorder.execute("CONTRACT.READ",null);
	
		Set<BaseCommand<BSCSModel,BSCSModel>> r1= recorder.getRecords("CONTRACT.READ");
		
		Assert.assertEquals(2, r1.size());
		BaseCommand<BSCSModel,BSCSModel> c1 = r1.iterator().next();
		Assert.assertEquals("Saved COMMAND name","CONTRACT.READ",c1.getCommand());
		Assert.assertEquals("Saved CT Output","CONTR1",c1.getOutput().getStringValue("CO_ID_PUB"));
		
		Set<BaseCommand<BSCSModel,BSCSModel>> r2= recorder.getRecords("CONTRACT_SERVICES.READ");
		Assert.assertEquals(1, r2.size());
		Assert.assertEquals("Saved SVC Output","SVC1",r2.iterator().next().getOutput().getStringValue("SNCODE_PUB"));

	}
	
	@Test
	public void checkDelegates(){
		ConnectionBackendFactoryMock mockFactory = new ConnectionBackendFactoryMock();
		ConnectionBackendFactoryRecorder factory = new ConnectionBackendFactoryRecorder(mockFactory);

		Assert.assertNotSame("should be different because of source",factory, factory.newInstance());

		ConnectionBackendTestMethodDelegation.callBackendMethods(factory);
		
	}
	
}
