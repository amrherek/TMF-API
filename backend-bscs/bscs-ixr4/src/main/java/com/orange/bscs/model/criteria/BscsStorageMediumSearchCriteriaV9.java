package com.orange.bscs.model.criteria;

import com.orange.bscs.api.utils.config.Constants;

public class BscsStorageMediumSearchCriteriaV9 extends BscsStorageMediumSearchCriteria {

    public BscsStorageMediumSearchCriteriaV9() {
        super();
    }

    @Override
    public void setSubmarketId(Long submId) {
        // SUBM_ID
        searchCriteria.setSubmarketId(submId);
    }

    @Override
    public void setNetworkId(Long plCode) {
        // PLCODE
        searchCriteria.setNetworkId(plCode);
    }

    @Override
    public void setType(Long smcId) {
        // SMC_ID
        searchCriteria.setLongValue(Constants.P_SMC_ID, smcId);
    }

    @Override
    public void setSerialNumber(String number) {
        // STMEDNO
        searchCriteria.setICCNumber(number);
    }


}
