package com.orange.mea.apisi.bucketbalance.backend.bscs;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orange.bscs.model.BscsBalance;
import com.orange.bscs.model.BscsContractFreeUnitAccount;
import com.orange.bscs.model.BscsContractSearch;
import com.orange.bscs.model.BscsUsageDataRecord;
import com.orange.bscs.model.criteria.BscsUsageDataRecordSearchCriteria;
import com.orange.bscs.model.factory.BscsObjectFactory;
import com.orange.bscs.service.BscsContractServiceAdapter;
import com.orange.bscs.service.BscsUsageDataServiceAdapter;
import com.orange.bscs.service.exception.BscsInvalidFieldException;

@Service
public class BucketBalanceBscsService {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
    private BscsContractServiceAdapter contractServiceAdapter;

	@Autowired
    private BscsUsageDataServiceAdapter usageDataRecordServiceAdapter;

    @Autowired
    protected BscsObjectFactory bscsObjectFactory;

	/**
	 * Execute CONTRACT_FUA.READ command. Return an empty list if no free unit found
	 * 
	 * @param coIdPub
	 * @return
	 */
    public List<BscsContractFreeUnitAccount> readFreeUnits(String coIdPub) {
		// execute CONTRACT_FUA.READ
        return contractServiceAdapter.getFreeUnits(coIdPub);
	}

	/**
	 * Execute BALANCES.READ command. Return an empty list if no balance found
	 * 
	 * @param coIdPub
	 * @return
	 */
    public List<BscsBalance> readBalances(String coIdPub) {
		// execute BALANCES.READ
        return contractServiceAdapter.getBalances(coIdPub);
	}

	/**
     * Execute USAGE_DATA_RECORDS.READ command. Return an empty list if no
     * records found
     * 
     * @param contractId
     * @param startFromDate
     * @param startToDate
     * @param callType
     * @param searchLimit
     * @return
     */
    public List<BscsUsageDataRecord> findUsageDataRecords(String contractId, Date startFromDate,
            Date startToDate, Long callType, int searchLimit) {
		// execute USAGE_DATA_RECORDS.READ
        BscsUsageDataRecordSearchCriteria bscsUsageDataRecordRead = bscsObjectFactory
                .createBscsUsageDataRecordSearchCriteria();
        bscsUsageDataRecordRead.setContractId(contractId);
		bscsUsageDataRecordRead.setCallType(callType);
		bscsUsageDataRecordRead.setStartFromDate(startFromDate);
        bscsUsageDataRecordRead.setStartToDate(startToDate);
		bscsUsageDataRecordRead.setSearchLimit(searchLimit);

        return usageDataRecordServiceAdapter.getUsageDataRecords(bscsUsageDataRecordRead);
	}

    /**
     * Find latest contract assigned to a msisdn
     * 
     * @param msisdn
     * @return
     * @throws BscsInvalidFieldException
     */
    public BscsContractSearch findContractByMsisdn(String msisdn) throws BscsInvalidFieldException {
        return contractServiceAdapter.findContractByMsisdn(msisdn);
    }

}
