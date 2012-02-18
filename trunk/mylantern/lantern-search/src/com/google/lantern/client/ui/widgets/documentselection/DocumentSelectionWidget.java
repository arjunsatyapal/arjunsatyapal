package com.google.lantern.client.ui.widgets.documentselection;

/* 
 * Smart GWT (GWT for SmartClient) 
 * Copyright 2008 and beyond, Isomorphic Software, Inc. 
 * 
 * Smart GWT is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License version 3 
 * as published by the Free Software Foundation.  Smart GWT is also 
 * available under typical commercial license terms - see 
 * http://smartclient.com/license 
 * 
 * This software is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
 * Lesser General Public License for more details. 
 */

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.lantern.client.ServiceProvider;
import com.google.lantern.client.handlers.GLightCallback;
import com.google.lantern.client.smartgwtentity.SGDocumentRecord;
import com.google.lantern.client.ui.widgets.searchwidgets.SearchTagWidget;
import com.google.lantern.shared.exceptions.checked.client.searchtagexception.SearchTagException;
import com.google.lantern.shared.objectifyobjects.SearchTags.SearchTagProfile;
import com.google.lantern.shared.objectifyobjects.documents.DocumentEntity;
import com.google.lantern.shared.objectifyobjects.documents.DocumentSearchResultWrapper;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.CellDoubleClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;

public class DocumentSelectionWidget extends VLayout {
    private SearchTagWidget searchTagWidget;
    ListGrid documentGrid;

    AsyncCallback<DocumentEntity> documentHandler;

    private ListGridField courseId;
    private ListGridField title;
    private ListGridField domain;
    private ListGridField usStates;
    private ListGridField grades;

    /**
     * @wbp.parser.constructor
     */
    protected DocumentSelectionWidget() {
    }

    public DocumentSelectionWidget(AsyncCallback<DocumentEntity> documentHandler) {
        super();
        this.documentHandler = checkNotNull(documentHandler);

        setHeight100();
        setWidth100();

        searchTagWidget = new SearchTagWidget(getChangedHandler());
        addMember(searchTagWidget);

        documentGrid = new ListGrid();

        documentGrid.setHeight100();
        documentGrid.setWidth100();
        documentGrid.setShowAllRecords(true);
        documentGrid.setWrapCells(true);
        documentGrid.setSelectionType(SelectionStyle.SINGLE);

        courseId =
                new ListGridField(SGDocumentRecord.ATTR_COURSE_ID, SGDocumentRecord.COL_COURSE_ID);

        title = new ListGridField(SGDocumentRecord.ATTR_COURSE_TITLE,
                SGDocumentRecord.COL_COURSE_TITLE);
        domain = new ListGridField(SGDocumentRecord.ATTR_DOMAIN, SGDocumentRecord.COL_DOMAIN);
        domain.setSortByDisplayField(true);

        usStates =
                new ListGridField(SGDocumentRecord.ATTR_US_STATES, SGDocumentRecord.COL_US_STATES);
        usStates.setSortByDisplayField(true);

        grades = new ListGridField(SGDocumentRecord.ATTR_GRADES, SGDocumentRecord.COL_GRADES);
        grades.setSortByDisplayField(true);

        documentGrid.setFields(courseId, title, domain, usStates, grades);
        documentGrid.addCellDoubleClickHandler(getDocItemDoubleClickHandler());
        addMember(documentGrid);

        refreshDocumentList();
    }

    private ChangedHandler getChangedHandler() {
        return new ChangedHandler() {
            public void onChanged(ChangedEvent event) {
                refreshDocumentList();
            }
        };
    }

    void refreshDocumentList() {
        try {
            ServiceProvider.getDocumentService().searchDocument(
                    searchTagWidget.getSearchCriteria(SearchTagProfile.NO_TAG_MANDATORY),
                    getPageSearchByTagsCb());
        } catch (SearchTagException e) {
            Window.alert("Failed to retrieve document list from server.");
        }
    }

    private AsyncCallback<DocumentSearchResultWrapper> getPageSearchByTagsCb() {
        return new GLightCallback<DocumentSearchResultWrapper>() {

            public void onSuccess(DocumentSearchResultWrapper result) {

                if (result.getListOfDocuments().size() == 0) {
                    documentGrid.setData(new ListGridRecord[] {});
                } else {
                    documentGrid.setData(SGDocumentRecord.fromDocumentEntities(result
                            .getListOfDocuments()));
                }
            }
        };
    }

    public SearchTagWidget getSearchTagWidget() {
        return searchTagWidget;
    }

    private CellDoubleClickHandler getDocItemDoubleClickHandler() {
        return new CellDoubleClickHandler() {
            public void onCellDoubleClick(CellDoubleClickEvent event) {
                SGDocumentRecord record = (SGDocumentRecord) event.getRecord();
                fetchDocument(record.getDocumentId());
            }
        };
    }

    void fetchDocument(Long documentId) {
        ServiceProvider.getDocumentService().getDocument(documentId, documentHandler);
    }
}
