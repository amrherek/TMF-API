package com.orange.mea.apisi.productordering.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.badrequest.BadParameterFormatException;
import com.orange.apibss.common.exceptions.badrequest.BadParameterValueException;
import com.orange.apibss.common.exceptions.badrequest.BadRequestException;
import com.orange.apibss.common.exceptions.badrequest.MissingParameterException;
import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.apibss.productOrdering.model.OrderItem;
import com.orange.apibss.productOrdering.model.OrderItemAction;
import com.orange.apibss.productOrdering.model.OrderItemRelationship;
import com.orange.apibss.productOrdering.model.OrderItemRelationshipType;
import com.orange.apibss.productOrdering.model.ProductOrder;
import com.orange.mea.apisi.productordering.orchestrator.ProductOrderOrchestrator;
import com.orange.mea.apisi.productordering.tools.ProductOrderingTools;
import com.orange.mea.apisi.productordering.transformer.ProductOrderResponseTransformer;

/**
 * Service for creating product orders
 *
 * @author xbbs3851
 *
 */
@Service
public class ProductOrderService {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String ORDERITEM_ID = "orderItem.id";
    private static final String ORDERITEM_RELATIONSHIP_ID = "orderItem.orderItemRelationship.id";

    @Value("${sleep.duration:1000}")
	private Long sleepDuration;
    
    @Autowired
    private ProductOrderResponseTransformer productOrderResponseTransformer;
    
    @Autowired
    private ProductOrderOrchestrator productOrderOrchestrator;
    
    public ProductOrder create(final ProductOrder productOrder) throws ApiException {

        // sort orderItems by id
        if (productOrder.getOrderItem() != null) {
            ProductOrderingTools.sortOrderItems(productOrder.getOrderItem());
        }

        validateProductOrder(productOrder);

        final ProductOrder response = new ProductOrder();
        final int size = productOrder.getOrderItem().size();
        for (int i = 0; i < size; i++) {
            final boolean isLast = i == size - 1;
            final OrderItem orderItem = productOrder.getOrderItem().get(i);
            if (orderItem.getOrderItemRelationship() != null) {
                final OrderItemRelationshipType type = orderItem.getOrderItemRelationship().getType();
                switch (type) {
                case ISPREREQUISITE:
                    final OrderItem orderItem2 = ProductOrderingTools.findFirstById(productOrder.getOrderItem(),
                            orderItem.getOrderItemRelationship().getId());
                    process(orderItem, orderItem2, productOrder, response, isLast);
                    break;
                case HASPREREQUISITE:
                    // already processed
                    break;
                default:
                    throw new BadParameterValueException("orderItem.orderItemRelationship.type", type.toString());
                }
            } else {
                process(orderItem, productOrder, response, isLast);
            }
        }
        // build response
        // TODO : catch exception and return partial status?
        productOrderResponseTransformer.doTransform(productOrder, response);
        return response;
    }

    /**
     * Process two linked orderItem. return true if both have been processed
     *
     * @param firstOrderItem
     * @param secondOrderItem
     * @param productOrder
     * @param response
     * @param isLast
     * @throws ApiException
     */
    private void process(final OrderItem firstOrderItem, final OrderItem secondOrderItem,
            final ProductOrder productOrder, final ProductOrder response, final boolean isLast) throws ApiException {
        // sim swap or FaF update
        if (firstOrderItem.getAction() == OrderItemAction.DELETECHARACTERISTIC
                && secondOrderItem.getAction() == OrderItemAction.ADDCHARACTERISTIC) {
        	productOrderOrchestrator.updateCharacteristic(firstOrderItem, secondOrderItem, productOrder, response);
            response.addOrderItemItem(firstOrderItem);
            response.addOrderItemItem(secondOrderItem);
        } else {
            logger.warn("Unknown linked orderItems with actions " + firstOrderItem.getAction() + " and "
                    + secondOrderItem.getAction());
            // process the two separately
            process(firstOrderItem, productOrder, response, false);
            sleep();
            process(secondOrderItem, productOrder, response, isLast);
        }
        if (!isLast) {
            sleep();
        }
    }

    private void sleep() throws TechnicalException {
        try {
            Thread.sleep(sleepDuration);
        } catch (final InterruptedException e) {
            logger.warn("sleep failed", e);
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Process an orderItem
     *
     * @param orderItem
     *            order item
     * @param productOrder
     *            product order for informations
     * @param response
     * @param isLast
     * @throws ApiException
     */
    private void process(final OrderItem orderItem, final ProductOrder productOrder, final ProductOrder response,
            final boolean isLast) throws ApiException {
        switch (orderItem.getAction()) {
        case ADDCHARACTERISTIC:
        	productOrderOrchestrator.addCharacteristic(orderItem, productOrder);
            break;
        case ADD:
        	productOrderOrchestrator.add(orderItem, productOrder);
            break;
        case ACTIVATE:
        	productOrderOrchestrator.activate(orderItem, productOrder, response);
            break;
        case REACTIVATE:
        	productOrderOrchestrator.reactivate(orderItem, productOrder);
            break;
        case DEACTIVATE:
        	productOrderOrchestrator.deactivate(orderItem, productOrder);
            break;
        case DELETE:
        	productOrderOrchestrator.delete(orderItem, productOrder);
            break;
        case DELETECHARACTERISTIC:
        	productOrderOrchestrator.deleteCharacteristic(orderItem, productOrder);
            break;
        case MIGRATE:
        	productOrderOrchestrator.migrate(orderItem, productOrder);
            break;
        case SUSPEND:
        	productOrderOrchestrator.suspend(orderItem, productOrder);
            break;
        case MODIFYCHARACTERISTIC:
        	productOrderOrchestrator.updateCharacteristic(orderItem, productOrder);
            break;
        default:
            throw new BadRequestException("Cannot process order item " + orderItem.getAction());
        }
        response.addOrderItemItem(orderItem);
        if (!isLast) {
            sleep();
        }
    }


    /**
     * Verify if the productOrder object received by the webservice is valid for
     * creation
     *
     * @param productOrder
     * @throws BadRequestException
     */
    protected void validateProductOrder(final ProductOrder productOrder) throws BadRequestException {
        if (productOrder == null) {
            throw new MissingParameterException("productOrder");
        }
        if (productOrder.getOrderItem() == null || productOrder.getOrderItem().isEmpty()) {
            throw new MissingParameterException("productOrder.orderItem");
        }
        // validate requestedStartDate
        if (productOrder.getRequestedStartDate() != null && productOrder.getRequestedStartDate().isBeforeNow()) {
            throw new BadParameterValueException("requestedStartDate must be must be equal or later than today");
        }
        // validate channel
        if (productOrder.getChannel() == null || productOrder.getChannel().getName() == null
                || productOrder.getChannel().getName().isEmpty()) {
            throw new MissingParameterException("productOrder.channel.name");
        }
        // validate orderItems
        validateOrderItems(productOrder.getOrderItem());
    }

    protected void validateOrderItems(List<OrderItem> orderItems)
            throws MissingParameterException, BadParameterFormatException, BadParameterValueException {
        for (final OrderItem orderItem : orderItems) {
            // Check order item Id is a long
            final String orderId = orderItem.getId();
            getLongValue(orderId, ORDERITEM_ID);
            // check action
            if (orderItem.getAction() == null) {
                throw new MissingParameterException("orderItem.action");
            }
            // Check relationship orders
            final String relationShipId;
            final OrderItemRelationship orderItemRelationship = orderItem.getOrderItemRelationship();
            if (orderItemRelationship != null) {
                relationShipId = orderItemRelationship.getId();
                switch (orderItemRelationship.getType()) {
                case ISPREREQUISITE:
                    checkIsPrerequisite(orderItems, orderId, relationShipId);
                    break;
                case HASPREREQUISITE:
                    checkHasPrerequisite(orderItems, orderId, relationShipId);
                    break;
                default:
                    break;
                }
            }
        }
    }

    protected void checkIsPrerequisite(final List<OrderItem> items, final String orderId, final String relationShipId)
            throws BadParameterValueException, MissingParameterException, BadParameterFormatException,
            MissingParameterException {
        final Long relationShipIdLong = getLongValue(relationShipId, ORDERITEM_RELATIONSHIP_ID);
        final Long orderIdLong = getLongValue(orderId, ORDERITEM_ID);
        if (orderIdLong >= relationShipIdLong) {
            throw new BadParameterValueException(ORDERITEM_ID + orderIdLong
                    + " must be before " + ORDERITEM_RELATIONSHIP_ID + " " + relationShipId);
        }
        final OrderItem relationship = ProductOrderingTools.findFirstById(items, relationShipId);
        if (null == relationship) {
            throw new MissingParameterException(ORDERITEM_ID + " == " + relationShipId);
        }
        if (
                // No relationship
                null == relationship.getOrderItemRelationship() ||
                // No prerequisite relationship
                OrderItemRelationshipType.HASPREREQUISITE != relationship.getOrderItemRelationship().getType() ||
                // Prerequisite is not the good one
                !StringUtils.equals(relationship.getOrderItemRelationship().getId(), orderId)) {
            throw new BadParameterValueException(ORDERITEM_RELATIONSHIP_ID, relationShipId, orderId);
        }
    }

    protected void checkHasPrerequisite(final List<OrderItem> items, final String orderId, final String relationShipId)
            throws BadParameterValueException, MissingParameterException, BadParameterFormatException,
            MissingParameterException {
        final Long relationShipIdLong = getLongValue(relationShipId, ORDERITEM_RELATIONSHIP_ID);
        final Long orderIdLong = getLongValue(orderId, ORDERITEM_ID);
        if (orderIdLong <= relationShipIdLong) {
            throw new BadParameterValueException(ORDERITEM_ID + orderIdLong
                    + " must be after " + ORDERITEM_RELATIONSHIP_ID + " " + relationShipId);
        }
        final OrderItem relationship = ProductOrderingTools.findFirstById(items, relationShipId);
        if (null == relationship) {
            throw new MissingParameterException(ORDERITEM_ID + " == " + relationShipId);
        }
        if (
                // No relationship
                null == relationship.getOrderItemRelationship() ||
                // No prerequist relationship
                OrderItemRelationshipType.ISPREREQUISITE != relationship.getOrderItemRelationship().getType() ||
                // Prerequiste is not the good one
                !StringUtils.equals(relationship.getOrderItemRelationship().getId(), orderId)) {
            throw new BadParameterValueException(ORDERITEM_RELATIONSHIP_ID, relationShipId, orderId);
        }
    }

    /**
     * Check if the value is a numeric Id throw exception if not
     *
     * @param value
     * @param name
     * @return
     * @throws MissingParameterException
     * @throws BadParameterFormatException
     */
    protected Long getLongValue(final String value, final String name)
            throws MissingParameterException, BadParameterFormatException {
        if (StringUtils.isEmpty(value)) {
            throw new MissingParameterException(name);
        }
        if (!StringUtils.isNumeric(value)) {
            throw new BadParameterFormatException(name, value, "a positive numeric value");
        }
        return Long.parseLong(value);
    }

}
