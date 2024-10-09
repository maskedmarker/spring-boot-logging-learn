package org.example.learn.spring.boot.logging.hello.init;

import org.example.learn.spring.boot.logging.hello.util.ConfigUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;

public class EnvironmentPreparedListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(EnvironmentPreparedListener.class);

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        logger.info("在spring-boot准备好environment后,立即赋值到ConfigUtils");
        ConfigUtils.setEnvironment(event.getEnvironment());
    }
}
