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

import com.google.gdata.data.Content;
import com.google.gdata.data.TextConstruct;
import com.google.gdata.data.TextContent;
import com.google.gdata.data.XhtmlTextConstruct;
import com.google.gdata.data.sites.WebPageEntry;
import com.google.gdata.util.XmlBlob;
import com.google.lantern.server.persistence.entities.ParentSitesPageEntity;
import com.google.lantern.server.utils.sites.SitesUriHelper;
import com.google.lantern.server.utils.xml.XmlPrinter;
import com.google.lantern.shared.objectifyobjects.SitesPage;
import java.io.IOException;
import org.jdom.JDOMException;

/**
 * 
 * @author Arjun Satyapal
 */
public class SitesUtils {
    static SitesPage getSitesPageFromWebPageEntry(WebPageEntry webPageEntry,
            String parentPageSitesId)
            throws JDOMException, IOException {
        String newSitesPageId = SitesUriHelper.getIdFromEntryId(webPageEntry.getId());

        String title = webPageEntry.getTitle().getPlainText();
        String content = getReformattedXhtmlFromContent(webPageEntry.getContent());

        // TODO(arjuns) : Fix this. Get search tags from local db.
        SitesPage sitesPage = new SitesPage(newSitesPageId, parentPageSitesId, title,
                webPageEntry.getSelfLink().getHref(), content, webPageEntry.getEtag());
        return sitesPage;
    }

    static ParentSitesPageEntity getParentSitesPageEntityFromWebPageEntry(WebPageEntry entry,
            String title, String domain, String siteName) {
        String entryId = SitesUriHelper.getIdFromEntryId(entry.getSelfLink().getHref());
        ParentSitesPageEntity entity = new ParentSitesPageEntity(entryId,
                title, domain, siteName, entry.getSelfLink().getHref());

        return entity;
    }

    protected static String getReformattedXhtmlFromContent(final Content content)
            throws JDOMException,
            IOException {
        if (content instanceof TextContent) {
            TextContent txtContent = (TextContent) content;
            TextConstruct txtConstruct = txtContent.getContent();

            if (txtConstruct instanceof XhtmlTextConstruct) {
                XhtmlTextConstruct xhtmlTxtConstruct = (XhtmlTextConstruct) txtConstruct;
                XmlBlob xhtmlBlob = xhtmlTxtConstruct.getXhtml();
                return reformatXml(xhtmlBlob.getBlob());
            }
        }
        throw new IllegalStateException(
                "Code should not reach here. Reached here for contentType = " + content.getClass());
    }

    protected static String reformatXml(final String originalXml) throws JDOMException, IOException {
        // TODO(arjuns) : Fix the hack.
        String errString = "src=\'//www";
        String fixString = "src=\'http://www";
        String fixedXhtml = originalXml.replace(errString, fixString);
        String reformattedXhtml = XmlPrinter.prettyPrint(fixedXhtml);

        return reformattedXhtml;
    }
}
