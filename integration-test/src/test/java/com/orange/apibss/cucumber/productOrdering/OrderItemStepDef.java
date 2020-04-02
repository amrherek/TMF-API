package com.orange.apibss.cucumber.productOrdering;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;

import com.orange.apibss.cucumber.config.ProductOrderingProperties;
import com.orange.apibss.cucumber.tools.Tools;
import com.orange.apibss.productOrdering.model.OrderItem;
import com.orange.apibss.productOrdering.model.OrderItemAction;
import com.orange.apibss.productOrdering.model.OrderItemRelationship;
import com.orange.apibss.productOrdering.model.OrderItemRelationshipType;
import com.orange.apibss.productOrdering.model.ProductOffering;
import com.orange.apibss.productOrdering.model.ProductOfferingRef;
import com.orange.apibss.productOrdering.model.ProductOrder;

import cucumber.api.java.en.When;

/**
 * All step definitions for order item management
 *
 * @author Thibault Duperron
 */
public class OrderItemStepDef extends ProductOrderingBaseStepDefs{
    @Autowired
    private ProductOrderingSharedData productOrderingSharedData;
    @Autowired
    private ProductOrderingProperties productOrderingProperties;

    private final String WHEN_BASE = "^With (friend and family|um13|um7|emergency credit|data bundle) ";

    @When("^Wait for (.*) seconds$")
    public void wait_seconds(final Long duration) throws InterruptedException {
        TimeUnit.SECONDS.sleep(duration);
    }

    /**
     * Init a new order item and add it to the request
     */
    @When("^Use an order item$")
    public void use_an_order_item(){
        ProductOrder productOrder = productOrderingSharedData.getTestProductOrder();
        OrderItem orderItem = new OrderItem();
        // Find next available id
        orderItem.setId(Tools.getNextOrderId(productOrder));
        // Add it to current product order
        productOrder.addOrderItemItem(orderItem);
        productOrderingSharedData.setTestOrderItem(orderItem);
    }
    
    /**
     * Init a new order item and add it to the request
     * 
     * @param action
     */
    @When("^Use an order item with (add|add characteristic|delete|delete characteristic|migrate|modify characteristic) action$")
    public void use_an_order_item_with_action(final String action){
        ProductOrder productOrder = productOrderingSharedData.getTestProductOrder();
        OrderItem orderItem = new OrderItem();
        // Find next available id
        orderItem.setId(Tools.getNextOrderId(productOrder));
        // set action
        switch (action){
            case "add":
            orderItem.setAction(OrderItemAction.ADD);
                break;
            case "add characteristic":
            orderItem.setAction(OrderItemAction.ADDCHARACTERISTIC);
                break;
            case "delete":
            orderItem.setAction(OrderItemAction.DELETE);
                break;
            case "delete characteristic":
            orderItem.setAction(OrderItemAction.DELETECHARACTERISTIC);
                break;
            case "migrate":
            orderItem.setAction(OrderItemAction.MIGRATE);
                break;
            case "modify characteristic":
            orderItem.setAction(OrderItemAction.MODIFYCHARACTERISTIC);
                break;
            default:
                throw new IllegalArgumentException("Can't use key " + action + " on \"With (*) action\" step");
        }
        // Add it to current product order
        productOrder.addOrderItemItem(orderItem);
        productOrderingSharedData.setTestOrderItem(orderItem);
    }

    /**
     * Init a new order item with no id and add it to the request
     */
    @When("^Use an order item with no id$")
    public void use_an_order_item_with_no_id(){
        ProductOrder productOrder = productOrderingSharedData.getTestProductOrder();
        OrderItem orderItem = new OrderItem();
        // Add it to current product order
        productOrder.addOrderItemItem(orderItem);
        productOrderingSharedData.setTestOrderItem(orderItem);
    }

    /**
     * Add simcard product to the current order item
     * 
     * @param state
     * @param icc
     * @param contract
     */
    @When("^With simcard product (after simswap|before simswap) with (valid|invalid|terminated) icc id and (valid|invalid|bad format|terminated) contract id$")
    public void with_simcard_product(final String state, final String icc,
            final String contract) {
        OrderItem orderItem = productOrderingSharedData.getTestOrderItem();
        switch (state){
            case "after simswap":
                orderItem.setProduct(productOrderingProperties.getSimSwap().getAfterSimSwap());
                break;
            case "before simswap":
                orderItem.setProduct(productOrderingProperties.getSimSwap().getBeforeSimSwap());
                break;
            default:
                throw new IllegalArgumentException("Can't use key " + state + " on \"With simcard product  (*)\" step");
        }
        switch (icc) {
        case "valid":
            orderItem.getProduct().getProductCharacteristic().get(0)
                    .setValue(productOrderingProperties.getSimSwap().getValidIccId());
            break;
        case "invalid":
            orderItem.getProduct().getProductCharacteristic().get(0)
                    .setValue(productOrderingProperties.getSimSwap().getInvalidIccId());
            break;
        case "terminated":
            orderItem.getProduct().getProductCharacteristic().get(0)
                    .setValue(productOrderingProperties.getSimSwap().getTerminatedIccId());
            break;
        }
        switch (contract) {
        case "valid":
            orderItem.getProduct().getProductRelationship().get(0).getProduct()
                    .setId(productOrderingProperties.getSimSwap().getValidProductId());
            break;
        case "invalid":
            orderItem.getProduct().getProductRelationship().get(0).getProduct()
                    .setId(productOrderingProperties.getSimSwap().getInvalidProductId());
            break;
        case "bad format":
            orderItem.getProduct().getProductRelationship().get(0).getProduct()
                    .setId(productOrderingProperties.getSimSwap().getBadFormatProductId());
            break;
        case "terminated":
            orderItem.getProduct().getProductRelationship().get(0).getProduct()
                    .setId(productOrderingProperties.getService().getTerminatedProductId());
            break;
        }
    }

    /**
     * Add prerequiste product to the current order item
     * @param isHas is or has prerequiste
     * @param count indicate modification from the current order Id to have te prerequiste id
     */
    @When("^With (is|has) prerequisite of (next|previous) order item$")
    public void with_prerequisite_of_order_item(final String isHas, final String count){
        OrderItem orderItem = productOrderingSharedData.getTestOrderItem();
        OrderItemRelationship orderItemRelationship = new OrderItemRelationship();
        switch (isHas){
            case "is":
            orderItemRelationship.setType(OrderItemRelationshipType.ISPREREQUISITE);
                break;
            case "has":
            orderItemRelationship.setType(OrderItemRelationshipType.HASPREREQUISITE);
                break;
            default:
                throw new IllegalArgumentException("Can't use key " + isHas + " on \"With (*) prerequisite of (*) order item\" step");
        }
        Long id = Long.parseLong(orderItem.getId());
        switch (count){
            case "next":
                orderItemRelationship.setId(Long.toString(id+1L));
                break;
            case "previous":
                orderItemRelationship.setId(Long.toString(id-1L));
                break;
            default:
                throw new IllegalArgumentException("Can't use key " + count + " on \"With (*) prerequisite of (*) order item\" step");
        }

        orderItem.setOrderItemRelationship(orderItemRelationship);
    }

    /**
     * Add product offering to the product order
     * 
     * @param usecase
     * @param state
     */
    @When(WHEN_BASE + "(valid|initial) product offering$")
    public void use_a_product_offering(final String usecase, final String state) {
        OrderItem orderItem = productOrderingSharedData.getTestOrderItem();
        ProductOffering po = null;
        switch (state) {
        case "initial":
            po = getProperties(usecase).getInitialProductOffering();
            break;
        case "valid":
            po = getProperties(usecase).getValidProductOffering();
            break;
        }
        orderItem.setProductOffering(po);
    }

    /**
     * Add productOffering.id to the product order
     * 
     * @param productOfferingId
     */
    @When("^With (.*) productOffering.id$")
    public void use_product_offering(final String productOfferingId) {
        OrderItem orderItem = productOrderingSharedData.getTestOrderItem();
        ProductOffering po = new ProductOffering();
        po.setId(productOfferingId);
        orderItem.setProductOffering(po);
    }

    /**
     * Add product.productOffering.id to the product order
     * 
     * @param productOfferingId
     */
    @When("^With (.*) product.productOffering.id$")
    public void use_product_productOffering(final String productOfferingId) {
        OrderItem orderItem = productOrderingSharedData.getTestOrderItem();
        ProductOfferingRef po = new ProductOfferingRef();
        po.setId(productOfferingId);
        orderItem.getProduct().setProductOffering(po);
    }
}
