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

### logger-name
To omit the package name from the logger name in Log4j2, you can customize the logging pattern in your log4j2.xml configuration file. 
You can use the %c{n} conversion pattern, where n specifies how many levels of the logger name to display.
If your logger is named com.example.MyClass, using %c{1} would result in just MyClass in the log output.

### case-insensitive
log4j2.xml文件中的tag名以及属性大小写不敏感.
log4j2-template.xml是从官网拷贝的写法,尽量与官网保持一致.