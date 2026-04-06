package dev.hashnode.systemdesigner.behavioral.cor.loggingframework_2;

public class RemoteClient {
  // dummy client to send logging details
  public void postMessage(String message) {
    System.out.println("__RemoteClient__: " + message);
  }
}
