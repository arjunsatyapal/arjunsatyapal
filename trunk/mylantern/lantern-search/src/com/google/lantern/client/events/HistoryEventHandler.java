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
package com.google.lantern.client.events;

import com.google.gwt.user.client.History;

import com.google.gwt.user.client.Window;

import com.google.lantern.client.ui.home.HomeScreen;

import com.google.lantern.client.ui.LanternUi;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;

/**
 * 
 * @author Arjun Satyapal
 */
public class HistoryEventHandler implements ValueChangeHandler<String> {
    public void onValueChange(ValueChangeEvent<String> triggeringEvent) {
        HistoryEvent event = HistoryEvent.getHistoryEvent(triggeringEvent.getValue());

        handleEvent(event);
    }

    private void handleEvent(HistoryEvent event) {
        LanternUi lanternUi = null;
        switch (event) {
            case AUTHOR_HOME:
                lanternUi = new HomeScreen();
                break;
            default:
                String errMsg = "Unsupported Event : " + event; 
                Window.alert(errMsg);
                throw new IllegalStateException(errMsg);
        }
        
        lanternUi.draw();
    }
    
    public static void triggerEvent(HistoryEvent event) {
        History.newItem(event.getName());
        History.fireCurrentHistoryState();
    }
}
