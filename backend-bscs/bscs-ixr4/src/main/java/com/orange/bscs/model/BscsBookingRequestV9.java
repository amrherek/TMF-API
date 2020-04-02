package com.orange.bscs.model;

import com.orange.bscs.model.accounting.EnumBookingActionCode;
import com.orange.bscs.model.accounting.EnumBookingFeeType;

public class BscsBookingRequestV9 extends BscsBookingRequest {

    public BscsBookingRequestV9() {
        super();
    }

    @Override
    public void setCustomerId(String customerId) {
        // CS_ID_PUB
        request.setCustomerIdPub(customerId);
    }

    @Override
    public void setContractId(String contractId) {
        // CO_ID_PUB
        request.setContractIdPub(contractId);
    }

    @Override
    public void setServiceCode(String serviceCode) {
        // SNCODE_PUB
        request.setServiceCodePub(serviceCode);
    }

    @Override
    public void setServicePackageCode(String servicePackageCode) {
        // SPCODE_PUB
        request.setServicePackageCodePub(servicePackageCode);
    }

    @Override
    public void setFeeType(EnumBookingFeeType feeType) {
        // FEE_TYPE
        request.setFeeType(feeType);
    }

    @Override
    public void setFeeClass(Integer feeClass) {
        // FEE_CLASS
        request.setFeeClass(feeClass);
    }

    @Override
    public void setActionCode(EnumBookingActionCode actionCode) {
        // ACTION_CODE
        request.setActionCode(actionCode);
    }

    @Override
    public void setRatePlanCode(String rpCode) {
        // RPCODE_PUB
        request.setStringValue("RPCODE_PUB", rpCode);
    }

    @Override
    public void setRatePlanVersionCode(Long rpVersion) {
        // RP_VSCODE
        request.setRatePlanVersionCode(rpVersion);
    }

}
