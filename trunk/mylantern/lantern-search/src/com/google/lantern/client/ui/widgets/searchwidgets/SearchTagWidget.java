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

import com.google.lantern.shared.dto.SearchCriteria;
import com.google.lantern.shared.objectifyobjects.SearchTags;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.form.SearchForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import java.util.List;

/**
 * 
 * @author Arjun Satyapal
 */
public class SearchTagWidget extends TagWidget {
    private TextItem searchBox;
    private SearchForm searchForm;
    private Button searchButton;

    ChangedHandler changedHandler;

    /**
     * @wbp.parser.constructor
     */
    public SearchTagWidget() {
        this(null);
    }

    public SearchTagWidget(ChangedHandler changedHandler) {
        super();
        setWidth100();
        this.changedHandler = changedHandler;

        searchBox = new TextItem("searchBox", "Search");
        searchBox.setWidth(500);

        searchForm = new SearchForm();
        searchForm.setItems(searchBox);
        searchForm.setAutoHeight();
        
        searchButton = new Button("Search");
        
        HLayout searchLayout = new HLayout();
        searchLayout.addMember(searchForm);
        searchLayout.addMember(searchButton);
        
        addMember(searchLayout);
        
        initWidgets();
    }

    private void initWidgets() {
        if (changedHandler != null) {
            domainSelectionWidget.addChangedHandler(changedHandler);
            usStateWidget.addChangedHandler(changedHandler);
            gradeWidget.addChangedHandler(changedHandler);
            searchBox.addChangedHandler(changedHandler);
        } else {
            System.out.println("No handler is registered.");
        }
        
        searchButton.addClickHandler(getSearchBoxClickHandler());
    }

    private com.smartgwt.client.widgets.events.ClickHandler getSearchBoxClickHandler() {
        return new com.smartgwt.client.widgets.events.ClickHandler() {

            public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
                changedHandler.onChanged(null);
            }
        };
    }

    public SearchCriteria getSearchCriteria(SearchTags.SearchTagProfile searchTagProfile)
            throws SearchTagException {
        SearchTags searchTags =
                new SearchTags(searchTagProfile, getSelectedDomains(), getSelectedUsStates(),
                        getSelectedGrades());

        return new SearchCriteria().setSearchString(searchBox.getValueAsString()).setSearchTags(
                searchTags);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
    }

    @Override
    public void appendError(List<String> listOfErrors) {
        super.appendError(listOfErrors);
    }
}
