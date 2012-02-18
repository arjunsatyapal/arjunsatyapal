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
package com.google.lantern.shared.enums.select;

import com.google.common.collect.Lists;
import java.util.EnumSet;
import java.util.List;

/**
 *
 * @author Arjun Satyapal
 */
public class SelectEnumHelper<E extends Enum<E>> {
    private Class<E> clazz;
    
    public SelectEnumHelper(Class<E> clazz) {
        this.clazz = clazz;
    }
    
    public String getName(E object) {
        return object.name();
    }
    
    public String getPublicName(E object) {
        return getName(object).replace("_", " ");
    }
    
    public Enum<E> getByPublicName(String publicName) {
        return Enum.valueOf(clazz, publicName.replace(" ", "_"));
    }
    
    @SuppressWarnings("unchecked")
    public List<E> getListOfSelected(String[] selected) {
        List<E> list = Lists.newArrayList();

        for (String curr : selected)
            list.add((E) getByPublicName(curr));

        return list;
    }
    
    @SuppressWarnings("unchecked")
    public String[] getArray() {
        Object[] values = EnumSet.allOf(clazz).toArray();
        
        
        String[] array = new String[values.length];
        
        for (int counter=0; counter < values.length; counter++) {
            array[counter] = getPublicName((E) values[counter]);
        }
        
        return array;
    }
}
