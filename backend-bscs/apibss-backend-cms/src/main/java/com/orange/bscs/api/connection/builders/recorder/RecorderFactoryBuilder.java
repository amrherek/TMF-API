package com.orange.bscs.api.connection.builders.recorder;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.orange.bscs.api.connection.IConnectionBackendFactory;
import com.orange.bscs.api.connection.backend.ConnectionBackendFactoryRecorder;
import com.orange.bscs.api.connection.builders.FactoryBuilder;

/**
 * Builds a ConnectionBackendFactoryRecorder using provided properties
 * 
 * @author xbbs3851
 *
 */
@Service
@Qualifier("recorderFactoryBuilder")
public class RecorderFactoryBuilder implements FactoryBuilder {


	@Override
	public IConnectionBackendFactory build(IConnectionBackendFactory sourceBackend) {

		return new ConnectionBackendFactoryRecorder(sourceBackend);

	}


}
