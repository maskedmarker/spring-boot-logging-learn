package org.example.learn.spring.boot.logging.hello;

import org.example.learn.spring.boot.logging.hello.init.EnvironmentPreparedListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 *
 * ApplicationListener可以通过spring.factories注册,
 * 也可以通过SpringApplicationBuilder注册
 */
@SpringBootApplication
public class HelloLoggingApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(HelloLoggingApplication.class)
                .listeners(new EnvironmentPreparedListener())
                .run(args);
    }

}