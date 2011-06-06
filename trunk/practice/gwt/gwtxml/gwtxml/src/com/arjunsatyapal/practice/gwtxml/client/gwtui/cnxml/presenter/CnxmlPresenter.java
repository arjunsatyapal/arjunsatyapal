// Copyright 2011 Google Inc. All Rights Reserved.

package com.arjunsatyapal.practice.gwtxml.client.gwtui.cnxml.presenter;

import static com.arjunsatyapal.practice.gwtxml.client.gwtui.cnxml.presenter.HtmlUtils.getBoldString;
import static com.arjunsatyapal.practice.gwtxml.client.xmlenums.TagMetadata.CREATED;
import static com.arjunsatyapal.practice.gwtxml.client.xmlenums.TagMetadata.REVISED;
import static com.arjunsatyapal.practice.gwtxml.client.xmlenums.TagMetadata.VERSION;
import static com.arjunsatyapal.practice.gwtxml.client.xmlenums.TagPersonCategory.getTagPersonCategoryByXmlTag;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.xml.client.DOMException;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.NamedNodeMap;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;

import com.arjunsatyapal.practice.gwtxml.client.gwtui.mvpinterfaces.Presenter;
import com.arjunsatyapal.practice.gwtxml.client.pojos.Person;
import com.arjunsatyapal.practice.gwtxml.client.xmlenums.CnxmlTag;
import com.arjunsatyapal.practice.gwtxml.client.xmlenums.TagDocumentAttribute;
import com.arjunsatyapal.practice.gwtxml.client.xmlenums.TagMetadata;
import com.arjunsatyapal.practice.gwtxml.client.xmlenums.TagPerson;
import com.arjunsatyapal.practice.gwtxml.client.xmlenums.TagPersonCategory;

import java.util.ArrayList;

import javax.jdo.metadata.Metadata;


/**
 * @author arjuns@google.com (Arjun Satyapal)
 *
 */
public class CnxmlPresenter extends Presenter {
  private CnxmlDisplay display;
  private String cnxml;

  public CnxmlPresenter(CnxmlDisplay display, String historyToken, String cnxml) {
    super(historyToken);
    this.display = display;
    this.cnxml = cnxml;
  }

  @Override
  public void go(HasWidgets container) {
    container.clear();
    container.add(display.asWidget());
    bind();
  }

  @Override
  public void bind() {
    try {
      Document messageDom = XMLParser.parse(cnxml);
      NodeList nodeList = messageDom.getChildNodes();
      for (int nodeIndex = 0; nodeIndex < nodeList.getLength(); nodeIndex++) {
        handleCurrentNode(display.getLayoutPanel(), nodeList.item(nodeIndex));
      }
    } catch (DOMException e) {
      Window.alert("Could not parse XML Document due to : " + e
          .getLocalizedMessage());
    }
  }

  private void handleCurrentNode(Panel panel, Node node) {
    CnxmlTag tag = CnxmlTag.getCnxmlTagByString(node.getNodeName());
    String temp = node.getNodeValue();
    switch (tag) {
      case DOCUMENT:
        handleDocumentNode(panel, node);
        break;
      // case TEXT_NO_ATTRS :
      // handleTextNode(panel, node);
      // break;

      case METADATA:
        handleMetaData(panel, node);
        break;
      case TITLE:
        handleTitleNode(panel, node);
      default:
        // TODO(arjuns) : Add exception later.
        break;
    }
  }

  private void handleMetaData(Panel panel, Node node) {
    NodeList childList = node.getChildNodes();

    VerticalPanel metaDataPanel = new VerticalPanel();

    for (int childIndex = 0; childIndex < childList.getLength(); childIndex++) {
      Node childNode = childList.item(childIndex);
      TagMetadata metadataTag = TagMetadata.getTagMetadataByXmlTag(childNode
          .getNodeName());

      switch (metadataTag) {
        case CONTENT_ID:
          // do nothing.
          break;
        case TITLE:
          // Already printed as part of the title tag. So ignoring it now.
          // TODO(arjuns) : Check if this can be different from the title tag.
          break;

        case VERSION:
          String versionText = getBoldString(VERSION.getPublicString()) + " : " + childNode
              .getFirstChild().getNodeValue();
          metaDataPanel.add(new HTML(versionText));
          break;

        case CREATED:
          String creationText = getBoldString(CREATED.getPublicString()) + " : " + childNode
              .getFirstChild().getNodeValue();
          metaDataPanel.add(new HTML(creationText));
          break;

        case REVISED:
          String revisedText = getBoldString(REVISED.getPublicString()) + " : " + childNode
              .getFirstChild().getNodeValue();
          metaDataPanel.add(new HTML(revisedText));
          break;

        case AUTHOR_LIST:
          handlePersonsList(panel, childNode, "Author");
          break;

        case MAINTAINER_LIST:
          handlePersonsList(panel, childNode, "Maintainer");
          break;
        default:
          // TODO(arjuns) : Add exception here.
          break;
      }
    }

    panel.add(metaDataPanel);
  }

  private void handlePersonsList(Panel panel, Node authorListNode,
      String personCategory) {
    NodeList authorList = authorListNode.getChildNodes();

    ArrayList<Person> persons = new ArrayList<Person>();

    for (int authorIndex = 0; authorIndex < authorList.getLength(); authorIndex++) {
      Node currAuthor = authorList.item(authorIndex);
      TagPersonCategory category = getTagPersonCategoryByXmlTag(currAuthor
          .getNodeName());

      switch (category) {
        case TEXT:
          // do nothing.
          break;

        case AUTHOR:
        case MAINTAINER:
          // TODO(arjuns) : Taking shortcut for the Person.
          Person person = getPersonFromNode(currAuthor);
          persons.add(person);
          break;



      }
    }

    StringBuilder htmlStringBuilder = new StringBuilder(
        getBoldString(personCategory + " : "));

    for (Person currAuthor : persons) {
      htmlStringBuilder.append(
          HtmlUtils.getEmailString(currAuthor.getEmail(),
              currAuthor.getFirstName() + " " + currAuthor.getLastName()))
          .append(" ");
    }

    panel.add(new HTML(htmlStringBuilder.toString()));
  }

  private Person getPersonFromNode(Node personNode) {
    String id = personNode.getAttributes().item(0).getNodeValue();
    Person.Builder builder = new Person.Builder();
    builder.setId(id);

    NodeList nodeList = personNode.getChildNodes();
    for (int childIndex = 0; childIndex < nodeList.getLength(); childIndex++) {
      Node currNode = nodeList.item(childIndex);
      TagPerson tag = TagPerson.getTagPersonByXmlTag(currNode.getNodeName());

      switch (tag) {
        case TEXT:
          // do nothing.
          break;
        case FIRST_NAME:
          if (currNode.hasChildNodes()) {
            builder.setFirstName(currNode.getFirstChild().getNodeValue());
          }
          break;
        case LAST_NAME:
          if (currNode.hasChildNodes()) {
            builder.setLastName(currNode.getFirstChild().getNodeValue());
          }
          break;

        case FULL_NAME:
          if (currNode.hasChildNodes()) {
            builder.setFullName(currNode.getFirstChild().getNodeValue());
          }
          break;

        case EMAIL:
          if (currNode.hasChildNodes()) {
            builder.setEmail(currNode.getFirstChild().getNodeValue());
          }
          break;

        case OTHER_NAME:
          if (currNode.hasChildNodes()) {
            builder.setOtherName(currNode.getFirstChild().getNodeValue());
          }
          break;

        default:
          Window.alert("Illegal tag : " + tag);
      }
    }

    return builder.build();
  }

  // // Person person = builder.

  private void handleTitleNode(Panel panel, Node node) {
    Node title = node.getFirstChild();
    String htmlText = HtmlUtils.getH1String(title.getNodeValue());
    panel.add(new HTML(htmlText));
  }

  private void handleTextNode(Panel panel, Node node) {
    // Window.alert(node.toString());
  }

  private void handleDocumentNode(Panel panel, Node documentNode) {
    VerticalPanel documentPanel = new VerticalPanel();

    NamedNodeMap attrs = documentNode.getAttributes();
    for (int attrIndex = 0; attrIndex < attrs.getLength(); attrIndex++) {
      Node currAttr = attrs.item(attrIndex);
      TagDocumentAttribute attribute = TagDocumentAttribute
          .getTagDocumentAttributeFromAttributeName(currAttr.getNodeName());
      String labelText = getBoldString(attribute.getPublicString()) + " : " + currAttr
          .getNodeValue();
      HTML html = new HTML(labelText);
      documentPanel.add(html);
    }
    panel.add(documentPanel);

    if (documentNode.hasChildNodes()) {
      NodeList childList = documentNode.getChildNodes();

      for (int childIndex = 0; childIndex < childList.getLength(); childIndex++) {
        VerticalPanel childPanel = new VerticalPanel();
        Node currChild = childList.item(childIndex);

        handleCurrentNode(childPanel, childList.item(childIndex));
        documentPanel.add(childPanel);
      }
    }
  }
}
// private void handleCurrentLevel(int currLevel, StringBuilder builder,
// NodeList nodeList) {
// if (currLevel > 2) {
// return;
// }
//
// StringBuilder prefixBuilder = new StringBuilder("");
//
// for (int i = 0; i < currLevel; i++) {
// prefixBuilder.append("\t");
// }
//
// String spacePrefix = prefixBuilder.toString();
// for (int nodeIndex = 0; nodeIndex < nodeList.getLength(); nodeIndex++) {
// Node currNode = nodeList.item(nodeIndex);
//
// builder.append("\n").append(spacePrefix).append("Level = ")
// .append(currLevel).append(" NodeName = ")
// .append(currNode.getNodeName());
// builder.append(" NodeType = ").append(currNode.getNodeType());
// builder.append(" NodeValue = ").append(currNode.getNodeValue());
//
// String temp = currNode.getNodeName();
// CnxmlTag tag = CnxmlTag.getCnxmlTagByString(currNode.getNodeName());
// if (tag.canHaveChildAttributes() && currNode.hasAttributes()) {
// NamedNodeMap attrs = currNode.getAttributes();
//
// if (attrs != null) {
// for (int attrIndex = 0; attrIndex < attrs.getLength(); attrIndex++) {
// Node currAttr = attrs.item(attrIndex);
// builder.append(" attr[").append(attrIndex).append("] : ")
// .append(currAttr.getNodeName()).append("[")
// .append(currAttr.getNodeValue()).append("],");
// }
// }
// }
//
// if (currNode.hasChildNodes()) {
// handleCurrentLevel(currLevel + 1, builder, currNode.getChildNodes());
// }
// }
// }
