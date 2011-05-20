package com.arjunsatyapal.practice.shared.exceptions;

import java.io.Serializable;

@SuppressWarnings("serial")
public class InvalidURLParamsException extends Exception implements Serializable {
  public InvalidURLParamsException() {
  }

  public InvalidURLParamsException(String message) {
    super(message);
  }

  public InvalidURLParamsException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidURLParamsException(Throwable cause) {
    super(cause);
  }
}
