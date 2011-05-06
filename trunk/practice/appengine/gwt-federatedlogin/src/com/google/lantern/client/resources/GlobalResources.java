/**
 * Copyright 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.lantern.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface GlobalResources extends ClientBundle {
  public static final GlobalResources RESOURCE = GWT.create(GlobalResources.class);

  @Source("com/google/lantern/client/resources/lantern_logo.png")
  ImageResource logo();

  @Source("com/google/lantern/client/resources/aol_logo.png")
  ImageResource aolLogo();

  @Source("com/google/lantern/client/resources/facebook_logo.jpg")
  ImageResource facebookLogo();

  @Source("com/google/lantern/client/resources/google_logo.png")
  ImageResource googleLogo();

  @Source("com/google/lantern/client/resources/myopenid_logo.png")
  ImageResource myOpenIdLogo();

  @Source("com/google/lantern/client/resources/twitter_logo.png")
  ImageResource twitterLogo();

  @Source("com/google/lantern/client/resources/yahoo_logo.png")
  ImageResource yahooLogo();
}
