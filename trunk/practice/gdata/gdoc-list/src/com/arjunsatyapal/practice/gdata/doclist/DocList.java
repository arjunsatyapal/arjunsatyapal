package com.arjunsatyapal.practice.gdata.doclist;

import com.google.gdata.client.docs.DocsService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.Link;
import com.google.gdata.data.docs.DocumentListEntry;
import com.google.gdata.data.docs.DocumentListFeed;
import com.google.gdata.data.extensions.LastModifiedBy;
import com.google.gdata.util.ServiceException;

import java.io.IOException;
import java.net.URL;

public class DocList {
  public void showAllDocs(DocsService client) throws IOException, ServiceException {
    URL feedUri = new URL("https://docs.google.com/feeds/default/private/full/-/trashed");
    DocumentListFeed feed = client.getFeed(feedUri, DocumentListFeed.class);

    for (DocumentListEntry entry : feed.getEntries()) {
      printEntry(entry);
    }
  }

  public void printEntry(DocumentListEntry entry) {
    String resourceId = entry.getResourceId();
    String docType = entry.getType();

    System.out.println("'" + entry.getTitle().getPlainText() + "' (" + docType + ")");
    System.out.println("  link to Google Docs: " + entry.getDocumentLink().getHref());
    System.out.println("  resource id: " + resourceId);
    System.out.println("  doc id: " + entry.getDocId());

    // print the parent folder the document is in
    if (!entry.getParentLinks().isEmpty()) {
      System.out.println("  Parent folders: ");
      for (Link link : entry.getParentLinks()) {
        System.out.println("    --" + link.getTitle() + " - " + link.getHref());
      }
    }

    // print the timestamp the document was last viewed
    DateTime lastViewed = entry.getLastViewed();
    if (lastViewed != null) {
      System.out.println("  last viewed: " + lastViewed.toUiString());
    }

    // print who made the last modification
    LastModifiedBy lastModifiedBy = entry.getLastModifiedBy();
    if (lastModifiedBy != null) {
      System.out.println("  updated by: " +
          lastModifiedBy.getName() + " - " + lastModifiedBy.getEmail());
    }

    // Files such as PDFs take up quota
    if (entry.getQuotaBytesUsed() > 0) {
      System.out.println("Quota used: " + entry.getQuotaBytesUsed() + " bytes");
    }

    // print other useful metadata
    System.out.println("  last updated: " + entry.getUpdated().toUiString());
    System.out.println("  viewed by user? " + entry.isViewed());
    System.out.println("  writersCanInvite? " + entry.isWritersCanInvite().toString());
    System.out.println("  hidden? " + entry.isHidden());
    System.out.println("  starred? " + entry.isStarred());
    System.out.println("  trashed? " + entry.isTrashed());
    System.out.println();
  }
}
