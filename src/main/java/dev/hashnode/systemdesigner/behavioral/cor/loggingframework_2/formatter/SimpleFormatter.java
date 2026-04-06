package dev.hashnode.systemdesigner.behavioral.cor.loggingframework_2.formatter;

import dev.hashnode.systemdesigner.behavioral.cor.loggingframework_2.Level;
import dev.hashnode.systemdesigner.behavioral.cor.loggingframework_2.Log;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;

public class SimpleFormatter implements Formatter{

  public FormattedMessage format(Log log) {
    String formatedText = String.format("%s [%s] %s %s %s", log.getTimestamp(), log.getThread(), log.getLevel(), log.getClass(), log.getMessage());
    if(log.getCause() != null) {
      formatedText = formatedText.concat("\n"+ Arrays.toString(log.getCause().getStackTrace()));
    }
    return new FormattedMessage(formatedText);
  }

}
