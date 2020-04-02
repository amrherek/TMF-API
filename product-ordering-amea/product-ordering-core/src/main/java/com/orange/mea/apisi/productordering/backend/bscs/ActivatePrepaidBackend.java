package com.orange.mea.apisi.productordering.backend.bscs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.party.model.Individual;
import com.orange.apibss.productOrdering.model.OrderItem;
import com.orange.apibss.productOrdering.model.ProductOrder;
import com.orange.bscs.api.aop.TransactionalBscs;
import com.orange.mea.apisi.productordering.backend.ActivateOfferBackend;
import com.orange.mea.apisi.productordering.backend.api.PartyClient;
import com.orange.mea.apisi.productordering.transformer.ActivatePrepaidProductOrderResponseTransformer;
import com.orange.mea.apisi.productordering.validator.ActivatePrepaidInputValidator;
import com.orange.mea.apisi.productordering.validator.ProductInputValidator;

/**
 * Service for prepaid sim activation
 *
 * @author xbbs3851
 *
 */
@Service
public class ActivatePrepaidBackend implements ActivateOfferBackend {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected BscsContractServiceFacade bscsContractService;

    @Autowired
    protected PartyClient partyClient;

    @Autowired
    protected ActivatePrepaidProductOrderResponseTransformer activatePrepaidProductOrderResponseTransformer;

    @Autowired
    protected ActivatePrepaidInputValidator activatePrepaidInputValidator;

    @Autowired
    protected ProductInputValidator productInputValidator;

    @Override
	@TransactionalBscs
    public void activateOffer(final OrderItem orderItem, final ProductOrder productOrder,
            final ProductOrder response) throws ApiException {
        this.logger.debug("Activating prepaid");
        this.activatePrepaidInputValidator.validateActivatePrepaid(orderItem, productOrder);

        this.logger.debug("Updating individual");
        final Individual individual = this.partyClient.updateIndividual(productOrder);

        this.logger.debug("Updating contract");
        final String coId = orderItem.getProduct().getId();
        Long statusReason = productInputValidator
                .validateAndExtractStatusReason(orderItem.getProduct().getProductCharacteristic(), orderItem.getId());
        this.bscsContractService.activateContract(coId, statusReason,
                productOrder.getRequestedStartDate() == null ? null : productOrder.getRequestedStartDate().toDate());

        this.activatePrepaidProductOrderResponseTransformer.doTransform(productOrder, individual, response);
    }

}
