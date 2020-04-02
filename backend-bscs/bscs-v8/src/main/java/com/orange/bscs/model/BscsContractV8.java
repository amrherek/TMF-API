package com.orange.bscs.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang.ObjectUtils;

import com.orange.bscs.model.businesspartner.EnumContractStatus;
import com.orange.bscs.model.contract.BSCSContract;
import com.orange.bscs.model.contract.BSCSContractService;

public class BscsContractV8 extends BscsContract {

    public BscsContractV8(BSCSContract contract) {
        super(contract);
    }

    public BscsContractV8() {
        super();
    }

    @Override
    public String getId() {
        // CO_ID
        return Objects.toString(contract.getContractId(), null);
    }

    @Override
    public void setId(String id) {
        // CO_ID
        if (id != null) {
            contract.setContractId(Long.parseLong(id));
        }
    }

    @Override
    public Long getNumericId() {
        // CO_ID
        return contract.getContractId();
    }

    @Override
    public String getRatePlanCode() {
        // RP_CODE
        return Objects.toString(getRatePlanNumericCode(), null);
    }

    @Override
    public Long getRatePlanNumericCode() {
        // RP_CODE
        return contract.getLongValue("RP_CODE");
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
        // CO_STATE
        if (status != null) {
            contract.setIntegerValue("CO_STATE", status.toInt());
        }
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
        // CS_ID
        return ObjectUtils.toString(contract.getCustomerId(), null);
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
            res.add(new BscsContractServiceFromContractV8(contractService));
        }
        return res;
    }

    @Override
    public void setValidFrom(Date statusModificationDate) {
        // VALID_FROM
        contract.setDateValidFrom(statusModificationDate);
    }

}
