package dev.hashnode.systemdesigner.behavioral.cor.loggingframework_2.appender;

import dev.hashnode.systemdesigner.behavioral.cor.loggingframework_2.Level;
import dev.hashnode.systemdesigner.behavioral.cor.loggingframework_2.Log;
import dev.hashnode.systemdesigner.behavioral.cor.loggingframework_2.formatter.FormattedMessage;
import dev.hashnode.systemdesigner.behavioral.cor.loggingframework_2.formatter.Formatter;

public abstract class Appender {
  private Formatter formatter;
  protected final Level minLevel;
  protected Appender nextAppender;

  protected Appender(Level minLevel) {
    this.minLevel = minLevel;
  }

  public void setAppender(Appender appender){
    this.nextAppender = appender;
  }
  public void append(Log log){
    FormattedMessage formattedLog = formatter.format(log);
    append(log.getLevel(), formattedLog);
  }

  private void append(Level level, FormattedMessage formattedLog){
    try {
      if(level.getValue() >= minLevel.getValue()){
        append(formattedLog);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      if(nextAppender != null){
        nextAppender.append(level, formattedLog);
      }
    }
  }

  protected abstract void append(FormattedMessage message);
}
