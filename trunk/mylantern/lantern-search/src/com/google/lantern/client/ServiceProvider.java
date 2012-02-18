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
package com.google.lantern.client;

import com.google.lantern.client.rpc.AdminService;

import com.google.lantern.client.rpc.AdminServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.lantern.client.rpc.CourseService;
import com.google.lantern.client.rpc.CourseServiceAsync;
import com.google.lantern.client.rpc.LoginService;
import com.google.lantern.client.rpc.LoginServiceAsync;
import com.google.lantern.client.rpc.DocumentService;
import com.google.lantern.client.rpc.DocumentServiceAsync;

/**
 *
 * @author Arjun Satyapal
 */
public class ServiceProvider {
    private final static CourseServiceAsync courseService = GWT.create(CourseService.class);
    public static CourseServiceAsync getCourseService() {
        return courseService;
    }

    private final static LoginServiceAsync loginService = GWT.create(LoginService.class);
    public static LoginServiceAsync getLoginService() {
        return loginService;
    }
    
    private final static DocumentServiceAsync documentService = GWT.create(DocumentService.class);
    public static DocumentServiceAsync getDocumentService() {
        return documentService;
    }
    
    private final static AdminServiceAsync adminService = GWT.create(AdminService.class);
    public static AdminServiceAsync getAdminService() {
        return adminService;
    }
}
