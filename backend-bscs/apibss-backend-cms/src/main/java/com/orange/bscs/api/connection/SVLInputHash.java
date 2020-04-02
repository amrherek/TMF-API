package com.orange.bscs.api.connection;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.orange.bscs.api.model.SVLObjectWrapper;

/**
 * Classe utilitaire utilisee par SVLDeserializer,ConnectionBackendCache, ConnectionBackendFile
 * pour calculer un nom de fichier correspondant à une commande et des paramètres.
 *
 * @author IT&Labs
 */
public final class SVLInputHash {

    private static final Logger LOG = LoggerFactory.getLogger(SVLInputHash.class);

    private static ThreadLocal<MessageDigest> threadLocal = new ThreadLocal<MessageDigest>();

    private SVLInputHash() {
        // Hide constructor in utility class
    }


    /**
     * Concatene la commande avec un hash correspondant a l'input.
     * <p>
     * La chaine resultat peut être utilisee pour nommer un fichier.
     *
     * @param command nom de la commande SOI ex CUSTOMER.READ
     * @param input   les paramètres de la commande
     * @return command [+ "_" + encodeInput(input)]
     */
    public static String encodeCommand(String command, SVLObjectWrapper input) {
        String result = command;
        String suffixe = encodeInput(input);

        if (null != suffixe) {
            result += "_" + suffixe;
        }
        return result;
    }


    /**
     * Calcule un Hash en fonction de l'input.
     * <p>
     * Comme les jeux de test sont realisés sous Windows,
     *
     * @param input an SVLObject
     * @return "EMPTY" if input is null or empty, otherwise return bas64(MD5(input.toString())
     */
    public static String encodeInput(SVLObjectWrapper input) {

        // Si pas de contenu, return EMPTY
        if (null == input || input.isEmpty()) {
            return "EMPTY";
        }

        byte[] datas = unixToDos(input.toString());
        return computeMD5(datas);
    }

    /*
     * Remarque, Base64 is not threadsafe
     */
    public static String computeMD5(byte[] datas) {
        MessageDigest md = getMessageDigest();
        if (md == null) {
            return null;
        }
        byte[] digest = md.digest(datas);
        String hash = DatatypeConverter.printBase64Binary(digest);
        hash = hash.replace('=', '_').replace('\\', '-').replace('/', '-').replace('+', '-');
        return hash;
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md = threadLocal.get();
        if (null == md) {
            try {
                md = MessageDigest.getInstance("MD5");
                threadLocal.set(md);
            } catch (NoSuchAlgorithmException e) {
                // Nothing that we can do.
                LOG.warn("Algo MD5 not found : {}", e);
            }
        }
        return md;
    }


    public static byte[] unixToDos(String input) {
        final int margeSupplementaire = 500;

        byte[] buffer;
        try {
            buffer = input.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOG.warn("Error reading stream as UTF-8: {}", e);
            buffer = input.getBytes(Charset.defaultCharset());
        }
        ByteArrayInputStream bis = new ByteArrayInputStream(buffer);

        ByteArrayOutputStream bos = new ByteArrayOutputStream(buffer.length + margeSupplementaire);

        byte[] result = null;

        // if finding \r, skip next \n
        boolean skipNext = false;
        try {
            int in;
            while ((in = bis.read()) != -1) {
                if ('\r' == in) {
                    bos.write(in);
                    skipNext = true;
                } else if (!(skipNext) && '\n' == in) {
                    bos.write('\r');
                    bos.write(in);
                } else {
                    bos.write(in);
                    skipNext = false;
                }
            }
            result = bos.toByteArray();
        } finally {
            try {
                bis.close();
            } catch (IOException e) {
                LOG.warn("Error closing input stream : {}", e);
                // do nothing
                bis = null;
            }
            try {
                bos.close();
            } catch (IOException e) {
                LOG.warn("Error closing output stream : {}", e);
                //do nothing
                bos = null;
            }
        }
        return result;
    }
}
