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
package com.google.lantern.server.persistence.dao;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.lantern.shared.exceptions.unchecked.UserNotLoggedInException;


import com.google.lantern.server.persistence.entities.UserEntity;
import com.google.lantern.server.persistence.utils.ObjectifyUtils;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.DAOBase;

/**
 *
 * @author Arjun Satyapal
 */
public class UserDao extends DAOBase {
    static {
        ObjectifyService.register(UserEntity.class);
    }
    
    public static void addNewUser(final UserEntity userEntity) {
        Objectify ofy = ObjectifyUtils.getObjectifyService();
        ofy.put(userEntity);
//        return checkNotNull(userEntity.getId());
    }
    
    public static UserEntity getByUserId(final String userId) throws UserNotLoggedInException {
        Objectify ofy = ObjectifyUtils.getObjectifyService();
        UserEntity userEntity = ofy.query(UserEntity.class).filter("userId", userId).get();
        
        if (userEntity == null) {
            throw new UserNotLoggedInException("UserEntity not found.");
        }
        
        return userEntity;
    }
    
    public static void updateUser(final UserEntity updatedUserEntity) {
        checkNotNull(updatedUserEntity);
        Objectify ofy = ObjectifyUtils.getObjectifyService();
        ofy.put(updatedUserEntity);
    }
}
