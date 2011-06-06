// Copyright 2011 Google Inc. All Rights Reserved.

package com.arjunsatyapal.practice.gwtxml.client.xmlenums;

import com.google.gwt.user.client.Window;

/**
 * Reference : http://cnx.org/content/m9000/latest/
 *
 * @author arjuns@google.com (Arjun Satyapal)
 */
public enum CnxmlTag {
  DOCUMENT("document", "Document"),
  PARA("para", "Para"),
  RULE("rule", "Rule"),
  SECTION("section", "Section"),
  SUB_FIGURE("subfigure", "Sub Figure"),
  MEDIA("media", "Media"),
  //list (duplicate)
  PREFORMAT("preformat", "PreFormat"),
  BLOCK_CODE("block code", "Block Code"),
  FIGURE("figure", "Figure"),
  BLOCK_NOTES("block notes", "Block Notes"),
  BIBLIOGRAPHY("bib:file", "References"),

  // Additional Tags.
  TITLE("title", "Title"),
  METADATA("metadata", "Metadata"),
  COMMENT("#comment", "Comment"), // for <!-- -->
  CONTENT("content", "Content"),



  //This is a temporary hack.
  TEXT_NO_ATTRS("#text", "Text");

  private String xmlTag;
  private String publicString;

  private CnxmlTag(String xmlTag, String publicString) {
    this.xmlTag = xmlTag;
    this.publicString = publicString;
  }

  public String getXmlTag() {
    return xmlTag;
  }

  public String getPublicString() {
    return publicString;
  }

  public static CnxmlTag getCnxmlTagByXmlTag(String tag) {
    for (CnxmlTag curr : CnxmlTag.values()) {
      if (curr.getXmlTag().equals(tag)) {
        return curr;
      }
    }

    String errorStr = "Invalid tag[" + tag + "]";
    Window.alert(errorStr);
    throw new IllegalArgumentException(errorStr);
  }

  public boolean canHaveChildAttributes() {
    if (this == TEXT_NO_ATTRS ||
        this == COMMENT) {
      return false;
    }

    return true;
  }
}
