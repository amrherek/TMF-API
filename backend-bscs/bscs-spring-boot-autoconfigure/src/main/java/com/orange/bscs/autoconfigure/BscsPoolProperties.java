package com.orange.bscs.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * SoiConnectionPool parameters
 * 
 * @author jwck2987
 *
 */
@ConfigurationProperties("pool")
public class BscsPoolProperties {

    /**
     * Timeout after which an idle connection must be destroyed (in minutes)
     */
    private long timeout = 10;

    /**
     * Time between eviction thread runs (in minutes)
     */
    private long timeBetweenEvictionThreadRuns = 1;

    /**
     * Timeout waiting for new connection from backend (in seconds)
     */
    private int maxWait = 30;

    /**
     * Number of SOIConnexion at pool initialization
     */
    private int initialSize = 5;

    /**
     * Maximum number of active connections at a moment. <br />
     * Beyond this limit, each new connection creation request will be rejected
     */
    private int size = 20;

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public long getTimeBetweenEvictionThreadRuns() {
        return timeBetweenEvictionThreadRuns;
    }

    public void setTimeBetweenEvictionThreadRuns(long timeBetweenEvictionThreadRuns) {
        this.timeBetweenEvictionThreadRuns = timeBetweenEvictionThreadRuns;
    }

    public int getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }

    public int getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(int initialSize) {
        this.initialSize = initialSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


}
