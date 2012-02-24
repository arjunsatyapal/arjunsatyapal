package com.google.multitenancy;

import com.googlecode.objectify.Objectify;

import com.googlecode.objectify.ObjectifyService;

import com.google.appengine.api.NamespaceManager;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class MultitenancyServlet extends HttpServlet {
  private static final String ns0 = "nss0";
  private static final String ns1 = "nss1";
  private static final String ns2 = "nss2";
  

  private static int counter = 1;
  
  synchronized
  private static int incCounter() {
    return counter++;
  }
  
  static {
    ObjectifyService.register(TestPojo.class);
  }
  
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    int testCounter = incCounter();
    
    StringBuilder builder = new StringBuilder("testCounter = " + testCounter);
    
    String localns = null;
    
    int rem = counter%3;
    if (rem == 0) {
      localns = ns0;
    } else if (rem == 1) {
      localns = ns1;
    } else if (rem == 2) {
      localns = ns2;
    }
    
    
    
    String currentNs = NamespaceManager.get();
    builder.append("\n before set = " + currentNs);
    
    NamespaceManager.set(localns);
    currentNs = NamespaceManager.get();
    builder.append("\n afterset = " + currentNs);

    TestPojo testPojo = new TestPojo(localns);
    Objectify ofy = ObjectifyService.begin();
    ofy.put(testPojo);
    
    builder.append("\n saved pojo = " + testPojo.toString());
    
    
//    int foo = 0;
//    
//    if (counter%2 == 0) {
//      builder.append("\n waiting for loop.");
//      long i = 0;
//      for (; i < Integer.MAX_VALUE * 2L ; i++) {
//        foo += i;
//      }
//      System.out.println("foo = " + foo);
//    }
    
//    
//    currentNs = NamespaceManager.get();
//    builder.append("\n after loop = " + currentNs);
    
    resp.getWriter().println(builder.toString());
  }
}
