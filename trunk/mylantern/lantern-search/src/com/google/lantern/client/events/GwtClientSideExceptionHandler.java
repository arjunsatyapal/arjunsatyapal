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
package com.google.lantern.client.events;

import com.google.lantern.shared.exceptions.unchecked.UserNotLoggedInException;

import com.google.lantern.shared.exceptions.checked.client.sites.SitesWebPageAlreadyExistsException;

import com.google.lantern.shared.exceptions.checked.client.searchtagexception.AllSearchTagRequired;

import com.google.gwt.user.client.Window;
import com.google.lantern.shared.ServletPaths;


/**
 *
 * @author Arjun Satyapal
 */
public class GwtClientSideExceptionHandler {
    public static void handleException(Throwable caught) {
        if (caught instanceof UserNotLoggedInException) {
            handleUserNotLoggedIn();
        } else if (caught instanceof SitesWebPageAlreadyExistsException) {
            handleDuplicateDocument();
        } else if (caught instanceof AllSearchTagRequired) {
            handleSearchTagsNotSet();
        } else {
            Window.alert("Failed due to : " + caught.getLocalizedMessage());
        }
    }

    private static void handleDuplicateDocument() {
        Window.alert("Document with exact path already exists. Try again.");
    }

    private static void handleUserNotLoggedIn() {
        Window.Location.assign(ServletPaths.JavaServlets.LOGIN_SERVLET);
    }
    
    private static void handleSearchTagsNotSet() {
        Window.alert("All search tags need to be set.");
    }
}
