package com.orange.bscs.api.connection.builders.remote;

import com.orange.bscs.svlbackend.SVLBackendPortType;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Date : 09/08/2017.
 *
 * @author Denis Borscia (deyb6792)
 */
@Configuration
public class RemoteConfiguration {

    @Value("${remoteBackendFactory.address}")
    private String address;

    @Value("${vcap.services.my-intranet-access.credentials.uri:}")
    private String proxyUri;

    @Bean
    public SVLBackendPortType remoteBackend() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(SVLBackendPortType.class);
        jaxWsProxyFactoryBean.setAddress(address);
        return (SVLBackendPortType) jaxWsProxyFactoryBean.create();
    }

    @Bean
    public Client client() {
        Client client = ClientProxy.getClient(remoteBackend());
        HTTPConduit http = (HTTPConduit) client.getConduit();
        if (null != proxyUri && !proxyUri.isEmpty()) {
            int pos = proxyUri.lastIndexOf(':');
            int pos2 = proxyUri.indexOf("://");
            http.getClient().setProxyServer(proxyUri.substring(pos2 + 3, pos));
            http.getClient().setProxyServerPort(Integer.parseInt(proxyUri.substring(pos + 1)));
        }
        return client;
    }
}
