package com.orange.bscs.api.connection.service;

import com.orange.bscs.api.exceptions.BscsConnectionException;

/**
 * Interface for BscsConnectionService (opening and closing bscs connections)
 * 
 * @author xbbs3851
 *
 */
public interface BscsConnectionService {

	/**
	 * Open a connection to bscs using provided credentials and store it into
	 * the ConnectionHolder ThreadLocal
	 */
	void openConnection() throws BscsConnectionException;

	/**
	 * Close an opened connection
	 */
	void closeConnection();

	/**
	 * Commit the transaction
	 */
	void commit();

}
