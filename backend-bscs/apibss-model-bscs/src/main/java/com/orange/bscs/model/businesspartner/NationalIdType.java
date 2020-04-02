package com.orange.bscs.model.businesspartner;

/**
 * Class representing national ID types stored in BSCS
 * 
 * @author xbbs3851
 *
 */
public class NationalIdType {
	
	private Long code;
	private String name;

	
	public NationalIdType(Long code, String name) {
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
