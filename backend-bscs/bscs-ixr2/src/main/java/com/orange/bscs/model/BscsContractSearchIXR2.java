package com.orange.bscs.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.orange.bscs.model.contract.BSCSContract;

public class BscsContractSearchIXR2 extends BscsContractSearchV9 {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public BscsContractSearchIXR2(BSCSContract contract) {
        super(contract);
    }

    @Override
    public Character getPaymentMethodIndicator() {
        // not in BSCS model
        logger.error("PaymentMethodInd Does not exist in BSCS model for contracts in IXR2");
        throw new RuntimeException("Not available in IXR2");
    }

}
