package com.orange.apibss.cucumber.productOrdering;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.orange.apibss.productOrdering.model.OrderItem;
import com.orange.apibss.productOrdering.model.ProductOrder;

import lombok.Data;

/**
 * @author Thibault Duperron
 */
@Component
// New bean for each scenario
@Scope("cucumber-glue")
@Data
public class ProductOrderingSharedData {

    /**
     * ProductOrder result of an API call
     */
    private ProductOrder productOrder;

    /**
     * ProductOrder use for calling the API (create, update)
     */
    private ProductOrder testProductOrder;

    /**
     * OrderItem use for creating the API call
     */
    private OrderItem testOrderItem;
}
