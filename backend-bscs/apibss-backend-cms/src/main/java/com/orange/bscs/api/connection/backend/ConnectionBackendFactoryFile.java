package com.orange.bscs.api.connection.backend;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import com.orange.bscs.api.connection.IConnectionBackend;
import com.orange.bscs.api.connection.IConnectionBackendFactory;

/**
 * ConnectionBackendFile compute MD5 of Command + Inputs and try to unmarshall a file with that name
 * and containing the wished result.
 * 
 * @author IT&Labs
 *
 */
public class ConnectionBackendFactoryFile extends ConnectionBackendFactoryBase<ConnectionBackendFile> {

    private static final String FILE_URI = "file://";

    private static final Logger LOG = LoggerFactory.getLogger(ConnectionBackendFactoryFile.class);

    /* For Factory */
    private Resource racineResource;
    private String racinePath;

    private File outputDirectory;
    private String outputPath;

    private String mode;

    private boolean readOnly;
    private boolean read;
    private boolean write;



    /**
     * Default constructor for Spring DI
     * 
     * (this factory create instance of fileBackEnd with protected constructor).
     */
    public ConnectionBackendFactoryFile(){
        // Empty Constructor
    }

    /**
     * FactoryConstructor
     * 
     * @param connectionBackendFactoryFile
     * @param mode one of READ / READONLY / READWRITE / WRITE
     * @param folder racine path.
     */
    public ConnectionBackendFactoryFile(IConnectionBackendFactory srcBackendFactory, String modeReadWrite, String racinePath) {
        super(srcBackendFactory);

        changeRacinePath(racinePath);
        parseMode(modeReadWrite);
    }



    /**
     * Only called if this factory is used in the chain from SOITransactionsManager
     */
    @Override
    public void postInitialisation()  {
        super.postInitialisation();

        assert null!=racineResource : "RacineResource not initialized";

        assert null!=getSourceBackendFactory() : "Source Backend Factory not initialized";

        LOG.info("FileBackendFactory initialised with :");
        LOG.info("Source backend   : {}" , getSourceBackendFactory().getClass().getSimpleName());
        LOG.info("Source resource  : {}" , null==this.racineResource ? "null" : this.racineResource.getDescription());
        LOG.info("Destination path : {}" , null==this.outputDirectory ? "null" : this.outputDirectory.getAbsolutePath());
        LOG.info("Mode             : {}" , this.mode);
    }


    public void changeRacine(Resource racine) {
        LOG.info("Set root resource : {}", null==racine ? "null":racine.getDescription());
        racineResource=racine;
    }

    public final void changeRacinePath(final String location){
        Resource resource = retrieveResource(location);
        changeRacine(resource);
    }

    private Resource retrieveResource(final String location) {
        String path=location;
        if(null==path){
            return null;
        }
        path=path.replace('\\', '/');
        if(!path.endsWith("/") ){
            path += "/";
        }
        if(path.contains(":") && 1==path.indexOf(':')){
            // C: or D:..
            path="file:///"+path;
        }
        if(path.startsWith(FILE_URI) && !path.startsWith(FILE_URI+"/")){
            path="file:///"+path.substring(7);
        }
        Resource resource = new DefaultResourceLoader().getResource(path);
        return resource;
    }

    /**
     * @throws com.orange.bscs.api.model.exception.SOIException 
     */
    @Override
    public ConnectionBackendFile newInstance() {
        IConnectionBackend source = getSourceBackendFactory().newInstance();

        return new ConnectionBackendFile(racineResource, outputDirectory, source, read,readOnly,write);
    }


    public String getRacinePath() {
        return racinePath;
    }

    public void setRacinePath(String path) {
        racinePath = path;
        changeRacinePath(path);
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        parseMode(mode);
    }

    private void parseMode(String pMode){
        read=false;
        readOnly=false;
        write=false;
        if(null!=pMode) {
            String lMode = pMode.toLowerCase().trim();
            if("read".equals(lMode)){
                read=true;
                readOnly=false;
                write=false;
            }else if ("readonly".equals(lMode)){
                read=true;
                readOnly=true;
                write=false;
            } else if ("write".equals(lMode)){
                read=false;
                readOnly=false;
                write=true;
            } else if ("readwrite".equals(lMode)){
                read=true;
                readOnly=false;
                write=true;
            } else {
                lMode=null;
            }
            mode=lMode;
        } else {
            mode=null;
        }
        
        LOG.info("Set mode : {}", mode);
    }

    /**
     * @return le outputPath
     */
    public String getOutputPath() {
        return outputPath;
    }

    /**
     * @param outputPath le repertoire dans lequel seront stockees les commandes si le mode Write/ReadWrite est actif.
     */
    public void setOutputPath(final String outputPath) {
        this.outputPath = outputPath;
        this.outputDirectory=null;
        Resource res = retrieveResource(outputPath);
        if(null!=res){
            File dir;
            try {
                dir = res.getFile();
                if(dir.isDirectory()){
                    outputDirectory=dir;
                    LOG.info("Set output path : {}", outputDirectory);
                } else {
                    LOG.warn("Ouput path {} is not a directory", outputPath);
                }
            } catch (IOException e) {
                LOG.warn("Ouput path {} is not a directory : {}", outputPath,e);
            }
        }
    }

}
