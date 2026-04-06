package dev.hashnode.systemdesigner.behavioral.cor.loggingframework_1.logger;

import dev.hashnode.systemdesigner.behavioral.cor.loggingframework_1.Level;
import dev.hashnode.systemdesigner.behavioral.cor.loggingframework_1.LogRequest;

import java.util.Objects;

public abstract class Logger {
  protected abstract Level getMinimumLogLevel();
  public abstract Logger getNextLogger();
  public abstract void setNextLogger(Logger logger);
  public void write(LogRequest request){
    try {
      validateRequest(request);
      if( request.getLevel().getValue() >= getMinimumLogLevel().getValue()){
        System.out.println(this.getClass().getSimpleName() + ": LEVEL:" + request.getLevel() + " :" + request.getMessage() );
      }
    }
    catch (Exception e){
      System.out.println(e.getMessage());
    }
    finally {
      if(getNextLogger() != null){
        getNextLogger().write(request);
      }
    }
  }
  private void validateRequest(LogRequest request){
    Objects.requireNonNull(request);
    Objects.requireNonNull(request.getLevel());
    Objects.requireNonNull(getMinimumLogLevel());
    Objects.requireNonNull(getMinimumLogLevel());
  }
}
