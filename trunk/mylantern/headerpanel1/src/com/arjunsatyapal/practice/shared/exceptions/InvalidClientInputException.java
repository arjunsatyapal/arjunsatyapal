package com.arjunsatyapal.practice.shared.exceptions;

import java.io.Serializable;

@SuppressWarnings("serial")
public class InvalidClientInputException extends RuntimeException implements Serializable {
  public InvalidClientInputException() {
  }

  public InvalidClientInputException(String message) {
    super(message);
  }

  public InvalidClientInputException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidClientInputException(Throwable cause) {
    super(cause);
  }
}
