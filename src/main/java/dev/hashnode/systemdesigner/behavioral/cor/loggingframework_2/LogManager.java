package dev.hashnode.systemdesigner.behavioral.cor.loggingframework_2;

import dev.hashnode.systemdesigner.behavioral.cor.loggingframework_2.appender.Appender;

import java.util.LinkedList;
import java.util.Queue;


// TODO - Need to Implement PRODUCER CONSUMER PATTERN here
public class LogManager {

  private Appender appender;
  private Queue<Log> logQueue = new LinkedList<Log>();

  public static LogManager getLogManager() {
    // TODO
    return null;
  }

  public void push(Log log){
    logQueue.add(log);
  }

}
