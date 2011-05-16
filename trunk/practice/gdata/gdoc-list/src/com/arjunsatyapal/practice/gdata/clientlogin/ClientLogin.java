package com.arjunsatyapal.practice.gdata.clientlogin;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientLogin {
  private static final String accountType = "HOSTED_OR_GOOGLE";
  private static final String source = "http-client-login";


  public static String getAuthToken() throws ClientProtocolException,
      IOException {
    System.out.println("Starting...");
    Console console = System.console();

    assert (console != null) : "Console is not present. Start this program from a command line window.";

    String email = "arjunsatyapal.appengine@gmail.com";
    char[] passwd;
    if ((passwd = console.readPassword("[%s]", "Password:")) == null) {
      System.out.println("Could not read password.");
      System.exit(1);
    }

    System.out.println("Select Service from Following Menu : ");
    for (GDataService currService : GDataService.values()) {
      System.out.println(currService.ordinal() + ". " + currService.name());
    }

     System.out.print("Select your Service : ");
     int ordinal = Integer.parseInt(console.readLine());
//    int ordinal = 0;
    assert (ordinal >= 0 && ordinal < GDataService.values().length) : ("Invalid choice " + ordinal);
    System.out.println("Chosen Service : " + ordinal);
    GDataService gDataService = GDataService.getGDataServiceByOrdinal(ordinal);

    HttpClient client = new DefaultHttpClient();
    HttpPost post = new HttpPost("https://www.google.com/accounts/ClientLogin");
    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
    nameValuePairs.add(new BasicNameValuePair("Email", email));
    nameValuePairs.add(new BasicNameValuePair("Passwd", new String(passwd)));
    nameValuePairs.add(new BasicNameValuePair("accountType", accountType));
    nameValuePairs.add(new BasicNameValuePair("source", source));
    nameValuePairs.add(new BasicNameValuePair("service", gDataService
        .getServiceId()));
    post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

    HttpResponse response = client.execute(post);
    StatusLine statusLine = response.getStatusLine();
    assert (statusLine.getStatusCode() == HttpStatus.SC_OK) : ("Failed Request with code : " + statusLine
        .getStatusCode() + " and  Reason : " + statusLine.getReasonPhrase());


    // Header[] headers = response.getAllHeaders();
    // for (Header currHeader : headers) {
    // System.out.println(currHeader);
    // }

    HttpEntity entity = response.getEntity();
    String entityString = EntityUtils.toString(entity);
    System.out.println(entityString);
    String[] responseParams = entityString.split("\n");

    System.out.println("Length = " + responseParams.length);

    String authString = null;
    for (String currString : responseParams) {
      if (currString.startsWith("Auth")) {
        authString = currString;
      }
    }

    assert (authString != null) : ("Auth token not found in " + EntityUtils.toString(entity));

    String authToken=(authString.split("="))[1];
    return authToken;
  }
}
