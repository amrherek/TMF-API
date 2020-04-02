package com.orange.mea.apisi.productordering.backend.bscs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.badrequest.BadParameterFormatException;
import com.orange.apibss.common.exceptions.badrequest.BadParameterValueException;
import com.orange.apibss.common.exceptions.badrequest.BadRequestException;
import com.orange.apibss.common.exceptions.badrequest.MissingParameterException;
import com.orange.apibss.common.exceptions.technical.PendingRequestException;
import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.apibss.productOrdering.model.Characteristic;
import com.orange.apibss.productOrdering.model.OrderItem;
import com.orange.apibss.productOrdering.model.Product;
import com.orange.apibss.productOrdering.model.ProductOffering;
import com.orange.apibss.productOrdering.model.ProductOrder;
import com.orange.apibss.productOrdering.model.ProductRef;
import com.orange.apibss.productOrdering.model.ProductRelationShipType;
import com.orange.apibss.productOrdering.model.ProductRelationship;
import com.orange.bscs.api.aop.TransactionalBscs;
import com.orange.bscs.api.connection.ConnectionHolder;
import com.orange.bscs.api.model.exception.SOIException;
import com.orange.bscs.model.BscsContractServiceParameter;
import com.orange.bscs.model.BscsFreeUnitPackage;
import com.orange.bscs.model.BscsParameter;
import com.orange.bscs.model.BscsParameterValue;
import com.orange.bscs.model.businesspartner.EnumContractStatus;
import com.orange.bscs.model.contract.EnumParameterType;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;
import com.orange.bscs.service.exception.BscsInvalidStateException;
import com.orange.bscs.service.exception.BscsPendingException;
import com.orange.mea.apisi.productordering.backend.ActivateServiceBackend;
import com.orange.mea.apisi.productordering.backend.AddServiceBackend;
import com.orange.mea.apisi.productordering.backend.DeactivateServiceBackend;
import com.orange.mea.apisi.productordering.backend.ModifyServiceCharacteristicBackend;
import com.orange.mea.apisi.productordering.constants.ProductOrderingConstants;
import com.orange.mea.apisi.productordering.exception.ServiceAddException;
import com.orange.mea.apisi.productordering.exception.ServiceParametersChangeException;

@Service
public class ServiceBackend implements AddServiceBackend, DeactivateServiceBackend, ModifyServiceCharacteristicBackend,
        ActivateServiceBackend {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String ORDERITEM_PRODUCT_ID = "orderItem.product.id";
    private static final String ORDERITEM_PRODUCT_PRODUCTCHARACTERISTIC_VALUE = "orderItem.product.productCharacteristic.value";
    private static final String SERVICE_FORMAT = "contractId|A|serviceId";

    @Autowired
    protected BscsContractServiceFacade bscsContractService;
    @Autowired
    private BscsFreeUnitPackageServiceFacade bscsFreeUnitPackageService;
    @Autowired
    private BscsParameterServiceFacade bscsParameterService;

    /**
     * Add a service
     *
     * @param orderItem
     * @param productOrder
     * @throws ApiException
     */
    @TransactionalBscs
    @Override
    public void addService(final OrderItem orderItem, final ProductOrder productOrder) throws ApiException {
        addServiceValidate(orderItem);
        final Product product = orderItem.getProduct();
        final ProductOffering productOffering = orderItem.getProductOffering();
        final List<ProductRelationship> productRelationships = product.getProductRelationship();
        final ProductRef contractProduct = productRelationships.get(0).getProduct();
        final String id = contractProduct.getId();
        final String[] split = id.split("\\|");
        final String contractId = split[0];
        final String serviceId = productOffering.getId();
        final String servicePackageId = split[2];
        Date activationDate;
        if (productOrder.getRequestedStartDate() == null) {
            // activate the service now
            activationDate = new Date();
        } else {
            activationDate = productOrder.getRequestedStartDate().toDate();
        }
        try {
            bscsContractService.addService(contractId, serviceId, servicePackageId, activationDate);

            // Add service parameters
            final List<Characteristic> productCharacteristic = product.getProductCharacteristic();
            if (productCharacteristic != null && !productCharacteristic.isEmpty()) {
                addServiceParameter(contractId, servicePackageId, serviceId, productCharacteristic);
            }
            // Commit the update
            ConnectionHolder.getCurrentConnection().commit();
        } catch (final SOIException e) {
            logger.debug("BSCS CONTRACT_SERVICE.WRITE or CONTRACT_SERVICE_PARAMETERS.WRITE error with code: "
                    + e.getCode());
            if (e.getCode() != null) {
                if (e.getCode().contains("Parameter.ConsistencyRuleViolated.InvalidFUServiceType")) {
                    throw new ServiceAddException(serviceId, servicePackageId, contractId,
                            "Free unit parameter value is not valid");
                }
                if (e.getCode().contains("Contract.ParameterValueMissing")) {
                    throw new ServiceAddException(serviceId, servicePackageId, contractId,
                            "Parameter must be set in orderItem.product.productCharacteristic");
                }
            }
            throw e;
        }
    }

    protected void addServiceValidate(final OrderItem orderItem) throws BadRequestException, TechnicalException {
        final ProductOffering productOffering = orderItem.getProductOffering();
        if (null == productOffering || null == productOffering.getId()) {
            throw new MissingParameterException("orderItem.productOffering.id");
        }
        final Product product = orderItem.getProduct();
        if (null == product) {
            throw new MissingParameterException("orderItem.product");
        }
        if ((product.getProductSpecification() != null
                && !ProductOrderingConstants.SERVICE_BSCS.equals(product.getProductSpecification().getId()))) {
            throw new BadParameterValueException("orderItem.product.productSpecification.id",
                    product.getProductSpecification().getId(), ProductOrderingConstants.SERVICE_BSCS);
        }

        final List<ProductRelationship> productRelationships = product.getProductRelationship();
        if (null == productRelationships || productRelationships.isEmpty()) {
            throw new MissingParameterException("orderItem.product.productRelationship");
        }
        final ProductRelationship productRelationship = productRelationships.get(0);
        if (!ProductRelationShipType.ISCONTAINEDIN.equals(productRelationship.getType())) {
            throw new BadParameterValueException("orderItem.product.productRelationship[0].type",
                    productRelationship.getType() == null ? null : productRelationship.getType().toString(),
                    ProductRelationShipType.ISCONTAINEDIN.toString());
        }
        final ProductRef contractProduct = productRelationship.getProduct();
        if (null == contractProduct || StringUtils.isBlank(contractProduct.getId())) {
            throw new MissingParameterException("orderItem.product.productRelationship[0].product.id");
        }
        // Decoupe de l'id
        final String id = contractProduct.getId();
        if (!isServicePackageId(id)) {
            throw new BadParameterFormatException("orderItem.product.productRelationship[0].product.id", id,
                    "contractId|C|servicePackageId");
        }
    }

    protected boolean isServicePackageId(String id) {
        // Decoupe de l'id
        final String[] split = id.split("\\|");
        if (split.length != 3 || !"C".equals(split[1])) {
            return false;
        }
        return true;
    }

    /**
     * Activate a service
     *
     * @param orderItem
     * @param productOrder
     * @throws ApiException
     */
    @Override
	@TransactionalBscs
    public void activateService(final OrderItem orderItem, final ProductOrder productOrder) throws ApiException {
        final Product product = orderItem.getProduct();
        if (null == product) {
            throw new MissingParameterException("orderItem.product");
        }
        if ((product.getProductSpecification() != null
                && !ProductOrderingConstants.SERVICE_BSCS.equals(product.getProductSpecification().getId()))) {
            throw new BadParameterValueException("orderItem.product.productSpecification.id",
                    product.getProductSpecification().getId(), ProductOrderingConstants.SERVICE_BSCS);
        }
        if (StringUtils.isBlank(product.getId())) {
            throw new MissingParameterException(ORDERITEM_PRODUCT_ID);
        }

        // Decoupe de l'id
        final String id = product.getId();
        final String[] split = id.split("\\|");
        if (split.length != 3 || !"A".equals(split[1])) {
            throw new BadParameterFormatException(ORDERITEM_PRODUCT_ID, id, SERVICE_FORMAT);
        }

        bscsContractService.modifyServiceStatus(split[0], split[2], EnumContractStatus.ACTIVATED,
                productOrder.getRequestedStartDate() == null ? null : productOrder.getRequestedStartDate().toDate());

        // update service parameters
        final List<Characteristic> productCharacteristic = product.getProductCharacteristic();
        if (productCharacteristic != null && !productCharacteristic.isEmpty()) {
            validateCharacteristics(productCharacteristic);
            updateServiceParameter(split[0], split[2], orderItem.getProduct().getProductCharacteristic());
        } else {
            // Commit the update
            ConnectionHolder.getCurrentConnection().commit();
        }
    }

    @Override
	@TransactionalBscs
    public void deactivateService(final OrderItem orderItem, final ProductOrder productOrder) throws ApiException {
        final Product product = orderItem.getProduct();
        if (null == product) {
            throw new MissingParameterException("orderItem.product");
        }
        if ((product.getProductSpecification() != null
                && !ProductOrderingConstants.SERVICE_BSCS.equals(product.getProductSpecification().getId()))) {
            throw new BadParameterValueException("orderItem.product.productSpecification.id",
                    product.getProductSpecification().getId(), ProductOrderingConstants.SERVICE_BSCS);
        }
        if (StringUtils.isBlank(product.getId())) {
            throw new MissingParameterException(ORDERITEM_PRODUCT_ID);
        }

        // Decoupe de l'id
        final String id = product.getId();
        final String[] split = id.split("\\|");
        if (split.length != 3 || !"A".equals(split[1])) {
            throw new BadParameterFormatException(ORDERITEM_PRODUCT_ID, id, SERVICE_FORMAT);
        }

        bscsContractService.modifyServiceStatus(split[0], split[2], EnumContractStatus.DEACTIVATED,
                productOrder.getRequestedStartDate() == null ? null : productOrder.getRequestedStartDate().toDate());
        // Commit the update
        ConnectionHolder.getCurrentConnection().commit();
    }

    /**
     * Add services parameters
     *
     * @param contractId
     * @param servicePackageId
     * @param serviceId
     * @param characteristics
     * @throws TechnicalException
     * @throws BadParameterFormatException
     * @throws ServiceAddException
     * @throws BadParameterValueException
     */
    protected void addServiceParameter(final String contractId, final String servicePackageId, final String serviceId,
            final List<Characteristic> characteristics)
            throws TechnicalException, BadParameterFormatException, ServiceAddException, BadParameterValueException {
        final List<BscsContractServiceParameter> params = new ArrayList<>();
        try {
            for (final Characteristic characteristic : characteristics) {
                BscsParameter bscsParameter = bscsParameterService.getParameter(serviceId, characteristic.getName());
                if (bscsParameter == null) {
                    throw new ServiceAddException(serviceId, servicePackageId, contractId,
                            "Unknown parameter id [" + characteristic.getName() + "] for this service");
                }
                String paramValue = characteristic.getValue();
                String paramDes;
                // specific treatment for free units
                if (bscsFreeUnitPackageService.isFreeUnit(serviceId)) {
                    paramDes = getParamDesForFreeUnit(paramValue);
                } else if (bscsParameter.getType() == EnumParameterType.LISTBOX) {
                    // specific treatment for list box
                    paramDes = fillListBox(bscsParameter, paramValue);
                    paramValue = getParamValueForListBox(paramValue, bscsParameter);
                    if (paramValue == null) {
                        // unauthorized value
                        throw new ServiceAddException(serviceId, servicePackageId, contractId,
                                "Unknown parameter value [" + characteristic.getValue() + "] for this service");
                    }
                } else {
                    paramDes = getParamDes(bscsParameter.getType(), bscsParameter.getId(), paramValue, serviceId);
                }
                params.add(bscsParameterService.buildParamToAdd(bscsParameter, paramValue, paramDes));
            }
            bscsParameterService.writeParameter(contractId, serviceId, params);
        } catch (BscsInvalidIdException | BscsInvalidFieldException | BscsInvalidStateException e) {
            logger.debug("Add service parameter error", e);
            throw new ServiceAddException(serviceId, servicePackageId, contractId, e.getMessage());
        } catch (BscsPendingException e) {
            logger.debug("Add service parameter error", e);
            throw new PendingRequestException(contractId);
        }
    }

    private String getParamValueForListBox(String paramValue, BscsParameter bscsParameter)
            throws BadParameterValueException {
        for (BscsParameterValue value : bscsParameter.getValues()) {
            if (value.getValue().equals(paramValue)) {
                return value.getSequenceNumber().toString();
            }
        }
        return null;
    }

    private String getParamDesForFreeUnit(String paramValue)
            throws BadParameterValueException, BadParameterFormatException {
        Long fuPackageId = null;
        try {
            fuPackageId = Long.parseLong(paramValue);
        } catch (NumberFormatException e) {
            throw new BadParameterFormatException(ORDERITEM_PRODUCT_PRODUCTCHARACTERISTIC_VALUE, paramValue,
                    "a numeric value (free units)");
        }
        try {
            BscsFreeUnitPackage freeUnitPackage = bscsFreeUnitPackageService.getFreeUnitPackage(fuPackageId);
            return freeUnitPackage.getDescription();
        } catch (BscsInvalidIdException e) {
            logger.debug("Get param for free units error", e);
            throw new BadParameterValueException(ORDERITEM_PRODUCT_PRODUCTCHARACTERISTIC_VALUE, paramValue,
                    "a valid free units value");
        }
    }

    /**
     * Fill parameter description depending on parameter type, except for LB
     * 
     * @param parameterType
     * @param parameterId
     * @param paramValue
     * @param serviceId
     * @return
     * @throws BadParameterValueException
     * @throws BadParameterFormatException
     * @throws TechnicalException
     */
    protected String getParamDes(EnumParameterType parameterType, String parameterId, String paramValue,
            String serviceId) throws BadParameterValueException, BadParameterFormatException, TechnicalException {
        // depending on parameter type, fill description
        if (parameterType == null) {
            throw new TechnicalException("Unable to find the type of parameter: " + parameterId);
        }
        switch (parameterType) {
        case CHECKBOX:
            return fillCheckBox(paramValue);
        case LISTBOX:
            // BscsParameter is required to fill description
            return null;
        case COMPLEX:
        case DATAFIELD:
        case DB:
        case SEARCHER:
        default:
            return paramValue;
        }
    }

    private String fillListBox(BscsParameter bscsParameter, String paramValue) {
        // get all possible values
        List<BscsParameterValue> values = bscsParameter.getValues();
        for (BscsParameterValue value : values) {
            if (value.getValue().equals(paramValue)) {
                return value.getValueDescription();
            }
        }
        return paramValue;
    }

    private String fillCheckBox(String paramValue) {
        switch (paramValue) {
        case "Y":
            return "Yes";
        case "N":
            return "No";
        default:
            return paramValue;
        }
    }

    protected void updateCharacteristicValidate(final OrderItem orderItem, ProductOrder productOrder)
            throws ApiException {
        final Product product = orderItem.getProduct();
        if (null == product) {
            throw new MissingParameterException("orderItem.product");
        }
        if ((product.getProductSpecification() != null
                && !ProductOrderingConstants.SERVICE_BSCS.equals(product.getProductSpecification().getId()))) {
            throw new BadParameterValueException("orderItem.product.productSpecification.id",
                    product.getProductSpecification().getId(), ProductOrderingConstants.SERVICE_BSCS);
        }
        if (StringUtils.isBlank(product.getId())) {
            throw new MissingParameterException(ORDERITEM_PRODUCT_ID);
        }

        // Split service id
        String id = product.getId();
        String[] split = id.split("\\|");
        if (split.length != 3 || !"A".equals(split[1])) {
            throw new BadParameterFormatException(ORDERITEM_PRODUCT_ID, id, SERVICE_FORMAT);
        }
        // characteristics
        if (product.getProductCharacteristic() == null || product.getProductCharacteristic().isEmpty()) {
            throw new MissingParameterException("orderItem.product.productCharacteristic");
        }
        validateCharacteristics(product.getProductCharacteristic());
        // requestedStartDate
        if (productOrder.getRequestedStartDate() != null) {
            throw new BadParameterValueException("requestedStartDate", productOrder.getRequestedStartDate().toString(),
                    "null");
        }
    }

    private void validateCharacteristics(List<Characteristic> productCharacteristics) throws MissingParameterException {
        for (Characteristic charac : productCharacteristics) {
            if (charac.getName() == null) {
                throw new MissingParameterException("orderItem.product.productCharacteristic.name");
            }
            if (charac.getValue() == null) {
                throw new MissingParameterException(ORDERITEM_PRODUCT_PRODUCTCHARACTERISTIC_VALUE);
            }
        }
    }

    @Override
	@TransactionalBscs
    public void updateServiceCharacteristic(OrderItem orderItem, ProductOrder productOrder) throws ApiException {
        // validation
        updateCharacteristicValidate(orderItem, productOrder);
        // Split service id
        String id = orderItem.getProduct().getId();
        String[] split = id.split("\\|");
        final String contractId = split[0];
        final String serviceId = split[2];

        // update parameters
        updateServiceParameter(contractId, serviceId, orderItem.getProduct().getProductCharacteristic());

    }

    /**
     * Update service parameters and commit
     * 
     * @param contractId
     * @param serviceId
     * @param characteristics
     * @throws TechnicalException
     * @throws BadParameterFormatException
     * @throws ServiceParametersChangeException
     * @throws BadParameterValueException
     */
    private void updateServiceParameter(final String contractId, final String serviceId,
            List<Characteristic> characteristics)
            throws TechnicalException, BadParameterFormatException, ServiceParametersChangeException,
            BadParameterValueException {
        final List<BscsContractServiceParameter> params = new ArrayList<>();
        try {
            for (final Characteristic characteristic : characteristics) {
                BscsContractServiceParameter bscsContractParameter = bscsParameterService
                        .getContractParameter(contractId, serviceId, characteristic.getName());
                if (bscsContractParameter == null) {
                    throw new ServiceParametersChangeException(serviceId, contractId,
                            "Unknown parameter id [" + characteristic.getName() + "] for this service");
                }
                String paramValue = characteristic.getValue();
                String paramDes;
                if (bscsFreeUnitPackageService.isFreeUnit(serviceId)) {
                    // specific treatment for free units
                    paramDes = getParamDesForFreeUnit(paramValue);
                } else if (bscsContractParameter.getType() == EnumParameterType.LISTBOX) {
                    // specific treatment for list box
                    BscsParameter bscsParameter = bscsParameterService.getParameter(serviceId,
                            characteristic.getName());
                    paramDes = fillListBox(bscsParameter, paramValue);
                    paramValue = getParamValueForListBox(paramValue, bscsParameter);
                    if (paramValue == null) {
                        // unauthorized value
                        throw new ServiceParametersChangeException(serviceId, contractId,
                                "Unknown parameter value [" + characteristic.getValue() + "] for this service");
                    }
                } else {
                    paramDes = getParamDes(bscsContractParameter.getType(), bscsContractParameter.getId(), paramValue,
                            serviceId);
                }
                params.add(bscsParameterService.buildParamToModify(bscsContractParameter, paramValue, paramDes));
            }

            bscsParameterService.writeParameter(contractId, serviceId, params);
            // Commit the update
            ConnectionHolder.getCurrentConnection().commit();
        } catch (BscsInvalidIdException | BscsInvalidFieldException | BscsInvalidStateException e) {
            logger.debug("Update service parameter error", e);
            throw new ServiceParametersChangeException(serviceId, contractId, e.getMessage());
        } catch (BscsPendingException e) {
            logger.debug("Update service parameter error", e);
            throw new PendingRequestException(contractId);
        }
    }

}
