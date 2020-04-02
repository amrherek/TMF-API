package com.orange.apisbss.common.configuration.test;

public class TestConfigurationHolder {
	
	private String backendFactory;
	private String recBackendFactoryBackendFactory;
	private String fileBackendFactoryBackendFactory;
	private String fileBackendFactoryMode;
	private String fileBackendFactoryRacine;
	
	public TestConfigurationHolder() {

	}
	
	
	public TestConfigurationHolder(String fileBackendFactoryMode,
			String fileBackendFactoryRacine) {
		super();
		this.fileBackendFactoryMode = fileBackendFactoryMode;
		this.fileBackendFactoryRacine = fileBackendFactoryRacine;
	}


	public TestConfigurationHolder(String backendFactory,
			String recBackendFactoryBackendFactory,
			String fileBackendFactoryBackendFactory,
			String fileBackendFactoryMode, String fileBackendFactoryRacine) {
		super();
		this.backendFactory = backendFactory;
		this.recBackendFactoryBackendFactory = recBackendFactoryBackendFactory;
		this.fileBackendFactoryBackendFactory = fileBackendFactoryBackendFactory;
		this.fileBackendFactoryMode = fileBackendFactoryMode;
		this.fileBackendFactoryRacine = fileBackendFactoryRacine;
	}
	public String getBackendFactory() {
		return backendFactory;
	}
	public void setBackendFactory(String backendFactory) {
		this.backendFactory = backendFactory;
	}
	public String getRecBackendFactoryBackendFactory() {
		return recBackendFactoryBackendFactory;
	}
	public void setRecBackendFactoryBackendFactory(
			String recBackendFactoryBackendFactory) {
		this.recBackendFactoryBackendFactory = recBackendFactoryBackendFactory;
	}
	public String getFileBackendFactoryBackendFactory() {
		return fileBackendFactoryBackendFactory;
	}
	public void setFileBackendFactoryBackendFactory(
			String fileBackendFactoryBackendFactory) {
		this.fileBackendFactoryBackendFactory = fileBackendFactoryBackendFactory;
	}
	public String getFileBackendFactoryMode() {
		return fileBackendFactoryMode;
	}
	public void setFileBackendFactoryMode(String fileBackendFactoryMode) {
		this.fileBackendFactoryMode = fileBackendFactoryMode;
	}
	public String getFileBackendFactoryRacine() {
		return fileBackendFactoryRacine;
	}
	public void setFileBackendFactoryRacine(String fileBackendFactoryRacine) {
		this.fileBackendFactoryRacine = fileBackendFactoryRacine;
	}

	

}
