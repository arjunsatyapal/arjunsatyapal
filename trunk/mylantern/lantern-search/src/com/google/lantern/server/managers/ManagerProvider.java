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
package com.google.lantern.server.managers;

import com.google.lantern.shared.exceptions.unchecked.UserNotLoggedInException;

import com.google.lantern.server.exceptions.ServerSideException;
import com.google.lantern.server.exceptions.credential.CredentialCouldNotBeRefreshed;
import com.google.lantern.server.managers.sites.SitesManager;
import com.google.lantern.shared.enums.select.DocumentType;
import com.google.lantern.shared.objectifyobjects.DocumentObjectInterface;
import java.io.IOException;

/**
 * 
 * @author Arjun Satyapal
 */
public class ManagerProvider {
    private static SitesManager sitesManager;

    private static SitesManager getSitesManager() throws UserNotLoggedInException,
            CredentialCouldNotBeRefreshed, IOException {
        if (sitesManager == null) {
            sitesManager = new SitesManager();
        }

        return sitesManager;
    }

    @SuppressWarnings("unchecked")
    public static <E extends DocumentObjectInterface, T extends ManagerInterface<E>>  T getManager(DocumentType type) throws UserNotLoggedInException,
            IOException, ServerSideException {
        switch (type) {
            case GOOGLE_SITES:
                return (T) getSitesManager();

            default:
                throw new ServerSideException("Unknown type : " + type);
        }
    }

}
