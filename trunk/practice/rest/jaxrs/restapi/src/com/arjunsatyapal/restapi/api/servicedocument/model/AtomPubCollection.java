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

import com.arjunsatyapal.restapi.api.servicedocument.model.atom.AppCategoriesInterface;

/**
 * This represents an AtomPub Collection.
 * 
 * @author Arjun Satyapal
 */
public class AtomPubCollection {
    // TODO(arjuns) : missing appCommonAttributes.

    
    // href is mandatory.
    // TODO(arjuns) : add validation for this.
    private AtomUri href;
    
    // Title is mandatory.
    // TODO(arjuns) : add validation.
    private AtomTitle title;
    
    // TODO(arjuns) : figure out if appAccpet makes sense. RFC : 2616.
    // Default expectation for clients is to treat as "application/atom+xml;type=entry"
    // may be we can use that. Null means non-postable,and missing means default value.
    
    private AppCategoriesInterface appCategory;
    
    //TODO(arjuns) : missing extensionSansTitleElement
}
