Logging Framework version 2.0:

For log.info("some message") System need to decide
1. should this log got to console
2. should this log go to file
3. should this log got remote server

Components:
- LogEvent => has-> log message and level
- Log Queue => Queue<LogEvent> ( for Async requests)
- Appenders => Are-> ConsoleAppender, FileAppender ErrorAppender
- Appenders => Has-> Log Formatter, Min Log level, Next Appender
- Levels ⇒ Are→ Trace, Debug, Info, Warn, Error
- Formatter => formats the log message
- LogManager ⇒ Has→ LogQueue, listening log events,  chain creation and invocation responsibility
- Configurer => contains configurations of logger -> PATTERN, Chain Creation
- LogRunner => runs while true.
- Logger => invokes the log messages from anywhere
- 


