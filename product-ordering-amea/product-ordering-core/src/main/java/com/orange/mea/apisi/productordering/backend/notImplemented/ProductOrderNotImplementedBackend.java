package com.orange.mea.apisi.productordering.backend.notImplemented;

import org.springframework.stereotype.Component;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.badrequest.BadRequestException;
import com.orange.apibss.common.exceptions.technical.NotImplementedException;
import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.apibss.productOrdering.model.OrderItem;
import com.orange.apibss.productOrdering.model.ProductOrder;
import com.orange.mea.apisi.productordering.backend.BundleBackend;
import com.orange.mea.apisi.productordering.backend.DeactivateOfferBackend;
import com.orange.mea.apisi.productordering.backend.DeleteBackend;
import com.orange.mea.apisi.productordering.backend.EmergencyBackend;

@Component
public class ProductOrderNotImplementedBackend
        implements DeleteBackend, DeactivateOfferBackend, BundleBackend, EmergencyBackend {

	@Override
	public void delete(OrderItem orderItem, ProductOrder productOrder) throws BadRequestException, TechnicalException {
        throw new NotImplementedException("delete");
	}

    @Override
    public void deactivateOffer(OrderItem orderItem, ProductOrder productOrder) throws ApiException {
        throw new NotImplementedException("deactivate offer");
    }

    @Override
    public void addBundle(String msisdn, String productOfferingId) throws ApiException {
        throw new NotImplementedException("Add bundle");
    }

    @Override
    public void addEmergencyCredit(String msisdn, String productOfferingId, String amount) throws ApiException {
        throw new NotImplementedException("Add emergency credit");
    }

    @Override
    public void addEmergencyData(String msisdn, String productOfferingId, String amount) throws ApiException {
        throw new NotImplementedException("Add emergency data");
    }

    @Override
    public void addEmergencyVoice(String msisdn, String productOfferingId, String amount) throws ApiException {
        throw new NotImplementedException("Add emergency voice");
    }

}
