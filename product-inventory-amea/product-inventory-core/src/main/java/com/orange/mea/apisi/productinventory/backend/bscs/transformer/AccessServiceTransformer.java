package com.orange.mea.apisi.productinventory.backend.bscs.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.orange.apibss.productInventory.model.Product;
import com.orange.apibss.productInventory.model.ProductCharacteristic;
import com.orange.apibss.productInventory.model.ProductOffering;
import com.orange.apibss.productInventory.model.ProductRelationship;
import com.orange.apibss.productInventory.model.ProductSpecification;
import com.orange.bscs.model.BscsContract;
import com.orange.bscs.model.businesspartner.EnumContractStatus;
import com.orange.mea.apisi.productinventory.constants.ProductInventoryConstants;

/**
 * Transformer for included services
 * 
 * Included services are fake products with msisdn and icc informations
 * 
 * @author Thibault Duperron
 *
 */
@Service
public class AccessServiceTransformer {

    @Autowired
    private ProductTransformerUtil productTransformerUtil;

	@Value("${productInventory.mobileCom.name}")
	private String mobileComName;

	@Value("${productInventory.simCard.name}")
	private String simCardName;

    /**
     * Map to a fake composite product with all its childs
     * 
     * @param contractDetail
     *            the contract
     * @param msisdn
     *            (contract read)
     * @return list witch contain the composite product on first place and all
     *         his contained product after
     */
    public List<Product> mapToAccessServicesProduct(BscsContract contractDetail, String msisdn) {
        List<Product> result = new ArrayList<>();

        // Build content of the access services product
        Product msisdnProduct = mapToMsisdnProduct(msisdn, contractDetail.getId(), contractDetail.getStatus());
        Product iccProduct = mapToIccProduct(contractDetail.getStorageMediumNum(), contractDetail.getId(),
                contractDetail.getStatus());

        result.add(msisdnProduct);
        result.add(iccProduct);

        return result;
    }

    /**
     * Create an msisdn product with all required fields
     * 
     * @param msisdn
     *            msisdn value
     * @param parentProductId
     *            parent product
     * @param contractStatus
     * @return
     */
    protected Product mapToMsisdnProduct(String msisdn, String parentProductId,
            EnumContractStatus contractStatus) {
        Product product = new Product();
		product.setName(mobileComName);
        product.setIsBundle(Boolean.FALSE);

        if (contractStatus != null) {
            product.setStatus(productTransformerUtil.transformToProductStatus(contractStatus));
        }

		// Build product offering
		ProductOffering productOffering = new ProductOffering();
        productOffering.setId(ProductInventoryConstants.MOBILE_COM_ID);
		productOffering.setName(product.getName());
        productOffering.setCategory(ProductInventoryConstants.INCLUDED_SERVICE);
		product.setProductOffering(productOffering);

        // Product Specification
        ProductSpecification productSpecification = new ProductSpecification();
        productSpecification.setId(ProductInventoryConstants.ACCESS_SERVICE);
        product.setProductSpecification(productSpecification);

        // Build product characteristics
        ProductCharacteristic productCharacteristic = new ProductCharacteristic();
		productCharacteristic.setName("msisdn");
        productCharacteristic.setValue(msisdn);
        product.addProductCharacteristicItem(productCharacteristic);

		// Build contained relationship
        ProductRelationship relationship = productTransformerUtil.buildContainedInRelationship(parentProductId);
        product.addProductRelationshipItem(relationship);
        return product;
    }

    /**
     * Create an icc product with all required fields
     * 
     * @param icc
     *            icc value
     * @param parentProductId
     * @param contractStatus
     * @return
     */
    protected Product mapToIccProduct(final String icc, final String parentProductId,
            EnumContractStatus contractStatus) {
        Product product = new Product();
		product.setName(simCardName);
        product.setIsBundle(Boolean.FALSE);

        if (contractStatus != null) {
            product.setStatus(productTransformerUtil.transformToProductStatus(contractStatus));
        }

		// Build product offering
		ProductOffering productOffering = new ProductOffering();
        productOffering.setId(ProductInventoryConstants.SIM_CARD_ID);
		productOffering.setName(product.getName());
        productOffering.setCategory(ProductInventoryConstants.INCLUDED_SERVICE);
		product.setProductOffering(productOffering);

        // Product Specification
        ProductSpecification productSpecification = new ProductSpecification();
        productSpecification.setId(ProductInventoryConstants.ACCESS_SERVICE);
        product.setProductSpecification(productSpecification);

        // characteristic
        ProductCharacteristic productCharacteristic = new ProductCharacteristic();
		productCharacteristic.setName("iccId");
        productCharacteristic.setValue(icc);
        product.addProductCharacteristicItem(productCharacteristic);

		// Build contained relationship
        ProductRelationship relationship = productTransformerUtil.buildContainedInRelationship(parentProductId);
        product.addProductRelationshipItem(relationship);

        return product;
    }

}
