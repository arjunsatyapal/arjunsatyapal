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

import com.google.lantern.shared.exceptions.unchecked.UserNotLoggedInException;

import com.google.lantern.client.rpc.AdminService;
import com.google.lantern.server.managers.DocumentManager;
import com.google.lantern.server.utils.appengine.AppEngineUtils;
import com.google.lantern.shared.exceptions.checked.common.GwtServiceException;
import com.google.lantern.shared.exceptions.unchecked.AdminOnlyAccessException;

/**
 * 
 * @author Arjun Satyapal
 */
@SuppressWarnings("serial")
public class AdminServiceImpl extends AbstractBaseGwtService implements AdminService {

    @Override
    protected void onBeforeRequestDeserialized(String serializedRequest) {
        super.onBeforeRequestDeserialized(serializedRequest);
       
        AppEngineUtils.validateAdminAccessOnly();
    }

    public boolean deletePage(Long documentId) throws GwtServiceException, AdminOnlyAccessException { 
        DocumentManager.deleteDocument(documentId);
        
        return true;
    }

    public boolean deleteAll() throws GwtServiceException, UserNotLoggedInException {
        DocumentManager.deleteAll();
        AppEngineUtils.deleteAllKinds();
        
        throw new UserNotLoggedInException("Everything deleted.");
    }
}
