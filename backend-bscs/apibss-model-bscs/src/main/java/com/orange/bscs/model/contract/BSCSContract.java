package com.orange.bscs.model.contract;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;
import com.orange.bscs.model.businesspartner.EnumContractStatus;
import com.orange.bscs.model.product.BSCSRatePlan;

/**
 * represent a contract in BSCS.
 *
 * Can be used with some commands : CONTRACT.READ/WRITE, CONTRACT_SERVICE.READ..
 * CONTRACT.SEARCH (without Contact/Address fields)
 *
 *  Contains an INFOFIELD fictif attribut
 *
 * @author IT&Labs
 * @version $Id: $
 */
public class BSCSContract extends BSCSModel {

    /** Constant <code>SEARCH_EVEN_WITHOUT_DN="SearchContractsEvenWithoutDN"</code> */
 
    private static final String FICTIF_INFOFIELD = "$CT_INFOFIELDS$";

    private BSCSRatePlan rateplan;

    private Map<String, BSCSContractServicePackage> servicesPackage = new HashMap<String, BSCSContractServicePackage>();

    /**
     * <p>getContractId.</p>
     *
     * @return CO_ID : 99999 : java.lang.Long
     */
    public Long getContractId() {
        return getLongValue(Constants.P_CO_ID);
    }

    /**
     * <p>setContractId.</p>
     *
     * @param id a {@link java.lang.Long} object.
     */
    public void setContractId(Long id) {
        setLongValue(Constants.P_CO_ID, id);
    }

    /**
     * CO_ID_PUB = CONTR0000000999 : java.lang.String
     *
     * @return a {@link java.lang.String} object.
     */
    public String getContractIdPub() {
        return getStringValue(Constants.P_CO_ID_PUB);
    }

    /**
     * <p>setContractIdPub.</p>
     *
     * @param idpub a {@link java.lang.String} object.
     */
    public void setContractIdPub(String idpub) {
        setStringValue(Constants.P_CO_ID_PUB, idpub);
    }

    /**
     * RPCODE = 99 : java.lang.Long
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getRatePlanCode() {
        return getLongValue(Constants.P_RPCODE);
    }

    /**
     * <p>setRatePlanCode.</p>
     *
     * @param rpCode a {@link java.lang.Long} object.
     */
    public void setRatePlanCode(Long rpCode) {
        setLongValue(Constants.P_RPCODE, rpCode);
    }

    /**
     * RPCODE_PUB : xxx : java.lang.String
     *
     * Not returned by CONTRACT.SEARCH
     *
     * @return a {@link java.lang.String} object.
     */
    public String getRatePlanPublicCode() {
        return getStringValue(Constants.P_RPCODE_PUB);
    }

    /**
     * <p>setRatePlanPublicCode.</p>
     *
     * @param rpCodePub a {@link java.lang.String} object.
     */
    public void setRatePlanPublicCode(String rpCodePub) {
        setStringValue(Constants.P_RPCODE_PUB, rpCodePub);
    }

    /**
     * <p>getCustomerId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getCustomerId() {
        return getLongValue(Constants.P_CS_ID);
    }

    /**
     * <p>setCustomerId.</p>
     *
     * @param csid a {@link java.lang.Long} object.
     */
    public void setCustomerId(Long csid) {
        setLongValue(Constants.P_CS_ID, csid);
    }

    /**
     * <p>getCustomerIdPub.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getCustomerIdPub() {
        return getStringValue(Constants.P_CS_ID_PUB);
    }

    /**
     * <p>setCustomerIdPub.</p>
     *
     * @param csidpub a {@link java.lang.String} object.
     */
    public void setCustomerIdPub(String csidpub) {
        setStringValue(Constants.P_CS_ID_PUB, csidpub);
    }

    /**
     * <p>getCustomerCode.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getCustomerCode() {
        return getStringValue(Constants.P_CS_CODE);
    }

    /**
     * <p>setCustomerCode.</p>
     *
     * @param cscode a {@link java.lang.String} object.
     */
    public void setCustomerCode(String cscode) {
        setStringValue(Constants.P_CS_CODE, cscode);
    }


    /**
     * Date when contract is activate (status=2)
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getDateActivated() {
        return getDateTimeValue(Constants.P_CO_ACTIVATED);
    }

    /**
     * <p>setDateActivated.</p>
     *
     * @param dt a {@link java.util.Date} object.
     */
    public void setDateActivated(Date dt) {
        setDateTimeValue(Constants.P_CO_ACTIVATED, dt);
    }

    /**
     * Contract Creation Date by default
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getDateSigned() {
        return getDateValue(Constants.P_CO_SIGNED_DATE);
    }

    /**
     * <p>setDateSigned.</p>
     *
     * @param dt a {@link java.util.Date} object.
     */
    public void setDateSigned(Date dt) {
        setDateValue(Constants.P_CO_SIGNED_DATE, dt);
    }

    /**
     * <p>getDateExpired.</p>
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getDateExpired() {
        return getDateValue(Constants.P_CO_EXPIR_DATE);
    }

    /**
	 * 
	 * Get the VALID_FROM date parameter
	 * 
	 * @return
	 */
	public Date getDateValidFrom() {
		return getDateValue(Constants.P_VALID_FROM);
	}

	/**
	 * Sets the Valid From date parameter
	 * 
	 * @param orderDate
	 */
	public void setDateValidFrom(Date orderDate) {
		setDateValue(Constants.P_VALID_FROM, orderDate);
	}

	/**
	 * <p>
	 * setDateExpired.
	 * </p>
	 *
	 * @param dt
	 *            a {@link java.util.Date} object.
	 */
    public void setDateExpired(Date dt) {
        setDateValue(Constants.P_CO_EXPIR_DATE, dt);
    }

    /**
     * <p>getDateModification.</p>
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getDateModification() {
        return getDateTimeValue(Constants.P_CO_MODDATE);
    }

    /**
     * <p>setDateModification.</p>
     *
     * @param dt a {@link java.util.Date} object.
     */
    public void setDateModification(Date dt) {
        setDateTimeValue(Constants.P_CO_MODDATE, dt);
    }

    /**
     * <p>getStatusAsInt.</p>
     *
     * @return a int.
     */
    public int getStatusAsInt() {
        Integer st = getIntegerValue(Constants.P_CO_STATUS);
        int status = -1;
        if (null != st) {
            status = st.intValue();
        }
        return status;
    }

    /**
     * <p>getStatus.</p>
     *
     * @return a {@link com.orange.bscs.model.businesspartner.EnumContractStatus} object.
     */
    public EnumContractStatus getStatus() {
        int st = getStatusAsInt();
        return EnumContractStatus.fromInt(st);
    }

    /**
     * <p>setStatus.</p>
     *
     * @param status a int.
     */
    public void setStatus(int status) {
        setIntegerValue(Constants.P_CO_STATUS, status);
    }

    /**
     * <p>setStatus.</p>
     *
     * @param status a {@link com.orange.bscs.model.businesspartner.EnumContractStatus} object.
     */
    public void setStatus(EnumContractStatus status) {
        setIntegerValue(Constants.P_CO_STATUS, (null == status ? null : status.toInt()));
    }

    /**
     * <p>setStatusDate.</p>
     *
     * @param dt a {@link java.util.Date} object.
     */
    public void setStatusDate(Date dt) {
        setDateTimeValue(Constants.P_CO_LAST_STATUS_CHANGE_DATE, dt);
    }

    /**
     * <p>getStatusDate.</p>
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getStatusDate() {
        return getDateTimeValue(Constants.P_CO_LAST_STATUS_CHANGE_DATE);
    }

    /**
     * <p>getPendingStatusAsInt.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getPendingStatusAsInt() {
        return getIntegerValue(Constants.P_CO_PENDING_STATUS);
    }

    /**
     * <p>getPendingStatus.</p>
     *
     * @return a {@link com.orange.bscs.model.businesspartner.EnumContractStatus} object.
     */
    public EnumContractStatus getPendingStatus() {
        Integer st = getPendingStatusAsInt();
        EnumContractStatus status = null;
        if (null != st) {
            status = EnumContractStatus.fromInt(st);
        }
        return status;
    }

    /**
     * <p>setPendingStatus.</p>
     *
     * @param status a int.
     */
    public void setPendingStatus(int status) {
        setIntegerValue(Constants.P_CO_PENDING_STATUS, status);
    }

    /**
     * <p>setPendingStatus.</p>
     *
     * @param status a {@link com.orange.bscs.model.businesspartner.EnumContractStatus} object.
     */
    public void setPendingStatus(EnumContractStatus status) {
        setIntegerValue(Constants.P_CO_PENDING_STATUS, (null == status ? null : status.toInt()));
    }

    /**
     * <p>setPendingDate.</p>
     *
     * @param dt a {@link java.util.Date} object.
     */
    public void setPendingDate(Date dt) {
        setDateTimeValue(Constants.P_CO_PENDING_DATE, dt);
    }

    /**
     * <p>getPendingDate.</p>
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getPendingDate() {
        return getDateTimeValue(Constants.P_CO_PENDING_DATE);
    }

    /**
     * <p>getBusinessUnit.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getBusinessUnit() {
        return getLongValue(Constants.P_BU_ID);
    }

    /**
     * <p>setBusinessUnit.</p>
     *
     * @param id a {@link java.lang.Long} object.
     */
    public void setBusinessUnit(Long id) {
        setLongValue(Constants.P_BU_ID, id);
    }

    /**
     * PLCODE : 1001 : java.lang.Long
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getNetworkId() {
        return getLongValue(Constants.P_PLCODE);
    }

    /**
     * <p>setNetworkId.</p>
     *
     * @param plcode a {@link java.lang.Long} object.
     */
    public void setNetworkId(Long plcode) {
        setLongValue(Constants.P_PLCODE, plcode);
    }

    /**
     * DESCRIPTION = : java.lang.String
     * 
     * @return
     */
    public String getDescription(){ return getStringValue(Constants.P_DESCRIPTION);}
    
    /**
     * DESCRIPTION = : java.lang.String
     * 
     * @param descr
     */
    public void setDescription(String descr){ setStringValue(Constants.P_DESCRIPTION, descr);}

    /**
     * DIRNUM = : java.lang.String
     *
     * @return a {@link java.lang.String} object.
     */
    public String getDirectoryNumber() {
        return getStringValue(Constants.P_DIRNUM);
    }

    /**
     * <p>setDirectoryNumber.</p>
     *
     * @param dirnum a {@link java.lang.String} object.
     */
    public void setDirectoryNumber(String dirnum) {
        setStringValue(Constants.P_DIRNUM, dirnum);
    }

    /**
     * <p>getSubmarketId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getSubmarketId() {
        return getLongValue(Constants.P_SUBM_ID);
    }

    /**
     * <p>setSubmarketId.</p>
     *
     * @param submid a {@link java.lang.Long} object.
     */
    public void setSubmarketId(Long submid) {
        setLongValue(Constants.P_SUBM_ID, submid);
    }

    /**
     * <p>getNetworkPublicCode.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getNetworkPublicCode() {
        return getStringValue(Constants.P_PLCODE_PUB);
    }

    /**
     * <p>setNetworkPublicCode.</p>
     *
     * @param copub a {@link java.lang.String} object.
     */
    public void setNetworkPublicCode(String copub) {
        setStringValue(Constants.P_PLCODE_PUB, copub);
    }

    /**
     * <p>getMarketId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getMarketId() {
        return getLongValue(Constants.P_SCCODE);
    }

    /**
     * <p>setMarketId.</p>
     *
     * @param scCode a {@link java.lang.Long} object.
     */
    public void setMarketId(Long scCode) {
        setLongValue(Constants.P_SCCODE, scCode);
    }

    /**
     * <p>getMarketIdPub.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getMarketIdPub() {
        return getStringValue(Constants.P_SCCODE_PUB);
    }

    /**
     * <p>setMarketIdPub.</p>
     *
     * @param sccodepub a {@link java.lang.String} object.
     */
    public void setMarketIdPub(String sccodepub) {
        setStringValue(Constants.P_SCCODE_PUB, sccodepub);
    }

    /**
     * <p>getHLRCode.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getHLRCode() {
        return getLongValue(Constants.P_HLCODE);
    }

    /**
     * <p>setHLRCode.</p>
     *
     * @param hlr a {@link java.lang.Long} object.
     */
    public void setHLRCode(Long hlr) {
        setLongValue(Constants.P_HLCODE, hlr);
    }

    /**
     * <p>getReasonChangeStatus.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getReasonChangeStatus() {
        return getLongValue(Constants.P_REASON);
    }

    /**
     * <p>setReasonChangeStatus.</p>
     *
     * @param reason a {@link java.lang.Long} object.
     */
    public void setReasonChangeStatus(Long reason) {
        setLongValue(Constants.P_REASON, reason);
    }

    /**
     * <p>getLastReasonChangeStatus.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getLastReasonChangeStatus() {
        return getLongValue(Constants.P_CO_LAST_REASON);
    }

    /**
     * <p>setLastReasonChangeStatus.</p>
     *
     * @param reason a {@link java.lang.Long} object.
     */
    public void setLastReasonChangeStatus(Long reason) {
        setLongValue(Constants.P_CO_LAST_REASON, reason);
    }

    // Only for Contract.New
    /**
     * <p>getDealerIdPub.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getDealerIdPub() {
        return getStringValue(Constants.P_DEALER_ID_PUB);
    }

    /**
     * <p>setDealerIdPub (DEALER_ID_PUB).</p>
     *
     * @param dealerIdPub a {@link java.lang.String} object.
     */
    public void setDealerIdPub(String dealerIdPub) {
        setStringValue(Constants.P_DEALER_ID_PUB, dealerIdPub);
    }

    /**
     * <p>getRatePlan.</p>
     *
     * @return a {@link com.orange.bscs.model.product.BSCSRatePlan} object.
     */
    public BSCSRatePlan getRatePlan() {
        return this.rateplan;
    }

    /**
     * <p>setRatePlan.</p>
     *
     * @param rateplan a {@link com.orange.bscs.model.product.BSCSRatePlan} object.
     */
    public void setRatePlan(BSCSRatePlan rateplan) {
        this.rateplan = rateplan;
    }

    /**
     * <p>Setter for the field <code>servicesPackage</code>.</p>
     *
     * @param spList a {@link java.util.List} object.
     */
    public void setServicesPackage(List<BSCSContractServicePackage> spList) {
        this.servicesPackage.clear();
        if (null != spList) {
            for (BSCSContractServicePackage sp : spList) {
                this.servicesPackage.put(sp.getServicePackagePublicCode(), sp);
            }
        }
    }

    /**
     * <p>Getter for the field <code>servicesPackage</code>.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<BSCSContractServicePackage> getServicesPackage() {
        return new ArrayList<BSCSContractServicePackage>(servicesPackage.values());
    }

    /**
     * <p>addServicePackage.</p>
     *
     * @param sp a {@link com.orange.bscs.model.contract.BSCSContractServicePackage} object.
     */
    public void addServicePackage(BSCSContractServicePackage sp) {
        servicesPackage.put(sp.getServicePackagePublicCode(), sp);
    }

    /**
     * <p>getServices.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<BSCSContractService> getServices() {
        List<BSCSContractService> services = getListOfBSCSModelValue(Constants.P_SERVICES, BSCSContractService.class);
        if (null != services) {
            for (BSCSContractService svc : services) {
                svc.setContractId(getContractId());
                svc.setContractIdPub(getContractIdPub());
            }
        }
        return services;
    }

    /**
     * Rebuild tree of ServicesPackages and services
     *
     * Nota, it is possible to inject result of Contract_Services.read in this
     * contract (to have more details) and then call parseServices
     */
    public void parseServices() {
        List<BSCSContractService> services = getServices();
        servicesPackage.clear();
        if (null == services) {
            return;
        }

        // Avec les objets SLV, préférable de travailler en dehors.
        Map<String, List<BSCSContractService>> mapsp = new HashMap<String, List<BSCSContractService>>();
        for (BSCSContractService svc : services) {
            String spcodepub = svc.getServicePackagePublicCode();
            List<BSCSContractService> spSvcs = mapsp.get(spcodepub);
            if (null == spSvcs) {
                spSvcs = new ArrayList<BSCSContractService>();
                mapsp.put(spcodepub, spSvcs);
            }
            spSvcs.add(svc);
        }
        for (Entry<String, List<BSCSContractService>> entry : mapsp.entrySet()) {
            BSCSContractServicePackage sp = new BSCSContractServicePackage();
            sp.setContractId(getContractId());
            sp.setContractIdPub(getContractIdPub());
            sp.setServicePackagePublicCode(entry.getKey());
            sp.addServiceAll(entry.getValue());
            if (0 < entry.getValue().size()) {
                sp.setServicePackageCode(entry.getValue().get(0).getServicePackageCode());
            }
            servicesPackage.put(entry.getKey(), sp);
        }
    }



    /**
     * <p>getCurrencyId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getCurrencyId() {
        return getLongValue(Constants.P_CURRENCY_ID);
    }

    /**
     * <p>setCurrencyId.</p>
     *
     * @param currency a {@link java.lang.Long} object.
     */
    public void setCurrencyId(Long currency) {
        setLongValue(Constants.P_CURRENCY_ID, currency);
    }

    /**
     * <p>getCurrencyIdPub.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getCurrencyIdPub() {
        return getStringValue(Constants.P_CURRENCY_ID_PUB);
    }

    /**
     * <p>setCurrencyIdPub.</p>
     *
     * @param currency a {@link java.lang.String} object.
     */
    public void setCurrencyIdPub(String currency) {
        setStringValue(Constants.P_CURRENCY_ID_PUB, currency);
    }

 
    /**
     * actual StorageMedium Number (CO_STMEDNO)
     * 
     * @return
     */
    public String getStorageMediumNum() { return getStringValue(Constants.P_CO_STMEDNO); }

    
    
    public BSCSModel getInfoFields(){
        return getBSCSModelValue(FICTIF_INFOFIELD);
    }
    
    public void setInfoFields(BSCSModel infofields){
        setBSCSModelValue(FICTIF_INFOFIELD, infofields);
    }

}
