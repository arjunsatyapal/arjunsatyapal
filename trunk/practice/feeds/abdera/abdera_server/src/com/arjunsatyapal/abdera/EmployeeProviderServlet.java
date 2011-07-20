/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  The ASF licenses this file to You
 * under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.  For additional information regarding
 * copyright in this work, please see the NOTICE file in the top level
 * directory of this distribution.
 */
package com.arjunsatyapal.abdera;

import org.apache.abdera.protocol.server.Provider;
import org.apache.abdera.protocol.server.impl.DefaultProvider;
import org.apache.abdera.protocol.server.impl.SimpleWorkspaceInfo;
import org.apache.abdera.protocol.server.servlet.AbderaServlet;

// START SNIPPET: servlet
public class EmployeeProviderServlet extends AbderaServlet {
    protected Provider createProvider() {
        EmployeeCollectionAdapter ca = new EmployeeCollectionAdapter();
        ca.setHref("employee");

        SimpleWorkspaceInfo wi = new SimpleWorkspaceInfo();
        wi.setTitle("Employee Directory Workspace");
        wi.addCollection(ca);

        DefaultProvider provider = new DefaultProvider("/");
        provider.addWorkspace(wi);

        provider.init(getAbdera(), null);
        return provider;
    }
}
// END SNIPPET: servlet