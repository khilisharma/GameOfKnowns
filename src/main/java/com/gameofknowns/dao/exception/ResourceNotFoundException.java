package com.gameofknowns.dao.exception;

public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException() {
    super();
  }

  public ResourceNotFoundException(String s) {
    super(s);
  }

  public ResourceNotFoundException(String s, Throwable throwable) {
    super(s, throwable);
  }

  public ResourceNotFoundException(Throwable throwable) {
    super(throwable);
  }
}
