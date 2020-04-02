package com.orange.bscs.api.connection.builders.remote;

import com.orange.bscs.api.connection.IConnectionBackendFactory;
import com.orange.bscs.api.connection.backend.RemoteConnectionBackendFactory;
import com.orange.bscs.api.connection.builders.FactoryBuilder;
import com.orange.bscs.svlbackend.SVLBackendPortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Date : 20/07/2017.
 *
 * @author Denis Borscia (deyb6792)
 */
@Service
@Qualifier("remoteFactoryBuilder")
public class RemoteFactoryBuilder implements FactoryBuilder {

    @Autowired
    private SVLBackendPortType backendPortType;

    @Override
    public IConnectionBackendFactory build(IConnectionBackendFactory source) {

        RemoteConnectionBackendFactory factory = new RemoteConnectionBackendFactory(backendPortType);


        return factory;
    }
}
