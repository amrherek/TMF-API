package com.orange.mea.apisi.productordering.backend;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.productOrdering.model.OrderItem;
import com.orange.apibss.productOrdering.model.ProductOrder;

public interface UpdateCharacteristicFafBackend {
	
	void updateCharacteristicFaf(final OrderItem deleteOrder, final OrderItem addOrder, final ProductOrder productOrder)
            throws ApiException;
	
}
