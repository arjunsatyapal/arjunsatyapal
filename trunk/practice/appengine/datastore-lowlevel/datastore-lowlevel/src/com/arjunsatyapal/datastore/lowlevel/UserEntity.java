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
package com.arjunsatyapal.datastore.lowlevel;

import java.util.Map;

import com.google.appengine.api.datastore.Entity;

/**
 *
 * @author Arjun Satyapal
 */
public class UserEntity {
    private final String id;
    private final String email;
    
    public UserEntity(String id, String email) {
        this.id = id;
        this.email = email;
    }
    
    public Entity toDatastoreEntity() {
        Entity userEntity = new Entity("UserEntity", id);
        userEntity.setProperty("email", email);
        return userEntity;
    }
    
    public static UserEntity fromDatastoreEntity(Entity entity) {
        String id = (String) entity.getProperty("id");
        String email = (String) entity.getProperty("email");
        
        Map<String, Object> map = entity.getProperties();
        for (String currKey : map.keySet()) {
            System.out.println("Key = " + currKey + " value = " + map.get(currKey));
        }
        
        
        return new UserEntity(id,  email);
        
    }
}
