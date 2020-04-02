package com.orange.mea.apisi.productinventory.backend;

import java.util.List;

import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.apibss.productInventory.model.Product;

public interface EnhancedBscsServiceBackend {

    /**
     * Return the new ProductSpecification.id to associate to this service
     * 
     * @return
     */
    String getSpecificationId();

    /**
     * Return the list of BSCS productOffering.id that are associated to this
     * service
     * 
     * @param msisdn
     * 
     * @return
     * @throws TechnicalException
     */
    List<String> getServiceCodes(String msisdn) throws TechnicalException;

    /**
     * Add info to an existing BSCS service
     * 
     * @param msisdn
     * @param service
     * @throws TechnicalException
     */
    void enhanceService(String msisdn, Product service) throws TechnicalException;

    /**
     * bad SN_CODE_PUB returned by enhanced service backend (not associated to
     * contract)
     * 
     * @param msisdn
     * @param snCode
     * @throws TechnicalException
     */
    void dealWithNoProductFound(String msisdn, String snCode) throws TechnicalException;

}
