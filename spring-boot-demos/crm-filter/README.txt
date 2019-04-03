spring boot默认使用logback做为日志框架。
如果你想使用其他日志系统，可以在application.properties使用org.springframework.boot.logging.LoggingSystem 来切换日志系统框架或者直接把value设置为none来禁止日志系统框架。
为什么使用的logback的日志文件命名是logback-spring.xml，而不是logback.xml，原因是，命名为logback-spring.xml的日志配置文件，spring boot可以为它添加一些spring boot特有的配置项。
首先第一个配置是springProfile节点，springProfile节点可以配置日志文件的任何节点位置，然后可以在application.properties配置文件中，通过spring.profiles来切换profile节点。
<springProfile name="staging">
    <!-- configuration to be enabled when the "staging" profile is active -->
</springProfile>

<springProfile name="dev, staging">
    <!-- configuration to be enabled when the "dev" or "staging" profiles are active -->
</springProfile>

<springProfile name="!production">
    <!-- configuration to be enabled when the "production" profile is not active -->
</springProfile>

第二个就是设置环境变量springProperty节点，这个类似于日志文件中的property节点，但提供了scope生命周期节点以及defaultValue默认值。
<springProperty scope="context" name="fluentHost" source="myapp.fluentd.host"
        defaultValue="localhost"/>
<appender name="FLUENT" class="ch.qos.logback.more.appenders.DataFluentAppender">
    <remoteHost>${fluentHost}</remoteHost>
    ...
</appender>