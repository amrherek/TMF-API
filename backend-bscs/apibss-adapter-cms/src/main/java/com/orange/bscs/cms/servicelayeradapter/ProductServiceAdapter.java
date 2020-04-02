package com.orange.bscs.cms.servicelayeradapter;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.orange.bscs.api.connection.ConnectionHolder;
import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.model.exception.SOIException;
import com.orange.bscs.api.utils.config.Constants;
import com.orange.bscs.commands.BaseDAO;
import com.orange.bscs.model.Caches;
import com.orange.bscs.model.contract.BSCSContract;
import com.orange.bscs.model.contract.BSCSFreeUnitPackage;
import com.orange.bscs.model.contract.BSCSService;
import com.orange.bscs.model.product.BSCSRatePlan;
import com.orange.bscs.model.product.BSCSRatePlanChange;
import com.orange.bscs.model.product.BSCSServicePackage;

/**
 * <pre>
 * {@code
 * ANONYMIZATION_PACKAGES.READ
 * BASIC_SERVICE_GROUP.READ
 * BASIC_SERVICE_GROUPS.SEARCH
 * BEARER_CAPABILITIES.READ
 * DEPENDENCIES.READ
 * EVENT_DRIVEN_VAS.READ
 *
 * ALLOWED_SERVICES.READ
 * SERVICES.READ
 * SERVICE_FUNCTIONALITIES.READ
 * SERVICE_PACKAGES_HISTORY.READ
 * SERVICE_PACKAGES.READ
 *
 * RATEPLAN_AVAILABILITY_PERIODS.READ
 * THRESHOLD_DEFAULTS.READ
 *
 * }</pre>
 *
 * @author IT&Labs
 *
 */
@Repository
public class ProductServiceAdapter extends BaseDAO implements ProductServiceAdapterI {

    /** {@inheritDoc} */
    @Cacheable(value = Caches.REFERENTIAL, key = QUOTE + Constants.CMD_SERVICE_PACKAGES_READ + QUOTE)
    @Override
    public List<BSCSServicePackage> servicePackagesRead() {
        final BSCSModel res = execute(Constants.CMD_SERVICE_PACKAGES_READ);
        return res.getListOfBSCSModelValue(Constants.P_NUM_SP, BSCSServicePackage.class);
    }

    @Override
    public BSCSServicePackage servicePackagesRead(final BSCSServicePackage input) {
        final BSCSModel res = execute(Constants.CMD_SERVICE_PACKAGES_READ, input);
        if (null != res) {
            final List<BSCSServicePackage> servicePackages = res.getListOfBSCSModelValue(Constants.P_NUM_SP,
                    BSCSServicePackage.class);
            if (!servicePackages.isEmpty()) {
                return servicePackages.get(0);
            }
        }
        return null;
    }


    @Override
    @Cacheable(Caches.REF_ALLOWEDSERVICES)
    public BSCSRatePlan allowedServicesRead(final Long rpcode, final String rpcodepub, final Long rpversion) {
        final BSCSRatePlan input = new BSCSRatePlan();
        if(null!=rpcodepub){
            input.setCodePublic(rpcodepub);
        }else {
            input.setCode(rpcode);
        }
        input.setVersion(rpversion);


        return execute(Constants.CMD_ALLOWED_SERVICES_READ, input, BSCSRatePlan.class);
    }

    @Override
    public List<BSCSRatePlan> allowedRateplansRead(BSCSContract input) {
        final BSCSModel res = execute(Constants.CMD_ALLOWED_RATEPLANS_READ, input);
        return res.getListOfBSCSModelValue(Constants.P_NUM_RP, BSCSRatePlan.class);
    }

    @Override
    public BSCSRatePlan allowedServicesRead(BSCSRatePlan input) {
        return execute(Constants.CMD_ALLOWED_SERVICES_READ, input, BSCSRatePlan.class);
    }

    /** {@inheritDoc} */
    @Cacheable(Caches.REF_SERVICES_BY_IDS)
    @Override
    public BSCSService servicesRead(final Long snCode, final String snCodePub) {
        final BSCSService input = new BSCSService();
        if (null != snCodePub) {
            input.setServicePublicCode(snCodePub);
        } else {
            input.setServiceCode(snCode);
        }
        final BSCSModel res = ConnectionHolder.getCurrentConnection().execute(Constants.CMD_SERVICES_READ, input);
        if (null != res) {
            final List<BSCSService> services = res.getListOfBSCSModelValue(Constants.P_NUM_SV, BSCSService.class);
            if (!services.isEmpty()) {
                return services.get(0);
            }
        }
        return null;
    }

    @Override
    public BSCSService servicesRead(final BSCSService input) {
        final BSCSModel res = ConnectionHolder.getCurrentConnection().execute(Constants.CMD_SERVICES_READ, input);
        if (null != res) {
            final List<BSCSService> services = res.getListOfBSCSModelValue(Constants.P_NUM_SV, BSCSService.class);
            if (!services.isEmpty()) {
                return services.get(0);
            }
        }
        return null;
    }

    @Cacheable(Caches.REF_SERVICES)
    @Override
    public BSCSService servicesRead(final Long spCode, final String spCodePub, final Long snCode, final String snCodePub, final Long rpCode,
            final String rpCodePub, final Long rpVersionCode) {
        final BSCSService input = new BSCSService();
        if (null != snCodePub) {
            input.setServicePublicCode(snCodePub);
        } else {
            input.setServiceCode(snCode);
        }
        if (null != spCodePub) {
            input.setServicePackagePublicCode(spCodePub);
        } else {
            input.setServicePackageCode(spCode);
        }
        if (null != rpCodePub) {
            input.setRatePlanPublicCode(rpCodePub);
        } else {
            input.setRatePlanCode(rpCode);
        }
        input.setRatePlanVersionCode(rpVersionCode);
        final BSCSModel res = ConnectionHolder.getCurrentConnection().execute(Constants.CMD_SERVICES_READ, input);
        if (null != res) {
            final List<BSCSService> services = res.getListOfBSCSModelValue(Constants.P_NUM_SV, BSCSService.class);
            if (!services.isEmpty()) {
                return services.get(0);
            }
        }
        return null;
    }


    /** {@inheritDoc} */
    @Override
    public BSCSFreeUnitPackage freeUnitPackageElementsRead(final Long fuPackageId) {
        final BSCSModel res = execute(Constants.CMD_FREE_UNIT_PACKAGE_ELEMENTS_READ);
        final List<BSCSFreeUnitPackage> pkgs = res.getListOfBSCSModelValue(Constants.P_FUP_LIST, BSCSFreeUnitPackage.class);
        if(!pkgs.isEmpty()){
            return pkgs.get(0);
        }
        return null;
    }

    @Override
    public BSCSFreeUnitPackage freeUnitPackagesRead(Long fuPackageId) {
        BSCSFreeUnitPackage input = new BSCSFreeUnitPackage();
        input.setFUPackageId(fuPackageId);
        final BSCSModel res = execute(Constants.CMD_FREE_UNIT_PACKAGES_READ, input);
        List<BSCSFreeUnitPackage> freeUnits = res.getListOfBSCSModelValue(Constants.P_FUP_LIST,
                BSCSFreeUnitPackage.class);
        if (!freeUnits.isEmpty()) {
            return freeUnits.get(0);
        }
        return null;
    }


    /** {@inheritDoc} */
    @Override
    @Cacheable(value = Caches.REFERENTIAL, key = QUOTE + Constants.CMD_FREE_UNIT_PACKAGES_READ + QUOTE)
    public List<BSCSFreeUnitPackage> freeUnitPackagesRead() {
        final BSCSModel res = execute(Constants.CMD_FREE_UNIT_PACKAGES_READ);
        return  res.getListOfBSCSModelValue(Constants.P_FUP_LIST, BSCSFreeUnitPackage.class);
    }

    @Override
    public List<BSCSFreeUnitPackage> freeUnitPackagesRead(Character assignementLevel) {
        BSCSFreeUnitPackage input = new BSCSFreeUnitPackage();
        input.setFUAssLevel(assignementLevel);
        final BSCSModel res = execute(Constants.CMD_FREE_UNIT_PACKAGES_READ, input);
        return res.getListOfBSCSModelValue(Constants.P_FUP_LIST, BSCSFreeUnitPackage.class);
    }


    /** {@inheritDoc} */
    @Cacheable(value = Caches.REFERENTIAL, key = QUOTE + Constants.CMD_RATEPLANS_READ + QUOTE)
    @Override
    public List<BSCSRatePlan> ratePlansRead() {
        return execute(Constants.CMD_RATEPLANS_READ).getListOfBSCSModelValue(Constants.P_NUM_RP, BSCSRatePlan.class);
    }


    @Override
    public BSCSRatePlanChange productChangeServicesRead(final Long coid, final String coidpub, final Long newrpcode, final String newrpcodepub) {
        final BSCSContract input = initContractModel(coid, coidpub,Constants.CMD_PRODUCT_CHANGE_SERVICES_READ);
        if(StringUtils.isNotBlank(newrpcodepub)){
            input.setStringValue(Constants.P_NEW_RPCODE_PUB, newrpcodepub);
        } else if(null!=newrpcode){
            input.setLongValue(Constants.P_NEW_RPCODE, newrpcode);
        } else {
            throw new SOIException(String.format(ERR_RC2000, Constants.CMD_PRODUCT_CHANGE_SERVICES_READ, Constants.P_NEW_RPCODE_PUB));
        }

        return execute(Constants.CMD_PRODUCT_CHANGE_SERVICES_READ, input,BSCSRatePlanChange.class);
    }

    //added to retieve a particular rateplan
    @Override
    public List<BSCSRatePlan> ratePlanRead(Long rpCode, String rpCodePub) {
        BSCSRatePlan ratePlanSearch = new BSCSRatePlan();
        ratePlanSearch.setCodePublic(rpCodePub);
        ratePlanSearch.setCode(rpCode);
        return execute(Constants.CMD_RATEPLANS_READ, ratePlanSearch).getListOfBSCSModelValue(Constants.P_NUM_RP,
                BSCSRatePlan.class);
    }

}
