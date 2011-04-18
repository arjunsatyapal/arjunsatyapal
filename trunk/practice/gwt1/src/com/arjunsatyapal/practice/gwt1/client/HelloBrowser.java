package com.arjunsatyapal.practice.gwt1.client;

import com.google.gwt.core.client.GWT;

public class HelloBrowser {

  public void salute() {
    HelloBrowserStdImpl testImpl = GWT
      .create(HelloBrowserStdImpl.class);
    testImpl.sayHello();
  }
}
