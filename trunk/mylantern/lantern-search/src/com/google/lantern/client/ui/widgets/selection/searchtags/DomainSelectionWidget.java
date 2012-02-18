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
package com.google.lantern.client.ui.widgets.selection.searchtags;

import com.google.lantern.shared.enums.select.Domain;
import com.smartgwt.client.types.MultipleAppearance;

/**
 * 
 * @author Arjun Satyapal
 */
public class DomainSelectionWidget extends AbstractSearchTagWidget<Domain, Domain> {
    public DomainSelectionWidget(boolean selectMultiple) {
        super(Domain.getHelper(), "Select Domain ", true, MultipleAppearance.GRID);
    }
}
