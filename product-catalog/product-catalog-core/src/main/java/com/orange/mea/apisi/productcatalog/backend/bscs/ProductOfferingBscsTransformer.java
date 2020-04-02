package com.orange.mea.apisi.productcatalog.backend.bscs;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.technical.EnumTechnicalException;
import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.apibss.productCatalog.BundledProductOffering;
import com.orange.apibss.productCatalog.CategoryRef;
import com.orange.apibss.productCatalog.Price;
import com.orange.apibss.productCatalog.PriceType;
import com.orange.apibss.productCatalog.ProductOfferPriceAlteration;
import com.orange.apibss.productCatalog.ProductOffering;
import com.orange.apibss.productCatalog.ProductOfferingPrice;
import com.orange.apibss.productCatalog.ProductSpecCharacteristic;
import com.orange.apibss.productCatalog.ProductSpecCharacteristicValue;
import com.orange.apibss.productCatalog.ProductSpecification;
import com.orange.apibss.productCatalog.ValueTypeCharacteristic;
import com.orange.apibss.productCatalog.ValueTypeCharacteristicValue;
import com.orange.bscs.model.BscsCharges;
import com.orange.bscs.model.BscsFreeUnitPackage;
import com.orange.bscs.model.BscsParameter;
import com.orange.bscs.model.BscsParameterValue;
import com.orange.bscs.model.BscsRatePlan;
import com.orange.bscs.model.BscsService;
import com.orange.bscs.model.contract.EnumParameterDataType;
import com.orange.bscs.model.contract.EnumParameterType;
import com.orange.mea.apisi.productcatalog.constants.ProductOfferingConstants;


/**
 *
 * @author Thibault Duperron
 *
 */
@Service
public class ProductOfferingBscsTransformer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Build product offering response. UC3
     *
     * @param services
     * @return
     * @throws TechnicalException
     */
    public List<ProductOffering> transform(final List<BscsService> services) throws TechnicalException {
        final List<ProductOffering> res = new ArrayList<>();
        for (final BscsService service : services) {
            // we only keep permanent services and remove event services
            if (service.getIndicator() == 'P') {
                final ProductOffering productOffering = new ProductOffering();
                productOffering.setId(service.getCode());
                productOffering.setName(service.getLabel());
                productOffering.setIsBundle(false);
                BundledProductOffering bundledProductOffering = new BundledProductOffering();
                bundledProductOffering.setId(service.getServicePackageCode());
                productOffering.addBundledProductOfferingItem(bundledProductOffering);

                final CategoryRef category = new CategoryRef();
                category.setName(ProductOfferingConstants.OPTION);
                productOffering.addCategoryItem(category);

                ProductSpecification productSpecification = new ProductSpecification();
                productSpecification.setId(ProductOfferingConstants.SERVICE_BSCS);
                productOffering.setProductSpecification(productSpecification);

                productOffering.setProductOfferingPrice(mapPrices(service));

                res.add(productOffering);
            }
        }
        return res;
    }

    protected List<ProductOfferingPrice> mapPrices(final BscsService service) throws TechnicalException {
        final List<ProductOfferingPrice> result = new ArrayList<>();
        for (final BscsCharges charge : service.getCharges()) {
            final ProductOfferingPrice productOfferingPrice = new ProductOfferingPrice();
            final ProductOfferPriceAlteration productOfferPriceAlteration = new ProductOfferPriceAlteration();
            switch (charge.getChargeType().intValue()) {
            case 1: // one-time
                productOfferingPrice.setPriceType(PriceType.PUNCTUAL);
                productOfferPriceAlteration.setPriceType(PriceType.PUNCTUAL);
                break;
            case 2: // usage
                productOfferingPrice.setPriceType(PriceType.USAGE);
                productOfferPriceAlteration.setPriceType(PriceType.USAGE);
                break;
            case 3: // recurring
            case 6: // advanced recurring
                productOfferingPrice.setPriceType(PriceType.RECURRING);
                productOfferPriceAlteration.setPriceType(PriceType.RECURRING);
                productOfferingPrice
                        .setRecurringChargePeriod(transformPeriod(service.getInterval(), service.getIntervalType()));
                productOfferPriceAlteration.setRecurringChargePeriod(productOfferingPrice.getRecurringChargePeriod());
                break;
            case 5: // event
                productOfferingPrice.setPriceType(PriceType.OPERATION);
                productOfferPriceAlteration.setPriceType(PriceType.OPERATION);
                break;
            default:
            }
            if (null != charge.getChargeAmount()) {
                final Price price = new Price();
                price.setTaxIncludedAmount(charge.getChargeAmount().floatValue());
                price.setCurrencyCode(charge.getChargeCurrency());
                productOfferingPrice.setPrice(price);
                if (null != charge.getChargeAmountOverwritten()) {
                    // price alteration is the reduction amount
                    final Price alterationPrice = new Price();
                    alterationPrice.setTaxIncludedAmount(BigDecimal.valueOf(charge.getChargeAmount())
                            .subtract(BigDecimal.valueOf(charge.getChargeAmountOverwritten())).floatValue());
                    alterationPrice.setCurrencyCode(charge.getChargeCurrencyOverwritten());
                    productOfferPriceAlteration.setPrice(alterationPrice);
                    productOfferingPrice.setProductOfferPriceAlteration(productOfferPriceAlteration);
                }
            }
            result.add(productOfferingPrice);
        }
        return result;
    }

    protected String transformPeriod(Integer interval, Character intervalType) {
        if (interval == null || intervalType == null) {
            return null;
        }
        String res = interval.toString();
        switch (intervalType) {
        case 'D':
            res += " day";
            break;
        case 'M':
            res += " month";
            break;
        default:
            logger.error("Unknown interval type: " + intervalType);
            res += " " + intervalType;
        }
        return res;
    }

    /**
     * Build product offering response. UC2
     * 
     * @param ratePlans
     * @return
     */
    public List<ProductOffering> transformForUC2(List<BscsRatePlan> ratePlans) {
        final List<ProductOffering> res = new ArrayList<>();
        for (BscsRatePlan ratePlan : ratePlans) {
            final ProductOffering productOffering = new ProductOffering();
            productOffering.setId(ratePlan.getCode());
            productOffering.setName(ratePlan.getDescription());
            productOffering.setIsBundle(true);

            final CategoryRef category = new CategoryRef();
            category.setName(ProductOfferingConstants.OFFER);
            productOffering.addCategoryItem(category);

            ProductSpecification productSpecification = new ProductSpecification();
            productSpecification.setId(ProductOfferingConstants.OFFER);
            productOffering.setProductSpecification(productSpecification);

            res.add(productOffering);
        }
        return res;
    }

    /**
     * Build a ProductOffering from a list of BSCSParameter
     * 
     * @param service
     * @param parameters
     * @param freeUnits
     * @return
     * @throws ApiException
     */
    public ProductOffering transformForUC1(BscsService service, List<BscsParameter> parameters,
            List<BscsFreeUnitPackage> freeUnits)
            throws ApiException {
        ProductOffering res = new ProductOffering();
        res.setId(service.getCode());
        res.setName(service.getLabel());
        res.setIsBundle(false);

        ProductSpecification productSpecification = new ProductSpecification();
        productSpecification.setId(ProductOfferingConstants.SERVICE_BSCS);
        for (BscsParameter param : parameters) {
            ProductSpecCharacteristic characteristic = new ProductSpecCharacteristic();
            characteristic.setName(param.getId());
            characteristic.setDescription(param.getDescription());
            characteristic.setConfigurable(true);
            characteristic.setMaxOccurrence(1);
            fillCharacteristic(param, characteristic, freeUnits);
            productSpecification.addProductSpecCharacteristicItem(characteristic);
        }

        res.setProductSpecification(productSpecification);
        return res;
    }

    private void fillCharacteristic(BscsParameter param, ProductSpecCharacteristic characteristic,
            List<BscsFreeUnitPackage> freeUnits)
            throws TechnicalException {
        EnumParameterType type = param.getType();
        if (type == null) {
            throw new TechnicalException("No type defined for the parameter: " + param.getId());
        }
        ValueTypeCharacteristicValue valueType = transformToValueTypeCharacteristicValue(param.getDataType());
        switch (type) {
        case DB:
            if (freeUnits != null) {
                // parameter must contain free units
                characteristic.setValueType(ValueTypeCharacteristic.LISTBOX);
                for (BscsFreeUnitPackage freeUnit : freeUnits) {
                    ProductSpecCharacteristicValue specCharValue = new ProductSpecCharacteristicValue();
                    specCharValue.setValueType(valueType);
                    specCharValue.setValue(freeUnit.getId().toString());
                    specCharValue.setDescription(freeUnit.getDescription());
                    characteristic.addProductSpecCharacteristicValueItem(specCharValue);
                }
            }
            break;
        case CHECKBOX:
        case LISTBOX:
            characteristic.setValueType(ValueTypeCharacteristic.LISTBOX);           
            for (BscsParameterValue paramValue : param.getValues()) {
                ProductSpecCharacteristicValue specCharValue = new ProductSpecCharacteristicValue();
                specCharValue.setValueType(valueType);
                specCharValue.setValue(paramValue.getValue());
                specCharValue.setDescription(fillDescription(type, paramValue));
                characteristic.addProductSpecCharacteristicValueItem(specCharValue);
            }
            break;
        case DATAFIELD:
            characteristic.setValueType(ValueTypeCharacteristic.FREEVALUE);
            ProductSpecCharacteristicValue specCharValue = new ProductSpecCharacteristicValue();
            specCharValue.setValueType(valueType);
            specCharValue.setFormat(param.getFormat());
            characteristic.addProductSpecCharacteristicValueItem(specCharValue);
            break;
        case SEARCHER:
        case COMPLEX:
        default:
            logger.error("Unknown or unsupported parameter type: " + type);
            throw new EnumTechnicalException("Unknown or unsupported parameter type: " + type);
        }
    }

    private String fillDescription(EnumParameterType type, BscsParameterValue paramValue) {
        if (type == EnumParameterType.LISTBOX) {
            return paramValue.getValueDescription();
        }
        // CB
        switch (paramValue.getValue()) {
        case "Y":
            return "Yes";
        case "N":
            return "No";
        default:
            return paramValue.getValueDescription();
        }
    }

    private ValueTypeCharacteristicValue transformToValueTypeCharacteristicValue(EnumParameterDataType dataType)
            throws EnumTechnicalException {
        if (dataType == null) {
            return null;
        }
        switch (dataType) {
        case OTHER:
            return ValueTypeCharacteristicValue.OTHER;
        case DATETIME:
            return ValueTypeCharacteristicValue.DATETIME;
        case NUMBER:
            return ValueTypeCharacteristicValue.NUMBER;
        case STRING:
            return ValueTypeCharacteristicValue.STRING;
        default:
            logger.error("Unknown parameter data type: " + dataType);
            throw new EnumTechnicalException("Unknown parameter data type: " + dataType);
        }
    }

}
