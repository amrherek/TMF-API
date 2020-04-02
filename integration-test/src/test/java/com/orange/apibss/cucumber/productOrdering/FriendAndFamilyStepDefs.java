package com.orange.apibss.cucumber.productOrdering;

import org.springframework.beans.factory.annotation.Autowired;

import com.orange.apibss.cucumber.config.productOrdering.ProductOrderCommonProperties;
import com.orange.apibss.productOrdering.model.Product;

import cucumber.api.java.en.When;

/**
 * Step defs for friend and family action for product ordering API
 * @author Thibault Duperron
 */
public class FriendAndFamilyStepDefs extends ProductOrderingBaseStepDefs {

    private final String WHEN_BASE = "^With (friend and family|um13|um7) product with ";

    @Autowired
    private ProductOrderingSharedData productOrderingSharedData;

    @When(WHEN_BASE + "(valid|invalid|no) product offering$")
    public void add_a_product(final String usecase, final String productOfferingState){
        ProductOrderCommonProperties properties = getProperties(usecase);
        Product product = new Product();
        product = add_offering(product, properties, productOfferingState);
        productOrderingSharedData.getTestOrderItem().setProduct(product);
    }
    @When(WHEN_BASE + "(valid|invalid|no) product relationship$")
    public void add_a_product_relationship(final String usecase, final String productRelationshipState){
        ProductOrderCommonProperties properties = getProperties(usecase);
        Product product = new Product();
        product = add_relationship(product, properties, productRelationshipState);
        productOrderingSharedData.getTestOrderItem().setProduct(product);
    }

    @When(WHEN_BASE + "(valid|invalid|no) product offering and (valid|invalid|no) product relationship$")
    public void add_a_product(final String usecase, final String productOfferingState, final  String productRelationshipState){
        ProductOrderCommonProperties properties = getProperties(usecase);
        Product product = new Product();
        product = add_offering(product, properties, productOfferingState);
        product = add_relationship(product, properties, productRelationshipState);
        productOrderingSharedData.getTestOrderItem().setProduct(product);
    }

    @When(WHEN_BASE
            + "(valid|invalid|no) contract, (valid|invalid|no) product characteristic, (valid|invalid|no) product relationship and (valid|invalid|no) product specification$")
    public void add_a_related_individual(final String usecase, final String contractState,
            final String productCharacterisitcState, final String productRelationshipState,
            final String productSpecificationState) {
        ProductOrderCommonProperties properties = getProperties(usecase);
        Product product = new Product();
        product = add_contract(product, properties, contractState);
        product = add_characteristic(product, properties, productCharacterisitcState);
        product = add_relationship(product, properties, productRelationshipState);
        product = add_spec(product, properties, productSpecificationState);
        productOrderingSharedData.getTestOrderItem().setProduct(product);
    }

    @When(WHEN_BASE + "(valid|invalid|no) contract, (valid|invalid|no) product characteristic and (valid|invalid|no) product relationship$")
    public void add_a_related_individual(final String usecase, final String contractState,final  String productCharacterisitcState,final  String productRelationshipState){
        ProductOrderCommonProperties properties = getProperties(usecase);
        Product product = new Product();
        product = add_contract(product, properties, contractState);
        product = add_characteristic(product, properties, productCharacterisitcState);
        product = add_relationship(product, properties, productRelationshipState);
        productOrderingSharedData.getTestOrderItem().setProduct(product);
    }

    @When(WHEN_BASE + "(valid|invalid|no) contract and (valid|invalid|no) product offering$")
    public void add_a_related_individual(final String usecase, final String contractState,
            final String productOfferingState) {
        ProductOrderCommonProperties properties = getProperties(usecase);
        Product product = new Product();
        product = add_contract(product, properties, contractState);
        product = add_offering(product, properties, productOfferingState);
        productOrderingSharedData.getTestOrderItem().setProduct(product);
    }
    /**
     * Add the offering from state
     * @param product
     * @param properties
     * @param productOfferingState
     * @return
     */
    private Product add_offering(final Product product, final ProductOrderCommonProperties properties, final String productOfferingState){
        Product productProperties = getProductProperties(properties, productOfferingState);
        if(null != productProperties) {
            product.setProductOffering(productProperties.getProductOffering());
        }
        return product;
    }
    
    /**
     * Add the specification from state
     * 
     * @param product
     * @param properties
     * @param productSpecState
     * @return
     */
    private Product add_spec(final Product product, final ProductOrderCommonProperties properties,
            final String productSpecState) {
        Product productProperties = getProductProperties(properties, productSpecState);
        if (null != productProperties) {
            product.setProductSpecification(productProperties.getProductSpecification());
        }
        return product;
    }

    /**
     * Add the contract from state
     * 
     * @param product
     * @param properties
     * @param contractState
     * @return
     */
    private Product add_contract(final Product product, final ProductOrderCommonProperties properties, final String contractState){
        Product productProperties = getProductProperties(properties, contractState);
        if(null != productProperties) {
            product.setId(productProperties.getId());
        }
        return  product;
    }

    /**
     * Add the characteristic from state
     * @param product
     * @param properties
     * @param productCharacterisitcState
     * @return
     */
    private Product add_characteristic(final Product product, final ProductOrderCommonProperties properties, final String productCharacterisitcState){
        Product productProperties = getProductProperties(properties, productCharacterisitcState);
        if(null != productProperties) {
            product.setProductCharacteristic(productProperties.getProductCharacteristic());
        }
        return  product;
    }

    /**
     * Find properties from state return null if state is no
     * @param properties
     * @param state
     * @return
     */
    private Product getProductProperties(final ProductOrderCommonProperties properties, final String state){
        switch(state){
            case "valid":
                return properties.getValidProduct();
            case "invalid":
                return properties.getInvalidProduct();
            case "no":
                return null;
            default:
                throw new IllegalArgumentException("Can't use key " + state);
        }
    }

    /**
     * Add the relationship from state
     * @param product
     * @param properties
     * @param productRelationshipState
     * @return
     */
    private Product add_relationship(final Product product, final ProductOrderCommonProperties properties, final String productRelationshipState){
        Product productProperties = getProductProperties(properties, productRelationshipState);
        if(null != productProperties) {
            product.setProductRelationship(productProperties.getProductRelationship());
        }
        return  product;
    }


}
