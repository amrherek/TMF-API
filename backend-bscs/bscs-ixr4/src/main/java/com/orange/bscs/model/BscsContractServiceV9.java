package com.orange.bscs.model;

import java.util.Date;

import com.orange.bscs.api.utils.config.Constants;
import com.orange.bscs.model.businesspartner.EnumContractStatus;
import com.orange.bscs.model.contract.BSCSContractService;

public class BscsContractServiceV9 extends BscsContractService {

    public BscsContractServiceV9(BSCSContractService contractService) {
        super(contractService);
    }

    public BscsContractServiceV9() {
        super();
    }

    @Override
    public Long getNumericCode() {
        // SNCODE
        return contractService.getServiceCode();
    }

    @Override
    public String getCode() {
        // SNCODE_PUB
        return contractService.getServiceCodePub();
    }

    @Override
    public void setCode(String serviceCode) {
        // SNCODE_PUB
        contractService.setServiceCodePub(serviceCode);
    }

    @Override
    public String getServicePackageCode() {
        // SPCODE_PUB
        return contractService.getServicePackagePublicCode();
    }

    @Override
    public void setServicePackageCode(String servicePackageCode) {
        // SPCODE_PUB
        contractService.setServicePackagePublicCode(servicePackageCode);
    }

    @Override
    public String getContractId() {
        // CO_ID_PUB
        return contractService.getContractIdPub();
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
        // COS_PENDING_STATUS_DATE
        contractService.setPendingStatusDate(pendingStatusDate);
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
