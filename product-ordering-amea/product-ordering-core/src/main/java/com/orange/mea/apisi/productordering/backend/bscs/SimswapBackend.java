package com.orange.mea.apisi.productordering.backend.bscs;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.productOrdering.model.OrderItem;
import com.orange.apibss.productOrdering.model.ProductOrder;
import com.orange.apibss.productOrdering.model.ProductRelationShipType;
import com.orange.apibss.productOrdering.model.ProductRelationship;
import com.orange.bscs.api.aop.TransactionalBscs;
import com.orange.bscs.model.BscsStorageMedium;
import com.orange.mea.apisi.productordering.backend.UpdateCharacteristicSimBackend;
import com.orange.mea.apisi.productordering.transformer.SimProductOrderTransformer;
import com.orange.mea.apisi.productordering.validator.SimswapInputValidator;

/**
 * Service for sim swapping
 * 
 * @author xbbs3851
 *
 */
@Service
public class SimswapBackend implements UpdateCharacteristicSimBackend {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SimswapInputValidator simswapInputValidator;

	@Autowired
	private BscsSimServiceFacade bscsSimService;

	@Autowired
	private SimProductOrderTransformer simProductOrderTransformer;

    /**
     * Replace a Sim card by another
     * 
     * @param deleteOrder
     * @param addOrder
     * @param productOrder
     * @throws ApiException
     */
    @Override
	@TransactionalBscs
    public void swapSim(OrderItem deleteOrder, OrderItem addOrder, ProductOrder productOrder) throws ApiException {

		logger.debug("Launching product-ordering core swapSim method");
		simswapInputValidator.validateSimswap(deleteOrder, addOrder);

		// Does the new sim exist ?
        BscsStorageMedium sim = searchSim(productOrder);

		// Replacing old sim by new sim
		replaceContractSim(productOrder, sim);

	}

	/**
     * Searches the sim concerned by the product order
     * 
     * @param productOrder
     * @return
     * @throws ApiException
     */
    private BscsStorageMedium searchSim(ProductOrder productOrder) throws ApiException {
		logger.debug("Searching sim");
		String newSimNum = simProductOrderTransformer.extractNewSimNumber(productOrder);

		return bscsSimService.searchSim(newSimNum);

	}

	/**
     * Replaces the sim card in the contract
     * 
     * @param productOrder
     * @param sim
     * @throws ApiException
     */
    private void replaceContractSim(ProductOrder productOrder, BscsStorageMedium sim) throws ApiException {
		logger.debug("Replacing sim in contract");
		String currentSimNum = simProductOrderTransformer.extractOldSimNumber(productOrder);
        Long newSimId = sim.getId();

		String coId = extractProductId(productOrder);
        bscsSimService.replaceContractResource(newSimId, currentSimNum, coId,
                productOrder.getRequestedStartDate() == null ? null : productOrder.getRequestedStartDate().toDate());
	}

	/**
	 * Gets the product id from the orderItem list of a productOrder
	 * 
	 * @param productOrder
	 * @return
	 */
	private String extractProductId(ProductOrder productOrder) {
		List<OrderItem> items = productOrder.getOrderItem();
        if (items == null) {
            return null;
        }
		for (OrderItem item : items) {
            if (item.getProduct() == null) {
                continue;
            }
            for (ProductRelationship pr : item.getProduct().getProductRelationship()) {
                if (pr.getType() == ProductRelationShipType.ISCONTAINEDIN) {
                    return pr.getProduct().getId();
                }
            }
		}
		return null;
	}

}
