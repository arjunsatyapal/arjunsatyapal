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
package com.google.lantern.client.ui.document;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.lantern.client.handlers.GLightCallback;
import com.google.lantern.client.ui.widgets.ShowDocumentWidget;
import com.google.lantern.client.ui.widgets.searchwidgets.SearchDocumentWidget;
import com.google.lantern.shared.objectifyobjects.documents.DocumentEntity;
import com.smartgwt.client.widgets.layout.Layout;

/**
 * 
 * @author Arjun Satyapal
 */
public class ShowDocument extends Layout {
    private SearchDocumentWidget searchWidget;

    public ShowDocument(DocumentEntity document) {
        super();
        setHeight100();
        setWidth100();
        searchWidget = new SearchDocumentWidget(getShowPageHandler());
        addMember(searchWidget);
        
        if (document != null) {
            getShowPageHandler().onSuccess(document);
        }
    }

    private AsyncCallback<DocumentEntity> getShowPageHandler() {
        return new GLightCallback<DocumentEntity>() {
            public void onSuccess(DocumentEntity result) {
                new ShowDocumentWidget(result).show();
            }
        };

    }
}
