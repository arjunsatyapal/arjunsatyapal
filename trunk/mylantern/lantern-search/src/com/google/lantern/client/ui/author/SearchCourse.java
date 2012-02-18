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
package com.google.lantern.client.ui.author;


import com.google.lantern.client.handlers.GLightCallback;

import com.google.lantern.shared.objectifyobjects.courses.ListOfCourses;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.lantern.client.ServiceProvider;

import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;

import com.google.common.base.Strings;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.TextBox;
import com.google.lantern.client.ui.UiUtils;
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
public class SearchCourse extends Layout {
    private final TextBox tbSearchBox;
    private final Button btnSubmit;
    private final Button btnCancel;

    public SearchCourse() {
        super();
        VStack vStack = new VStack();
        vStack.setHeight100();
        vStack.setWidth100();
        vStack.setDefaultLayoutAlign(Alignment.CENTER);
        
        Label lbl = new Label("Enter search parameter :");
        lbl.setAutoHeight();
        lbl.setAlign(Alignment.CENTER);
        vStack.addMember(lbl);
        
        tbSearchBox = new TextBox();
        getTbSearchBox().setWidth("100%");
        getTbSearchBox().setAlignment(TextAlignment.CENTER);
        
        Layout textBoxWrapper = new Layout();
        textBoxWrapper.setWidth("80%");
        textBoxWrapper.setAlign(Alignment.CENTER);
        textBoxWrapper.addMember(getTbSearchBox());
        textBoxWrapper.setAutoHeight();
        vStack.addMember(textBoxWrapper);

        HStack btnStack = new HStack();
        btnStack.setAlign(Alignment.CENTER);
        
        btnSubmit = new Button("Submit");
        btnStack.addMember(btnSubmit);
        
        btnCancel = UiUtils.getCancelButton();
        btnStack.addMember(btnCancel);
        
        vStack.addMember(btnStack);
        
        this.addMember(vStack);
        
        initButtons();
    }

    private void initButtons() {
        initBtnSubmit();
    }
    
    private void initBtnSubmit() {
        btnSubmit.addClickHandler(new ClickHandler() {
            
            public void onClick(ClickEvent event) {
                if (Strings.isNullOrEmpty(getTbSearchBox().getText())) {
                    Window.alert("Enter some text to search.");
                    return;
                } 
                
                ServiceProvider.getCourseService().searchCourses(getTbSearchBox().getText(), getSearchCallback());
            }

            private AsyncCallback<ListOfCourses> getSearchCallback() {
                return new GLightCallback<ListOfCourses>() {
                    public void onFailure(Throwable caught) {
                        Window.alert("Could not get result for SearchCourses due to : " + caught.getLocalizedMessage());
                        
                    }

                    public void onSuccess(ListOfCourses result) {
                        Window.alert("See server logs.");
                    }
                };
            }
        });
    }

    /**
     * @return the tbSearchBox
     */
    public TextBox getTbSearchBox() {
        return tbSearchBox;
    }
}
