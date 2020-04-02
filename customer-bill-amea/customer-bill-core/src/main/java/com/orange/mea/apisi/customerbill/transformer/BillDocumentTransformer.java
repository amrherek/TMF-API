package com.orange.mea.apisi.customerbill.transformer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.orange.apibss.customerbill.model.Attachment;
import com.orange.bscs.model.BscsBillDocument;
import com.orange.mea.apisi.customerbill.rest.model.BillDocument;

@Component
public class BillDocumentTransformer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public BillDocument transform(BscsBillDocument billDocument) {
        BillDocument file = new BillDocument();
        Attachment billImage = new Attachment();
        billImage.setMimeType(transformMimeType(billDocument.getFormat()));
        billImage.setSize((float) billDocument.getDocument().length);
        billImage.setSizeUnit("bytes");
        file.setDocument(billImage);
        file.setData(billDocument.getDocument());
        return file;
    }

    private String transformMimeType(String format) {
        if ("pdf".equalsIgnoreCase(format)) {
            return MediaType.APPLICATION_PDF.toString();
        }
        if ("xml".equalsIgnoreCase(format)) {
            return MediaType.APPLICATION_XML_VALUE.toString();
        }
        if ("ps".equalsIgnoreCase(format)) {
            return "application/postscript";
        }
        if ("htm".equalsIgnoreCase(format)) {
            return MediaType.TEXT_HTML_VALUE.toString();
        }
        logger.warn("Unknown mimeType for format: " + format);
        return format;
    }



}
