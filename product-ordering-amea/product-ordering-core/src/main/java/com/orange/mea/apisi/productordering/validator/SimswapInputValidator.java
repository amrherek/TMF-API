package com.orange.mea.apisi.productordering.validator;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.badrequest.BadParameterValueException;
import com.orange.apibss.common.exceptions.badrequest.BadRequestException;
import com.orange.apibss.common.exceptions.badrequest.MissingParameterException;
import com.orange.apibss.productOrdering.model.Characteristic;
import com.orange.apibss.productOrdering.model.OrderItem;
import com.orange.apibss.productOrdering.model.Product;
import com.orange.apibss.productOrdering.model.ProductRelationShipType;
import com.orange.apibss.productOrdering.model.ProductRelationship;

/**
 * Class for validating a ProductOrder before launching sim swap process
 * 
 * @author xbbs3851
 *
 */
@Service
public class SimswapInputValidator {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${simcard.productOffering.id:simCard}")
	private String simcardProductOfferingId;

	@Value("${iccId:iccId}")
	private String iccId;

    /**
     * Verifies if the request is valid for sim swapping
     * 
     * @param deleteOrder
     * @param addOrder
     * @throws BadRequestException
     */
	public void validateSimswap(OrderItem deleteOrder, OrderItem addOrder) throws BadRequestException {
		logger.debug("Validating productOrder for sim swapping");

		// Checking that product is valid
		checkProducts(deleteOrder, addOrder);
	}

	/**
	 * Checks that the product id is the same for both actions
	 * 
	 * @param deleteOrder
	 * @param addOrder
	 * @throws BadRequestException
	 */
	private void checkProducts(OrderItem deleteOrder, OrderItem addOrder) throws BadRequestException {
		String oldCoId = dataValidateProduct(deleteOrder.getProduct());
		String newCoId = dataValidateProduct(addOrder.getProduct());

		if (oldCoId == null || newCoId == null || !oldCoId.equals(newCoId)) {
			throw new BadParameterValueException("Contract id must be the same in each orderItem for sim swap");
		}

	}

	/**
	 * Validates a product
	 * 
	 * @param product
	 * @return
	 * @throws BadRequestException
	 */
    protected String dataValidateProduct(Product product) throws BadRequestException {
		// validate product offering
        if (product.getProductOffering() == null || StringUtils.isBlank(product.getProductOffering().getId())) {
            throw new MissingParameterException("orderItem.product.productOffering.id");
        }
        if (!product.getProductOffering().getId().equals(simcardProductOfferingId)) {
			throw new BadParameterValueException("orderItem.product.productOffering.id",
					product.getProductOffering().getId(), simcardProductOfferingId);
		}

		// validate characteristic
		checkProductCharacteristics(product.getProductCharacteristic());

		// validate relationship
        if (product.getProductRelationship() == null || product.getProductRelationship().isEmpty()) {
			throw new BadRequestException("orderItem.product.productRelationship must not be empty for sim swapping");
		}

		for (ProductRelationship relation : product.getProductRelationship()) {
            if (relation.getType() == ProductRelationShipType.ISCONTAINEDIN) {
				if (relation.getProduct() == null || StringUtils.isBlank(relation.getProduct().getId())) {
					throw new BadRequestException(
							"orderItem.product.productRelationship.product.id must not be null for sim swapping");
				}
				return relation.getProduct().getId();
			}
		}
		throw new BadRequestException("ProductRelationship must contain contract id for sim swapping");
	}

	/**
	 * Validate product characteristics
	 * 
	 * @param characteristics
	 * @throws BadRequestException
	 */
	private void checkProductCharacteristics(List<Characteristic> characteristics) throws BadRequestException {
        if (characteristics == null || characteristics.isEmpty()) {
            throw new MissingParameterException("orderItem.product.productCharacteristic");
		}
		for (Characteristic car : characteristics) {
			if (car.getName() == null || !car.getName().equals(iccId)) {
				throw new BadParameterValueException("orderItem.product.productCharacteristic.name", car.getName(),
						iccId);
			}
			if (StringUtils.isEmpty(car.getValue())) {
                throw new MissingParameterException("orderItem.product.productCharacteristic.value");
			}
		}
	}

}
