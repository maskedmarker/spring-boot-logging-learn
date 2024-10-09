package org.example.learn.spring.boot.logging.hello.util;

import org.springframework.core.env.Environment;

public class ConfigUtils {

    private static Environment environment;

    public static void setEnvironment(Environment environment) {
        environment = environment;
    }

    public static Object get(String key) {
        return environment.getProperty(key);
    }
}
