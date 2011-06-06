// Copyright 2011 Google Inc. All Rights Reserved.

package com.arjunsatyapal.practice.gwtxml.client.xmlenums;

/**
 * @author arjuns@google.com (Arjun Satyapal)
 *
 */
public enum HtmlTag {
  BOLD("<b>", "</b>"),
  H1("<h1>", "</h1>");

  private String startTag;
  private String endTag;

  private HtmlTag(String startTag, String endTag) {
    this.startTag = startTag;
    this.endTag = endTag;
  }

  public String getStartTag() {
    return startTag;
  }

  public String getEndTag() {
    return endTag;
  }
}
