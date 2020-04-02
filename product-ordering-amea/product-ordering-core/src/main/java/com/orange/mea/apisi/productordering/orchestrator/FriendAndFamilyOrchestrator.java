package com.orange.mea.apisi.productordering.orchestrator;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.badrequest.BadParameterValueException;
import com.orange.apibss.common.exceptions.badrequest.BadRequestException;
import com.orange.apibss.common.exceptions.badrequest.MissingParameterException;
import com.orange.apibss.common.exceptions.badrequest.TooManyParameterException;
import com.orange.apibss.productOrdering.model.Characteristic;
import com.orange.apibss.productOrdering.model.OrderItem;
import com.orange.apibss.productOrdering.model.Product;
import com.orange.apibss.productOrdering.model.ProductOrder;
import com.orange.mea.apisi.productordering.backend.AddCharacteristicFafBackend;
import com.orange.mea.apisi.productordering.backend.DeleteCharacteristicFafBackend;
import com.orange.mea.apisi.productordering.backend.UpdateCharacteristicFafBackend;
import com.orange.mea.apisi.productordering.backend.faf.AddFaf;
import com.orange.mea.apisi.productordering.backend.faf.DeleteFaf;
import com.orange.mea.apisi.productordering.backend.faf.UpdateFaf;
import com.orange.mea.apisi.productordering.constants.ProductOrderingConstants;
import com.orange.mea.apisi.productordering.tools.ProductOrderingTools;

@Component
public class FriendAndFamilyOrchestrator implements AddCharacteristicFafBackend, DeleteCharacteristicFafBackend, UpdateCharacteristicFafBackend{
	
	@Autowired
	private AddFaf addFaf;
	
	@Autowired
	private UpdateFaf updateFaf;

	@Autowired
	private DeleteFaf deleteFaf;

    /**
     * Add a friend and family
     *
     * @param orderItem
     * @throws ApiException
     */
    @Override
    public void addCharacteristicFaf(final OrderItem orderItem, final ProductOrder productOrder) throws ApiException {

		validate(orderItem);

        final Product product = orderItem.getProduct();

        final Characteristic productCharacteristic = product.getProductCharacteristic().get(0);
        final String friendAndFamilyNumber = productCharacteristic.getValue();
        final String msisdn = ProductOrderingTools.extractMsisdn(productOrder.getRelatedPublicKey());

        addFaf.addFaf(msisdn, friendAndFamilyNumber);
    }

    /**
     * Remove a friend and family
     *
     * @param orderItem
     * @throws ApiException
     */
    @Override
    public void deleteCharacteristicFaf(final OrderItem orderItem, final ProductOrder productOrder)
            throws ApiException {

		validate(orderItem);

        final Product product = orderItem.getProduct();

        final Characteristic productCharacteristic = product.getProductCharacteristic().get(0);
        final String friendAndFamilyNumber = productCharacteristic.getValue();

        final String msisdn = ProductOrderingTools.extractMsisdn(productOrder.getRelatedPublicKey());

        deleteFaf.deleteFaf(msisdn, friendAndFamilyNumber);
    }

    /**
     * Update a friend and family by replacing an existing one by a new one
     * 
     * @param deleteOrder
     * @param addOrder
     * @param productOrder
     * @throws ApiException
     */
    @Override
    public void updateCharacteristicFaf(final OrderItem deleteOrder, final OrderItem addOrder,
            final ProductOrder productOrder) throws ApiException {

    	validate(deleteOrder);
    	validate(addOrder);

        final Product deleteProduct = deleteOrder.getProduct();
        final Product addProduct = addOrder.getProduct();

        final String currentNumber = deleteProduct.getProductCharacteristic().get(0).getValue();
        final String newNumber = addProduct.getProductCharacteristic().get(0).getValue();

        final String msisdn = ProductOrderingTools.extractMsisdn(productOrder.getRelatedPublicKey());

        updateFaf.updateFaf(msisdn, currentNumber, newNumber);
    }


    /**
     * Validate the request content
     *
     * @param orderItem
     * @throws BadRequestException
     */
    protected void validate(final OrderItem orderItem) throws BadRequestException {
        final Product product = orderItem.getProduct();
        if (null == product) {
            throw new MissingParameterException("orderItem.product");
        }
        if (null != product.getProductSpecification()
                && !ProductOrderingConstants.FAF.equals(product.getProductSpecification().getId())) {
            throw new BadParameterValueException("orderItem.product.productSpecification.id",
                    product.getProductSpecification().getId(), ProductOrderingConstants.FAF);
        }
        if (null == product.getProductCharacteristic() || product.getProductCharacteristic().isEmpty()) {
            throw new MissingParameterException("orderItem.product.productCharacteristic");
        }
        if (product.getProductCharacteristic().size() != 1) {
            throw new TooManyParameterException("orderItem.product.productCharacteristic", 1);
        }
        final Characteristic productCharacteristic = product.getProductCharacteristic().get(0);
        if (null == productCharacteristic) {
            throw new MissingParameterException("orderItem.product.productCharacteristic");
        }
        if (!StringUtils.equals("faf", productCharacteristic.getName())) {
            throw new BadParameterValueException("orderItem.product.productCharacteristic.name",
                    productCharacteristic.getName(), "faf");
        }
        if (null == productCharacteristic.getValue()) {
            throw new MissingParameterException("orderItem.product.productCharacteristic.value");
        }
    }

}
