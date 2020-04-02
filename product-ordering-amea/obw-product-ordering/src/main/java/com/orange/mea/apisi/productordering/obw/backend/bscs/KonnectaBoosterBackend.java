package com.orange.mea.apisi.productordering.obw.backend.bscs;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.badrequest.BadParameterFormatException;
import com.orange.apibss.common.exceptions.badrequest.BadParameterValueException;
import com.orange.apibss.common.exceptions.badrequest.BadRequestException;
import com.orange.apibss.common.exceptions.badrequest.MissingParameterException;
import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.apibss.productOrdering.model.OrderItem;
import com.orange.apibss.productOrdering.model.Product;
import com.orange.apibss.productOrdering.model.ProductOffering;
import com.orange.apibss.productOrdering.model.ProductOrder;
import com.orange.apibss.productOrdering.model.ProductRelationShipType;
import com.orange.apibss.productOrdering.model.ProductRelationship;
import com.orange.bscs.api.aop.TransactionalBscs;
import com.orange.bscs.model.BscsContract;
import com.orange.bscs.service.exception.BscsInvalidIdException;
import com.orange.mea.apisi.productordering.backend.bscs.BscsContractServiceFacade;
import com.orange.mea.apisi.productordering.exception.OptionAddDeleteException;

@Service
public class KonnectaBoosterBackend {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BscsContractServiceFacade bscsContractService;

    @Autowired
    private BscsBookingRequestServiceFacade bscsBookingRequestService;

    @TransactionalBscs
    public void purchase(OrderItem orderItem, ProductOrder productOrder)
            throws TechnicalException, BadRequestException {
        validate(orderItem);

        String productId = extractProductId(orderItem);
        final String[] split = productId.split("\\|");
        final String contractId = split[0];
        final String servicePackageId = split[2];
        final String serviceId = orderItem.getProductOffering().getId();

        try {
            // read contract
            final BscsContract contractDetail = bscsContractService.getContract(contractId);
            // booking request
            bscsBookingRequestService.bookingRequest(contractId, contractDetail.getCustomerId(), serviceId,
                    servicePackageId);
        } catch (BscsInvalidIdException e) {
            logger.error("Konnecta booster error: " + e.getMessage());
            throw new OptionAddDeleteException(serviceId, servicePackageId, contractId, e.getMessage());
        }
    }

    private String extractProductId(OrderItem orderItem) {
        final Product product = orderItem.getProduct();
        for (ProductRelationship relation : product.getProductRelationship()) {
            if (relation.getType() == ProductRelationShipType.ISCONTAINEDIN) {
                return relation.getProduct().getId();
            }
        }
        // the validation prevents this situation from happening
        return "";
    }

    private void validate(OrderItem orderItem)
            throws MissingParameterException, BadParameterValueException, BadParameterFormatException {
        final Product product = orderItem.getProduct();
        final ProductOffering productOffering = orderItem.getProductOffering();
        if (StringUtils.isBlank(productOffering.getId())) {
            throw new MissingParameterException("orderItem.productOffering.id");
        }
        if (null == product) {
            throw new MissingParameterException("orderItem.product");
        }
        final List<ProductRelationship> productRelationships = product.getProductRelationship();
        if (productRelationships == null || productRelationships.isEmpty()) {
            throw new MissingParameterException("orderItem.product.productRelationship");
        }

        boolean isContainedIn = false;
        for (ProductRelationship relation : product.getProductRelationship()) {
            if (relation.getType() == ProductRelationShipType.ISCONTAINEDIN) {
                isContainedIn = true;
                if (relation.getProduct() == null || StringUtils.isBlank(relation.getProduct().getId())) {
                    throw new MissingParameterException("orderItem.product.productRelationship.product.id");
                }
                // split id
                final String id = relation.getProduct().getId();
                if (!isServicePackageId(id)) {
                    throw new BadParameterFormatException("orderItem.product.productRelationship.product.id", id,
                            "contractId|C|servicePackageId");
                }
            }
        }
        if (!isContainedIn) {
            throw new BadParameterValueException("orderItem.product.productRelationship.type", "",
                    ProductRelationShipType.ISCONTAINEDIN.toString());
        }
    }

    protected boolean isServicePackageId(String id) {
        // Decoupe de l'id
        final String[] split = id.split("\\|");
        if (split.length != 3 || !"C".equals(split[1])) {
            return false;
        }
        return true;
    }

}
