package com.orange.bscs.model;

import com.orange.bscs.api.utils.config.Constants;
import com.orange.bscs.model.criteria.BscsStorageMediumSearchCriteria;

public class BscsStorageMediumSearchCriteriaV8 extends BscsStorageMediumSearchCriteria {

    public BscsStorageMediumSearchCriteriaV8() {
        super();
    }

    @Override
    public void setSubmarketId(Long submId) {
        // SUBM_ID
        searchCriteria.setSubmarketId(submId);
    }

    @Override
    public void setNetworkId(Long plCode) {
        // NTWK_CODE
        searchCriteria.setLongValue(Constants.P_NTWK_CODE, plCode);
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
