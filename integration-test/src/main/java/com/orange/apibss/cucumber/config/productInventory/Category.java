package com.orange.apibss.cucumber.config.productInventory;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Properties for Category
 * 
 * @author Thibault Duperron
 *
 */
@ToString
public class Category {
	/**
	 * Category with contracts
	 */
	@Getter
	@Setter
	public String withContracts;
	/**
	 * Category with no contracts
	 */
	@Getter
	@Setter
	public String withoutContracts;

	/**
	 * Category not well formated
	 */
	@Getter
	@Setter
	public String invalid;

}
