package com.orange.apibss.common.audit.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("audit.database")
public class DatabaseAuditProperties {

    /**
     * Indicate if the database audit is enabled
     */
    private boolean enabled = true;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
