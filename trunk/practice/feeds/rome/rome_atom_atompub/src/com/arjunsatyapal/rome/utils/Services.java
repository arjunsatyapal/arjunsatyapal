/*
 * Copyright 2011 Google Inc.
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
package com.arjunsatyapal.rome.utils;

import com.sun.syndication.propono.atom.common.AtomService;
import com.sun.syndication.propono.atom.server.AtomException;
import com.sun.syndication.propono.atom.server.AtomHandler;
import com.sun.syndication.propono.atom.server.AtomHandlerFactory;
import com.sun.syndication.propono.atom.server.AtomRequest;
import com.sun.syndication.propono.atom.server.AtomRequestImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Arjun Satyapal
 */
public class Services {
    public static AtomService getServiceDocumentService(HttpServletRequest req,
            HttpServletResponse res) throws AtomException {
        AtomHandler handler = createAtomRequestHandler(req, res);
        AtomRequest areq = new AtomRequestImpl(req);
        return handler.getAtomService(areq);
    }

    /**
     * Create an Atom request handler. TODO: make AtomRequestHandler
     * implementation configurable.
     */
    private static AtomHandler createAtomRequestHandler(
            HttpServletRequest request, HttpServletResponse response) {
        AtomHandlerFactory ahf = AtomHandlerFactory.newInstance();
        return ahf.newAtomHandler(request, response);
    }
}
