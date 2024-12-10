package org.example.learn.spring.boot.log4j2.hello.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ExampleLoggingRunner implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(ExampleLoggingRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("log something");
    }
}
