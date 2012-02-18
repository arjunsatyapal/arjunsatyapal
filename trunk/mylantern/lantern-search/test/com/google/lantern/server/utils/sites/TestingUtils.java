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
package com.google.lantern.server.utils.sites;

import com.google.lantern.shared.exceptions.checked.client.searchtagexception.SearchTagException;

import com.google.common.collect.Lists;
import com.google.lantern.shared.enums.select.Domain;
import com.google.lantern.shared.enums.select.Grades;
import com.google.lantern.shared.enums.select.UsStates;
import com.google.lantern.shared.objectifyobjects.SearchTags;
import java.util.List;
import java.util.UUID;

/**
 * 
 * @author Arjun Satyapal
 */
public class TestingUtils {
    public static SearchTags getDefaultSearchTags() throws SearchTagException {
        List<Domain> domains = Lists.newArrayList(Domain.Biology, Domain.Chemistry);
        List<UsStates> usStates = Lists.newArrayList(UsStates.Alabama, UsStates.Alaska);
        List<Grades> grades =
                Lists.newArrayList(Grades.GRADE_1, Grades.First_Year);

        return new SearchTags(SearchTags.SearchTagProfile.ALL_TAGS_MANDATORY, domains, usStates,
                grades);
    }

    public static UUID getUUID() {
        return UUID.randomUUID();
    }
    
    public static String getRandomString() {
        return getUUID().toString();
    }
    
    public static String getHtmlWrapper(String text) {
        return "<html><body>" + text + "</body></html>"; 
    }
}
