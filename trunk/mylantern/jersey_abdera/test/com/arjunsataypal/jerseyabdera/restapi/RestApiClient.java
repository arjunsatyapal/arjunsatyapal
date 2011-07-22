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
package com.arjunsataypal.jerseyabdera.restapi;

import com.arjunsatyapal.jerseyabdera.restapi.enums.RestUrls.CnxModules;

import org.apache.abdera.Abdera;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.i18n.iri.IRI;
import org.apache.abdera.model.Content;
import org.apache.abdera.model.Document;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Person;
import org.apache.abdera.protocol.client.AbderaClient;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

/**
 * 
 * @author Arjun Satyapal
 */
public class RestApiClient {
    private static Logger log = Logger.getLogger(RestApiClient.class.getName());
//    private static final String SERVER_URL = "http://100.cnx-repo.appspot.com";
    private static final String SERVER_URL = "http://127.0.0.1:8888";
    private static final String REST_SERVER_URL = SERVER_URL + "/rest";
    private static HttpClient client = new HttpClient();

    private static File file = new File("/home/arjuns/test.txt");
    
    private static Abdera abdera = new Abdera();
    private static AbderaClient abderaClient = new AbderaClient(abdera);

    public static void main(String[] args) throws HttpException, IOException {
     /*   
        // Getting a UploadURL.
        PostMethod postResource = new PostMethod(REST_SERVER_URL
                + CnxResources.BASE_URL);
        postResource.setParameter("key", "value");

        int statusCode = client.executeMethod(postResource);
        String responseBody = postResource.getResponseBodyAsString();

        String string = new String(responseBody);
        log.info("StatusCode = " + statusCode);

        String[] responseLines = responseBody.split("\n");

        String resourceId = (responseLines[0].split("="))[1];
        log.info("ResourceId = " + resourceId);

        String uploadUrl = (responseLines[1].split("="))[1];
        log.info("UploadUrl = " + uploadUrl);

        // Uploading Blob.
        PostMethod uploadResource = new PostMethod(SERVER_URL + uploadUrl);
        Part[] parts = { new StringPart("param_name", "value"),
                new FilePart(file.getName(), file) };

        uploadResource.setRequestEntity(new MultipartRequestEntity(parts,
                uploadResource.getParams()));
        int uploadStatusCode = client.executeMethod(uploadResource);
        log.info("******uploadStatusCode = " + uploadStatusCode);
        
        // Downloading Blob.'
        String resourceUrl = REST_SERVER_URL
                + CnxResources.BASE_URL + "/" + resourceId;
        log.info("ResourceUrl = " + resourceUrl);
        GetMethod getResource = new GetMethod(resourceUrl);
        int downloadCode = client.executeMethod(getResource);
        log.info("DownloadCode = " + downloadCode);
        log.info("Body = " + getResource.getResponseBodyAsString());
        */
        
        /*
         * Now going for Modules. Starting with Abdera. 
         */
       
        Factory factory = abdera.getFactory();

        // Create the entry to post to Modules.
        Entry postEntry = factory.newEntry();
        
        postEntry.setId("http://www.google.com/tempid");
        
        String name = "Arjun Satyapal";
        String email = "arjuns@google.com";
        String uri = "http://profiles.google.com/arjunsatyapal";
        Person arjun = postEntry.addAuthor(name, email, uri);
        
        String scheme = "http://cnx-repo/cnxcategory.html";
        String term = "CNXModule";
        String label = "Module";
        postEntry.addCategory(scheme, term, label);
        
        String comment = "Creating new module.";
        postEntry.addComment(comment);
        
        Person tal = postEntry.addContributor("Tal Dayan", "tal@google.com", "http://profiles.google.com/tal");
        
        postEntry.setContent("<hello>1234</hello>", Content.Type.XML);
//        entry.setRights(arg0, arg1)
        postEntry.setSummary("This is a summary.");
        postEntry.setMustPreserveWhitespace(true);
        postEntry.setTitle("This is a title.");
        postEntry.setUpdated(new Date());
        
        IRI iri = new IRI(REST_SERVER_URL + CnxModules.BASE_URL);
        Document<Entry> postResponseDoc = abderaClient.post(iri.toString(), postEntry)
                .getDocument();
        IRI moduleId = postResponseDoc.getRoot().getId();
        log.info("IRI = " + moduleId);
        
        
        // Now doing get.
        
        // Downloading Blob.'
        String moduleUrl = REST_SERVER_URL + CnxModules.BASE_URL + "/" + moduleId;
        log.info("ModuleUrl = " + moduleUrl);
        GetMethod getResource = new GetMethod(moduleUrl);
        int downloadCode = client.executeMethod(getResource);
        log.info("DownloadCode = " + downloadCode);
        log.info("Body = " + getResource.getResponseBodyAsString());
        
        
        log.info("*****Reaching here with root = " + postResponseDoc.getRoot().toString());
        
        int x = 1;
    }
}
