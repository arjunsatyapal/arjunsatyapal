// Copyright 2011 Google Inc. All Rights Reserved.

package com.arjunsatyapal.practice.gwtxml.client.xmlenums;

import com.google.gwt.user.client.Window;

/**
 * Reference : http://cnx.org/content/m9000/latest/
 *
 * @author arjuns@google.com (Arjun Satyapal)
 */
public enum CnxmlTag {
  DOCUMENT("document", false),
  PARA("para", false),
  RULE("rule", false),
  SECTION("section", false),
  SUB_FIGURE("subfigure", false),
  MEDIA("media", false),
  //list (duplicate)
  PREFORMAT("preformat", false),
  BLOCK_CODE("block code", false),
  FIGURE("figure", false),
  BLOCK_NOTES("block notes", false),

  // Additional Tags.
  TITLE("title", false),
  METADATA("metadata", false),
  COMMENT("#comment", false), // for <!-- -->
  CONTENT("content", false),

  //This is a temporary hack.
  TEXT_NO_ATTRS("#text", false);

  private String tag;
  private boolean enabled;

  private CnxmlTag(String tag, boolean enabled) {
    this.tag = tag;
    this.enabled = enabled;
  }

  public String getTag() {
    return tag;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public static CnxmlTag getCnxmlTagByString(String tag) {
    for (CnxmlTag curr : CnxmlTag.values()) {
      if (curr.getTag().equals(tag)) {
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
