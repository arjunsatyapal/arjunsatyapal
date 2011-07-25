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

import static com.google.common.base.Preconditions.checkNotNull;

import com.arjunsatyapal.rome.atompubimpl.CnxAtomHandlerEnum;
import com.arjunsatyapal.rome.server.atompub.resource.ResourceAtomHandler;
import com.sun.syndication.propono.atom.server.AtomHandler;
import com.sun.syndication.propono.atom.server.AtomHandlerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Arjun Satyapal
 */
public class CnxAtomHandlerFactory extends AtomHandlerFactory {
    private CnxAtomHandlerEnum handlerType;

    @Override
    public AtomHandler newAtomHandler(HttpServletRequest req, HttpServletResponse res) {
        /*
         * rome-propono assumes that there is only one hanlder for the whole
         * system. Whereas it is better to have different AtomHandlers for each
         * CNX collection.
         */
        checkNotNull(handlerType, "handlerType should be set first.");

        // TODO(arjuns) : Move propono.properties to proper location so that
        // when it is deployed to appengine it is properly created.

        switch (handlerType) {
        case RESOURCE: return new ResourceAtomHandler(req, res);
        case SERVICE:
            return new CnxServiceAtomHandler(req, res);
        default :
             throw new IllegalArgumentException("Unsupported handlerType = " + handlerType);
        }
    }

    /**
     * @return the handlerType
     */
    public CnxAtomHandlerEnum getHandlerType() {
        return handlerType;
    }

    /**
     * @param handlerType
     *            the handlerType to set
     */
    public void setHandlerType(CnxAtomHandlerEnum handlerType) {
        this.handlerType = handlerType;
    }
}
