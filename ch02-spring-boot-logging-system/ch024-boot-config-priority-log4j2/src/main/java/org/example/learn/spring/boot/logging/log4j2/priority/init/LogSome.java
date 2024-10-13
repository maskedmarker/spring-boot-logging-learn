package org.example.learn.spring.boot.logging.log4j2.priority.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class LogSome implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(LogSome.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.debug("app complete start at {}", Calendar.getInstance());
        logger.info("hello world");
    }
}
