package dev.hashnode.systemdesigner.behavioral.cor.loggingframework_1.logger;

import dev.hashnode.systemdesigner.behavioral.cor.loggingframework_1.Level;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class FileLogger extends Logger {
  private Level minimumLogLevel;
  private Logger nextLogger;
}
