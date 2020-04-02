package com.orange.bscs.model;

import java.util.List;

import com.orange.bscs.model.contract.BSCSService;
import com.orange.bscs.model.contract.EnumServiceSubType;

/**
 * Model used for result of SERVICES.READ command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsService {

    protected BSCSService service;

    private String servicePackageCode;

    public BscsService(BSCSService service) {
        this.service = service;
    }

    public abstract Long getNumericCode();

    public abstract String getCode();

    public abstract String getLabel();

    public String getServicePackageCode() {
        return servicePackageCode;
    }

    public void setServicePackageCode(String servicePackageCode) {
        this.servicePackageCode = servicePackageCode;
    }

    public abstract List<BscsCharges> getCharges();

    public abstract Integer getInterval();

    public abstract Character getIntervalType();

    public abstract boolean hasParameters();

    public abstract boolean isCoreService();

    public abstract EnumServiceSubType getSubType();

    public abstract Character getIndicator();

    @Deprecated
    public abstract Double getAccessFee();

    @Deprecated
    public abstract Double getSubscriptionFee();

    @Deprecated
    public abstract String getAccessFeeCurrency();

    @Deprecated
    public abstract String getSubscriptionFeeCurrency();

    @Deprecated
    public abstract Double getEventFee();
    
    @Deprecated
    public abstract String getEventFeeCurrency();


}
