package org.example.learn.spring.boot.logging.priority.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class AppStartLogger implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(AppStartLogger.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.debug("app complete start at {}", Calendar.getInstance());
    }
}
