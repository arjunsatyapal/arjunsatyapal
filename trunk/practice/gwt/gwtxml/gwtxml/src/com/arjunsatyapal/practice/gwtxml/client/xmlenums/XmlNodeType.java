// Copyright 2011 Google Inc. All Rights Reserved.

package com.arjunsatyapal.practice.gwtxml.client.xmlenums;

import com.google.gwt.user.client.Window;

/**
 * Reference : http://www.w3schools.com/Dom/dom_nodetype.asp
 *
 * @author arjuns@google.com (Arjun Satyapal)
 */
public enum XmlNodeType {
  ELEMENT_NODE(1),
  ATTRIBUTE_NODE(2),
  TEXT_NODE(3),
  CDATA_SECTION_NODE(4),
  ENTITY_REFERENCE_NODE(5),
  ENTITY_NODE(6),
  PROCESSING_INSTRUCTION_NODE(7),
  COMMENT_NODE(8),
  DOCUMENT_NODE(9),
  DOCUMENT_TYPE_NODE(10),
  DOCUMENT_FRAGMENT_NODE(11),
  NOTATION_NODE(12);

  private int xmlNodeTypeInt;

  public int getXmlNodeTypeInt() {
    return xmlNodeTypeInt;
  }

  private XmlNodeType(int xmlNodeTypeInt) {
    this.xmlNodeTypeInt = xmlNodeTypeInt;
  }

  public static XmlNodeType getXmlNodeTypeFromInt(int xmlNodeTypeInt) {
    for (XmlNodeType currType : XmlNodeType.values()) {
      if (currType.getXmlNodeTypeInt() == xmlNodeTypeInt) {
        return currType;
      }
    }

    String errorStr = "Invalid xmlNodteTypeInt value[" + xmlNodeTypeInt + "].";
    Window.alert(errorStr);
    throw new IllegalArgumentException(errorStr);
  }
}
