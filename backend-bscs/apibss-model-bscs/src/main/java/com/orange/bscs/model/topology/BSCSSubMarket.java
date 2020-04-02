package com.orange.bscs.model.topology;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

public class BSCSSubMarket extends BSCSModel{

    private static final String P_SUBM_SHDES = "SUBM_SHDES";
    private static final String P_SUBM_DES = "SUBM_DES";

    /** (SUBM_ID) */
    public Long getSubMarketId(){ return getLongValue(Constants.P_SUBM_ID);}
    /** (SUBM_ID_PUB) */
    public String getSubMarketIdPub(){ return getStringValue(Constants.P_SUBM_ID_PUB);}

    /** (SCCODE) */
    public Long getMarketId(){ return getLongValue(Constants.P_SCCODE);}
    /** (SCCODE_PUB) */
    public String getMarketIdPub(){ return getStringValue(Constants.P_SCCODE_PUB);}
    
    
    public String getShortName(){ return getStringValue(P_SUBM_SHDES);}

    public String getDescription(){ return getStringValue(P_SUBM_DES);}
    
    
}
