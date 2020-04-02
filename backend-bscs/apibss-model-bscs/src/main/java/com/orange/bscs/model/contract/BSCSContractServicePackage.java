package com.orange.bscs.model.contract;

import java.util.ArrayList;
import java.util.List;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;
import com.orange.bscs.model.resource.BSCSStorageMedium;

/**
 * May be used as input of CONTRACT_SERVICES.ADD
 *
 * @author IT&Labs
 * @version $Id: $
 */
public class BSCSContractServicePackage extends BSCSModel {

    private BSCSStorageMedium device;

    /**
     * <p>getContractId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getContractId() {
        return getLongValue(Constants.P_CO_ID);
    }

    /**
     * <p>setContractId.</p>
     *
     * @param coid a {@link java.lang.Long} object.
     */
    public void setContractId(Long coid) {
        setLongValue(Constants.P_CO_ID, coid);
    }

    /**
     * <p>getContractIdPub.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getContractIdPub() {
        return getStringValue(Constants.P_CO_ID_PUB);
    }

    /**
     * <p>setContractIdPub.</p>
     *
     * @param coidpub a {@link java.lang.String} object.
     */
    public void setContractIdPub(String coidpub) {
        setStringValue(Constants.P_CO_ID_PUB, coidpub);
    }

    /**
     * <p>getServicePackageCode.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getServicePackageCode() {
        return getLongValue(Constants.P_SPCODE);
    }

    /**
     * <p>setServicePackageCode.</p>
     *
     * @param spcode a {@link java.lang.Long} object.
     */
    public void setServicePackageCode(Long spcode) {
        setLongValue(Constants.P_SPCODE, spcode);
    }

    /**
     * <p>getServicePackagePublicCode.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getServicePackagePublicCode() {
        return getStringValue(Constants.P_SPCODE_PUB);
    }

    /**
     * <p>setServicePackagePublicCode.</p>
     *
     * @param spcodepub a {@link java.lang.String} object.
     */
    public void setServicePackagePublicCode(String spcodepub) {
        setStringValue(Constants.P_SPCODE_PUB, spcodepub);
    }

    /**
     * <p>getServices.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<BSCSContractService> getServices() {
        List<BSCSContractService> services = getListOfBSCSModelValue(Constants.P_SERVICES, BSCSContractService.class);
        if (null == services) {
            services = new ArrayList<BSCSContractService>();
            setListOfBSCSModelValue(Constants.P_SERVICES, services);
        }
        return services;
    }

    /**
     * <p>addService.</p>
     *
     * @param svc a {@link com.orange.bscs.model.contract.BSCSContractService} object.
     */
    public void addService(BSCSContractService svc) {
        if (null == svc) {
            return;
        }

        List<BSCSContractService> svcs = getServices();
        svcs.add(svc);
        setListOfBSCSModelValue(Constants.P_SERVICES, svcs);
        if (null == svc.getServicePackageCode()) {
            svc.setServicePackageCode(getServicePackageCode());
        }
        if (null == svc.getServicePackagePublicCode()) {
            svc.setServicePackagePublicCode(getServicePackagePublicCode());
        }
    }

    /**
     * <p>addServiceAll.</p>
     *
     * @param services a {@link java.util.List} object.
     */
    public void addServiceAll(List<BSCSContractService> services) {
        List<BSCSContractService> svcs = getServices();
        svcs.addAll(services);
        setListOfBSCSModelValue(Constants.P_SERVICES, svcs);
    }

    /**
     * <p>addDevice.</p>
     *
     * @param device a {@link com.orange.bscs.model.resource.BSCSStorageMedium} object.
     */
    public void addDevice(BSCSStorageMedium device) {
        this.device = device;
    }

    /**
     * <p>Getter for the field <code>device</code>.</p>
     *
     * @return a {@link com.orange.bscs.model.resource.BSCSStorageMedium} object.
     */
    public BSCSStorageMedium getDevice() {
        return this.device;
    }
    
    /**
     * <p>removeService.</p>
     *
     * @param svc a {@link com.orange.bscs.model.contract.BSCSContractService} object.
     */
    public void removeService(BSCSContractService svc) {
        if (null == svc) {
            return;
        }
        List<BSCSContractService> svcs = getServices();
        svcs.remove(svc);
        setListOfBSCSModelValue(Constants.P_SERVICES, svcs);
        
    }


}
