package com.google.lantern.server.utils.sites;
/* Copyright (c) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */




/**
 * Exception to be thrown when there is an issue with the SitesHelper class.
 */
public class SitesException extends Exception {
    private static final long serialVersionUID = 8189203237976896087L;

public SitesException() {
    super();
  }

  public SitesException(String msg) {
    super(msg);
  }
}
