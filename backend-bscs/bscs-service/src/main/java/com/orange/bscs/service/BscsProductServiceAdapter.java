package com.orange.bscs.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.orange.bscs.api.model.exception.SOIException;
import com.orange.bscs.api.utils.config.Constants;
import com.orange.bscs.cms.servicelayeradapter.ProductServiceAdapterI;
import com.orange.bscs.model.BscsContract;
import com.orange.bscs.model.BscsFreeUnitPackage;
import com.orange.bscs.model.BscsRatePlan;
import com.orange.bscs.model.BscsService;
import com.orange.bscs.model.BscsServicePackage;
import com.orange.bscs.model.contract.BSCSContract;
import com.orange.bscs.model.contract.BSCSFreeUnitPackage;
import com.orange.bscs.model.contract.BSCSService;
import com.orange.bscs.model.criteria.BscsServicePackageSearchCriteria;
import com.orange.bscs.model.criteria.BscsServiceSearchCriteria;
import com.orange.bscs.model.factory.BscsObjectFactory;
import com.orange.bscs.model.product.BSCSRatePlan;
import com.orange.bscs.model.product.BSCSServicePackage;
import com.orange.bscs.service.exception.BscsFieldExceptionEnum;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;

public abstract class BscsProductServiceAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected ProductServiceAdapterI productServiceAdapter;

    @Autowired
    protected BscsObjectFactory bscsObjectFactory;

    /**
     * execute RATEPLANS.READ
     * 
     * @param code
     * @return
     * @throws BscsInvalidIdException
     */
    public BscsRatePlan getRatePlan(String code) throws BscsInvalidIdException {
        return getRatePlan(null, code);
    }

    /**
     * execute RATEPLANS.READ
     * 
     * @param code
     * @return
     * @throws BscsInvalidIdException
     */
    public BscsRatePlan getRatePlan(Long code) throws BscsInvalidIdException {
        return getRatePlan(code, null);
    }

    protected BscsRatePlan getRatePlan(Long rpCode, String rpCodePub) throws BscsInvalidIdException {
        try {
            List<BSCSRatePlan> rateplans = productServiceAdapter.ratePlanRead(rpCode, rpCodePub);
            if (rateplans.isEmpty()) {
                String rpId = rpCode != null ? rpCode.toString() : rpCodePub;
                throw new BscsInvalidIdException(BscsFieldExceptionEnum.RATE_PLAN_ID, rpId,
                        "Invalid rate plan id: {" + rpId + "}");
            }
            return bscsObjectFactory.createBscsRatePlan(rateplans.get(0));
        } catch (SOIException ex) {
            logger.debug("BSCS FREE_UNIT_PACKAGES.READ error with code: " + ex.getCode());
            if (ex.getCode() != null) {
                if (ex.getCode().contains("RC6705") || ex.getCode().contains("FUNC_FRMWK_SRV.id0468")) {
                    throw new BscsInvalidIdException(BscsFieldExceptionEnum.RATE_PLAN_ID, ex.getFirstArg(),
                            ex.getMessage());
                }
            }
            throw ex;
        }
    }

    /**
     * Return the list of available services from a contract
     * (ALLOWED_SERVICES_READ)
     * 
     * @param contract
     * @return
     */
    public abstract List<BscsService> getAvailableServicesFromContract(final BscsContract contract);

    /**
     * Return the list of available services from a rate plan
     * (ALLOWED_SERVICES_READ)
     * 
     * @param ratePlanCode
     * @return
     */
    public List<BscsService> getAvailableServicesFromRatePlan(String ratePlanCode) {
        final List<BscsService> result = new ArrayList<>();
        try {
            final BSCSRatePlan ratePlan = allowedServicesRead(ratePlanCode);
            final List<BSCSServicePackage> allowedServicePackages = ratePlan.getAllowedServicePackages();
            for (final BSCSServicePackage bscsServicePackage : allowedServicePackages) {
                final List<BSCSService> allowedServices = bscsServicePackage.getAllowedService();
                for (final BSCSService bscsService : allowedServices) {
                    BscsRatePlan rp = bscsObjectFactory.createBscsRatePlan(ratePlan);
                    String spCode = bscsObjectFactory.createBscsServicePackage(bscsServicePackage).getCode();
                    final BscsService service = getService(bscsObjectFactory.createBscsService(bscsService).getCode(),
                            spCode, rp.getCode(), rp.getVersion());
                    // add only services that are not included in rateplan
                    if (!service.isCoreService()) {
                        // add service package to service to be able to map it
                        // in transformer
                        service.setServicePackageCode(spCode);
                        result.add(service);
                    }
                }
            }
        } catch (BscsInvalidIdException | BscsInvalidFieldException e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Command ALLOWED_SERVICES_READ
     * 
     * @param rpCode
     * @return
     * @throws BscsInvalidIdException
     */
    protected abstract BSCSRatePlan allowedServicesRead(String rpCode) throws BscsInvalidIdException;

    /**
     * Command ALLOWED_RATEPLANS.READ
     * 
     * @param contractId
     * @param paymentMethod
     *            only used from BSCS IXR4
     * @return
     */
    public List<BscsRatePlan> getAllowedRatePlansFromContract(String contractId, Character paymentMethod) {
        BSCSContract input = new BSCSContract();
        input.setContractIdPub(contractId);
        // note: payment method indicator is only available from BSCS IXR4
        input.setCharacterValue(Constants.P_PAYMENT_METHOD_IND, paymentMethod);
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
                if (ex.getCode().contains("FUNC_FRMWK_SRV.id0468")) {
                    // invalid public key
                    return res;
                }
            }
            throw ex;
        }
    }

    /**
     * Command ALLOWED_RATEPLANS.READ
     * 
     * @param ratePlanCode
     * @param paymentMethod
     *            only used from BSCS IXR4
     * @return
     */
    public List<BscsRatePlan> getAllowedRatePlansFromRatePlan(String ratePlanCode, Character paymentMethod) {
        BSCSContract input = new BSCSContract();
        input.setRatePlanPublicCode(ratePlanCode);
        // note: payment method indicator is only available from BSCS IXR4
        input.setCharacterValue(Constants.P_PAYMENT_METHOD_IND, paymentMethod);
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
                if (ex.getCode().contains("FUNC_FRMWK_SRV.id0468")) {
                    // invalid public key
                    return res;
                }
            }
            throw ex;
        }
    }

    /**
     * Command ALLOWED_RATEPLANS.READ with rate plan as input
     * 
     * @param ratePlanCode
     * @return
     */
    public abstract List<BscsRatePlan> getAllowedRatePlansByRp(Long ratePlanCode);

    /**
     * Get a service based on rateplan, service package and service ids .
     * execute SERVICES.READ command
     *
     * @param ratePlanId
     * @param servicePackageId
     * @param serviceId
     * @return
     * @throws BscsInvalidFieldException
     * @throws BscsInvalidIdException
     */
    public BscsService getServiceByRatePlanServicePackageAndServiceId(final String ratePlanId,
            final String servicePackageId, final String serviceId)
            throws BscsInvalidFieldException, BscsInvalidIdException {
        try {
            BscsServiceSearchCriteria criteria = bscsObjectFactory.createBscsServiceSearchCriteria();
            criteria.setCode(serviceId);
            criteria.setServicePackageCode(servicePackageId);
            criteria.setRatePlanCode(ratePlanId);
            return getBscsService(criteria);
        } catch (BscsInvalidFieldException e) {
            if (e.getName() == BscsFieldExceptionEnum.SERVICE_PACKAGE_ID) {
                // transform message
                throw new BscsInvalidFieldException(BscsFieldExceptionEnum.SERVICE_PACKAGE_ID, servicePackageId,
                        "The service package with id {" + servicePackageId
                                + "} was not found in the current version of rate plan with id {" + ratePlanId
                                + "}");
            } else if (e.getName() == BscsFieldExceptionEnum.SERVICE_ID) {
                // transform message
                throw new BscsInvalidFieldException(BscsFieldExceptionEnum.SERVICE_ID, serviceId,
                        "The service with id {" + servicePackageId
                                + "} was not found in the current version of rate plan with id {" + ratePlanId + "}");
            }
            throw e;
        }
    }

    /**
     * execute SERVICES.READ
     * 
     * @param serviceId
     * @param servicePackageId
     * @param ratePlanId
     * @param ratePlanVersion
     * @return
     * @throws BscsInvalidFieldException
     * @throws BscsInvalidIdException
     */
    public BscsService getService(final String serviceId, final String servicePackageId, final String ratePlanId,
            final Long ratePlanVersion) throws BscsInvalidFieldException, BscsInvalidIdException {
        try {
            BscsServiceSearchCriteria criteria = bscsObjectFactory.createBscsServiceSearchCriteria();
            criteria.setCode(serviceId);
            criteria.setServicePackageCode(servicePackageId);
            criteria.setRatePlanCode(ratePlanId);
            criteria.setRatePlanVersion(ratePlanVersion);
            return getBscsService(criteria);
        } catch (BscsInvalidFieldException e) {
            if (e.getName() == BscsFieldExceptionEnum.SERVICE_PACKAGE_ID) {
                // transform message
                throw new BscsInvalidFieldException(BscsFieldExceptionEnum.SERVICE_PACKAGE_ID, servicePackageId,
                        "The service package with id {" + servicePackageId
                                + "} was not found in the version {" + ratePlanVersion + "} of rate plan with id {"
                                + ratePlanId
                                + "}");
            } else if (e.getName() == BscsFieldExceptionEnum.SERVICE_ID) {
                // transform message
                throw new BscsInvalidFieldException(BscsFieldExceptionEnum.SERVICE_ID, serviceId,
                        "The service with id {" + servicePackageId + "} was not found in the version {"
                                + ratePlanVersion + "} of rate plan with id {" + ratePlanId + "}");
            }
            throw e;
        }
    }

    /**
     * execute SERVICES.READ
     * 
     * @param serviceId
     * @return
     * @throws BscsInvalidIdException
     */
    public BscsService getService(final String serviceId) throws BscsInvalidIdException {
        try {
            BscsServiceSearchCriteria criteria = bscsObjectFactory.createBscsServiceSearchCriteria();
            criteria.setCode(serviceId);
            return getBscsService(criteria);
        } catch (BscsInvalidFieldException | BscsInvalidIdException e) {
            throw new BscsInvalidIdException(BscsFieldExceptionEnum.SERVICE_ID, serviceId, e.getMessage());
        }
    }

    protected BscsService getBscsService(BscsServiceSearchCriteria criteria)
            throws BscsInvalidFieldException, BscsInvalidIdException {
        try {
            BSCSService service = productServiceAdapter.servicesRead(criteria.getBscsModel());
            if (service == null) {
                // service not found
                throw new BscsInvalidFieldException(BscsFieldExceptionEnum.SERVICE_ID, criteria.getCode(),
                        "No service found with id {" + criteria.getCode() + "} matching the given criteria");
            }
            return bscsObjectFactory.createBscsService(service);
        } catch (SOIException ex) {
            logger.debug("BSCS SERVICES.READ error with code: " + ex.getCode());
            if (ex.getCode() != null) {
                if (ex.getCode().contains("RC6704-001") || ex.getCode().contains("RC6704-002")) {
                    // Service package not found in rateplan corresponding to
                    // the contract (or not with the current or given rp
                    // version)
                    throw new BscsInvalidFieldException(BscsFieldExceptionEnum.SERVICE_PACKAGE_ID, ex.getArg(0),
                            "The service package with id {" + ex.getArg(0)
                                    + "} was not found in the current or given version of rate plan with id {"
                                    + ex.getArg(1) + "}");
                }
                if (ex.getCode().contains("FUNC_FRMWK_SRV.id0468")) {
                    // invalid public key
                    throw new BscsInvalidIdException(null, ex.getFirstArg(),
                            "Invalid public key: {" + ex.getFirstArg() + "}");
                }
                if (ex.getCode().contains("RC6703-")) {
                    // service does not belong to service package in rate plan
                    throw new BscsInvalidFieldException(BscsFieldExceptionEnum.SERVICE_ID, ex.getFirstArg(),
                            ex.getMessage());
                }
            }
            throw ex;
        }
    }

    /**
     * Execute SERVICE_PACKAGES.READ
     *
     * @param servicePackageCode
     *            service package's code
     * @return the service package
     * @throws BscsInvalidIdException
     */
    public BscsServicePackage getServicePackage(final String servicePackageCode) throws BscsInvalidIdException {
        BscsServicePackageSearchCriteria criteria = bscsObjectFactory.createBscsServicePackageSearchCriteria();
        criteria.setCode(servicePackageCode);
        return getBscsServicePackage(criteria);
    }

    protected BscsServicePackage getBscsServicePackage(BscsServicePackageSearchCriteria criteria)
            throws BscsInvalidIdException {
        // retreive all Service packages
        List<BSCSServicePackage> servicesPackages = productServiceAdapter.servicePackagesRead();
        for (BSCSServicePackage servicePackage : servicesPackages) {
            // keep only the one that match criteria
            if (servicePackage.match(criteria.getBscsModel())) {
                return bscsObjectFactory.createBscsServicePackage(servicePackage);
            }
        }
        // service package not found
        throw new BscsInvalidIdException(BscsFieldExceptionEnum.SERVICE_PACKAGE_ID, criteria.getCode(),
                "No service package found with id {" + criteria.getCode() + "} matching the given criteria");
    }

    /**
     * execute FREE_UNIT_PACKAGES.READ
     * 
     * @return
     */
    public List<BscsFreeUnitPackage> getFreeUnitPackages() {
        List<BSCSFreeUnitPackage> freeUnits = productServiceAdapter.freeUnitPackagesRead();
        List<BscsFreeUnitPackage> res = new ArrayList<>();
        for (BSCSFreeUnitPackage freeUnit : freeUnits) {
            res.add(bscsObjectFactory.createBscsFreeUnitPackage(freeUnit));
        }
        return res;
    }

    /**
     * execute FREE_UNIT_PACKAGES.READ
     * 
     * @param id
     * @return
     * @throws BscsInvalidIdException
     */
    public abstract BscsFreeUnitPackage getFreeUnitPackage(Long id) throws BscsInvalidIdException;

    /**
     * execute FREE_UNIT_PACKAGES.READ
     * 
     * @param assignmentLevel
     * @return
     * @throws BscsInvalidFieldException
     */
    public List<BscsFreeUnitPackage> getFreeUnitPackages(Character assignmentLevel) throws BscsInvalidFieldException {
        try {
            List<BSCSFreeUnitPackage> freeUnits = productServiceAdapter.freeUnitPackagesRead(assignmentLevel);
            List<BscsFreeUnitPackage> res = new ArrayList<>();
            for (BSCSFreeUnitPackage freeUnit : freeUnits) {
                res.add(bscsObjectFactory.createBscsFreeUnitPackage(freeUnit));
            }
            return res;
        } catch (SOIException ex) {
            logger.debug("BSCS FREE_UNIT_PACKAGES.READ with code: " + ex.getCode());
            if (ex.getCode() != null) {
                if (ex.getCode().contains("RC2002-001")) {
                    // The parameter FUP_ASS_LEVEL has an invalid value
                    logger.error("The parameter FUP_ASS_LEVEL has an invalid value: {" + assignmentLevel
                            + "}, expected: " + ex.getArg(1));
                    throw new BscsInvalidFieldException(BscsFieldExceptionEnum.ASSIGNEMENT_LEVEL,
                            ObjectUtils.toString(assignmentLevel, "null"),
                            ex.getMessage());
                }
            }
            throw ex;
        }
    }

}
