# 关于spring-boot-logging的学习



## 配置的优先级
日志的配置项的优先级遵顼spring-boot框架关于配置项的通用规则:

SpringBoot Externalized Configuration
Spring Boot uses a very particular PropertySource order that is designed to allow sensible overriding of values, properties are considered in the the following order:
1. Command line arguments.
2. Java System properties (System.getProperties()).
3. OS environment variables.
4. @PropertySource annotations on your @Configuration classes.
5. Application properties outside of your packaged jar (application.properties including YAML and profile variants).
6. Application properties packaged inside your jar (application.properties including YAML and profile variants).
7. Default properties (specified using SpringApplication.setDefaultProperties).

所以,在application.yml中的配置项优先于xml中的.



## 注意

