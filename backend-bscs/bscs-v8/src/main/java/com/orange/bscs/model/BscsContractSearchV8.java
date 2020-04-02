package com.orange.bscs.model;

import java.util.Date;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.orange.bscs.api.utils.config.Constants;
import com.orange.bscs.model.businesspartner.EnumContractStatus;
import com.orange.bscs.model.contract.BSCSContract;

public class BscsContractSearchV8 extends BscsContractSearch {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public BscsContractSearchV8(BSCSContract contract) {
        super(contract);
    }

    @Override
    public String getId() {
        // CO_ID
        return Objects.toString(contract.getContractId(), null);
    }

    @Override
    public Long getNumericId() {
        // CO_ID
        return contract.getContractId();
    }

    @Override
    public String getDirectoryNumber() {
        // DN_NUM
        return contract.getStringValue("DN_NUM");
    }

    @Override
    public String getCustomerId() {
        // CS_ID
        return Objects.toString(contract.getCustomerId(), null);
    }

    @Override
    public EnumContractStatus getStatus() {
        // CO_STATUS
        return contract.getStatus();
    }

    @Override
    public Date getDateActivated() {
        // CO_ACTIVATED as Date
        return contract.getDateValue(Constants.P_CO_ACTIVATED);
    }

    @Override
    public String getStorageMediumNum() {
        // SM_NUM
        return contract.getStringValue("SM_NUM");
    }

    @Override
    public Long getNumericRatePlanCode() {
        // TMCODE
        return contract.getLongValue("TMCODE");
    }

    @Override
    public Character getPaymentMethodIndicator() {
        // not in BSCS model
        logger.error("PaymentMethodInd Does not exist in BSCS model for contracts in V8");
        throw new RuntimeException("Not available in V8");
    }

}
