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

import com.smartgwt.client.widgets.HTMLFlow;

import com.google.gwt.user.client.Window;

import com.smartgwt.client.types.Alignment;

import com.smartgwt.client.widgets.Label;

import com.smartgwt.client.widgets.layout.HStack;

import com.smartgwt.client.widgets.layout.VLayout;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

import com.smartgwt.client.widgets.Button;

import com.smartgwt.client.widgets.tab.Tab;

import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VStack;

/**
 * 
 * @author Arjun Satyapal
 */
public class AuthorScreen extends Layout {
    private static final String cmdStackWidth = "10%";
    private static final String courseListWidth = "10%";
    private static final String workAreaWidth = "80%";

    private HStack authorScreen;

    private VStack commandStack;
    private VStack courseStack;
    private VLayout workArea;

    private CourseGrid courseGrid;
    private HTMLFlow htmlFlow;

    private Button btnCreateCourse;
    private Button btnEditCourse;
    private Button btnSearchCourse;

    public AuthorScreen(Tab tabAuthor) {
        authorScreen = new HStack();

        htmlFlow = new HTMLFlow("<div align=center> Select a Course / Command. </div>");
        htmlFlow.setWidth100();
        
        initCommandStack();
        initCourseStack();

        btnCreateCourse = new Button("Create Course");
        btnCreateCourse.setWidth100();
        commandStack.addMember(btnCreateCourse);

        btnEditCourse = new Button("Edit Course");
        btnEditCourse.setWidth100();
        commandStack.addMember(btnEditCourse);

        btnSearchCourse = new Button("Search Course");
        btnSearchCourse.setWidth100();
        commandStack.addMember(btnSearchCourse);

        createNewWorkArea(htmlFlow);
        tabAuthor.setPane(authorScreen);

        initButtons();
    }

    private void initCommandStack() {
        commandStack = new VStack();
        commandStack.setShowEdges(true);
        commandStack.setHeight100();
        commandStack.setWidth(cmdStackWidth);

        Label cmdStackTitle = new Label("Select Command");
        cmdStackTitle.setAutoHeight();
        cmdStackTitle.setAlign(Alignment.CENTER);
        commandStack.addMember(cmdStackTitle);

        authorScreen.addMember(commandStack);
    }

    private void initCourseStack() {
        courseStack = new VStack();
        courseStack.setShowEdges(true);
        courseStack.setHeight100();
        courseStack.setWidth(courseListWidth);
        authorScreen.addMember(courseStack);

        Label courseStackTitle = new Label("Select Course");
        courseStackTitle.setAutoHeight();
        courseStackTitle.setAlign(Alignment.CENTER);
        courseStack.addMember(courseStackTitle);

//        courseGrid = new CourseGrid(htmlFlow);
//        courseStack.addMember(courseGrid);
    }

    void createNewWorkArea(Canvas childCanvas) {
        if (workArea != null) {
            if (authorScreen.hasMember(workArea)) {
                authorScreen.removeMember(workArea);
            }
        }

        workArea = new VLayout();
        workArea.setHeight100();
        workArea.setWidth(workAreaWidth);
        workArea.addMember(childCanvas);
        workArea.setShowEdges(true);

        authorScreen.addMember(workArea);
        authorScreen.redraw();
    }

    private void initButtons() {
        initBtnCreateCourse();
        initBtnEditCourse();
        initBtnSearchCourse();
    }

    private void initBtnSearchCourse() {
        btnSearchCourse.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                createNewWorkArea(new SearchCourse());
            }
            
        });
    }

    private void initBtnEditCourse() {
        btnEditCourse.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
//                if (getCourseGrid().getSelectedRecord() == null) {
//                    Window.alert("Select a course.");
//                    return;
//                }
//                
//                Long id = getCourseGrid().getSelectedRecord().getCourseId();
//                createNewWorkArea(new EditCourse(id));
                Window.alert("Yet to be implemented.");
            }
        });
    }

    private void initBtnCreateCourse() {
        btnCreateCourse.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                createNewWorkArea(new CreateCourse());
            }
        });
    }

    public CourseGrid getCourseGrid() {
        return courseGrid;
    }
}
