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
package com.google.lantern.client.ui;


import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VStack;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.google.gwt.user.client.ui.TextBoxBase;

/**
 *
 * @author Arjun Satyapal
 */
public class CreateCourse extends Composite {
    private VStack vStack;
    private TextArea textArea;
    public CreateCourse() {
        vStack = new VStack();
        vStack.setSize("100%", "100%");
        vStack.setDefaultLayoutAlign(Alignment.CENTER);
        
        Label lblCourseTitle = new Label("Course Title");
        vStack.addMember(lblCourseTitle);
        
        lblCourseTitle.setAlign(Alignment.CENTER);
        
        textArea = new TextArea();
        textArea.setCharacterWidth(100);
        textArea.setVisibleLines(1);
        textArea.setTextAlignment(TextBoxBase.ALIGN_CENTER);
        textArea.setAlignment(TextAlignment.CENTER);
        HLayout textAreaWrapper = new HLayout();
        textAreaWrapper.setWidth("80%");
        textAreaWrapper.setAlign(Alignment.CENTER);
        textAreaWrapper.addMember(textArea);
        textAreaWrapper.setAutoHeight();
//        textArea.setWidth("100%");
        
        vStack.addMember(textAreaWrapper);
        initWidget(vStack);
    }
}
