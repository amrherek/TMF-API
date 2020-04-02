package com.orange.bscs.api.command;

import java.security.NoSuchAlgorithmException;

import com.orange.bscs.api.model.AbstractSVLObjectFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.Assert;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.model.factory.SVLObjectFactoryIXR4;

public class BaseCommandTest {

	@BeforeClass
	public static void initExchange() throws NoSuchAlgorithmException{
		AbstractSVLObjectFactory.init(new SVLObjectFactoryIXR4());
	}
	
	@Test
	public void testBaseCommand(){
		TestCommand tst = new TestCommand("NOM", 456L);
		
		InModel in = tst.getInput();
		OutModel out = tst.getOutput();
		
		Assert.assertEquals("Compare Command",TestCommand.CMD, tst.getCommand());
		Assert.assertEquals("Compare In","NOM", in.getNom());
		Assert.assertEquals("Compare Out",Long.valueOf(456L), out.getId());
	}
	
	/** Compile time check existance of accessors */
	@Test
	public void accessorsTest(){
		TestCommand tst = new TestCommand("NOM", 456L);

		tst.setInput(tst.getInput());
		tst.setOutput(tst.getOutput());
		
		Assert.assertEquals("Compare In","NOM", tst.getInput().getNom());
		Assert.assertEquals("Compare Out",Long.valueOf(456L), tst.getOutput().getId());
		
	}
	
	
	class TestCommand extends BaseCommand<InModel, OutModel>{
		public static final String CMD ="COMMAND.TEST";
		
		TestCommand(String name, long id){
			setInput(new InModel(name));
			setOutput(new OutModel(id));
		}
		
		@Override
		public String getCommand() {
			return CMD;
		}
		
	}
	
	class InModel extends BSCSModel{

		public InModel(String name){
			setStringValue("NOM", name);
		}
		
		public String getNom() { return getStringValue("NOM");}
		public void setNom(String name){ setStringValue("NOM", name);}
	}
	
	class OutModel extends BSCSModel{
		
		public OutModel(long id){ 
			setLongValue("ID", id);
		}
		
		public Long getId(){ return getLongValue("ID");}
		public void setId(Long id){ setLongValue("ID", id);}
		
	}
}
