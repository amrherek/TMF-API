package com.orange.mea.apisi.productinventory.orchestrator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.apibss.productInventory.model.Product;
import com.orange.bscs.model.BscsContract;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;
import com.orange.mea.apisi.productinventory.backend.EnhancedBscsServiceBackend;
import com.orange.mea.apisi.productinventory.backend.GetBscsServiceBackend;
import com.orange.mea.apisi.productinventory.backend.bscs.BscsFacadeService;

@Component
public class BcscEnhancedServiceOrchestrator {

    @Autowired(required = false)
    private List<EnhancedBscsServiceBackend> enhancedBscsServiceBackends;

    @Autowired
    protected BscsFacadeService bscsFacadeService;

    @Autowired
    private GetBscsServiceBackend getBscsServiceBackend;

    /**
     * Enhance a BSCS service with specific informations
     * 
     * @param service
     * @throws TechnicalException
     */
    public void checkAndEnhanceService(Product service) throws TechnicalException {
        if (enhancedBscsServiceBackends == null) {
            return;
        }
        if (service.getIsBundle() != null && !service.getIsBundle()) {
            // it is an atomic service
            final String[] split = service.getId().split("\\|");
            String msisdn = bscsFacadeService.getMsisdn(split[0]);
            for (EnhancedBscsServiceBackend enhancedBscsServiceBackend : enhancedBscsServiceBackends) {
                List<String> enhancedBscsServiceCodes = enhancedBscsServiceBackend.getServiceCodes(msisdn);
                if (enhancedBscsServiceCodes.contains(service.getProductOffering().getId())) {
                    // the service must be enhanced
                    enhancedBscsServiceBackend.enhanceService(msisdn, service);
                }
            }
        }
    }

    /**
     * Find service to enhance based on the contract
     * 
     * @param products
     * @param specId
     * @param contract
     * @param msisdn
     * @param withParameters
     * @throws TechnicalException
     */
    public void findEnhancedServiceByContract(List<Product> products, String specId, BscsContract contract,
            String msisdn, boolean withParameters) throws TechnicalException {
        if (enhancedBscsServiceBackends == null) {
            return;
        }
        for (EnhancedBscsServiceBackend enhancedBscsServiceBackend : enhancedBscsServiceBackends) {
            if (specId != null && !enhancedBscsServiceBackend.getSpecificationId().equalsIgnoreCase(specId)) {
                continue;
            }
            List<String> enhancedBscsServiceCodes = enhancedBscsServiceBackend.getServiceCodes(msisdn);
            for (String snCode : enhancedBscsServiceCodes) {
                Product enhancedProduct;
                if (specId == null) {
                    // Bscs product already found: update service
                    // retreive BSCS product with the same snCode
                    enhancedProduct = findProductBySnCode(products, snCode);
                    if (enhancedProduct == null) {
                        enhancedBscsServiceBackend.dealWithNoProductFound(msisdn, snCode);
                    }
                } else {
                    // Bscs product must be retreived to enhance service
                    try {
                        enhancedProduct = getBscsServiceBackend.getBscsService(contract.getId(), snCode,
                                withParameters);
                        products.add(enhancedProduct);
                    } catch (BscsInvalidIdException | BscsInvalidFieldException e) {
                        enhancedProduct = null;
                        enhancedBscsServiceBackend.dealWithNoProductFound(msisdn, snCode);
                    }
                }
                enhancedBscsServiceBackend.enhanceService(msisdn, enhancedProduct);
            }
        }
    }

    /**
     * Retreive product with the given snCodePub
     * 
     * @param products
     * @param snCodePub
     * @return
     */
    protected Product findProductBySnCode(List<Product> products, String snCodePub) {
        for (Product product : products) {
            if (product.getProductOffering() != null && snCodePub.equals(product.getProductOffering().getId())) {
                return product;
            }
        }
        return null;
    }

}
