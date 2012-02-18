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
package com.google.lantern.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.lantern.shared.ServletPaths;

/**
 *
 * @author Arjun Satyapal
 */
@RemoteServiceRelativePath(ServletPaths.GwtServices.ADMIN_SERVICE_SERVLET)
public interface AdminServiceAsync {

    void deletePage(Long documentId, AsyncCallback<Boolean> callback);

    void deleteAll(AsyncCallback<Boolean> callback);

}
