// Copyright 2011 Google Inc. All Rights Reserved.

package com.arjunsatyapal.practice.gwtxml.client.gwtui.cnxml.presenter;

import static com.arjunsatyapal.practice.gwtxml.client.gwtui.cnxml.presenter.HtmlUtils.getBlockQuoteString;
import static com.arjunsatyapal.practice.gwtxml.client.gwtui.cnxml.presenter.HtmlUtils.getBoldString;
import static com.arjunsatyapal.practice.gwtxml.client.gwtui.cnxml.presenter.HtmlUtils.getH1String;
import static com.arjunsatyapal.practice.gwtxml.client.xmlenums.CnxmlTag.BIBLIOGRAPHY;
import static com.arjunsatyapal.practice.gwtxml.client.xmlenums.TagContent.getTagContentByXmlTag;
import static com.arjunsatyapal.practice.gwtxml.client.xmlenums.TagMetadata.ABSTRACT;
import static com.arjunsatyapal.practice.gwtxml.client.xmlenums.TagMetadata.CREATED;
import static com.arjunsatyapal.practice.gwtxml.client.xmlenums.TagMetadata.KEYWORD_LIST;
import static com.arjunsatyapal.practice.gwtxml.client.xmlenums.TagMetadata.REVISED;
import static com.arjunsatyapal.practice.gwtxml.client.xmlenums.TagMetadata.SUBJECT_LIST;
import static com.arjunsatyapal.practice.gwtxml.client.xmlenums.TagMetadata.VERSION;
import static com.arjunsatyapal.practice.gwtxml.client.xmlenums.TagPersonCategory.getTagPersonCategoryByXmlTag;
import static com.arjunsatyapal.practice.gwtxml.client.xmlenums.TagTypeSetting.getTagTypeSettingByXmlTag;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.xml.client.DOMException;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.NamedNodeMap;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;

import com.arjunsatyapal.practice.gwtxml.client.gwtui.mvpinterfaces.Presenter;
import com.arjunsatyapal.practice.gwtxml.client.pojos.Book;
import com.arjunsatyapal.practice.gwtxml.client.pojos.Person;
import com.arjunsatyapal.practice.gwtxml.client.xmlenums.CnxmlTag;
import com.arjunsatyapal.practice.gwtxml.client.xmlenums.TagBibliographyEntry;
import com.arjunsatyapal.practice.gwtxml.client.xmlenums.TagBibliographyFile;
import com.arjunsatyapal.practice.gwtxml.client.xmlenums.TagBook;
import com.arjunsatyapal.practice.gwtxml.client.xmlenums.TagContent;
import com.arjunsatyapal.practice.gwtxml.client.xmlenums.TagDocumentAttribute;
import com.arjunsatyapal.practice.gwtxml.client.xmlenums.TagMetadata;
import com.arjunsatyapal.practice.gwtxml.client.xmlenums.TagPerson;
import com.arjunsatyapal.practice.gwtxml.client.xmlenums.TagPersonCategory;
import com.arjunsatyapal.practice.gwtxml.client.xmlenums.TagTypeSetting;
import com.arjunsatyapal.practice.gwtxml.client.xmlenums.XmlNodeType;

import java.util.ArrayList;


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
    CnxmlTag tag = CnxmlTag.getCnxmlTagByXmlTag(node.getNodeName());
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
      case CONTENT:
        handleContent(panel, node);
        break;
      case BIBLIOGRAPHY:
        panel.add(new HTML(getH1String(BIBLIOGRAPHY.getPublicString())));
        handleBibliographyFile(panel, node);
        break;
      default:
        // TODO(arjuns) : Add exception later.
        break;
    }
  }

  private void handleContent(Panel panel, Node node) {
    NodeList childNodes = node.getChildNodes();
    for (int childIndex = 0; childIndex < childNodes.getLength(); childIndex++) {
      handleContentChilds(panel, childNodes.item(childIndex));
    }
  }

  private void handleContentChilds(Panel panel, Node node) {
    TagContent tag = TagContent.getTagContentByXmlTag(node.getNodeName());

    switch (tag) {
      case TEXT:
        // do nothing.
        break;
      case PARA:
        String paraString = handlePara(node);
        paraString += "<p>";
        panel.add(new HTML(paraString));
        break;

      case QUOTE:
        String quoteString = handleSection(node);
        quoteString += "<p>";
        panel.add(new HTML(HtmlUtils.getBlockQuoteString(quoteString)));
        break;

      case SECTION:
        String sectionString = handleSection(node);
        sectionString += "<p>";
        panel.add(new HTML(sectionString));
        break;


    }
  }

  private void handleBibliographyFile(Panel panel, Node node) {
    NodeList nodeList = node.getChildNodes();
    for (int childIndex = 0; childIndex < nodeList.getLength(); childIndex++) {
      Node childNode = nodeList.item(childIndex);
      TagBibliographyFile tag = TagBibliographyFile
          .getTagBibliographyFileByXmlTag(childNode.getNodeName());

      switch (tag) {
        case TEXT:
          // do nothing.
          break;

        case ENTRY:
          // TODO(arjuns) : Convert this to string Builder.
          handleBibliographyEntry(panel, childNode);
          break;

        default:
          String errString = "Invalid tag type[" + tag + "] for TagBibliographyFile";
          Window.alert(errString);
          throw new IllegalArgumentException(errString);
      }
    }
  }


  private void handleBibliographyEntry(Panel panel, Node node) {
    NodeList nodeList = node.getChildNodes();

    StringBuilder bibBuilder = new StringBuilder();
    bibBuilder.append("<p>");

    int counter = 0;
    for (int childIndex = 0; childIndex < nodeList.getLength(); childIndex++) {
      Node childNode = nodeList.item(childIndex);
      TagBibliographyEntry tag = TagBibliographyEntry
          .getTagBibliographyEntryByXmlTag(childNode.getNodeName());

      switch (tag) {
        case TEXT:
          // do nothing.
          break;

        case BOOK:
          // TODO(arjuns) : This will change as more bibliography nodes will be
          // added.
          Book book = getBookFromNode(childNode);
          bibBuilder.append(++counter).append(".").append(book.getHtmlString());
          break;
      }
    }

    panel.add(new HTML(bibBuilder.toString()));
  }

  private Book getBookFromNode(Node bookNode) {
    Book.Builder builder = new Book.Builder();

    NodeList nodeList = bookNode.getChildNodes();
    for (int childIndex = 0; childIndex < nodeList.getLength(); childIndex++) {
      Node currNode = nodeList.item(childIndex);
      TagBook tag = TagBook.getTagPersonByXmlTag(currNode.getNodeName());

      switch (tag) {
        case ADDRESS:
          if (currNode.hasChildNodes()) {
            builder.setAddress(currNode.getFirstChild().getNodeValue());
          }
          break;

        case AUTHOR:
          if (currNode.hasChildNodes()) {
            builder.setAuthor(currNode.getFirstChild().getNodeValue());
          }
          break;

        case TITLE:
          if (currNode.hasChildNodes()) {
            builder.setTitle(currNode.getFirstChild().getNodeValue());
          }
          break;

        case EDITION:
          if (currNode.hasChildNodes()) {
            builder.setEdition(currNode.getFirstChild().getNodeValue());
          }
          break;

        case MONTH:
          if (currNode.hasChildNodes()) {
            builder.setMonth(currNode.getFirstChild().getNodeValue());
          }
          break;

        case NOTE:
          if (currNode.hasChildNodes()) {
            builder.setNote(currNode.getFirstChild().getNodeValue());
          }
          break;

        case PUBLISHER:
          if (currNode.hasChildNodes()) {
            builder.setPublisher(currNode.getFirstChild().getNodeValue());
          }
          break;

        case SERIES:
          if (currNode.hasChildNodes()) {
            builder.setSeries(currNode.getFirstChild().getNodeValue());
          }
          break;

        case VOLUME:
          if (currNode.hasChildNodes()) {
            builder.setVolume(currNode.getFirstChild().getNodeValue());
          }
          break;

        case YEAR:
          if (currNode.hasChildNodes()) {
            builder.setYear(currNode.getFirstChild().getNodeValue());
          }
          break;

        case TEXT:
        case COMMENT:
          // do nothing.
          break;

        default:
          String errString = "Invalid tag[" + tag + "].";
          Window.alert(errString);
          throw new IllegalArgumentException(errString);
      }
    }

    return builder.build();
  }

  private String handleSection(Node node) {
    NodeList childNodes = node.getChildNodes();

    StringBuilder htmlStringBuilder = new StringBuilder();
    for (int childIndex = 0; childIndex < childNodes.getLength(); childIndex++) {
      Node child = childNodes.item(childIndex);
      TagContent tag = getTagContentByXmlTag(child.getNodeName());

      switch (tag) {
        case SECTION:
          htmlStringBuilder.append(handleSection(child));
          break;

        case PARA:
          htmlStringBuilder.append(handlePara(child));
          break;

        case TITLE:
          htmlStringBuilder.append(getH1String(child.getFirstChild()
              .getNodeValue()));
          htmlStringBuilder.append("<p><p>");
          break;

        case QUOTE:
          // TODO(arjuns) : Random hack having single quote.
          NodeList nodeList = child.getChildNodes();

          StringBuilder quoteStringBuilder = new StringBuilder("<p>");
          for (int quoteChildIndex = 0; quoteChildIndex < nodeList.getLength(); quoteChildIndex++) {
            Node childNode = nodeList.item(quoteChildIndex);
            TagContent tagContent = TagContent.getTagContentByXmlTag(childNode
                .getNodeName());
            switch (tagContent) {
              case SECTION:
                quoteStringBuilder.append(handleSection(childNode));
                break;
              case PARA:
                quoteStringBuilder.append(handlePara(childNode));
                break;
              case TEXT:
              case PREFORMAT:
                // do nothing;
                break;
              default:
                String errMsg = "Unexpected tag : " + tagContent + " inside quote.";
                Window.alert(errMsg);
            }

            quoteStringBuilder.append("<p>");

          }
          String quoteString = getBlockQuoteString(quoteStringBuilder
              .toString());
          htmlStringBuilder.append(quoteString);
          break;
      }
    }

    return htmlStringBuilder.toString();
  }

  private String handlePara(Node node) {
    NodeList childNodes = node.getChildNodes();

    StringBuilder htmlStringBuilder = new StringBuilder();
    for (int childIndex = 0; childIndex < childNodes.getLength(); childIndex++) {
      Node child = childNodes.item(childIndex);
      TagTypeSetting tag = getTagTypeSettingByXmlTag(child.getNodeName());

      switch (tag) {
        case TEXT:
          htmlStringBuilder.append(child.getNodeValue());
          break;

        case EMPHASIS:
          String strToEmphasize = handlePara(child);
          htmlStringBuilder.append(getBoldString(strToEmphasize));
          break;

        case QUOTE:
          String quoteString = handlePara(child);
          htmlStringBuilder.append(getBlockQuoteString(quoteString));

        default:
          // TODO(arjuns) : Add exception here;
          break;
      }
    }

    return htmlStringBuilder.toString();
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
        case LICENSOR_LIST:
          handlePersonsList(panel, childNode, "Licensor");
          break;

        case LICENSE:
          handleLicense(panel, childNode);
          break;

        case KEYWORD_LIST:
          handleGenericList(panel, childNode, KEYWORD_LIST.getPublicString());
          break;

        case SUBJECT_LIST:
          handleGenericList(panel, childNode, SUBJECT_LIST.getPublicString());
          break;

        case ABSTRACT:
          String abstractText = getBoldString(ABSTRACT.getPublicString()) + " : " + childNode
              .getFirstChild().getNodeValue();
          metaDataPanel.add(new HTML(abstractText));
          break;
        default:
          // TODO(arjuns) : Add exception here.
          break;
      }
    }

    panel.add(metaDataPanel);
  }

  private void handleGenericList(Panel panel, Node node, String typeOfList) {
    StringBuilder builder = new StringBuilder(getBoldString(typeOfList + " : "));
    NodeList childList = node.getChildNodes();
    for (int childIndedx = 0; childIndedx < childList.getLength(); childIndedx++) {
      Node childNode = childList.item(childIndedx);

      if (childNode.getNodeType() == XmlNodeType.TEXT_NODE.getXmlNodeTypeInt()) {
        continue;
      }

      builder.append("\"").append(childNode.getFirstChild().getNodeValue())
          .append("\" ");
    }

    panel.add(new HTML(builder.toString()));
  }

  private void handleLicense(Panel panel, Node licenseNode) {
    // TODO(arjuns) : Taking shortcut and selecting directly first item.
    NamedNodeMap licenseAttr = licenseNode.getAttributes();
    Node licenseLinkNode = licenseAttr.getNamedItem("href");
    String htmlString = getBoldString("License : ") + HtmlUtils.getHrefString(
        licenseLinkNode.getNodeValue(), "Creative Commons License");

    panel.add(new HTML(htmlString));
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
        case LICENSOR:
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
