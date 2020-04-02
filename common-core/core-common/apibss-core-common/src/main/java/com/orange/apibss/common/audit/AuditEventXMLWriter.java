package com.orange.apibss.common.audit;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuditEventXMLWriter implements AuditEventWriter {

    /**
     * Static variable used to log execution informations of this class
     */
    protected final static Logger logger = LoggerFactory
            .getLogger(AuditEventXMLWriter.class);

    private Marshaller marshaller;

    public AuditEventXMLWriter() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(AuditEvent.class);
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch (JAXBException e) {
            logger.error("Problem writing audit event", e);
        }
    }

    @Override
    public void write(AuditEvent auditEvent) {
        StringWriter writer = new StringWriter();
        try {
            marshaller.marshal(auditEvent, writer);
            logger.info(writer.toString());
        } catch (JAXBException e) {
            logger.error("Problem writing audit event", e);
        }
    }

}
