package com.orange.bscs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.orange.bscs.api.model.exception.SOIException;
import com.orange.bscs.model.BscsContract;
import com.orange.bscs.model.BscsFreeUnitPackage;
import com.orange.bscs.model.BscsRatePlan;
import com.orange.bscs.model.BscsService;
import com.orange.bscs.model.contract.BSCSContract;
import com.orange.bscs.model.contract.BSCSFreeUnitPackage;
import com.orange.bscs.model.contract.BSCSService;
import com.orange.bscs.model.product.BSCSRatePlan;
import com.orange.bscs.model.product.BSCSServicePackage;
import com.orange.bscs.service.exception.BscsFieldExceptionEnum;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;

@Service
public class BscsProductServiceAdapterV9 extends BscsProductServiceAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<BscsService> getAvailableServicesFromContract(final BscsContract contract) {
        final List<BscsService> result = new ArrayList<>();
        try {
            final Set<Long> subscribedService = contract.getSubscribedServices();
            final BSCSRatePlan ratePlan = allowedServicesRead(contract.getRatePlanCode());
            final List<BSCSServicePackage> allowedServicePackages = ratePlan.getAllowedServicePackages();
            for (final BSCSServicePackage bscsServicePackage : allowedServicePackages) {
                final List<BSCSService> allowedServices = bscsServicePackage.getAllowedService();
                for (final BSCSService bscsService : allowedServices) {
                    // add only services that are not already assigned to
                    // contract
                    if (!subscribedService.contains(bscsService.getServiceCode())) {
                        final BscsService service = getService(bscsService.getServicePublicCode(),
                                bscsServicePackage.getServicePackageCodePub(), ratePlan.getCodePublic(),
                                ratePlan.getVersion());
                        // add service package to service to be able to map it
                        // in transformer
                        service.setServicePackageCode(bscsServicePackage.getServicePackageCodePub());
                        result.add(service);
                    }
                }
            }
        } catch (BscsInvalidIdException | BscsInvalidFieldException e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public BscsFreeUnitPackage getFreeUnitPackage(Long id) throws BscsInvalidIdException {
        try {
            BSCSFreeUnitPackage freeUnit = productServiceAdapter.freeUnitPackagesRead(id);
            return bscsObjectFactory.createBscsFreeUnitPackage(freeUnit);
        } catch (SOIException ex) {
            logger.debug("BSCS FREE_UNIT_PACKAGES.READ error with code: " + ex.getCode());
            if (ex.getCode() != null) {
                if (ex.getCode().contains("RC_FU_NOT_FOUND")) {
                    throw new BscsInvalidIdException(BscsFieldExceptionEnum.FREE_UNIT_PACKAGE_ID, id.toString(),
                            "No free unit package found with id {" + id + "}");
                }
            }
            throw ex;
        }
    }

    @Override
    protected BSCSRatePlan allowedServicesRead(String rpCode) throws BscsInvalidIdException {
        try {
            return productServiceAdapter.allowedServicesRead(null, rpCode, null);
        } catch (SOIException ex) {
            logger.debug("BSCS ALLOWED_SERVICES_READ error with code: " + ex.getCode());
            if (ex.getCode() != null) {
                if (ex.getCode().contains("FUNC_FRMWK_SRV.id0468")) {
                    throw new BscsInvalidIdException(BscsFieldExceptionEnum.RATE_PLAN_ID, rpCode,
                            "No rateplan found with id {" + rpCode + "}");
                }
            }
            throw ex;
        }
    }

    @Override
    public List<BscsRatePlan> getAllowedRatePlansByRp(Long ratePlanCode) {
        BSCSContract input = new BSCSContract();
        input.setRatePlanCode(ratePlanCode);
        List<BscsRatePlan> res = new ArrayList<>();
        try {
            List<BSCSRatePlan> rateplans = productServiceAdapter.allowedRateplansRead(input);
            for (final BSCSRatePlan plan : rateplans) {
                res.add(bscsObjectFactory.createBscsRatePlan(plan));
            }
            return res;
        } catch (SOIException ex) {
            logger.debug("BSCS  ALLOWED_RATEPLANS.READ error with code: " + ex.getCode());
            if (ex.getCode() != null) {
                if (ex.getCode().contains("RC6705")) {
                    // unknown rate plan id
                    return res;
                }
            }
            throw ex;
        }
    }

}
