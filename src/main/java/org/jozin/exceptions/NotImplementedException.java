package org.jozin.exceptions;

public class NotImplementedException extends RuntimeException {
  public NotImplementedException() {
    super();
  }
  public NotImplementedException(String msg) {
    super(msg);
  }
  public NotImplementedException(Throwable cause) {
    super(cause);
  }
  public NotImplementedException(String msg, Throwable cause) {
    super(msg, cause);
  }
}
