package com.orange.mea.apisi.productordering.obw.orchestrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.badrequest.BadParameterValueException;
import com.orange.apibss.productOrdering.model.OrderItem;
import com.orange.apibss.productOrdering.model.ProductOrder;
import com.orange.mea.apisi.productordering.constants.ProductOrderingConstants;
import com.orange.mea.apisi.productordering.obw.backend.bscs.KonnectaBoosterBackend;
import com.orange.mea.apisi.productordering.orchestrator.ProductOrderOrchestrator;
import com.orange.mea.apisi.productordering.tools.ProductOrderingTools;

@Service
@Primary
public class ProductOrderOrchestratorOBW extends ProductOrderOrchestrator {

    @Autowired
    private KonnectaBoosterBackend konnectaBoosterService;

    @Override
    public void add(final OrderItem orderItem, final ProductOrder productOrder) throws ApiException {
        String productSpecId = ProductOrderingTools.getProductSpecId(orderItem);
        String category = ProductOrderingTools.getProductOfferingCategory(orderItem);
        if ("boostData".equalsIgnoreCase(productSpecId)
                || (productSpecId == null && ProductOrderingConstants.OPTION.equalsIgnoreCase(category))) {
            konnectaBoosterService.purchase(orderItem, productOrder);
        } else {
            try {
                super.add(orderItem, productOrder);
            } catch (BadParameterValueException e) {
                if ("orderItem.productOffering.productSpecification.id".equals(e.getParameterName())) {
                    throw new BadParameterValueException("orderItem.productOffering.productSpecification.id",
                            productSpecId,
                            ProductOrderingConstants.SERVICE_BSCS + " or " + ProductOrderingConstants.TRANSFER_CREDIT
                                    + " or " + ProductOrderingConstants.BUNDLE_SERVICES + " or boostData");
                }
                throw e;
            }
        }
    }

}
