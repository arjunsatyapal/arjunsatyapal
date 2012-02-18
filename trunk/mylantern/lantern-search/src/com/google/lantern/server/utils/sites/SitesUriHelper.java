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

import com.google.lantern.server.managers.sites.SitesContentKind;

import java.net.URISyntaxException;

/**
 * TODO(arjuns) : Eventually replace these with UriBuilder.
 * 
 * @author Arjun Satyapal
 */
public class SitesUriHelper {
    // TODO(arjuns) : Optimize this class.

    private final String domain;
    private final String siteName;

    static final String FEED = "https://sites.google.com/feeds/";
    static final String FEED_CONTENT = FEED + "content/";
    static final String FEED_REVISION = FEED + "revision/";
    static final String FEED_ACTIVITY = FEED + "activity/";
    static final String FEED_SITE = FEED + "site/";
    static final String FEED_ACL_SITE = FEED + "/" + "acl/site/";

    public String getDomain() {
        return domain;
    }

    public String getSiteName() {
        return siteName;
    }

    public SitesUriHelper(String domain, String siteName) {
        String modifiedDomain = domain;
        if (!modifiedDomain.endsWith("/")) {
            modifiedDomain = modifiedDomain + "/";
        }
        this.domain = modifiedDomain;
        this.siteName = siteName;
    }

    public String getContentFeedUrl() throws URISyntaxException {
        return new SitesUriBuilder(FEED_CONTENT).appendChild(domain)
                .appendChild(siteName).build();
    }

    public String getContentFeedUrlForWebPages() throws URISyntaxException {
        return getContentFeedUrlForKind(SitesContentKind.WEB_PAGE);
    }

    public String getContentFeedUrlForKind(SitesContentKind kind) throws URISyntaxException {
        String query = "kind=" + kind.get();
        return new SitesUriBuilder(getContentFeedUrl()).appendQuery(query).build();
    }

    public String getRevisionFeedUrl() throws URISyntaxException {
        return new SitesUriBuilder(FEED_REVISION).appendChild(domain)
                .appendChild(siteName).build();
    }

    public String getActivityFeedUrl() throws URISyntaxException {
        return new SitesUriBuilder(FEED_ACTIVITY).appendChild(domain)
                .appendChild(siteName).build();
    }

    public String getSiteFeedUrl() throws URISyntaxException {
        return new SitesUriBuilder(FEED_SITE).appendChild(domain).build();
    }

    public String getAclFeedUrl(String siteName) throws URISyntaxException {
        return new SitesUriBuilder(FEED_ACL_SITE).appendChild(domain)
                .appendChild(siteName).build();
    }

    public static String getIdFromEntryId(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }

    // TODO(arjuns) : Fix this.
    public static String getWebUrlFromUrl(String url) {
        return "xyz";
    }
}
