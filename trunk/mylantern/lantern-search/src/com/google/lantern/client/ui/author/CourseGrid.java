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

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.lantern.client.ServiceProvider.getCourseService;

import com.google.lantern.client.handlers.GLightCallback;


import com.google.lantern.shared.objectifyobjects.courses.ListOfCourses;

import com.google.lantern.shared.objectifyobjects.courses.CourseEntity;


import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.lantern.client.ServiceProvider;
import com.google.lantern.client.smartgwtentity.SGCourseRecord;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.HTMLFlow;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.Layout;

/**
 *
 * @author Arjun Satyapal
 */
public class CourseGrid extends Layout {

    
    private ListGrid courseGrid;
    private SGCourseRecord[] courseRecords;
    private SGCourseRecord selectedRecord;
    private String cursorStr;
    private final HTMLFlow htmlFlow;
    
    public CourseGrid(HTMLFlow htmlFlow) {
        super();
        
        this.htmlFlow = checkNotNull(htmlFlow, "htmlFlow cannot be null.");
        courseGrid = new ListGrid();
        courseGrid.setHeight100();
        courseGrid.setWidth100();
        courseGrid.setShowAllRecords(true);
        courseGrid.setSelectionType(SelectionStyle.SINGLE);
        
        ListGridField idField = new ListGridField(SGCourseRecord.ATTR_COURSE_ID, "Id");  
        ListGridField titleField = new ListGridField(SGCourseRecord.ATTR_COURSE_TITLE, "Title");  
        courseGrid.setFields(idField, titleField);
  
        courseGrid.addSelectionChangedHandler(getSelectionChangeHandler());  

        updateCourseGrid();
        this.addMember(getCourseGrid());
    }

    private SelectionChangedHandler getSelectionChangeHandler() {
        return new SelectionChangedHandler() {  
            public void onSelectionChanged(SelectionEvent event) {
                if (getCourseGrid().getSelectedRecord() == null) {
                    return;
                }
                SGCourseRecord currRecord = (SGCourseRecord) getCourseGrid().getSelectedRecord(); 
                setSelectedRecord(currRecord);
                updateHtmlFlow(currRecord.getCourseId());
            }

            private void updateHtmlFlow(Long courseId) {
                ServiceProvider.getCourseService().getCourse(courseId, getHtmlFlowUpdateCallback(courseId));
                
            }

            private AsyncCallback<CourseEntity> getHtmlFlowUpdateCallback(final Long courseId) {
                return new GLightCallback<CourseEntity>() {
                    public void onFailure(Throwable caught) {
                        Window.alert("Failed to get Content for courseId[" + courseId + "].");
                    }

                    public void onSuccess(CourseEntity result) {
                        if (result == null) {
                            Window.alert("Could not get content for CourseId[" + courseId + "].");
                            return;
                        }
                        
                        if (result.getContent() == null) {
                            getHtmlFlow().setContents("<b> No content added yet.</b>");
                        } else {
                            getHtmlFlow().setContents(result.getContent());
                        }
                    }
                };
            }  
        };
    }

    public void updateCourseGrid() {
        getCourseService().getListOfCourses(null /*cursor*/, getListOfCourseCallback());
    }

    private AsyncCallback<ListOfCourses> getListOfCourseCallback() {
        return new GLightCallback<ListOfCourses>() {
            public void onFailure(Throwable caught) {
                Window.alert("Failed to get List of Courses due to : " + caught.getLocalizedMessage());
            }

            public void onSuccess(ListOfCourses result) {
//                setCursorStr(result.getCursor());
//                setCourseRecords(CourseRecord.fromCourseEntities(result.getListOfDocuments()));
//                if (getCourseRecords() != null && getCourseRecords().length != 0) {
//                    getCourseGrid().setData(getCourseRecords());
//                    getCourseGrid().setAutoFitData(Autofit.VERTICAL);
//                }
                Window.alert("To be implemented.");
            }
        };
    }

    protected String getCursorStr() {
        return cursorStr;
    }
    
    protected void setCursorStr(String cursorStr) {
        this.cursorStr = cursorStr;
    }

    protected SGCourseRecord[] getCourseRecords() {
        return courseRecords;
    }

    public void setCourseRecords(SGCourseRecord[] courseRecords) {
        this.courseRecords = courseRecords;
    }

    public ListGrid getCourseGrid() {
        return courseGrid;
    }

    public void setCourseGrid(ListGrid courseGrid) {
        this.courseGrid = courseGrid;
    }

    public SGCourseRecord getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(SGCourseRecord selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public HTMLFlow getHtmlFlow() {
        return htmlFlow;
    }
}
