package com.orange.apibss.cucumber.productOrdering;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.orange.apibss.cucumber.config.ApibssProperties;
import com.orange.apibss.cucumber.config.ProductOrderingProperties;
import com.orange.apibss.cucumber.tools.Tools;
import com.orange.apibss.productOrdering.model.ChannelRef;
import com.orange.apibss.productOrdering.model.Characteristic;
import com.orange.apibss.productOrdering.model.OrderItem;
import com.orange.apibss.productOrdering.model.OrderItemAction;
import com.orange.apibss.productOrdering.model.Product;
import com.orange.apibss.productOrdering.model.ProductOffering;
import com.orange.apibss.productOrdering.model.ProductOfferingRef;
import com.orange.apibss.productOrdering.model.ProductOrder;
import com.orange.apibss.productOrdering.model.ProductRef;
import com.orange.apibss.productOrdering.model.ProductRelationShipType;
import com.orange.apibss.productOrdering.model.ProductRelationship;
import com.orange.apibss.productOrdering.model.ProductSpecification;
import com.orange.apibss.productOrdering.model.RelatedIndividual;
import com.orange.apibss.productOrdering.model.State;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


/**
 * Step defs for product ordering API
 * @author Thibault Duperron
 */
public class ProductOrderingStepDefs extends ProductOrderingBaseStepDefs {

    @Autowired
    private ApibssProperties apibssProperties;

    @Autowired
    private ProductOrderingProperties productOrderingProperties;

    @Autowired
    private ProductOrderingSharedData productOrderingSharedData;

    @Autowired
    private RestTemplate restTemplate;

    @Given("^Use a valid product ordering body$")
    public void use_a_valid_product_ordering_body() {
        ProductOrder productOrder = new ProductOrder();
        ChannelRef channel = new ChannelRef();
        channel.setName("integrationTest");
        productOrder.setChannel(channel);
        productOrderingSharedData.setTestProductOrder(productOrder);
    }

    @When("^Add (reactivate|activate|suspend) offer with (valid|invalid|terminated|updatable) product id$")
    public void add_activate_offer_with_product_id(String offerType, String type) {
        ProductOrder productOrder = productOrderingSharedData.getTestProductOrder();
        OrderItem orderItem = new OrderItem();
        orderItem.setId(Tools.getNextOrderId(productOrder));

        switch (offerType) {
        case "activate":
            orderItem.setAction(OrderItemAction.ACTIVATE);
            break;
        case "reactivate":
            orderItem.setAction(OrderItemAction.REACTIVATE);
            break;
        case "suspend":
            orderItem.setAction(OrderItemAction.SUSPEND);
            break;
        }

        Product product = new Product();
        switch (type) {
        case "valid":
            product.setId(productOrderingProperties.getActivateOffer().getValidProductId());
            break;
        case "invalid":
            product.setId(productOrderingProperties.getActivateOffer().getInvalidProductId());
            break;
        case "terminated":
            product.setId(productOrderingProperties.getActivateOffer().getTerminatedProductId());
            break;
        case "updatable":
            product.setId(productOrderingProperties.getActivateOffer().getUpdatableProductId());
            break;
        }
        orderItem.setProduct(product);

        ProductSpecification productSpecification = new ProductSpecification();
        productSpecification.setId("offer");
        product.setProductSpecification(productSpecification);

        productOrder.addOrderItemItem(orderItem);
        productOrderingSharedData.setTestOrderItem(orderItem);
    }

    @When("^Add (reactivate|suspend) offer with old format category$")
    public void add_update_offer_with_category(String action) {
        ProductOrder productOrder = productOrderingSharedData.getTestProductOrder();
        OrderItem orderItem = new OrderItem();
        orderItem.setId(Tools.getNextOrderId(productOrder));

        switch (action) {
        case "reactivate":
            orderItem.setAction(OrderItemAction.REACTIVATE);
            break;
        case "suspend":
            orderItem.setAction(OrderItemAction.SUSPEND);
            break;
        }

        Product product = new Product();
        product.setId(productOrderingProperties.getActivateOffer().getUpdatableProductId());
        orderItem.setProduct(product);

        ProductOfferingRef productOfferingRef = new ProductOfferingRef();
        productOfferingRef.setCategory("offer");
        product.setProductOffering(productOfferingRef);

        productOrder.addOrderItemItem(orderItem);
        productOrderingSharedData.setTestOrderItem(orderItem);
    }

    @When("^Add (activate|deactivate) service with (valid|invalid|terminated) product id and (valid|invalid|bad|activated) service id$")
    public void add_activate_service_with_product_id(String action, String typeProduct, String typeService) {
        ProductOrder productOrder = productOrderingSharedData.getTestProductOrder();
        OrderItem orderItem = new OrderItem();
        orderItem.setId(Tools.getNextOrderId(productOrder));
        switch (action) {
        case "activate":
            orderItem.setAction(OrderItemAction.ACTIVATE);
            break;
        case "deactivate":
            orderItem.setAction(OrderItemAction.DEACTIVATE);
            break;
        }

        Product product = new Product();
        String id = "";
        switch (typeProduct) {
        case "valid":
            id += productOrderingProperties.getService().getValidProductId();
            break;
        case "invalid":
            id += productOrderingProperties.getService().getInvalidProductId();
            break;
        case "terminated":
            id += productOrderingProperties.getService().getTerminatedProductId();
            break;
        }
        id += "|A|";
        switch (typeService) {
        case "valid":
            id += productOrderingProperties.getService().getValidServiceIdActivate();
            break;
        case "invalid":
            id += productOrderingProperties.getService().getInvalidServiceId();
            break;
        case "bad":
            id += productOrderingProperties.getService().getBadServiceId();
            break;
        }

        product.setId(id);
        orderItem.setProduct(product);

        productOrder.addOrderItemItem(orderItem);
        productOrderingSharedData.setTestOrderItem(orderItem);
    }

    @When("^Add suspend status reason$")
    public void add_statusReason() {
        OrderItem orderItem = productOrderingSharedData.getTestOrderItem();
        Characteristic pc = new Characteristic();
        pc.setName("statusReason");
        pc.setValue("2"); // suspendig reason status
        orderItem.getProduct().addProductCharacteristicItem(pc);
    }

    @When("^Add (.*) value for (.*) productCharacteristic$")
    public void add_productCharacteristic(String value, String name) {
        OrderItem orderItem = productOrderingSharedData.getTestOrderItem();
        Characteristic pc = new Characteristic();
        pc.setName(name);
        pc.setValue(value); // suspendig reason status
        orderItem.getProduct().addProductCharacteristicItem(pc);
    }

    @When("^Add add service with (valid|invalid|terminated) product id and (valid|invalid|bad|activated) service id and (valid|invalid|bad) service package id$")
    public void add_add_service_with_product_id(String typeProduct, String typeService,
            String typeSp) {
        ProductOrder productOrder = productOrderingSharedData.getTestProductOrder();
        OrderItem orderItem = new OrderItem();
        orderItem.setId(Tools.getNextOrderId(productOrder));
        orderItem.setAction(OrderItemAction.ADD);

        ProductOffering productOffering = new ProductOffering();
        switch (typeService) {
        case "valid":
            productOffering.setId(productOrderingProperties.getService().getValidServiceIdAdd());
            break;
        case "invalid":
            productOffering.setId(productOrderingProperties.getService().getInvalidServiceId());
            break;
        case "bad":
            productOffering.setId(productOrderingProperties.getService().getBadServiceId());
            break;
        case "activated":
            productOffering.setId(productOrderingProperties.getService().getValidServiceIdActivate());
            break;
        }
        orderItem.setProductOffering(productOffering);

        ProductRef product = new ProductRef();
        String id = "";
        switch (typeProduct) {
        case "valid":
            id += productOrderingProperties.getService().getValidProductId();
            break;
        case "invalid":
            id += productOrderingProperties.getService().getInvalidProductId();
            break;
        case "terminated":
            id += productOrderingProperties.getService().getTerminatedProductId();
            break;
        }
        id += "|C|";
        switch (typeSp) {
        case "valid":
            id += productOrderingProperties.getService().getValidServicePackageIdAdd();
            break;
        case "invalid":
            id += productOrderingProperties.getService().getInvalidServicePackageId();
            break;
        case "bad":
            id += productOrderingProperties.getService().getBadServicePackageId();
            break;
        }
        product.setId(id);

        Product p = new Product();
        ProductRelationship pr = new ProductRelationship();
        pr.setType(ProductRelationShipType.ISCONTAINEDIN);
        pr.setProduct(product);
        p.addProductRelationshipItem(pr);

        orderItem.setProduct(p);

        productOrder.addOrderItemItem(orderItem);
        productOrderingSharedData.setTestOrderItem(orderItem);
    }

    @When("^Add add parameter service with (valid|invalid|terminated) product id and (valid|invalid|bad|withoutParameter) service id and (valid|invalid|withoutParameter) service package id$")
    public void add_add_service_with_parameters(String typeProduct, String typeService, String typeSp) {
        ProductOrder productOrder = productOrderingSharedData.getTestProductOrder();
        OrderItem orderItem = new OrderItem();
        orderItem.setId(Tools.getNextOrderId(productOrder));

        Product p = new Product();
        orderItem.setAction(OrderItemAction.ADD);
        ProductOffering productOffering = new ProductOffering();
        switch (typeService) {
        case "valid":
            productOffering.setId(productOrderingProperties.getService().getValidServiceIdWithparametersAdd());
            break;
        case "invalid":
            productOffering.setId(productOrderingProperties.getService().getInvalidServiceId());
            break;
        case "bad":
            productOffering.setId(productOrderingProperties.getService().getBadServiceId());
            break;
        case "withoutParameter":
            // service without parameters
            productOffering.setId(productOrderingProperties.getService().getValidServiceIdAdd());
            break;
        }
        orderItem.setProductOffering(productOffering);

        ProductRef product = new ProductRef();
        String id = "";
        switch (typeProduct) {
        case "valid":
            id += productOrderingProperties.getService().getValidProductId();
            break;
        case "invalid":
            id += productOrderingProperties.getService().getInvalidProductId();
            break;
        case "terminated":
            id += productOrderingProperties.getService().getTerminatedProductId();
            break;
        }
        id += "|C|";
        switch (typeSp) {
        case "valid":
            id += productOrderingProperties.getService().getValidServicePackageIdWithparameters();
            break;
        case "invalid":
            id += productOrderingProperties.getService().getInvalidServicePackageId();
            break;
        case "withoutParameter":
            // SP for service without parameters
            id += productOrderingProperties.getService().getValidServicePackageIdAdd();
            break;
        }
        product.setId(id);

        ProductRelationship pr = new ProductRelationship();
        pr.setType(ProductRelationShipType.ISCONTAINEDIN);
        pr.setProduct(product);
        p.addProductRelationshipItem(pr);

        orderItem.setProduct(p);

        productOrder.addOrderItemItem(orderItem);
        productOrderingSharedData.setTestOrderItem(orderItem);
    }

    @When("^Add modify parameter service with (valid|invalid|terminated) product id and (valid|invalid|bad) service id$")
    public void add_modify_service_with_parameters(String typeProduct, String typeService) {
        ProductOrder productOrder = productOrderingSharedData.getTestProductOrder();
        OrderItem orderItem = new OrderItem();
        orderItem.setId(Tools.getNextOrderId(productOrder));

        Product p = new Product();
        orderItem.setAction(OrderItemAction.MODIFYCHARACTERISTIC);
        String id = "";
        switch (typeProduct) {
        case "valid":
            id += productOrderingProperties.getService().getValidProductId();
            break;
        case "invalid":
            id += productOrderingProperties.getService().getInvalidProductId();
            break;
        case "terminated":
            id += productOrderingProperties.getService().getTerminatedProductId();
            break;
        }
        id += "|A|";
        switch (typeService) {
        case "valid":
            id += productOrderingProperties.getService().getValidServiceIdWithparametersModify();
            break;
        case "invalid":
            id += productOrderingProperties.getService().getInvalidServiceId();
            break;
        case "bad":
            id += productOrderingProperties.getService().getBadServiceId();
            break;
        }
        p.setId(id);
        orderItem.setProduct(p);

        productOrder.addOrderItemItem(orderItem);
        productOrderingSharedData.setTestOrderItem(orderItem);
    }

    @When("^Add productCharacteristic for (valid|invalid) parameter id and (valid|invalid) parameter value$")
    public void add_parameters(String typeId, String typeValue) {
        OrderItem orderItem = productOrderingSharedData.getTestOrderItem();
        Characteristic characteristic = new Characteristic();
        switch (typeId) {
        case "valid":
            characteristic.setName(productOrderingProperties.getService().getValidParameterId());
            break;
        case "invalid":
            characteristic.setName(productOrderingProperties.getService().getInvalidParameterId());
            break;
        }
        switch (typeValue) {
        case "valid":
            characteristic.setValue(productOrderingProperties.getService().getValidParameterValue());
            break;
        case "invalid":
            characteristic.setValue(productOrderingProperties.getService().getInvalidParameterValue());
            break;
        }
        orderItem.getProduct().addProductCharacteristicItem(characteristic);
    }


    @When("^Add a valid related individual$")
    public void add_a_related_individual(){
        ProductOrder productOrder = productOrderingSharedData.getTestProductOrder();
        RelatedIndividual relatedIndividual = productOrderingProperties.getActivateOffer().getValidRelatedIndividual();
        productOrder.addRelatedIndividualItem(relatedIndividual);
    }

    @When("^Send product ordering request$")
    public void send_product_ordering_request(){

        try {
            HttpHeaders headers = new HttpHeaders();
            Tools.addHeaders(apibssProperties.getHeaders(), headers);
            ResponseEntity<ProductOrder> response = restTemplate.exchange(
                    apibssProperties.getProductOrderingUrl() + "/productOrder", HttpMethod.POST,
                    new HttpEntity<>(productOrderingSharedData.getTestProductOrder(), headers), ProductOrder.class);
            productOrderingSharedData.setProductOrder(response.getBody());
        } catch (HttpStatusCodeException e) {
            sharedData.setException(e);
        }
    }


    @Then("^Api return the product order acknowledged$")
    public void api_return_the_order_item_acknowledged(){
        assertThat(sharedData.getException()).isNull();
        ProductOrder productOrder = productOrderingSharedData.getProductOrder();
        assertThat(productOrder).isNotNull();
        assertThat(productOrder.getState()).isEqualTo(State.ACKNOWLEDGED);
    }


    @Then("^Api return the product order in progress$")
    public void api_return_the_order_item_in_progress(){
        assertThat(sharedData.getException()).isNull();
        ProductOrder productOrder = productOrderingSharedData.getProductOrder();
        assertThat(productOrder).isNotNull();
        assertThat(productOrder.getState()).isEqualTo(State.INPROGRESS);
    }

    @When("^Add (.*) product.productSpecification.id$")
    public void add_productProductSpecId(String productSpecId) {
        OrderItem orderItem = productOrderingSharedData.getTestOrderItem();
        ProductSpecification productSpecification = new ProductSpecification();
        productSpecification.setId(productSpecId);
        if (orderItem.getProduct() == null) {
            orderItem.setProduct(new Product());
        }
        orderItem.getProduct().setProductSpecification(productSpecification);
    }

    @When("^Add (.*) productOffering.category$")
    public void add_category(String category) {
        OrderItem orderItem = productOrderingSharedData.getTestOrderItem();
        orderItem.getProductOffering().setCategory(category);
    }

    @When("^Add (.*) product.productOffering.category$")
    public void add_ProductCategory(String category) {
        OrderItem orderItem = productOrderingSharedData.getTestOrderItem();
        if (orderItem.getProduct() == null) {
            orderItem.setProduct(new Product());
        }
        if (orderItem.getProduct().getProductOffering() == null) {
            orderItem.getProduct().setProductOffering(new ProductOfferingRef());
        }
        orderItem.getProduct().getProductOffering().setCategory(category);
    }

}
