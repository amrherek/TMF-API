package com.orange.mea.apisi.productordering.validator;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.badrequest.BadParameterValueException;
import com.orange.apibss.common.exceptions.badrequest.BadRequestException;
import com.orange.apibss.common.exceptions.badrequest.MissingParameterException;
import com.orange.apibss.common.exceptions.badrequest.TooManyParameterException;
import com.orange.apibss.productOrdering.model.OrderItem;
import com.orange.apibss.productOrdering.model.Product;
import com.orange.apibss.productOrdering.model.ProductOrder;
import com.orange.apibss.productOrdering.model.RelatedIndividual;
import com.orange.apibss.productOrdering.model.Role;

/**
 * Class for validating a ProductOrder before launching activate prepaid process
 *
 * @author xbbs3851
 *
 */
@Service
public class ActivatePrepaidInputValidator {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Verifies if the request is valid for activating prepaid
     * 
     * @param orderItem
     * @param productOrder
     * @throws BadRequestException
     */
    public void validateActivatePrepaid(final OrderItem orderItem, final ProductOrder productOrder) throws BadRequestException {

        logger.debug("Validating produtOrder for activating prepaid");

        dataValidateOrderItem(orderItem);
        dataValidateRelatedParty(productOrder);
    }

    protected void dataValidateRelatedParty(final ProductOrder productOrder) throws BadRequestException {
        final List<RelatedIndividual> relatedIndividuals = productOrder.getRelatedIndividual();
        if (relatedIndividuals == null || relatedIndividuals.isEmpty()) {
            // no individual to update
            return;
        }
        if (relatedIndividuals.size() != 1) {
            throw new TooManyParameterException("relatedIndividual", 1);
        }

        final RelatedIndividual relatedIndividual = relatedIndividuals.get(0);

        if (StringUtils.isBlank(relatedIndividual.getId())) {
            throw new MissingParameterException("relatedIndividual.id");
        }
        if (!relatedIndividual.getRole().equals(Role.CUSTOMER)) {
            throw new BadParameterValueException("relatedIndividual.role", relatedIndividual.getRole().toString(),
                    Role.CUSTOMER.toString());
        }
    }

    /**
     * Method to validate the basic and common request structure
     *
     * @param order
     * @throws BadRequestException
     */
    protected void dataValidateOrderItem(final OrderItem order) throws BadRequestException {

        if (order.getProduct() == null) {
            throw new MissingParameterException("orderItem.product");
        }
        dataValidateProduct(order.getProduct());
    }

    protected String dataValidateProduct(final Product product) throws BadRequestException {
        final String coId = product.getId();

        if (StringUtils.isBlank(coId)) {
            throw new MissingParameterException("orderItem.product.id");
        }

        return coId;
    }

}
