package com.orange.mea.apisi.productcatalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.audit.AuditContext;
import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.badrequest.BadParameterValueException;
import com.orange.apibss.productCatalog.ProductOffering;
import com.orange.mea.apisi.productcatalog.backend.FindConfigurationBackend;
import com.orange.mea.apisi.productcatalog.backend.GetAvailableOfferForMigrationBackend;
import com.orange.mea.apisi.productcatalog.backend.GetConfigurationBackend;
import com.orange.mea.apisi.productcatalog.backend.GetEligibleOptionsBackend;
import com.orange.mea.apisi.productcatalog.enums.ProductOfferOperationTypeEnum;

/**
 * Service for managing product Offering
 *
 * @author xbbs3851
 *
 */
@Service
public class ProductOfferingService {

    @Autowired
    private GetAvailableOfferForMigrationBackend getAvailableOfferForMigrationBackend;

    @Autowired
    private GetConfigurationBackend getConfigurationBackend;

    @Autowired
    private FindConfigurationBackend findConfigurationBackend;

    @Autowired
    private GetEligibleOptionsBackend getEligibleOptionsBackend;

    @Autowired
    private AuditContext auditContext;

    /**
     * Gets a list of offers depending on parameters
     *
     * @param offerProductOfferingId
     * @param operationType
     * @param productSpecId
     * @return
     * @throws ApiException
     */
    public List<ProductOffering> getOffers(final String offerProductOfferingId,
            final ProductOfferOperationTypeEnum operationType, final String productSpecId) throws ApiException {
        switch (operationType) {
        case MIGRATIONFROM:
            // UC2
            auditContext.getAuditEvent().getUseCase().setMethod("findProductOfferingsForMigration");
            return getAvailableOfferForMigrationBackend.getAvailableOfferForMigration(offerProductOfferingId);
        case COMPATIBILITYWITH:
            // UC3
            auditContext.getAuditEvent().getUseCase().setMethod("findProductOfferingsForCompatibility");
            return getEligibleOptionsBackend.getEligibleOptions(offerProductOfferingId, productSpecId);
        case PARAMETERCONFIGURATION:
            // UC1
            auditContext.getAuditEvent().getUseCase().setMethod("findProductOfferingsConfiguration");
            return findConfigurationBackend.findConfiguration(offerProductOfferingId, productSpecId);
        default:
            throw new BadParameterValueException("operationType", operationType.toString(),
                    ProductOfferOperationTypeEnum.MIGRATIONFROM.toString() + " or "
                            + ProductOfferOperationTypeEnum.COMPATIBILITYWITH.toString() + " or "
                            + ProductOfferOperationTypeEnum.PARAMETERCONFIGURATION.toString());
        }
    }

    /**
     * Get a productOffering
     * 
     * @param productOfferingId
     * @param operationType
     * @return
     * @throws ApiException
     */
    public ProductOffering getProductOffering(String productOfferingId, ProductOfferOperationTypeEnum operationType)
            throws ApiException {
        switch (operationType) {
        case PARAMETERCONFIGURATION:
            // UC1
            auditContext.getAuditEvent().getUseCase().setMethod("getProductOfferingConfiguration");
            return getConfigurationBackend.getConfiguration(productOfferingId);
        case MIGRATIONFROM:
        case COMPATIBILITYWITH:
        default:
            throw new BadParameterValueException("operationType", operationType.toString(),
                    ProductOfferOperationTypeEnum.PARAMETERCONFIGURATION.toString());
        }
    }

}
