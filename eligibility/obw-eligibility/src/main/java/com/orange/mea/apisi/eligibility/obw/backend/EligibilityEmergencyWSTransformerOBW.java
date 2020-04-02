package com.orange.mea.apisi.eligibility.obw.backend;

import org.springframework.stereotype.Component;

import com.orange.apibss.eligibility.model.EligibleCharacteristic;
import com.orange.apibss.eligibility.model.EligibleOption;
import com.orange.apibss.eligibility.model.ProductSpecCharacteristicValues;
import com.orange.apibss.eligibility.model.ProductSpecification;
import com.orange.mea.apisi.eligibility.constants.EligibilityConstants;
import com.orange.mea.apisi.obw.webservice.emergency.TDebitAuthResponse;

@Component
public class EligibilityEmergencyWSTransformerOBW {

    /**
     * 
     * @param response
     * @return
     */
    public EligibleOption transform(TDebitAuthResponse response) {
        EligibleOption option = null;
        // TODO: check resultCode?
        if (response != null && response.getMaxAmount() != null) {
            option = new EligibleOption();
            option.setProductOfferingName("EmergencyCredit");
            option.setCanBeSubscribed(true);

            ProductSpecification productSpecification = new ProductSpecification();
            productSpecification.setId(EligibilityConstants.EMERGENCY_CREDIT);
            option.setProductSpecification(productSpecification);

            EligibleCharacteristic charac = new EligibleCharacteristic();
            charac.setName("maxAmount");
            ProductSpecCharacteristicValues item = new ProductSpecCharacteristicValues();
            item.setValue(response.getMaxAmount());
            charac.addProductSpecCharacteristicValuesItem(item);
            option.addEligibleCharacteristicItem(charac);
        }
        return option;
    }


}
