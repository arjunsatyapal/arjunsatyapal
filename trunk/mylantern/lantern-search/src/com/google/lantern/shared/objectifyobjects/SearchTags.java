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
package com.google.lantern.shared.objectifyobjects;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.lantern.shared.exceptions.checked.client.searchtagexception.AllSearchTagRequired;
import com.google.lantern.shared.exceptions.checked.client.searchtagexception.AtleastOneSearchTagRequiredException;
import com.google.lantern.shared.exceptions.checked.client.searchtagexception.InvalidSearchTagException;
import com.google.lantern.shared.exceptions.checked.client.searchtagexception.SearchTagException;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.lantern.shared.enums.select.Domain;
import com.google.lantern.shared.enums.select.Grades;
import com.google.lantern.shared.enums.select.HelperEnum;
import com.google.lantern.shared.enums.select.UsStates;
import java.util.List;
import javax.persistence.Id;

/**
 * 
 * @author Arjun Satyapal
 */
public class SearchTags implements IsSerializable {
    @Id
    Long Id;

    SearchTagProfile profile;
    List<Domain> domains;
    List<UsStates> usStates;
    List<Grades> grades;
    
    public String toString() {
        StringBuilder builder = new StringBuilder("SearchTags = [");
        
        if (hasDomains()) {
            builder.append("domains[").append(Iterables.toString(domains)).append("], ");
        }
        
        if (hasUsStates()) {
            builder.append("usStates[").append(Iterables.toString(usStates)).append("], ");
        }
        
        if (hasGrades()) {
            builder.append("grades[").append(Iterables.toString(grades)).append("], ");
        }
        
        return builder.toString();
    }

    public SearchTags() {
        profile = SearchTagProfile.NO_TAG_MANDATORY;
        domains = Lists.newArrayList();
        usStates = Lists.newArrayList();
        grades = Lists.newArrayList();
    }

    public SearchTags(SearchTagProfile profile, List<Domain> domains, List<UsStates> usStates,
            List<Grades> grades) throws SearchTagException {
        this.profile = checkNotNull(profile);
        this.domains = checkNotNull(domains);
        this.usStates = checkNotNull(usStates);
        this.grades = checkNotNull(grades);
        validate();
    }

    public List<Domain> getDomains() {
        return domains;
    }

    public SearchTags setDomains(List<Domain> domains) {
        this.domains = domains;
        return this;
    }

    public boolean hasDomains() {
        return domains.size() > 0;
    }

    public List<UsStates> getUsStates() {
        return usStates;
    }

    public SearchTags setUsStates(List<UsStates> usStates) {
        this.usStates = usStates;
        return this;
    }

    public boolean hasUsStates() {
        return usStates.size() > 0;
    }

    public List<Grades> getGrades() {
        return grades;
    }

    public SearchTags setGrades(List<Grades> grades) {
        this.grades = grades;
        return this;
    }

    public boolean hasGrades() {
        return grades.size() > 0;
    }

    public void validate() throws AllSearchTagRequired, AtleastOneSearchTagRequiredException,
            InvalidSearchTagException {
        switch (profile) {
            case NO_TAG_MANDATORY :
                return;
                
            case ALL_TAGS_MANDATORY:
                validateAllTagsRequired();
                break;

            case AT_LEAST_ONE_TAG_MANDATORY:
                validateAtLeastOneTagRequired();
                break;

            default:
                throw new InvalidSearchTagException("Invalid profile type : " + profile);
        }
    }

    public void validateAllTagsRequired() throws AllSearchTagRequired {
        if (domains.size() == 0 || usStates.size() == 0 || grades.size() == 0) {
            throw new AllSearchTagRequired("All search tags are mandatory.");
        }
    }

    public void validateAtLeastOneTagRequired() throws AtleastOneSearchTagRequiredException {
        if (domains.size() == 0 && usStates.size() == 0 && grades.size() == 0) {
            throw new AtleastOneSearchTagRequiredException(
                    "At least one search tag must be set.");
        }
    }

    public static enum SearchTagProfile {
        ALL_TAGS_MANDATORY,
        AT_LEAST_ONE_TAG_MANDATORY,
        NO_TAG_MANDATORY;
    }

    public SearchTags addDomain(Domain domain) {
        domains.add(domain);
        return this;
    }

    public SearchTags addUsState(UsStates usState) {
        usStates.add(usState);
        return this;
    }

    public SearchTags addGrade(Grades grade) {
        grades.add(grade);
        return this;
    }

    public boolean hasTags() {
        return hasDomains() || hasUsStates() || hasGrades();
    }

    public <T extends Enum<T>, E extends HelperEnum<T>> SearchTags add(E value) {
        if (value instanceof Domain) {
            addDomain((Domain) value);
        } else if (value instanceof UsStates) {
            addUsState((UsStates) value);
        } else if (value instanceof Grades) {
            addGrade((Grades) value);
        } else {
            throw new IllegalArgumentException("Unknown type [" + value.getClass()
                    + "] with value [" + value + "].");
        }

        return this;
    }

}
