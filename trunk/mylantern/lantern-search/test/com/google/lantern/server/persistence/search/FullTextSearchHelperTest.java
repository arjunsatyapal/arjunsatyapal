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
package com.google.lantern.server.persistence.search;

import static com.google.lantern.server.persistence.search.FullTextSearchHelper.FILTER_DOMAIN;
import static com.google.lantern.server.persistence.search.FullTextSearchHelper.FILTER_GRADES;
import static com.google.lantern.server.persistence.search.FullTextSearchHelper.FILTER_US_STATES;
import static com.google.lantern.server.persistence.search.FullTextSearchHelper.queryBuilderFromSearchCriteria;
import static org.junit.Assert.assertEquals;

import com.google.lantern.shared.enums.select.HelperEnum;

import com.google.lantern.server.utils.sites.TestingUtils;
import com.google.lantern.shared.dto.SearchCriteria;
import com.google.lantern.shared.enums.select.Domain;
import com.google.lantern.shared.enums.select.Grades;
import com.google.lantern.shared.enums.select.UsStates;
import com.google.lantern.shared.objectifyobjects.SearchTags;
import org.junit.Test;

/**
 * Test for {@link FullTextSearchHelper}
 * 
 * @author Arjun Satyapal
 */
public class FullTextSearchHelperTest {
    /**
     * Test for {@link FullTextSearchHelper#queryBuilderFromSearchCriteria}
     */
    @Test
    public void testQueryBuilder_with_search_string() throws Exception {
        String randomSearchString = TestingUtils.getRandomString();
        
        SearchTags searchTags = new SearchTags();
        SearchCriteria onlySearchString = new SearchCriteria(randomSearchString, searchTags);
        assertEquals(randomSearchString, queryBuilderFromSearchCriteria(onlySearchString));
        
        searchTags.addDomain(Domain.Biology);
        searchTags.addUsState(UsStates.American_Samoa);
        searchTags.addGrade(Grades.Final_Year);
        
        String expected = new StringBuilder()
            .append(randomSearchString)
            .append(" AND ")
            .append(FILTER_DOMAIN).append(":\"Biology\" AND ")
            .append(FILTER_US_STATES).append(":\"American Samoa\" AND ")
            .append(FILTER_GRADES).append(":\"Final Year\"")
            .toString();
        assertEquals(expected, queryBuilderFromSearchCriteria(onlySearchString));
    }

    @Test
    public void testQueryBuilder_only_domains() throws Exception {
        doBuildQueryTest(TestingUtils.getRandomString(), FILTER_DOMAIN, Domain.Biology, Domain.Chemistry);
    }

    @Test
    public void testQueryBuilder_only_us_states() throws Exception {
        doBuildQueryTest(TestingUtils.getRandomString(), FILTER_US_STATES, UsStates.Alabama,
                UsStates.Northern_Marianas_Islands);
    }

    @Test
    public void testQueryBuilder_only_grades() throws Exception {
        doBuildQueryTest(TestingUtils.getRandomString(), FILTER_GRADES, Grades.Final_Year,
                Grades.GRADE_12);
    }

    private <T extends Enum<T>, E extends HelperEnum<T>> void doBuildQueryTest(String searchString,
            String filterName, E testValue1, E testValue2) {
        // First without searchString.
        SearchTags searchTags = new SearchTags().add(testValue1);
        SearchCriteria criteria = new SearchCriteria("", searchTags);
        String expected = filterName + ":\"" + testValue1.getPublicName() + "\"";
        assertEquals(expected, queryBuilderFromSearchCriteria(criteria));

        searchTags.add(testValue2);
        expected = expected + " AND " + filterName + ":\"" + testValue2.getPublicName() + "\"";
        assertEquals(expected, queryBuilderFromSearchCriteria(criteria));

        // Now testing with Search String.
        criteria.setSearchString(searchString);
        expected = searchString + " AND " + expected;
        assertEquals(expected, queryBuilderFromSearchCriteria(criteria));
    }
}
