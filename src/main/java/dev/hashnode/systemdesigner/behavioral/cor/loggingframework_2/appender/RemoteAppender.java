package dev.hashnode.systemdesigner.behavioral.cor.loggingframework_2.appender;

import dev.hashnode.systemdesigner.behavioral.cor.loggingframework_2.Level;
import dev.hashnode.systemdesigner.behavioral.cor.loggingframework_2.RemoteClient;
import dev.hashnode.systemdesigner.behavioral.cor.loggingframework_2.formatter.FormattedMessage;

public class RemoteAppender extends Appender {
  private RemoteClient remoteClient;
  protected RemoteAppender(Level minLevel) {
    super(minLevel);
  }

  @Override
  protected void append(FormattedMessage message) {
    try {
      remoteClient.postMessage(message.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
