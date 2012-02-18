/*
 * Copyright (C) Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.lantern.client.ui.document;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.lantern.client.ServiceProvider;
import com.google.lantern.client.events.HistoryEvent;
import com.google.lantern.client.events.HistoryEventHandler;
import com.google.lantern.client.handlers.GLightCallback;
import com.google.lantern.client.rte.RichTextToolbar;
import com.google.lantern.client.ui.widgets.ShowDocumentWidget;
import com.google.lantern.client.ui.widgets.searchwidgets.SearchDocumentWidget;
import com.google.lantern.client.ui.widgets.searchwidgets.TagWidget;
import com.google.lantern.shared.exceptions.checked.client.searchtagexception.SearchTagException;
import com.google.lantern.shared.objectifyobjects.SearchTags;
import com.google.lantern.shared.objectifyobjects.SearchTags.SearchTagProfile;
import com.google.lantern.shared.objectifyobjects.documents.DocumentEntity;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HStack;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author Arjun Satyapal
 */
public class EditDocument extends VLayout {
    // VStack vStack;
    SearchDocumentWidget searchWidget;
    VLayout container;
    VLayout textEditorLayout;
    DocumentEntity document;
    HStack btnStack;
    private Button btnSave;
    private Button btnCancel;
    RichTextArea richTextArea;
    private RichTextToolbar richTextAreaToolBar;
    TagWidget tagWidget;

    public EditDocument() {
        super();
        setHeight100();
        setWidth100();

        container = new VLayout();

        richTextArea = new RichTextArea();
        richTextArea.setWidth("100%");
        richTextArea.setHeight("100%");

        richTextAreaToolBar = new RichTextToolbar(richTextArea);
        richTextAreaToolBar.setWidth("100%");

        btnStack = new HStack();

        btnStack.setAlign(Alignment.RIGHT);
        btnStack.setWidth100();
        btnStack.setAutoHeight();

        btnSave = new Button("Save");
        btnStack.addMember(btnSave);

        btnCancel = new Button("Cancel");
        btnStack.addMember(btnCancel);

        tagWidget = new TagWidget();

        textEditorLayout = new VLayout();
        textEditorLayout.addMember(btnStack);
        textEditorLayout.addMember(richTextAreaToolBar);
        textEditorLayout.addMember(richTextArea);
        textEditorLayout.addMember(tagWidget);
        textEditorLayout.setShowEdges(true);

        searchWidget = new SearchDocumentWidget(getEditDocumentHandler());
        searchWidget.setHeight100();
        searchWidget.setHeight100();
        container.addMember(searchWidget);

        initButtons();
        addMember(container);
    }

    private AsyncCallback<DocumentEntity> getEditDocumentHandler() {
        return new GLightCallback<DocumentEntity>() {
            public void onSuccess(DocumentEntity result) {
                searchWidget.hide();
                initEditor(result);
                initTags(result);

            }

            private void initEditor(DocumentEntity result) {
                textEditorLayout.show();
                document = result;

                if (result == null) {
                    return;
                }
                SafeHtml safeHtml =
                        new SafeHtmlBuilder().appendHtmlConstant(result.getContent()).toSafeHtml();
                richTextArea.setHTML(safeHtml);
                container.addMember(textEditorLayout);
            }

            private void initTags(DocumentEntity result) {
                tagWidget.setSearchTags(result.getSearchTags());
            }
        };
    }

    private void initButtons() {
        initBtnCancel();
        initBtnSave();
    }

    private void initBtnSave() {
        btnSave.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                SearchTags updatedSearchTags = null;
                try {
                    updatedSearchTags = tagWidget.getSearchTags(SearchTagProfile.ALL_TAGS_MANDATORY);
                } catch (SearchTagException e) {
                    Window.alert("All tags are mandatory.");
                    return;
                } 
                ServiceProvider.getDocumentService().updateDocument(document.getDocumentId(),
                        richTextArea.getHTML(), updatedSearchTags, getCallbackForUpdatePage());
            }
        });
    }

    AsyncCallback<DocumentEntity> getCallbackForUpdatePage() {
        return new GLightCallback<DocumentEntity>() {
            public void onSuccess(DocumentEntity result) {
                textEditorLayout.hide();
                searchWidget.show();
                cleanup();
                new ShowDocumentWidget(result).show();
            }

            private void cleanup() {
                richTextArea.setHTML("");
                richTextArea.setTitle("");
                document = null;
            }
        };
    }

    private void initBtnCancel() {
        btnCancel.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                // TODO(arjuns) : Optimize for cancel.
                redirectToHome();
            }

        });
    }

    void redirectToHome() {
        HistoryEventHandler.triggerEvent(HistoryEvent.AUTHOR_HOME);
    }

}
