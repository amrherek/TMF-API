package com.orange.bscs.api.connection.builders;

import com.orange.bscs.api.connection.IConnectionBackendFactory;

/**
 * Interface for building decorated connections factories. Each builder has the
 * responbility to read the appropriate properties for the factory it manages.
 * 
 * @author xbbs3851
 *
 */
public interface FactoryBuilder {

	/**
	 * Builds the factory
	 * 
	 * @return
	 */
	IConnectionBackendFactory build(IConnectionBackendFactory sourceBackend);

}
