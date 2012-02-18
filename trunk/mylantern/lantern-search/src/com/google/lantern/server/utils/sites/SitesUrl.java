/*
 * Copyright (c) 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.lantern.server.utils.sites;

import com.google.api.client.googleapis.GoogleUrl;
import com.google.api.client.util.Key;

/**
 * @author Arjun Satyapal
 */
public class SitesUrl extends GoogleUrl {

  private static final String ROOT_URL = "https://sites.google.com/feeds";
  
  public static final String getRootUrl() {
      return ROOT_URL;
  }

  @Key("max-results")
  public Integer maxResults;

  public SitesUrl(String url) {
    super(url);
  }
}
