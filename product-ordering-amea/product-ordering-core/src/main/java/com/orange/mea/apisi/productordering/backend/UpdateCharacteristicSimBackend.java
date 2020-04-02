package com.orange.mea.apisi.productordering.backend;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.productOrdering.model.OrderItem;
import com.orange.apibss.productOrdering.model.ProductOrder;

public interface UpdateCharacteristicSimBackend {

	void swapSim(OrderItem deleteOrder, OrderItem addOrder, ProductOrder productOrder)
            throws ApiException;
			
}
