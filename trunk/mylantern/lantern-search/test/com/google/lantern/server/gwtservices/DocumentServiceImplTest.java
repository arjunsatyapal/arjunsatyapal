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

import static com.google.lantern.server.utils.sites.TestingUtils.getDefaultSearchTags;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.google.appengine.api.utils.SystemProperty;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalUserServiceTestConfig;
import com.google.common.collect.Lists;
import com.google.lantern.client.rpc.AdminService;
import com.google.lantern.client.rpc.DocumentService;
import com.google.lantern.server.utils.appengine.AppEngineUtils;
import com.google.lantern.server.utils.sites.TestingUtils;
import com.google.lantern.shared.dto.SearchCriteria;
import com.google.lantern.shared.enums.select.DocumentType;
import com.google.lantern.shared.enums.select.Domain;
import com.google.lantern.shared.enums.select.Grades;
import com.google.lantern.shared.enums.select.HelperEnum;
import com.google.lantern.shared.enums.select.UsStates;
import com.google.lantern.shared.exceptions.checked.client.document.DocumentNotFoundException;
import com.google.lantern.shared.exceptions.checked.client.sites.SitesWebPageAlreadyExistsException;
import com.google.lantern.shared.exceptions.checked.common.GwtServiceException;
import com.google.lantern.shared.objectifyobjects.SearchTags;
import com.google.lantern.shared.objectifyobjects.SearchTags.SearchTagProfile;
import com.google.lantern.shared.objectifyobjects.documents.DocumentEntity;
import com.google.lantern.shared.objectifyobjects.documents.DocumentSearchResultWrapper;
import com.google.lantern.shared.objectifyobjects.documents.DocumentSearchResultWrapper.CursorType;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for {@link DocumentServiceImpl}
 * 
 * @author Arjun Satyapal
 */
public class DocumentServiceImplTest {
    private DocumentService documentService;
    private AdminService adminService;

    private static final LocalServiceTestHelper helper =
            new LocalServiceTestHelper(
                    new LocalDatastoreServiceTestConfig(),
                    new LocalUserServiceTestConfig())
                    .setEnvIsLoggedIn(true)
                    .setEnvEmail("arjuns@google.com")
                    .setEnvAuthDomain("google.com");

    static {
        helper.setUp();
    }

    @Before
    public void setUp() throws Exception {
        SystemProperty.environment.set(SystemProperty.Environment.Value.Development);

        assertTrue(AppEngineUtils.isDevServer());
        assertTrue(AppEngineUtils.isJunitTesting());
        documentService = new DocumentServiceImpl();

    }

    @After
    public void tearDown() {
        // TODO(arjuns): Move this to end of test suite.
        // helper.tearDown();
    }

    @Test
    public void test_searchDocument() throws Exception {
        String title = "testGetDocumentsBySearchTags " + System.currentTimeMillis();

        String randomText = TestingUtils.getRandomString();

        List<Domain> listOfDomains =
                Lists.newArrayList(Domain.Physics, Domain.Chemistry, Domain.Other);
        List<UsStates> listOfUsStates =
                Lists.newArrayList(UsStates.Puerto_Rico, UsStates.California,
                        UsStates.Northern_Marianas_Islands);
        List<Grades> listOfGrades =
                Lists.newArrayList(Grades.First_Year, Grades.GRADE_6, Grades.GRADE_12);

        SearchTags searchTags =
                new SearchTags(SearchTags.SearchTagProfile.ALL_TAGS_MANDATORY, listOfDomains,
                        listOfUsStates, listOfGrades);

        DocumentEntity createdDocument =
                documentService.createDocument(DocumentType.GOOGLE_SITES, title, randomText,
                        searchTags);

        validateForTag(createdDocument, randomText, listOfDomains);
        validateForTag(createdDocument, randomText, listOfUsStates);
        validateForTag(createdDocument, randomText, listOfGrades);
    }

    private <E extends Enum<E>, T extends HelperEnum<E>> void validateForTag(
            DocumentEntity createdDocument, String searchString, List<T> list)
            throws GwtServiceException {

        for (T curr : list) {
            SearchTags tempTags = new SearchTags();
            tempTags.add(curr);

            validateSearch(createdDocument, searchString, tempTags);
        }
    }

    private void validateSearch(DocumentEntity createdDocument, String searchString,
            SearchTags tempTags)
            throws GwtServiceException {
        SearchCriteria searchCriteria = new SearchCriteria(searchString, new SearchTags());
        validateForSearchCriteria(createdDocument, searchCriteria);

        searchCriteria = new SearchCriteria("", tempTags);
        validateForSearchCriteria(createdDocument, searchCriteria);

        searchCriteria = new SearchCriteria(searchString, tempTags);
        validateForSearchCriteria(createdDocument, searchCriteria);
    }

    int counter = 0;

    private void validateForSearchCriteria(DocumentEntity createdDocument,
            SearchCriteria searchCriteria) throws GwtServiceException {
        DocumentSearchResultWrapper result =
                documentService.searchDocument(searchCriteria);
        assertNotNull(result);
        assertEquals(1, result.getListOfDocuments().size());
        assertEquals(createdDocument.getDocumentId(), result.getListOfDocuments().get(0)
                .getDocumentId());
    }

    @Test
    public void test_updateDocument_Text() throws Exception {
        String title = "testUpdateText " + System.currentTimeMillis();

        // First creating with one content.
        String randomText1 = TestingUtils.getRandomString();

        DocumentEntity createdEntity1 =
                documentService.createDocument(DocumentType.GOOGLE_SITES, title, randomText1,
                        TestingUtils.getDefaultSearchTags());

        DocumentEntity fetch1 = documentService.getDocument(createdEntity1.getDocumentId());
        assertTrue(fetch1.getContent().contains(randomText1));

        // Now updating the content.
        String randomText2 = TestingUtils.getRandomString();
        assertTrue(!randomText1.equals(randomText2));

        DocumentEntity createdEntity2 =
                documentService.updateDocument(createdEntity1.getDocumentId(),
                        randomText2, TestingUtils.getDefaultSearchTags());

        DocumentEntity fetch2 = documentService.getDocument(createdEntity2.getDocumentId());
        assertTrue(fetch2.getContent().contains(randomText2));
        assertTrue(!fetch2.getContent().contains(randomText1));
    }

    @Test
    public void test_updateDocument_tags() throws Exception {
        String title = "test_updateDocument_tags " + System.currentTimeMillis();

        // First creating with one content.
        String randomText = TestingUtils.getRandomString();

        List<Domain> domains = Lists.newArrayList(Domain.Biology);
        List<UsStates> usStates = Lists.newArrayList(UsStates.Alabama);
        List<Grades> grades = Lists.newArrayList(Grades.Final_Year);
        SearchTags searchTags = new SearchTags(SearchTagProfile.ALL_TAGS_MANDATORY,
                domains, usStates, grades);

        DocumentEntity createdEntity =
                documentService.createDocument(DocumentType.GOOGLE_SITES, title, randomText,
                        searchTags);

        DocumentEntity fetch1 = documentService.getDocument(createdEntity.getDocumentId());
        assertTrue(fetch1.getContent().contains(randomText));
        assertEquals(domains, fetch1.getSearchTags().getDomains());
        assertEquals(usStates, fetch1.getSearchTags().getUsStates());
        assertEquals(grades, fetch1.getSearchTags().getGrades());

        // Now updating the search tags.
        domains = Lists.newArrayList(Domain.Chemistry);
        usStates = Lists.newArrayList(UsStates.District_of_Columbia);
        grades = Lists.newArrayList(Grades.GRADE_2);
        searchTags = new SearchTags(SearchTagProfile.ALL_TAGS_MANDATORY, domains, usStates, grades);

        documentService.updateDocument(createdEntity.getDocumentId(),
                randomText, searchTags);
        DocumentEntity fetch2 = documentService.getDocument(createdEntity.getDocumentId());

        assertTrue(fetch2.getContent().contains(randomText));
        assertEquals(domains, fetch2.getSearchTags().getDomains());
        assertEquals(usStates, fetch2.getSearchTags().getUsStates());
        assertEquals(grades, fetch2.getSearchTags().getGrades());
    }

    @Test
    public void testFullTextSearch() throws Exception {
        String title = "testFullTextSearch " + System.currentTimeMillis();

        String randomText1 = TestingUtils.getRandomString();

        SearchTags createTags = new SearchTags();

        Domain domain1 = Domain.Biology;
        Domain domain2 = Domain.Chemistry;
        createTags.addDomain(domain1).addDomain(domain2);

        UsStates state1 = UsStates.Alabama;
        UsStates state2 = UsStates.District_of_Columbia;
        createTags.addUsState(state1).addUsState(state2);

        Grades grade1 = Grades.Final_Year;
        Grades grade2 = Grades.GRADE_10;
        createTags.addGrade(grade1).addGrade(grade2);

        DocumentEntity createdEntity = documentService.createDocument(DocumentType.GOOGLE_SITES,
                title, randomText1, createTags);

        // Now testing.
        doFullTextSearchTest(createdEntity, randomText1, domain1, domain2);
        doFullTextSearchTest(createdEntity, randomText1, state1, state2);
        doFullTextSearchTest(createdEntity, randomText1, grade1, grade2);

        // Now testing with all.
        SearchCriteria searchCriteria = new SearchCriteria(randomText1, createTags);
        DocumentSearchResultWrapper result = documentService.searchDocument(searchCriteria);
        assertNotNull(result);
        assertEquals(1, result.getListOfDocuments().size());
        assertEquals(createdEntity.getDocumentId(), result.getListOfDocuments().get(0)
                .getDocumentId());

        // TODO(arjuns) : Add more variations so that test has more coverage.
    }

    private <T extends Enum<T>, E extends HelperEnum<T>> void doFullTextSearchTest(
            DocumentEntity createdEntity, String searchString, E testValue1, E testValue2)
            throws GwtServiceException {
        SearchTags searchTags = new SearchTags();
        SearchCriteria searchCriteria = new SearchCriteria();

        // First searching without search string.
        // Search only with test values.
        searchTags = new SearchTags().add(testValue1);
        searchCriteria.setSearchTags(searchTags);
        DocumentSearchResultWrapper result = documentService.searchDocument(searchCriteria);
        assertNotNull(result);
        validateCreatedEntityPresnt(result.getListOfDocuments(), createdEntity);

        // Testing with only testValue2
        searchTags = new SearchTags().add(testValue2);
        searchCriteria.setSearchTags(searchTags);
        result = documentService.searchDocument(searchCriteria);
        assertNotNull(result);
        validateCreatedEntityPresnt(result.getListOfDocuments(), createdEntity);
        
        // Testing with testvalue1 & testvalue2
        searchTags = new SearchTags().add(testValue1).add(testValue2);
        searchCriteria.setSearchTags(searchTags);
        result = documentService.searchDocument(searchCriteria);
        assertNotNull(result);
        validateCreatedEntityPresnt(result.getListOfDocuments(), createdEntity);

        // Now testing only with search string.
        SearchTags newSearchTags = new SearchTags();
        SearchCriteria newSearchCriteria = new SearchCriteria(searchString, newSearchTags);
        result = documentService.searchDocument(newSearchCriteria);
        assertNotNull(result);
        validateCreatedEntityPresnt(result.getListOfDocuments(), createdEntity);

        // Now testing only searchString + testValue1.
        newSearchTags = new SearchTags().add(testValue1);
        searchCriteria.setSearchTags(newSearchTags);
        result = documentService.searchDocument(newSearchCriteria);
        assertNotNull(result);
        validateCreatedEntityPresnt(result.getListOfDocuments(), createdEntity);

        // Now testing with searchString + testvalue2
        newSearchTags = new SearchTags().add(testValue2);
        searchCriteria.setSearchTags(newSearchTags);
        result = documentService.searchDocument(newSearchCriteria);
        assertNotNull(result);
        validateCreatedEntityPresnt(result.getListOfDocuments(), createdEntity);

        // Now testing with searchString + testvalue1 + testvalue2
        newSearchTags = new SearchTags().add(testValue1).add(testValue2);
        searchCriteria.setSearchTags(newSearchTags);
        result = documentService.searchDocument(newSearchCriteria);
        assertNotNull(result);
        validateCreatedEntityPresnt(result.getListOfDocuments(), createdEntity);
    }

    private void validateCreatedEntityPresnt(List<DocumentEntity> list,
            DocumentEntity createdEntity) {
        for (DocumentEntity curr : list) {
            if (curr.getDocumentId() == createdEntity.getDocumentId()) {
                return;
            }
        }
        fail();
    }

    @Test
    public void testPageLifeCycle() throws Exception {
        // String title =
        // "This Is Going To Be A Very Long Title. So That Url can be tested." +
        // " This has a Period and - Hyphens in it." + System.currentTimeMillis();

        String title = "testPageLifeCycle " + System.currentTimeMillis();
        String randomText1 = TestingUtils.getRandomString();

        DocumentEntity createdEntity = null;
        try {
            createdEntity =
                    documentService.createDocument(DocumentType.GOOGLE_SITES, title, randomText1,
                            getDefaultSearchTags());
            createdEntity =
                    documentService.createDocument(DocumentType.GOOGLE_SITES, title, randomText1,
                            getDefaultSearchTags());
            fail("Should have failed.");
        } catch (SitesWebPageAlreadyExistsException e) {
            // expected.
        }
        assertNotNull(createdEntity);

        DocumentEntity fetchedEntity = documentService.getDocument(createdEntity.getDocumentId());
        assertNotNull(fetchedEntity);
        assertTrue(fetchedEntity.getContent().contains(randomText1));

        // Now searching by RandomText1.
        SearchCriteria searchCriteria = new SearchCriteria(randomText1, new SearchTags());
        DocumentSearchResultWrapper resultWrapper = documentService.searchDocument(searchCriteria);
        assertEquals(CursorType.FTS, resultWrapper.getCursorType());
        assertEquals(1, resultWrapper.getListOfDocuments().size());
        assertEquals(fetchedEntity.getDocumentId(), resultWrapper.getListOfDocuments().get(0)
                .getDocumentId());

        // Now updating the content
        String randomText2 = TestingUtils.getRandomString();
        assertTrue(!randomText1.equals(randomText2));

        // TODO(arjuns) : Add test for updated Search Tags.
        DocumentEntity updatedEntity =
                documentService.updateDocument(fetchedEntity.getDocumentId(), randomText2,
                        getDefaultSearchTags());
        assertTrue(!updatedEntity.getContent().contains(randomText1));
        assertTrue(updatedEntity.getContent().contains(randomText2));

        // Now searching by RandomText2.
        searchCriteria = new SearchCriteria(randomText2, new SearchTags());
        resultWrapper = documentService.searchDocument(searchCriteria);
        assertEquals(CursorType.FTS, resultWrapper.getCursorType());
        assertEquals(1, resultWrapper.getListOfDocuments().size());
        assertEquals(updatedEntity.getDocumentId(), resultWrapper.getListOfDocuments().get(0)
                .getDocumentId());

        // Now searching by RandomText1. It should not be found.
        searchCriteria = new SearchCriteria(randomText1, new SearchTags());
        resultWrapper = documentService.searchDocument(searchCriteria);
        assertEquals(CursorType.FTS, resultWrapper.getCursorType());
        assertEquals(0, resultWrapper.getListOfDocuments().size());

        boolean deleteSuccess = false;
        try {
            helper.setEnvIsAdmin(true);
            adminService = new AdminServiceImpl();
            deleteSuccess = adminService.deletePage(createdEntity.getDocumentId());
            deleteSuccess = adminService.deletePage(createdEntity.getDocumentId());
        } catch (DocumentNotFoundException e) {
            // Expected.
        } finally {
            // TODO(arjuns) : Add them to testingutils
            helper.setEnvIsAdmin(false);
        }
        assertTrue(deleteSuccess);

        try {
            documentService.getDocument(createdEntity.getDocumentId());
            fail("should have failed.");
        } catch (DocumentNotFoundException e) {
            // expected.
        }
    }

    @Test
    public void testDeleteAll() throws Exception {
        // This is a very heavy test. so disabled by default.
        try {
            helper.setEnvIsAdmin(true);
            adminService = new AdminServiceImpl();
            // adminService.deleteAll();
        } finally {
            helper.setEnvIsAdmin(false);
        }
    }
}
