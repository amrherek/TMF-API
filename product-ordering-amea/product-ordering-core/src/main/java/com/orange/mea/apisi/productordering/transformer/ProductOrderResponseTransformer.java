package com.orange.mea.apisi.productordering.transformer;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import com.orange.apibss.productOrdering.model.ProductOrder;
import com.orange.apibss.productOrdering.model.State;

@Service
public class ProductOrderResponseTransformer {

	public void doTransform(ProductOrder productOrderRequest, ProductOrder productOrderResponse) {

		// copy id
		productOrderResponse.setId(productOrderRequest.getId());
		
        // copy relatedPublicKey
        productOrderResponse.setRelatedPublicKey(productOrderRequest.getRelatedPublicKey());

		// Updating dates and state
        productOrderResponse.setOrderDate(DateTime.now());
		if (productOrderRequest.getRequestedStartDate() == null) {
			// order has begun
            productOrderResponse.setState(State.INPROGRESS);
		} else {
			productOrderResponse.setRequestedStartDate(productOrderRequest.getRequestedStartDate());
            productOrderResponse.setState(State.ACKNOWLEDGED);
		}
		
		// copy channel
		productOrderResponse.setChannel(productOrderRequest.getChannel());
	}
}
