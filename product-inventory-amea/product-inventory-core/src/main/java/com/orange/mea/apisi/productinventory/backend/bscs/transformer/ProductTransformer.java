package com.orange.mea.apisi.productinventory.backend.bscs.transformer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.apibss.productInventory.model.BillingAccount;
import com.orange.apibss.productInventory.model.Product;
import com.orange.apibss.productInventory.model.ProductCharacteristic;
import com.orange.apibss.productInventory.model.ProductOffering;
import com.orange.apibss.productInventory.model.ProductRatingType;
import com.orange.apibss.productInventory.model.ProductRelationship;
import com.orange.apibss.productInventory.model.ProductSpecification;
import com.orange.apibss.productInventory.model.RelatedParty;
import com.orange.apibss.productInventory.model.Role;
import com.orange.apibss.productInventory.model.StateService;
import com.orange.bscs.model.BscsBillingAssignment;
import com.orange.bscs.model.BscsContract;
import com.orange.bscs.model.BscsContractService;
import com.orange.bscs.model.BscsContractServiceFromContract;
import com.orange.bscs.model.BscsContractServiceParameter;
import com.orange.bscs.model.BscsParameterValue;
import com.orange.bscs.model.BscsRatePlan;
import com.orange.bscs.model.BscsService;
import com.orange.bscs.model.BscsServicePackage;
import com.orange.bscs.model.businesspartner.EnumContractStatus;
import com.orange.bscs.model.contract.EnumParameterDataType;
import com.orange.bscs.model.contract.EnumParameterType;
import com.orange.bscs.model.contract.EnumServiceSubType;
import com.orange.mea.apisi.productinventory.constants.ProductInventoryConstants;

@Component
public class ProductTransformer {

    @Autowired
    protected ProductTransformerUtil productTransformerUtil;

    /**
     * Map a main offer to a product, and add relationship for service packages
     * and included service
     * 
     * @param contractDetail
     *            contract of the offer
     * @param ratePlan
     *            rateplan of the offer
     * @param billingAccounts
     * @return
     * @throws TechnicalException
     */
    public Product mapMainOffer(BscsContract contractDetail, BscsRatePlan ratePlan,
            List<BscsBillingAssignment> billingAccounts) throws TechnicalException {
        Product product = new Product();

        product.setId(contractDetail.getId());
        product.setName(ratePlan.getDescription());
        if (contractDetail.getStatus() != null) {
            product.setStatus(productTransformerUtil.transformToProductStatus(contractDetail.getStatus()));
        }
        product.setRatingType(getProductRatingType(ratePlan));
        if (contractDetail.getDateActivated() != null) {
            product.setOrderDate(new DateTime(contractDetail.getDateActivated()));
        }
        if (contractDetail.getStatusDate() != null) {
            product.setStartDate(new DateTime(contractDetail.getStatusDate()));
        }

        ProductOffering offerSpecificationContract = new ProductOffering();
        offerSpecificationContract.setId(ratePlan.getCode());
        offerSpecificationContract.setCategory(ProductInventoryConstants.OFFER);
        offerSpecificationContract.setName(product.getName());
        product.setProductOffering(offerSpecificationContract);

        ProductSpecification productSpecification = new ProductSpecification();
        productSpecification.setId(ProductInventoryConstants.OFFER);
        product.setProductSpecification(productSpecification);

        RelatedParty party = new RelatedParty();
        party.setId(contractDetail.getCustomerId());
        party.setRole(Role.CUSTOMER);
        product.addRelatedPartyItem(party);
        product.setIsBundle(Boolean.TRUE);

        for (BscsBillingAssignment ba : billingAccounts) {
            BillingAccount billingAccount = new BillingAccount();
            billingAccount.setId(ba.getBillingAccountCode());
            product.addBillingAccountItem(billingAccount);
        }

        // Build product characteristics
        ProductCharacteristic productCharacteristic = new ProductCharacteristic();
        productCharacteristic.setName("statusReason");
        productCharacteristic.setValue(Objects.toString(contractDetail.getLastReasonChangeStatus(), null));
        product.addProductCharacteristicItem(productCharacteristic);

        // add contains relationships
        List<BscsContractServiceFromContract> services = contractDetail.getServices();
        // for SP
        Set<String> servicePackages = new HashSet<>();
        for (BscsContractServiceFromContract service : services) {
            final String servicePackagePublicCode = service.getServicePackageCode();
            if (!servicePackages.contains(servicePackagePublicCode)) {
                servicePackages.add(servicePackagePublicCode);
                product.addProductRelationshipItem(productTransformerUtil
                        .buildContainsRelationship(
                                getServicePackageId(product.getId(), servicePackagePublicCode)));
            }
        }
        // for access service
        product.addProductRelationshipItem(
                productTransformerUtil
                        .buildContainsRelationshipWithProductOffering(ProductInventoryConstants.SIM_CARD_ID));
        product.addProductRelationshipItem(
                productTransformerUtil
                        .buildContainsRelationshipWithProductOffering(ProductInventoryConstants.MOBILE_COM_ID));

        return product;
    }

    /**
     * Map a service package to a product
     *
     * @param contractId
     *            the contract id
     * @param bscsServicePackage
     *            the service package
     * @return
     */
    public Product mapServicePackageToProduct(String contractId, BscsServicePackage bscsServicePackage) {
        Product product = new Product();
        // Build an id
        product.setId(getServicePackageId(contractId, bscsServicePackage.getCode()));

        product.setName(bscsServicePackage.getDescription());
        product.setIsBundle(Boolean.TRUE);

        // add contract to SP relationship
        ProductRelationship contractRelationShip = productTransformerUtil.buildContainedInRelationship(contractId);
        product.addProductRelationshipItem(contractRelationShip);

        ProductOffering offerSpecificationComposite = new ProductOffering();
        offerSpecificationComposite.setId(bscsServicePackage.getCode());
        offerSpecificationComposite.setName(product.getName());
        offerSpecificationComposite.setCategory(ProductInventoryConstants.PACKAGE);
        product.setProductOffering(offerSpecificationComposite);
        return product;
    }

    /**
     * Map a service to a product
     * 
     * @param contractService
     * @param service
     * @param servicePackageId
     * @param params
     * @return
     */
    public Product mapServiceToProduct(BscsContractService contractService, BscsService service,
            String servicePackageId, List<BscsContractServiceParameter> params) {
        Product product = new Product();
        // Build id
        product.setId(getServiceId(contractService.getContractId(), contractService.getCode()));
        product.setName(service.getLabel());
        if (contractService.getLastActivationDate() != null) {
            product.setOrderDate(new DateTime(contractService.getLastActivationDate()));
        }

        if (null != contractService.getStatus()) {
            product.setStatus(productTransformerUtil.transformToProductStatus(contractService.getStatus()));
        }
        if (contractService.getStatusDate() != null) {
            product.setStartDate(new DateTime(contractService.getStatusDate()));
        }
        product.setIsBundle(Boolean.FALSE);
        // Relationship with
        ProductRelationship serviceRelationship = productTransformerUtil.buildContainedInRelationship(servicePackageId);
        product.addProductRelationshipItem(serviceRelationship);

        // Product offering
        ProductOffering offerSpecificationService = new ProductOffering();
        offerSpecificationService.setId(contractService.getCode());
        offerSpecificationService.setName(product.getName());
        if (service.isCoreService()) {
            offerSpecificationService.setCategory(ProductInventoryConstants.INCLUDED_SERVICE);
        } else {
            offerSpecificationService.setCategory(ProductInventoryConstants.OPTION);
        }
        product.setProductOffering(offerSpecificationService);

        // Product Specification
        ProductSpecification productSpecification = new ProductSpecification();
        productSpecification.setId(ProductInventoryConstants.SERVICE_BSCS);
        product.setProductSpecification(productSpecification);

        // productCharacteristic
        if (params != null) {
            boolean isFreeUnit = isFreeUnit(service);
            product.setProductCharacteristic(mapParamsToCharacteristics(params, isFreeUnit));
        }

        return product;
    }

    private boolean isFreeUnit(BscsService service) {
        EnumServiceSubType srvSubType = service.getSubType();
        boolean isFreeUnit = false;
        if (srvSubType != null) {
            switch (srvSubType) {
            case COFU:
            case POFU:
            case POFUL:
                isFreeUnit = true;
                break;
            default:
                // other subTypes are not in enum
                isFreeUnit = false;
                break;
            }
        }
        return isFreeUnit;
    }

    /**
     * map service parameters
     * 
     * @param params
     * @param isFreeUnit
     * @return
     */
    private List<ProductCharacteristic> mapParamsToCharacteristics(List<BscsContractServiceParameter> params,
            boolean isFreeUnit) {
        List<ProductCharacteristic> res = new ArrayList<>();
        for (BscsContractServiceParameter param : params) {
            ProductCharacteristic pc = new ProductCharacteristic();
            List<BscsParameterValue> values = param.getParametersValues();
            pc.setName(param.getId());
            if (!values.isEmpty()) {
                if (param.getDateType() == EnumParameterDataType.NUMBER && param.getType() != EnumParameterType.LISTBOX
                        && values.get(0).getValue().matches("-?[0-9]+(,[0-9]{3})+(.[0-9]+)?")) {
                    // a comma is added for numeric values between 3 digits
                    // groups (e.g: "300,000 or -31,213.12") but must not be
                    // present for update => remove it
                    pc.setValue(values.get(0).getValue().replace(",", ""));
                } else if (param.getDateType() == EnumParameterDataType.NUMBER
                        && param.getType() == EnumParameterType.DB && isFreeUnit) {
                    // freeUnits, value is a Long so remove unwanted dot and
                    // zero (87.0=>87)
                    pc.setValue(new BigDecimal(values.get(0).getValue()).stripTrailingZeros().toPlainString());
                } else {
                    pc.setValue(values.get(0).getValue());
                }
            }
            res.add(pc);
        }
        return res;
    }

    public String getServicePackageId(String coId, String spCode) {
        return coId + "|C|" + spCode;
    }

    public String getServiceId(String coId, String snCode) {
        return coId + "|A|" + snCode;
    }

    /**
     * Map an {@link EnumContractStatus} to a {@link StateService}
     * 
     * @param bscsStatus
     *            bscs contract status
     * @return realizing service status
     */
    protected StateService transformToRealizingServiceStatus(EnumContractStatus bscsStatus) {
        switch (bscsStatus) {
        case ACTIVATED:
            return StateService.ACTIVE;
        case DEACTIVATED:
        case SUSPENDED:
            return StateService.INACTIVE;
        case ON_HOLD:
            return StateService.BEING_DEPLOYED;
        case REMOVED:
        case UNKNOWN:
        default:
            return null;
        }
    }

    /**
     * map a {@link BscsRatePlan} to a {@link ProductRatingType}. Only available
     * from BSCS IXR4
     * 
     * @param ratePlan
     *            the rate plan
     * @return the product rating type
     * @throws TechnicalException
     *             unknow value
     */
    protected ProductRatingType getProductRatingType(BscsRatePlan ratePlan) throws TechnicalException {
        // get the short description of the Rateplan
        Character rpType = ratePlan.getPaymentMethodInd();
        if (rpType == null) {
            throw new TechnicalException("Can't map rate plan with short description " + ratePlan.getShortDescription()
                    + " to product rating type");
        }
        switch (rpType) {
        case 'A':
            return ProductRatingType.PREPAID;
        case 'P':
            return ProductRatingType.POSTPAID;
        case 'H':
            return ProductRatingType.HYBRID;
        default:
            throw new TechnicalException("Can't map rate plan method indicator " + rpType + " to product rating type");
        }
    }


}
