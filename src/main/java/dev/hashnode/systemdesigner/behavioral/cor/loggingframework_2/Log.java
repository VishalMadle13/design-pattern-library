package dev.hashnode.systemdesigner.behavioral.cor.loggingframework_2;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class Log {
  Instant timestamp;
  String thread;
  Level level;
  Class<?> clazz;
  String message;
  Throwable cause;
}
