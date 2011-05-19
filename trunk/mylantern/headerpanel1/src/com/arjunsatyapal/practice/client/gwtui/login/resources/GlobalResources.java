package com.arjunsatyapal.practice.client.gwtui.login.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface GlobalResources extends ClientBundle {
  public static final GlobalResources RESOURCE = GWT.create(GlobalResources.class);

  @Source("com/arjunsatyapal/practice/client/gwtui/login/resources/aol_logo.png")
  ImageResource aolLogo();

  @Source("com/arjunsatyapal/practice/client/gwtui/login/resources/facebook_logo.jpg")
  ImageResource facebookLogo();

  @Source("com/arjunsatyapal/practice/client/gwtui/login/resources/google_logo.png")
  ImageResource googleLogo();

  @Source("com/arjunsatyapal/practice/client/gwtui/login/resources/myopenid_logo.png")
  ImageResource myOpenIdLogo();

  @Source("com/arjunsatyapal/practice/client/gwtui/login/resources/twitter_logo.png")
  ImageResource twitterLogo();

  @Source("com/arjunsatyapal/practice/client/gwtui/login/resources/yahoo_logo.png")
  ImageResource yahooLogo();
}
