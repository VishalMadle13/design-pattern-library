package dev.hashnode.systemdesigner.behavioral.cor.loggingframework_2;

import lombok.Getter;

@Getter
public enum Level {
  TRACE(1),
  DEBUG(2),
  INFO(3),
  WARN(4),
  ERROR(5),
  FATAL(6);

  private final int value;

  Level(int value) {
    this.value = value;
  }
}
