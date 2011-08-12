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
package com.arjunsatyapal.httpclient;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.http.message.BasicNameValuePair;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

/**
 *
 * @author Arjun Satyapal
 */
public class MyMain {

    public static void main(String[] args) throws HttpException, IOException {
        HttpClient httpClient = new HttpClient();
        // PostMethod postMethod = new PostMethod("http://arjuns-test.appspot.com/testPage");

        String fileName = "/home/arjuns/cnxmodules/m0504.modified/index_auto_generated.cnxml";
        File cnxml = new File(fileName);
        String cnxmlAsString = Files.toString(cnxml, Charsets.UTF_8);
        // System.out.println(cnxmlAsString);

        UTF8PostMethod postMethod = new UTF8PostMethod("http://localhost:8888/testPage");
        // postMethod.addRequestHeader("Content-Type", "text/html; charset=UTF-8");

        File file = new File(fileName);
        Part[] parts = { new FilePart(file.getName(), file) };

        postMethod.setParameter("Content-Type", "text/xml; charset=UTF-8");

        String encodedString = get64bitEncodedString(cnxmlAsString);
        postMethod.setRequestEntity(new StringRequestEntity(cnxmlAsString, "text/xml", "UTF-8"));

        httpClient.executeMethod(postMethod);

        System.out.println(new String(postMethod.getResponseBody()));
        System.out.println("**Done**");
    }

    public static class UTF8PostMethod extends PostMethod {
        public UTF8PostMethod(String url) {
            super(url);
        }

        @Override
        public String getRequestCharSet() {
            // return super.getRequestCharSet();
            return "UTF-8";
        }
    }

    public static String get64bitEncodedString(String originalString) {
        Base64 base64 = new Base64(true);
        byte[] encodedStringBytes = Base64.encodeBase64URLSafe(originalString.getBytes());
        String encoded64BitString = DatatypeConverter.printBase64Binary(encodedStringBytes);

        return encoded64BitString;
    }

    public String decodeFrom64BitEncodedString(String encodedString) {
        byte[] encodedBytes = DatatypeConverter.parseBase64Binary(encodedString);
        byte[] decodedBytes = Base64.decodeBase64(encodedBytes);
        return new String(decodedBytes);
    }


}
