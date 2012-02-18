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
package com.google.lantern.server.managers;

import org.jdom.JDOMException;

import com.google.lantern.shared.exceptions.checked.server.SitesMoreThenOnePageExistsException;

import com.google.gdata.util.ServiceException;
import com.google.lantern.server.exceptions.managers.ManagerException;
import com.google.lantern.shared.exceptions.checked.common.GwtServiceException;
import com.google.lantern.shared.exceptions.checked.server.SitesWebPageDoesNotExistException;
import com.google.lantern.shared.exceptions.unchecked.AdminOnlyAccessException;
import com.google.lantern.shared.objectifyobjects.DocumentObjectInterface;
import com.google.lantern.shared.objectifyobjects.SearchTags;
import com.google.lantern.shared.objectifyobjects.documents.DocumentEntity;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

/**
 * 
 * @author Arjun Satyapal
 */
public interface ManagerInterface<T extends DocumentObjectInterface> {
    DocumentEntity createDocument(String title, String content, SearchTags searchTags)
            throws ManagerException, GwtServiceException;

    DocumentEntity getDocument(DocumentEntity existingDocument) throws GwtServiceException,
            IOException,
            ServiceException, URISyntaxException;

    void updateDocument(DocumentEntity documentEntity, String content)
            throws MalformedURLException, IOException, ServiceException, JDOMException;

    void deleteDocument(DocumentEntity documentEntity) throws AdminOnlyAccessException,
            MalformedURLException, IOException, ServiceException, URISyntaxException,
            SitesWebPageDoesNotExistException;

    boolean deleteAll() throws SitesWebPageDoesNotExistException,
            SitesMoreThenOnePageExistsException, IOException, ServiceException, URISyntaxException;
}
