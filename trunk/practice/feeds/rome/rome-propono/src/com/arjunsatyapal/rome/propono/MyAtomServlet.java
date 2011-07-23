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
package com.arjunsatyapal.rome.propono;

import com.arjunsatyapal.rome.propono.myatom.MyAtomHandlerFactory;
import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.io.WireFeedOutput;
import com.sun.syndication.io.impl.Atom10Generator;
import com.sun.syndication.propono.atom.common.AtomService;
import com.sun.syndication.propono.atom.common.Categories;
import com.sun.syndication.propono.atom.server.AtomException;
import com.sun.syndication.propono.atom.server.AtomHandler;
import com.sun.syndication.propono.atom.server.AtomMediaResource;
import com.sun.syndication.propono.atom.server.AtomRequest;
import com.sun.syndication.propono.atom.server.AtomRequestImpl;
import com.sun.syndication.propono.atom.server.AtomServlet;
import com.sun.syndication.propono.utils.Utilities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import java.io.Writer;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Arjun Satyapal
 */
public class MyAtomServlet extends AtomServlet {
//    private Logger log = Logger.getLogger(MyAtomServlet.class.getName());
    
//    @Override
//    public void doGet(HttpServletRequest req, HttpServletResponse res) {
//    }
    
//    @Override
//    public void doGet(HttpServletRequest req, HttpServletResponse res) {
//        AtomHandler handler = new MyAtomHandlerFactory().newAtomHandler(req, res);
//        String userName = handler.getAuthenticatedUsername();
//        
//        if (userName != null) {
//            AtomRequest areq = new AtomRequestImpl(req);
//            try {
//                if (handler.isAtomServiceURI(areq)) {
//                    // return an Atom Service document
//                    AtomService service = handler.getAtomService(areq);
//                    Document doc = service.serviceToDocument(); 
//                    res.setContentType("application/atomsvc+xml; charset=utf-8");
//                    Writer writer = res.getWriter();
//                    XMLOutputter outputter = new XMLOutputter();
//                    outputter.setFormat(Format.getPrettyFormat());
//                    outputter.output(doc, writer);
//                    writer.close();
//                    res.setStatus(HttpServletResponse.SC_OK);
//                } 
//                else if (handler.isCategoriesURI(areq)) {
//                    Categories cats = handler.getCategories(areq);
//                    res.setContentType("application/xml");
//                    Writer writer = res.getWriter();
//                    Document catsDoc = new Document();
//                    catsDoc.setRootElement(cats.categoriesToElement());
//                    XMLOutputter outputter = new XMLOutputter();
//                    outputter.output(catsDoc, writer);
//                    writer.close();
//                    res.setStatus(HttpServletResponse.SC_OK);
//                } 
//                else if (handler.isCollectionURI(areq)) {
//                    // return a collection
//                    Feed col = handler.getCollection(areq);
//                    col.setFeedType(FEED_TYPE);
//                    WireFeedOutput wireFeedOutput = new WireFeedOutput();
//                    Document feedDoc = wireFeedOutput.outputJDom(col);
//                    res.setContentType("application/atom+xml; charset=utf-8");
//                    Writer writer = res.getWriter();
//                    XMLOutputter outputter = new XMLOutputter();
//                    outputter.setFormat(Format.getPrettyFormat());
//                    outputter.output(feedDoc, writer);
//                    writer.close();
//                    res.setStatus(HttpServletResponse.SC_OK);
//                } 
//                else if (handler.isEntryURI(areq)) {
//                    // return an entry
//                    Entry entry = handler.getEntry(areq);
//                    if (entry != null) {
//                        res.setContentType("application/atom+xml; type=entry; charset=utf-8");
//                        Writer writer = res.getWriter();
//                        Atom10Generator.serializeEntry(entry, writer);
//                        writer.close();
//                    } else {
//                        res.setStatus(HttpServletResponse.SC_NOT_FOUND);
//                    }
//                }
//                else if (handler.isMediaEditURI(areq)) {
//                    AtomMediaResource entry = handler.getMediaResource(areq);
//                    res.setContentType(entry.getContentType());
//                    res.setContentLength((int)entry.getContentLength());
//                    Utilities.copyInputToOutput(entry.getInputStream(), res.getOutputStream());
//                    res.getOutputStream().flush();
//                    res.getOutputStream().close();
//                }
//                else {
//                    res.setStatus(HttpServletResponse.SC_NOT_FOUND);
//                }
//            } catch (AtomException ae) {
//                res.sendError(ae.getStatus(), ae.getMessage());
//                log.info("ERROR processing GET");
//            } catch (Exception e) {
//                res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
//                log.info("ERROR processing GET");
//            }
//        } else {
//            res.setHeader("WWW-Authenticate", "BASIC realm=\"AtomPub\"");
//            res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//        }
//        log.info("Exiting");
//    }
}
