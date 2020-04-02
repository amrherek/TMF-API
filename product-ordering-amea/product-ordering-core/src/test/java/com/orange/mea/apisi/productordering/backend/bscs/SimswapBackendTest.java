package com.orange.mea.apisi.productordering.backend.bscs;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
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
import com.orange.apibss.productOrdering.model.ProductRef;
import com.orange.apibss.productOrdering.model.ProductRelationShipType;
import com.orange.apibss.productOrdering.model.ProductRelationship;
import com.orange.bscs.model.BscsStorageMedium;
import com.orange.mea.apisi.productordering.transformer.SimProductOrderTransformer;
import com.orange.mea.apisi.productordering.validator.SimswapInputValidator;

@RunWith(SpringRunner.class)
public class SimswapBackendTest {

    @InjectMocks
    private SimswapBackend simswapBackend;

    @Mock
    private SimswapInputValidator simswapInputValidator;

    @Mock
    private SimProductOrderTransformer simProductOrderTransformer;

    @Mock
    private BscsSimServiceFacade bscsSimService;

    @Test
    public void swapSim_noRequestedStartDate() throws ApiException {

        // prepare data
        OrderItem deleteOrder = buildOrderItem();
        OrderItem addOrder = buildOrderItem();
        ProductOrder productOrder = new ProductOrder();
        productOrder.addOrderItemItem(addOrder);
        Long newSim = 123L;
        String oldSim = "oldSim";

        BscsStorageMedium value = mock(BscsStorageMedium.class);
        when(bscsSimService.searchSim(anyString())).thenReturn(value);
        when(value.getId()).thenReturn(newSim);
        when(simProductOrderTransformer.extractOldSimNumber(productOrder)).thenReturn(oldSim);

        // perform test
        simswapBackend.swapSim(deleteOrder, addOrder, productOrder);
        
        verify(bscsSimService).replaceContractResource(newSim, oldSim, "contractId", null);

    }

    @Test
    public void swapSim_withRequestedStartDate() throws ApiException {

        // prepare data
        OrderItem deleteOrder = buildOrderItem();
        OrderItem addOrder = buildOrderItem();
        ProductOrder productOrder = new ProductOrder();
        DateTime dt = DateTime.parse("2022-01-01");
        productOrder.setRequestedStartDate(dt);
        productOrder.addOrderItemItem(addOrder);
        Long newSim = 123L;
        String oldSim = "oldSim";

        BscsStorageMedium value = mock(BscsStorageMedium.class);
        when(bscsSimService.searchSim(anyString())).thenReturn(value);
        when(value.getId()).thenReturn(newSim);
        when(simProductOrderTransformer.extractOldSimNumber(productOrder)).thenReturn(oldSim);

        // perform test
        simswapBackend.swapSim(deleteOrder, addOrder, productOrder);

        verify(bscsSimService).replaceContractResource(newSim, oldSim, "contractId", dt.toDate());

    }

    private OrderItem buildOrderItem() {
        OrderItem orderItem = new OrderItem();
        Product product = new Product();
        ProductRelationship pri = new ProductRelationship();
        pri.setType(ProductRelationShipType.ISCONTAINEDIN);
        ProductRef pr = new ProductRef();
        pr.setId("contractId");
        pri.setProduct(pr);
        product.addProductRelationshipItem(pri);
        orderItem.setProduct(product);
        return orderItem;
    }

}
