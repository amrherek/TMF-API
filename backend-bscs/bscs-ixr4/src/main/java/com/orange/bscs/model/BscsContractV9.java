package com.orange.bscs.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.orange.bscs.model.businesspartner.EnumContractStatus;
import com.orange.bscs.model.contract.BSCSContract;
import com.orange.bscs.model.contract.BSCSContractService;

public class BscsContractV9 extends BscsContract {

    public BscsContractV9(BSCSContract contract) {
        super(contract);
    }

    public BscsContractV9() {
        super();
    }

    @Override
    public String getId() {
        // CO_ID_PUB
        return contract.getContractIdPub();
    }

    @Override
    public void setId(String id) {
        // CO_ID_PUB
        contract.setContractIdPub(id);
    }

    @Override
    public Long getNumericId() {
        // CO_ID
        return contract.getContractId();
    }

    @Override
    public String getRatePlanCode() {
        // RPCODE_PUB
        return contract.getRatePlanPublicCode();
    }

    @Override
    public Long getRatePlanNumericCode() {
        // RPCODE
        return contract.getRatePlanCode();
    }

    @Override
    public String getStorageMediumNum() {
        // CO_STMEDNO
        return contract.getStorageMediumNum();
    }

    @Override
    public EnumContractStatus getStatus() {
        // CO_STATUS
        return contract.getStatus();
    }

    @Override
    public void setStatus(EnumContractStatus status) {
        // CO_STATUS
        contract.setStatus(status);
    }

    @Override
    public Date getDateActivated() {
        // CO_ACTIVATED
        return contract.getDateActivated();
    }

    @Override
    public Date getStatusDate() {
        // CO_LAST_STATUS_CHANGE_DATE
        return contract.getStatusDate();
    }

    @Override
    public String getCustomerId() {
        // CS_ID_PUB
        return contract.getCustomerIdPub();
    }

    @Override
    public Long getLastReasonChangeStatus() {
        // CO_LAST_REASON
        return contract.getLastReasonChangeStatus();
    }
    
    @Override
    public void setReasonChangeStatus(Long reason) {
        // REASON
        contract.setReasonChangeStatus(reason);
    }

    @Override
    public List<BscsContractServiceFromContract> getServices() {
        // services
        List<BSCSContractService> services = contract.getServices();
        List<BscsContractServiceFromContract> res = new ArrayList<>();
        for (BSCSContractService contractService : services) {
            res.add(new BscsContractServiceFromContractV9(contractService));
        }
        return res;
    }

    @Override
    public void setValidFrom(Date statusModificationDate) {
        // VALID_FROM
        contract.setDateValidFrom(statusModificationDate);
    }

}
