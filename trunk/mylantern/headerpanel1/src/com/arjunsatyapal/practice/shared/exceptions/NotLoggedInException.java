package com.arjunsatyapal.practice.shared.exceptions;

import java.io.Serializable;

/**
 * @author arjuns@google.com (Arjun Satyapal)
 */
@SuppressWarnings("serial")
public class NotLoggedInException extends Exception implements Serializable {
  public NotLoggedInException() {
  }

  public NotLoggedInException(String message) {
    super(message);
  }

  public NotLoggedInException(String message, Throwable cause) {
    super(message, cause);
  }

  public NotLoggedInException(Throwable cause) {
    super(cause);
  }
}
