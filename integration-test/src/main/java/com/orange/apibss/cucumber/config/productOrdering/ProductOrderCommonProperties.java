package com.orange.apibss.cucumber.config.productOrdering;

import com.orange.apibss.productOrdering.model.Product;
import com.orange.apibss.productOrdering.model.ProductOffering;

import lombok.Data;

/**
 * Friend and family offer properties
 *
 * @author Thibault Duperron
 */
@Data
public class ProductOrderCommonProperties {
    private Product validProduct;

    private ProductOffering validProductOffering;
    
    private ProductOffering initialProductOffering;

    private Product invalidProduct;

}
