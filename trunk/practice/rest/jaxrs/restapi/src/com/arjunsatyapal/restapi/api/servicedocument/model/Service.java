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

import java.util.List;

/**
 * app:service Element.
 * 
 * @author Arjun Satyapal
 */
public class Service {
    private final NameSpace nameSpace = new NameSpace("", "http://www.w3.org/2007/app");
    private final NameSpace atomNameSpace = new NameSpace("atom", "http://www.w3.org/2005/Atom");
    
    // TODO(arjuns) : missing appCommonAttributes
    
    // There should be atleast one workspace.
    // TODO(arjuns) : add validation.
    private List<AtomPubWorkspace> listOfWorkSpaces;
    
    // TODO(arjuns) : missing extensionSansTitleElement.
}
