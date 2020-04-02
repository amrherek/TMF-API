package com.orange.bscs.api.connection.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.orange.bscs.api.connection.ConnectionHolder;
import com.orange.bscs.api.connection.SOIConnectionImpl;
import com.orange.bscs.api.connection.SOITransactionsManager;
import com.orange.bscs.api.exceptions.BscsConnectionException;

/**
 * Manage creation/release of SOIConnection to BSCS
 * 
 */
@Service
public class BscsConnectionServiceImpl implements BscsConnectionService {
	
	@Autowired
	private SOITransactionsManager soiTransactionsManager;

	@Value("${bscs.transactionid}")
	private String transactionId;

	@Value("${bscs.applicationid}")
	private String applicationid;

	@Value("${bscs.userId}")
	private String userId;

	@Value("${bscs.bu}")
	private String businessUnit;


	@Override
	public void openConnection()
			throws BscsConnectionException {
		try {
			SOIConnectionImpl soiConnection = soiTransactionsManager
					.getSOIConnection(transactionId, applicationid, userId, businessUnit);
			ConnectionHolder.setConnection(soiConnection);

		} catch (Exception exception) {
			throw new BscsConnectionException("Cannot get connection to BSCS");
		}
	}

	@Override
	public void closeConnection() {
		soiTransactionsManager.releaseSOIConnection(ConnectionHolder.getCurrentConnection());
		ConnectionHolder.unset();
	}

	@Override
	public void commit() {
		ConnectionHolder.getCurrentConnection().commit();
	}

}
