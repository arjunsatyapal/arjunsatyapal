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

import static org.junit.Assert.assertEquals;

import com.google.lantern.server.managers.sites.SitesContentKind;

import org.junit.Test;

import com.google.lantern.server.utils.sites.SitesUriHelper;

/**
 * Test for {@link SitesUriHelper}
 * 
 * @author Arjun Satyapal
 */
public class SitesUriHelperTest {
    private final String DOMAIN = "google.com/";
    private final String SITE_NAME = "arjuns-lantern";
    
    private final SitesUriHelper sitesUrlHelper = new SitesUriHelper(DOMAIN, SITE_NAME);
    
    /**
     * Test for {@link SitesUriHelper#getContentFeedUrl()}
     */
    @Test
    public void testGetContentFeedUrl() throws Exception {
        String expected = SitesUriHelper.FEED + "content/" + DOMAIN + SITE_NAME;
        assertEquals(expected, sitesUrlHelper.getContentFeedUrl());
    }
    
    /**
     * Test for {@link SitesUriHelper#getContentFeedUrlForWebPages()
     */
    @Test
    public void testGetContentFeedUrlForWebPages() throws Exception {
        String expected = SitesUriHelper.FEED + "content/" + DOMAIN + SITE_NAME + "?kind=webpage";
        assertEquals(expected, sitesUrlHelper.getContentFeedUrlForWebPages());
    }
    
    /**
     * Test for {@link SitesUriHelper#getContentFeedUrlForKind(SitesContentKind)
     */
    @Test
    public void testGetContentFeedUrlForKind() throws Exception {
        String prefix= SitesUriHelper.FEED + "content/" + DOMAIN + SITE_NAME + "?kind=";
        
        for (SitesContentKind currKind : SitesContentKind.values()) {
            String expected = prefix + currKind.get();
            assertEquals(expected, sitesUrlHelper.getContentFeedUrlForKind(currKind));
        }
    }
    
    /**
     * Test for {@link SitesUriHelper#getRevisionFeedUrl()
     */
    @Test
    public void testGetRevisionFeedUrl() throws Exception {
        String expected = SitesUriHelper.FEED_REVISION + DOMAIN + SITE_NAME;
        assertEquals(expected, sitesUrlHelper.getRevisionFeedUrl());
    }
    
    /**
     * Test for {@link SitesUriHelper#getActivityFeedUrl()
     */
    @Test
    public void testGetActivityFeedUrl() throws Exception {
        String expected = SitesUriHelper.FEED_ACTIVITY + DOMAIN + SITE_NAME;
        assertEquals(expected, sitesUrlHelper.getActivityFeedUrl());
    }
    
    /**
     * Test for {@link SitesUriHelper#getSiteFeedUrl()
     */
    @Test
    public void testGetSiteFeedUrl() throws Exception {
        String expected = SitesUriHelper.FEED_SITE + DOMAIN;
        assertEquals(expected, sitesUrlHelper.getSiteFeedUrl());
    }
}
