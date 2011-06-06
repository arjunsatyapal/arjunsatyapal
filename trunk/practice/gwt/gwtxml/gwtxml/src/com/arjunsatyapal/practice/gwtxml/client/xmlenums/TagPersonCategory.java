// Copyright 2011 Google Inc. All Rights Reserved.

package com.arjunsatyapal.practice.gwtxml.client.xmlenums;

import com.google.gwt.user.client.Window;

/**
 * @author arjuns@google.com (Arjun Satyapal)
 *
 */
public enum TagPersonCategory {
  AUTHOR("md:author", "Author"),
  MAINTAINER("md:maintainer", "Maintainer"),
  LICENSOR("md:licensor", "Licensor"),

  // Text
  // TODO(arjuns) : Probably combine text and comment in parent enum.
  TEXT("#text", "Text"),
  COMMENT("#comment", "Comment");

  private String xmlTag;
  private String publicString;

  private TagPersonCategory(String xmlTag, String publicString) {
    this.xmlTag = xmlTag;
    this.publicString = publicString;
  }

  public String getXmlTag() {
    return xmlTag;
  }

  public String getPublicString() {
    return publicString;
  }

  public static TagPersonCategory getTagPersonCategoryByXmlTag(String xmlTag) {
    for (TagPersonCategory curr : TagPersonCategory.values()) {
      if (curr.getXmlTag().equals(xmlTag)) {
        return curr;
      }
    }

    String errString = "Invalid xmlTag[" + xmlTag + "].";
    Window.alert(errString);
    throw new IllegalArgumentException(errString);
  }
}
