///*
// * Copyright (C) Google Inc.
// *
// * Licensed under the Apache License, Version 2.0 (the "License"); you may not
// * use this file except in compliance with the License. You may obtain a copy of
// * the License at
// *
// * http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
// * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
// * License for the specific language governing permissions and limitations under
// * the License.
// */
//package com.google.lantern.server.servlets;
//
///**
// * Servlet for rendering Home Page.
// * 
// * @author Arjun Satyapal
// */
//
//import com.google.lantern.server.utils.appengine.AppEngineUtils;
//import java.io.IOException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@SuppressWarnings("serial")
//public class LoginHandler extends AbstractOAuthHandler {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws IOException {
//        if (AppEngineUtils.isDevServer()) {
//            //TODO(arjuns) : fix this.
//            response.sendRedirect("http://127.0.0.1:8888/Light.html?gwt.codesvr=127.0.0.1:9997#author");
//        } else {
//            response.sendRedirect("/Light.html#author");
//        }
//    }
//}

/*
 * Copyright (C) Google Inc.
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
package com.google.lantern.server.servlets;

/**
 * Servlet for handling Login.
 * 
 * @author Arjun Satyapal
 */

import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAuthorizationRequestUrl;
import com.google.lantern.server.utils.oauth2.ResourceConsumerCredentials;
import com.google.lantern.server.utils.sites.SitesUrl;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class LoginHandler extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse response)
            throws IOException {
        // TODO(arjuns) : Add test for this.
        String authUrl = new GoogleAuthorizationRequestUrl(
                ResourceConsumerCredentials.getClientId(),
                ResourceConsumerCredentials.getRedirectUris(),
                SitesUrl.getRootUrl()).setAccessType("offline").setApprovalPrompt("force").build();
        response.sendRedirect(authUrl);
    }
}
