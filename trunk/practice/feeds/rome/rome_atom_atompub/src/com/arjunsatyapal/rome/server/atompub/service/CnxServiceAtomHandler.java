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
package com.arjunsatyapal.rome.server.atompub.service;

import com.arjunsatyapal.rome.atompubimpl.CnxAtomService;
import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.propono.atom.common.AtomService;
import com.sun.syndication.propono.atom.common.Categories;
import com.sun.syndication.propono.atom.server.AtomHandler;
import com.sun.syndication.propono.atom.server.AtomMediaResource;
import com.sun.syndication.propono.atom.server.AtomRequest;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Arjun Satyapal
 */
public class CnxServiceAtomHandler implements AtomHandler {
    private static Logger log = Logger.getLogger(CnxServiceAtomHandler.class.getName());
    private CnxAtomService atomService;
//    private static String fileStoreDir = null;
//    private String userName = null;
//    private String atomProtocolURL = null;
//    private String contextURI = null;    
//    private String uploadurl = null;
//    private FileBasedAtomService service = null;
    
    private HttpServletRequest req;
    private HttpServletResponse res;
    /**
     * @param req
     * @param res
     */
    public CnxServiceAtomHandler(HttpServletRequest req, HttpServletResponse res) {
        this.req = req;
        this.res = res;
        
        atomService = new CnxAtomService(req);
    }

    @Override
    public String getAuthenticatedUsername() {
        throw new UnsupportedOperationException();
    }

    @Override
    public AtomService getAtomService(AtomRequest req) {
        return atomService;
    }

    @Override
    public Categories getCategories(AtomRequest req) {
        // TODO(arjuns) : Implement this.
        return null;
    }


    /* (non-Javadoc)
     * @see com.sun.syndication.propono.atom.server.AtomHandler#getCollection(com.sun.syndication.propono.atom.server.AtomRequest)
     */
    @Override
    public Feed getCollection(AtomRequest req) {
        return null;
    }

    /* (non-Javadoc)
     * @see com.sun.syndication.propono.atom.server.AtomHandler#postEntry(com.sun.syndication.propono.atom.server.AtomRequest, com.sun.syndication.feed.atom.Entry)
     */
    @Override
    public Entry postEntry(AtomRequest req, Entry entry) {
        return null;
    }

    /* (non-Javadoc)
     * @see com.sun.syndication.propono.atom.server.AtomHandler#getEntry(com.sun.syndication.propono.atom.server.AtomRequest)
     */
    @Override
    public Entry getEntry(AtomRequest req) {
        return null;
    }

    /* (non-Javadoc)
     * @see com.sun.syndication.propono.atom.server.AtomHandler#getMediaResource(com.sun.syndication.propono.atom.server.AtomRequest)
     */
    @Override
    public AtomMediaResource getMediaResource(AtomRequest req) {
        return null;
    }

    /* (non-Javadoc)
     * @see com.sun.syndication.propono.atom.server.AtomHandler#putEntry(com.sun.syndication.propono.atom.server.AtomRequest, com.sun.syndication.feed.atom.Entry)
     */
    @Override
    public void putEntry(AtomRequest req, Entry entry) {
    }

    /* (non-Javadoc)
     * @see com.sun.syndication.propono.atom.server.AtomHandler#deleteEntry(com.sun.syndication.propono.atom.server.AtomRequest)
     */
    @Override
    public void deleteEntry(AtomRequest req) {
    }

    /* (non-Javadoc)
     * @see com.sun.syndication.propono.atom.server.AtomHandler#postMedia(com.sun.syndication.propono.atom.server.AtomRequest, com.sun.syndication.feed.atom.Entry)
     */
    @Override
    public Entry postMedia(AtomRequest req, Entry entry) {
        return null;
    }

    /* (non-Javadoc)
     * @see com.sun.syndication.propono.atom.server.AtomHandler#putMedia(com.sun.syndication.propono.atom.server.AtomRequest)
     */
    @Override
    public void putMedia(AtomRequest req) {
    }

    /* (non-Javadoc)
     * @see com.sun.syndication.propono.atom.server.AtomHandler#isAtomServiceURI(com.sun.syndication.propono.atom.server.AtomRequest)
     */
    @Override
    public boolean isAtomServiceURI(AtomRequest req) {
        return false;
    }

    /* (non-Javadoc)
     * @see com.sun.syndication.propono.atom.server.AtomHandler#isCategoriesURI(com.sun.syndication.propono.atom.server.AtomRequest)
     */
    @Override
    public boolean isCategoriesURI(AtomRequest req) {
        return false;
    }

    /* (non-Javadoc)
     * @see com.sun.syndication.propono.atom.server.AtomHandler#isCollectionURI(com.sun.syndication.propono.atom.server.AtomRequest)
     */
    @Override
    public boolean isCollectionURI(AtomRequest req) {
        return false;
    }

    /* (non-Javadoc)
     * @see com.sun.syndication.propono.atom.server.AtomHandler#isEntryURI(com.sun.syndication.propono.atom.server.AtomRequest)
     */
    @Override
    public boolean isEntryURI(AtomRequest req) {
        return false;
    }

    /* (non-Javadoc)
     * @see com.sun.syndication.propono.atom.server.AtomHandler#isMediaEditURI(com.sun.syndication.propono.atom.server.AtomRequest)
     */
    @Override
    public boolean isMediaEditURI(AtomRequest req) {
        return false;
    }
    //TODO(arjuns) : Comeup with a good name.
    

}
