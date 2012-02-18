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
package com.google.lantern.shared.objectifyobjects.courses;


import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Strings;

import com.google.common.base.Preconditions;

import com.google.gwt.user.client.rpc.IsSerializable;

import javax.persistence.Id;

/**
 *
 * @author Arjun Satyapal
 */
public class CourseEntity implements IsSerializable {
    @Id Long id;
    String title;
    String content;
    
    // Protected constructor for GWT.
    protected CourseEntity() {}
    
    // Use this constructor when you are creating a new course.
    public CourseEntity(String title) {
        this.title = title;
        this.content = null;
    }
    
    // Use this constructor when you are adding content to a course.
    public CourseEntity(Long id, String title, String content) {
        this.id = checkNotNull(id, "Id cannot be null.");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(title), "title cannot be null/empty.");
        this.title = title;
        
        this.content = checkNotNull(content, "Content cannot be null.");
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
