package com.google.lantern.client.ui.widgets.searchwidgets;


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

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.lantern.client.ui.widgets.documentselection.DocumentSelectionWidget;
import com.google.lantern.shared.objectifyobjects.documents.DocumentEntity;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VStack;

/**
 * 
 * @author Arjun Satyapal
 */
public class SearchDocumentWidget extends Layout {
    private VStack vStack;
    private DocumentSelectionWidget documentSelectionWidget;
    
    public SearchDocumentWidget(AsyncCallback<DocumentEntity> documentHandler) {
        super();
        vStack = new VStack();
        vStack.setSize("100%", "100%");
        vStack.setDefaultLayoutAlign(Alignment.CENTER);

        Label lblSearchString = new Label("Select Tags");
        lblSearchString.setAutoHeight();
        lblSearchString.setWrap(false);
        lblSearchString.setAlign(Alignment.CENTER);
        vStack.addMember(lblSearchString);

        documentSelectionWidget = new DocumentSelectionWidget(documentHandler);
        vStack.addMember(documentSelectionWidget);
        
        addMember(vStack);
    }
}
