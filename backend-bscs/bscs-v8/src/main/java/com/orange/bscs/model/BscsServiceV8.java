package com.orange.bscs.model;

import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.orange.bscs.model.contract.BSCSService;
import com.orange.bscs.model.contract.EnumServiceSubType;

public class BscsServiceV8 extends BscsService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public BscsServiceV8(BSCSService service) {
        super(service);
    }

    @Override
    public Long getNumericCode() {
        // SV_CODE
        return service.getLongValue("SV_CODE");
    }

    @Override
    public String getCode() {
        // SV_CODE
        return ObjectUtils.toString(getNumericCode(), null);
    }

    @Override
    public String getLabel() {
        // SV_DES
        return service.getLabel();
    }

    @Override
    public List<BscsCharges> getCharges() {
        // not in BSCS model
        logger.error("BscsCharges Does not exist in BSCS model for service in V8");
        throw new RuntimeException("Not available in V8");
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
        // SV_AFEE
        return service.getMoneyAmountValue("SV_AFEE");
    }

    @Override
    public String getAccessFeeCurrency() {
        // SV_AFEE#currency
        return service.getMoneyCurrencyCodeValue("SV_AFEE");
    }

    @Override
    public Double getSubscriptionFee() {
        // SV_SFEE
        return service.getMoneyAmountValue("SV_SFEE");
    }

    @Override
    public String getSubscriptionFeeCurrency() {
        // SV_SFEE#currency
        return service.getMoneyCurrencyCodeValue("SV_SFEE");
    }

    @Override
    public Double getEventFee() {
        // EVENT
        return service.getMoneyAmountValue("EVENT");
    }

    @Override
    public String getEventFeeCurrency() {
        // EVENT#currency
        return service.getMoneyCurrencyCodeValue("EVENT");
    }

    @Override
    public Character getIndicator() {
        return service.getCharacterValue("SRVIND");
    }

}
