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
package com.google.lantern.server.utils.oauth2;

import com.google.lantern.server.utils.appengine.AppEngineUtils;

import com.google.lantern.shared.ServletPaths;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Arjun Satyapal
 */
public class OAuth2Utils {
    public static final String AUTH_URI = "https://accounts.google.com/o/oauth2/auth";
    public static final String TOKEN_URI = "https://accounts.google.com/o/oauth2/token";
    
    public static String getOAuth2CallbackUrl(HttpServletRequest req) {
        StringBuilder builder = new StringBuilder();
        
        builder.append("http://").append(req.getServerName());
        
        if (AppEngineUtils.isDevServer()) {
            builder.append(":").append(req.getServerPort());
        }
        
        builder.append(ServletPaths.JavaServlets.OAUTH_2_CALLBACK_SERVLET);
        
        return builder.toString();
    }
}
