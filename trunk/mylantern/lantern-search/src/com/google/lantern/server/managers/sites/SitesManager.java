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
package com.google.lantern.server.managers.sites;

import com.google.gdata.data.sites.WebPageEntry;
import com.google.gdata.util.ServiceException;
import com.google.lantern.server.exceptions.credential.CredentialCouldNotBeRefreshed;
import com.google.lantern.server.exceptions.managers.sites.SitesManagerException;
import com.google.lantern.server.managers.ManagerInterface;
import com.google.lantern.server.persistence.dao.SitesDao;
import com.google.lantern.server.persistence.entities.ParentSitesPageEntity;
import com.google.lantern.server.persistence.entities.UserEntity;
import com.google.lantern.server.utils.appengine.AppEngineUtils;
import com.google.lantern.server.utils.oauth2.ResourceOwnerCredentials;
import com.google.lantern.shared.exceptions.checked.common.GwtServiceException;
import com.google.lantern.shared.exceptions.checked.server.SitesMoreThenOnePageExistsException;
import com.google.lantern.shared.exceptions.checked.server.SitesWebPageDoesNotExistException;
import com.google.lantern.shared.exceptions.unchecked.AdminOnlyAccessException;
import com.google.lantern.shared.exceptions.unchecked.UserNotLoggedInException;
import com.google.lantern.shared.objectifyobjects.SearchTags;
import com.google.lantern.shared.objectifyobjects.SitesPage;
import com.google.lantern.shared.objectifyobjects.documents.DocumentEntity;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.logging.Logger;
import org.jdom.JDOMException;

/**
 * 
 * @author Arjun Satyapal
 */
public class SitesManager implements ManagerInterface<SitesPage> {
    private final static Logger logger = Logger.getLogger(SitesManager.class.getName());
    // TODO(arjuns) : Make this env dependent.

    private UserEntity userEntity;
    private final String APPLICATION_NAME = "light";
    private final String DOMAIN = "google.com";
    private final String SITE_NAME = "arjuns-lantern";
    protected JavaSitesHelper javaSitesHelper;

    public SitesManager() throws UserNotLoggedInException, CredentialCouldNotBeRefreshed,
            IOException {
        this.userEntity = ResourceOwnerCredentials.loadValidUserEntity();

        javaSitesHelper =
                new JavaSitesHelper(APPLICATION_NAME, DOMAIN, SITE_NAME,
                        userEntity.getAuthorizationHeader());
    }

    private ParentSitesPageEntity determineParent() throws MalformedURLException,
            IOException,
            ServiceException, URISyntaxException, JDOMException, GwtServiceException {
        ParentSitesPageEntity parentSitesPage = getOrCreateParentPage();

        return parentSitesPage;
    }

    private ParentSitesPageEntity getOrCreateParentPage() throws MalformedURLException,
            IOException, ServiceException, URISyntaxException, JDOMException, GwtServiceException {
        String defaultParentTitle = ParentCategories.DEFAULT_PARENT.getPath();

        ParentSitesPageEntity result = SitesDao.getParentPageEntityByTitle(defaultParentTitle);

        if (result == null) {
            // First try to fetch entry from Google Sites.
            WebPageEntry webPageEntry = null;

            // If some one goes and deletes the ParentPage at sites, then this will be in soup
            // till the time feed from Sites is not processed.
            try {
                webPageEntry = javaSitesHelper.getSitesPageByTitle(null, defaultParentTitle);
            } catch (SitesWebPageDoesNotExistException e) {
                // Try to create page.
            }

            if (webPageEntry == null) {
                // If it does not exist, then create it.
                webPageEntry = createParent(defaultParentTitle);
            }

            return updateDataStoreWithParentSitesPageEntry(webPageEntry, defaultParentTitle);
        }
        return result;
    }

    private ParentSitesPageEntity updateDataStoreWithParentSitesPageEntry(
            WebPageEntry webPageEntry, String parentTitle) {
        ParentSitesPageEntity entity =
                SitesUtils.getParentSitesPageEntityFromWebPageEntry(webPageEntry, parentTitle,
                        DOMAIN, SITE_NAME);

        SitesDao.updateParentPage(entity);
        return entity;
    }

    private WebPageEntry createParent(String parentTitle) throws MalformedURLException,
            IOException, ServiceException, URISyntaxException, GwtServiceException {
        WebPageEntry webPageEntry = javaSitesHelper.createSitesParentPage(parentTitle,
                "Parent : " + parentTitle);

        return webPageEntry;
    }

    public DocumentEntity createDocument(String title, String content, SearchTags searchTags)
            throws SitesManagerException, GwtServiceException {
        try {
            ParentSitesPageEntity parentSitesPageEntity = determineParent();
            logger.info("Parent AtomLink = " + parentSitesPageEntity.getAtomSelfLink());

            WebPageEntry webPageEntry = null;
            if (parentSitesPageEntity == null) {
                webPageEntry = javaSitesHelper.createSitesParentPage(title, content);
            } else {
                webPageEntry =
                        javaSitesHelper.createSitesSubPage(parentSitesPageEntity.getAtomSelfLink(),
                                title, content);
            }
            logger.info("Successfully created SitesPage for title[" + title + "] with Link["
                    + webPageEntry.getSelfLink().getHref() + "].");

            // TODO(arjuns) : Fix this for parent.
            SitesPage sitesPage = SitesUtils.getSitesPageFromWebPageEntry(webPageEntry,
                    parentSitesPageEntity.getId());

            DocumentEntity documentEntity =
                    new DocumentEntity(AppEngineUtils.getUserEmail(), sitesPage, searchTags);
            return documentEntity;
        } catch (GwtServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new SitesManagerException(e);
        }
    }

    public void deleteDocument(DocumentEntity documentEntity) throws AdminOnlyAccessException,
            MalformedURLException, IOException, ServiceException, URISyntaxException,
            SitesWebPageDoesNotExistException {
        AppEngineUtils.validateAdminAccessOnly();
        javaSitesHelper.deleteSitesPage(documentEntity.getSitesPage().getAtomSelfLink());
    }

    public DocumentEntity getDocument(DocumentEntity existingDocument) throws GwtServiceException,
            IOException,
            ServiceException, URISyntaxException {
        // TODO(arjuns) : update local datastore.

        javaSitesHelper.getSitesPage(existingDocument.getSitesPage().getAtomSelfLink());

        // TODO(arjuns) : Update document, and search index.

        return existingDocument;
    }

    public boolean deleteAll() throws SitesWebPageDoesNotExistException,
            SitesMoreThenOnePageExistsException, IOException, ServiceException, URISyntaxException {
        // First delete all the sites pages.
        javaSitesHelper.deleteAll();

        return true;
    }

    public void updateDocument(DocumentEntity documentEntity, String content)
            throws MalformedURLException, IOException, ServiceException, JDOMException {
        SitesPage oldSitesPage = documentEntity.getSitesPage();

        WebPageEntry updatedEntry =
                javaSitesHelper.updateSitesPage(oldSitesPage.getAtomSelfLink(), content);

        if (oldSitesPage.isModified(updatedEntry.getEtag())) {
            logger.info("Etag changed. So makring Document[" + documentEntity.getDocumentId()
                    + "] as dirty.");
            // TODO(arjuns) : See what needs to be done with the following method.
            SitesPage updatedSitesPage =
                    SitesUtils.getSitesPageFromWebPageEntry(updatedEntry,
                            oldSitesPage.getParentSitesPageId());

            oldSitesPage.updateFrom(updatedSitesPage);
            documentEntity.setDirty(true);
        }
    }
}
