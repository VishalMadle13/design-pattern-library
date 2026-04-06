package dev.hashnode.systemdesigner.behavioral.cor.loggingframework_2.formatter;

import dev.hashnode.systemdesigner.behavioral.cor.loggingframework_2.Level;
import dev.hashnode.systemdesigner.behavioral.cor.loggingframework_2.Log;

public interface Formatter {
  FormattedMessage format(Log message);
}
