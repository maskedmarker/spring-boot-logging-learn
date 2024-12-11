
## Configuration file
```text
Using a configuration file is the most popular and recommended approach for configuring Log4j Core. 
```
log4j2的配置不仅仅有常见的log4j2.xml,还有log4j2.properties/log4j2.json/log4j2.yaml

## xml文件

### case-insensitive
log4j2.xml文件中的tag名以及属性大小写不敏感.
log4j2-template.xml是从官网拷贝的写法,尽量与官网保持一致.


## logger-name
To omit the package name from the logger name in Log4j2, you can customize the logging pattern in your log4j2.xml configuration file.
You can use the %c{n} conversion pattern, where n specifies how many levels of the logger name to display.
If your logger is named com.example.MyClass, using %c{1} would result in just MyClass in the log output.

## PatternLayout
参考: https://logging.apache.org/log4j/2.x/manual/pattern-layout.html


##待处理
additivity(可累加性)

```text
The word "additivity" refers to the property of being additive, which means that something can be added or combined while maintaining a certain characteristic. 
In the context of logging (e.g., in Log4j2), additivity determines whether log messages from a specific logger are also passed to its parent logger.
```



