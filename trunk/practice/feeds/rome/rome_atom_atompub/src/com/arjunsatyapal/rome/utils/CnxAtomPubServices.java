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

import com.arjunsatyapal.rome.atompubimpl.CnxAtomHandlerFactory;
import com.arjunsatyapal.rome.atompubimpl.CnxAtomService;
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
public class CnxAtomPubServices {
    private CnxAtomService atomService;
    public AtomService getServiceDocumentService(HttpServletRequest req,
            HttpServletResponse res) throws AtomException {
        
        AtomHandler handler = createAtomRequestHandler(req, res);
        AtomRequest areq = new AtomRequestImpl(req);
        
        atomService = (CnxAtomService) handler.getAtomService(areq);
        return atomService;
    }

    private AtomHandler createAtomRequestHandler(
            HttpServletRequest request, HttpServletResponse response) {
        // Ensuring that CnxAtomHandlerFactory is created. 
        // It is possible that wrong factory is created if propno.properties file is missing.
        // This will ensure that properties file was placed correctly else server will fail.
        CnxAtomHandlerFactory ahf = (CnxAtomHandlerFactory) AtomHandlerFactory.newInstance();
        
        return ahf.newAtomHandler(request, response);
    }
}
