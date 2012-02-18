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
package com.google.lantern.client.ui.widgets;

import com.google.lantern.shared.objectifyobjects.documents.DocumentEntity;
import com.smartgwt.client.types.AnimationAcceleration;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.types.ContentsType;
import com.smartgwt.client.types.LayoutResizeBarPolicy;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.Window;

/**
 *
 * @author Arjun Satyapal
 */
public class ShowDocumentWidget extends Window {
    
    public ShowDocumentWidget(DocumentEntity document) {
        setSize("90%", "90%");
        setCanDropComponents(false);
        setDefaultResizeBars(LayoutResizeBarPolicy.MARKED);
        setAnimateShowEffect(AnimationEffect.SLIDE);
        setAnimateAcceleration(AnimationAcceleration.SMOOTH_START);
        setMinimized(false);
        setCanDragReposition(false);
        setCanDrag(false);
        setCanDragResize(false);
        setIsModal(true);
        setShowModalMask(true);
        centerInPage();
        
        HTMLPane htmlPane = new HTMLPane();
        htmlPane.setContentsType(ContentsType.PAGE);
        htmlPane.setTitle(document.getTitle());
        htmlPane.setContents(document.getContent());

        addItem(htmlPane);
    }
}
