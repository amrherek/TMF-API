package com.orange.apibss.common.utils;

import java.io.ByteArrayOutputStream;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*
 * This simple SOAPHandler will output the contents of incoming
 * and outgoing messages.
 */
public class SOAPLoggingHandler implements SOAPHandler<SOAPMessageContext> {

    protected final static Logger logger = LoggerFactory.getLogger(SOAPLoggingHandler.class);

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleMessage(final SOAPMessageContext smc) {
        log(smc);
        return true;
    }

    @Override
    public boolean handleFault(final SOAPMessageContext smc) {
        log(smc);
        return true;
    }

    // nothing to clean up
    @Override
    public void close(final MessageContext messageContext) {
    }

    private void log(final SOAPMessageContext smc) {
        final String direction = (Boolean) smc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY) ? "REQUEST" : "RESPONSE";
        final String operation = "Operation: " + smc.get(MessageContext.WSDL_OPERATION);
        logger.debug(direction + " - " + operation);
        final SOAPMessage message = smc.getMessage();
        if (message == null) {
            logger.warn("SOAPMessage is NULL");
        } else {
            try {
                final ByteArrayOutputStream out = new ByteArrayOutputStream();
                message.writeTo(out);
                logger.debug('\n' + out.toString().replaceAll(" +", " ").replaceAll("\n", "") + '\n');
            } catch (final Exception e) {
                logger.debug("Exception in soap logging handler: ", e);
            }
        }
    }
}
