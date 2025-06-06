# pattern layout
参考: https://logging.apache.org/log4j/2.x/manual/pattern-layout.html

```text
PatternLayout is a customizable, efficient, garbage-free, and human-readable string generating layout using a user-provided pattern. 
It is analogous to String#format() with specialized directives on injecting certain properties of a LogEvent.

"generating layout using a user-provided pattern"基于用户提供的pattern来生成日志的布局(布局指的是日志文件中每行日志展示的整体格式/结构)
```


## conversion pattern 
```text
Pattern Layout is primarily configured using a conversion pattern referring to(引用) certain properties of a LogEvent. 
A conversion pattern is composed of literal text and format control expressions called conversion specifiers.

前面的"a user-provided pattern"指的就是这里的"a conversion pattern".
"a conversion pattern is composed of literal text and format control expressions called conversion specifiers"
"a conversion pattern"不仅包含"literal text"字面文本(pattern写什么,日志中就输出什么,即字面值)还包含format control expressions格式化控制表达式(日志中输出的与pattern写的不一样,非字面值)

"a conversion pattern referring to certain properties of a LogEvent" 
"a conversion pattern"中的format control expressions表达式中不仅可以引用PatternConverter(通过%?的形式引用)还以引用LogEvent的属性(通常被当作PatternConverter的入参)
```

### conversion specifier
"format control expressions called conversion specifiers"
"Each conversion specifier starts with a % character, and is followed by optional format modifiers and a conversion character. "

```text
Any literal text, including \t, \n, \r\, and \f special characters, may be included in the conversion pattern. 
Use \\ to insert a single backslash into the output.

Each conversion specifier starts with a % character, and is followed by optional format modifiers and a conversion character. 
The conversion character specifies the type of data, e.g., category, priority, date, thread name. 
The format modifiers control such things as field width, padding, and left and right justification. 
Use %% to insert a single % into the output. Use %n to insert the line separator of the platform.

There is no explicit separator between text and conversion specifiers. 
The pattern parser knows when it has reached the end of a conversion specifier when it reads a conversion character. 
In the example above the conversion specifier %-5p means the priority of the log event should be left justified to a width of five characters.

If the pattern string does not contain a specifier to handle a Throwable being logged, parsing of the pattern will act as if the %xEx specifier had been added to the end of the string. 
To suppress the formatting of the Throwable completely simply add %ex{0} as a specifier in the pattern string.
```




## pattern converter
```text
The Pattern Layout conversion pattern is composed of literal text and format control expressions called conversion specifiers. 
Plugins implementing PatternConverter are admitted to the pattern converter registry of Pattern Layout, and used to resolve the conversion specifiers.
```


### 语法
Consider the following example for the syntax of Date pattern converter:
```text
语法:
%d{dateSpecifier}[{timezone}]

This means that
%d identifies the associated pattern converter
{dateSpecifier} indicates that the converter accepts a required dateSpecifier parameter
[{timezone}] indicates that the converter accepts an optional timezone parameter

%d表示某个PatternConverter的唯一标识,即conversion character
{dateSpecifier}是该PatternConverter的必传参数
[{timezone}]是该PatternConverter的非必传参数
```


### predefined set of pattern converters

#### Date
Outputs the date of the log event
```text
d{pattern}[{timezone}]
date{pattern}[{timezone}]
```

#### Encode
Encodes and escapes special characters suitable for output in specific markup languages
```text
enc{pattern}{[HTML|XML|JSON|CRLF]}
encode{pattern}{[HTML|XML|JSON|CRLF]}
```


#### Exception
Outputs the Throwable attached to the log event
```text
ex|exception|throwable
  { "none"
  | "full"
  | depth
  | "short"
  | "short.className"
  | "short.fileName"
  | "short.lineNumber"
  | "short.methodName"
  | "short.message"
  | "short.localizedMessage"
  }
  {filters(package,package,...)}
  {suffix(pattern)}
  {separator(separator)}

By default this will output the full trace as one would normally find with a call to Throwable#printStackTrace().
Exception converter is not garbage-free.
```

#### Line
Outputs the line number from where the log request was issued
```text
L
line
```

```text
Capturing the source location information to generate the line number of the caller is an expensive operation, and is not garbage-free. See this section of the layouts page for details.
```

#### Location
Outputs location information of the caller which generates the logging event
```text
l
location
```

```text
The location information depends on the JVM implementation, but it usually consists of the fully qualified name of the calling method followed by the callers' file name and line number.
```

#### Class
Outputs the fully qualified class name of the caller issuing the logging request
```text
C{precision}
class{precision}

大写的C
caller的qualified class name
```

#### File
Outputs the file name where the logging request was issued
```text
F
file
```

#### Logger
Outputs the name of the logger that published the log event
```text
c{precision}
logger{precision}

小写的c
logger的name,非caller的qualified class name
```

%C和%c是不同的,注意区别
```text
logger = LoggerFactory.getLogger(ExampleLoggingRunner.class)
logger = LoggerFactory.getLogger("example")
通常我们使用前者的形式定义logger,有时也通过后者的形式定义logger.
当使用前者形式时,%C和%c碰巧等价.注意是碰巧!!!!
建议使用%C,谁会关心logger的名字,更多是关心caller及其方法和行号

```

#####  precision specifier

```text
By default, the layout prints the logger name in full.
A logger conversion specifier can be optionally followed by a precision specifier, which consists of a decimal integer, or a pattern starting with a decimal integer.

When the precision specifier is an integer value, it reduces the size of the logger name. 
If the number is positive, the layout prints the corresponding number of the rightmost logger name components. 
If negative, the layout removes the corresponding number of leftmost logger name components.

If the precision contains periods(小数点) then 
    the number before the first period identifies the length to be printed from items that precede tokens in the rest of the pattern. 
If the number after the first period is followed by an asterisk 
    it indicates how many of the rightmost tokens will be printed in full.

If the precision contains any non-integer characters, then the layout abbreviates the name based on the pattern. 
If the precision integer is less than one, the layout still prints the right-most token in full.

精度说明符的解释:
整数值情况
    正数：当精度说明符是一个正整数时，布局会打印日志名称中相应数量的最右边的组件。
    负数：当精度说明符是一个负整数时，布局会移除日志名称中相应数量的最左边的组件。
包含小数点的情况
    小数点前的数字：如果精度包含小数点，那么小数点前的数字表示从模式中其余部分之前的项目中打印的长度。
    小数点后带星号的数字：如果小数点后的数字后面跟着一个星号，那么它表示将完整打印最右边的多少个标记。
非整数字符情况
    非整数字符：如果精度包含任何非整数字符，那么布局将根据模式缩写名称。
    小于1的整数：如果精度整数小于1，布局仍然会完整打印最右边的标记。
```

#### Message
Outputs the message associated with the log event
```text
m{lookups}{ansi}
msg{lookups}{ansi}
message{lookups}{ansi}
```


#### Method
Outputs the method name where the logging request was issued
```text
M
method
```



#### Sequence number
Includes a sequence number that will be incremented in every event
```text
sn
sequenceNumber
```


#### Thread context stack
```text
x
NDC

```

#### Thread context map
Outputs the Thread Context map (aka. Mapped Diagnostic Context or MDC) associated with the thread that generated the log event
```text
X{key[,key2...]}
mdc{key[,key2...]}
MDC{key[,key2...]}
```


#### Thread ID
Outputs the ID of the thread that generated the log event
```text
T
tid
threadId
```



#### thread name
Outputs the name of the thread that generated the log event
```text
t
tn
thread
threadName
```








