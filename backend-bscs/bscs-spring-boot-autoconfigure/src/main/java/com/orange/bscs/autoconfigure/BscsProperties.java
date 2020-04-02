package com.orange.bscs.autoconfigure;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("bscs")
public class BscsProperties {

    /**
     * if true : use SOIConnectionPool else use SOIConnectionFactory
     */
    @NotNull
    private boolean useConnectionPool = true;

    /**
     * Bscs provider
     */
    private BscsProviderEnum provider = BscsProviderEnum.CORBA;

    public BscsProviderEnum getProvider() {
        return provider;
    }

    public void setProvider(BscsProviderEnum provider) {
        this.provider = provider;
    }

    public boolean getUseConnectionPool() {
        return useConnectionPool;
    }

    public void setUseConnectionPool(boolean useConnectionPool) {
        this.useConnectionPool = useConnectionPool;
    }

}
