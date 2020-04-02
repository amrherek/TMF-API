package com.orange.bscs.api.connection;

import com.orange.bscs.api.model.BSCSModel;

public class SOIConnectionMock implements SOIConnection{

	private int nbExecute;
	
		
		public <T extends BSCSModel> T execute(String commandName, BSCSModel input, Class<T> outputClass) {
			nbExecute++;
			return BSCSModel.newInstance(outputClass, input.getSVLObject());
		}

		
		
		public BSCSModel execute(String commandName, BSCSModel input) {
			return execute(commandName,input, BSCSModel.class);
		}

		
		public void commit() {
		}

		
		public void rollback() {
		}

		
		public String getConnectionId() {
			return null;
		}

		
		public String getTransactionId() {
			return null;
		}

		
		public String getBusinessUnit() {
			return null;
		}

		
		public IConnectionBackend getBackend() {
			return null;
		}

		
		public <T extends IConnectionBackend> T getBackend(Class<T> clazz) {
			return null;
		}
		
		
		public int getNbExecution(){
			return nbExecute;
		}
	}
