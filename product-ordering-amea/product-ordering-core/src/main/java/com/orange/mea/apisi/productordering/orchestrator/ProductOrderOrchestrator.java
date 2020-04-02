package com.orange.mea.apisi.productordering.orchestrator;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.badrequest.BadParameterValueException;
import com.orange.apibss.common.exceptions.badrequest.MissingParameterException;
import com.orange.apibss.common.exceptions.badrequest.TooManyParameterException;
import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.apibss.productOrdering.model.Characteristic;
import com.orange.apibss.productOrdering.model.OrderItem;
import com.orange.apibss.productOrdering.model.ProductOffering;
import com.orange.apibss.productOrdering.model.ProductOrder;
import com.orange.mea.apisi.productordering.backend.ActivateOfferBackend;
import com.orange.mea.apisi.productordering.backend.ActivateServiceBackend;
import com.orange.mea.apisi.productordering.backend.AddCharacteristicFafBackend;
import com.orange.mea.apisi.productordering.backend.AddServiceBackend;
import com.orange.mea.apisi.productordering.backend.BundleBackend;
import com.orange.mea.apisi.productordering.backend.DeactivateOfferBackend;
import com.orange.mea.apisi.productordering.backend.DeactivateServiceBackend;
import com.orange.mea.apisi.productordering.backend.DeleteBackend;
import com.orange.mea.apisi.productordering.backend.DeleteCharacteristicFafBackend;
import com.orange.mea.apisi.productordering.backend.EmergencyBackend;
import com.orange.mea.apisi.productordering.backend.MigrateOfferBackend;
import com.orange.mea.apisi.productordering.backend.ModifyServiceCharacteristicBackend;
import com.orange.mea.apisi.productordering.backend.ReactivateOfferBackend;
import com.orange.mea.apisi.productordering.backend.SuspendOfferBackend;
import com.orange.mea.apisi.productordering.backend.UpdateCharacteristicFafBackend;
import com.orange.mea.apisi.productordering.backend.UpdateCharacteristicSimBackend;
import com.orange.mea.apisi.productordering.constants.ProductOrderingConstants;
import com.orange.mea.apisi.productordering.tools.ProductOrderingTools;

@Component
public class ProductOrderOrchestrator {

    private static final String ORDERITEM_PRODUCT_PRODUCTSPECIFICATION_ID = "orderItem.product.productSpecification.id";

	@Value("${simcard.productOffering.id:simCard}")
	private String simcardProductOfferingId;

	@Autowired
	private ActivateOfferBackend activateOfferBackend;

	@Autowired
	private ActivateServiceBackend activateServiceBackend;

	@Autowired
    private AddServiceBackend addServiceBackend;
	@Autowired
	private DeleteBackend deleteBackend;
	@Autowired
	private DeactivateServiceBackend deactivateServiceBackend;
	@Autowired
	private ReactivateOfferBackend reactivateOfferBackend;
	@Autowired
	private MigrateOfferBackend migrateOfferBackend;
	@Autowired
	private SuspendOfferBackend suspendOfferBackend;
	@Autowired
	private ModifyServiceCharacteristicBackend modifyServiceCharacteristicBackend;
    @Autowired
    private DeactivateOfferBackend deactivateOfferBackend;

	@Autowired
	private UpdateCharacteristicSimBackend updateCharacteristicSimBackend;

	@Autowired
	private AddCharacteristicFafBackend addCharacteristicFafBackend;
	@Autowired
	private DeleteCharacteristicFafBackend deleteCharacteristicFafBackend;
	@Autowired
	private UpdateCharacteristicFafBackend updateCharacteristicFafBackend;
	
    @Autowired
    private TransferOrchestrator transferOrchestrator;
    @Autowired
    private BundleBackend bundleBackend;
    @Autowired
    private EmergencyBackend emergencyBackend;
	
	/**
     * Activate action
     *
     * @param orderItem
     * @param productOrder
     * @param response
     * @throws ApiException
     */
    public void activate(OrderItem orderItem, ProductOrder productOrder, ProductOrder response) throws ApiException {
        String productSpecId = ProductOrderingTools.getProductSpecId(orderItem);
        if (ProductOrderingTools.isOffer(orderItem)) {
			// Activate offer
			activateOfferBackend.activateOffer(orderItem, productOrder, response);
        } else if (ProductOrderingTools.isBscsService(orderItem)) {
            // Activate service
            activateServiceBackend.activateService(orderItem, productOrder);
        } else if (productSpecId == null) {
            // For backward compatibility: activate service
			activateServiceBackend.activateService(orderItem, productOrder);
        } else {
            throw new BadParameterValueException("orderItem.product.productSpecification.id", productSpecId,
                    ProductOrderingConstants.OFFER + " or " + ProductOrderingConstants.SERVICE_BSCS);
		}
	}

	/**
     * 
     * @param orderItem
     * @param productOrder
     * @throws ApiException
     */
    public void addCharacteristic(OrderItem orderItem, ProductOrder productOrder) throws ApiException {
        String productSpecId = ProductOrderingTools.getProductSpecId(orderItem);
        if (ProductOrderingTools.isFaf(orderItem)) {
            addCharacteristicFafBackend.addCharacteristicFaf(orderItem, productOrder);
        } else if (StringUtils.isBlank(productSpecId)) {
            throw new MissingParameterException(ORDERITEM_PRODUCT_PRODUCTSPECIFICATION_ID);
        } else {
            throw new BadParameterValueException(ORDERITEM_PRODUCT_PRODUCTSPECIFICATION_ID, productSpecId,
                    "'" + ProductOrderingConstants.FAF + "' for order item #" + orderItem.getId());
        }
	}

	/**
     * 
     * @param orderItem
     * @param productOrder
     * @throws ApiException
     */
    public void deleteCharacteristic(OrderItem orderItem, ProductOrder productOrder) throws ApiException {
        String productSpecId = ProductOrderingTools.getProductSpecId(orderItem);
        if (ProductOrderingTools.isFaf(orderItem)) {
            deleteCharacteristicFafBackend.deleteCharacteristicFaf(orderItem, productOrder);
        } else if (StringUtils.isBlank(productSpecId)) {
            throw new MissingParameterException(ORDERITEM_PRODUCT_PRODUCTSPECIFICATION_ID);
        } else {
            throw new BadParameterValueException(ORDERITEM_PRODUCT_PRODUCTSPECIFICATION_ID, productSpecId,
                    "'" + ProductOrderingConstants.FAF + "' for order item #" + orderItem.getId());
        }
	}

	/**
     * 
     * @param firstOrderItem
     * @param secondOrderItem
     * @param productOrder
     * @param response
     * @throws ApiException
     */
	public void updateCharacteristic(OrderItem firstOrderItem, OrderItem secondOrderItem, ProductOrder productOrder,
            ProductOrder response) throws ApiException {
        if (ProductOrderingTools.isFaf(firstOrderItem)) {
            // FaF
            updateCharacteristicFafBackend.updateCharacteristicFaf(firstOrderItem, secondOrderItem, productOrder);
            return;
        }
        if (null == firstOrderItem.getProduct()) {
            throw new MissingParameterException("orderItem.product");
        }
        if ((firstOrderItem.getProduct().getProductSpecification() != null
                && ProductOrderingConstants.ACCESS_SERVICE
                        .equals(firstOrderItem.getProduct().getProductSpecification().getId()))
                || firstOrderItem.getProduct().getProductSpecification() == null) {
            // accessService or no productSpecId => sim swap
            if (firstOrderItem.getProduct().getProductOffering() == null) {
                throw new MissingParameterException("orderItem.product.productOffering");
            }
            final String productOfferingRefId = firstOrderItem.getProduct().getProductOffering().getId();
            if (productOfferingRefId == null) {
                throw new MissingParameterException("orderItem.product.productOffering.id");
            }
            if (simcardProductOfferingId.equalsIgnoreCase(productOfferingRefId)) {
                updateCharacteristicSimBackend.swapSim(firstOrderItem, secondOrderItem, productOrder);
                return;
            }
            throw new BadParameterValueException("orderItem.product.productOffering.id", productOfferingRefId,
                        simcardProductOfferingId);
        }

        if (firstOrderItem.getProduct().getProductSpecification() == null
                || StringUtils.isBlank(firstOrderItem.getProduct().getProductSpecification().getId())) {
            throw new MissingParameterException(ORDERITEM_PRODUCT_PRODUCTSPECIFICATION_ID);
        }
        throw new BadParameterValueException(ORDERITEM_PRODUCT_PRODUCTSPECIFICATION_ID,
                    firstOrderItem.getProduct().getProductSpecification().getId(),
                "'" + ProductOrderingConstants.FAF + "' or '" + ProductOrderingConstants.ACCESS_SERVICE
                        + "' for order item #" + firstOrderItem.getId());
	}

	/**
     * 
     * @param orderItem
     * @param productOrder
     * @throws ApiException
     */
    public void add(OrderItem orderItem, ProductOrder productOrder) throws ApiException {
        String productSpecId = ProductOrderingTools.getProductSpecId(orderItem);
        if (ProductOrderingConstants.TRANSFER_DATA.equalsIgnoreCase(productSpecId)) {
            transferOrchestrator.transferData(orderItem, productOrder);
        } else if (ProductOrderingConstants.TRANSFER_CREDIT.equalsIgnoreCase(productSpecId)) {
            transferOrchestrator.transferCredit(orderItem, productOrder);
        } else if (ProductOrderingConstants.TRANSFER_CREDIT_POSTPAID.equalsIgnoreCase(productSpecId)) {
            transferOrchestrator.transferCreditPostpaid(orderItem, productOrder);
        } else if (ProductOrderingConstants.BUNDLE_SERVICES.contains(productSpecId)) {
            addBundle(orderItem, productOrder);
        } else if (ProductOrderingConstants.EMERGENCY_SERVICES.contains(productSpecId)) {
            addEmergencyService(orderItem, productOrder, productSpecId);
        } else if (ProductOrderingTools.isBscsService(orderItem)) {
            // Add service
            addServiceBackend.addService(orderItem, productOrder);
        } else if (productSpecId == null) {
            // For backward compatibility: add service
            addServiceBackend.addService(orderItem, productOrder);
        } else {
            throw new BadParameterValueException("orderItem.product.productSpecification.id", productSpecId,
                    ProductOrderingConstants.TRANSFER_DATA + " or " + ProductOrderingConstants.TRANSFER_CREDIT + " or "
                            + ProductOrderingConstants.TRANSFER_CREDIT_POSTPAID + " or "
                            + ProductOrderingConstants.BUNDLE_SERVICES + " or "
                            + ProductOrderingConstants.EMERGENCY_SERVICES + " or "
                            + ProductOrderingConstants.SERVICE_BSCS);
        }
	}

    private void addEmergencyService(OrderItem orderItem, ProductOrder productOrder, String productSpecId)
            throws ApiException {
        // validation (productOfferingId value or amount) depends on backend
        final String msisdn = ProductOrderingTools.extractMsisdn(productOrder.getRelatedPublicKey());
        if (orderItem.getProductOffering() == null || StringUtils.isBlank(orderItem.getProductOffering().getId())) {
            throw new MissingParameterException("orderItem.productOffering.id");
        }

        final String productOfferingId = orderItem.getProductOffering().getId();
        String amount = null;
        if (orderItem.getProduct().getProductCharacteristic() != null) {
            for (Characteristic car : orderItem.getProduct().getProductCharacteristic()) {
                if ("volume".equalsIgnoreCase(car.getName())) {
                    if (amount != null) {
                        throw new TooManyParameterException("orderItem.product.productCharacteristic[volume]", 1);
                    }
                    amount = car.getValue();
                }
            }
        }

        // backend call
        if (ProductOrderingConstants.EMERGENCY_CREDIT.equals(productSpecId)) {
            emergencyBackend.addEmergencyCredit(msisdn, productOfferingId, amount);
        } else if (ProductOrderingConstants.EMERGENCY_DATA.equals(productSpecId)) {
            emergencyBackend.addEmergencyData(msisdn, productOfferingId, amount);
        } else if (ProductOrderingConstants.EMERGENCY_VOICE.equals(productSpecId)) {
            emergencyBackend.addEmergencyVoice(msisdn, productOfferingId, amount);
        } else {
            throw new TechnicalException(productSpecId + " is not an emergency service productSpecification.id");
        }
    }

    private void addBundle(OrderItem orderItem, ProductOrder productOrder) throws ApiException {
        // validation
        final ProductOffering productOffering = orderItem.getProductOffering();
        if (null == productOffering || StringUtils.isBlank(productOffering.getId())) {
            throw new MissingParameterException("orderItem.productOffering.id");
        }
        final String productOfferingId = productOffering.getId();
        final String msisdn = ProductOrderingTools.extractMsisdn(productOrder.getRelatedPublicKey());

        // backend call
        bundleBackend.addBundle(msisdn, productOfferingId);
    }

    /**
     * 
     * @param orderItem
     * @param productOrder
     * @throws ApiException
     */
    public void reactivate(OrderItem orderItem, ProductOrder productOrder) throws ApiException {
        String productSpecId = ProductOrderingTools.getProductSpecId(orderItem);
        if (ProductOrderingTools.isOffer(orderItem)) {
            reactivateOfferBackend.reactivateOffer(orderItem, productOrder);
        } else if (StringUtils.isBlank(productSpecId)) {
            throw new MissingParameterException(ORDERITEM_PRODUCT_PRODUCTSPECIFICATION_ID);
        } else {
            throw new BadParameterValueException(ORDERITEM_PRODUCT_PRODUCTSPECIFICATION_ID, productSpecId,
                    "'" + ProductOrderingConstants.OFFER + "' for order item #" + orderItem.getId());
        }
	}

	/**
     * 
     * @param orderItem
     * @param productOrder
     * @throws ApiException
     */
    public void deactivate(OrderItem orderItem, ProductOrder productOrder) throws ApiException {
        String productSpecId = ProductOrderingTools.getProductSpecId(orderItem);
        if (ProductOrderingTools.isOffer(orderItem)) {
            // Deactivate offer
            deactivateOfferBackend.deactivateOffer(orderItem, productOrder);
        } else if (ProductOrderingTools.isBscsService(orderItem)) {
            // Deactivate service
            deactivateServiceBackend.deactivateService(orderItem, productOrder);
        } else if (productSpecId == null) {
            // For backward compatibility: deactivate service
            deactivateServiceBackend.deactivateService(orderItem, productOrder);
        } else {
            throw new BadParameterValueException("orderItem.product.productSpecification.id", productSpecId,
                    ProductOrderingConstants.OFFER + " or " + ProductOrderingConstants.SERVICE_BSCS);
        }
	}

	/**
     * 
     * @param orderItem
     * @param productOrder
     * @throws ApiException
     */
    public void delete(OrderItem orderItem, ProductOrder productOrder) throws ApiException {
		deleteBackend.delete(orderItem, productOrder);
	}

	/**
     * 
     * @param orderItem
     * @param productOrder
     * @throws ApiException
     */
    public void migrate(OrderItem orderItem, ProductOrder productOrder) throws ApiException {
        String productSpecId = ProductOrderingTools.getProductSpecId(orderItem);
        if (ProductOrderingTools.isOffer(orderItem)) {
            migrateOfferBackend.migrateOffer(orderItem, productOrder);
        } else if (StringUtils.isBlank(productSpecId)) {
            throw new MissingParameterException(ORDERITEM_PRODUCT_PRODUCTSPECIFICATION_ID);
        } else {
            throw new BadParameterValueException(ORDERITEM_PRODUCT_PRODUCTSPECIFICATION_ID, productSpecId,
                    "'" + ProductOrderingConstants.OFFER + "' for order item #" + orderItem.getId());
        }
	}

	/**
     * 
     * @param orderItem
     * @param productOrder
     * @throws ApiException
     */
    public void suspend(OrderItem orderItem, ProductOrder productOrder) throws ApiException {
        String productSpecId = ProductOrderingTools.getProductSpecId(orderItem);
        if (ProductOrderingTools.isOffer(orderItem)) {
            suspendOfferBackend.suspendOffer(orderItem, productOrder);
        } else if (StringUtils.isBlank(productSpecId)) {
            throw new MissingParameterException(ORDERITEM_PRODUCT_PRODUCTSPECIFICATION_ID);
        } else {
            throw new BadParameterValueException(ORDERITEM_PRODUCT_PRODUCTSPECIFICATION_ID, productSpecId,
                    "'" + ProductOrderingConstants.OFFER + "' for order item #" + orderItem.getId());
        }
	}

	/**
     * 
     * @param orderItem
     * @param productOrder
     * @throws ApiException
     */
    public void updateCharacteristic(OrderItem orderItem, ProductOrder productOrder) throws ApiException {
        String productSpecId = ProductOrderingTools.getProductSpecId(orderItem);
        if (ProductOrderingTools.isBscsService(orderItem)) {
            // modify service
            modifyServiceCharacteristicBackend.updateServiceCharacteristic(orderItem, productOrder);
        } else if(productSpecId == null){
            // For backward compatibility: modify service
            modifyServiceCharacteristicBackend.updateServiceCharacteristic(orderItem, productOrder);
        } else {
            throw new BadParameterValueException(ORDERITEM_PRODUCT_PRODUCTSPECIFICATION_ID, productSpecId,
                    "'" + ProductOrderingConstants.SERVICE_BSCS + "' for order item #" + orderItem.getId());
        }
	}

}
