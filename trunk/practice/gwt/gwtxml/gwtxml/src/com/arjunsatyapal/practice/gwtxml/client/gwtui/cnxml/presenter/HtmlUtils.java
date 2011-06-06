// Copyright 2011 Google Inc. All Rights Reserved.

package com.arjunsatyapal.practice.gwtxml.client.gwtui.cnxml.presenter;

import static com.arjunsatyapal.practice.gwtxml.client.xmlenums.HtmlTag.BLOCKQUOTE;
import static com.arjunsatyapal.practice.gwtxml.client.xmlenums.HtmlTag.BOLD;
import static com.arjunsatyapal.practice.gwtxml.client.xmlenums.HtmlTag.ITALIC;

import com.google.gwt.user.client.Window;

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

  public static String getItalicString(String string) {
    return getHtmlFormattedString(string, ITALIC);
  }

  public static String getBlockQuoteString(String string) {
    return getHtmlFormattedString(string, BLOCKQUOTE);
  }

  public static String getH1String(String string) {
    return getHtmlFormattedString(string, HtmlTag.H1);
  }

  public static String getEmailString(String emailId, String anchorText) {


    return getHrefString("mailto:" + emailId, anchorText);
  }

  public static String getHrefString(String link, String anchorText) {
    if (anchorText == null || anchorText.isEmpty()) {
      String errString = "href cannot be empty";
      Window.alert(errString);
      throw new IllegalArgumentException(errString);
    }

    if (link == null || link.isEmpty()) {
      String errString = "link cannot be empty.";
      Window.alert(errString);
      throw new IllegalArgumentException(errString);
    }
    return "<a href=\"" + link + "\">" + anchorText + "</a>";
  }

  private static String getHtmlFormattedString(String string, HtmlTag htmlTag) {
    return htmlTag.getStartTag() + string + htmlTag.getEndTag();
  }
}
