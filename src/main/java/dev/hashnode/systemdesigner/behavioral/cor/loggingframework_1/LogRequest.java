package dev.hashnode.systemdesigner.behavioral.cor.loggingframework_1;

import dev.hashnode.systemdesigner.behavioral.cor.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LogRequest implements Request {
  private Level level;
  private String message;
}
