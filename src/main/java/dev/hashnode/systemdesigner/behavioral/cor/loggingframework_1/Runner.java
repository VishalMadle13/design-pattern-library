package dev.hashnode.systemdesigner.behavioral.cor.loggingframework_1;

import dev.hashnode.systemdesigner.behavioral.cor.loggingframework_1.logger.ConsoleLogger;
import dev.hashnode.systemdesigner.behavioral.cor.loggingframework_1.logger.FileLogger;
import dev.hashnode.systemdesigner.behavioral.cor.loggingframework_1.logger.RemoteServerLogger;

import java.util.ArrayList;
import java.util.List;

public class Runner implements Runnable {

  @Override
  public void run() {
    FileLogger fileLogger = new FileLogger();
    RemoteServerLogger remoteServerLogger = new RemoteServerLogger();
    ConsoleLogger console = new ConsoleLogger();

    remoteServerLogger.setMinimumLogLevel(Level.INFO);
    fileLogger.setMinimumLogLevel(Level.DEBUG);
    console.setMinimumLogLevel(Level.INFO);

    console.setNextLogger(fileLogger);
    fileLogger.setNextLogger(remoteServerLogger);

    List<LogRequest> dummyRequests = new ArrayList<LogRequest>();
    dummyRequests.add(new LogRequest(Level.INFO, "INFO MESSAGE"));
    dummyRequests.add(new LogRequest(Level.TRACE, "TRACE MESSAGE"));
    dummyRequests.add(new LogRequest(Level.DEBUG, "DEBUG MESSAGE"));
    dummyRequests.add(new LogRequest(Level.ERROR, "ERROR MESSAGE"));
    dummyRequests.add(new LogRequest(Level.FATAL, "FATAL MESSAGE"));
    dummyRequests.add(new LogRequest(Level.WARN, "WARN MESSAGE"));

    dummyRequests.forEach(console::write);


  }
}
