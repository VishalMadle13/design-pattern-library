Logging Framework version 1.0:

For log.info("some message") System need to decide
1. should this log got to console
2. should this log go to file
3. should this log got remote server

Components:
- Loggers => ConsoleLogger, FileLogger, ErrorLogger
- Levels => Trace, Debug, Info, Warn, Error
- Loggers has field for deciding acceptance of min level logging.
- Logger( minAcceptableLevel, nextLogger) is handler
