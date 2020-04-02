package com.orange.apibss.cucumber.config.productInventory;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Properties for ProductStatus
 * 
 * @author Thibault Duperron
 *
 */
@ToString
public class ProductStatus {
	/**
	 * ProductStatus with contracts
	 */
	@Getter
	@Setter
	public String withContracts;
	/**
	 * ProductStatus with no contracts
	 */
	@Getter
	@Setter
	public String withoutContracts;

	/**
	 * ProductStatus not well formated
	 */
	@Getter
	@Setter
	public String invalid;

}
