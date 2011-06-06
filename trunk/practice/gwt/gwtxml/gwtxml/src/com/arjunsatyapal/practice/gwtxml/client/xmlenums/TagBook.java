// Copyright 2011 Google Inc. All Rights Reserved.

package com.arjunsatyapal.practice.gwtxml.client.xmlenums;

import com.google.gwt.user.client.Window;

/**
 * @author arjuns@google.com (Arjun Satyapal)
 *
 */
public enum TagBook {
  AUTHOR("bib:author", "Author"),
  TITLE("bib:title", "Title"),
  PUBLISHER("bib:publisher", "Publisher"),
  YEAR("bib:year", "Year"),
  VOLUME("bib:volume", "Voluvme"),
  SERIES("bib:series", "Series"),
  ADDRESS("bib:address", "Address"),
  EDITION("bib:edition", "Edition"),
  MONTH("bib:month", "Month"),
  NOTE("bib:note", "Note"),

  // TODO(arjuns) : Probably combine text and comment in parent enum.
  TEXT("#text", "Text"),
  COMMENT("#comment", "Comment");

  private String xmlTag;
  private String publicString;

  private TagBook(String xmlTag, String publicString) {
    this.xmlTag = xmlTag;
    this.publicString = publicString;
  }

  public String getXmlTag() {
    return xmlTag;
  }

  public String getPublicString() {
    return publicString;
  }

  public static TagBook getTagPersonByXmlTag(String xmlTag) {
    for (TagBook currTag : TagBook.values()) {
      if (currTag.getXmlTag().equals(xmlTag)) {
        return currTag;
      }
    }

    String errString = "Invalid xmlTag[" + xmlTag + "].";
    Window.alert(errString);
    throw new IllegalArgumentException(errString);
  }
}
