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
package com.arjunsatyapal.restapi.api.servicedocument.model;

/**
 * Class for NameSpace.
 * 
 * @author Arjun Satyapal
 */
public class NameSpace {

    private String nameSpace;
    private String url;

    public NameSpace(String nameSpace, String url) {
        this.nameSpace = nameSpace;
        this.url = url;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public String getUrl() {
        return url;
    }

}
