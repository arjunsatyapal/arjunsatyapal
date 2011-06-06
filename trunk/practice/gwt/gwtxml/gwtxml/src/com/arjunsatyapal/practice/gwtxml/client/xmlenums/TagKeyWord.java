// Copyright 2011 Google Inc. All Rights Reserved.

package com.arjunsatyapal.practice.gwtxml.client.xmlenums;

import com.google.gwt.user.client.Window;

/**
 * @author arjuns@google.com (Arjun Satyapal)
 *
 */
public enum TagKeyWord {
  KEYWORD("md:keyword", "Keyword"),

  // TODO(arjuns) : Probably combine text and comment in parent enum.
  TEXT("#text", "Text"),
  COMMENT("#comment", "Comment");

  private String xmlTag;
  private String publicString;

  private TagKeyWord(String xmlTag, String publicString) {
    this.xmlTag = xmlTag;
    this.publicString = publicString;
  }

  public String getXmlTag() {
    return xmlTag;
  }

  public String getPublicString() {
    return publicString;
  }

  public static TagMetadata getTagMetadataByXmlTag(String xmltag) {
    for (TagMetadata curr : TagMetadata.values()) {
      if (curr.getXmlTag().equals(xmltag)) {
        return curr;
      }
    }

    String errStr = "Invalid xmlTag[" + xmltag + "].";
    Window.alert(errStr);
    throw new IllegalArgumentException(errStr);
  }
}
