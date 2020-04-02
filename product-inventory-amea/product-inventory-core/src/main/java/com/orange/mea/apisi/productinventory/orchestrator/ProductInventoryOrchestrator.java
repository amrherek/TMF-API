package com.orange.mea.apisi.productinventory.orchestrator;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.badrequest.BadRequestException;
import com.orange.apibss.common.exceptions.badrequest.NotFoundException;
import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.apibss.productInventory.model.Product;
import com.orange.bscs.api.aop.TransactionalBscs;
import com.orange.bscs.model.BscsContract;
import com.orange.bscs.model.BscsContractSearch;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;
import com.orange.mea.apisi.productinventory.backend.FindAccessServicesByContractBackend;
import com.orange.mea.apisi.productinventory.backend.FindMainOfferByContractBackend;
import com.orange.mea.apisi.productinventory.backend.FindOtherByContractBackend;
import com.orange.mea.apisi.productinventory.backend.FindServicesByContractBackend;
import com.orange.mea.apisi.productinventory.backend.GetBscsServiceBackend;
import com.orange.mea.apisi.productinventory.backend.GetBscsServicePackageBackend;
import com.orange.mea.apisi.productinventory.backend.GetMainOfferBackend;
import com.orange.mea.apisi.productinventory.backend.bscs.BscsFacadeService;

/**
 * Service for products
 *
 * @author Thibault Duperron
 *
 */
@Service
public class ProductInventoryOrchestrator implements FindProductsByIccIdOrchestrator, FindProductsByMsisdnOrchestrator, GetProductOrchestrator {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FindMainOfferByContractBackend findMainOfferByContract;
    @Autowired
    private FindServicesByContractBackend findServicesByContractBackend;
    @Autowired
    private FindAccessServicesByContractBackend findAccessServicesByContractBackend;
    @Autowired(required = false)
    private List<FindOtherByContractBackend> findOtherByContractBackends;

    @Autowired
    private GetBscsServiceBackend getBscsServiceBackend;
    @Autowired
    private GetBscsServicePackageBackend getBscsServicePackageBackend;
    @Autowired
    private GetMainOfferBackend getMainOfferBackend;

    @Autowired
    private BcscEnhancedServiceOrchestrator bcscEnhancedServiceOrchestrator;

    @Autowired
    protected BscsFacadeService bscsFacadeService;

    /**
     * Search a product by Msisdn
     *
     * @param msisdn
     *            the msisdn
     * @param specId
     *            productSpecification.id
     * @param withParameters
     * @return a list with the product and all subproducts
     * @throws ApiException
     */
    @Override
    @TransactionalBscs
    public List<Product> findProductsByMsisdn(final String msisdn, final String specId, final boolean withParameters)
            throws ApiException {
        List<BscsContractSearch> contracts;
        try {
            contracts = bscsFacadeService.findCurrentContractsByMsisdn(msisdn);
        } catch (BscsInvalidFieldException e) {
            return new ArrayList<>();
        }
        return getProductByContracts(specId, contracts, withParameters);
    }


    /**
     * Search a product by IccId
     *
     * @param iccId
     *            the iccid
     * @param specId
     *            productSpecification.id
     * @param withParameters
     * @return a list with the product and all subproducts
     * @throws ApiException
     */
    @Override
    @TransactionalBscs
    public List<Product> findProductsByIccId(final String iccId, final String specId, final boolean withParameters)
            throws ApiException {
        List<BscsContractSearch> contracts;
        try {
            contracts = bscsFacadeService.findContractsByIccId(iccId);
        } catch (BscsInvalidFieldException e) {
            return new ArrayList<>();
        }
        return getProductByContracts(specId, contracts, withParameters);
    }

    /**
     * Get a Product based on its id
     * 
     * @param productId
     * @param withParameters
     * @return
     * @throws TechnicalException
     * @throws BadRequestException
     */
    @Override
    @TransactionalBscs
    public Product getProduct(String productId, boolean withParameters) throws TechnicalException, BadRequestException {
        // Bscs service (coreService or service) and service package
        Product service = getBscsServiceOrServicePackage(productId, withParameters);
        if (service != null) {
            // enhanced service ?
            bcscEnhancedServiceOrchestrator.checkAndEnhanceService(service);
            // fafOrchestrator.checkAndEnhanceFaf(service);
            return service;
        }

        // main offer
        Product mainOffer = getMainOfferBackend.getMainOffer(productId);
        if (mainOffer != null) {
            return mainOffer;
        }

        throw new NotFoundException("No product found with id: " + productId);
    }

    private Product getBscsServiceOrServicePackage(String productId, boolean withParameters)
            throws TechnicalException, NotFoundException {
        final String[] split = productId.split("\\|");
        if (split.length == 3) {
            if ("A".equals(split[1])) {
                // it is a BSCS service
                try {
                    Product res = getBscsServiceBackend.getBscsService(split[0], split[2], withParameters);
                    // checkAndEnhanceFaf(split[0], split[2], res);
                    return res;
                } catch (BscsInvalidIdException | BscsInvalidFieldException e) {
                    throw new NotFoundException("No product (service) found with id: " + productId);
                }
            } else if ("C".equals(split[1])) {
                // it is a BSCS service package
                try {
                    return getBscsServicePackageBackend.getBscsServicePackage(split[0], split[2]);
                } catch (BscsInvalidIdException | BscsInvalidFieldException e) {
                    throw new NotFoundException("No product (service package) found with id: " + productId);
                }
            }
        }
        // id does not match a BSCS service or service package id
        return null;
    }

    /**
     *
     * @param specId
     *            productSpecification.id
     * @param contracts
     *            base contract (from search)
     * @param withParameters
     * @return list of products and sub products for the contract
     * @throws ApiException
     */
    protected List<Product> getProductByContracts(String specId, final List<BscsContractSearch> contracts,
            final boolean withParameters) throws ApiException {
        final List<Product> result = new ArrayList<>();

        for (final BscsContractSearch contract : contracts) {
            // BscsContractSearch => BscsContract
            BscsContract contractDetail;
            try {
                contractDetail = bscsFacadeService.getContract(contract.getId());
            } catch (BscsInvalidIdException e) {
                throw new TechnicalException(e.getMessage(), e);
            }

            // BscsContract => main offer Product
            Product mainOffer = findMainOfferByContract.getMainOffer(result, specId, contractDetail);

            // BscsContract => bscs products
            getBscsProducts(result, specId, contractDetail, contract.getDirectoryNumber(), withParameters);

            // msisdn => other products
            getOtherProducts(result, specId, contract.getDirectoryNumber(), mainOffer);

        }

        return result;
    }

    /**
     * Build all BSCS products other than main offer
     * 
     * @param products
     * @param specId
     * @param contract
     * @param msisdn
     * @param withParameters
     * @throws ApiException
     */
    protected void getBscsProducts(List<Product> products, final String specId, final BscsContract contract,
            final String msisdn, final boolean withParameters) throws ApiException {
        // BSCS services and service packages
        findServicesByContractBackend.getServices(products, specId, contract, withParameters);

        // access services
        findAccessServicesByContractBackend.findAccessServicesByContract(products, specId, contract, msisdn);

        // enhanced BSCS service
        bcscEnhancedServiceOrchestrator.findEnhancedServiceByContract(products, specId, contract, msisdn,
                withParameters);
    }

    /**
     * Build non BSCS products and add relationships to main offer
     * 
     * @param products
     * @param specId
     * @param msisdn
     * @param mainOffer
     * @throws TechnicalException
     * @throws BadRequestException
     */
    protected void getOtherProducts(List<Product> products, final String specId, String msisdn,
            final Product mainOffer) throws TechnicalException, BadRequestException {
        if (findOtherByContractBackends != null) {
            for (FindOtherByContractBackend findOtherByContractBackend : findOtherByContractBackends) {
                findOtherByContractBackend.findOtherProducts(products, specId, msisdn, mainOffer);
            }
        }
    }

}
