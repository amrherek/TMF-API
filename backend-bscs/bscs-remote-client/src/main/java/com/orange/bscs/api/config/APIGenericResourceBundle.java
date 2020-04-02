package com.orange.bscs.api.config;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Specialised PropertyResourceBundle/PropertyResourceBundleFactory checking if bundle exist before 
 * creating a new one.
 * 
 * hierarchy level of bundle is preserve by using ResourceBundle.setParent(bundle).
 * 
 * @author IT&Labs
 *
 */
public final class APIGenericResourceBundle extends PropertyResourceBundle {

    private static final String PROPERTIES = ".properties";
    private static final Logger LOG = LoggerFactory.getLogger(APIGenericResourceBundle.class);

    /**
     * private Constructor, used by the factoryMethod : getBundle(...)
     * 
     * @param is inputStream of this Bundle. should not be null.
     * @param parent the parent bundle.
     * 
     * @throws IOException
     */
    private APIGenericResourceBundle(InputStream is, ResourceBundle parent) throws IOException{
        super(is);
        setParent(parent);
    }

    public static ResourceBundle getBundle(String baseName, ResourceBundle parent){
        String name = "/" + baseName + PROPERTIES;
        InputStream is = ApiGeneric.class.getResourceAsStream(name);
        if(null==is){
            is = ApiGeneric.class.getClassLoader().getResourceAsStream(baseName + PROPERTIES);
        }
        if(null==is){
            is = ApiGeneric.class.getClassLoader().getResourceAsStream(name);
        }
        if(null==is){
            is = new ByteArrayInputStream(new byte[]{});
        }
        try {
            return new APIGenericResourceBundle(is,parent);
        } catch (IOException e) {
            LOG.info("Can't load ResourceBundle {} : {}",baseName,e);
            return parent;
        }finally{
            try {
                is.close();
            } catch (IOException e) {
                LOG.info("Can't close ResourceBundle {} : {}",baseName,e);
            }
        }
    }

}
