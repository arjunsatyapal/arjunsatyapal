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

import static com.google.lantern.client.ServiceProvider.getCourseService;

import com.google.lantern.client.handlers.GLightCallback;


import com.google.lantern.shared.objectifyobjects.courses.CourseEntity;


import com.smartgwt.client.widgets.layout.HStack;

import com.google.lantern.client.events.HistoryEvent;

import com.google.lantern.client.events.HistoryEventHandler;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VStack;

/**
 * 
 * @author Arjun Satyapal
 */
public class CreateCourse extends Layout {
    private VStack vStack;
    private TextBox tbTitle;
    private Button btnSubmitTitle;
    private Button btnCancel;

    public CreateCourse() {
        super();
        vStack = new VStack();
        vStack.setSize("100%", "100%");
        vStack.setDefaultLayoutAlign(Alignment.CENTER);

        Label lblCourseTitle = new Label("Course Title");
        lblCourseTitle.setAutoFit(true);
        lblCourseTitle.setWrap(false);
        lblCourseTitle.setAlign(Alignment.CENTER);
        vStack.addMember(lblCourseTitle);

        tbTitle = new TextBox();
        tbTitle.setMaxLength(100);
        tbTitle.setAlignment(TextAlignment.CENTER);
        tbTitle.setWidth("100%");

        Layout textBoxWrapper = new Layout();
        textBoxWrapper.setWidth("80%");
        textBoxWrapper.setAlign(Alignment.CENTER);
        textBoxWrapper.addMember(getTbTitle());
        textBoxWrapper.setAutoHeight();
        vStack.addMember(textBoxWrapper);

        HStack btnStack = new HStack();
        
        btnSubmitTitle = new Button("Create Course");
        btnStack.addMember(btnSubmitTitle);
        
        btnCancel = new Button("Cancel");
        btnStack.addMember(btnCancel);
        
        btnStack.setWidth100();
        btnStack.setAutoHeight();
        btnStack.setAlign(Alignment.CENTER);
        
        vStack.addMember(btnStack);

        this.addMember(vStack);

        initButton();
    }

    private void initButton() {
        initBtnCreateCourse();
        initBtnCancel();
    }

    private void initBtnCancel() {
        btnCancel.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                // TODO(arjuns) : Optimize for cancel.
                HistoryEventHandler.triggerEvent(HistoryEvent.AUTHOR_HOME);
            }
        });
    }

    private void initBtnCreateCourse() {
        btnSubmitTitle.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                if (getTbTitle().getText().isEmpty()) {
                    Window.alert("Enter a title.");
                    return;
                }

                CourseEntity newCourseEntity = new CourseEntity(getTbTitle().getText());
                getCourseService().createNewCourse(newCourseEntity, getCallback());
            }

            private AsyncCallback<Long> getCallback() {
                return new GLightCallback<Long>() {

                    public void onFailure(Throwable caught) {
                        Window.alert("Failed to stored newCourseEntity because : ." + caught.getLocalizedMessage());
                        
                    }

                    public void onSuccess(Long result) {
                        Window.alert("Successfully stored course as : " + result);
                        HistoryEventHandler.triggerEvent(HistoryEvent.AUTHOR_HOME);
                    }
                };
            }
        });
    }

    public TextBox getTbTitle() {
        return tbTitle;
    }
}
