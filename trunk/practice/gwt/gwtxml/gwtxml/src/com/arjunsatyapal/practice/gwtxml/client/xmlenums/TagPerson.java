// Copyright 2011 Google Inc. All Rights Reserved.

package com.arjunsatyapal.practice.gwtxml.client.xmlenums;

import com.google.gwt.user.client.Window;

/**
 * @author arjuns@google.com (Arjun Satyapal)
 *
 */
public enum TagPerson {
  FIRST_NAME("md:firstname", "First Name"),
  // This maps to surname.
  LAST_NAME("md:surname", "Last Name"),
  FULL_NAME("md:fullname", "Full Name"),
  OTHER_NAME("md:othername", "Other Name"),
  EMAIL("md:email", "Email");

  private String xmlTag;
  private String publicString;

  private TagPerson(String xmlTag, String publicString) {
    this.xmlTag = xmlTag;
    this.publicString = publicString;
  }

  public String getXmlTag() {
    return xmlTag;
  }

  public String getPublicString() {
    return publicString;
  }

  public static TagPerson getTagPersonByXmlTag(String xmlTag) {
    for (TagPerson currTag : TagPerson.values()) {
      if (currTag.getXmlTag().equals(xmlTag)) {
        return currTag;
      }
    }

    String errString = "Invalid xmlTag[" + xmlTag + "].";
    Window.alert(errString);
    throw new IllegalArgumentException(errString);
  }
}
