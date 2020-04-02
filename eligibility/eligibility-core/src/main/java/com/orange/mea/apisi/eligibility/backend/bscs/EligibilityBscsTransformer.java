package com.orange.mea.apisi.eligibility.backend.bscs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.orange.apibss.eligibility.model.EligibleContract;
import com.orange.apibss.eligibility.model.EligibleOption;
import com.orange.apibss.eligibility.model.Price;
import com.orange.apibss.eligibility.model.ProductOffering;
import com.orange.apibss.eligibility.model.ProductSpecification;
import com.orange.bscs.model.BscsCharges;
import com.orange.bscs.model.BscsRatePlan;
import com.orange.bscs.model.BscsService;
import com.orange.mea.apisi.eligibility.constants.EligibilityConstants;

@Component
public class EligibilityBscsTransformer {

    public List<EligibleOption> transformToEligibleOption(final List<BscsService> services) {
        final List<EligibleOption> result = new ArrayList<>();
        for (final BscsService service : services) {
            // we only keep permanent services and remove event services
            if (service.getIndicator() == 'P') {
                final EligibleOption eligibleOption = new EligibleOption();
                eligibleOption.setCanBeSubscribed(true);
                eligibleOption.setProductOfferingId(service.getCode());
                eligibleOption.setProductOfferingName(service.getLabel());

                // productSpecId
                ProductSpecification productSpecification = new ProductSpecification();
                productSpecification.setId(EligibilityConstants.SERVICE_BSCS);
                eligibleOption.setProductSpecification(productSpecification);

                // price
                eligibleOption.setPrice(mapPrice(service));

                result.add(eligibleOption);
            }
        }
        return result;
    }

    protected Price mapPrice(BscsService service) {
        boolean returnPrice = false;
        Price price = new Price();
        for (final BscsCharges charge : service.getCharges()) {
            if (charge.getChargeType() == 3 || charge.getChargeType() == 6) {
                // recurring or advanced recurring
                returnPrice = true;
                if (price.getTaxIncludedAmount() == null || price.getTaxIncludedAmount() == 0) {
                    price.setTaxIncludedAmount(charge.getChargeAmount().floatValue());
                    price.setCurrencyCode(charge.getChargeCurrency());
                }
            }
        }
        if (returnPrice) {
            return price;
        }
        return null;
    }

    public EligibleContract transformToEligibleContract(List<BscsRatePlan> ratePlans) {
        final EligibleContract contract = new EligibleContract();
        for (BscsRatePlan ratePlan : ratePlans) {
            final ProductOffering productOffering = new ProductOffering();
            productOffering.setId(ratePlan.getCode());
            productOffering.setName(ratePlan.getDescription());
            contract.addProductOfferingItem(productOffering);
        }
        return contract;
    }

}
