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
package com.google.lantern.client.ui.widgets.selection;

import com.smartgwt.client.types.MultipleAppearance;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import java.util.List;

/**
 * 
 * @author Arjun Satyapal
 */
public abstract class AbstractSelectItemWidget<E extends Enum<E>> extends SelectItem {
    String title;

    public AbstractSelectItemWidget(String title, boolean selectMultiple,
            MultipleAppearance appearance, String[] valueMap) {
        super();

        this.title = title;
        setTitle(title);
        // TODO(arjuns) : Temporary hack.
        setMultiple(true);
        setMultipleAppearance(appearance);
        setValueMap(valueMap);
        setAllowEmptyValue(true);
    }

    public boolean isValid() {
        return getValues().length != 0;
    }

    public void appendError(List<String> listOfErrors) {
        if (!isValid()) {
            listOfErrors.add(title);
        }
    }

    public boolean isEnabled() {
        return isEnabled();
    }

    public void setEnabled(boolean enabled) {
        setEnabled(enabled);
    }
}
