package com.orange.mea.apisi.productinventory.backend.bscs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.orange.apibss.common.exceptions.badrequest.BadRequestException;
import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.apibss.productInventory.model.Product;
import com.orange.bscs.model.BscsBillingAssignment;
import com.orange.bscs.model.BscsContract;
import com.orange.bscs.model.BscsContractSearch;
import com.orange.bscs.model.BscsContractService;
import com.orange.bscs.model.BscsContractServiceParameter;
import com.orange.bscs.model.BscsRatePlan;
import com.orange.bscs.model.BscsService;
import com.orange.bscs.model.BscsServicePackage;
import com.orange.bscs.service.exception.BscsFieldExceptionEnum;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;
import com.orange.mea.apisi.productinventory.backend.FindAccessServicesByContractBackend;
import com.orange.mea.apisi.productinventory.backend.FindMainOfferByContractBackend;
import com.orange.mea.apisi.productinventory.backend.FindOtherByContractBackend;
import com.orange.mea.apisi.productinventory.backend.FindServicesByContractBackend;
import com.orange.mea.apisi.productinventory.backend.GetBscsServiceBackend;
import com.orange.mea.apisi.productinventory.backend.GetBscsServicePackageBackend;
import com.orange.mea.apisi.productinventory.backend.GetMainOfferBackend;
import com.orange.mea.apisi.productinventory.backend.bscs.transformer.AccessServiceTransformer;
import com.orange.mea.apisi.productinventory.backend.bscs.transformer.ProductTransformer;
import com.orange.mea.apisi.productinventory.backend.bscs.transformer.ProductTransformerUtil;
import com.orange.mea.apisi.productinventory.constants.ProductInventoryConstants;

@Component
public class ProductInventoryBscsBackend implements FindMainOfferByContractBackend, FindAccessServicesByContractBackend,
        FindServicesByContractBackend, GetMainOfferBackend, GetBscsServiceBackend, GetBscsServicePackageBackend {

    @Autowired
    protected BscsFacadeService bscsFacadeService;

    @Autowired
    protected ProductTransformer productTransformer;
    @Autowired
    protected AccessServiceTransformer accessServiceTransformer;
    @Autowired
    protected ProductTransformerUtil productTransformerUtil;

    @Autowired(required = false)
    private List<FindOtherByContractBackend> findOtherByContractBackends;

    @Override
    public Product getMainOffer(List<Product> products, String specId, BscsContract contract)
            throws TechnicalException, BadRequestException {
        Product mainOffer = getMainOffer(contract);
        if (specId == null || ProductInventoryConstants.OFFER.equalsIgnoreCase(specId)) {
            products.add(mainOffer);
        }
        return mainOffer;
    }

    protected Product getMainOffer(BscsContract contract) throws TechnicalException {
        final BscsRatePlan ratePlan = bscsFacadeService.getRatePlans(contract);
        final List<BscsBillingAssignment> billingAccounts = bscsFacadeService.getBillingAccounts(contract);

        return productTransformer.mapMainOffer(contract, ratePlan, billingAccounts);
    }

    /**
     * Add access services (fake product with msisdn, iccid...)
     */
    @Override
    public void findAccessServicesByContract(List<Product> products, String specId, BscsContract contractDetail,
            String msisdn) throws TechnicalException, BadRequestException {
        if (specId == null || ProductInventoryConstants.ACCESS_SERVICE.equalsIgnoreCase(specId)) {
            // Add access services for MSISDN and iccId
            products.addAll(accessServiceTransformer.mapToAccessServicesProduct(contractDetail, msisdn));
        }
    }

    /**
     * Add services retreived from BSCS
     * 
     */
    @Override
    public void getServices(List<Product> products, final String specId, final BscsContract contractDetail,
            final boolean withParameters) throws TechnicalException, BadRequestException {
        if (specId == null || ProductInventoryConstants.SERVICE_BSCS.equalsIgnoreCase(specId)) {
            // Add bscs services and optionally services packages
            products.addAll(getServices(contractDetail, withParameters, specId == null));
        }
    }

    /**
     * Search all BSCS services of a contract
     *
     * @param contractDetail
     *            the contract details
     * @param withParameters
     * @param addServicePackages
     * @return
     * @throws TechnicalException
     * @throws BadRequestException
     */
    protected List<Product> getServices(final BscsContract contractDetail, final boolean withParameters,
            final boolean addServicePackages)
            throws TechnicalException, BadRequestException {
        try {
            final List<Product> options = new ArrayList<>();
            final Map<String, Product> servicePackages = new HashMap<>();
            final List<BscsContractService> contractServices = bscsFacadeService
                    .findContractServiceByContractId(contractDetail.getNumericId());
            for (final BscsContractService contractService : contractServices) {
                Product servicePackage;
                final String servicePackagePublicCode = contractService.getServicePackageCode();
                if (!servicePackages.containsKey(servicePackagePublicCode)) {
                    BscsServicePackage bscsServicePackage = bscsFacadeService
                            .findServicePackageById(servicePackagePublicCode);
                    servicePackage = productTransformer.mapServicePackageToProduct(contractDetail.getId(),
                            bscsServicePackage);
                    servicePackages.put(servicePackagePublicCode, servicePackage);
                    // relationship is already added to the parent
                } else {
                    servicePackage = servicePackages.get(servicePackagePublicCode);
                }
                final BscsService service = bscsFacadeService.getServiceByContractAndContractService(contractDetail,
                        contractService);
                List<BscsContractServiceParameter> params = null;
                if (withParameters && service.hasParameters()) {
                    params = bscsFacadeService.findContractServiceWithParameters(contractService);
                }
                final Product serviceProduct = productTransformer.mapServiceToProduct(contractService, service,
                        servicePackage.getId(), params);
                options.add(serviceProduct);
                // Add contains relationship to the package service
                servicePackage.addProductRelationshipItem(
                        productTransformerUtil.buildContainsRelationship(serviceProduct.getId()));
            }
            if (addServicePackages) {
                options.addAll(servicePackages.values());
            }

            return options;
        } catch (BscsInvalidIdException | BscsInvalidFieldException e) {
            throw new TechnicalException(e.getMessage(), e);
        }
    }

    /**
     * Get a product that represents a BSCS service from contractId and
     * serviceId.
     *
     * @param contractId
     * @param serviceId
     * @param withParameters
     * @return
     * @throws TechnicalException
     * @throws BscsInvalidIdException
     * @throws BscsInvalidFieldException
     */
    @Override
    public Product getBscsService(final String contractId, final String serviceId, boolean withParameters)
            throws TechnicalException, BscsInvalidIdException, BscsInvalidFieldException {
        final BscsContractService contractService = bscsFacadeService
                .findContractServiceByContractIdAndSncode(contractId, serviceId);
        final BscsContract contract = bscsFacadeService.getContract(contractId);
        final BscsService service = bscsFacadeService.getServiceByContractAndContractService(contract, contractService);
        List<BscsContractServiceParameter> params = null;
        if (withParameters && service.hasParameters()) {
            params = bscsFacadeService.findContractServiceWithParameters(contractService);
        }
        final String spId = productTransformer.getServicePackageId(contractService.getContractId(),
                contractService.getServicePackageCode());
        return productTransformer.mapServiceToProduct(contractService, service, spId, params);
    }

    @Override
    public Product getMainOffer(String productId) throws TechnicalException {
        Product mainOffer = null;
        // check if it is a contract
        try {
            BscsContract contract = bscsFacadeService.getContract(productId);
            mainOffer = getMainOffer(contract);

            // add other products to relationship
            if (findOtherByContractBackends != null) {
                for (FindOtherByContractBackend findOtherByContractBackend : findOtherByContractBackends) {
                    BscsContractSearch contractSearch = bscsFacadeService.findContractById(productId);
                    findOtherByContractBackend.getProducts(contractSearch.getDirectoryNumber(), mainOffer);
                }
            }
        } catch (BscsInvalidIdException e) {
            // it is not a contract
        }
        return mainOffer;
    }


    /**
     * Get a product that represents a BSCS service package from contractId and
     * servicePackageId.
     * 
     * @param contractId
     * @param servicePackageId
     * @return
     * @throws BscsInvalidIdException
     * @throws BscsInvalidFieldException
     */
    @Override
    public Product getBscsServicePackage(String contractId, String servicePackageId)
            throws BscsInvalidIdException, BscsInvalidFieldException {
        BscsServicePackage bscsServicePackage = bscsFacadeService.findServicePackageById(servicePackageId);
        final List<BscsContractService> contractServices = bscsFacadeService
                .getServicesByContractIdAndServicePackageId(contractId, servicePackageId);
        if (contractServices.isEmpty()) {
            // no services found with this id and sp
            throw new BscsInvalidFieldException(BscsFieldExceptionEnum.SERVICE_PACKAGE_ID, servicePackageId,
                    "No service found for this service package in this contract");
        }
        Product sp = productTransformer.mapServicePackageToProduct(contractId, bscsServicePackage);
        for (BscsContractService service : contractServices) {
            // add contains relationship to sp
            String serviceProductId = productTransformer.getServiceId(contractId, service.getCode());
            sp.addProductRelationshipItem(productTransformerUtil.buildContainsRelationship(serviceProductId));
        }
        return sp;
    }

}
