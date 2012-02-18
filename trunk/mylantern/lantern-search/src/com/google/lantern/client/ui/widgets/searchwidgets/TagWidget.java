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
package com.google.lantern.client.ui.widgets.searchwidgets;

import com.google.lantern.shared.exceptions.checked.client.searchtagexception.SearchTagException;

import com.google.lantern.client.ui.widgets.GlightWidgets;
import com.google.lantern.client.ui.widgets.selection.searchtags.GradeSelectionWidget;
import com.google.lantern.client.ui.widgets.selection.searchtags.DomainSelectionWidget;
import com.google.lantern.client.ui.widgets.selection.searchtags.UsStateSelectionWidget;
import com.google.lantern.shared.enums.select.Domain;
import com.google.lantern.shared.enums.select.Grades;
import com.google.lantern.shared.enums.select.UsStates;
import com.google.lantern.shared.objectifyobjects.SearchTags;
import com.smartgwt.client.types.FormLayoutType;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.VLayout;
import java.util.List;

/**
 * 
 * @author Arjun Satyapal
 */
public class TagWidget extends VLayout implements GlightWidgets {
    protected DomainSelectionWidget domainSelectionWidget;
    protected UsStateSelectionWidget usStateWidget;
    protected GradeSelectionWidget gradeWidget;
    protected DynamicForm dynamicForm;

    public TagWidget() {
        super();
        setWidth100();

        dynamicForm = new DynamicForm();
        dynamicForm.setNumCols(6);
        dynamicForm.setItemLayout(FormLayoutType.TABLE);
        dynamicForm.setFixedColWidths(false);
        dynamicForm.setWidth100();

        domainSelectionWidget = new DomainSelectionWidget(true);
        usStateWidget = new UsStateSelectionWidget(true);
        gradeWidget = new GradeSelectionWidget(true);

        dynamicForm.setItems(domainSelectionWidget, usStateWidget, gradeWidget);
        addMember(dynamicForm);
    }

    public List<Domain> getSelectedDomains() {
        return domainSelectionWidget.getSelected();
    }

    public List<UsStates> getSelectedUsStates() {
        return usStateWidget.getSelected();
    }

    public List<Grades> getSelectedGrades() {
        return gradeWidget.getSelected();
    }

    public SearchTags getSearchTags(SearchTags.SearchTagProfile searchTagProfile)
            throws SearchTagException {
        return new SearchTags(searchTagProfile, getSelectedDomains(), getSelectedUsStates(),
                getSelectedGrades());
    }
    
    public void setSearchTags(SearchTags searchTags) {
        if (searchTags.hasDomains()) {
            domainSelectionWidget.setSelected(searchTags.getDomains());
        }
        
        if (searchTags.hasUsStates()) {
            usStateWidget.setSelected(searchTags.getUsStates());
        }
        
        if (searchTags.hasGrades()) {
            gradeWidget.setSelected(searchTags.getGrades());
        }
    }

    public boolean isEnabled() {
        return isEnabled();
    }

    public void setEnabled(boolean enabled) {
        setEnabled(enabled);
    }

    public void appendError(List<String> listOfErrors) {
        domainSelectionWidget.appendError(listOfErrors);
        usStateWidget.appendError(listOfErrors);
        gradeWidget.appendError(listOfErrors);
    }
}
