package com.orange.bscs.api.connection.backend;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.orange.bscs.api.command.BaseCommand;
import com.orange.bscs.api.connection.IConnectionBackend;
import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.model.SVLObjectWrapper;

/**
 * Objectif: Placer en Set l'intégralité des commandes exécutées sans erreur.
 * 
 * Usage : Tests Unitaires, permet de vérifier que certaines commandes de mise à jour ont effectivement 
 *         été appeler.
 * 
 * Mise en oeuvre, declarer un bean recordBackEnd et y associer un backend super. 
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
 * Mise en oeuvre, declarer un bean recordBackEnd et y associer un backend super. 
 * L'enregistement peut être desactive.
 * 
 * @author IT&Labs
 *
 */
public class ConnectionBackendRecorder extends ConnectionBackendBase {


    /** Each backend : Result of executed CMS Command	 
     * LinkedHashSet preserve order of insertion. 
     **/
    private Set<BackendCommand> recorder = new LinkedHashSet<BackendCommand>();

    private boolean recordParameters=false;

    /* ==================================================================
     *                  CONSTRUCTORS
     * ==================================================================*/

    /** 
     * Backend internal usage, used by factory 
     *
     * default visibility, used only by Factory
     **/
    ConnectionBackendRecorder(IConnectionBackend source, boolean recOutputs) {
        super(source);
        recordParameters=recOutputs;
    }


    /* ==================================================================
     *                  BACKEND methods
     * ==================================================================*/

    @Override
    public void close() {
        super.close();
        addSimpleCommand("CLOSE");
    }

    @Override
    public void reopen() {
        super.reopen();
        addSimpleCommand("REOPEN");
    }

    @Override
    public void commit() {
        super.commit();
        addSimpleCommand("COMMIT");
    }

    private void addSimpleCommand(String operationCMS) {
        recorder.add(new BackendCommand(operationCMS));
    }

    @Override
    public void rollback() {
        super.rollback();
        addSimpleCommand("ROLLBACK");
    }

    @Override
    public SVLObjectWrapper execute(String command, SVLObjectWrapper input) {
        SVLObjectWrapper output = super.execute(command, input);
        if(recordParameters) {
            recorder.add(new BackendCommand(command, input, output));
        } else {
            addSimpleCommand(command);
        }
        return output;
    }


    /* ==================================================================
     *                  ACCESSORS
     * ==================================================================*/

    /**
     * @return Set of executed commands, ordering by execution. This set is immutable (unmodifiable).
     */
    public Set<? extends BaseCommand<BSCSModel,BSCSModel>> getRecords(){
        return Collections.unmodifiableSet(recorder);
    }

    /**
     * @return Set of all execution of this commandCMS.
     */
    @SuppressWarnings("unchecked")
    public <T extends BaseCommand<BSCSModel,BSCSModel>> Set<T> getRecords(String commandCMS){
        Set<BackendCommand> set  = new LinkedHashSet<BackendCommand>();
        if(null!=commandCMS){
            for(BackendCommand cmd : recorder){
                if(commandCMS.equals(cmd.getCommand())){
                    set.add(cmd);
                }
            }
        }
        return (Set<T>) set;
    }

    public <T extends BaseCommand<BSCSModel,BSCSModel>> T getRecord(String commandCMS){
        T result=null;

        Set<T> allRunnings = getRecords(commandCMS);
        if(null!=allRunnings){
            if(allRunnings.size()>1){
                throw new IndexOutOfBoundsException(String.format("More than 1 execution of command %s : %s", commandCMS,allRunnings.size()));
            }
            result = allRunnings.iterator().next();
        }
        return result;
    }

    /**
     * Check if all this commands were executed.
     * 
     * @param commands
     * @return
     */
    public boolean containsAll(String... commands){
        boolean contains=false;
        for(String cmd : commands){
            contains=false;
            for(BackendCommand cc :recorder){
                if(cc.getCommand().equals(cmd)){
                    contains=true;
                    break;
                }
            }
            if(!contains){
                // this command is not present, exit
                break;
            }
        }
        return contains;

    }

    public int retrieveNbExecution(String command){
        int nb=0;
        for(BackendCommand cmd :recorder){
            if(cmd.getCommand().equals(command)){
                nb++;
            }
        }
        return nb;
    }

    public Map<String,Integer> retrieveNbExecution(String... commands){
        Map<String,Integer> result = new HashMap<String,Integer>();
        for(String cmd : commands){
            result.put(cmd, retrieveNbExecution(cmd));
        }
        return result;
    }

    /**
     * Flush cache of executed commands.
     */
    public void reset(){
        recorder.clear();
    }




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
