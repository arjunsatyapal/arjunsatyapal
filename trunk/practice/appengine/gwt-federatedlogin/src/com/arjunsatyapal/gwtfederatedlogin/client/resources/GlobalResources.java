package com.arjunsatyapal.gwtfederatedlogin.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface GlobalResources extends ClientBundle {
  public static final GlobalResources RESOURCE = GWT.create(GlobalResources.class);

  @Source("com/arjunsatyapal/gwtfederatedlogin/client/resources/lantern_logo.png")
  ImageResource logo();

  @Source("com/arjunsatyapal/gwtfederatedlogin/client/resources/aol_logo.png")
  ImageResource aolLogo();

  @Source("com/arjunsatyapal/gwtfederatedlogin/client/resources/facebook_logo.jpg")
  ImageResource facebookLogo();

  @Source("com/arjunsatyapal/gwtfederatedlogin/client/resources/google_logo.png")
  ImageResource googleLogo();

  @Source("com/arjunsatyapal/gwtfederatedlogin/client/resources/myopenid_logo.png")
  ImageResource myOpenIdLogo();

  @Source("com/arjunsatyapal/gwtfederatedlogin/client/resources/twitter_logo.png")
  ImageResource twitterLogo();

  @Source("com/arjunsatyapal/gwtfederatedlogin/client/resources/yahoo_logo.png")
  ImageResource yahooLogo();
}
