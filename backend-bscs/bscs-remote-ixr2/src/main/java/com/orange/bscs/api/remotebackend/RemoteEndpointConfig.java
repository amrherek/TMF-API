package com.orange.bscs.api.remotebackend;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * Date : 20/07/2017.
 *
 * @author Denis Borscia (deyb6792)
 */
@Configuration
public class RemoteEndpointConfig {

    @Autowired
    private Bus bus;

    @Autowired
    private RemoteBackendImpl backend;

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, backend);
        endpoint.publish("/bscs");

        return endpoint;
    }
}
