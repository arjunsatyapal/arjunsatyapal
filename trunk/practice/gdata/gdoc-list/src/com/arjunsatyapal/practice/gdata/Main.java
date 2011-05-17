package com.arjunsatyapal.practice.gdata;

import com.google.gdata.util.ServiceException;

import com.arjunsatyapal.practice.gdata.doclist.DocList;
import com.arjunsatyapal.practice.gdata.doclist.MyDocService;

import java.io.Console;
import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException, ServiceException {
    System.out.println("Starting...");
    Console console = System.console();

    assert (console != null) : "Console is not present. Start this program from a command line window.";

    String email = "arjunsatyapal.appengine@gmail.com";
    char[] password;
    if ((password = console.readPassword("[%s]", "Password:")) == null) {
      System.out.println("Could not read password.");
      System.exit(1);
    }

    MyDocService service = new MyDocService(
        "arjunsatyapal.appengine@gmail.com", new String(password));

    DocList x = new DocList();
    x.showAllDocs(service.getClient());
  }
}
