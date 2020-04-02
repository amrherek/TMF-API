package com.orange.bscs.api.connection.backend;

import com.orange.bscs.api.connection.IConnectionBackend;
import com.orange.bscs.api.connection.IConnectionBackendFactory;

/**
 * Objectif: Placer en Set l'intégralité des commandes exécutées sans erreur.
 * 
 * Usage : Tests Unitaires, permet de vérifier que certaines commandes de mise à jour ont effectivement 
 *         été appeler.
 * 
 * Mise en oeuvre, declarer un bean recordBackEnd et y associer un backend source. 
 * L'enregistement peut être desactive.
 * 
 * @author IT&Labs
 *
 */
/**

 * Objectif: Placer en Set l'intégralité des commandes exécutées sans erreur.
 * 
 * Usage : Tests Unitaires, permet de vérifier que certaines commandes de mise à jour ont effectivement 
 *         été appeler.
 * 
 * Mise en oeuvre, declarer un bean recordBackEnd et y associer un backend source. 
 * L'enregistement peut être desactive.
 * 
 * @author IT&Labs
 *
 */
public class ConnectionBackendFactoryRecorder extends ConnectionBackendFactoryBase<ConnectionBackendRecorder> {

    private boolean recordParameters=false;

    /* ==================================================================
     *                  CONSTRUCTORS
     * ==================================================================*/


    /* ==================================================================
     *                  SPECIFICS Methods
     * ==================================================================*/

    public ConnectionBackendFactoryRecorder() {	
        // Empty Constructor
    }

    public ConnectionBackendFactoryRecorder(IConnectionBackendFactory srcFactory) {
        super(srcFactory);
    }




    /**
     * Initialise a new source backend each time this method is called and return "this".
     *  
     * this factory don't manage multiples instances,and must not be used by many
     * SOIConnection.
     */
    @Override
    public ConnectionBackendRecorder newInstance()  {
        IConnectionBackend source = getSourceBackendFactory().newInstance();
        return  new ConnectionBackendRecorder(source, recordParameters);
    }


    @Override
    public void postInitialisation() {
        assert null!=getSourceBackendFactory() : "Source Backend Factory not initialized";
    }

    /* ==================================================================
     *                  ACCESSORS
     * ==================================================================*/



    /**
     * @return true if saved Command contains Input and Output SVLObjects.
     */
    public boolean isRecordParameters() {
        return recordParameters;
    }

    /**
     * @param recordParameters if true, save Input and Output SVLObjects otherwise (default), only records Command Names.
     */
    public void setRecordParameters(boolean recordParameters) {
        this.recordParameters = recordParameters;
    }

}
