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

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import com.google.gdata.client.sites.SitesService;
import com.google.gdata.data.HtmlTextConstruct;
import com.google.gdata.data.Link;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.XhtmlTextConstruct;
import com.google.gdata.data.sites.BaseContentEntry;
import com.google.gdata.data.sites.ContentFeed;
import com.google.gdata.data.sites.SiteEntry;
import com.google.gdata.data.sites.SiteFeed;
import com.google.gdata.data.sites.SitesLink;
import com.google.gdata.data.sites.WebPageEntry;
import com.google.gdata.util.ResourceNotFoundException;
import com.google.gdata.util.ServiceException;
import com.google.gdata.util.VersionConflictException;
import com.google.gdata.util.XmlBlob;
import com.google.lantern.server.utils.appengine.AppEngineUtils;
import com.google.lantern.server.utils.sites.SitesUriBuilder;
import com.google.lantern.server.utils.sites.SitesUriHelper;
import com.google.lantern.shared.exceptions.checked.client.sites.SitesWebPageAlreadyExistsException;
import com.google.lantern.shared.exceptions.checked.common.GwtServiceException;
import com.google.lantern.shared.exceptions.checked.server.CodeShouldNotReachHere;
import com.google.lantern.shared.exceptions.checked.server.SitesMoreThenOnePageExistsException;
import com.google.lantern.shared.exceptions.checked.server.SitesWebPageDoesNotExistException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.logging.Logger;
import org.jdom.JDOMException;

/**
 * 
 * @author Arjun Satyapal
 */
public class JavaSitesHelper {
    private static final Logger logger = Logger.getLogger(JavaSitesHelper.class.getName());
    private final SitesUriHelper sitesUriHelper;
    private final SitesService client;

    public JavaSitesHelper(String appName, String domain, String siteName, String accessToken) {
        sitesUriHelper = new SitesUriHelper(domain, siteName);
        client = new SitesService(appName, "https", domain);
        client.getRequestFactory().setHeader("Authorization", accessToken);
    }

    public void getSiteFeed() throws MalformedURLException, IOException, ServiceException,
            URISyntaxException {
        SiteFeed siteFeed =
                client.getFeed(new URL(sitesUriHelper.getSiteFeedUrl()), SiteFeed.class);
        for (SiteEntry entry : siteFeed.getEntries()) {
            System.out.println("title: " + entry.getTitle().getPlainText());
            System.out.println("site name: " + entry.getSiteName().getValue());
            System.out.println("theme: " + entry.getTheme().getValue());
            System.out.println("");
        }
    }

    public WebPageEntry createSitesParentPage(String parentTitle, String content)
            throws MalformedURLException,
            IOException, ServiceException, URISyntaxException, GwtServiceException {
        return doCreateSitesPage(new WebPageEntry(), parentTitle, content);
    }

    public WebPageEntry createSitesSubPage(String parentAtomLink, String title, String content)
            throws MalformedURLException, IOException, ServiceException, URISyntaxException,
            GwtServiceException {
        WebPageEntry entry = new WebPageEntry();
        entry.addLink(SitesLink.Rel.PARENT, Link.Type.ATOM, parentAtomLink);

        return doCreateSitesPage(entry, title, content);
    }

    private WebPageEntry doCreateSitesPage(WebPageEntry entry, String title, String content)
            throws MalformedURLException, IOException, ServiceException, URISyntaxException,
            GwtServiceException {
        entry.setTitle(new PlainTextConstruct(title));
        setContentBlob(entry, content);

        try {
            return client.insert(new URL(sitesUriHelper.getContentFeedUrl()), entry);
        } catch (VersionConflictException e) {
            String trace = Throwables.getStackTraceAsString(e);
            if (trace.contains("Duplicate insert")) {
                throw new SitesWebPageAlreadyExistsException("Page with title[" + title
                        + " already exists.");
            }
        }

        throw new CodeShouldNotReachHere();
    }

    public void deleteSitesPage(String atomLink) throws MalformedURLException, IOException,
            ServiceException, URISyntaxException, SitesWebPageDoesNotExistException {
        AppEngineUtils.validateAdminAccessOnly();

        WebPageEntry entry = getSitesPage(atomLink);
        entry.delete();
        logger.info("Successfully deleted Page with Title[" + entry.getTitle() + ", link["
                + atomLink + "].");
    }

    private void setContentBlob(BaseContentEntry<?> entry, String content) {
        XmlBlob xml = new XmlBlob();
        xml.setBlob(String.format(
                content, entry.getCategories().iterator().next().getLabel()));
        entry.setContent(new XhtmlTextConstruct(xml));
    }

    public WebPageEntry getSitesPageByTitle(String pathToParent, String title)
            throws IOException, ServiceException, URISyntaxException,
            SitesWebPageDoesNotExistException,
            SitesMoreThenOnePageExistsException, JDOMException {
        // TODO(arjuns): Eventually enable this. Only admins should be using this. Rest
        // should use the entryId stored in Datastore.
        // AppEngineUtils.validateAdminAccessOnly();
        String url = getPageUri(pathToParent, title);

        List<SiteEntry> siteEntries = client.getFeed(new URL(url), SiteFeed.class).getEntries();
        if (siteEntries.size() < 1) {
            throw new SitesWebPageDoesNotExistException("url[" + url + "] does not exist.");
        } else if (siteEntries.size() > 1) {
            throw new SitesMoreThenOnePageExistsException("Found [" + siteEntries.size()
                    + "] entries for url = " + url);
        }

        SiteEntry entry = siteEntries.get(0);

        WebPageEntry webPageEntry = (WebPageEntry) entry.getAdaptedEntry();
        return webPageEntry;

    }

    private String getPageUri(String pathToParent, String title) throws URISyntaxException {
        SitesUriBuilder builder = new SitesUriBuilder();

        if (pathToParent != null) {
            builder.appendChild(pathToParent);
        }

        Preconditions.checkArgument(!Strings.isNullOrEmpty(title),
                "title cannot be null.");
        builder.appendChild(title);

        String pathToWebPage = builder.build();

        String contentFeedUrl = sitesUriHelper.getContentFeedUrl();
        String query = "path=/" + pathToWebPage;
        String url = new SitesUriBuilder(contentFeedUrl).appendQuery(query).build();
        return url;
    }

    public WebPageEntry getSitesPage(String atomLink) throws IOException, ServiceException,
            URISyntaxException, SitesWebPageDoesNotExistException {
        WebPageEntry entry = client.getEntry(new URL(atomLink), WebPageEntry.class);

        if (entry == null) {
            throw new SitesWebPageDoesNotExistException("Page with link [" + atomLink
                    + "], does not exist.");
        }

        return entry;
    }

    public void deleteAll() throws IOException, ServiceException, URISyntaxException,
            SitesWebPageDoesNotExistException, SitesMoreThenOnePageExistsException {
        AppEngineUtils.validateAdminAccessOnly();
        ContentFeed feed = getContentFeed(SitesContentKind.ALL);

        logger.info("Found [" + feed.getEntries().size() + "] entries.");

        for (BaseContentEntry currEntry : feed.getEntries()) {
            try {
                currEntry.delete();
                logger.info("Successfully deleted Entry with Title["
                        + currEntry.getTitle().getPlainText() + "], SelfLink["
                        + currEntry.getSelfLink().getHref() + "].");
            } catch (ResourceNotFoundException e) {
                logger.info("Failed to delete : " + currEntry);
            }
        }
    }

    public ContentFeed getContentFeed(SitesContentKind kind) throws IOException, ServiceException,
            MalformedURLException, URISyntaxException {
        String contentFeedUrl = sitesUriHelper.getContentFeedUrl();
        String url =
                kind == SitesContentKind.ALL ? contentFeedUrl : contentFeedUrl + "?kind="
                        + kind.get();
        ContentFeed contentFeed = client.getFeed(new URL(url), ContentFeed.class);
        return contentFeed;
    }

    public WebPageEntry updateSitesPage(String atomLink, String content) throws MalformedURLException, IOException, ServiceException {
        WebPageEntry entry = client.getEntry(new URL(atomLink), WebPageEntry.class);
        
        entry.setContent(getHtmlContentFromString(content));
        WebPageEntry updatedEntry = entry.update();
        
        return updatedEntry;
    }
    
    private HtmlTextConstruct getHtmlContentFromString(String htmlString) {
        return new HtmlTextConstruct(htmlString);
    }

}
