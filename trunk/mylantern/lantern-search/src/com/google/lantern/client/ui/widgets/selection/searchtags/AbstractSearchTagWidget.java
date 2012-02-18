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

import com.google.lantern.shared.enums.select.HelperEnum;

import com.google.lantern.client.ui.widgets.selection.AbstractSelectItemWidget;
import com.google.lantern.shared.enums.select.SelectEnumHelper;
import com.smartgwt.client.types.MultipleAppearance;
import java.util.List;

/**
 * 
 * @author Arjun Satyapal
 */
public abstract class AbstractSearchTagWidget<E extends Enum<E>, T extends HelperEnum<E>> extends
        AbstractSelectItemWidget<E> {
    private SelectEnumHelper<E> helper;

    public AbstractSearchTagWidget(SelectEnumHelper<E> helper, String title,
            boolean selectMultiple, MultipleAppearance appearance) {

        super(title, selectMultiple, appearance, helper.getArray());
        this.helper = helper;
    }

    public List<E> getSelected() {
        return helper.getListOfSelected(getValues());
    }
    
    public void setSelected(List<T> values) {
        for (T currValue : values) {
            super.setValue(currValue.getPublicName());
        }
    }
}
