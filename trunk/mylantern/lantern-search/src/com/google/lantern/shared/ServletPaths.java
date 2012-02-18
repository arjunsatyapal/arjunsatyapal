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
package com.google.lantern.shared;

/**
 * 
 * @author Arjun Satyapal
 */
public class ServletPaths {
    public static class GwtServices {
        public static final String COURSE_SERVICE_SERVLET = "courseService";

        public static final String SITES_SERVICE_SERVLET = "sitesService";

        public static final String LOGIN_SERVICE_SERVLET = "loginService";

        public static final String MYTEST_SERVICE_SERVLET = "myTestRpc";
        
        public static final String ADMIN_SERVICE_SERVLET = "admin/adminService";
    };

    // TODO(arjuns) : Think about naming.
    public static class JavaServlets {
        public static final String OAUTH_2_CALLBACK_SERVLET = "/oauth2Callback";

        public static final String OAUTH_2_DENIED_SERVLET = "/deniedAuth";

        public static final String LOGIN_SERVLET = "/login";

        public static final String HOME = "/";
        
        public static final String HOME_DEV_SERVER = "/Light.html?gwt.codesvr=127.0.0.1:9997";
    }

    public static class Resources {
        public static final String CLIENT_SECRETS_LOCAL_DEV_SERVER =
                "/dev_server/resource_consumer_credentials.dev_server.json";

        public static final String CLIENT_SECRETS_PROD =
                "/prod/resource_consumer_credentials.prod.json";

        public static final String HOME_PAGE = "/Light.html";
    }
}
