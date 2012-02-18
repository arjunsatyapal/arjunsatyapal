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
package com.google.lantern.client.ui.home;


import com.google.lantern.client.ui.admin.AdminUi;

import com.google.lantern.client.ui.document.DocumentUi;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.lantern.client.ui.LanternUi;
import com.smartgwt.client.widgets.layout.HStack;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

/**
 *
 * @author Arjun Satyapal
 */
public class HomeScreen implements LanternUi {
    private TabSet tabSet;
    private Tab tabHome;
    private Tab tabStudent;
    private Tab tabTeacher;
//    private Tab tabAuthor;
    private Tab tabDocuments;
    private Tab tabAdmin;
    private HStack hStack;

    public HomeScreen() {
        tabHome = new Tab("Home");
        tabStudent = new Tab("Student");
        tabTeacher = new Tab("Teacher");
//        tabAuthor = new Tab("Author");
        tabDocuments = new Tab("Documents");
        tabAdmin = new Tab("Admin");
        
        hStack = new HStack();
        hStack.setWidth100();
        hStack.setHeight100();

        // Now initializing tabset.
        tabSet = new TabSet();
        tabSet.setHeight100();
        tabSet.setWidth100();

        tabSet.addTab(tabHome);
        tabSet.addTab(tabStudent);
        tabSet.addTab(tabTeacher);
//        tabSet.addTab(tabAuthor);
        tabSet.addTab(tabDocuments);
        tabSet.addTab(tabAdmin);
        
        tabSet.selectTab(tabDocuments);
        
        initTabs();
    }
    
    private void initTabs() {
//        initAuthor();
        
        initMainScreen();
    }

    private void initMainScreen() {
        new DocumentUi(tabDocuments);
        new AdminUi(tabAdmin);
    }

//    private void initAuthor() {
//        new AuthorScreen(tabAuthor);
//    }

    public void draw() {
        Window.addResizeHandler(new ResizeHandler() {
            
            public void onResize(ResizeEvent event) {
                // TODO Auto-generated method stub
                getTabSet().resizeTo("100%", "100%");
            }
        });
        
        getTabSet().draw();
    }

    public TabSet getTabSet() {
        return tabSet;
    }
}
