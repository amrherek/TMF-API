package com.orange.apibss.cucumber.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.orange.apibss.productCatalog.model.ProductOffering;

import lombok.Data;

/**
 * Properties for product catalog tests
 *
 * @author Stéphanie Gerbaud
 */
@Data
@ConfigurationProperties(prefix = "productCatalog", ignoreUnknownFields = false)
public class ProductCatalogProperties {
    
    private Id offerProductOfferingId;
    
    private Id productOffering;
    
    private List<ProductOffering> productOfferingsForMigration;
    
    private List<ProductOffering> productOfferingsForCompatibility;
    
    private List<ProductOffering> productOfferingsForFafConfiguration;
    
    private ProductOffering productOfferingForConfiguration;

}
