package com.orange.bscs.api.model;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

/**
 * Created by deyb6792 on 17/08/2016.
 */
public interface SVLDeserializer {
    SVLObjectWrapper deserialize(InputStream is) throws IOException, ParseException;
}
