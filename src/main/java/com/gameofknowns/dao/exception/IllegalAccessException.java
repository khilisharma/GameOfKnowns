package com.gameofknowns.dao.exception;

public class IllegalAccessException extends RuntimeException {

  public IllegalAccessException() {
    super();
  }

  public IllegalAccessException(String s) {
    super(s);
  }

  public IllegalAccessException(String s, Throwable throwable) {
    super(s, throwable);
  }

  public IllegalAccessException(Throwable throwable) {
    super(throwable);
  }
}
