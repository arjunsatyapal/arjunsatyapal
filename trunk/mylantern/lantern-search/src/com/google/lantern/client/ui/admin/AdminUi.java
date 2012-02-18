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
package com.google.lantern.client.ui.admin;

import com.google.gwt.user.client.Window;

import com.google.lantern.client.handlers.GLightCallback;

import com.google.gwt.user.client.rpc.AsyncCallback;

import com.google.lantern.client.ServiceProvider;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HStack;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.layout.VStack;
import com.smartgwt.client.widgets.tab.Tab;
import java.util.List;

/**
 * 
 * @author Arjun Satyapal
 */
public class AdminUi extends Layout {
    private static final int cmdStackWidth = 10;
    private static final int workAreaWidth = 100 - cmdStackWidth;

    private HStack documentScreen;

    private VStack commandStack;
    private VLayout workArea;

    private Button btnDeleteAll;
    Button btnDeleteDocument;

    List<Button> listOfButtons;

    public AdminUi(Tab documentTab) {
        documentScreen = new HStack();

        initCommandStack();

        btnDeleteAll = new Button("Delete All");
        btnDeleteAll.setWidth100();
        commandStack.addMember(btnDeleteAll);

        btnDeleteDocument = new Button("Delete Document");
        btnDeleteDocument.setWidth100();
        commandStack.addMember(btnDeleteDocument);

        initButtons();
        createNewWorkArea(null);
        documentTab.setPane(documentScreen);
    }

    private void initCommandStack() {
        commandStack = new VStack();
        commandStack.setShowEdges(true);
        commandStack.setHeight100();
        commandStack.setWidth(cmdStackWidth + "%");

        Label cmdStackTitle = new Label("Select Command");
        cmdStackTitle.setAutoHeight();
        cmdStackTitle.setAlign(Alignment.CENTER);
        commandStack.addMember(cmdStackTitle);

        documentScreen.addMember(commandStack);
    }

    private void initButtons() {
        initBtnDeleteAll();
        initBtnDeleteDocument();
    }

    private void initBtnDeleteAll() {
        btnDeleteAll.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                ServiceProvider.getAdminService().deleteAll(getDeleteAllCallback());
            }

            private AsyncCallback<Boolean> getDeleteAllCallback() {
                return new GLightCallback<Boolean>() {
                    public void onSuccess(Boolean result) {
                        Window.alert("Should not reach here.");
                    }
                };
            }
        });
    }

    private void initBtnDeleteDocument() {
        btnDeleteDocument.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                Window.alert("Not yet implemented.");
            }
        });
    }

    void createNewWorkArea(Canvas childCanvas) {
        if (workArea != null) {
            if (documentScreen.hasMember(workArea)) {
                documentScreen.removeMember(workArea);
            }
        }

        workArea = new VLayout();
        workArea.setHeight100();
        workArea.setWidth(workAreaWidth + "%");

        if (childCanvas != null) {
            workArea.addMember(childCanvas);
        }
        workArea.setShowEdges(true);

        documentScreen.addMember(workArea);
        documentScreen.redraw();
    }
}
