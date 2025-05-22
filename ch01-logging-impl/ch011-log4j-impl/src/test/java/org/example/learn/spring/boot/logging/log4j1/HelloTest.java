package org.example.learn.spring.boot.logging.log4j1;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

public class HelloTest {

    @Test
    public void test0() {
        Logger logger = Logger.getLogger(HelloTest.class);

        // 记录不同级别的日志
        logger.debug("This is a debug message.");
        logger.info("This is an info message.");
        logger.warn("This is a warn message.");
        logger.error("This is an error message.");
        logger.fatal("This is a fatal message.");

        // 模拟一个异常
        try {
            int result = 10 / 0;
        } catch (Exception e) {
            logger.error("An exception occurred", e);
        }
    }
}
