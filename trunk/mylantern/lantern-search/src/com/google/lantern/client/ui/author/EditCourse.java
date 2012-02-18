///*
// * Copyright (C) Google Inc.
// *
// * Licensed under the Apache License, Version 2.0 (the "License"); you may not
// * use this file except in compliance with the License. You may obtain a copy of
// * the License at
// *
// * http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
// * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
// * License for the specific language governing permissions and limitations under
// * the License.
// */
//package com.google.lantern.client.ui.author;
//
//import static com.google.common.base.Preconditions.checkNotNull;
//
//import com.smartgwt.client.widgets.Canvas;
//
//import com.axeiya.gwtckeditor.client.CKConfig;
//import com.axeiya.gwtckeditor.client.CKEditor;
//import com.google.gwt.user.client.History;
//import com.google.gwt.user.client.Window;
//import com.google.gwt.user.client.rpc.AsyncCallback;
//import com.google.lantern.client.ServiceProvider;
//import com.google.lantern.client.events.HistoryEvent;
//import com.google.lantern.client.events.HistoryEventHandler;
//import com.google.lantern.shared.CourseEntity;
//import com.smartgwt.client.types.Alignment;
//import com.smartgwt.client.widgets.Button;
//import com.smartgwt.client.widgets.Label;
//import com.smartgwt.client.widgets.events.ClickEvent;
//import com.smartgwt.client.widgets.events.ClickHandler;
//import com.smartgwt.client.widgets.layout.HStack;
//import com.smartgwt.client.widgets.layout.Layout;
//import com.smartgwt.client.widgets.layout.VLayout;
//
///**
// * 
// * @author Arjun Satyapal
// */
//public class EditCourse extends Layout {
//    private final Long courseId;
//    private String courseTitle;
//    
//    private Label lblCourseId;
//    private Label lblCourseTitle;
//    private VLayout vStack;
//    private CKEditor ckEditor;
//    
//    private Button btnUpdateCourse;
//    private Button btnCancel;
//    
//
//    public EditCourse(Long courseId) {
//        super();
//
//        this.courseId = checkNotNull(courseId, "courseId cannot be null.");
//
//        ServiceProvider.getCourseService().getCourse(courseId, getCourseCallback(courseId));
//
//        vStack = new VLayout();
//        vStack.setSize("100%", "100%");
//        vStack.setDefaultLayoutAlign(Alignment.LEFT);
//
//        lblCourseId = new Label();
//        lblCourseId.setWidth100();
//        lblCourseId.setAutoHeight();
//        vStack.addMember(lblCourseId);
//
//        lblCourseTitle = new Label();
//        lblCourseTitle.setWidth100();
//        lblCourseTitle.setAutoHeight();
//        vStack.addMember(lblCourseTitle);
//
//        initBtnStack();
//
//        ckEditor = new CKEditor(getCkConfig());
//        ckEditor.setSize("100%", "100%");
//        Canvas canvas = new Canvas();
//        canvas.setWidth100();
//        canvas.setHeight100();
//        canvas.setShowEdges(false);
//        canvas.addChild(ckEditor);
//        
//        vStack.addMember(canvas);
//        
//        this.addMember(vStack);
//        initButton();
//    }
//    
//    private HStack initBtnStack() {
//        HStack btnStack = new HStack();
//        btnStack.setAlign(Alignment.CENTER);
//        btnUpdateCourse = new Button("Update Course");
//        btnStack.addMember(btnUpdateCourse);
//        
//        btnCancel = new Button("Cancel");
//        btnStack.addMember(btnCancel);
//
//        btnStack.setWidth100();
//        btnStack.setAutoHeight();
//        vStack.addMember(btnStack);
//        return btnStack;
//    }
//    
//    private CKConfig getCkConfig() {
//        CKConfig ckConfig = new CKConfig(CKConfig.PRESET_TOOLBAR.FULL);
//        ckConfig.setWidth("100%");
//        ckConfig.setHeight("50em");
//        ckConfig.setBaseFloatZIndex(1000000);
//        ckConfig.setUseFormPanel(false);
//        ckConfig.setResizeEnabled(false);
//
//        return ckConfig; 
//    }
//
//    private AsyncCallback<CourseEntity> getCourseCallback(final Long courseId) {
//        return new AsyncCallback<CourseEntity>() {
//
//            public void onFailure(Throwable caught) {
//                Window.alert("Failed to get Course[" + courseId + "] due to : "
//                        + caught.getLocalizedMessage());
//                History.newItem(HistoryEvent.AUTHOR_HOME.getName(), true);
//            }
//
//            public void onSuccess(CourseEntity result) {
//                updateLblCourseId(result.getId());
//                updateLblCourseTitle(result.getTitle());
//                updateCKEditor(result.getContent());
//            }
//        };
//    }
//
//    private void initButton() {
//        initBtnUpdateCourse();
//        initBtnCancel();
//    }
//
//    private void initBtnCancel() {
//        btnCancel.addClickHandler(new ClickHandler() {
//            
//            public void onClick(ClickEvent event) {
//                // TODO(arjuns) : Optimize for cancel.
//                HistoryEventHandler.triggerEvent(HistoryEvent.AUTHOR_HOME);
//            }
//        });
//    }
//
//    private void initBtnUpdateCourse() {
//        btnUpdateCourse.addClickHandler(new ClickHandler() {
//
//            public void onClick(ClickEvent event) {
//                String content = getCKEditor().getHTML();
//                CourseEntity entity = new CourseEntity(getCourseId(), getCourseTitle(), content);
//                ServiceProvider.getCourseService().updateCourse(entity, getUpdateCallback());
//            }
//
//            private AsyncCallback<Void> getUpdateCallback() {
//                return new AsyncCallback<Void>() {
//
//                    public void onFailure(Throwable caught) {
//                        Window.alert("Failed to update content for Course[" + getCourseId()
//                                + "] due to : " + caught.getLocalizedMessage());
//
//                    }
//
//                    public void onSuccess(Void result) {
//                        Window.alert("Successuflly updated content for Course[" + getCourseId() + "].");
//                        HistoryEventHandler.triggerEvent(HistoryEvent.AUTHOR_HOME);
//                    }
//                };
//            }
//        });
//    }
//
//    protected Long getCourseId() {
//        return courseId;
//    }
//
//    public CKEditor getCKEditor() {
//        return ckEditor;
//    }
//
//    protected void updateLblCourseId(Long courseId) {
//        lblCourseId.setContents("Id = " + courseId);
//    }
//
//    protected void updateLblCourseTitle(String courseTitle) {
//        this.setCourseTitle(courseTitle);
//        lblCourseTitle.setContents("Title = " + courseTitle);
//    }
//
//    protected void updateCKEditor(String html) {
//        ckEditor.setHTML(html);
//    }
//
//    public String getCourseTitle() {
//        return courseTitle;
//    }
//
//    public void setCourseTitle(String courseTitle) {
//        this.courseTitle = courseTitle;
//    }
//}
