// Copyright 2011 Google Inc. All Rights Reserved.

package com.arjunsatyapal.practice.gwtxml.client.xmlenums;

import com.google.gwt.user.client.Window;

/**
 * @author arjuns@google.com (Arjun Satyapal)
 *
 */
public enum TagTypeSetting {
  EQUATION("equation"),
  LIST("list"),
  PROBLEM("problem"),
  MEANING("meaning"),
  PROOF("proof"),
  DEFINITION("definition"),
  EXERCISE("exercise"),
  TABLE("table"),
  DIV("div"),
  EXAMPLE("example"),
  FOOT_NOTE("footnote"),
  SOLUTION("solution"),
  BLOCK_QUOTES("block quotes"),
  EMPHASIS("emphasis"),
  TERM("term"),
  NOTE("note"),
  LINK("link"),
  CITE("cite"),
  QUOTE("quote"),
  FOREIGN("foreign"),
  PREFORMAT("preformat"),

  // TODO(arjuns) : Probably combine text and comment in parent enum.
  TEXT("#text"),
  COMMENT("#comment");

  private String xmlTag;

  private TagTypeSetting(String xmlTag) {
    this.xmlTag = xmlTag;
  }

  public String getXmlTag() {
    return xmlTag;
  }

  public static TagTypeSetting getTagTypeSettingByXmlTag(String xmlTag) {
    for (TagTypeSetting curr : TagTypeSetting.values()) {
      if (curr.getXmlTag().equals(xmlTag)) {
        return curr;
      }
    }

    String errString = "Invalid xmlTag[" + xmlTag + "].";
    Window.alert(errString);
    throw new IllegalArgumentException(errString);
  }

}
