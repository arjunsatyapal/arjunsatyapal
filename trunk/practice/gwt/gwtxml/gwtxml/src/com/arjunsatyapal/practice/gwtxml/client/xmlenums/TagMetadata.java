// Copyright 2011 Google Inc. All Rights Reserved.

package com.arjunsatyapal.practice.gwtxml.client.xmlenums;

import com.google.gwt.user.client.Window;

/**
 * @author arjuns@google.com (Arjun Satyapal)
 *
 */
public enum TagMetadata {
  CONTENT_ID("md:content-id", "Content ID"),
  TITLE("md:title", "Title"),
  VERSION("md:version", "Version"),
  CREATED("md:created", "Creation Date"),
  REVISED("md:revised", "Last Modified"),
  ACTOR("md:actor", "Actor"),
  ROLES("md:roles", "Roles"),
  AUTHOR_LIST("md:authorlist", "Author List"),
  LICENSOR_LIST("md:licensorlist", "Licensor List"),
  MAINTAINER_LIST("md:maintainerlist", "Maintainer List"),
  LICENSE("md:license", "License"),
  ACTORS("md:actors", "Actors"),

  KEYWORD_LIST("md:keywordlist", "Keyword List"),
  SUBJECT_LIST("md:subjectlist", "Subject List"),
  ABSTRACT("md:abstract", "Abstract"),
  LANGUAGE("md:language", "Language"),
  REPOSITORY("md:repository", "Repository"),
  CONTENT_URL("md:content-url", "Content URL"),

  // Text
  // TODO(arjuns) : Probably combine text and comment in parent enum.
  TEXT("#text", "Text"),
  COMMENT("#comment", "Comment");

  private String xmlTag;
  private String publicString;

  private TagMetadata(String xmlTag, String publicString) {
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
