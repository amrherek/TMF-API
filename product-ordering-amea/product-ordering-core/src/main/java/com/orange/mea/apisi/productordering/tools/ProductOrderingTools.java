package com.orange.mea.apisi.productordering.tools;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.orange.apibss.common.exceptions.badrequest.BadParameterValueException;
import com.orange.apibss.common.exceptions.badrequest.MissingParameterException;
import com.orange.apibss.productOrdering.model.OrderItem;
import com.orange.apibss.productOrdering.model.PublicKey;
import com.orange.mea.apisi.productordering.constants.ProductOrderingConstants;

/**
 * Tools for product ordering
 *
 * @author Thibault Duperron
 *
 */
public class ProductOrderingTools {

    class OrderItemComparator implements Comparator<OrderItem> {
        @Override
        public int compare(final OrderItem oi1, final OrderItem oi2) {
            return oi1.getId().compareTo(oi2.getId());
        }
    }

    /**
     * Find first or return null
     *
     * @param orderItems
     *            list items
     * @param id
     *            searched id
     * @return the item or null
     */
    public static OrderItem findFirstById(final List<OrderItem> orderItems, final String id) {
        for (final OrderItem orderItem : orderItems) {
            if (StringUtils.equals(orderItem.getId(), id)) {
                return orderItem;
            }
        }
        return null;
    }

    /**
     * Sort orderItems by theirs id
     * 
     * @param list
     */
    public static void sortOrderItems(final List<OrderItem> list) {
        Collections.sort(list, new Comparator<OrderItem>() {
            @Override
            public int compare(final OrderItem oi1, final OrderItem oi2) {
                return oi1.getId().compareTo(oi2.getId());
            }
        });
    }

    /**
     * Get productOfferinf.category (either from orderItem or form
     * orderItem.product)
     * 
     * @param orderItem
     * @return
     */
    public static String getProductOfferingCategory(final OrderItem orderItem) {
        // orderItem.product.productOffering.category
        if (orderItem.getProduct() != null && orderItem.getProduct().getProductOffering() != null
                && orderItem.getProduct().getProductOffering().getCategory() != null) {
            return orderItem.getProduct().getProductOffering().getCategory();
        }
        // orderItem.productOffering.category
        if (orderItem.getProductOffering() != null && orderItem.getProductOffering().getCategory() != null) {
            return orderItem.getProductOffering().getCategory();
        }
        return null;
    }

    /**
     * Return true if the order Item is an offer
     *
     * @param orderItem
     * @return
     */
    public static boolean isOffer(final OrderItem orderItem) {
        String productSpecId = getProductSpecId(orderItem);
        if (productSpecId != null) {
            return ProductOrderingConstants.OFFER.equalsIgnoreCase(productSpecId);
        }
        // For backward compatibility
        String category = getProductOfferingCategory(orderItem);
        return ProductOrderingConstants.OFFER.equalsIgnoreCase(category);
    }

    /**
     * Return true if the order Item is a BSCS service
     *
     * @param orderItem
     * @return
     */
    public static boolean isBscsService(final OrderItem orderItem) {
        String productSpecId = getProductSpecId(orderItem);
        return ProductOrderingConstants.SERVICE_BSCS.equalsIgnoreCase(productSpecId);
    }

    /**
     * Return true if the order Item is a FaF service
     * 
     * @param orderItem
     * @return
     */
    public static boolean isFaf(OrderItem orderItem) {
        String productSpecId = getProductSpecId(orderItem);
        if (productSpecId != null) {
            return ProductOrderingConstants.FAF.equalsIgnoreCase(productSpecId);
        }
        // For backward compatibility
        String category = getProductOfferingCategory(orderItem);
        return ProductOrderingConstants.FAF.equalsIgnoreCase(category);
    }

    /**
     * Get productSpecification.id from orderItem.product if modification=true
     * 
     * @param orderItem
     * @return
     */
    public static String getProductSpecId(OrderItem orderItem) {
        // orderItem.product.productSpecification.id
        if (orderItem.getProduct() != null
                && orderItem.getProduct().getProductSpecification() != null) {
            return orderItem.getProduct().getProductSpecification().getId();
        }
        return null;
    }

    /**
     * Extract msisdn
     *
     * @param relatedPublicKey
     * @return
     * @throws MissingParameterException
     * @throws BadParameterValueException
     */
    public static String extractMsisdn(final List<PublicKey> relatedPublicKey)
            throws MissingParameterException, BadParameterValueException {
        if (null == relatedPublicKey || relatedPublicKey.size() != 1) {
            throw new MissingParameterException("relatedPublicKey");
        }
        final PublicKey key = relatedPublicKey.get(0);
        if(!"customerMSISDN".equals(key.getName())){
            throw new BadParameterValueException("relatedPublicKey.name", key.getName(), "customerMSISDN");
        }
        final String msisdn = key.getId();
        if (StringUtils.isEmpty(msisdn)) {
            throw new MissingParameterException("relatedPublicKey.id");
        }
        return msisdn;
    }


}
