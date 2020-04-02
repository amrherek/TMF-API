package com.orange.bscs.api.connection.backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import com.orange.bscs.api.connection.IConnectionBackend;
import com.orange.bscs.api.connection.SOIConnection;
import com.orange.bscs.api.connection.SOITransactionsManager;
import com.orange.bscs.api.connection.SVLInputHash;
import com.orange.bscs.api.model.AbstractSVLObjectFactory;
import com.orange.bscs.api.model.SVLObjectWrapper;
import com.orange.bscs.api.model.exception.SOIException;

public class ConnectionBackendFile extends ConnectionBackendBase {

    private static final Logger LOG = LoggerFactory.getLogger(ConnectionBackendFile.class);

    // Read File
    private Resource racineResource;

    // Write file. Must be a directory
    private File outputResource;

    private boolean readOnly;
    private boolean read;
    private boolean write;


    /**
     * Default constructor for Spring DI
     * <p>
     * (this factory create instance of fileBackEnd with protected constructor).
     */
    public ConnectionBackendFile() {
        super(null);
    }

    /**
     * Constructor for Instances of backend.
     * Not public because only used by factory.
     *
     * @param racine   Where are files?
     * @param source   delegated backend
     * @param read     read result of commands ?
     * @param readonly force to read but denied write
     * @param write    write response of source backend to file.
     */
    protected ConnectionBackendFile(Resource racine, File output, IConnectionBackend source, boolean read, boolean readonly, boolean write) {
        super(source);
        racineResource = racine;
        outputResource = output;
        this.read = read;
        readOnly = readonly;
        this.write = write;

        LOG.info("Initialised with racine : {}, output: {}, source : {}, readOnly : {}, read : {}, write : {}",
                racine, output, source, readonly, read, write);
    }


    @Override
    public SVLObjectWrapper execute(String command, SVLObjectWrapper input) {

        String filename = SVLInputHash.encodeCommand(command, input) + ".TXT";
        String defaultfilename = command + ".TXT";

        SVLObjectWrapper result = null;

        Resource resCommand = null;

        try {


            // 1°) Recherche le fichier avec l'exactitude des paramètres, si non-trouvé,
            //     , recherche du fichier par defaut (retourné quelque soient les paramètres)

            resCommand = searchResource(filename, defaultfilename);

            if (readOnly) {
                if ("SESSION.CHANGE".equalsIgnoreCase(command)) {
                    return createSVLObject();
                }
                if (null == resCommand) {
                    LOG.warn("execute ({}, {\n{}} => ERROR: file {} does not exist to replay", new Object[]{command, input, filename});
                    throw new SOIException(String.format("Error file %s does not exists to replay!", filename));
                }
                result = deserialise(resCommand);

            } else if (read && null != resCommand) {
                result = deserialise(resCommand);
            } else {
                LOG.trace("Calling backend with command: {}", command);
                if (null != getSourceBackend()) {
                    result = super.execute(command, input);
                    if (write && (null == resCommand || !(resCommand.exists())) && !"SESSION.CHANGE".equalsIgnoreCase(command)) {
                        try {
                            File outDir = null == outputResource ? racineResource.getFile() : outputResource;
                            File fileOutput = new File(outDir, filename);

                            // Enregistre le fichier (avec le suffixe correspondant aux paramètres d'input
                            serialise(fileOutput, command, input, result);
                        } catch (IOException e) {
                            throw new SOIException(String.format("Error serialising in filename %s", filename), e);
                        }
                    }
                }
            }

        } catch (IOException e) {
            throw new SOIException(String.format("Error deserialising %s ", filename), e);
        } catch (ParseException e) {
            throw new SOIException(String.format("Error deserialising %s ", filename), e);
        }
        return result;
    }

    private Resource searchResource(final String filename, final String defaultfilename) {
        Resource resCommand = null;

        LOG.trace("Trying to find filename: {}", filename);

        resCommand = checkExistingResource(racineResource, filename);
        if (null == resCommand) {
            resCommand = checkExistingResource(racineResource, "../" + filename);
        }

        if (null == resCommand) {
            resCommand = checkExistingResource(racineResource, defaultfilename);
        }
        if (null == resCommand) {
            resCommand = checkExistingResource(racineResource, "../" + defaultfilename);
        }

        if (null != resCommand) {
            LOG.debug("   Found filename : {}", resCommand);
        }
        return resCommand;
    }

    private Resource checkExistingResource(Resource parent, String filename) {
        if (null == parent) {
            return null;
        }

        Resource res = null;
        try {
            res = parent.createRelative(filename);
            if (null != res && !res.exists()) {
                res = null;
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return res;
    }


    private void serialise(File fileCmd, String command, SVLObjectWrapper input, SVLObjectWrapper output) throws FileNotFoundException, UnsupportedEncodingException {

        PrintStream fout = new PrintStream(fileCmd, "UTF-8");
        try {
            fout.println("<!--");
            fout.append(" execute (")
                    .append(command)
                    .append(", {")
                    .append(null == input ? "})\r\n" : "\r\n"
                            + input.toString() + "})\r\n").append("-->\r\n");
            if (null == output) {
                fout.println("<!-- Output=Null -->");
            } else {
                fout.println(" => {");
                fout.println(output.toString());
                fout.println('}');
            }
            fout.println();
        } finally {
            fout.close();
        }
    }

    private SVLObjectWrapper deserialise(Resource resource) throws IOException, ParseException {
        SVLObjectWrapper out = null;
        InputStream is = resource.getInputStream();
        if (null != is) {
            try {
                out = AbstractSVLObjectFactory.getDeserializer().deserialize(is);
            } finally {
                is.close();
            }
        }
        return out;
    }

    public void changeRacine(Resource racine) {
        racineResource = racine;
        LOG.info("Changing racine to : {}", racine);

    }

    public Resource getRacine() {
        return racineResource;
    }

    @Override
    public void doChangeSession(SOITransactionsManager soiTransactionsManager, SOIConnection soiConnection,
            String bscsBusinessUnit, String bscsLogin) {
        getSourceBackend().doChangeSession(soiTransactionsManager, soiConnection, bscsBusinessUnit, bscsLogin);
    }

}
