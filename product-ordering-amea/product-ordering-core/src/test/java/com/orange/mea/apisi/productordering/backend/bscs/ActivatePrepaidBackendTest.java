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
import com.orange.apibss.productOrdering.model.OrderItem;
import com.orange.apibss.productOrdering.model.Product;
import com.orange.apibss.productOrdering.model.ProductOrder;
import com.orange.mea.apisi.productordering.backend.api.PartyClient;
import com.orange.mea.apisi.productordering.transformer.ActivatePrepaidProductOrderResponseTransformer;
import com.orange.mea.apisi.productordering.validator.ActivatePrepaidInputValidator;
import com.orange.mea.apisi.productordering.validator.ProductInputValidator;

@RunWith(SpringRunner.class)
public class ActivatePrepaidBackendTest {

    @InjectMocks
    private ActivatePrepaidBackend activatePrepaidBackend;

    @Mock
    private ActivatePrepaidInputValidator activatePrepaidInputValidator;

    @Mock
    private ProductInputValidator productInputValidator;

    @Mock
    private PartyClient partyClient;

    @Mock
    private BscsContractServiceFacade bscsContractService;

    @Mock
    private ActivatePrepaidProductOrderResponseTransformer activatePrepaidProductOrderResponseTransformer;

    @Test
    public void testActivateOffer_noRequestedStartDate() throws ApiException {
        // prepare data
        OrderItem orderItem = buildValidOrderItem();
        ProductOrder productOrder = new ProductOrder();
        productOrder.addOrderItemItem(orderItem);
        ProductOrder response = null;

        when(productInputValidator.validateAndExtractStatusReason(null, null)).thenReturn(null);

        // perform test
        activatePrepaidBackend.activateOffer(orderItem, productOrder, response);

        verify(bscsContractService).activateContract("contractId", null, null);
    }

    @Test
    public void testActivateOffer_withRequestedStartDate() throws ApiException {
        // prepare data
        OrderItem orderItem = buildValidOrderItem();
        ProductOrder productOrder = new ProductOrder();
        DateTime dt = DateTime.parse("2022-01-01");
        productOrder.setRequestedStartDate(dt);
        productOrder.addOrderItemItem(orderItem);
        ProductOrder response = null;

        when(productInputValidator.validateAndExtractStatusReason(null, null)).thenReturn(null);

        // perform test
        activatePrepaidBackend.activateOffer(orderItem, productOrder, response);

        verify(bscsContractService).activateContract("contractId", null, dt.toDate());
    }

    private OrderItem buildValidOrderItem() {
        OrderItem orderItem = new OrderItem();
        Product product = new Product();
        product.setId("contractId");
        orderItem.setProduct(product);
        return orderItem;
    }

}
