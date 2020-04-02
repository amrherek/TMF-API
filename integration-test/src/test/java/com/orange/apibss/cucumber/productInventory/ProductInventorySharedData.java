package com.orange.apibss.cucumber.productInventory;

import com.orange.apibss.productInventory.model.Product;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Thibault Duperron
 */
@Component
// New bean for each scenario
@Scope("cucumber-glue")
@Data
public class ProductInventorySharedData {

    private List<Product> products;
    
    private Product product;
}
