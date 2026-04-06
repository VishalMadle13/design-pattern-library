package dev.hashnode.systemdesigner.behavioral.cor.loggingframework_1;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LogRequest{
  private Level level;
  private String message;
}
