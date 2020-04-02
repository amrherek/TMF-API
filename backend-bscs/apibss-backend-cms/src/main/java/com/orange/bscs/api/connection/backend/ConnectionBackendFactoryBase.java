/**
 * 
 */
package com.orange.bscs.api.connection.backend;

import com.orange.bscs.api.connection.IConnectionBackend;
import com.orange.bscs.api.connection.IConnectionBackendFactory;

/**
 * BackendFactory with source factory may implements this base class
 * 
 * @author IT&Labs
 *
 */
public abstract class ConnectionBackendFactoryBase<BACKEND extends IConnectionBackend> implements IConnectionBackendFactory {

    private IConnectionBackendFactory sourceBackendFactory;


    /**
     * Default constructor, warning, setSourceBackendFactory() may be called
     * if this factory decorate a source factory.
     */
    protected ConnectionBackendFactoryBase(){
        // Comment in empty method
    }


    /**
     * Correctly initialise this factory.
     */
    protected ConnectionBackendFactoryBase(IConnectionBackendFactory srcFactory){
        sourceBackendFactory=srcFactory;
    }


    /**
     * @see com.orange.bscs.api.connection.IConnectionBackendFactory#newInstance()
     */
    @Override
    public abstract BACKEND newInstance();




    /**
     * @return configured source factory
     */
    public IConnectionBackendFactory getSourceBackendFactory(){
        return sourceBackendFactory;
    }


    /**
     * @param srcFactory source factory in case of chain
     * 
     * @see com.orange.bscs.api.connection.IConnectionBackendFactory#setSourceBackendFactory(com.orange.bscs.api.connection.IConnectionBackendFactory)
     */
    @Override
    public void setSourceBackendFactory(IConnectionBackendFactory srcFactory) {
        sourceBackendFactory=srcFactory;
    }


    /**
     * Called once if this factory is in the BackendFactory chain used to initialise a SOIConnection.
     *
     *<p>
     * May be override to check configuration but 
     * should call super.postInitialisation() or sourceFactory.postInitialisation()
     * </p>
     * 
     * @see com.orange.bscs.api.connection.IConnectionBackendFactory#postInitialisation()
     */
    @Override
    public void postInitialisation() {
        if(null!=sourceBackendFactory){
            sourceBackendFactory.postInitialisation();
        }
    }

}
