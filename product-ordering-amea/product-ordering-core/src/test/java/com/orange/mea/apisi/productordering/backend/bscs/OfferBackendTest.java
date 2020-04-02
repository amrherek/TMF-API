package com.orange.mea.apisi.productordering.backend.bscs;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.badrequest.BadParameterValueException;
import com.orange.apibss.productOrdering.model.OrderItem;
import com.orange.apibss.productOrdering.model.Product;
import com.orange.apibss.productOrdering.model.ProductOffering;
import com.orange.apibss.productOrdering.model.ProductOfferingRef;
import com.orange.apibss.productOrdering.model.ProductOrder;
import com.orange.mea.apisi.productordering.validator.ProductInputValidator;

@RunWith(SpringRunner.class)
public class OfferBackendTest {

    @InjectMocks
    private OfferBackend offerBackend;

    @Mock
    private ProductInputValidator productInputValidator;

    @Mock
    private BscsContractServiceFacade bscsContractService;

    @Test
    public void suspendOffer_noRequestedStartDate() throws ApiException {

        // prepare data
        OrderItem orderItem = buildValidOrderItem();
        ProductOrder productOrder = new ProductOrder();
        productOrder.addOrderItemItem(orderItem);

        when(productInputValidator.validateAndExtractStatusReason(null, null)).thenReturn(null);

        // perform test
        offerBackend.suspendOffer(orderItem, productOrder);

        verify(bscsContractService).suspend("contractId", null, null);
    }

    @Test
    public void suspendOffer_withRequestedStartDate() throws ApiException {

        // prepare data
        OrderItem orderItem = buildValidOrderItem();
        ProductOrder productOrder = new ProductOrder();
        DateTime dt = DateTime.parse("2022-01-01");
        productOrder.setRequestedStartDate(dt);
        productOrder.addOrderItemItem(orderItem);

        when(productInputValidator.validateAndExtractStatusReason(null, null)).thenReturn(null);

        // perform test
        offerBackend.suspendOffer(orderItem, productOrder);

        verify(bscsContractService).suspend("contractId", null, dt.toDate());
    }

    @Test
    public void reactivateOffer_noRequestedStartDate() throws ApiException {

        // prepare data
        OrderItem orderItem = buildValidOrderItem();
        ProductOrder productOrder = new ProductOrder();
        productOrder.addOrderItemItem(orderItem);

        when(productInputValidator.validateAndExtractStatusReason(null, null)).thenReturn(null);

        // perform test
        offerBackend.reactivateOffer(orderItem, productOrder);

        verify(bscsContractService).reactivateContract("contractId", null, null);
    }

    @Test
    public void reactivateOffer_withRequestedStartDate() throws ApiException {

        // prepare data
        OrderItem orderItem = buildValidOrderItem();
        ProductOrder productOrder = new ProductOrder();
        DateTime dt = DateTime.parse("2022-01-01");
        productOrder.setRequestedStartDate(dt);
        productOrder.addOrderItemItem(orderItem);

        when(productInputValidator.validateAndExtractStatusReason(null, null)).thenReturn(null);

        // perform test
        offerBackend.reactivateOffer(orderItem, productOrder);

        verify(bscsContractService).reactivateContract("contractId", null, dt.toDate());
    }

    @Test
    public void migrateOffer_noRequestedStartDate() throws ApiException {

        // prepare data
        OrderItem orderItem = buildValidOrderItemForMigration();
        ProductOrder productOrder = new ProductOrder();
        productOrder.addOrderItemItem(orderItem);

        // perform test
        offerBackend.migrateOffer(orderItem, productOrder);

        verify(bscsContractService).changeRatePlan("contractId", "rpId");
    }

    @Test(expected = BadParameterValueException.class)
    public void migrateOffer_errorWithRequestedStartDate() throws ApiException {

        // prepare data
        OrderItem orderItem = buildValidOrderItemForMigration();
        ProductOrder productOrder = new ProductOrder();
        DateTime dt = DateTime.parse("2022-01-01");
        productOrder.setRequestedStartDate(dt);
        productOrder.addOrderItemItem(orderItem);

        // perform test
        offerBackend.migrateOffer(orderItem, productOrder);
    }

    private OrderItem buildValidOrderItemForMigration() {
        OrderItem orderItem = new OrderItem();
        ProductOffering po = new ProductOffering();
        po.setId("rpId");
        orderItem.setProductOffering(po);
        Product product = new Product();
        ProductOfferingRef poRef = new ProductOfferingRef();
        po.setCategory("offer");
        product.setProductOffering(poRef);
        product.setId("contractId");
        orderItem.setProduct(product);
        return orderItem;
    }

    private OrderItem buildValidOrderItem() {
        OrderItem orderItem = new OrderItem();
        Product product = new Product();
        ProductOfferingRef po = new ProductOfferingRef();
        po.setCategory("offer");
        product.setProductOffering(po);
        product.setId("contractId");
        orderItem.setProduct(product);
        return orderItem;
    }

}
