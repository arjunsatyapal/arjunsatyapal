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
package com.arjunsatyapal.rome.atompubimpl;

import com.sun.syndication.propono.atom.common.AtomService;
import com.sun.syndication.propono.atom.common.Workspace;

/**
 *
 * @author Arjun Satyapal
 */
public class CnxAtomService extends AtomService {
    public CnxAtomService() {
        /*
         *  Every API will get at max two workspaces.
         *  1. For AtomPub reado only links.
         *  2. If its an authenticated user, then AtomPub collection for that particular user. 
         */
        Workspace readOnly = new Workspace("ReadOnly", "text");
        getWorkspaces().add(readOnly);
    }

}
