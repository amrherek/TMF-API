package com.orange.bscs.model;

import java.util.Date;

import org.apache.commons.lang.ObjectUtils;

import com.orange.bscs.api.utils.config.Constants;
import com.orange.bscs.model.businesspartner.EnumContractStatus;
import com.orange.bscs.model.contract.BSCSContractService;

public class BscsContractServiceV8 extends BscsContractService {

    public BscsContractServiceV8(BSCSContractService contractService) {
        super(contractService);
    }

    public BscsContractServiceV8() {
        super();
    }

    @Override
    public Long getNumericCode() {
        // COS_SNCODE
        return contractService.getLongValue("COS_SNCODE");
    }

    @Override
    public String getCode() {
        // COS_SNCODE
        return ObjectUtils.toString(getNumericCode());
    }

    @Override
    public void setCode(String serviceCode) {
        // SNCODE (CONTRACT_SERVICES.ADD) or CO_SN_CODE (CONTRACT_SERVICES.WRITE)
        if (serviceCode != null) {
            contractService.setServiceCode(Long.parseLong(serviceCode));
            contractService.setLongValue("CO_SN_CODE", Long.parseLong(serviceCode));
        }
    }

    @Override
    public String getServicePackageCode() {
        // COS_SPCODE
        return ObjectUtils.toString(contractService.getLongValue("COS_SPCODE"), null);
    }

    @Override
    public void setServicePackageCode(String servicePackageCode) {
        // SPCODE
        contractService.setServicePackageCode(Long.parseLong(servicePackageCode));
    }

    @Override
    public String getContractId() {
        // CO_ID
        return ObjectUtils.toString(contractService.getContractId(), null);
    }

    @Override
    public Date getLastActivationDate() {
        // COS_LAST_ACT_DATE
        return contractService.getLastActivationDate();
    }

    @Override
    public EnumContractStatus getStatus() {
        // COS_STATUS
        return contractService.getCOSStatus();
    }

    @Override
    public void setStatus(EnumContractStatus pendingStatus) {
        // COS_PENDING_STATUS
        contractService.setPendingStatus(pendingStatus);
    }

    @Override
    public Date getStatusDate() {
        // COS_STATUS_DATE
        return contractService.getCOSStatusDate();
    }

    @Override
    public void setStatusDate(Date pendingStatusDate) {
        // COS_STATUS_DATE but as Date
        contractService.setDateValue(Constants.P_COS_STATUS_DATE, pendingStatusDate);
    }

    @Override
    public void setActivationDate(Date activationDate) {
        // COS_ACTIVATION_DATE
        contractService.setDateValue(Constants.P_COS_ACTIVATION_DATE, activationDate);
    }

    @Override
    public void setProfileId(Long profileId) {
        // PROFILE_ID
        contractService.setProfileId(profileId);
    }

}
