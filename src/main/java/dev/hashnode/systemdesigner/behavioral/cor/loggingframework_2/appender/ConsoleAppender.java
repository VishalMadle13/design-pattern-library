package dev.hashnode.systemdesigner.behavioral.cor.loggingframework_2.appender;

import dev.hashnode.systemdesigner.behavioral.cor.loggingframework_2.Level;
import dev.hashnode.systemdesigner.behavioral.cor.loggingframework_2.formatter.FormattedMessage;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class ConsoleAppender extends Appender{
  public ConsoleAppender(Level minLevel) {
    super(minLevel);
  }

  @Override
  protected void append(FormattedMessage message) {
    if(Objects.isNull(message) || StringUtils.isBlank(message.getMessage())) return;
    System.out.println(message.getMessage());
  }
}
