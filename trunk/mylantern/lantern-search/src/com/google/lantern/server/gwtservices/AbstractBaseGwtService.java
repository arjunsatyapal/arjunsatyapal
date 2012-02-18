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

import com.google.gdata.util.common.base.Preconditions;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.lantern.server.persistence.entities.UserEntity;
import com.google.lantern.server.utils.oauth2.ResourceOwnerCredentials;

/**
 * 
 * @author Arjun Satyapal
 */
@SuppressWarnings("serial")
public class AbstractBaseGwtService extends RemoteServiceServlet {

    // TODO(arjuns) : Temporary hack for Junittests.
    protected AbstractBaseGwtService() {
        onBeforeRequestDeserialized(null);
    }
    
    @Override
    protected void onBeforeRequestDeserialized(String serializedRequest) {
        UserEntity userEntity = null;
        try {
            userEntity = ResourceOwnerCredentials.loadValidUserEntity();
        } catch (UserNotLoggedInException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Preconditions.checkNotNull(userEntity);
    }
}
