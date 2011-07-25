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
package com.arjunsatyapal.rome.server.atompub.resource;

import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.propono.atom.common.AtomService;
import com.sun.syndication.propono.atom.common.Categories;
import com.sun.syndication.propono.atom.server.AtomException;
import com.sun.syndication.propono.atom.server.AtomHandler;
import com.sun.syndication.propono.atom.server.AtomMediaResource;
import com.sun.syndication.propono.atom.server.AtomRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Arjun Satyapal
 */
public class ResourceAtomHandler implements AtomHandler {

    /**
     * @param req
     * @param res
     */
    public ResourceAtomHandler(HttpServletRequest req, HttpServletResponse res) {
        // TODO(arjuns): Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see com.sun.syndication.propono.atom.server.AtomHandler#getAuthenticatedUsername()
     */
    @Override
    public String getAuthenticatedUsername() {
        return null;
    }

    /* (non-Javadoc)
     * @see com.sun.syndication.propono.atom.server.AtomHandler#getAtomService(com.sun.syndication.propono.atom.server.AtomRequest)
     */
    @Override
    public AtomService getAtomService(AtomRequest req) throws AtomException {
        return null;
    }

    /* (non-Javadoc)
     * @see com.sun.syndication.propono.atom.server.AtomHandler#getCategories(com.sun.syndication.propono.atom.server.AtomRequest)
     */
    @Override
    public Categories getCategories(AtomRequest req) throws AtomException {
        return null;
    }

    /* (non-Javadoc)
     * @see com.sun.syndication.propono.atom.server.AtomHandler#getCollection(com.sun.syndication.propono.atom.server.AtomRequest)
     */
    @Override
    public Feed getCollection(AtomRequest req) throws AtomException {
        return null;
    }

    @Override
    public Entry postEntry(AtomRequest req, Entry entry) throws AtomException {
        return null;
    }

    /* (non-Javadoc)
     * @see com.sun.syndication.propono.atom.server.AtomHandler#getEntry(com.sun.syndication.propono.atom.server.AtomRequest)
     */
    @Override
    public Entry getEntry(AtomRequest req) throws AtomException {
        return null;
    }

    /* (non-Javadoc)
     * @see com.sun.syndication.propono.atom.server.AtomHandler#getMediaResource(com.sun.syndication.propono.atom.server.AtomRequest)
     */
    @Override
    public AtomMediaResource getMediaResource(AtomRequest req) throws AtomException {
        return null;
    }

    /* (non-Javadoc)
     * @see com.sun.syndication.propono.atom.server.AtomHandler#putEntry(com.sun.syndication.propono.atom.server.AtomRequest, com.sun.syndication.feed.atom.Entry)
     */
    @Override
    public void putEntry(AtomRequest req, Entry entry) throws AtomException {
    }

    /* (non-Javadoc)
     * @see com.sun.syndication.propono.atom.server.AtomHandler#deleteEntry(com.sun.syndication.propono.atom.server.AtomRequest)
     */
    @Override
    public void deleteEntry(AtomRequest req) throws AtomException {
    }

    /* (non-Javadoc)
     * @see com.sun.syndication.propono.atom.server.AtomHandler#postMedia(com.sun.syndication.propono.atom.server.AtomRequest, com.sun.syndication.feed.atom.Entry)
     */
    @Override
    public Entry postMedia(AtomRequest req, Entry entry) throws AtomException {
        return null;
    }

    /* (non-Javadoc)
     * @see com.sun.syndication.propono.atom.server.AtomHandler#putMedia(com.sun.syndication.propono.atom.server.AtomRequest)
     */
    @Override
    public void putMedia(AtomRequest req) throws AtomException {
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

}
