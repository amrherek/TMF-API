package com.orange.mea.apisi.productordering.backend.bscs;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.badrequest.BadParameterValueException;
import com.orange.apibss.common.exceptions.badrequest.MissingParameterException;
import com.orange.apibss.productOrdering.model.OrderItem;
import com.orange.apibss.productOrdering.model.Product;
import com.orange.apibss.productOrdering.model.ProductOffering;
import com.orange.apibss.productOrdering.model.ProductOrder;
import com.orange.bscs.api.aop.TransactionalBscs;
import com.orange.mea.apisi.productordering.backend.MigrateOfferBackend;
import com.orange.mea.apisi.productordering.backend.ReactivateOfferBackend;
import com.orange.mea.apisi.productordering.backend.SuspendOfferBackend;
import com.orange.mea.apisi.productordering.validator.ProductInputValidator;

@Service
public class OfferBackend implements ReactivateOfferBackend, MigrateOfferBackend, SuspendOfferBackend {

    @Autowired
    private BscsContractServiceFacade bscsContractService;
    @Autowired
    private ProductInputValidator productInputValidator;

    /**
     * Processs a suspend order item
     *
     * @param orderItem
     *            order item
     * @param productOrder
     *            product order for informations
     * @throws ApiException
     */
    @TransactionalBscs
    @Override
    public void suspendOffer(final OrderItem orderItem, final ProductOrder productOrder) throws ApiException {
        final Product product = orderItem.getProduct();
        if (null == product || StringUtils.isBlank(product.getId())) {
            throw new MissingParameterException("orderItem.product.id for orderItem " + orderItem.getId());
        }
        Long statusReason = productInputValidator.validateAndExtractStatusReason(product.getProductCharacteristic(),
                orderItem.getId());
        bscsContractService.suspend(product.getId(), statusReason,
                productOrder.getRequestedStartDate() == null ? null : productOrder.getRequestedStartDate().toDate());
    }

    /**
     * Reactivate offer
     * 
     * @param orderItem
     * @param productOrder
     * @throws ApiException
     */
    @Override
	@TransactionalBscs
    public void reactivateOffer(final OrderItem orderItem, final ProductOrder productOrder) throws ApiException {
        final Product product = orderItem.getProduct();
        if (null == product || StringUtils.isBlank(product.getId())) {
            throw new MissingParameterException("orderItem.product.id for orderItem " + orderItem.getId());
        }
        Long statusReason = productInputValidator.validateAndExtractStatusReason(product.getProductCharacteristic(),
                orderItem.getId());
        bscsContractService.reactivateContract(product.getId(), statusReason,
                productOrder.getRequestedStartDate() == null ? null : 
                productOrder.getRequestedStartDate().toDate());
    }

    /**
     * Migrate Offer (change rate plan)
     * 
     * @param orderItem
     * @param productOrder
     */
    @Override
	@TransactionalBscs
    public void migrateOffer(OrderItem orderItem, ProductOrder productOrder) throws ApiException {
        // validation
        final ProductOffering productOffering = orderItem.getProductOffering();
        if (null == productOffering || StringUtils.isBlank(productOffering.getId())) {
            throw new MissingParameterException("orderItem.productOffering.id");
        }
        if (orderItem.getProduct() == null || StringUtils.isBlank(orderItem.getProduct().getId())) {
            throw new MissingParameterException("orderItem.product.id");
        }
        if (productOrder.getRequestedStartDate() != null) {
            throw new BadParameterValueException("requestedStartDate", productOrder.getRequestedStartDate().toString(),
                    "null");
        }
        bscsContractService.changeRatePlan(orderItem.getProduct().getId(), productOffering.getId());
    }

}
