package com.orange.bscs.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * SoiTransactionManager parameters
 * 
 * @author jwck2987
 *
 */
@ConfigurationProperties("statefull.connection")
public class BscsStatefullConnectionProperties {


    /**
     * Case of the stateful mode : timeout for a connection (in seconds)
     */
    private long timeout = 600;

    private final Control control = new Control();

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public Control getControl() {
        return control;
    }

    public static class Control {

        /**
         * Delay before the reservations control scheduler is to be executed (in
         * seconds)
         */
        private long delay = 180;

        /**
         * Time between successive reservations control executions (in seconds)
         */
        private long period = 60;

        public long getDelay() {
            return delay;
        }

        public void setDelay(long delay) {
            this.delay = delay;
        }

        public long getPeriod() {
            return period;
        }

        public void setPeriod(long period) {
            this.period = period;
        }

    }

}
