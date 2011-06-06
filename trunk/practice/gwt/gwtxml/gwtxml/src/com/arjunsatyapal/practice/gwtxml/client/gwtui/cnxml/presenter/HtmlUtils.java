// Copyright 2011 Google Inc. All Rights Reserved.

package com.arjunsatyapal.practice.gwtxml.client.gwtui.cnxml.presenter;

import static com.arjunsatyapal.practice.gwtxml.client.xmlenums.HtmlTag.BOLD;

import com.arjunsatyapal.practice.gwtxml.client.xmlenums.HtmlTag;

/**
 * @author arjuns@google.com (Arjun Satyapal)
 *
 */
public class HtmlUtils {
  // Utility class.
  private HtmlUtils() {
  }

  public static String getBoldString(String string) {
    return getHtmlFormattedString(string, BOLD);
  }

  public static String getH1String(String string) {
    return getHtmlFormattedString(string, HtmlTag.H1);
  }

  public static String getEmailString(String emailId, String href) {
    if (href == null || href.isEmpty()) {
      throw new IllegalArgumentException("href cannot be empty");
    }

    return "<a href=\"mailto:" + emailId + "\">" + href + "</a>";
  }


  private static String getHtmlFormattedString(String string, HtmlTag htmlTag) {
    return htmlTag.getStartTag() + string + htmlTag.getEndTag();
  }
}
