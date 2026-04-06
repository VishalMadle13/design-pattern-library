package dev.hashnode.systemdesigner.behavioral.cor.loggingframework_2;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoggerContext {
  private String thread;
  private Class<?> clazz;
}
