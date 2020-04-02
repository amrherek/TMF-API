package com.orange.bscs.api.connection.backend;

import com.orange.bscs.api.connection.IConnectionBackend;
import com.orange.bscs.api.connection.SOIConnection;
import com.orange.bscs.api.connection.SOITransactionsManager;
import com.orange.bscs.api.exceptions.APIException;
import com.orange.bscs.api.model.AbstractSVLObjectFactory;
import com.orange.bscs.api.model.ParameterListWrapper;
import com.orange.bscs.api.model.SVLObjectListWrapper;
import com.orange.bscs.api.model.SVLObjectWrapper;

/**
 * Base Backend délégating to source backend.
 *
 * @author IT&Labs
 */
public class ConnectionBackendBase implements IConnectionBackend {

    /* by Instances */
    private IConnectionBackend srcBackend;

    /**
     * Instance/by backend constructor
     *
     * @param source source/delegated backend
     */
    public ConnectionBackendBase(IConnectionBackend source) {
        srcBackend = source;
    }


    @Override
    public void close() {
        if (null != srcBackend) {
            srcBackend.close();
        }
    }


    @Override
    public void reopen() {
        if (null != srcBackend) {
            srcBackend.reopen();
        }
    }

    @Override
    public boolean isAlive() {
        if (null != srcBackend) {
            return srcBackend.isAlive();
        }
        return false;
    }

    @Override
    public void commit() {
        if (null != srcBackend) {
            srcBackend.commit();
        }
    }

    @Override
    public void rollback() {
        if (null != srcBackend) {
            srcBackend.rollback();
        }
    }

    @Override
    public SVLObjectWrapper execute(String command, SVLObjectWrapper input) {
        if (null == srcBackend) {
            return createSVLObject();
        } else {
            return srcBackend.execute(command, input);
        }
    }


    @Override
    public SVLObjectWrapper createSVLObject() {
        if (null == srcBackend) {
            return AbstractSVLObjectFactory.createSVLObject();
        } else {
            return srcBackend.createSVLObject();
        }
    }

    @Override
    public SVLObjectListWrapper createSVLObjectList() {
        if (null == srcBackend) {
            return AbstractSVLObjectFactory.createSVLObjectList();
        } else {
            return srcBackend.createSVLObjectList();
        }
    }

    /**
     * Implementation of ServiceLayerIntrospector
     * <p>
     * No trace
     */
    @Override
    public String[] getCommands(String componentName, String componentVersion) {
        String[] result = null;
        if (null != srcBackend) {
            result = srcBackend.getCommands(componentName, componentVersion);
        }
        return result;
    }

    /**
     * Implementation of ServiceLayerIntrospector
     * <p>
     * No trace
     */
    @Override
    public ParameterListWrapper getInputList(String command, String componentName, String componentVersion)
            throws APIException {
        ParameterListWrapper result = null;
        if (null != srcBackend) {
            result = srcBackend.getInputList(command, componentName, componentVersion);
        }
        return result;
    }

    /**
     * Implementation of ServiceLayerIntrospector
     * <p>
     * No Trace
     */
    @Override
    public ParameterListWrapper getOutputList(String command, String componentName, String componentVersion)
            throws APIException {
        ParameterListWrapper result = null;
        if (null != srcBackend) {
            result = srcBackend.getOutputList(command, componentName, componentVersion);
        }
        return result;
    }

    @Override
    public void doChangeSession(SOITransactionsManager soiTransactionsManager, SOIConnection soiConnection, String bscsBusinessUnit, String bscsLogin) {
        // By default nothing to do
    }


    public IConnectionBackend getSourceBackend() {
        return srcBackend;
    }

    public void setSourceBackend(IConnectionBackend backend) {
        srcBackend = backend;
    }

}
