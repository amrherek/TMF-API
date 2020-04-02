package com.orange.mea.apisi.productordering.transformer;

import java.util.List;

import org.springframework.stereotype.Component;

import com.orange.apibss.productOrdering.model.OrderItem;
import com.orange.apibss.productOrdering.model.OrderItemAction;
import com.orange.apibss.productOrdering.model.ProductOrder;

@Component
public class SimProductOrderTransformer {


    /**
     * Extracts the new Sim number from a product order
     *
     * @param input
     * @return
     */
    public String extractNewSimNumber(final ProductOrder input) {
        final List<OrderItem> items = input.getOrderItem();
        if (items == null) {
            return null;
        }
        for(final OrderItem item : items) {
            if (item.getAction().equals(OrderItemAction.ADDCHARACTERISTIC) && item.getProduct() != null
                    && item.getProduct().getProductCharacteristic() != null
                    && !item.getProduct().getProductCharacteristic().isEmpty()) {
                return item.getProduct().getProductCharacteristic().get(0).getValue();
            }
        }
        return null;
    }

    /**
     * Extracts the old Sim number from a product order
     *
     * @param input
     * @return
     */
    public String extractOldSimNumber(final ProductOrder input) {
        final List<OrderItem> items = input.getOrderItem();
        if (items == null) {
            return null;
        }
        for (final OrderItem item : items) {
            if (item.getAction().equals(OrderItemAction.DELETECHARACTERISTIC) && item.getProduct() != null
                    && item.getProduct().getProductCharacteristic() != null
                    && !item.getProduct().getProductCharacteristic().isEmpty()) {
                return item.getProduct().getProductCharacteristic().get(0).getValue();
            }
        }
        return null;
    }
}
