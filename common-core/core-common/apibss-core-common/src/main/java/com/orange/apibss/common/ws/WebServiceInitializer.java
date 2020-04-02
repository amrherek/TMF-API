package com.orange.apibss.common.ws;

import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WebServiceInitializer {

    @Value("${http.proxy.host:}")
    private String proxyHost;

    @Value("${http.proxy.port:}")
    private Integer proxyPort;

    public Object init(Class<?> serviceClass, String url, String login, String password, Logger logger) {
        final JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setServiceClass(serviceClass);
        factoryBean.setAddress(url);
        // log
        if (logger.isDebugEnabled()) {
            factoryBean.getInInterceptors().add(new LoggingInInterceptor());
            factoryBean.getOutInterceptors().add(new LoggingOutInterceptor());
        }

        Object jaxwsProxy = factoryBean.create();
        final Client client = ClientProxy.getClient(jaxwsProxy);

        // proxy
        if (!proxyHost.isEmpty()) {
            final HTTPConduit http = (HTTPConduit) client.getConduit();
            http.getClient().setProxyServer(proxyHost);
            http.getClient().setProxyServerPort(proxyPort);
            http.getClient().setNonProxyHosts("localhost");
        }

        // username token security
        if (login != null && !login.isEmpty()) {
            Endpoint endpoint = client.getEndpoint();
            Map<String, Object> outProps = new HashMap<String, Object>();
            outProps.put(WSHandlerConstants.MUST_UNDERSTAND, "false");
            outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
            outProps.put(WSHandlerConstants.USER, login);
            outProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
            WSPasswordHandler pwdHandler = new WSPasswordHandler();
            pwdHandler.setPassword(password);
            outProps.put(WSHandlerConstants.PW_CALLBACK_REF, pwdHandler);
            outProps.put(WSHandlerConstants.ADD_USERNAMETOKEN_NONCE, "true");
            outProps.put(WSHandlerConstants.ADD_USERNAMETOKEN_CREATED, "true");
            WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);
            endpoint.getOutInterceptors().add(wssOut);
        }

        return jaxwsProxy;

    }

}
