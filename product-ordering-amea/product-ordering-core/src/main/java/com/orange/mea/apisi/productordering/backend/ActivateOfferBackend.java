package com.orange.mea.apisi.productordering.backend;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.productOrdering.model.OrderItem;
import com.orange.apibss.productOrdering.model.ProductOrder;

public interface ActivateOfferBackend {

	void activateOffer(final OrderItem orderItem, final ProductOrder productOrder,
            final ProductOrder response) throws ApiException;
	
}