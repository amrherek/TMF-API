package com.orange.mea.apisi.productordering.validator;

import java.util.List;

import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.badrequest.BadParameterFormatException;
import com.orange.apibss.common.exceptions.badrequest.BadParameterValueException;
import com.orange.apibss.common.exceptions.badrequest.BadRequestException;
import com.orange.apibss.common.exceptions.badrequest.MissingParameterException;
import com.orange.apibss.productOrdering.model.Characteristic;

@Service
public class ProductInputValidator {

    /**
     * Validate productCharacteristics searching for statusReason
     * 
     * @param productCharacteristics
     * @param orderItemId
     * @return
     * @throws BadRequestException
     */
    public Long validateAndExtractStatusReason(List<Characteristic> productCharacteristics, String orderItemId)
            throws BadRequestException {
        if (productCharacteristics == null) {
            return null;
        }
        for (Characteristic charac : productCharacteristics) {
            if (charac.getName() == null) {
                throw new MissingParameterException(
                        "orderItem.product.productCharacteristic.name for orderItem " + orderItemId);
            }
            if (!"statusReason".equals(charac.getName())) {
                throw new BadParameterValueException(
                        "orderItem.product.productCharacteristic.name for orderItem " + orderItemId, charac.getName(),
                        "statusReason");
            }
            if (charac.getValue() == null) {
                throw new MissingParameterException(
                        "orderItem.product.productCharacteristic.value for orderItem " + orderItemId);
            }
            try {
                return Long.parseLong(charac.getValue());
            } catch (final NumberFormatException nfe) {
                throw new BadParameterFormatException(
                        "orderItem.product.productCharacteristic.value for orderItem " + orderItemId, charac.getValue(),
                        "a numeric value (statusReason)");
            }
        }
        return null;
    }

}
