package com.orange.mea.apisi.productinventory.backend.bscs.transformer;

import org.springframework.stereotype.Component;

import com.orange.apibss.productInventory.model.ProductOfferingRef;
import com.orange.apibss.productInventory.model.ProductRef;
import com.orange.apibss.productInventory.model.ProductRelationship;
import com.orange.apibss.productInventory.model.ProductRelationshipType;
import com.orange.apibss.productInventory.model.State;
import com.orange.bscs.model.businesspartner.EnumContractStatus;

@Component
public class ProductTransformerUtil {

    /**
     * Build a contained in relationship
     * 
     * @param parentProductId
     *            the parent product ID
     * @return a ISCONTAINEDIN product relationship
     */
    public ProductRelationship buildContainedInRelationship(String parentProductId) {
        ProductRelationship relationship = new ProductRelationship();
        relationship.setType(ProductRelationshipType.ISCONTAINEDIN);
        ProductRef productRef = new ProductRef();
        productRef.setId(parentProductId);
        relationship.setProduct(productRef);
        return relationship;
    }

    /**
     * Build a contained in relationship
     * 
     * @param childProductId
     *            the child product ID
     * @return a ISCONTAINEDIN product relationship
     */
    public ProductRelationship buildContainsRelationship(String childProductId) {
        ProductRelationship relationship = new ProductRelationship();
        relationship.setType(ProductRelationshipType.CONTAINS);
        ProductRef productRef = new ProductRef();
        productRef.setId(childProductId);
        relationship.setProduct(productRef);
        return relationship;
    }

    public ProductRelationship buildContainsRelationshipWithProductOffering(String id) {
        ProductRelationship relationship = new ProductRelationship();
        relationship.setType(ProductRelationshipType.CONTAINS);
        ProductRef productRef = new ProductRef();
        ProductOfferingRef productOfferingRef = new ProductOfferingRef();
        productOfferingRef.setId(id);
        productRef.setProductOffering(productOfferingRef);
        relationship.setProduct(productRef);
        return relationship;
    }

    public ProductRelationship buildContainedInRelationshipWithProductOffering(String id) {
        ProductRelationship relationship = new ProductRelationship();
        relationship.setType(ProductRelationshipType.ISCONTAINEDIN);
        ProductRef productRef = new ProductRef();
        ProductOfferingRef productOfferingRef = new ProductOfferingRef();
        productOfferingRef.setId(id);
        productRef.setProductOffering(productOfferingRef);
        relationship.setProduct(productRef);
        return relationship;
    }

    public State transformToProductStatus(EnumContractStatus bscsStatus) {
        switch (bscsStatus) {
        case ACTIVATED:
            return State.ACTIVE;
        case DEACTIVATED:
            return State.TERMINATED;
        case ON_HOLD:
            return State.PENDING_ACTIVE;
        case SUSPENDED:
            return State.SUSPENDED;
        case REMOVED:
        case UNKNOWN:
        default:
            return null;
        }
    }

}
