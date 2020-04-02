package com.orange.bscs.cms.servicelayeradapter;

import static com.orange.bscs.api.utils.config.Constants.CMD_CUSTOMER_INFO_WRITE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.model.exception.SOIException;
import com.orange.bscs.api.utils.config.Constants;
import com.orange.bscs.commands.BaseDAO;
import com.orange.bscs.model.Caches;
import com.orange.bscs.model.businesspartner.BSCSCustomer;

@Repository
public class MarketSegmentServiceAdapter extends BaseDAO implements MarketSegmentServiceAdapterI {

    private static final String CMD_PHONE_USAGE_READ = "PHONE_USAGE.READ";


    @Override
    public BSCSModel customerInfoRead(Long csId, String csIdPub) {
        BSCSCustomer input = new BSCSCustomer();
        if (null != csIdPub) {
            input.setCustomerIDPub(csIdPub);
        } else if (null != csId) {
            input.setCustomerID(csId);
            input.setLongValue(Constants.P_CUSTOMER_ID, csId);
        } else {
            throw new SOIException(String.format(ERR_RC2000, Constants.CMD_CUSTOMER_INFO_READ, Constants.P_CS_ID));
        }
        return execute(Constants.CMD_CUSTOMER_INFO_READ,input);
    }

    /** {@inheritDoc} */
    @Override
    public void customerInfoWrite(BSCSModel input) {
        if(null==input.getLongValue(Constants.P_CS_ID) && null==input.getStringValue(Constants.P_CS_ID_PUB) && null==input.getLongValue(Constants.P_CUSTOMER_ID)){
            throw new SOIException(String.format(ERR_RC2000, CMD_CUSTOMER_INFO_WRITE, Constants.P_CS_ID));
        }
        execute(CMD_CUSTOMER_INFO_WRITE, input);
    }

    
    @Override
    @Cacheable(value = Caches.REFERENTIAL, key = QUOTE + CMD_PHONE_USAGE_READ + QUOTE)
    public Map<Integer, String> phoneUsageRead() {
        List<BSCSModel> usages = execute(CMD_PHONE_USAGE_READ).getListOfBSCSModelValue("usage");
        Map<Integer,String> result = new HashMap<Integer, String>();
        for(BSCSModel usage : usages){
            result.put(usage.getIntegerValue("PU_CODE"), "PU_DES");
        }
        return result;
    }

}
