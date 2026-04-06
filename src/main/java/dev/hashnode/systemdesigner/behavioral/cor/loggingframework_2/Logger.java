package dev.hashnode.systemdesigner.behavioral.cor.loggingframework_2;

import java.time.Instant;

public class Logger {
  private LoggerContext context;
  private LogManager logManager = LogManager.getLogManager();
  public void info(String message){
    logManager.push(new Log(Instant.now(), context.getThread(), Level.INFO, context.getClass(), message, null));
  }
  public void warn(String message){
    logManager.push(new Log(Instant.now(), context.getThread(), Level.WARN, context.getClass(), message, null));
  }
  public void error(String message,  Throwable e){
    logManager.push(new Log(Instant.now(),  context.getThread(), Level.ERROR, context.getClass(),  message, e));
  }
  public void fatal(String message,  Throwable e){
    logManager.push(new Log(Instant.now(), context.getThread(), Level.FATAL, context.getClass(), message, e));
  }
  public void debug(String message){
    logManager.push(new Log(Instant.now(), context.getThread(), Level.DEBUG, context.getClass(), message, null));
  }
  public void trace(String message){
    logManager.push(new Log(Instant.now(), context.getThread(), Level.TRACE, context.getClass(), message, null));
  }

}
