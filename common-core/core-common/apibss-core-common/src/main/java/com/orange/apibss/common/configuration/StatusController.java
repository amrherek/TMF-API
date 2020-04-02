package com.orange.apibss.common.configuration;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.orange.apibss.common.model.Status;

@RestController
@RequestMapping("/status")
public class StatusController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${api.version}")
    private String version;

    @Value("${spring.application.name}")
    private String name;

    private Status status;

    @PostConstruct
    private void initStatus() {
        logger.info("API name:      " + name);
        logger.info("API version:   " + version);
        status = new Status();
        status.setName(name);
        status.setVersion(version);
        status.setStatus("ok");
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Status getStatus() {
        return status;
    }

}
