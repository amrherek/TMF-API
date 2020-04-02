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
import com.orange.bscs.model.BscsRatePlanV8;
import com.orange.bscs.model.BscsService;
import com.orange.bscs.model.contract.BSCSContract;
import com.orange.bscs.model.contract.BSCSService;
import com.orange.bscs.model.product.BSCSRatePlan;
import com.orange.bscs.model.product.BSCSServicePackage;
import com.orange.bscs.service.exception.BscsFieldExceptionEnum;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;

@Service
public class BscsProductServiceAdapterV8 extends BscsProductServiceAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public BscsRatePlan getRatePlan(String code) throws BscsInvalidIdException {
        return getRatePlan(Long.valueOf(code), null);
    }
    
    @Override
    protected BscsRatePlan getRatePlan(Long rpCode, String rpCodePub) throws BscsInvalidIdException {
        List<BSCSRatePlan> rateplans = productServiceAdapter.ratePlansRead();
        for (final BSCSRatePlan plan : rateplans) {
            if(plan.getLongValue("RP_CODE").equals(rpCode)) {
                return new BscsRatePlanV8(plan);
            }
        }
        throw new BscsInvalidIdException(BscsFieldExceptionEnum.RATE_PLAN_ID, rpCode.toString(),
                "Invalid rate plan id: {" + rpCode + "}");
    }

    @Override
    public List<BscsService> getAvailableServicesFromContract(BscsContract contract) {
        final List<BscsService> result = new ArrayList<>();
        try {
            final Set<Long> subscribedService = contract.getSubscribedServices();
            final BSCSRatePlan ratePlan = allowedServicesRead(contract.getRatePlanCode());
            for (final BSCSServicePackage bscsServicePackage : ratePlan.getAllowedServicePackages()) {
                for (final BSCSService bscsService : bscsServicePackage.getAllowedService()) {
                    // add only services that are not already assigned to
                    // contract
                    if (!subscribedService.contains(bscsService.getLongValue("SV_CODE"))) {
                        final BscsService service = getService(bscsService.getLongValue("SV_CODE").toString(),
                                bscsServicePackage.getLongValue("SP_CODE").toString(),
                                ratePlan.getLongValue("RP_CODE").toString(), ratePlan.getLongValue("RP_VSCODE"));
                        // add service package to service to be able to map it
                        // in transformer
                        service.setServicePackageCode(bscsServicePackage.getLongValue("SP_CODE").toString());
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
        // command FREE_UNIT_PACKAGES.READ does not take free unit package id as argument
        List<BscsFreeUnitPackage> freeUnits = getFreeUnitPackages();
        for(BscsFreeUnitPackage freeUnit : freeUnits) {
            if(freeUnit.getId() != null && freeUnit.getId().equals(id)) {
                return freeUnit;
            }
        }
        throw new BscsInvalidIdException(BscsFieldExceptionEnum.FREE_UNIT_PACKAGE_ID, id.toString(),
                "No free unit package found with id {" + id + "}");
    }

    @Override
    protected BSCSRatePlan allowedServicesRead(String rpCode) throws BscsInvalidIdException {
        try {
            final BSCSRatePlan input = new BSCSRatePlan();
            input.setLongValue("RP_CODE", Long.valueOf(rpCode));
            return productServiceAdapter.allowedServicesRead(input);
        } catch (SOIException ex) {
            logger.debug("BSCS ALLOWED_SERVICES_READ error with code: " + ex.getCode());
            if (ex.getCode() != null) {
                if (ex.getCode().contains("RC6705")) {
                    throw new BscsInvalidIdException(BscsFieldExceptionEnum.RATE_PLAN_ID, rpCode,
                            "No rateplan found with id {" + rpCode + "}");
                }
            }
            throw ex;
        }
    }

    @Override
    public List<BscsRatePlan> getAllowedRatePlansFromContract(String contractId, Character paymentMethod) {
        logger.error("ALLOWED_RATEPLANS.READ is not available in V8 with contract id as input");
        throw new RuntimeException("Not available in V8");
    }

    @Override
    public List<BscsRatePlan> getAllowedRatePlansFromRatePlan(String ratePlanCode, Character paymentMethod) {
        // payment method indicator is ignored in V8
        return getAllowedRatePlansByRp(Long.valueOf(ratePlanCode));
    }

    @Override
    public List<BscsRatePlan> getAllowedRatePlansByRp(Long ratePlanCode) {
        BSCSContract input = new BSCSContract();
        input.setLongValue("RP_CODE", ratePlanCode);
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
