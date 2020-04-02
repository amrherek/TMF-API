package com.orange.bscs.model;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.orange.bscs.model.contract.BSCSCharges;
import com.orange.bscs.model.contract.BSCSService;
import com.orange.bscs.model.contract.EnumServiceSubType;

public class BscsServiceV9 extends BscsService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public BscsServiceV9(BSCSService service) {
        super(service);
    }

    @Override
    public Long getNumericCode() {
        // SNCODE
        return service.getServiceCode();
    }

    @Override
    public String getCode() {
        // SNCODE_PUB
        return service.getServicePublicCode();
    }

    @Override
    public String getLabel() {
        // SV_DES
        return service.getLabel();
    }

    @Override
    public List<BscsCharges> getCharges() {
        List<BscsCharges> res = new ArrayList<>();
        List<BSCSCharges> charges = service.getChargesPackages();
        for (BSCSCharges charge : charges) {
            res.add(new BscsChargesV9(charge));
        }
        return res;
    }

    @Override
    public Integer getInterval() {
        // INTERVAL
        return service.getInterval();
    }

    @Override
    public Character getIntervalType() {
        // INTERVAL_TYPE
        return service.getIntervalType();
    }

    @Override
    public boolean hasParameters() {
        // SERVICE_PARAMERTER_IND
        return service.hasParameters();
    }

    @Override
    public boolean isCoreService() {
        // SV_CSIND
        return service.isCoreService();
    }

    @Override
    public EnumServiceSubType getSubType() {
        // SRV_SUBTYPE
        return service.getServiceSubType();
    }

    @Override
    public Double getAccessFee() {
        // not in BSCS model
        logger.error("Access fee does not exist in BSCS model for service in IX");
        throw new RuntimeException("Not available in V9");
    }

    @Override
    public Double getSubscriptionFee() {
        // not in BSCS model
        logger.error("Subscription fee does not exist in BSCS model for service in IX");
        throw new RuntimeException("Not available in V9");
    }

    @Override
    public String getAccessFeeCurrency() {
        // not in BSCS model
        logger.error("Access fee does not exist in BSCS model for service in IX");
        throw new RuntimeException("Not available in V9");
    }

    @Override
    public String getSubscriptionFeeCurrency() {
        // not in BSCS model
        logger.error("Access fee does not exist in BSCS model for service in IX");
        throw new RuntimeException("Not available in V9");
    }

    @Override
    public Double getEventFee() {
        // not in BSCS model
        logger.error("Event fee does not exist in BSCS model for service in IX");
        throw new RuntimeException("Not available in V9");
    }

    @Override
    public String getEventFeeCurrency() {
        // not in BSCS model
        logger.error("Event fee does not exist in BSCS model for service in IX");
        throw new RuntimeException("Not available in V9");
    }

    @Override
    public Character getIndicator() {
        return service.getCharacterValue("SRVIND");
    }

}
