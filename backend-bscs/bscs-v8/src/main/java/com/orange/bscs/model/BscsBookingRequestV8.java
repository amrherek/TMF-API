package com.orange.bscs.model;

import com.orange.bscs.model.accounting.EnumBookingActionCode;
import com.orange.bscs.model.accounting.EnumBookingFeeType;

public class BscsBookingRequestV8 extends BscsBookingRequest {

    public BscsBookingRequestV8() {
        super();
    }

    @Override
    public void setCustomerId(String customerId) {
        // CUSTOMER_ID
        request.setLongValue("CUSTOMER_ID", Long.valueOf(customerId));
    }

    @Override
    public void setContractId(String contractId) {
        // CONTRACT_ID
        request.setLongValue("CONTRACT_ID", Long.valueOf(contractId));
    }

    @Override
    public void setServiceCode(String serviceCode) {
        // SN_CODE
        request.setLongValue("SN_CODE", Long.valueOf(serviceCode));
    }

    @Override
    public void setServicePackageCode(String servicePackageCode) {
        // SP_CODE
        request.setLongValue("SP_CODE", Long.valueOf(servicePackageCode));
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
        // RP_CODE
        request.setLongValue("RP_CODE", Long.valueOf(rpCode));
    }

    @Override
    public void setRatePlanVersionCode(Long rpVersion) {
        // RP_VSCODE
        request.setRatePlanVersionCode(rpVersion);
    }

}
