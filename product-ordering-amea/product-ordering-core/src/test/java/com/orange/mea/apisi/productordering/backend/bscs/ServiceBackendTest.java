package com.orange.mea.apisi.productordering.backend.bscs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.badrequest.BadParameterValueException;
import com.orange.apibss.productOrdering.model.Characteristic;
import com.orange.apibss.productOrdering.model.OrderItem;
import com.orange.apibss.productOrdering.model.Product;
import com.orange.apibss.productOrdering.model.ProductOffering;
import com.orange.apibss.productOrdering.model.ProductOrder;
import com.orange.apibss.productOrdering.model.ProductRef;
import com.orange.apibss.productOrdering.model.ProductRelationShipType;
import com.orange.apibss.productOrdering.model.ProductRelationship;
import com.orange.bscs.api.connection.ConnectionHolder;
import com.orange.bscs.api.connection.SOIConnection;
import com.orange.bscs.model.BscsContractServiceParameter;
import com.orange.bscs.model.businesspartner.EnumContractStatus;
import com.orange.bscs.model.contract.EnumParameterType;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;
import com.orange.bscs.service.exception.BscsInvalidStateException;
import com.orange.bscs.service.exception.BscsPendingException;

@RunWith(SpringRunner.class)
public class ServiceBackendTest {

    @InjectMocks
    private ServiceBackend serviceBackend;

    @Mock
    private BscsContractServiceFacade bscsContractService;

    @Mock
    private BscsParameterServiceFacade bscsParameterService;

    @Mock
    private BscsFreeUnitPackageServiceFacade bscsFreeUnitPackageService;

    @Mock
    private SOIConnection connection;

    @Before
    public void init() {
        // set mock connection
        ConnectionHolder.setConnection(connection);
    }

    @Test
    public void testAddService_noParameters_noRequestedStartDate() throws ApiException {
        // prepare data
        OrderItem orderItem = buildValidOrderItemForAdd();
        ProductOrder po = new ProductOrder();
        po.addOrderItemItem(orderItem);

        // perform test
        serviceBackend.addService(orderItem, po);

        ArgumentCaptor<Date> dateCaptor = ArgumentCaptor.forClass(Date.class);
        verify(bscsContractService).addService(eq("contractId"), eq("serviceId"), eq("servicePackageId"),
                dateCaptor.capture());
        assertThat(dateCaptor.getValue()).isCloseTo(new Date(), 1000);
    }

    @Test
    public void testAddService_noParameters_withRequestedStartDate() throws ApiException {
        // prepare data
        OrderItem orderItem = buildValidOrderItemForAdd();
        ProductOrder po = new ProductOrder();
        DateTime dt = DateTime.parse("2022-01-01");
        po.setRequestedStartDate(dt);
        po.addOrderItemItem(orderItem);

        // perform test
        serviceBackend.addService(orderItem, po);

        verify(bscsContractService).addService(eq("contractId"), eq("serviceId"), eq("servicePackageId"),
                eq(dt.toDate()));
    }

    @Test
    public void testActivateService_noParameters_noRequestedStartDate() throws ApiException {
        // prepare data
        OrderItem orderItem = buildValidOrderItemForActivation();
        ProductOrder po = new ProductOrder();
        po.addOrderItemItem(orderItem);

        // perform test
        serviceBackend.activateService(orderItem, po);

        verify(bscsContractService).modifyServiceStatus("contractId", "serviceId", EnumContractStatus.ACTIVATED, null);
    }

    @Test
    public void testActivateService_noParameters_withRequestedStartDate() throws ApiException {
        // prepare data
        OrderItem orderItem = buildValidOrderItemForActivation();
        ProductOrder po = new ProductOrder();
        DateTime dt = DateTime.parse("2022-01-01");
        po.setRequestedStartDate(dt);
        po.addOrderItemItem(orderItem);

        // perform test
        serviceBackend.activateService(orderItem, po);

        verify(bscsContractService).modifyServiceStatus("contractId", "serviceId", EnumContractStatus.ACTIVATED,
                dt.toDate());
    }

    @Test
    public void testDeactivateService_noParameters_noRequestedStartDate() throws ApiException {
        // prepare data
        OrderItem orderItem = buildValidOrderItemForActivation();
        ProductOrder po = new ProductOrder();
        po.addOrderItemItem(orderItem);

        // perform test
        serviceBackend.deactivateService(orderItem, po);

        verify(bscsContractService).modifyServiceStatus("contractId", "serviceId", EnumContractStatus.DEACTIVATED,
                null);
    }

    @Test
    public void testDeactivateService_noParameters_withRequestedStartDate() throws ApiException {
        // prepare data
        OrderItem orderItem = buildValidOrderItemForActivation();
        ProductOrder po = new ProductOrder();
        DateTime dt = DateTime.parse("2022-01-01");
        po.setRequestedStartDate(dt);
        po.addOrderItemItem(orderItem);

        // perform test
        serviceBackend.deactivateService(orderItem, po);

        verify(bscsContractService).modifyServiceStatus("contractId", "serviceId", EnumContractStatus.DEACTIVATED,
                dt.toDate());
    }

    /**
     * RequestedStartDate is ignored
     * 
     * @throws ApiException
     * @throws BscsInvalidIdException
     * @throws BscsInvalidFieldException
     * @throws BscsPendingException
     * @throws BscsInvalidStateException
     */
    @Test
    public void testUpdateServiceCharacteristic_noRequestedStartDate() throws ApiException, BscsInvalidIdException,
            BscsInvalidFieldException, BscsPendingException, BscsInvalidStateException {
        // prepare data
        OrderItem orderItem = buildValidOrderItemForUpdateCharacteristics();
        ProductOrder po = new ProductOrder();
        po.addOrderItemItem(orderItem);
        BscsContractServiceParameter param = mock(BscsContractServiceParameter.class);

        when(bscsParameterService.getContractParameter("contractId", "serviceId", "name")).thenReturn(param);
        when(param.getType()).thenReturn(EnumParameterType.DATAFIELD);

        // perform test
        serviceBackend.updateServiceCharacteristic(orderItem, po);

        verify(bscsParameterService).writeParameter(eq("contractId"), eq("serviceId"),
                anyListOf(BscsContractServiceParameter.class));
    }

    @Test(expected = BadParameterValueException.class)
    public void testUpdateServiceCharacteristic_errorWithRequestedStartDate() throws ApiException,
            BscsInvalidIdException,
            BscsInvalidFieldException, BscsPendingException, BscsInvalidStateException {
        // prepare data
        OrderItem orderItem = buildValidOrderItemForUpdateCharacteristics();
        ProductOrder po = new ProductOrder();
        DateTime dt = DateTime.parse("2022-01-01");
        po.setRequestedStartDate(dt);
        po.addOrderItemItem(orderItem);

        // perform test
        serviceBackend.updateServiceCharacteristic(orderItem, po);
    }

    private OrderItem buildValidOrderItemForUpdateCharacteristics() {
        OrderItem orderItem = new OrderItem();
        Product product = new Product();
        product.setId("contractId|A|serviceId");
        Characteristic charac = new Characteristic();
        charac.setName("name");
        charac.setValue("value");
        product.addProductCharacteristicItem(charac);
        orderItem.setProduct(product);
        return orderItem;
    }

    private OrderItem buildValidOrderItemForActivation() {
        OrderItem orderItem = new OrderItem();
        Product product = new Product();
        product.setId("contractId|A|serviceId");
        orderItem.setProduct(product);
        return orderItem;
    }

    private OrderItem buildValidOrderItemForAdd() {
        OrderItem orderItem = new OrderItem();
        ProductOffering po = new ProductOffering();
        po.setId("serviceId");
        orderItem.setProductOffering(po);
        Product product = new Product();
        ProductRelationship prItem = new ProductRelationship();
        prItem.setType(ProductRelationShipType.ISCONTAINEDIN);
        ProductRef pr = new ProductRef();
        pr.setId("contractId|C|servicePackageId");
        prItem.setProduct(pr);
        product.addProductRelationshipItem(prItem);
        orderItem.setProduct(product);
        return orderItem;
    }

}
