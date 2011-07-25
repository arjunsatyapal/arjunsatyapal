/*
 * Copyright 2011 Google Inc.
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
package com.arjunsatyapal.atompub.abdera.client;

import static com.arjunsatyapal.atompub.abdera.client.CnxClientConstants.CNX_WORKSPACE;
import static com.arjunsatyapal.atompub.abdera.client.CnxClientConstants.COLLECTION_CNX_RESOURCE;

import org.apache.abdera.Abdera;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.model.Collection;
import org.apache.abdera.model.Document;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Service;
import org.apache.abdera.model.Workspace;
import org.apache.abdera.protocol.client.AbderaClient;

import java.util.Date;
import java.util.logging.Logger;

/**
 * 
 * @author Arjun Satyapal
 */
public class CnxAbderaClient {
    private static Logger logger = Logger.getLogger(CnxAbderaClient.class.getName());

    public static void main(String[] args) {

        CnxClientConstants clientConstants = new CnxClientConstants(!false);

        Abdera abdera = new Abdera();
        AbderaClient abderaClient = new AbderaClient(abdera);
        Factory factory = abdera.getFactory();

        // Fetch ServiceDocuemnt
        Document<Service> serviceDocument = abderaClient.get(
                clientConstants.getServiceDocumentUrl()).getDocument();
        Service service = serviceDocument.getRoot();

        // Get Workspace.
        Workspace workSpace = service.getWorkspace(CNX_WORKSPACE);

        // Get Resource Collection.
        Collection collection = workSpace
                .getCollection(COLLECTION_CNX_RESOURCE);

        // Create a Resource by posting entry to Collection of Resource.

        String resourceId1 = "tag:example.org,2006:foo";
        // Create the entry to post to the collection
        Entry entry = factory.newEntry();
        entry.setId(resourceId1); // todo : remove.
        entry.setTitle("This is the title"); // todo remove.
        entry.setUpdated(new Date());
        entry.addAuthor("James");
        entry.setContent("This is the content"); // todo remove.

        // Post the entry. Be sure to grab the resolved HREF of the collection
        String resourceCollectionUrl = collection.getResolvedHref().toString();
        logger.info("Posting to : " + resourceCollectionUrl);
        Document<Entry> doc = abderaClient.post(resourceCollectionUrl, entry).getDocument();
        
        if (doc != null) {
            logger.info(doc.getRoot().toString());
        } else {
            logger.info("Doc is null.");
        }
    }
}
