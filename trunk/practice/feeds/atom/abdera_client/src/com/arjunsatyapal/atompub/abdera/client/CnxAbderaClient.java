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
import org.apache.abdera.model.Link;
import org.apache.abdera.model.Service;
import org.apache.abdera.model.Workspace;
import org.apache.abdera.protocol.client.AbderaClient;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.MultipartPostMethod;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Arjun Satyapal
 */
public class CnxAbderaClient {
    private static Logger logger = Logger.getLogger(CnxAbderaClient.class.getName());

    public static void main(String[] args) throws IOException {

        CnxClientConstants clientConstants = new CnxClientConstants(false);

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
        Collection resourceCollection = workSpace
                .getCollection(COLLECTION_CNX_RESOURCE);

        // Create a Resource by posting entry to Collection of Resource.
        Entry entry = factory.newEntry();
        entry.addAuthor("Arjun Satyapal", "arjuns@google.com", "http://plus.google.com/arjuns");

        String resourceCollectionUrl = resourceCollection.getResolvedHref().toString();
        logger.info("Posting to : " + resourceCollectionUrl);

        Document<Entry> doc = abderaClient.post(resourceCollectionUrl, entry).getDocument();

        Entry postResourceResp = doc.getRoot();
        List<Link> links = postResourceResp.getLinks();

        String blobstoreUrl = null;
        for (Link currLink : links) {
            if (currLink.getTitle().equals("UploadURL")) {
                blobstoreUrl = currLink.getRel() + currLink.getHref();
                logger.info("BlobStore UploadUrl = " + blobstoreUrl);
            } else if (currLink.getTitle().equals("ResourceID")) {
                logger.info("Resource URL = " + currLink.getRel() + currLink.getHref());
            }
        }

        // Now posting file to Blobstore.
        File file = new File("/home/arjuns/test_file.txt");
        HttpClient httpClient = new HttpClient();
        MultipartPostMethod postMethod = new MultipartPostMethod(blobstoreUrl);
        postMethod.addParameter("test_file.txt", file);
        httpClient.executeMethod(postMethod);
        String response = postMethod.getResponseBodyAsString();
        logger.info(response);

        logger.info("***** Done.");


        // Now uploading Module.
        //

    }
}
