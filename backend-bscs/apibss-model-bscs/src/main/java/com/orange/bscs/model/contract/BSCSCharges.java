package com.orange.bscs.model.contract;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

public class BSCSCharges extends BSCSModel {

    public Long getChargeId(){
        return getLongValue(Constants.P_CHARGE_TYPE_ID);
    }

    public void setChargeId(final Long code) {
        setLongValue(Constants.P_CHARGE_TYPE_ID, code);
    }

    public Double  getChargeAmount(){
        return getMoneyAmountValue(Constants.P_CHARGE_AMOUNT);
    }
    public void setChargeAmount(final Double chargeAmt){
        setMoneyValue(Constants.P_CHARGE_AMOUNT, chargeAmt);
    }

    public String getChargeCurrencyCode() {
        return getMoneyCurrencyCodeValue(Constants.P_CHARGE_AMOUNT);
    }

    public Double getChargeAmountOVW() {
        return getMoneyAmountValue(Constants.P_CHARGE_AMOUNT_OVW);
    }

    public void setChargeAmountOVW(final Double chargeAmt) {
        setMoneyValue(Constants.P_CHARGE_AMOUNT_OVW, chargeAmt);
    }

    public String getChargeCurrencyCodeOVW() {
        return getMoneyCurrencyCodeValue(Constants.P_CHARGE_AMOUNT_OVW);
    }

}
