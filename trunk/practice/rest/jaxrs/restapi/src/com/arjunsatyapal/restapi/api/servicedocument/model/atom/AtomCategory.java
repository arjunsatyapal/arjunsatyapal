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
package com.arjunsatyapal.restapi.api.servicedocument.model.atom;

import com.arjunsatyapal.restapi.api.servicedocument.model.AtomUri;

/**
 *
 * @author Arjun Satyapal
 */
public class AtomCategory {
    // TODO(arjuns) : missing atomCommonAttributes.

    // type text.        attribute term { text },

    private String term;
    
    private AtomUri scheme;
    
    private String label;
    
    // TODO(arjuns): missing undefinedContent
}
