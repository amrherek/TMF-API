package com.orange.bscs.model.contract;

import java.util.List;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

public class BSCSProductChange extends BSCSModel{
    
    /**
     * <p>Constructor for CommandContractServiceResource.</p>
     *
     * @param command a {@link java.lang.String} object.
     * @param input a {@link com.orange.bscs.api.model.BSCSModel} object.
     */
    public BSCSProductChange(String coidpub, String newRatePlan) {
        setStringValue(Constants.P_CO_ID_PUB, coidpub);
        setStringValue(Constants.P_RPCODE_NEW_PUB, newRatePlan);
    }

    public String getContractIdPub(){ return getStringValue(Constants.P_CO_ID_PUB);}
    
    public String getNewRatePlanIdPub() { return getStringValue(Constants.P_RPCODE_NEW_PUB);}
    
    
    public List<BSCSModel> getServices(){ return getListOfBSCSModelValue(Constants.P_SEQUENCE_OF_SERVICES_AND_SERVICE_PACKAGES);}
    public void setServices(List<BSCSModel> svcs){ setListOfBSCSModelValue(Constants.P_SEQUENCE_OF_SERVICES_AND_SERVICE_PACKAGES,svcs);}
    public void addService(String sn, String destSP) {
        BSCSModel svc = new BSCSModel();
        svc.setStringValue(Constants.P_SNCODE_PUB,sn);
        svc.setStringValue(Constants.P_SPCODE_PUB, destSP);

        List<BSCSModel> services = getServices();
        services.add(svc);
        setServices(services);
    }
    
}
