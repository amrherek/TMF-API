package com.orange.mea.apisi.security.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("common.security")
public class CommonSecurityProperties {
    /**
     * Indicate if the security is disabled
     */
    private boolean disabled = false;
    /**
     * Indicate if using H2 database (developpement)
     */
    private boolean useH2 = false;

    /**
     * @return the disabled
     */
    public boolean isDisabled() {
        return disabled;
    }

    /**
     * @param disabled
     *            the disabled to set
     */
    public void setDisabled(final boolean disabled) {
        this.disabled = disabled;
    }

    /**
     * @return the useH2
     */
    public boolean isUseH2() {
        return useH2;
    }

    /**
     * @param useH2
     *            the useH2 to set
     */
    public void setUseH2(final boolean useH2) {
        this.useH2 = useH2;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "CommonSecurityProperties [disabled=" + disabled + ", useH2=" + useH2 + "]";
    }

}
