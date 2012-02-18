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

import static com.google.common.base.Preconditions.checkArgument;
import com.google.common.base.Strings;
import com.google.common.base.Preconditions;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * TODO(arjuns) : Add tess for this.
 * 
 * @author Arjun Satyapal
 */
public class SitesUriBuilder {
    private String currentUri;

    public SitesUriBuilder() {
        this(null);
    }

    public SitesUriBuilder(String parentUrl) {
        if (parentUrl != null) {
            checkArgument(!parentUrl.isEmpty());
            checkArgument(!parentUrl.startsWith("/"));
        }

        currentUri = parentUrl;
    }

    public String fixRepetition(String origString, String oldString, String newString) {
        String modifiedString = origString;
        Preconditions.checkArgument(oldString.length() > newString.length());
        do {
            modifiedString = modifiedString.replace(oldString, newString);
        } while (modifiedString.contains(oldString));

        return modifiedString;
    }

    public String build() throws URISyntaxException {
        String newUriPrefix = "";
        String stringToBeModified = currentUri;
        if (currentUri.contains("/")) {
            newUriPrefix = currentUri.substring(0, currentUri.lastIndexOf("/"));
            stringToBeModified = currentUri.substring(currentUri.lastIndexOf("/") + 1);
        }

        stringToBeModified = fixRepetition(stringToBeModified, ". ", ".");
        stringToBeModified = fixRepetition(stringToBeModified, "  ", ".");
        stringToBeModified = fixRepetition(stringToBeModified, " .", ".");
        stringToBeModified = fixRepetition(stringToBeModified, "..", ".");

        stringToBeModified = stringToBeModified.replace(" ", "-");
        stringToBeModified = stringToBeModified.replace(".", "-");

        do {
            if (stringToBeModified.endsWith(".") || stringToBeModified.endsWith("-")) {
                stringToBeModified =
                        stringToBeModified.substring(0, stringToBeModified.length() - 1);
            }
        } while (stringToBeModified.endsWith(".") || stringToBeModified.endsWith("-"));

        if (newUriPrefix.length() > 1) {
            newUriPrefix = newUriPrefix + "/";
        }
        currentUri = newUriPrefix + stringToBeModified;
        validateUri(currentUri);
        return currentUri.toLowerCase();
    }

    private URI validateUri(String testUrl) throws URISyntaxException {
        return new URI(testUrl);
    }

    public SitesUriBuilder appendChild(String child) throws URISyntaxException {
        if (currentUri == null) {
            currentUri = "";
        }

        Preconditions.checkArgument(!Strings.isNullOrEmpty(child));
        if (currentUri.length() > 0 && !currentUri.endsWith("/")) {
            currentUri += "/";
        }

        if (child.startsWith("/")) {
            // One / is already added.
            currentUri += child.substring(1);
        } else {
            currentUri += child;
        }

        return this;
    }

    public SitesUriBuilder appendQuery(String query) throws URISyntaxException {
        checkArgument(!Strings.isNullOrEmpty(currentUri));
        checkArgument(!Strings.isNullOrEmpty(query));

        if (!currentUri.contains("?")) {
            currentUri += "?";
        }

        if (!currentUri.endsWith("?") && !currentUri.endsWith("&")) {
            currentUri += "&";
        }

        if (query.startsWith("?") || query.startsWith("&")) {
            currentUri += query.substring(1);
        } else {
            currentUri += query;
        }

        return this;

    }
}
