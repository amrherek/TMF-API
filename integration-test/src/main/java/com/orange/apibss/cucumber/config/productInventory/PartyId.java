package com.orange.apibss.cucumber.config.productInventory;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Properties for PartyId
 * 
 * @author Thibault Duperron
 *
 */
@ToString
public class PartyId {
	/**
	 * PartyId with contracts
	 */
	@Getter
	@Setter
	public String withContracts;
	/**
	 * PartyId with no contracts
	 */
	@Getter
	@Setter
	public String withoutContracts;

	/**
	 * PartyId not well formated
	 */
	@Getter
	@Setter
	public String invalid;

}
