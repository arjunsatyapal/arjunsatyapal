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
package com.google.lantern.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.lantern.client.events.GwtClientSideExceptionHandler;
import com.google.lantern.client.events.HistoryEvent;
import com.google.lantern.client.events.HistoryEventHandler;
import com.google.lantern.shared.objectifyobjects.LoggedInUser;

/**
 * 
 * @author Arjun Satyapal
 */
public class LanternMain implements EntryPoint {
    public void onModuleLoad() {
         ServiceProvider.getLoginService().getLoggedInUser(getLoggedInUserCb());
    }
    
    private AsyncCallback<LoggedInUser> getLoggedInUserCb() {
        return new AsyncCallback<LoggedInUser>() {

            public void onFailure(Throwable caught) {
                GwtClientSideExceptionHandler.handleException(caught);
            }

            public void onSuccess(LoggedInUser result) {
                History.addValueChangeHandler(new HistoryEventHandler());
                HistoryEventHandler.triggerEvent(HistoryEvent.AUTHOR_HOME);
            }
        };
    }
}
