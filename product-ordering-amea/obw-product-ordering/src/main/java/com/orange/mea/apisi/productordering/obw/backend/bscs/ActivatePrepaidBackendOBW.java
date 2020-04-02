package com.orange.mea.apisi.productordering.obw.backend.bscs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.party.model.Individual;
import com.orange.apibss.productOrdering.model.OrderItem;
import com.orange.apibss.productOrdering.model.ProductOrder;
import com.orange.bscs.api.aop.TransactionalBscs;
import com.orange.bscs.api.connection.ConnectionHolder;
import com.orange.bscs.model.businesspartner.EnumContractStatus;
import com.orange.mea.apisi.productordering.backend.bscs.ActivatePrepaidBackend;
import com.orange.mea.apisi.productordering.exception.ContractStateChangeException;
import com.orange.mea.apisi.productordering.exception.ServiceStateChangeException;

/**
 * Service for prepaid sim activation specific for OBW
 *
 * @author xbbs3851
 *
 */
@Service
@Primary
public class ActivatePrepaidBackendOBW extends ActivatePrepaidBackend {

    @Value("${serviceToDeactivate.snCodePub}")
    private String snCodePubtoDeactivate;

    @Override
    @TransactionalBscs
    public void activateOffer(final OrderItem orderItem, final ProductOrder productOrder, final ProductOrder response)
            throws ApiException {
        this.logger.debug("Activating prepaid");
        this.activatePrepaidInputValidator.validateActivatePrepaid(orderItem, productOrder);

        this.logger.debug("Updating individual");
        final Individual individual = this.partyClient.updateIndividual(productOrder);

        this.logger.debug("deactivate service");

        String coIdPub = orderItem.getProduct().getId();
        try {
            bscsContractService.modifyServiceStatus(coIdPub, snCodePubtoDeactivate, EnumContractStatus.DEACTIVATED,
                    productOrder.getRequestedStartDate() == null ? null
                            : productOrder.getRequestedStartDate().toDate());
        } catch (ServiceStateChangeException e) {
            throw new ContractStateChangeException(coIdPub, e.getMessage());
        }
        // Commit the update
        ConnectionHolder.getCurrentConnection().commit();

        this.activatePrepaidProductOrderResponseTransformer.doTransform(productOrder, individual, response);
    }

}
