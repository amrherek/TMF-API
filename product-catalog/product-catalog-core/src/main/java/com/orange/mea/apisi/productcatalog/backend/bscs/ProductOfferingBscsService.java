package com.orange.mea.apisi.productcatalog.backend.bscs;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.badrequest.BadRequestException;
import com.orange.apibss.common.exceptions.badrequest.notfound.NotFoundIdException;
import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.apibss.productCatalog.ProductOffering;
import com.orange.bscs.api.aop.TransactionalBscs;
import com.orange.bscs.model.BscsFreeUnitPackage;
import com.orange.bscs.model.BscsParameter;
import com.orange.bscs.model.BscsRatePlan;
import com.orange.bscs.model.BscsService;
import com.orange.bscs.model.contract.EnumServiceSubType;
import com.orange.bscs.service.BscsContractServiceAdapter;
import com.orange.bscs.service.BscsParameterServiceAdapter;
import com.orange.bscs.service.BscsProductServiceAdapter;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;
import com.orange.mea.apisi.productcatalog.backend.GetAvailableOfferForMigrationBackend;
import com.orange.mea.apisi.productcatalog.backend.GetConfigurationBackend;
import com.orange.mea.apisi.productcatalog.backend.GetEligibleOptionsBackend;
import com.orange.mea.apisi.productcatalog.constants.ProductOfferingConstants;

@Service
public class ProductOfferingBscsService
        implements GetAvailableOfferForMigrationBackend, GetEligibleOptionsBackend, GetConfigurationBackend {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected BscsContractServiceAdapter contractServiceAdapter;
    @Autowired
    protected BscsProductServiceAdapter productServiceAdapter;
    @Autowired
    private BscsParameterServiceAdapter parameterServiceAdapter;
    @Autowired
    protected ProductOfferingBscsTransformer productOfferingTransformer;

    @TransactionalBscs
    @Override
    public List<ProductOffering> getAvailableOfferForMigration(final String offerProductOfferingId)
            throws TechnicalException, BadRequestException {
        BscsRatePlan offerRatePlan;
        try {
            offerRatePlan = productServiceAdapter.getRatePlan(offerProductOfferingId);
        } catch (BscsInvalidIdException e) {
            logger.debug("Unknown rate plan", e);
            return new ArrayList<>();
        }
        // note: payment method indicator is only available from BSCS IXR4
        List<BscsRatePlan> ratePlans = productServiceAdapter.getAllowedRatePlansFromRatePlan(offerProductOfferingId,
                offerRatePlan.getPaymentMethodInd());
        // filter actual rateplan
        List<BscsRatePlan> migrationRatePlans = new ArrayList<>();
        for (BscsRatePlan ratePlan : ratePlans) {
            if (!ratePlan.getNumericCode().equals(offerRatePlan.getNumericCode())) {
                migrationRatePlans.add(ratePlan);
            }
        }
        return productOfferingTransformer.transformForUC2(migrationRatePlans);
    }

    @TransactionalBscs
    @Override
    public List<ProductOffering> getEligibleOptions(final String offerProductOfferingId, String productSpecId)
            throws TechnicalException, BadRequestException {
        if (productSpecId == null || ProductOfferingConstants.SERVICE_BSCS.equalsIgnoreCase(productSpecId)) {
            final List<BscsService> result = productServiceAdapter
                    .getAvailableServicesFromRatePlan(offerProductOfferingId);
            return productOfferingTransformer.transform(result);
        }
        return new ArrayList<>();
    }

    @TransactionalBscs
    @Override
    public ProductOffering getConfiguration(String productOfferingId) throws ApiException {
        try {
            BscsService service = productServiceAdapter.getService(productOfferingId);
            List<BscsParameter> parameters = parameterServiceAdapter.getParameters(productOfferingId);
            List<BscsFreeUnitPackage> freeUnits = null;
            if (isFreeUnit(service)) {
                freeUnits = productServiceAdapter.getFreeUnitPackages('C');
            }
            return productOfferingTransformer.transformForUC1(service, parameters, freeUnits);
        } catch (BscsInvalidIdException e) {
            logger.debug("Unkown productOfferingId", e);
            throw new NotFoundIdException("ProductOffering", productOfferingId);
        } catch (BscsInvalidFieldException e) {
            logger.error("Unkwon assignementLevel", e);
            throw new TechnicalException("Unkwon assignementLevel", e);
        }        
    }

    private boolean isFreeUnit(BscsService service) {
        EnumServiceSubType srvSubType = service.getSubType();
        boolean isFreeUnit = false;
        if (srvSubType != null) {
            switch (srvSubType) {
            case COFU:
            case POFU:
            case POFUL:
                isFreeUnit = true;
                break;
            default:
                // other subTypes are not in enum
                isFreeUnit = false;
                break;
            }
        }
        return isFreeUnit;
    }

}
