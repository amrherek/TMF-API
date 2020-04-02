package com.orange.bscs.model;

import java.util.Date;

import com.orange.bscs.api.utils.config.Constants;
import com.orange.bscs.model.businesspartner.EnumContractStatus;
import com.orange.bscs.model.contract.BSCSContract;

public class BscsContractSearchV9 extends BscsContractSearch {

    public BscsContractSearchV9(BSCSContract contract) {
        super(contract);
    }

    @Override
    public String getId() {
        // CO_ID_PUB
        return contract.getContractIdPub();
    }

    @Override
    public Long getNumericId() {
        // CO_ID
        return contract.getContractId();
    }

    @Override
    public String getDirectoryNumber() {
        // DIRNUM
        return contract.getDirectoryNumber();
    }

    @Override
    public String getCustomerId() {
        // CS_ID_PUB
        return contract.getCustomerIdPub();
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
        // RPCODE
        return contract.getRatePlanCode();
    }

    @Override
    public Character getPaymentMethodIndicator() {
        // PAYMENT_METHOD_IND
        return contract.getCharacterValue(Constants.P_PAYMENT_METHOD_IND);
    }

}
