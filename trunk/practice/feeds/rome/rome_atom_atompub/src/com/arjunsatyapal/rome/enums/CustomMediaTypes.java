/*
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
package com.arjunsatyapal.rome.enums;

import javax.ws.rs.core.MediaType;

/**
 *
 * @author Arjun Satyapal
 */
public class CustomMediaTypes extends MediaType {
    /** "application/atomsvc+xml" */
    public final static String APPLICATION_ATOMSVC_XML = "application/atomsvc+xml";
    /** "application/atomsvc+xml" */
    public final static MediaType APPLICATION_ATOMSVC_XML_TYPE = new MediaType("application","atomsvc+xml");
}
