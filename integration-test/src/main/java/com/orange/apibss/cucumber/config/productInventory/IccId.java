package com.orange.apibss.cucumber.config.productInventory;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Properties for IccId
 * 
 * @author Thibault Duperron
 *
 */
@ToString
public class IccId {

	/**
	 * IccId with contracts
	 */
	@Getter
	@Setter
	public String withContracts;
	/**
	 * IccId with no contracts
	 */
	@Getter
	@Setter
	public String withoutContracts;

	/**
	 * IccId not well formated
	 */
	@Getter
	@Setter
	public String invalid;
	
	/**
     * IccId partial with *
     */
    @Getter
    @Setter
    public String partial;

}
