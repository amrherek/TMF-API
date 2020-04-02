package com.orange.apibss.cucumber.productCatalog;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.orange.apibss.productCatalog.model.ProductOffering;

import lombok.Data;

@Component
// New bean for each scenario
@Scope("cucumber-glue")
@Data
public class ProductCatalogSharedData {

    private List<ProductOffering> productOfferings;
    
    private ProductOffering productOffering;
}
