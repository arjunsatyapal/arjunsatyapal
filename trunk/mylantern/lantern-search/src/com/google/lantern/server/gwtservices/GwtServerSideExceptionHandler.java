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
package com.google.lantern.server.gwtservices;

import static com.google.common.base.Throwables.getStackTraceAsString;

import com.google.lantern.shared.exceptions.checked.client.sites.SitesWebPageAlreadyExistsException;

import com.google.lantern.shared.exceptions.checked.common.GwtServerSideException;


import com.google.common.base.Throwables;
import com.google.gdata.util.VersionConflictException;

/**
 * 
 * @author Arjun Satyapal
 */
public class GwtServerSideExceptionHandler {
    //TODO(arjuns) : Add logging for exceptions.
    public static void handleVersionConflictException(String title, VersionConflictException e)
            throws SitesWebPageAlreadyExistsException {
        throw new SitesWebPageAlreadyExistsException(title, Throwables.getStackTraceAsString(e));
    }

    public static void handleGwtServerSideException(Exception e) throws GwtServerSideException {
        throw new GwtServerSideException("Server Error", getStackTraceAsString(e));
    }
}
