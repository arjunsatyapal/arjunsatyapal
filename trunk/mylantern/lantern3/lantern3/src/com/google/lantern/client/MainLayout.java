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

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DragAppearance;
import com.smartgwt.client.types.ImageStyle;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.RichTextEditor;
import com.smartgwt.client.widgets.layout.HStack;
import com.smartgwt.client.widgets.layout.VStack;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

/**
 * 
 * @author Arjun Satyapal
 */
public class MainLayout extends Composite {
    private final Img logo;
    private final Tab tabStudent;
    private final Tab tabTeacher;
    private final Tab tabAuthor;
    private VStack vStack_1;
    private final DropBox gray;
    private DragButton button1;

    public MainLayout() {

        VStack vStack = new VStack();
        vStack.setSize("100%", "100%");

        HStack hStack = new HStack();
        hStack.setSize("100%", "67px");

        logo = new Img("lantern_logo.png");
        logo.setHeight("55px");
        logo.setImageType(ImageStyle.NORMAL);
        hStack.addMember(logo);
        vStack.addMember(hStack);

        TabSet tabSet = new TabSet();
        tabSet.setSize("100%", "100%");
        tabSet.setTabBarPosition(Side.TOP);
        tabSet.setTabBarAlign(Side.TOP);

        tabStudent = new Tab("Student");
        tabStudent.setCanClose(true);
        tabStudent.setCanClose(false);
        tabSet.addTab(tabStudent);

        tabTeacher = new Tab("Teacher");
        tabSet.addTab(tabTeacher);

        tabAuthor = new Tab("Author");

        vStack_1 = new VStack();
        tabAuthor.setPane(vStack_1);
        tabSet.addTab(tabAuthor);
        vStack.addMember(tabSet);
        initWidget(vStack);

        gray = new DropBox();
        gray.addMember(new DragPiece("cube_blue.png", "b"));
        // gray.addMember(new DragPiece("cube_green.png", "g"));
        // gray.addMember(new DragPiece("cube_yellow.png", "y"));
        button1 = new DragButton("Button1", "b");
        gray.addMember(button1);
        
        button1.addClickHandler(new ClickHandler() {
            
            public void onClick(ClickEvent event) {
                Window.alert(getAlertString(gray));
            }
        });
        
        // gray.addMember(new DragButton("Button2", "g"));
        // gray.addMember(new DragButton("Button3", "y"));
        gray.addMember(new DragRichTextEditor());
        gray.addMember(new DragRichTextEditor());
        // gray.setDropTypes("b", "g", "y");

        vStack_1.addChild(gray);
        tabSet.selectTab(tabAuthor);
    }

    private String getAlertString(DropBox dropBox) {
        StringBuilder builder = new StringBuilder();

        for (Canvas currMember : gray.getMembers()) {
            builder.append(currMember.getClass().getName()).append(", ");
        }

        return builder.toString();
    }

    public Img getLogo() {
        return logo;
    }

    public Tab getTabStudent() {
        return tabStudent;
    }

    public Tab getTabTeacher() {
        return tabTeacher;
    }

    public Tab getTabAuthor() {
        return tabAuthor;
    }

    private class DropBox extends VStack {
        public DropBox() {
            setShowEdges(true);
            setMembersMargin(10);
            setLayoutMargin(10);
            setCanAcceptDrop(true);
            setAnimateMembers(true);
            setDropLineThickness(4);
            setAutoHeight();
        }

    }

    private class DragPiece extends Img {
        public DragPiece() {
            setWidth(48);
            setHeight(48);
            setLayoutAlign(Alignment.CENTER);
            setCanDragReposition(true);
            setCanDrop(true);
            setDragAppearance(DragAppearance.TARGET);
        }

        public DragPiece(String src, String dragType) {
            this();
            setSrc(src);
            setDragType(dragType);
        }
    }

    private class DragButton extends Button {
        public DragButton() {
            setWidth(48);
            setHeight(48);
            setLayoutAlign(Alignment.CENTER);
            setCanDragReposition(true);
            setCanDrop(true);
            setDragAppearance(DragAppearance.TARGET);
        }

        public DragButton(String src, String dragType) {
            this();
            setTitle(src);
            // setDragType(dragType);
        }
    }

    private class DragRichTextEditor extends RichTextEditor {
        public DragRichTextEditor() {
            setWidth(48);
            setHeight(48);
            setShowEdges(true);
            setLayoutAlign(Alignment.CENTER);
            setCanDragReposition(true);
            setCanDrop(true);
            setDragAppearance(DragAppearance.TARGET);
        }

        public DragRichTextEditor(String src, String dragType) {
            this();
            setTitle(src);
            // setDragType(dragType);
        }
    }
}
