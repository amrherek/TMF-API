package com.orange.bscs.model;

import com.orange.bscs.model.accounting.BSCSBookingRequest;
import com.orange.bscs.model.accounting.EnumBookingActionCode;
import com.orange.bscs.model.accounting.EnumBookingFeeType;

/**
 * Model used for input of BOOKING_REQUEST.WRITE command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsBookingRequest {

    protected BSCSBookingRequest request;

    public BscsBookingRequest() {
        this.request = new BSCSBookingRequest();
    }

    public BSCSBookingRequest getBscsModel() {
        return request;
    }

    public abstract void setCustomerId(String customerId);

    public abstract void setContractId(String contractId);

    public abstract void setServiceCode(String serviceCode);

    public abstract void setServicePackageCode(String servicePackageCode);

    public abstract void setRatePlanCode(String rpCode);

    public abstract void setRatePlanVersionCode(Long rpVersion);

    public abstract void setFeeType(EnumBookingFeeType feeType);

    public abstract void setFeeClass(Integer feeClass);

    public abstract void setActionCode(EnumBookingActionCode actionCode);

}
