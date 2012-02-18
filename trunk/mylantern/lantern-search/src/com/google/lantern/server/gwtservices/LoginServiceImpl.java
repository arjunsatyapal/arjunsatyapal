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

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.lantern.client.rpc.LoginService;
import com.google.lantern.server.persistence.dao.UserDao;
import com.google.lantern.server.persistence.entities.UserEntity;
import com.google.lantern.server.utils.appengine.AppEngineUtils;
import com.google.lantern.shared.objectifyobjects.LoggedInUser;

/**
 *
 * @author Arjun Satyapal
 */
@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

    public LoggedInUser getLoggedInUser() throws UserNotLoggedInException {
        UserEntity userEntity = UserDao.getByUserId(AppEngineUtils.getUserId());

        return new LoggedInUser(userEntity.getEmail());
    }
}
