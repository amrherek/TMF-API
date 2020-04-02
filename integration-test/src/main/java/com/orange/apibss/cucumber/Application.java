package com.orange.apibss.cucumber;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;

import com.orange.apibss.cucumber.config.ApibssProperties;
import com.orange.apibss.cucumber.config.BillingAccountProperties;
import com.orange.apibss.cucumber.config.BucketBalanceProperties;
import com.orange.apibss.cucumber.config.EligibilityProperties;
import com.orange.apibss.cucumber.config.PaymentProperties;
import com.orange.apibss.cucumber.config.ProductCatalogProperties;
import com.orange.apibss.cucumber.config.ProductOrderingProperties;

@ComponentScan
@EnableConfigurationProperties({ ApibssProperties.class, ProductOrderingProperties.class,
        ProductCatalogProperties.class, BucketBalanceProperties.class, BillingAccountProperties.class,
        EligibilityProperties.class, PaymentProperties.class })
@EnableAutoConfiguration
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    /**
     * Main method, used to run the application.
     */
    public static void main(final String[] args) throws UnknownHostException {
        final SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        final SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);
        final Environment env = app.run(args).getEnvironment();
        log.info("Access URLs:\n----------------------------------------------------------\n\t" +
                        "Local: \t\thttp://127.0.0.1:{}\n\t" +
                        "External: \thttp://{}:{}\n----------------------------------------------------------",
                env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"));

    }
}
