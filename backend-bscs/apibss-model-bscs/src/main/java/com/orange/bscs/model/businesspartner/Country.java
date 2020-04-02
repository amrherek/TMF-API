package com.orange.bscs.model.businesspartner;

/**
 * 
 * Class representing countries stored in BSCS
 * 
 * @author xbbs3851
 *
 */
public class Country {
	
	private Long code;
	private String name;
	// iso 3166-1 alpha 2 (2 letters)
	private String iso2;
	
	
	public Country(Long code, String name, String iso2) {
		this.code = code;
		this.name = name;
		this.iso2 = iso2;
	}
	
	public Long getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getIso2() {
		return iso2;
	}

}
