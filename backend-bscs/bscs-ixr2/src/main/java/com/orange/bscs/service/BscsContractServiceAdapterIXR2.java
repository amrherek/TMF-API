package com.orange.bscs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.orange.bscs.model.BscsContractSearch;
import com.orange.bscs.model.businesspartner.EnumContractStatus;
import com.orange.bscs.model.criteria.BscsContractSearchCriteria;

@Service
@Primary
public class BscsContractServiceAdapterIXR2 extends BscsContractServiceAdapterV9 {

    @Override
    public List<BscsContractSearch> findContractsByIccId(String iccId) {
        final BscsContractSearchCriteria contractSearchCriteria = bscsObjectFactory.createBscsContractSearchCriteria();
        contractSearchCriteria.setStorageMedium(iccId);
        // get only currently assigned contract with this iccId => not
        // operational for OTN
        contractSearchCriteria.setSearcher("ContractSearchWithoutHistory");
        List<BscsContractSearch> contracts = findContracts(contractSearchCriteria);
        // keep only latest contracts
        return filterLatestContractsForIccId(contracts);
    }
    
    private List<BscsContractSearch> filterLatestContractsForIccId(List<BscsContractSearch> contracts) {
        // we may have different iccId (if the input iccId contains a '*')
        Map<String, BscsContractSearch> contractsByIccId = new HashMap<>();
        for (BscsContractSearch contract : contracts) {
            if (!contractsByIccId.containsKey(contract.getStorageMediumNum())) {
                contractsByIccId.put(contract.getStorageMediumNum(), contract);
            } else {
                BscsContractSearch memorizedContract = contractsByIccId.get(contract.getStorageMediumNum());
                if (memorizedContract.getStatus() == EnumContractStatus.ON_HOLD
                        || memorizedContract.getStatus() == EnumContractStatus.ACTIVATED) {
                    // most recent contract already found
                    continue;
                }
                if (memorizedContract.getDateActivated() == null // no date in memorized contract
                        || contract.getStatus() == EnumContractStatus.ON_HOLD // current contract is on hold (no date) => most recent 
                        || (contract.getDateActivated() != null 
                                && contract.getDateActivated().after(memorizedContract.getDateActivated()))) // activation date is more recent 
                {
                    contractsByIccId.put(contract.getStorageMediumNum(), contract);
                }
            }
        }
        return new ArrayList<>(contractsByIccId.values());
    }

}
