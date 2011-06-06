// Copyright 2011 Google Inc. All Rights Reserved.

package com.arjunsatyapal.practice.gwtxml.client.xmlenums;

import com.google.gwt.user.client.Window;

/**
 * @author arjuns@google.com (Arjun Satyapal)
 *
 */
public enum TagDocumentAttribute {
  XMLNS("xmlns", "Primary Name Space"),
  MDML("xmlns:md", "MDML Name Space"),
  MATHML("xmlns:m", "MathML"),
  BIBLIOGRAPY_NS("xmlns:bib", "Bibliography Name Space"),
  QML("xmlns:q", "QML"),
  ID("id", "ID"),
  MODULE_ID("module-id", "Module Id"),
  CNXML_VERSION("cnxml-version", "CNXML Version");

  private String attributeName;

  private String publicString;

  private TagDocumentAttribute(String attributeName, String publicString) {
    this.attributeName = attributeName;
    this.publicString = publicString;
  }

  public String getAttributeName() {
    return attributeName;
  }

  public String getPublicString() {
    return publicString;
  }

  public static TagDocumentAttribute getTagDocumentAttributeFromAttributeName(String attributeName) {
    for (TagDocumentAttribute curr : TagDocumentAttribute.values()) {
      if (curr.getAttributeName().equals(attributeName)) {
        return curr;
      }
    }

    String errorStr = "Invalid attributeName[" + attributeName + "].";
    Window.alert(errorStr);
    throw new IllegalArgumentException(errorStr);
  }
}
