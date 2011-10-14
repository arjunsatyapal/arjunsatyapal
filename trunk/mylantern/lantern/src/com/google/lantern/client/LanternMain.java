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
package com.google.lantern.client;

import com.smartgwt.client.widgets.layout.VLayout;

import com.google.gwt.user.client.ui.Composite;
import com.google.lantern.client.ui.CreateCourse;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HStack;
import com.smartgwt.client.widgets.layout.VStack;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

/**
 * 
 * @author Arjun Satyapal
 */
public class LanternMain extends Composite {
    private TabSet tabSet;
    private Tab tabStudent;
    private Tab tabTeacher;
    private Tab tabAuthor;
    private HStack hStack;
    private VStack vStack;
    private Button btnCreateCourse;
    private VLayout workArea;
    
    public LanternMain() {
        tabStudent = new Tab("Student");
        tabTeacher = new Tab("Teacher");
        tabAuthor = new Tab("Author");
        
        hStack = new HStack();
        hStack.setSize("100%", "100%");
        
        vStack = new VStack();
        vStack.setSize("20%", "100%");
        vStack.setShowEdges(true);
        
        btnCreateCourse = new Button("Create Course");
        btnCreateCourse.setWidth("100%");
        vStack.addMember(btnCreateCourse);
        hStack.addMember(vStack);
        
        workArea = new VLayout();
        workArea.setShowEdges(true);
        workArea.setWidth("80%");
        workArea.setHeight("100%");
        hStack.addMember(workArea);
        
        tabAuthor.setPane(hStack);
        
        // Now initializing tabset.
        tabSet = new TabSet();
        tabSet.setSize("100%", "100%");
        tabSet.addTab(tabStudent);
        tabSet.addTab(tabTeacher);
        tabSet.addTab(tabAuthor);
        tabSet.selectTab(tabAuthor);
        
        initButtons();
    }

    public void onModuleLoad() {

        tabSet.draw();
    }
    
    /**
     * 
     */
    private void initButtons() {
        initCreateCourse();
        
    }

    private void initCreateCourse() {
        btnCreateCourse.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                    Canvas[] children = workArea.getChildren();
                    for (Canvas currChild : children) {
                        workArea.removeChild(currChild);
                    }
                
                workArea.addMember(new CreateCourse());
            }
        });
    }
}
