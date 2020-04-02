package com.orange.bscs.model.businesspartner;

/**
 * Class representing individual titles stored in BSCS
 * 
 * @author xbbs3851
 *
 */
public class IndividualTitle {
	
	private Long code;
	private String name;
	
	public IndividualTitle(Long code, String name) {
		this.code = code;
		this.name = name;
	}

	public Long getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

}
