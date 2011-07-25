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

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.common.base.Throwables;

import com.arjunsatyapal.rome.atompubimpl.CnxAtomHandlerEnum;
import com.arjunsatyapal.rome.atompubimpl.CnxAtomService;
import com.arjunsatyapal.rome.server.atompub.resource.CnxResourceAtomHandler;
import com.arjunsatyapal.rome.server.atompub.service.CnxAtomHandlerFactory;
import com.arjunsatyapal.rome.server.atompub.service.CnxServiceAtomHandler;
import com.sun.syndication.propono.atom.common.AtomService;
import com.sun.syndication.propono.atom.server.AtomException;
import com.sun.syndication.propono.atom.server.AtomHandler;
import com.sun.syndication.propono.atom.server.AtomHandlerFactory;
import com.sun.syndication.propono.atom.server.AtomRequest;
import com.sun.syndication.propono.atom.server.AtomRequestImpl;

import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;

import java.util.Collections;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Arjun Satyapal
 */
public class CnxAtomPubServices {
    private static Logger logger = Logger.getLogger(CnxAtomPubServices.class.getName());
    private static Cache cache;
    private static BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
    
    private CnxAtomService atomService;
    
    static {
        try {
            CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
            cache = cacheFactory.createCache(Collections.emptyMap());
        } catch (CacheException e) {
            logger.severe(Throwables.getStackTraceAsString(e));
        }
    }

    public AtomService getServiceDocumentService(HttpServletRequest req, HttpServletResponse res,
            CnxAtomHandlerEnum handlerType) throws AtomException {

        AtomHandler handler = createAtomRequestHandler(req, res, handlerType);
        AtomRequest areq = new AtomRequestImpl(req);

        atomService = (CnxAtomService) handler.getAtomService(areq);
        return atomService;
    }

    private AtomHandler getAtomHandler(HttpServletRequest req, HttpServletResponse res,
            CnxAtomHandlerEnum handlerType) {
        return createAtomRequestHandler(req, res, handlerType);
    }

    public CnxServiceAtomHandler createCnxServiceAtomHandler(HttpServletRequest req,
            HttpServletResponse res) {
        return (CnxServiceAtomHandler) getAtomHandler(req, res, CnxAtomHandlerEnum.SERVICE);
    }
    
    public CnxResourceAtomHandler createCnxResourceAtomHandler(HttpServletRequest req,
            HttpServletResponse res) {
        return (CnxResourceAtomHandler) getAtomHandler(req, res, CnxAtomHandlerEnum.RESOURCE);
    }

    private AtomHandler createAtomRequestHandler(HttpServletRequest request,
            HttpServletResponse response, CnxAtomHandlerEnum handlerType) {
        CnxAtomHandlerFactory ahf = getCnxAtomHandlerFactory();
        ahf.setHandlerType(handlerType);
        return ahf.newAtomHandler(request, response);
    }

    private CnxAtomHandlerFactory getCnxAtomHandlerFactory() {
        // Ensuring that CnxAtomHandlerFactory is created.
        // It is possible that wrong factory is created if propno.properties
        // file is missing.
        // This will ensure that properties file was placed correctly else
        // server will fail.
        return (CnxAtomHandlerFactory) AtomHandlerFactory.newInstance();
    }
    
    public static Cache getCache() {
        return cache;
    }
    
    public static BlobstoreService getBlobStoreService() {
        return blobstoreService;
    }
}
