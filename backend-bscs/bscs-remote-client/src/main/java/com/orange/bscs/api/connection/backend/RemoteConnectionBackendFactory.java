package com.orange.bscs.api.connection.backend;

import com.orange.bscs.api.connection.IConnectionBackend;
import com.orange.bscs.api.connection.IConnectionBackendFactory;
import com.orange.bscs.svlbackend.SVLBackendPortType;

/**
 * Configure and create when needed (ask by ConnnectionPoolFactory) a
 * backend with is a customer of a SVLBackend WebService.
 *
 * @author IT&L@bs
 */
public class RemoteConnectionBackendFactory implements IConnectionBackendFactory {

    private SVLBackendPortType remoteBackend;

    public RemoteConnectionBackendFactory(SVLBackendPortType remoteBackend) {
        this.remoteBackend = remoteBackend;
    }

    @Override
    public void postInitialisation() {
        // Nothing to do
    }

    @Override
    public IConnectionBackend newInstance() {

        RemoteConnectionBackend backend = new RemoteConnectionBackend();

        // Ok , Address an proxy configure, can be used by backend client.
        backend.setClient(remoteBackend);

        return backend;
    }


    /**
     * Don't allow sourceFactory but doesn't throw any exception, ignore this call.
     */
    @Override
    public void setSourceBackendFactory(IConnectionBackendFactory srcFactory) {
        // no source to set
    }

}
