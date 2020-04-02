package com.orange.apibss.cucumber.config;

import java.util.List;

import com.orange.apibss.cucumber.config.productInventory.Category;
import com.orange.apibss.cucumber.config.productInventory.IccId;
import com.orange.apibss.cucumber.config.productInventory.Msisdn;
import com.orange.apibss.cucumber.config.productInventory.PartyId;
import com.orange.apibss.cucumber.config.productInventory.ProductStatus;
import com.orange.apibss.productInventory.model.Product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Properties for product inventory tests
 * 
 * @author Thibault Duperron
 *
 */
@ToString
public class ProductInventoryProperties {
	/**
	 * Msisdn properties
	 */
	@Getter
	@Setter
	private Msisdn msisdn;
	/**
	 * Iccid properties
	 */
	@Getter
	@Setter
	private IccId iccId;
	/**
	 * partyId properties
	 */
	@Getter
	@Setter
	private PartyId partyId;
	/**
	 * Category properties
	 */
	@Getter
	@Setter
	private Category category;
	/**
	 * ProductStatus properties
	 */
	@Getter
	@Setter
	private ProductStatus productStatus;

	/**
	 * Product with contracts
	 */
	@Getter
	@Setter
	public List<Product> productWithContracts;
	
	/**
     * FaF Product
     */
    @Getter
    @Setter
    public List<Product> productFaf;
    
    /**
     * Service with parameter product
     */
    @Getter
    @Setter
    public Product serviceWithParameter;
    
    /**
     * Transfer data Products
     */
    @Getter
    @Setter
    public List<Product> productTransferData;
    
    /**
     * Transfer credit Products
     */
    @Getter
    @Setter
    public List<Product> productTransferCredit;
    
    /**
     * Data Bundle Products
     */
    @Getter
    @Setter
    public List<Product> productDataBundle;
}
