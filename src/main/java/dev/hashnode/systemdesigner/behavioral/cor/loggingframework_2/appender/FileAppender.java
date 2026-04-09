package dev.hashnode.systemdesigner.behavioral.cor.loggingframework_2.appender;

import dev.hashnode.systemdesigner.behavioral.cor.loggingframework_2.Level;
import dev.hashnode.systemdesigner.behavioral.cor.loggingframework_2.formatter.FormattedMessage;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.Objects;

public class FileAppender extends Appender{
  private final File file;

  public FileAppender(File file, Level level) {
    super(level);
    this.file = file;
  }

  @Override
  protected void append(FormattedMessage message) {
    if(Objects.isNull(message) || StringUtils.isBlank(message.getMessage())) return;
    try {
      writeToFile(message.getMessage());
    }
    catch (IOException e) {
      System.out.println("Error while writing to file");
    }
  }

  private void writeToFile(String message) throws IOException {
    try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false))){
      bufferedWriter.append(message);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}
