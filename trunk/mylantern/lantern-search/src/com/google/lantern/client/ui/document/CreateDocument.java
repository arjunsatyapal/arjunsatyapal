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

import com.google.lantern.shared.exceptions.checked.client.searchtagexception.AllSearchTagRequired;
import com.google.lantern.shared.exceptions.checked.client.searchtagexception.SearchTagException;

import com.google.lantern.shared.enums.select.DocumentType;

import com.google.common.base.Strings;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.google.lantern.client.ServiceProvider;
import com.google.lantern.client.events.GwtClientSideExceptionHandler;
import com.google.lantern.client.events.HistoryEvent;
import com.google.lantern.client.events.HistoryEventHandler;
import com.google.lantern.client.handlers.GLightCallback;
import com.google.lantern.client.ui.widgets.searchwidgets.TagWidget;
import com.google.lantern.shared.objectifyobjects.SearchTags;
import com.google.lantern.shared.objectifyobjects.SearchTags.SearchTagProfile;
import com.google.lantern.shared.objectifyobjects.documents.DocumentEntity;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HStack;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VStack;

/**
 * 
 * @author Arjun Satyapal
 */
@SuppressWarnings("synthetic-access")
public class CreateDocument extends Layout {
    private VStack vStack;
    private TextBox tbPageTitle;
    private Button btnValidate;
    private Button btnCreatePage;
    private Button btnCancel;
    private TagWidget tagWidget;

    public CreateDocument() {
        super();
        vStack = new VStack();
        vStack.setSize("100%", "100%");
        vStack.setDefaultLayoutAlign(Alignment.CENTER);

        Label lblPageTitle = new Label("Page Title");
        lblPageTitle.setAutoFit(true);
        lblPageTitle.setWrap(false);
        lblPageTitle.setAlign(Alignment.CENTER);
        vStack.addMember(lblPageTitle);

        tbPageTitle = new TextBox();
        tbPageTitle.setMaxLength(100);
        tbPageTitle.setAlignment(TextAlignment.CENTER);
        tbPageTitle.setWidth("100%");

        Layout textBoxWrapper = new Layout();
        textBoxWrapper.setWidth("80%");
        textBoxWrapper.setAlign(Alignment.CENTER);
        textBoxWrapper.addMember(getTbTitle());
        textBoxWrapper.setAutoHeight();
        vStack.addMember(textBoxWrapper);

        HStack btnStack = new HStack();

        btnValidate = new Button("Validate");
        btnStack.addMember(btnValidate);

        btnCreatePage = new Button("Create Page");
        btnStack.addMember(btnCreatePage);

        btnCancel = new Button("Cancel");
        btnStack.addMember(btnCancel);

        btnStack.setWidth100();
        btnStack.setAutoHeight();
        btnStack.setAlign(Alignment.CENTER);
        vStack.addMember(btnStack);

        tagWidget = new TagWidget();
        vStack.addMember(tagWidget);

        this.addMember(vStack);

        initButton();
    }

    private void initButton() {
        initBtnValidate();
        initBtnCreatePage();
        initBtnCancel();
    }

    private void initBtnValidate() {
        btnValidate.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                // ServiceProvider.getSitesService().getSites(getSitesCallback());
                Window.alert("Not yet implemented. btnValidate");
            }

            // private AsyncCallback<List<String>> getSitesCallback() {
            // return new AsyncCallback<List<String>>() {
            //
            // public void onFailure(Throwable caught) {
            // Window.alert("Failed to get list of sites from server due to : "
            // + caught.getLocalizedMessage());
            //
            // }
            //
            // public void onSuccess(List<String> result) {
            // StringBuilder strBuilder = new StringBuilder(Iterables.toString(result));
            // Window.alert(strBuilder.toString());
            // }
            // };
            // }
        });
    }

    private void initBtnCancel() {
        btnCancel.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                // TODO(arjuns) : Optimize for cancel.
                HistoryEventHandler.triggerEvent(HistoryEvent.AUTHOR_HOME);
            }
        });
    }

    private void initBtnCreatePage() {
        btnCreatePage.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                final String pageTitle = tbPageTitle.getText();

                if (Strings.isNullOrEmpty(pageTitle)) {
                    Window.alert("Please Enter a Title for Page.");
                    return;
                }

                SearchTags searchTags = null;
                try {
                    searchTags = tagWidget.getSearchTags(SearchTagProfile.ALL_TAGS_MANDATORY);
                } catch (AllSearchTagRequired e) {
                    Window.alert("All search tags need to be set.");
                    return;
                } catch (SearchTagException e) {
                    GwtClientSideExceptionHandler.handleException(e);
                }
                ServiceProvider
                        .getDocumentService()
                        .createDocument(
                                DocumentType.GOOGLE_SITES,
                                pageTitle,
                                "Hello World", searchTags,
                                getCreatePageCallback());
            }

            private AsyncCallback<DocumentEntity> getCreatePageCallback() {
                return new GLightCallback<DocumentEntity>() {
                    public void onSuccess(DocumentEntity result) {
                        HistoryEventHandler.triggerEvent(HistoryEvent.AUTHOR_HOME);
                    }
                };
            }
        });
    }

    public TextBox getTbTitle() {
        return tbPageTitle;
    }
}
